package net.stacket.drupi.legacy.addon;

import org.graalvm.polyglot.Value;

import net.stacket.drupi.legacy.MainPlugin;
import net.stacket.drupi.shared.api.Drupi;

public class DrupiAddon {

    private String name;
    private DrupiAddon instance;
    private Drupi drupi;

    public DrupiAddon(String name, Object clas){
        this.name = name;
        this.instance = this;
        this.drupi =  MainPlugin.drupi;
        drupi.registerManager(name, clas);
        if(Drupi.engine != null) {
            final Value bindings = Drupi.engine.getBindings("js");
            bindings.putMember(name, clas);
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
