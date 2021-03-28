package net.stacket.drupi.bungeecord.script;

import express.Express;
import net.stacket.drupi.bungeecord.MainPlugin;

public class ExpressManager {

    MainPlugin instance;

    public ExpressManager(MainPlugin instance){
        this.instance = instance;
    }

    public Express New(){
        Express ex = new Express();
        instance.ExpressRunning.add(ex);
        return ex;
    }
    public Express New(String hostName){
        Express ex = new Express(hostName);
        instance.ExpressRunning.add(ex);
        return ex;
    }
}
