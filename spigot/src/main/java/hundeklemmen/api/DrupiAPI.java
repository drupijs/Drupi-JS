package hundeklemmen.api;

import hundeklemmen.MainPlugin;

import javax.script.Invocable;

public class DrupiAPI {

    private String addonName;

    public DrupiAPI(String addonName) {
        this.addonName = addonName;
    }
    public void register(Object clas){
        MainPlugin.engine.put(addonName, clas);
    }
    public void registerEvent(Object event, String functionName){
        callCustomEvent(event, addonName + "_" + functionName);
    }
    public Object variableGet(String index){
        return MainPlugin.instance.variables.get(index);
    }
    public void variableSet(String index, Object content){
        MainPlugin.instance.variables.put(index, content);
    }
    public boolean variableExists(String index){
        return MainPlugin.instance.variables.containsKey(index);
    }
    public void variableDelete(String index){
        if(MainPlugin.instance.variables.containsKey(index)) {
            MainPlugin.instance.variables.remove(index);
        };
    }

    public void callCustomEvent(Object event, String functionName){
        if (MainPlugin.engine.get(functionName) == null) {
            return;
        }
        try {
            ((Invocable) MainPlugin.engine).invokeFunction(functionName, event);
        } catch (final Exception se) {
            MainPlugin.instance.getLogger().warning("Error while calling " + functionName);
            se.printStackTrace();
        }
    }
}
