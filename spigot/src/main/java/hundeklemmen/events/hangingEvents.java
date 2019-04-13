package hundeklemmen.events;

import hundeklemmen.MainPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;

public class hangingEvents implements Listener {

    @EventHandler
    public void HangingBreakByEntity(HangingBreakByEntityEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void HangingBreak(HangingBreakEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void HangingPlace(HangingPlaceEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
}
