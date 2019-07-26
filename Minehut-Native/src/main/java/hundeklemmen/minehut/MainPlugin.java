package hundeklemmen.minehut;


import hundeklemmen.minehut.api.handlers.SpigotConfig;
import hundeklemmen.minehut.expansions.Vault;
import hundeklemmen.minehut.expansions.placeholderapi.PlaceholderAPIEventHandler;
import hundeklemmen.minehut.expansions.placeholderapi.PlaceholderAPIExtension;
import hundeklemmen.minehut.expansions.skript.SkAddon;
import hundeklemmen.minehut.script.*;
import hundeklemmen.shared.api.Drupi;
import hundeklemmen.shared.api.DrupiScript;
import hundeklemmen.shared.api.Platform;
import hundeklemmen.shared.api.interfaces.ScriptLoadMessage;
import hundeklemmen.shared.api.interfaces.SetupMessage;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;



public class MainPlugin extends JavaPlugin implements Listener {

    public static MainPlugin instance;
    public static HashMap<String, Object> variables = new HashMap<String, Object>();
    public static File DrupiFile;
    public static String serverVersion;

    public static int ErrorAmount = 0;


    public static Drupi drupi;
    public static boolean dev = false;

    @Override
    public void onEnable() {
        instance = this;
        Metrics metrics = new Metrics(this); //OOF
        DrupiFile = instance.getFile();
        serverVersion = instance.getServer().getClass().getPackage().getName().split("\\.")[3];
        //Prepare
        if (!instance.getDataFolder().exists()) {
            instance.getDataFolder().mkdir();
        }
        File defaultJS = new File(instance.getDataFolder(), "scripts");
        if(!defaultJS.exists()){
            defaultJS.mkdir();
            File defaultJSFile = new File(defaultJS, "default.drupi");
            util.copy(instance.getResource("default.drupi"), defaultJSFile);
        }
        File UtilsJSFile = new File(instance.getDataFolder(), "utils.drupi");
        if(!UtilsJSFile.exists()) {
            util.copy(instance.getResource("utils.drupi"), UtilsJSFile);
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

        drupi.LoadVariables();
        devLog("[DEV] Setup proccess!");

        devLog("Registering global managers");
        //Register managers:
        drupi.registerManager("server", instance.getServer());
        drupi.registerManager("plugin", instance);
        drupi.registerManager("manager", new FunctionManager(instance));

        drupi.registerManager("cast", new castManager());
        drupi.registerManager("logger", instance.getLogger());
        drupi.registerManager("variable", new variableManager(instance));
        drupi.registerManager("scoreboard", new scoreboardManager(instance));
        drupi.registerManager("material", new materialManager(instance));

        if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
            drupi.registerManager("vault", new Vault());
        }

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            drupi.log.info("PlaceholderAPI found, activating PlaceholderAPI expansion class.");
            new PlaceholderAPIExtension().register();
            this.getServer().getPluginManager().registerEvents(new PlaceholderAPIEventHandler(), instance);
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
                File UtilsJSFile = new File(instance.getDataFolder(), "utils.drupi");
                devLog("[DEV] Loading managers!");


                devLog("[DEV] Done loading managers!");

                drupi.log.info("Loading scripts..");
                ErrorAmount = 0;

                DrupiScript UtilsDS = new DrupiScript(UtilsJSFile);
                UtilsDS.Load(drupi, new ScriptLoadMessage() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(String error){
                        MainPlugin.ErrorAmount++;
                    }
                });

