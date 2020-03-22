package hundeklemmen.bungeecord;

import express.Express;
import hundeklemmen.bungeecord.Commands.DrupiCommand;
import hundeklemmen.bungeecord.Events.eventListener;
import hundeklemmen.bungeecord.api.BungeeConfig;
import hundeklemmen.bungeecord.script.*;
import hundeklemmen.shared.api.Drupi;
import hundeklemmen.shared.api.DrupiScript;
import hundeklemmen.shared.api.Platform;
import hundeklemmen.shared.api.interfaces.ScriptLoadMessage;
import hundeklemmen.shared.api.interfaces.SetupMessage;
import io.socket.client.Socket;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import org.bstats.bungeecord.Metrics;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

public class MainPlugin extends Plugin implements Listener {

    public static MainPlugin instance;
    public static HashMap<String, Object> variables = new HashMap<String, Object>();
    public static File DrupiFile;
    public static String serverVersion;

    public ArrayList<Socket> sockets = new ArrayList<io.socket.client.Socket>();
    public ArrayList<Express> ExpressRunning = new ArrayList<>();

    public static int ErrorAmount = 0;

    public static Drupi drupi;
    public static boolean dev = false;

    public static int loadedScripts = 0;
    public static ArrayList<String> loadedModules = new ArrayList<String>();


    @Override
    public void onEnable(){
        instance = this;
        Metrics metrics = new Metrics(this, 4590); //OOF
        DrupiFile = instance.getFile();
        serverVersion = instance.getProxy().getVersion();


        registerCommands();

        //Prepare
        if (!instance.getDataFolder().exists()) {
            instance.getDataFolder().mkdir();
        }
        File sciptsFolder = new File(instance.getDataFolder(), "scripts");
        if(!sciptsFolder.exists()){
            sciptsFolder.mkdir();
            File defaultJSFile = new File(sciptsFolder, "default.js");
            util.copy(this.getResourceAsStream("default.js"), defaultJSFile);
        }
        File defaultModules = new File(sciptsFolder, "modules");
        if(!defaultModules.exists()){
            defaultModules.mkdir();
        }
        File UtilsJSFile = new File(instance.getDataFolder(), "utils.js");
        if(!UtilsJSFile.exists()) {
            util.copy(instance.getResourceAsStream("utils.js"), UtilsJSFile);
        }

        File BabelJSFile = new File(instance.getDataFolder(), "babel.js");
        if(!BabelJSFile.exists()) {
            util.copy(instance.getResourceAsStream("babel.js"), BabelJSFile);
        }

        File variablesFile = new File(instance.getDataFolder(), "variables.csv");
        if(!variablesFile.exists()) {
            try (InputStream in = getResourceAsStream("variables.csv")) {
                Files.copy(in, variablesFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        drupi = new Drupi(Platform.Bungeecord, instance.getLogger(), instance.getDataFolder(), instance.getFile(), (Plugin) instance);
        drupi.setConfig(new BungeeConfig(instance));
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

        drupi.registerManager("event", new hundeklemmen.shared.script.EventManager(drupi));
        drupi.registerManager("server", instance.getProxy());
        drupi.registerManager("drupi", drupi);
        drupi.registerManager("plugin", instance);
        drupi.registerManager("manager", new FunctionManager(instance, drupi));
        drupi.registerManager("command", new commandManager(drupi));
        drupi.registerManager("drupihelper", new drupiHelper(instance));

        drupi.registerManager("cast", new castManager());
        drupi.registerManager("logger", instance.getLogger());

        //drupi.registerManager("variable", new variableManager(instance));
        drupi.registerManager("socket", new socketManager(instance));
        drupi.registerManager("Express", new ExpressManager(instance));

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

                if(ErrorAmount == 0) {
                    drupi.log.info("Successfully reloaded all scripts!");
                } else {
                    drupi.log.info("Loaded all scripts with a total of "+ ErrorAmount + " errors!");
                }
            }
        });

        drupi.log.info("Loading events");

        //Register drupi required events for bungeecord events and 3rd party stuff.
        instance.getProxy().getPluginManager().registerListener(instance, this);
        instance.getProxy().getPluginManager().registerListener(instance, new eventListener(drupi));
    }

    @Override
    public void onDisable(){

    }

    public void reloadDrupi(CommandSender sender){
        if(sockets.size() != 0){
            drupi.log.info("Disconnecting sockets..");
            for (int i = 0; i < sockets.size(); i++){
                sockets.get(i).disconnect();
            }
            sockets = new ArrayList<io.socket.client.Socket>();
        }
        if(ExpressRunning.size() != 0){
            drupi.log.info("Stopping web servers..");
            for (int i = 0; i < ExpressRunning.size(); i++){
                ExpressRunning.get(i).stop();
            }
            ExpressRunning = new ArrayList<Express>();
        }
        loadedModules.clear();
        instance.getProxy().getScheduler().cancel(this);
        try {
            util.unregisterCommands();
        } catch (Exception e){
            drupi.log.warning("Error while unregistering commands!");
            drupi.log.warning(e.getMessage());
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

    public void registerCommands(){
        instance.getProxy().getPluginManager().registerCommand(instance, new DrupiCommand("drupi", instance));
    }

    public static void devLog(String Message){
        if(dev == true) drupi.log.info(Message);
    }

    public static void saveResource(String name, File file){
        try {
            if (!file.exists()) {
                Files.copy(instance.getResourceAsStream(name), file.toPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
