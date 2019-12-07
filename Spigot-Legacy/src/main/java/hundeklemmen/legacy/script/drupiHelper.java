package hundeklemmen.legacy.script;

import hundeklemmen.legacy.MainPlugin;

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
