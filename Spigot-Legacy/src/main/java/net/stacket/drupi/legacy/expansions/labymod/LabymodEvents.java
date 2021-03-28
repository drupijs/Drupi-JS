package net.stacket.drupi.legacy.expansions.labymod;

import net.stacket.drupi.shared.api.Drupi;
import net.labymod.serverapi.bukkit.event.LabyModPlayerJoinEvent;
import net.labymod.serverapi.bukkit.event.MessageReceiveEvent;
import net.labymod.serverapi.bukkit.event.MessageSendEvent;
import net.labymod.serverapi.bukkit.event.PermissionsSendEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

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
        drupi.callEvent("LabyModLabyModPlayerJoinEvent", event);
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
