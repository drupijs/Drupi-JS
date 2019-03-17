package hundeklemmen;

import cn.nukkit.IPlayer;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Event;
import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.Task;
import cn.nukkit.utils.TextFormat;
import hundeklemmen.script.*;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.Invocable;
import javax.script.ScriptException;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class main extends PluginBase implements Listener {

    private static NashornScriptEngine engine;
    public static main instance;
    public static HashMap<String, Object> variables = new HashMap<String, Object>();

    @Override
    public void onEnable() {
        instance = this;
        this.saveResource("variables.csv");
        loadVariables();

        String[] options = new String[] {"--language=es6"};
        final NashornScriptEngineFactory manager = new NashornScriptEngineFactory();
        engine = (NashornScriptEngine) manager.getScriptEngine(options);
        if (engine == null) {
            getLogger().error("No JavaScript engine was found!");
            return;
        }
        if (!(engine instanceof Invocable)) {
            getLogger().error("JavaScript engine does not support the Invocable API!");
            engine = null;
            return;
        }

        getLogger().info(TextFormat.WHITE + "Javascript engine: " + engine.getFactory().getEngineName() + " " + engine.getFactory().getEngineVersion());

        engine.put("server", getServer());
        engine.put("plugin", this);
        engine.put("manager", new FunctionManager(instance));
        engine.put("forms", new formsManager(instance));
        engine.put("http", new httpManager(instance));
        engine.put("cast", new castManager(instance));
        engine.put("logger", getLogger());
        engine.put("variable", new variableManager(instance));
        engine.put("scoreboard", new scoreboardManager(instance));
        engine.put("players", 0);
        engine.put("database", new databaseManager(instance));

        if(main.instance.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            engine.put("placeholderapi", new placeholderAPIManager(instance));
            getLogger().info("Hooked into placeholder and registered onPlaceholderRequest event");
        }

        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File defaultJS = new File(getDataFolder(), "scripts");
        if(!defaultJS.exists()){
            defaultJS.mkdir();
            File defaultJSFile = new File(defaultJS, "default.js");
            util.copy(instance.getResource("default.js"), defaultJSFile);
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
        this.getServer().getScheduler().scheduleDelayedRepeatingTask(new Task() {
            @Override
            public void onRun(int i) {
                engine.put("players", getServer().getOnlinePlayers().values());
            }
        }, 20, 20, true);

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
        final NashornScriptEngineFactory manager = new NashornScriptEngineFactory();
        engine = (NashornScriptEngine) manager.getScriptEngine(options);
        if (engine == null) {
            instance.getLogger().error("No JavaScript engine was found!");
            return;
        }
        if (!(engine instanceof Invocable)) {
            instance.getLogger().error("JavaScript engine does not support the Invocable API!");
            engine = null;
            return;
        }

        instance.getLogger().info(TextFormat.WHITE + "Javascript engine: " + engine.getFactory().getEngineName() + " " + engine.getFactory().getEngineVersion());

        engine.put("server",  instance.getServer());
        engine.put("plugin", instance);
        engine.put("manager", new FunctionManager(instance));
        engine.put("forms", new formsManager(instance));
        engine.put("http", new httpManager(instance));
        engine.put("cast", new castManager(instance));
        engine.put("logger",  instance.getLogger());
        engine.put("variable", new variableManager(instance));
        engine.put("scoreboard", new scoreboardManager(instance));
        engine.put("players", 0);
        engine.put("database", new databaseManager(instance));

        if(main.instance.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            engine.put("placeholderapi", new placeholderAPIManager(instance));
            instance.getLogger().info("Hooked into placeholder and registered onPlaceholderRequest event");
        }

        for (File file : Objects.requireNonNull(instance.getDataFolder().listFiles())) {
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
                    IPlayer p = instance.getServer().getOfflinePlayer(originalContent);
                    variables.put(originalName, p);
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
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getLabel().equalsIgnoreCase("drupi")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        sender.sendMessage("Reloading scripts");
                        try {
                            main.reload();
                            sender.sendMessage("Reloaded scripts!");
                        } catch (Exception e) {
                            sender.sendMessage("Something went wrong!");
                        }
                    } else {
                        sender.sendMessage("Uknown argument");
                    }
                } else {
                    sender.sendMessage("usage: /drupi reload - Reload js scripts");
                }
            }
        }
        return true;
    }

    public synchronized void callEventHandler(final Event e, final String functionName) {
        if (engine.get(functionName) == null) {
            return;
        }
        try {
            ((Invocable) engine).invokeFunction(functionName, e);
        } catch (final Exception se) {
            getLogger().error("Error while calling " + functionName, se);
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
            getLogger().error("Error while calling " + functionName, se);
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
            getLogger().error("Error while calling " + functionName, se);
            se.printStackTrace();
        }
    }

    private synchronized Object eval(final CommandSender sender, final String expression) throws ScriptException {
        if (sender != null && sender.isPlayer()) {
            final Player player = (Player) sender;
            engine.put("me", player);
            engine.put("level", player.getPosition().level);
            engine.put("pos", player.getPosition());
        } else {
            engine.put("me", null);
            engine.put("level", getServer().getDefaultLevel());
            engine.put("pos", null);
        }
        return engine.eval(expression);
    }
}