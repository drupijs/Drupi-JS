package hundeklemmen.shared.api;

import com.google.gson.Gson;
import hundeklemmen.shared.api.interfaces.SetupMessage;
import hundeklemmen.shared.script.*;
import hundeklemmen.shared.api.utils.http;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Drupi {

    public Logger log;
    public Drupi instance;
    public Object plugin;
    public Platform platform;
    public String version;

    public static boolean update = false;
    public static ScriptEngine engine;
    public static HashMap<String, Object> variables = new HashMap<String, Object>();
    public static List<String> registeredCommands = new ArrayList<String>();
    public static HashMap<String, Object> managers = new HashMap<String, Object>();
    public static HashMap<String, ArrayList<Value>> registeredEvents = new HashMap<String, ArrayList<Value>>();


    public File DataFolder;

    public config config;

    public Drupi(Platform p, Logger log, File drupiPath, File DrupiPL, Object plugin){
        this.instance = this;
        this.log = log;
        this.DataFolder = drupiPath;
        this.platform = p;

        log.info("Starting Drupi instance");
        if(p == Platform.Spigot){
            plugin = (org.bukkit.plugin.Plugin) plugin;
            log.info("Started Drupi instance (Spigot)");
        } else if(p == Platform.Bungeecord){
            plugin = (net.md_5.bungee.api.plugin.Plugin) plugin;
            log.info("Started Drupi instance (Bungee)");
        };
    }

    public void CheckForUpdate(){
        log.info("Checking for updates");
        try {
            String githubLatest = http.fireGet("https://api.github.com/repos/drupijs/Drupi-JS/releases/latest");
            Map<String, Object> javaMap = new Gson().fromJson(githubLatest, Map.class);
            String latestVersion = javaMap.get("tag_name").toString();
            if(!version.equalsIgnoreCase(latestVersion)){
                update = true;
                log.info("Whoups! It looks like Drupi is out of date!");
                log.info("Go download the latest version of Drupi at https://www.spigotmc.org/resources/drupi-js.65706/");
            } else {
                log.info("You're running the latest version of Drupi!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void registerManager(String name, Object obj){
        managers.put(name, obj);
    }


    public void Setup(SetupMessage SM){
        String[] options = new String[] {"--language=es6"};
        engine = new ScriptEngineManager().getEngineByName("graal.js");
        if (engine == null) {
            log.warning("No JavaScript engine was found!");
            if(platform == Platform.Spigot){
                ((org.bukkit.plugin.Plugin) this.plugin).getServer().shutdown();
            } else if(platform == Platform.Bungeecord){
                ((net.md_5.bungee.api.plugin.Plugin) this.plugin).getProxy().stop("[DRUPI] No Graal.JS engine was found!");
            }
            return;
        }
        if (!(engine instanceof Invocable)) {
            log.warning("JavaScript engine does not support the Invocable API!");
            if(platform == Platform.Spigot){
                ((org.bukkit.plugin.Plugin) this.plugin).getServer().shutdown();
            } else if(platform == Platform.Bungeecord){
                ((net.md_5.bungee.api.plugin.Plugin) this.plugin).getProxy().stop("[DRUPI] JavaScript engine does not support the Invocable API!");
            }
            return;
        }

        //log.info("Javascript engine: " + engine.getFactory().getEngineName() + " " + engine.getFactory().getEngineVersion());
        //log.info("Engine factories: " + engine.getFactory().getLanguageName() + " " + engine.getFactory().getLanguageVersion());


        registeredEvents.clear();

        //Load our global managers which is included in core to make things easier
        //Loading them before the others so that platforms can override the global managers
        engine.put("database", new DatabaseManager());
        engine.put("http", new HttpManager());
        engine.put("console", new console(this));

        //Load all our managers here
        for(Map.Entry<String, Object> mng : managers.entrySet()){
            engine.put(mng.getKey(), mng.getValue());
        };
        SM.loadManagers(engine);

    }


    public Drupi getInstance(){
        return instance;
    }

    public void setConfig(config config){
        this.config = config;
    }
    public void setVersion(String version){
        this.version = version;
    }

    public void callFunction(String functionName, Object... args){
        if (engine.get(functionName) == null) {
            return;
        }
        try {
            ((Invocable) engine).invokeFunction(functionName, args);
        } catch (final Exception se) {
            log.warning("Error while calling " + functionName);
            se.printStackTrace();
        }
    }
    public Object callFunctionWithResult(String functionName, Object... args){
        if (engine.get(functionName) == null) {
            return null;
        }
        try {
            return ((Invocable) engine).invokeFunction(functionName, args);
        } catch (final Exception se) {
            log.warning("Error while calling " + functionName);
            se.printStackTrace();
        }
        return null;
    }




    public void callEvent(String eventName, Object... args){
        if(registeredEvents.containsKey(eventName)){
            for (Value function : registeredEvents.get(eventName)) {
                function.executeVoid(null, args);
            }
        }
    }

    public ScriptEngine getEngine(){
        return engine;
    }
    public HashMap<String, Object> getVariables(){
        return variables;
    }
    public List<String> getRegisteredCommands(){
        return registeredCommands;
    }
    public HashMap<String, Object> getManagers(){
        return managers;
    }

    public Platform getPlatform() {
        return platform;
    }

    public HashMap<String, ArrayList<Value>> getRegisteredEvents(){
        return registeredEvents;
    }

    public Object getPlugin(){
        return plugin;
    }
}
