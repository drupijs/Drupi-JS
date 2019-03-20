package hundeklemmen.events;

import hundeklemmen.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.*;

public class serverEvents implements Listener {

    /*@EventHandler
    public void BroadcastMessage(BroadcastMessageEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void MapInitialize(MapInitializeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void PluginDisable(PluginDisableEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void PluginEnable(PluginEnableEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void RemoteServerCommand(RemoteServerCommandEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ServerCommand(ServerCommandEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ServerListPing(ServerListPingEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void ServerLoad(ServerLoadEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/

    @EventHandler
    public void ServiceRegister(ServiceRegisterEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ServiceUnregister(ServiceUnregisterEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void TabComplete(TabCompleteEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
}
