package net.stacket.drupi.bungeecord.Events;

import net.stacket.drupi.shared.api.Drupi;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class eventListener implements Listener {

    private Drupi drupi;

    public eventListener(Drupi drupi){
        this.drupi = drupi;
    }

    @EventHandler
    public void ChatEvent(ChatEvent event){
        drupi.callEvent("ChatEvent", event);
    }

    @EventHandler
    public void LoginEvent(LoginEvent event){
        drupi.callEvent("LoginEvent", event);
    }

    @EventHandler
    public void PermissionCheckEvent(PermissionCheckEvent event){
        drupi.callEvent("PermissionCheckEvent", event);
    }

    @EventHandler
    public void PlayerDisconnectEvent(PlayerDisconnectEvent event){
        drupi.callEvent("PlayerDisconnectEvent", event);
    }

    @EventHandler
    public void PlayerHandshakeEvent(PlayerHandshakeEvent event){
        drupi.callEvent("PlayerHandshakeEvent", event);
    }

    @EventHandler
    public void PluginMessageEvent(PluginMessageEvent event){
        drupi.callEvent("PluginMessageEvent", event);
    }

    @EventHandler
    public void PostLoginEvent(PostLoginEvent event){
        drupi.callEvent("PostLoginEvent", event);
    }

    @EventHandler
    public void PreLoginEvent(PreLoginEvent event){
        drupi.callEvent("PreLoginEvent", event);
    }

    @EventHandler
    public void ProxyPingEvent(ProxyPingEvent event){
        drupi.callEvent("ProxyPingEvent", event);
    }

    @EventHandler
    public void ProxyReloadEvent(ProxyReloadEvent event){
        drupi.callEvent("ProxyReloadEvent", event);
    }

    @EventHandler
    public void ServerConnectedEvent(ServerConnectedEvent event){
        drupi.callEvent("ServerConnectedEvent", event);
    }

    @EventHandler
    public void ServerConnectEvent(ServerConnectEvent event){
        drupi.callEvent("ServerConnectEvent", event);
    }

    @EventHandler
    public void ServerDisconnectEvent(ServerDisconnectEvent event){
        drupi.callEvent("ServerDisconnectEvent", event);
    }

    @EventHandler
    public void ServerKickEvent(ServerKickEvent event){
        drupi.callEvent("ServerKickEvent", event);
    }

    @EventHandler
    public void ServerSwitchEvent(ServerSwitchEvent event){
        drupi.callEvent("ServerSwitchEvent", event);
    }

    @EventHandler
    public void SettingsChangedEvent(SettingsChangedEvent event){
        drupi.callEvent("SettingsChangedEvent", event);
    }

    @EventHandler
    public void TabCompleteEvent(TabCompleteEvent event){
        drupi.callEvent("TabCompleteEvent", event);
    }

    @EventHandler
    public void TabCompleteResponseEvent(TabCompleteResponseEvent event){
        drupi.callEvent("TabCompleteResponseEvent", event);
    }

    @EventHandler
    public void TargetedEvent(TargetedEvent event){
        drupi.callEvent("TargetedEvent", event);
    }
}