                File defaultJS = new File(instance.getDataFolder(), "scripts");
                for (File file : Objects.requireNonNull(defaultJS.listFiles())) {
                    if(file.isDirectory()) continue;
                    if(file.getName().toLowerCase().contains(".drupi")){
                        if(!file.getName().substring(0, 1).equalsIgnoreCase("_")) {
                            DrupiScript DS = new DrupiScript(file);
                            DS.Load(drupi, new ScriptLoadMessage() {
                                @Override
                                public void onSuccess() {

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
                }
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
            drupi.log.info("Loading 1.11 events");
            new hundeklemmen.v1_11.loadEvents(drupi); //From v1_11 module
            drupi.log.info("Loaded 1.11 events");
        } else if(serverVersion.startsWith("v1_12")){
            drupi.log.info("Loading 1.12 events");
            new hundeklemmen.v1_12.loadEvents(drupi); //From v1_12 module
            drupi.log.info("Loaded 1.12 events");
        } else if(serverVersion.startsWith("v1_13")){
            drupi.log.info("Loading 1.13 events");
            new hundeklemmen.v1_13.loadEvents(drupi); //From v1_13 module
            drupi.log.info("Loaded 1.13 events");
        } else if(serverVersion.startsWith("v1_14")){
            drupi.log.info("Loading 1.14 events");
            new hundeklemmen.v1_14.loadEvents(drupi); //From v1_14 module
            drupi.log.info("Loaded 1.14 events");
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
        Bukkit.getScheduler().cancelTasks(instance);
        try {
            util.unregisterCommands();
        } catch (Exception e){
            drupi.log.warning("Error while unregistering commands!");
            drupi.log.warning(e.getMessage());
        }
        drupi.Setup(new SetupMessage() {
            @Override
            public void onMessage(String message) {
                //Idk why ¯\_(ツ)_/¯
            }

            @Override
            public void loadManagers(NashornScriptEngine engine) {
                File UtilsJSFile = new File(instance.getDataFolder(), "utils.drupi");
                devLog("[DEV] Loading managers!");

                devLog("[DEV] Done loading managers!");

                drupi.log.info("Loading scripts..");
                ErrorAmount = 0;

                sender.sendMessage("§a[§bDrupi§a] "+ "§aReloading all scripts..");
                DrupiScript UtilsDS = new DrupiScript(UtilsJSFile);
                UtilsDS.Load(drupi, new ScriptLoadMessage() {
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
                File defaultJS = new File(instance.getDataFolder(), "scripts");
                for (File file : Objects.requireNonNull(defaultJS.listFiles())) {
                    if(file.isDirectory()) continue;
                    if(file.getName().toLowerCase().contains(".js")||file.getName().toLowerCase().contains(".drupi")){
                        if(!file.getName().substring(0, 1).equalsIgnoreCase("_")) {
                            DrupiScript DS = new DrupiScript(file);
                            DS.Load(drupi, new ScriptLoadMessage() {
                                @Override
                                public void onSuccess() {
                                    sender.sendMessage("§aLoaded Script §b" + file.getName() + "§a successfully");
                                }

                                @Override
                                public void onError(String error){
                                    sender.sendMessage("§cCould not load §a" + file.getName());
                                    sender.sendMessage("§4[ERROR]§c " + error);
                                    MainPlugin.ErrorAmount++;
                                }
                            });
                        } else {
                            drupi.log.warning(file.getName() + " is disabled and was not loaded! Remove _ to enable it!");
                        }
                    }
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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getLabel().equalsIgnoreCase("drupi")) {
            if (args.length != 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        if (player.isOp() || player.hasPermission("drupi.reload")) {
                            sender.sendMessage(ChatColor.GREEN + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.GREEN + "]--------------");
                            reloadDrupi(sender);
                        } else {
                            sender.sendMessage(ChatColor.GREEN + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.GREEN + "]--------------");
                            sender.sendMessage(ChatColor.RED + "Whoops! It looks like you don't have access");
                        }
                    } else {
                        sender.sendMessage(ChatColor.GREEN + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.GREEN + "]--------------");
                        reloadDrupi(sender);
                    }
                } else if (args[0].equalsIgnoreCase("update")) {
                    sender.sendMessage(ChatColor.GREEN + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.GREEN + "]--------------");
                    sender.sendMessage(ChatColor.AQUA + "Go to our SpigotMC resources page to see updates and much more!");
                    sender.sendMessage(ChatColor.AQUA + "https://www.spigotmc.org/resources/drupi-js.65706/updates");
                    sender.sendMessage(ChatColor.GREEN + "Current version: " + ChatColor.AQUA + MainPlugin.instance.getDescription().getVersion());

                } else if (args[0].equalsIgnoreCase("support")||args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage(ChatColor.GREEN + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.GREEN + "]--------------");
                    sender.sendMessage(ChatColor.AQUA + "You can get support / help in our official community discord");
                    sender.sendMessage(ChatColor.AQUA + "https://discord.gg/XhGUykp");

                } else if (args[0].equalsIgnoreCase("about")) {
                    sender.sendMessage(ChatColor.GREEN + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.GREEN + "]--------------");
                    sender.sendMessage(ChatColor.AQUA + "Drupi is a plugin that allows you to create custom plugins easily using JavaScript.");
                    sender.sendMessage(ChatColor.AQUA + "Drupi is developed and maintained by Hundeklemmen.");
                    sender.sendMessage(ChatColor.GREEN + "Links:");
                    sender.sendMessage(ChatColor.GREEN + "SpigotMC: " + ChatColor.AQUA + "https://www.spigotmc.org/resources/drupi-js.65706/");
                    sender.sendMessage(ChatColor.GREEN + "GitHub: " + ChatColor.AQUA + "https://github.com/drupijs/Drupi-JS");


                } else {
                    sender.sendMessage(ChatColor.GREEN + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.GREEN + "]--------------");
                    sender.sendMessage(ChatColor.GREEN + "/drupi reload " + ChatColor.AQUA + "Reload scripts");
                    sender.sendMessage(ChatColor.GREEN + "/drupi update " + ChatColor.AQUA + "Update page");
                    sender.sendMessage(ChatColor.GREEN + "/drupi support " + ChatColor.AQUA + "Links to our Community Discord");
                    sender.sendMessage(ChatColor.GREEN + "/drupi help " + ChatColor.AQUA + "Links to our Community Discord");
                    sender.sendMessage(ChatColor.GREEN + "/drupi about " + ChatColor.AQUA + "About the plugin");
                }
            } else {
                sender.sendMessage(ChatColor.GREEN + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.GREEN + "]--------------");
                sender.sendMessage(ChatColor.GREEN + "/drupi reload " + ChatColor.AQUA + "Reload scripts");
                sender.sendMessage(ChatColor.GREEN + "/drupi update " + ChatColor.AQUA + "Update page");
                sender.sendMessage(ChatColor.GREEN + "/drupi support " + ChatColor.AQUA + "Links to our Community Discord");
                sender.sendMessage(ChatColor.GREEN + "/drupi help " + ChatColor.AQUA + "Links to our Community Discord");
                sender.sendMessage(ChatColor.GREEN + "/drupi about " + ChatColor.AQUA + "About the plugin");
            }
        }
        return true;
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
}