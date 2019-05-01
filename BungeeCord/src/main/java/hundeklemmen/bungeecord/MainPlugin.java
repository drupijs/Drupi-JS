package hundeklemmen.bungeecord;

import hundeklemmen.bungeecord.api.BungeeConfig;
import hundeklemmen.shared.api.Drupi;
import hundeklemmen.shared.api.DrupiScript;
import hundeklemmen.shared.api.Platform;
import hundeklemmen.shared.api.interfaces.ScriptLoadMessage;
import hundeklemmen.shared.api.interfaces.SetupMessage;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

public class MainPlugin extends Plugin implements Listener {

    public static MainPlugin instance;
    public static HashMap<String, Object> variables = new HashMap<String, Object>();
    public static File DrupiFile;

    public static int ErrorAmount = 0;


    public static Drupi drupi;
    public static boolean dev = true;


    @Override
    public void onEnable(){
        instance = this;
        this.getProxy().broadcast();
        //Metrics metrics = new Metrics(this); //OOF

        //Prepare
        if (!instance.getDataFolder().exists()) {
            instance.getDataFolder().mkdir();
        }
        File defaultJS = new File(instance.getDataFolder(), "scripts");
        if(!defaultJS.exists()){
            defaultJS.mkdir();
            File defaultJSFile = new File(defaultJS, "default.js");
            saveResource("default.js", defaultJSFile);
        }
        File UtilsJSFile = new File(instance.getDataFolder(), "utils.js");
        if(!UtilsJSFile.exists()) {
            saveResource("utils.js", UtilsJSFile);
        }

        File variablesFile = new File(instance.getDataFolder(), "variables.csv");
        if(!variablesFile.exists()) {
            saveResource("variables.csv", variablesFile);
        }
        drupi = new Drupi(Platform.Bungeecord, instance.getLogger(), instance.getDataFolder(), instance.getFile(), (Plugin) instance);
        drupi.setConfig(new BungeeConfig(instance));
        drupi.setVersion(MainPlugin.instance.getDescription().getVersion());

        if(drupi.config.VC_checkOnLoad == true){
            drupi.CheckForUpdate();
        }

        drupi.LoadVariables();
        devLog("[DEV] Setup proccess!");

        devLog("Registering global managers");
        //Register managers:
        drupi.registerManager("server", instance.getProxy());
        drupi.registerManager("proxy", instance.getProxy());
        drupi.registerManager("plugin", instance);
        //drupi.registerManager("manager", new FunctionManager(instance));

        //drupi.registerManager("cast", new castManager());
        drupi.registerManager("logger", instance.getLogger());
        //drupi.registerManager("variable", new variableManager(instance));
        //drupi.registerManager("scoreboard", new scoreboardManager(instance));
        //drupi.registerManager("material", new materialManager(instance));


        drupi.Setup(new SetupMessage() {
            @Override
            public void onMessage(String message) {
                //Idk why ¯\_(ツ)_/¯
            }

            @Override
            public void loadManagers(NashornScriptEngine engine) {
                File UtilsJSFile = new File(instance.getDataFolder(), "utils.js");
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
                for (File file : defaultJS.listFiles()) {
                    if(file.isDirectory()) continue;
                    if(file.getName().toLowerCase().contains(".js")||file.getName().toLowerCase().contains(".drupi")){
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


        //Register drupi required events for bungeecord events and 3rd party stuff.
        instance.getProxy().getPluginManager().registerListener(instance, this);
    }

    @Override
    public void onDisable(){

    }

    public static void devLog(String Message){
        if(dev == true) {
            drupi.log.info(Message);
        }
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
