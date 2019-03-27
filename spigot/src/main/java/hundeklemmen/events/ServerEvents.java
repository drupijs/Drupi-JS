package hundeklemmen.events;

import hundeklemmen.MainPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.*;

public class ServerEvents implements Listener {

    /*@EventHandler
    public void BroadcastMessage(BroadcastMessageEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void MapInitialize(MapInitializeEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void PluginDisable(PluginDisableEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void PluginEnable(PluginEnableEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void RemoteServerCommand(RemoteServerCommandEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ServerCommand(ServerCommandEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ServerListPing(ServerListPingEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void ServerLoad(ServerLoadEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/

    @EventHandler
    public void ServiceRegister(ServiceRegisterEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ServiceUnregister(ServiceUnregisterEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void TabComplete(TabCompleteEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
}
