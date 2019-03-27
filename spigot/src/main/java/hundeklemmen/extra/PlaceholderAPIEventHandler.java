package hundeklemmen.extra;

import hundeklemmen.MainPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import javax.script.Invocable;

public class PlaceholderAPIEventHandler implements Listener {

    @EventHandler
    public String get(PlaceholderAPIEvent event){
        if (MainPlugin.engine.get("onPlaceholderRequest") == null) {
            return null;
        }
        try {
            ((Invocable) MainPlugin.engine).invokeFunction("onPlaceholderRequest", event);
        } catch (final Exception se) {
            MainPlugin.instance.getLogger().warning("Error while calling onPlaceholderRequest");
            se.printStackTrace();
        }
        return null;
    }
}
