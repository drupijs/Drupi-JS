package net.stacket.drupi.shared.script;

import net.stacket.drupi.shared.api.Drupi;

import java.util.ArrayList;
import java.util.Arrays;

import org.graalvm.polyglot.Value;


public class EventManager {
    private Drupi drupi;

    public EventManager(Drupi drupi){
        this.drupi = drupi;
    }

    public Value addListener(String eventName, Value invokeFunction){
        if(this.drupi.registeredEvents.containsKey(eventName)) {
            this.drupi.registeredEvents.get(eventName).add(invokeFunction);
            return invokeFunction;
        } else {
            this.drupi.registeredEvents.put(eventName, new ArrayList<Value>(Arrays.asList(new Value[]{invokeFunction})));
            return invokeFunction;
        }
    }


    public void removeListener(String eventName, Value invokeFunction){
        if(this.drupi.registeredEvents.containsKey(eventName)) {
            if(this.drupi.registeredEvents.get(eventName).contains(invokeFunction)) {
                this.drupi.registeredEvents.get(eventName).remove(invokeFunction);
            }
        }
    }
}
