package hundeklemmen.legacy.expansions.placeholderapi;

import hundeklemmen.legacy.MainPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlaceholderAPIEventHandler implements Listener {

    @EventHandler
    public String get(PlaceholderAPIEvent event){
        MainPlugin.drupi.callFunction("onPlaceholderRequest", event);
        return null;
    }
}