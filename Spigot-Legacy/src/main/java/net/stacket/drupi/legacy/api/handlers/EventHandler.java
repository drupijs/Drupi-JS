package net.stacket.drupi.legacy.api.handlers;

import net.stacket.drupi.legacy.MainPlugin;
import net.stacket.drupi.shared.api.Drupi;
import net.stacket.drupi.shared.script.EventManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.graalvm.polyglot.Value;

import java.util.ArrayList;
import java.util.Arrays;

public class EventHandler extends EventManager {

    private Drupi drupi;

    public EventHandler(Drupi drupi) {
        super(drupi);
        this.drupi = drupi;
    }


    public Value addListenerExternal(Class<? extends Event> event, Value invokeFunction){
        if(this.drupi.registeredEvents.containsKey(event.getSimpleName())) {
            this.drupi.registeredEvents.get(event.getSimpleName()).add(invokeFunction);;
            return invokeFunction;
        } else {
            drupiCustomEvent listener = new drupiCustomEvent();
            Bukkit.getPluginManager().registerEvent(event,  listener, EventPriority.NORMAL, listener, MainPlugin.instance, false);
            this.drupi.registeredEvents.put(event.getSimpleName(), new ArrayList<Value>(Arrays.asList(new Value[]{invokeFunction})));
            return invokeFunction;
        }
    }
    public class drupiCustomEvent implements EventExecutor, Listener {

        public drupiCustomEvent() {
            super();
        }


        @Override
        public void execute(Listener l, Event event) {
            if (MainPlugin.drupi.registeredEvents.containsKey(event.getEventName())) {
                for (Value function : MainPlugin.drupi.registeredEvents.get(event.getEventName())) {
                    if(function.canExecute()) function.executeVoid(event);
                }
            }
        }
    }
}
