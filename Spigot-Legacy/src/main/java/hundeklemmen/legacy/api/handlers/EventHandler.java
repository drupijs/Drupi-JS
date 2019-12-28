package hundeklemmen.legacy.api.handlers;

import hundeklemmen.legacy.MainPlugin;
import hundeklemmen.shared.api.Drupi;
import jdk.nashorn.api.scripting.JSObject;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;

import java.util.ArrayList;
import java.util.Arrays;

public class EventHandler extends hundeklemmen.shared.script.EventManager {

    private Drupi drupi;

    public EventHandler(Drupi drupi) {
        super(drupi);
        this.drupi = drupi;
    }


    public JSObject addListenerExternal(Class<? extends Event> event, JSObject invokeFunction){
        if(this.drupi.registeredEvents.containsKey(event.getSimpleName())) {
            this.drupi.registeredEvents.get(event.getSimpleName()).add(invokeFunction);;
            return invokeFunction;
        } else {
            drupiCustomEvent listener = new drupiCustomEvent();
            Bukkit.getPluginManager().registerEvent(event,  listener, EventPriority.NORMAL, listener, MainPlugin.instance, false);
            this.drupi.registeredEvents.put(event.getSimpleName(), new ArrayList<JSObject>(Arrays.asList(new JSObject[]{invokeFunction})));
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
                for (JSObject function : MainPlugin.drupi.registeredEvents.get(event.getEventName())) {
                    function.call(null, event);
                }
            }
        }
    }
}
