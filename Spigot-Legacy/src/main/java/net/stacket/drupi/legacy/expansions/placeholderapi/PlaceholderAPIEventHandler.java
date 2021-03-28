package net.stacket.drupi.legacy.expansions.placeholderapi;

import net.stacket.drupi.legacy.MainPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlaceholderAPIEventHandler implements Listener {

    @EventHandler
    public String get(PlaceholderAPIEvent event){
        MainPlugin.drupi.callEvent("onPlaceholderRequest", event);
        return null;
    }
}