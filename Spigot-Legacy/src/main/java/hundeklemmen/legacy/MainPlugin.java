package hundeklemmen.legacy;

import express.Express;
import hundeklemmen.legacy.api.handlers.SpigotConfig;
import hundeklemmen.legacy.commands.drupiCommand;
import hundeklemmen.legacy.expansions.labymod.LabymodEvents;
import hundeklemmen.legacy.expansions.placeholderapi.PlaceholderAPIExtension;
import hundeklemmen.legacy.expansions.skript.SkAddon;
import hundeklemmen.legacy.script.*;
import hundeklemmen.shared.api.Drupi;
import hundeklemmen.shared.api.DrupiScript;
import hundeklemmen.shared.api.Platform;
import hundeklemmen.shared.api.interfaces.ScriptLoadMessage;
import hundeklemmen.shared.api.interfaces.SetupMessage;
import io.puharesource.mc.titlemanager.api.v2.TitleManagerAPI;
import io.socket.client.Socket;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import net.labymod.serverapi.bukkit.LabyModPlugin;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;


public class MainPlugin extends JavaPlugin implements Listener, PluginMessageListener {

    public static MainPlugin instance;
    public static HashMap<String, Object> variables = new HashMap<String, Object>();
    public static File DrupiFile;
    public static String serverVersion;

    public ArrayList<Socket> sockets = new ArrayList<Socket>();
    public ArrayList<Express> ExpressRunning = new ArrayList<>();

    public static int ErrorAmount = 0;

    public static Drupi drupi;
    public static boolean dev = false;

    public static int loadedScripts = 0;
    public static ArrayList<String> loadedModules = new ArrayList<String>();


    @Override
    public void onEnable() {
        instance = this;
        Metrics metrics = new Metrics(this); //OOF
        DrupiFile = instance.getFile();
        serverVersion = instance.getServer().getClass().getPackage().getName().split("\\.")[3];

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        registerCommands();

        //Prepare
        if (!instance.getDataFolder().exists()) {
            instance.getDataFolder().mkdir();
        }
        File defaultJS = new File(instance.getDataFolder(), "scripts");
        if(!defaultJS.exists()){
            defaultJS.mkdir();
            File defaultJSFile = new File(defaultJS, "default.js");
            util.copy(instance.getResource("default.js"), defaultJSFile);
        }
        File defaultModules = new File(defaultJS, "modules");
        if(!defaultModules.exists()){
            defaultModules.mkdir();
        }
        File UtilsJSFile = new File(instance.getDataFolder(), "utils.js");
        if(!UtilsJSFile.exists()) {
            util.copy(instance.getResource("utils.js"), UtilsJSFile);
        }

        File BabelJSFile = new File(instance.getDataFolder(), "babel.js");
        if(!BabelJSFile.exists()) {
            util.copy(instance.getResource("babel.js"), BabelJSFile);
        }

        File variablesFile = new File(instance.getDataFolder(), "variables.csv");
        if(!variablesFile.exists()) {
            saveResource("variables.csv", false);
        }
        drupi = new Drupi(Platform.Spigot, instance.getLogger(), instance.getDataFolder(), instance.getFile(), (Plugin) instance);
        drupi.setConfig(new SpigotConfig(instance));
        drupi.setVersion(MainPlugin.instance.getDescription().getVersion());

        if(drupi.config.VC_checkOnLoad == true){
            drupi.CheckForUpdate();
        }
        if(drupi.config.compileMethod.equalsIgnoreCase("modern")){
            try {
                drupi.startCompileEngine();
                devLog("[DEV] Loading babel on compile engine");

                DrupiScript BabelJSDS = new DrupiScript(BabelJSFile);
                BabelJSDS.Load(drupi, drupi.compileEngine, false, new ScriptLoadMessage() {
                    @Override
                    public void onSuccess() {
                        drupi.log.info("Babel loaded successfully");
                    }

                    @Override
                    public void onError(String error){
                        drupi.log.warning("Babel error! " + error);
                    }
                });
                drupi.compileEngine.eval("function convertBabelJS(i){return Babel.transform(i, {presets:[['es2015',{loose: !0,modules: !1}]],}).code}");
            } catch (ScriptException e) {
                drupi.config.compileMethod = "legacy"; //Defaulting back to legacy
                e.printStackTrace();
            }


        }

        drupi.LoadVariables();
        devLog("[DEV] Setup proccess!");

        devLog("Registering global managers");
        //Register managers:

        drupi.registerManager("event", new hundeklemmen.legacy.api.handlers.EventHandler(drupi));
        drupi.registerManager("server", instance.getServer());
        drupi.registerManager("drupi", drupi);
        drupi.registerManager("plugin", instance);
        drupi.registerManager("manager", new FunctionManager(instance, drupi));
        drupi.registerManager("command", new commandManager(drupi));
        drupi.registerManager("drupihelper", new drupiHelper(instance));

        drupi.registerManager("cast", new castManager());
        drupi.registerManager("logger", instance.getLogger());
        drupi.registerManager("variable", new variableManager(instance));
        drupi.registerManager("scoreboard", new scoreboardManager(instance));
        drupi.registerManager("material", new materialManager(instance));
        drupi.registerManager("socket", new socketManager(instance));
        drupi.registerManager("Express", new ExpressManager(instance));

        if (Bukkit.getPluginManager().getPlugin("TitleManager") != null) {
            drupi.log.info("TitleManager found, activating TitleManager expansion class.");
            TitleManagerAPI TitleManagerapi = (TitleManagerAPI) Bukkit.getServer().getPluginManager().getPlugin("TitleManager");
            drupi.registerManager("TitleManager", TitleManagerapi);
        }
        if (Bukkit.getPluginManager().getPlugin("LabyModAPI") != null) {
            drupi.log.info("LabyModAPI found, activating LabyModAPI expansion class.");
            drupi.registerManager("LabyModAPI", LabyModPlugin.getInstance());
            this.getServer().getPluginManager().registerEvents(new LabymodEvents(drupi), this);
        }
        
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            drupi.log.info("PlaceholderAPI found, activating PlaceholderAPI expansion class.");
            new PlaceholderAPIExtension().register();
            //this.getServer().getPluginManager().registerEvents(new PlaceholderAPIEventHandler(), instance);
            drupi.registerManager("placeholderapi", new placeholderAPIManager(instance));
        }

