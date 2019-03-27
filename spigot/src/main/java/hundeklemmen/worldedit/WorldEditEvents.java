package hundeklemmen.worldedit;

import com.sk89q.worldedit.event.extent.EditSessionEvent;
import hundeklemmen.MainPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import javax.script.Invocable;

public class WorldEditEvents implements Listener {

    @EventHandler
    public void SessionEditEvent(EditSessionEvent event){
        this.callWorldEditEvent(event, event.getClass().getSimpleName());
    }


    public void callWorldEditEvent(EditSessionEvent event, String functionName){
        functionName = "WorldEdit_" + functionName;
        if (MainPlugin.engine.get(functionName) == null) {
            return;
        }
        try {
            ((Invocable) MainPlugin.engine).invokeFunction(functionName, event);
        } catch (final Exception se) {
            MainPlugin.instance.getLogger().warning("Error while calling " + functionName);
            se.printStackTrace();
        }
    }
}
