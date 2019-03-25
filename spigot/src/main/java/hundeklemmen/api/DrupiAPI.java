package hundeklemmen.api;

import hundeklemmen.main;

import javax.script.Invocable;

public class DrupiAPI {

    private String addonName;

    public DrupiAPI(String addonName) {
        this.addonName = addonName;
    }
    public void register(Object clas){
        main.engine.put(addonName, clas);
    }
    public void registerEvent(Object event, String functionName){
        callCustomEvent(event, addonName + "_" + functionName);
    }
    public Object variableGet(String index){
        return main.instance.variables.get(index);
    }
    public void variableSet(String index, Object content){
        main.instance.variables.put(index, content);
    }
    public boolean variableExists(String index){
        return main.instance.variables.containsKey(index);
    }
    public void variableDelete(String index){
        if(main.instance.variables.containsKey(index)) {
            main.instance.variables.remove(index);
        };
    }

    public void callCustomEvent(Object event, String functionName){
        if (hundeklemmen.main.engine.get(functionName) == null) {
            return;
        }
        try {
            ((Invocable) hundeklemmen.main.engine).invokeFunction(functionName, event);
        } catch (final Exception se) {
            hundeklemmen.main.instance.getLogger().warning("Error while calling " + functionName);
            se.printStackTrace();
        }
    }
}
