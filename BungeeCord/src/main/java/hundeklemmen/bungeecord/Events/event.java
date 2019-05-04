package hundeklemmen.bungeecord.Events;

import hundeklemmen.shared.api.Drupi;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class event implements Listener {

    private Drupi drupi;

    public event(Drupi drupi){
        this.drupi = drupi;
    }

    @EventHandler
    public void ChatEvent(ChatEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void LoginEvent(LoginEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void PermissionCheckEvent(PermissionCheckEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void PlayerDisconnectEvent(PlayerDisconnectEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void PlayerHandshakeEvent(PlayerHandshakeEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void PluginMessageEvent(PluginMessageEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void PostLoginEvent(PostLoginEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void PreLoginEvent(PreLoginEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void ProxyPingEvent(ProxyPingEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void ProxyReloadEvent(ProxyReloadEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void ServerConnectedEvent(ServerConnectedEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void ServerConnectEvent(ServerConnectEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void ServerDisconnectEvent(ServerDisconnectEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void ServerKickEvent(ServerKickEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void ServerSwitchEvent(ServerSwitchEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void SettingsChangedEvent(SettingsChangedEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void TabCompleteEvent(TabCompleteEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void TabCompleteResponseEvent(TabCompleteResponseEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }

    @EventHandler
    public void TargetedEvent(TargetedEvent event){
        drupi.callFunction(event.getClass().getName(), event);
    }
}