        if(Bukkit.getPluginManager().getPlugin("WorldGuard") != null){
            if(serverVersion.startsWith("v1_8")||serverVersion.startsWith("v1_9")||serverVersion.startsWith("v1_10")||serverVersion.startsWith("v1_11")||serverVersion.startsWith("v1_12")) {
                drupi.log.info("WorldGuard found, activating WorldGuard 1.8-1.12 expansion class.");
                this.getServer().getPluginManager().registerEvents(new hundeklemmen.v1_8.expansions.worldguard.WGRegionEventsListener(drupi), this);
                this.getServer().getPluginManager().registerEvents(new hundeklemmen.v1_8.expansions.worldguard.worldguardEvents(drupi), this);
                drupi.registerManager("worldguard", new hundeklemmen.v1_8.expansions.worldguard.WorldguardAPIManager(drupi));
                drupi.log.info("Hooked into WorldGuard events");
            } else {
                drupi.log.info("WorldGuard found, activating WorldGuard 1.13+ expansion class.");
                this.getServer().getPluginManager().registerEvents(new hundeklemmen.v1_13.expansions.worldguard.Listeners(), this);
                this.getServer().getPluginManager().registerEvents(new hundeklemmen.v1_13.expansions.worldguard.DrupiWGListener(drupi), this);
                drupi.registerManager("worldguard", new hundeklemmen.v1_13.expansions.worldguard.WorldguardAPIManager(drupi));
                drupi.log.info("Hooked into WorldGuard events");
            }
        }

        if (Bukkit.getPluginManager().getPlugin("Holograms") != null) {
            drupi.log.info("Holograms found, activating Holograms expansion class.");
            drupi.registerManager("holograms", new hundeklemmen.v1_8.expansions.Holograms.HologramApi(drupi));
        }

        if (Bukkit.getPluginManager().getPlugin("Skript") != null) {
            drupi.log.info("Skript found, activating Skript expansion class.");
            new SkAddon(drupi);
        }

