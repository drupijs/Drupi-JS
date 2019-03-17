package hundeklemmen.extra;

import hundeklemmen.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import javax.script.Invocable;

public class PlaceholderAPIEventHandler implements Listener {

    @EventHandler
    public String get(PlaceholderAPIEvent event){
        if (main.engine.get("onPlaceholderRequest") == null) {
            return null;
        }
        try {
            ((Invocable) main.engine).invokeFunction("onPlaceholderRequest", event);
        } catch (final Exception se) {
            main.instance.getLogger().warning("Error while calling onPlaceholderRequest");
            se.printStackTrace();
        }
        return null;
    }
}
