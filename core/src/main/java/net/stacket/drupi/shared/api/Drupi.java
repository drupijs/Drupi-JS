package net.stacket.drupi.shared.api;

import com.google.gson.Gson;
import com.oracle.truffle.api.object.dsl.Nullable;

import net.stacket.drupi.shared.api.interfaces.SetupMessage;
import net.stacket.drupi.shared.api.utils.http;
import net.stacket.drupi.shared.script.DatabaseManager;
import net.stacket.drupi.shared.script.HttpManager;
import net.stacket.drupi.shared.script.console;
import org.bukkit.plugin.Plugin;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Value;

import net.stacket.drupi.shared.script.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static Context engine;
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
        this.plugin = plugin;

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


    interface ContextCallback {
        void call() throws IOException, URISyntaxException;
    }

    public void runInPluginContext(ContextCallback callback) throws Exception {
        ClassLoader oldCl = Thread.currentThread().getContextClassLoader();
        ClassLoader newCl;
        switch (platform) {
            case Spigot:
                newCl = ((Plugin) plugin).getClass().getClassLoader();
                break;
            case Bungeecord:
                newCl = ((net.md_5.bungee.api.plugin.Plugin) plugin).getClass().getClassLoader();
                break;
            default:
                throw new Exception("Unsupported server platform.");
        }
        Thread.currentThread().setContextClassLoader(newCl);
        callback.call();
        Thread.currentThread().setContextClassLoader(oldCl);
    }

    public void Setup(SetupMessage SM) throws Exception{
        runInPluginContext(() -> {
            final HostAccess hostAccess = HostAccess.newBuilder(HostAccess.ALL).targetTypeMapping(Double.class, Float.class, null, Double::floatValue).build();
            engine = Context.newBuilder("js", "regex")
                .allowExperimentalOptions(true)
                .allowIO(true)
                .allowHostAccess(hostAccess)
                .allowHostClassLookup(c -> true)
                .allowNativeAccess(true)
                .allowCreateThread(true)
                .allowCreateProcess(true)
                .allowAllAccess(true)
                .build();
            if (engine == null) {
                log.warning("No JavaScript engine was found! (Java 11 or higher is required)");
                if(platform == Platform.Spigot){
                    ((org.bukkit.plugin.Plugin) this.plugin).getServer().shutdown();
                } else if(platform == Platform.Bungeecord){
                    ((net.md_5.bungee.api.plugin.Plugin) this.plugin).getProxy().stop("[DRUPI] No JavaScript engine was found! (Java 11 or higher is required)");
                }
                return;
            }
            registeredEvents.clear();

            final Value bindings = engine.getBindings("js");
            engine.eval("js", "var exports = {};");
            
    
            //Load our global managers which is included in core to make things easier
            //Loading them before the others so that platforms can override the global managers
            bindings.putMember("database", new DatabaseManager());
            bindings.putMember("http", new HttpManager());
            bindings.putMember("console", new console(this));
    
            //Load all our managers here
            for(Map.Entry<String, Object> mng : managers.entrySet()){
                bindings.putMember(mng.getKey(), mng.getValue());
            };
            SM.loadManagers(engine);
        });

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
        final Value bindings = engine.getBindings("js");
        if (!bindings.hasMember(functionName)) {
            return;
        }
        try {
            final Value function = bindings.getMember(functionName);
            if(function.canExecute()) function.execute(args);
        } catch (final Exception se) {
            log.warning("Error while calling " + functionName);
            se.printStackTrace();
        }
    }
    public Object callFunctionWithResult(String functionName, Object... args){
        final Value bindings = engine.getBindings("js");
        if (!bindings.hasMember(functionName)) {
            return null;
        }
        try {
            final Value function = bindings.getMember(functionName);
            if(function.canExecute()) return function.execute(args);
        } catch (final Exception se) {
            log.warning("Error while calling " + functionName);
            se.printStackTrace();
        }
        return null;
    }


    public void callEvent(String eventName, Object... args){
        if(!registeredEvents.containsKey(eventName)) return;
        ArrayList<Value> handlers = registeredEvents.get(eventName);
        for (Object fn : Arrays.copyOf(handlers.toArray(), handlers.size())) {
            Value function = (Value) fn;
            if(function.canExecute()) function.executeVoid(args);
        }
    }
    public Context getEngine(){
        return engine;
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
