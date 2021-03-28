package net.stacket.drupi.legacy.script;

import net.stacket.drupi.legacy.MainPlugin;

import javax.script.Invocable;
import javax.script.ScriptException;

public class drupiHelper {

    MainPlugin plugin;

    public drupiHelper(MainPlugin plugin){
        this.plugin = plugin;
    }

    public void moduleLoaded(String name, String version){
        if(!plugin.loadedModules.contains(name + "@" + version)) {
            plugin.loadedModules.add(name + "@" + version);
        }
    }
}
