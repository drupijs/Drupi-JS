package hundeklemmen.legacy.script;

import hundeklemmen.legacy.MainPlugin;

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
    public String convertScript(String original){
        if(plugin.drupi.config.compileMethod.equalsIgnoreCase("none")) {
            return original;
        } else if(plugin.drupi.config.compileMethod.equalsIgnoreCase("legacy")){
            try {
                return (String) ((Invocable) plugin.drupi.engine).invokeFunction("convertBabelJS", original);
            } catch (ScriptException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else if(plugin.drupi.config.compileMethod.equalsIgnoreCase("modern")){
            try {
                return (String) ((Invocable) plugin.drupi.compileEngine).invokeFunction("convertBabelJS", original);
            } catch (ScriptException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
