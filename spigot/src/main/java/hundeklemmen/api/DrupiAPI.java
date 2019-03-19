package hundeklemmen.api;

import hundeklemmen.main;
import org.bukkit.event.Event;

public class DrupiAPI {

    private String addonName;

    public DrupiAPI(String addonName) {
        this.addonName = addonName;
    }
    public void register(Object clas){
        main.engine.put(addonName, clas);
    }
    public void registerEvent(Event event, String functionName){
        main.instance.callEventHandler(event, addonName + "_" + functionName);
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
}
