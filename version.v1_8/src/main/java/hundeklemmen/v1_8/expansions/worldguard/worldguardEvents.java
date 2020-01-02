package hundeklemmen.v1_8.expansions.worldguard;

import com.sk89q.worldguard.protection.events.DisallowedPVPEvent;
import hundeklemmen.shared.api.Drupi;
import hundeklemmen.v1_8.expansions.worldguard.customEvents.RegionEnterEvent;
import hundeklemmen.v1_8.expansions.worldguard.customEvents.RegionEnteredEvent;
import hundeklemmen.v1_8.expansions.worldguard.customEvents.RegionLeaveEvent;
import hundeklemmen.v1_8.expansions.worldguard.customEvents.RegionLeftEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class worldguardEvents implements Listener {

    Drupi drupi;
    public worldguardEvents(Drupi drupi){
        this.drupi = drupi;
    }

    @EventHandler
    public void pvpDisallowedEvent(DisallowedPVPEvent event){
        drupi.callEvent("WorldGuard_"+event.getClass().getSimpleName(), event);
    }

    @EventHandler
    public void regionEnter(RegionEnterEvent event){
        drupi.callEvent("WorldGuard_"+event.getClass().getSimpleName(), event);
    }
    @EventHandler
    public void regionEntered(RegionEnteredEvent event){
        drupi.callEvent("WorldGuard_"+event.getClass().getSimpleName(), event);
    }
    @EventHandler
    public void regionLeave(RegionLeaveEvent event){
        drupi.callEvent("WorldGuard_"+event.getClass().getSimpleName(), event);
    }
    @EventHandler
    public void regionLeave(RegionLeftEvent event){
        drupi.callEvent("WorldGuard_"+event.getClass().getSimpleName(), event);
    }
}