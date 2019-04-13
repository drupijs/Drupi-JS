package hundeklemmen.events;

import hundeklemmen.MainPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.*;

public class vehicleEvents implements Listener {

    @EventHandler
    public void VehicleBlockCollision(VehicleBlockCollisionEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void VehicleCreate(VehicleCreateEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void VehicleDamage(VehicleDamageEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void VehicleDestroy(VehicleDestroyEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void VehicleEnter(VehicleEnterEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void VehicleEntityCollision(VehicleEntityCollisionEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void VehicleExit(VehicleExitEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void VehicleMove(VehicleMoveEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void VehicleUpdate(VehicleUpdateEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
}
