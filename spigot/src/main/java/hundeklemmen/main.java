package hundeklemmen;


import hundeklemmen.api.DrupiLoadEvent;
import hundeklemmen.extra.PlaceholderAPIEventHandler;
import hundeklemmen.extra.PlaceholderAPIExtension;
import hundeklemmen.script.*;
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
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.script.Invocable;
import javax.script.ScriptException;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class main extends JavaPlugin implements Listener {

    public static NashornScriptEngine engine;
    public static main instance;
    public static HashMap<String, Object> variables = new HashMap<String, Object>();
    public static HashMap<Object, Object> addons = new HashMap<Object, Object>();

    public static boolean update = false;

    @Override
    public void onEnable() {
        instance = this;
        Metrics metrics = new Metrics(this);


        util.checkVersion();
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
        String[] options = new String[] {"--language=es6"};
        final NashornScriptEngineFactory  manager = new NashornScriptEngineFactory();
        engine = (NashornScriptEngine) manager.getScriptEngine(options);
        if (engine == null) {
            getLogger().warning("No JavaScript engine was found!");
            return;
        }
        if (!(engine instanceof Invocable)) {
            getLogger().warning("JavaScript engine does not support the Invocable API!");
            engine = null;
            return;
        }
        getLogger().info("Javascript engine: " + engine.getFactory().getEngineName() + " " + engine.getFactory().getEngineVersion());
        getLogger().info("Engine factories: " + engine.getFactory().getLanguageName() + " " + engine.getFactory().getLanguageVersion());
        getLogger().info("Engine loaded");
        getLogger().info("Registering javascript global variables");
        engine.put("server", getServer());
        engine.put("plugin", this);
        engine.put("manager", new FunctionManager(instance));
        engine.put("http", new httpManager(instance));
        engine.put("cast", new castManager(instance));
        engine.put("logger", getLogger());
        engine.put("variable", new variableManager(instance));
        engine.put("scoreboard", new scoreboardManager(instance));
        engine.put("players", 0);
        engine.put("material", new materialManager(instance));
        engine.put("database", new databaseManager(instance));

        getLogger().info("Global variables registered");

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            engine.put("placeholderapi", new placeholderAPIManager(instance));
            getLogger().info("Hooked into placeholder and registered onPlaceholderRequest event");
        }
        if(Bukkit.getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Economy> rspE = main.instance.getServer().getServicesManager().getRegistration(Economy.class);
            if(rspE != null){
                engine.put("economy", rspE.getProvider());
                instance. getLogger().info("Hooked into vault Economy and registered economy variable");
            }
            RegisteredServiceProvider<Permission> rspP = main.instance.getServer().getServicesManager().getRegistration(Permission.class);
            if(rspP != null){
                engine.put("permission", rspP.getProvider());
                instance.getLogger().info("Hooked into vault Permission and registered permission variable");
            }
        }

        DrupiLoadEvent loadE = new DrupiLoadEvent();
        main.instance.getServer().getPluginManager().callEvent(loadE);

        getLogger().info("Loading scripts");
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File defaultJS = new File(instance.getDataFolder(), "scripts");
        if(!defaultJS.exists()){
            defaultJS.mkdir();
            File defaultJSFile = new File(defaultJS, "default.js");
            util.copy(instance.getResource("default.js"), defaultJSFile);
        }
        File UtilsJSFile = new File(instance.getDataFolder(), "utils.js");
        if(!UtilsJSFile.exists()) {
            util.copy(instance.getResource("utils.js"), UtilsJSFile);
        }
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
            if(file.getName().contains(".js")){
                if(!file.getName().substring(0, 1).equalsIgnoreCase("_")) {
                    try (final Reader reader = new InputStreamReader(new FileInputStream(file))) {
                        engine.eval(reader);
                        getLogger().warning("Loaded Script: " + file.getName());
                    } catch (final Exception e) {
                        getLogger().warning("Could not load " + file.getName());
                        e.printStackTrace();
                    }
                } else {
                    getLogger().warning(file.getName() + " is disabled and was not loaded! Remove _ to enable it!");
                }
            }
        }
        getLogger().info("All scripts have been loaded!");

        this.getServer().getScheduler().scheduleSyncRepeatingTask(main.instance, new BukkitRunnable() {
            @Override
            public void run() {
                int playersOnline = Bukkit.getServer().getOnlinePlayers().size();
                engine.put("players", playersOnline);
            }
        }, 20, 20);

        this.getServer().getPluginManager().registerEvents(this, this);


        new EventLoader(this);
    }

    @Override
    public void onDisable(){
        if(!variables.isEmpty()) {
            saveVariables();
        }
    }

    public static void reload(){
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

        engine.put("server",  instance.getServer());
        engine.put("plugin", instance);
        engine.put("manager", new FunctionManager(instance));
        engine.put("http", new httpManager(instance));
        engine.put("cast", new castManager(instance));
        engine.put("logger",  instance.getLogger());
        engine.put("variable", new variableManager(instance));
        engine.put("scoreboard", new scoreboardManager(instance));
        engine.put("players", 0);
        engine.put("material", new materialManager(instance));
        engine.put("database", new databaseManager(instance));

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            engine.put("placeholderapi", new placeholderAPIManager(instance));
            instance.getLogger().info("Hooked into placeholder and registered onPlaceholderRequest event");
        }
        if(Bukkit.getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Economy> rspE = main.instance.getServer().getServicesManager().getRegistration(Economy.class);
            if(rspE != null){
                engine.put("economy", rspE.getProvider());
                instance. getLogger().info("Hooked into vault Economy and registered economy variable");
            }
            RegisteredServiceProvider<Permission> rspP = main.instance.getServer().getServicesManager().getRegistration(Permission.class);
            if(rspP != null){
                engine.put("permission", rspP.getProvider());
                instance.getLogger().info("Hooked into vault Permission and registered permission variable");
            }
        }

        DrupiLoadEvent loadE = new DrupiLoadEvent();
        main.instance.getServer().getPluginManager().callEvent(loadE);

        if (!instance.getDataFolder().exists()) {
            instance.getDataFolder().mkdir();
        }
        File defaultJS = new File(instance.getDataFolder(), "scripts");
        if(!defaultJS.exists()){
            defaultJS.mkdir();
            File defaultJSFile = new File(defaultJS, "default.js");
            util.copy(instance.getResource("default.js"), defaultJSFile);
        }
        File UtilsJSFile = new File(instance.getDataFolder(), "utils.js");
        if(!UtilsJSFile.exists()) {
            util.copy(instance.getResource("utils.js"), UtilsJSFile);
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
            if(file.getName().contains(".js")){
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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if(sender instanceof Player) {
                        Player player = (Player) sender;
                        if (player.isOp()) {
                            sender.sendMessage("Reloading scripts");
                            try {
                                main.reload();
                                sender.sendMessage("Reloaded scripts!");
                            } catch (Exception e) {
                                sender.sendMessage("Something went wrong!");
                            }
                        } else {
                            player.sendMessage("Whoups! It requires OP to reload scripts/Drupis!");
                        }
                    } else {
                        sender.sendMessage("Reloading scripts");
                        try {
                            main.reload();
                            sender.sendMessage("Reloaded scripts!");
                        } catch (Exception e) {
                            sender.sendMessage("Something went wrong!");
                        }
                    }
                } else {
                    sender.sendMessage("Uknown argument");
                }
            } else {
                sender.sendMessage("usage: /drupi reload - Reload js scripts");
            }
        }
        return true;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(player.isOp() == true){
            if(main.instance.update == true){
                String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "Drupi" + ChatColor.GRAY + "]" + ChatColor.WHITE;
                player.sendMessage(prefix + " It looks like Drupi is out of date!");
                player.sendMessage(prefix + " Please update Drupi to get the latest features and bug fixes");
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