        drupi.Setup(new SetupMessage() {
            @Override
            public void onMessage(String message) {
                //Idk why ¯\_(ツ)_/¯
            }

            @Override
            public void loadManagers(NashornScriptEngine engine) {
                File UtilsJSFile = new File(instance.getDataFolder(), "utils.js");
                if(drupi.config.compileMethod.equalsIgnoreCase("legacy")) {
                    drupi.log.info("Loading babel.js");
                    File BabelJSFile = new File(instance.getDataFolder(), "babel.js");
                    devLog("[DEV] Loading managers!");

                    DrupiScript BabelJSDS = new DrupiScript(BabelJSFile);
                    BabelJSDS.Load(drupi, drupi.engine, false, new ScriptLoadMessage() {
                        @Override
                        public void onSuccess() {
                            drupi.log.info("Babel.js loaded successfully");
                        }

                        @Override
                        public void onError(String error) {
                            drupi.log.warning("Babel error! " + error);
                        }
                    });
                }


                devLog("[DEV] Done loading managers!");


                drupi.log.info("Loading scripts..");
                ErrorAmount = 0;

                DrupiScript UtilsDS = new DrupiScript(UtilsJSFile);
                UtilsDS.Load(drupi, drupi.engine,false, new ScriptLoadMessage() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(String error){
                        MainPlugin.ErrorAmount++;
                    }
                });


                loadedScripts = 0;

                File defaultJS = new File(instance.getDataFolder(), "scripts");
                try {
                    Files.walk(defaultJS.toPath())
                            .filter(path -> !Files.isDirectory(path))
                            .sorted()
                            .forEach(path -> {
                                if(!path.toString().contains(new File(defaultJS, "modules").getPath())){
                                    File file = new File(path.toString());
                                    if(file.getName().toLowerCase().endsWith(".js")||file.getName().toLowerCase().endsWith(".drupi")) {
                                        if (!file.getName().startsWith("_")) {
                                            DrupiScript DS = new DrupiScript(file);
                                            DS.Load(drupi, drupi.engine,true, new ScriptLoadMessage() {
                                                @Override
                                                public void onSuccess() {
                                                    loadedScripts++;
                                                }

                                                @Override
                                                public void onError(String error) {
                                                    MainPlugin.ErrorAmount++;
                                                }
                                            });
                                        } else {
                                            drupi.log.warning(file.getName() + " is disabled and was not loaded! Remove _ to enable it!");
                                        }
                                    }
                                }
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }

                /*for (File file : Objects.requireNonNull(defaultJS.listFiles())) {
                    if(file.isDirectory()) continue;
                    if(file.getName().toLowerCase().contains(".js")||file.getName().toLowerCase().contains(".drupi")){
                        if(!file.getName().substring(0, 1).equalsIgnoreCase("_")) {
                            DrupiScript DS = new DrupiScript(file);
                            DS.Load(drupi, true, new ScriptLoadMessage() {
                                @Override
                                public void onSuccess() {
                                    loadedScripts++;
                                }

                                @Override
                                public void onError(String error){
                                    MainPlugin.ErrorAmount++;
                                }
                            });
                        } else {
                            drupi.log.warning(file.getName() + " is disabled and was not loaded! Remove _ to enable it!");
                        }
                    }
                }*/
                if(ErrorAmount == 0) {
                    drupi.log.info("Successfully reloaded all scripts!");
                } else {
                    drupi.log.info("Loaded all scripts with a total of "+ ErrorAmount + " errors!");
                }
            }
        });

//Load other scripts from folder


        if(serverVersion.startsWith("v1_8")) {
            drupi.log.info("Loading 1.8 events");
            new hundeklemmen.v1_8.loadEvents(drupi); //From v1_8 module
            drupi.log.info("Loaded 1.8 events");
        } else if(serverVersion.startsWith("v1_9")){
            drupi.log.info("Loading 1.9 events");
            new hundeklemmen.v1_9.loadEvents(drupi); //From v1_9 module
            drupi.log.info("Loaded 1.9 events");
        } else if(serverVersion.startsWith("v1_10")){
            drupi.log.info("Loading 1.10 events");
            new hundeklemmen.v1_10.loadEvents(drupi); //From v1_10 module
            drupi.log.info("Loaded 1.10 events");
        } else if(serverVersion.startsWith("v1_11")){
            drupi.log.info("Loading 1.11 1events");
            new hundeklemmen.v1_11.loadEvents(drupi); //From v1_11 module
            drupi.log.info("Loaded 1.11 events");
        } else if(serverVersion.startsWith("v1_12")){
            drupi.log.info("Loading 1.12 1events");
            new hundeklemmen.v1_12.loadEvents(drupi); //From v1_12 module
            drupi.log.info("Loaded 1.12 events");
        } else if(serverVersion.startsWith("v1_13")){
            drupi.log.info("Loading 1.13 1events");
            new hundeklemmen.v1_13.loadEvents(drupi); //From v1_13 module
            drupi.log.info("Loaded 1.13 events");
        } else if(serverVersion.startsWith("v1_14")){
            drupi.log.info("Loading 1.14 1events");
            new hundeklemmen.v1_14.loadEvents(drupi); //From v1_14 module
            drupi.log.info("Loaded 1.14 events");
        } else if(serverVersion.startsWith("v1_15")){
            drupi.log.info("Loading 1.15 events");
            new hundeklemmen.v1_15.loadEvents(drupi); //From v1_15 module
            drupi.log.info("Loaded 1.15 events");
        } else if(serverVersion.startsWith("v1_16")){
            drupi.log.info("Loading 1.16 events");
            new hundeklemmen.v1_16.loadEvents(drupi); //From v1_16 module
            drupi.log.info("Loaded 1.16 events");
        } else {
            drupi.log.warning("This Drupi version doesn't support " + serverVersion + " events! Fallback: 1.8 events");
            drupi.log.info("Loading 1.8 events");
            new hundeklemmen.v1_8.loadEvents(drupi); //From v1_8 module
            drupi.log.info("Loaded 1.8 events");
        }

        //Register drupi required events for bukkit events and 3rd party stuff.
        this.getServer().getPluginManager().registerEvents(this, this);
        /*hundeklemmen.legacy.util.loadListeners(this,
                new hundeklemmen.legacy.events.playerEvents(),
                new hundeklemmen.legacy.events.entityEvents(),
                new hundeklemmen.legacy.events.inventoryEvents(),
                new hundeklemmen.legacy.events.hangingEvents(),
                new hundeklemmen.legacy.events.enchantmentEvents(),
                new hundeklemmen.legacy.events.blockEvents(),
                new hundeklemmen.legacy.events.serverEvents(),
                new hundeklemmen.legacy.events.vehicleEvents(),
                new weatherEvents(),
                new hundeklemmen.legacy.events.worldEvents());


        if(Bukkit.getPluginManager().getPlugin("WorldEdit") != null){
            this.getServer().getPluginManager().registerEvents(new worldEditEvents(), this);
            this.getServer().getLogger().info("Hooked into WorldEdit events");
        }
        if(Bukkit.getPluginManager().getPlugin("WorldGuard") != null){
            this.getServer().getPluginManager().registerEvents(new WGRegionEventsListener(instance), this);
            this.getServer().getPluginManager().registerEvents(new worldguardEvents(), this);
            this.getServer().getLogger().info("Hooked into WorldGuard events");
        }
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getLogger().info("PlaceholderAPI found, loading PlaceholderManager class");
            new PlaceholderAPIExtension(instance, "drupi").hook();
            getServer().getPluginManager().registerEvents(new PlaceholderAPIEventHandler(), instance);
            getLogger().info("Hooked into placeholderapi");
        }*/
    }

