package hundeklemmen.worldedit;

import com.sk89q.worldedit.event.extent.EditSessionEvent;
import hundeklemmen.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import javax.script.Invocable;

public class worldEditEvents implements Listener {

    @EventHandler
    public void SessionEditEvent(EditSessionEvent event){
        this.callWorldEditEvent(event, event.getClass().getSimpleName());
    }


    public void callWorldEditEvent(EditSessionEvent event, String functionName){
        functionName = "WorldEdit_" + functionName;
        if (main.engine.get(functionName) == null) {
            return;
        }
        try {
            ((Invocable) main.engine).invokeFunction(functionName, event);
        } catch (final Exception se) {
            main.instance.getLogger().warning("Error while calling " + functionName);
            se.printStackTrace();
        }
    }
}
