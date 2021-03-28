package net.stacket.drupi.bungeecord.script;


import net.stacket.drupi.bungeecord.MainPlugin;

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
