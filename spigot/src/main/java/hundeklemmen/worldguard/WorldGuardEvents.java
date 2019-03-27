package hundeklemmen.worldguard;

import com.sk89q.worldguard.protection.events.DisallowedPVPEvent;
import hundeklemmen.MainPlugin;
import hundeklemmen.worldguard.customEvents.RegionEnterEvent;
import hundeklemmen.worldguard.customEvents.RegionLeaveEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class WorldGuardEvents implements Listener {

    @EventHandler
    public void pvpDisallowedEvent(DisallowedPVPEvent event){
        MainPlugin.instance.callEventHandler(event, "WorldGuard_"+event.getClass().getSimpleName());
    }

    @EventHandler
    public void regionEnter(RegionEnterEvent event){
        MainPlugin.instance.callEventHandler(event, "WorldGuard_"+event.getClass().getSimpleName());
    }
    @EventHandler
    public void regionLeave(RegionLeaveEvent event){
        MainPlugin.instance.callEventHandler(event, "WorldGuard_"+event.getClass().getSimpleName());
    }
}
