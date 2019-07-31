package hundeklemmen.legacy.addon;

import hundeklemmen.legacy.MainPlugin;
import hundeklemmen.shared.api.Drupi;

public class DrupiAddon {

    private String name;
    private DrupiAddon instance;
    private Drupi drupi;

    public DrupiAddon(String name, Object clas){
        this.name = name;
        this.instance = this;
        this.drupi =  MainPlugin.drupi;
        drupi.registerManager(name, clas);
        if(drupi.engine != null) {
            drupi.engine.put(name, clas);
        }
        drupi.log.info("Registered Addon: " + name);
    }

    public void callEvent(String name, Object... event){
        drupi.callFunction(this.name + "_" + name, event);
    }
    public Object callEventWithResult(String name, Object... event){
        return drupi.callFunctionWithResult(this.name + "_" + name, event);
    }


}
