package hundeklemmen.worldguard;

import com.sk89q.worldguard.protection.events.DisallowedPVPEvent;
import hundeklemmen.main;
import hundeklemmen.worldguard.customEvents.RegionEnterEvent;
import hundeklemmen.worldguard.customEvents.RegionLeaveEvent;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class worldguardEvents implements Listener {

    @EventHandler
    public void pvpDisallowedEvent(DisallowedPVPEvent event){
        main.instance.callEventHandler(event, "WorldGuard_"+event.getClass().getSimpleName());
    }

    @EventHandler
    public void regionEnter(RegionEnterEvent event){
        main.instance.callEventHandler(event, "WorldGuard_"+event.getClass().getSimpleName());
    }
    @EventHandler
    public void regionLeave(RegionLeaveEvent event){
        main.instance.callEventHandler(event, "WorldGuard_"+event.getClass().getSimpleName());
    }
}
