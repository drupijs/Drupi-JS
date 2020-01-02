package hundeklemmen.shared.script;

import hundeklemmen.shared.api.Drupi;
import jdk.nashorn.api.scripting.JSObject;

import java.util.ArrayList;
import java.util.Arrays;


public class EventManager {
    private Drupi drupi;

    public EventManager(Drupi drupi){
        this.drupi = drupi;
    }

    public JSObject addListener(String eventName, JSObject invokeFunction){
        if(this.drupi.registeredEvents.containsKey(eventName)) {
            this.drupi.registeredEvents.get(eventName).add(invokeFunction);
            return invokeFunction;
        } else {
            this.drupi.registeredEvents.put(eventName, new ArrayList<JSObject>(Arrays.asList(new JSObject[]{invokeFunction})));
            return invokeFunction;
        }
    }


    public void removeListener(String eventName, JSObject invokeFunction){
        if(this.drupi.registeredEvents.containsKey(eventName)) {
            if(this.drupi.registeredEvents.get(eventName).contains(invokeFunction)) {
                this.drupi.registeredEvents.get(eventName).remove(invokeFunction);
            }
        }
    }
}
