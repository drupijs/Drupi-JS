package hundeklemmen.legacy.script;

import com.sun.net.httpserver.HttpsConfigurator;
import express.Express;
import hundeklemmen.legacy.MainPlugin;
import hundeklemmen.shared.api.Drupi;

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
