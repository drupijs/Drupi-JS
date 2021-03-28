package net.stacket.drupi.bungeecord.script.labymod;

import net.stacket.drupi.shared.api.Drupi;
import net.labymod.serverapi.bungee.event.LabyModPlayerJoinEvent;
import net.labymod.serverapi.bungee.event.MessageReceiveEvent;
import net.labymod.serverapi.bungee.event.MessageSendEvent;
import net.labymod.serverapi.bungee.event.PermissionsSendEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LabymodEvents implements Listener {

    private Drupi drupi;

    public LabymodEvents(Drupi drupi){
        this.drupi = drupi;
    }

    @EventHandler
    public void LabyMessageReceive(MessageReceiveEvent event){
        drupi.callEvent("LabyModMessageReceiveEvent", event);
    }

    @EventHandler
    public void LabyModPlayerJoin(LabyModPlayerJoinEvent event){
        drupi.callEvent("LabyModPlayerJoinEvent", event);
    }

    @EventHandler
    public void PermissionsSend(PermissionsSendEvent event){
        drupi.callEvent("LabyModPermissionsSendEvent", event);
    }

    @EventHandler
    public void MessageSend(MessageSendEvent event){
        drupi.callEvent("LabyModMessageSendEvent", event);
    }
}
