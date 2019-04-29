package hundeklemmen.legacy.expansions.worldguard;

import com.sk89q.worldguard.protection.events.DisallowedPVPEvent;
import hundeklemmen.legacy.MainPlugin;
import hundeklemmen.legacy.expansions.worldguard.customEvents.RegionEnterEvent;
import hundeklemmen.legacy.expansions.worldguard.customEvents.RegionEnteredEvent;
import hundeklemmen.legacy.expansions.worldguard.customEvents.RegionLeaveEvent;
import hundeklemmen.legacy.expansions.worldguard.customEvents.RegionLeftEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class worldguardEvents implements Listener {

    @EventHandler
    public void pvpDisallowedEvent(DisallowedPVPEvent event){
        MainPlugin.drupi.callFunction("WorldGuard_"+event.getClass().getSimpleName(), event);
    }

    @EventHandler
    public void regionEnter(RegionEnterEvent event){
        MainPlugin.drupi.callFunction("WorldGuard_"+event.getClass().getSimpleName(), event);
    }
    @EventHandler
    public void regionEntered(RegionEnteredEvent event){
        MainPlugin.drupi.callFunction("WorldGuard_"+event.getClass().getSimpleName(), event);
    }
    @EventHandler
    public void regionLeave(RegionLeaveEvent event){
        MainPlugin.drupi.callFunction("WorldGuard_"+event.getClass().getSimpleName(), event);
    }
    @EventHandler
    public void regionLeave(RegionLeftEvent event){
        MainPlugin.drupi.callFunction("WorldGuard_"+event.getClass().getSimpleName(), event);
    }
}