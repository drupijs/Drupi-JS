package hundeklemmen.v1_13.expansions.worldguard;

import hundeklemmen.shared.api.Drupi;
import hundeklemmen.v1_13.expansions.worldguard.events.RegionEnteredEvent;
import hundeklemmen.v1_13.expansions.worldguard.events.RegionLeftEvent;
import hundeklemmen.v1_13.expansions.worldguard.events.RegionsEnteredEvent;
import hundeklemmen.v1_13.expansions.worldguard.events.RegionsLeftEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DrupiWGListener implements Listener {


    Drupi drupi;
    public DrupiWGListener(Drupi drupi){
        this.drupi = drupi;
    }


    @EventHandler
    public void regionEnter(RegionEnteredEvent event){
        drupi.callEvent("WorldGuard_RegionEnterEvent", event);
    }
    @EventHandler
    public void regionsEnter(RegionsEnteredEvent event){
        drupi.callEvent("WorldGuard_RegionsEnterEvent", event);
    }

    @EventHandler
    public void regionLeft(RegionLeftEvent event){
        drupi.callEvent("WorldGuard_RegionLeaveEvent", event);
    }
    @EventHandler
    public void regionsLeft(RegionsLeftEvent event){
        drupi.callEvent("WorldGuard_RegionsLeaveEvent", event);
    }
}
