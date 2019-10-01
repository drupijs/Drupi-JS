package hundeklemmen.legacy.extra;

import hundeklemmen.legacy.MainPlugin;
import hundeklemmen.shared.api.Drupi;

public class eventsHandler {

    private MainPlugin plugin;
    private Drupi drupi;

    public eventsHandler(MainPlugin plugin, Drupi drupi){
        this.plugin = plugin;
        this.drupi = drupi;
    }

    public void register(String event, Runnable func){

    }
}
