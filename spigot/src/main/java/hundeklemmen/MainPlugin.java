package hundeklemmen;


import hundeklemmen.api.DrupiLoadEvent;
import hundeklemmen.events.*;
import hundeklemmen.extra.PlaceholderAPIEventHandler;
import hundeklemmen.extra.PlaceholderAPIExtension;
import hundeklemmen.script.*;
import hundeklemmen.worldedit.WorldEditEvents;
import hundeklemmen.worldguard.Region;
import hundeklemmen.worldguard.WGRegionEventsListener;
import hundeklemmen.worldguard.WorldGuardEvents;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import javax.script.Invocable;
import javax.script.ScriptException;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainPlugin extends JavaPlugin implements Listener {

    public static NashornScriptEngine engine;
    public static MainPlugin instance;
    public static HashMap<String, Object> variables = new HashMap<String, Object>();
    public static HashMap<Object, Object> addons = new HashMap<Object, Object>();
    public static FileConfiguration config;
    public static File DrupiFile;

    public static boolean update = false;
    public static boolean updateNotifyOP = true;

    @Override
    public void onEnable() {
        instance = this;
        Metrics metrics = new Metrics(this);

        //saving the config
        instance.saveDefaultConfig();
        config = instance.getConfig();
        DrupiFile = this.getFile();
        updateNotifyOP = config.getBoolean("versionChecker.notifyOP");
        if(config.getBoolean("versionChecker.checkOnLoad")) {
            Utils.checkVersion();
        }

       /*try {
            WatchService watcher = FileSystems.getDefault().newWatchService();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getLogger().info("PlaceholderAPI found, loading PlaceholderManager class");
            new PlaceholderAPIExtension(instance, "drupi").hook();
            getServer().getPluginManager().registerEvents(new PlaceholderAPIEventHandler(), instance);
            getLogger().info("Hooked into placeholderapi");
        }
        getLogger().info("Loading Drupi-JS");
        getLogger().info("Loading variables");
        File variablesFile = new File(instance.getDataFolder(), "variables.csv");
        if(!variablesFile.exists()){
            saveResource("variables.csv", false);
        }
        loadVariables();
        getLogger().info("Starting engine");
        setupDrupi();

        this.getServer().getPluginManager().registerEvents(this, this);
        //new EventLoader(this);
        Utils.loadListeners(this,
                new PlayerEvents(),
                new EntityEvents(),
                new InventoryEvents(),
                new HangingEvents(),
                new EnchantEvents(),
                new BlockEvents(),
                new ServerEvents(),
                new VehicleEvents(),
                new WeatherEvents(),
                new WorldEvents());

        if(Bukkit.getPluginManager().getPlugin("WorldEdit") != null){
            this.getServer().getPluginManager().registerEvents(new WorldEditEvents(), this);
            this.getServer().getLogger().info("Hooked into WorldEdit events");
        }
        if(Bukkit.getPluginManager().getPlugin("WorldGuard") != null){
            this.getServer().getPluginManager().registerEvents(new WGRegionEventsListener(instance), this);
            this.getServer().getPluginManager().registerEvents(new WorldGuardEvents(), this);
            this.getServer().getLogger().info("Hooked into WorldGuard events");
        }
    }

    @Override
    public void onDisable(){
        if(!variables.isEmpty()) {
            saveVariables();
        }
    }

    public static void setupDrupi(){
        String[] options = new String[] {"--language=es6"};
        final NashornScriptEngineFactory  manager = new NashornScriptEngineFactory();
        engine = (NashornScriptEngine) manager.getScriptEngine(options);
        if (engine == null) {
            instance.getLogger().warning("No JavaScript engine was found!");
            return;
        }
        if (!(engine instanceof Invocable)) {
            instance.getLogger().warning("JavaScript engine does not support the Invocable API!");
            engine = null;
            return;
        }

        instance.getLogger().info("Javascript engine: " + engine.getFactory().getEngineName() + " " + engine.getFactory().getEngineVersion());
        instance.getLogger().info("Engine factories: " + engine.getFactory().getLanguageName() + " " + engine.getFactory().getLanguageVersion());

        instance.getLogger().info("Executing Drupi API event");
        DrupiLoadEvent loadE = new DrupiLoadEvent();
        MainPlugin.instance.getServer().getPluginManager().callEvent(loadE);

        engine.put("server",  instance.getServer());
        engine.put("plugin", instance);
        engine.put("manager", new FunctionManager(instance));
        engine.put("http", new HttpManager(instance));
        engine.put("cast", new CastManager(instance));
        engine.put("logger",  instance.getLogger());
        engine.put("variable", new VariableManager(instance));
        engine.put("scoreboard", new ScoreboardManager(instance));
        engine.put("players", instance.getServer().getOnlinePlayers().size());
        engine.put("material", new MaterialManager(instance));
        engine.put("database", new DatabaseManager(instance));

        try {
            engine.eval("function color(message){return manager.color(message);};");
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            engine.put("placeholderapi", new PlaceholderAPIManager(instance));
            instance.getLogger().info("Hooked into placeholder and registered onPlaceholderRequest event");
        }
        if(Bukkit.getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Economy> rspE = MainPlugin.instance.getServer().getServicesManager().getRegistration(Economy.class);
            if(rspE != null){
                engine.put("economy", rspE.getProvider());
                instance. getLogger().info("Hooked into vault Economy and registered economy variable");
            }
            RegisteredServiceProvider<Permission> rspP = MainPlugin.instance.getServer().getServicesManager().getRegistration(Permission.class);
            if(rspP != null){
                engine.put("permission", rspP.getProvider());
                instance.getLogger().info("Hooked into vault Permission and registered permission variable");
            }
        }
        if(Bukkit.getPluginManager().getPlugin("WorldEdit") != null){
            engine.put("worldedit", new WorldEditManager(instance));
            instance.getLogger().info("Hooked into WorldEdit");
        }
        if(Bukkit.getPluginManager().getPlugin("WorldGuard") != null){
            engine.put("worldguard", new Region(instance));
            instance.getLogger().info("Hooked into WorldGuard");
        }

        if (!instance.getDataFolder().exists()) {
            instance.getDataFolder().mkdir();
        }
        File defaultJS = new File(instance.getDataFolder(), "scripts");
        if(!defaultJS.exists()){
            defaultJS.mkdir();
            File defaultJSFile = new File(defaultJS, "default.js");
            Utils.copy(instance.getResource("default.js"), defaultJSFile);
        }
        File UtilsJSFile = new File(instance.getDataFolder(), "utils.js");
        if(!UtilsJSFile.exists()) {
            Utils.copy(instance.getResource("utils.js"), UtilsJSFile);
        }
        //instance.saveResource("utils.js", false);
        //saveResource("init.js");
        //Load Utils.js
        try (final Reader reader = new InputStreamReader(new FileInputStream(UtilsJSFile))) {
            engine.eval(reader);
            instance.getLogger().warning("Loaded Script: " + UtilsJSFile.getName());
        } catch (final Exception e) {
            instance.getLogger().warning("Could not load " + UtilsJSFile.getName());
            e.printStackTrace();
        }

        //Load other scripts from folder
        for (File file : Objects.requireNonNull(defaultJS.listFiles())) {
            if(file.isDirectory()) continue;
            if(file.getName().contains(".js")||file.getName().contains(".drupi")){
                if(!file.getName().substring(0, 1).equalsIgnoreCase("_")) {
                    try (final Reader reader = new InputStreamReader(new FileInputStream(file))) {
                        engine.eval(reader);
                        instance.getLogger().warning("Loaded Script: " + file.getName());
                    } catch (final Exception e) {
                        instance.getLogger().warning("Could not load " + file.getName());
                        e.printStackTrace();
                    }
                } else {
                    instance.getLogger().warning(file.getName() + " is disabled and was not loaded! Remove _ to enable it!");
                }
            }
        }
    }

    public void loadVariables(){
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(new File(instance.getDataFolder(), "variables.csv")))) {
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(cvsSplitBy);

                String originalName = data[0];
                String originalType = data[1];
                String originalContent = new String(DatatypeConverter.parseBase64Binary(data[2]));

                if(originalType.equals("cn.nukkit.Player")||originalType.equals("cn.nukkit.OfflinePlayer")) {
                    OfflinePlayer p = instance.getServer().getOfflinePlayer(originalContent);
                    variables.put(originalName, p);
                    getLogger().info("PlayerName: " + p.getName());
                } else {
                    variables.put(originalName, Class.forName(originalType).cast(originalContent));
                }

            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void saveVariables(){
        try {
            FileWriter fileWriter = new FileWriter(new File(instance.getDataFolder(), "variables.csv"));
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Map.Entry<String, Object> entry : variables.entrySet()) {
                String variableName = entry.getKey();
                String variableType = entry.getValue().getClass().getName();
                String variableContent = DatatypeConverter.printBase64Binary(String.valueOf(entry.getValue()).getBytes());
                if(variableType.equals("cn.nukkit.Player")||variableType.equals("cn.nukkit.OfflinePlayer")){
                    Player p = (Player) entry.getValue();
                    variableContent = DatatypeConverter.printBase64Binary(String.valueOf(p.getName()).getBytes());
                }
                printWriter.print(variableName + "," + variableType + "," + variableContent + "\n");
            }
            printWriter.close();
            instance.getLogger().info("Saved to file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getLabel().equalsIgnoreCase("drupi")) {
            if (args.length != 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        if (player.isOp()) {
                            sender.sendMessage(ChatColor.GREEN + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.GREEN + "]--------------");
                            sender.sendMessage(ChatColor.GREEN + "Reloading Scripts...");
                            try {
                                MainPlugin.setupDrupi();
                                sender.sendMessage(ChatColor.GREEN + "Successfully reloaded the scripts");
                            } catch (Exception e) {
                                sender.sendMessage(ChatColor.RED + "Whoops! Something went wrong");
                            }
                        } else {
                            sender.sendMessage(ChatColor.GREEN + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.GREEN + "]--------------");
                            sender.sendMessage(ChatColor.RED + "Whoops! It looks like you don't have access");
                        }
                    } else {
                        sender.sendMessage(ChatColor.GREEN + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.GREEN + "]--------------");
                        sender.sendMessage(ChatColor.GREEN + "Reloading Scripts...");
                        try {
                            MainPlugin.setupDrupi();
                            sender.sendMessage(ChatColor.GREEN + "Successfully reloaded the scripts");
                        } catch (Exception e) {
                            sender.sendMessage(ChatColor.RED + "Whoops! Something went wrong");
                        }
                    }
                } else if (args[0].equalsIgnoreCase("update")) {
                    if(args.length == 2){
                        if(args[1].equalsIgnoreCase("download")) {
                            boolean access = true;
                            if(sender instanceof Player){
                                if(!((Player) sender).isOp()){
                                    access = false;
                                }
                            }
                            if(access){
                                Utils.Update(sender);
                            } else {
                                sender.sendMessage(ChatColor.GREEN + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.GREEN + "]--------------");
                                sender.sendMessage(ChatColor.RED + "Whoops! It looks like you don't have access");
                            }
                        } else {
                            sender.sendMessage(ChatColor.GREEN + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.GREEN + "]--------------");
                            sender.sendMessage(ChatColor.AQUA + "Arguments:");
                            sender.sendMessage(ChatColor.AQUA + "/Drupi update " + ChatColor.GRAY + " - Check Drupi version.");
                            sender.sendMessage(ChatColor.AQUA + "/Drupi update download" + ChatColor.GRAY + " - Download the latest version of Drupi");
                        };
                    } else {
                        sender.sendMessage(ChatColor.GREEN + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.GREEN + "]--------------");
                        sender.sendMessage(ChatColor.GREEN + "You're currently running " + ChatColor.AQUA + MainPlugin.instance.getDescription().getVersion());
                        sender.sendMessage(ChatColor.GREEN + "Latest version: " + ChatColor.AQUA + Utils.getLatestVersion());
                        sender.sendMessage(ChatColor.GREEN + "Releases: " + ChatColor.AQUA + "https://drupi.xyz/releases");
                    }
                } else {
                    sender.sendMessage(ChatColor.GREEN + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.GREEN + "]--------------");
                    sender.sendMessage(ChatColor.GREEN + "/Drupi reload " + ChatColor.AQUA + "Reload your scripts");
                    sender.sendMessage(ChatColor.GREEN + "/Drupi update " + ChatColor.AQUA + "Update Drupi");
                }
            } else {
                sender.sendMessage(ChatColor.GREEN + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.GREEN + "]--------------");
                sender.sendMessage(ChatColor.GREEN + "/Drupi reload " + ChatColor.AQUA + "Reload your scripts");
                sender.sendMessage(ChatColor.GREEN + "/Drupi update " + ChatColor.AQUA + "Update Drupi");
            }
        }
        return true;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if(updateNotifyOP) {
            Player player = event.getPlayer();
            if (player.isOp()) {
                if (update) {
                    String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "Drupi" + ChatColor.GRAY + "]" + ChatColor.WHITE;
                    player.sendMessage(prefix + " It looks like Drupi is out of date!");
                    player.sendMessage(prefix + " Use /Drupi update download to download the lateset version!");
                }
            }
        }
    }

    public synchronized void callEventHandler(final Event e, final String functionName) {
        if (engine.get(functionName) == null) {
            return;
        }
        try {
            ((Invocable) engine).invokeFunction(functionName, e);
        } catch (final Exception se) {
            getLogger().warning("Error while calling " + functionName);
            se.printStackTrace();
        }
    }

    public synchronized void callCommand(CommandSender sender, String[] args, String functionName){
        if(engine.get(functionName) == null){
            return;
        }
        try {
            ((Invocable) engine).invokeFunction(functionName, sender, args);
        } catch (final Exception se) {
            getLogger().warning("Error while calling " + functionName);
            se.printStackTrace();
        }
    }

    public synchronized void call(String functionName, Object... args){
        if(engine.get(functionName) == null){
            return;
        }
        try {
            ((Invocable) engine).invokeFunction(functionName, args);
        } catch (final Exception se) {
            getLogger().warning("Error while calling " + functionName);
            se.printStackTrace();
        }
    }

    private synchronized Object eval(final CommandSender sender, final String expression) throws ScriptException {
        if (sender != null && sender instanceof Player) {
            Player player = (Player) sender;
            engine.put("me", player);
            engine.put("level", player.getLocation().getY());
            engine.put("pos", player.getLocation());
        } else {
            engine.put("me", null);
        }
        return engine.eval(expression);
    }
}