    public void reloadDrupi(CommandSender sender){
        if(sockets.size() != 0){
            drupi.log.info("Disconnecting sockets..");
            for (int i = 0; i < sockets.size(); i++){
                sockets.get(i).disconnect();
            }
            sockets = new ArrayList<Socket>();
        }
        if(ExpressRunning.size() != 0){
            drupi.log.info("Stopping web servers..");
            for (int i = 0; i < ExpressRunning.size(); i++){
                ExpressRunning.get(i).stop();
            }
            ExpressRunning = new ArrayList<Express>();
        }
        loadedModules.clear();
        if(serverVersion.startsWith("v1_8")||serverVersion.startsWith("v1_9")||serverVersion.startsWith("v1_10")||serverVersion.startsWith("v1_11")||serverVersion.startsWith("v1_12")) {
            Bukkit.getScheduler().cancelAllTasks();
        } else {
            Bukkit.getScheduler().cancelTasks(instance);
        }
        try {
            util.unregisterCommands();
        } catch (Exception e){
            drupi.log.warning("Error while unregistering commands!");
            drupi.log.warning(e.getMessage());
        }

        sender.sendMessage("§a[§bDrupi§a] "+ "§aRemoving custom listeners.. ");
        try {
            ArrayList<RegisteredListener> handlers = HandlerList.getRegisteredListeners(instance);
            for (RegisteredListener handler : handlers) {
                if(handler.getListener() instanceof hundeklemmen.legacy.api.handlers.EventHandler.drupiCustomEvent){
                    HandlerList.unregisterAll(handler.getListener());
                }
            }
            sender.sendMessage("§a[§bDrupi§a] "+ "§aRemoved custom listeners. ");
        } catch (Exception e){
            e.printStackTrace();
            sender.sendMessage("§a[§bDrupi§a] "+ "§cSomething went wrong while trying to unregister all custom events!");
        }

        sender.sendMessage("§a[§bDrupi§a] "+ "§aReloading all scripts..");
        drupi.Setup(new SetupMessage() {
            @Override
            public void onMessage(String message) {
                //Idk why ¯\_(ツ)_/¯
            }

            @Override
            public void loadManagers(NashornScriptEngine engine) {
                File UtilsJSFile = new File(instance.getDataFolder(), "utils.js");
                if(drupi.config.compileMethod.equalsIgnoreCase("legacy")){
                    File BabelJSFile = new File(instance.getDataFolder(), "babel.js");
                    devLog("[DEV] Loading managers!");

                    DrupiScript BabelJSDS = new DrupiScript(BabelJSFile);
                    BabelJSDS.Load(drupi, drupi.engine, false, new ScriptLoadMessage() {
                        @Override
                        public void onSuccess() {
                            drupi.log.info("Babel loaded successfully");
                        }

                        @Override
                        public void onError(String error){
                            drupi.log.warning("Babel error! " + error);
                        }
                    });
                }


                devLog("[DEV] Done loading managers!");

                drupi.log.info("Loading scripts..");
                ErrorAmount = 0;

                DrupiScript UtilsDS = new DrupiScript(UtilsJSFile);
                UtilsDS.Load(drupi, drupi.engine,false, new ScriptLoadMessage() {
                    @Override
                    public void onSuccess() {
                        sender.sendMessage("§aLoaded Script §b" + UtilsJSFile.getName() + "§a successfully");
                    }

                    @Override
                    public void onError(String error){
                        sender.sendMessage("§cCould not load §a" + UtilsJSFile.getName());
                        sender.sendMessage("§4[ERROR]§c " + error);
                        MainPlugin.ErrorAmount++;
                    }
                });

                loadedScripts = 0;
                File defaultJS = new File(instance.getDataFolder(), "scripts");
                try {
                    Files.walk(defaultJS.toPath())
                            .filter(path -> !Files.isDirectory(path))
                            .sorted()
                            .forEach(path -> {
                                if(!path.toString().contains(new File(defaultJS, "modules").getPath())){
                                    File file = new File(path.toString());
                                    if(file.getName().toLowerCase().endsWith(".js")||file.getName().toLowerCase().endsWith(".drupi")) {
                                        if (!file.getName().startsWith("_")) {
                                            DrupiScript DS = new DrupiScript(file);
                                            String filePath = file.getPath();
                                            String scriptsPath = instance.drupi.DataFolder.toString() + java.io.File.separator + "scripts" + java.io.File.separator;
                                            String finalPath = filePath.replace(scriptsPath, "");
                                            DS.Load(drupi, drupi.engine,true, new ScriptLoadMessage() {
                                                @Override
                                                public void onSuccess() {
                                                    loadedScripts++;
                                                    sender.sendMessage("§aLoaded Script §b" + finalPath + "§a successfully");
                                                }

                                                @Override
                                                public void onError(String error) {
                                                    sender.sendMessage("§cCould not load §a" + finalPath);
                                                    sender.sendMessage("§4[ERROR]§c " + error);
                                                    MainPlugin.ErrorAmount++;
                                                }
                                            });
                                        } else {
                                            drupi.log.warning(file.getName() + " is disabled and was not loaded! Remove _ to enable it!");
                                        }
                                    }
                                }
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(ErrorAmount == 0) {
                    sender.sendMessage(util.color("§a[§bDrupi§a] "+ "&aSuccessfully reloaded all scripts!"));
                    drupi.log.info("Successfully reloaded all scripts!");
                } else {
                    sender.sendMessage(util.color("§a[§bDrupi§a] "+"§cLoaded all scripts with a total of §4"+ ErrorAmount + "§c errors!"));
                    drupi.log.info("Loaded all scripts with a total of "+ ErrorAmount + " errors!");
                }
            }
        });
    }

    @Override
    public void onDisable(){
        if(!drupi.variables.isEmpty()) {
            drupi.SaveVariables();
        }
    }

    public static void devLog(String Message){
        if(dev == true) {
            drupi.log.info(Message);
        }
    }

    private void registerCommands(){
        instance.getCommand("drupi").setExecutor(new drupiCommand(instance));
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if(drupi.config.VC_notifyOP == true && drupi.update == true) {
            Player player = event.getPlayer();
            if (player.isOp() == true) {
                String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "Drupi" + ChatColor.GRAY + "]" + ChatColor.WHITE;
                player.sendMessage(prefix + " It looks like Drupi is out of date!");
                player.sendMessage(prefix + " Use /Drupi update download to download the latest version!");
            }
        }
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        drupi.callFunction("onPluginMessageReceived", channel, player, message);
    }
}
