package net.stacket.drupi.shared.script;

import net.stacket.drupi.shared.api.Drupi;

public class console {

    public static Drupi drupi;
    private console instance;

    public console(Drupi drupi){
        this.drupi = drupi;
        this.instance = this;
    }

    public void log(String info){
        drupi.log.info(info);
    }
    public void warn(String info){
        drupi.log.warning(info);
    }
    public void error(String info){
        drupi.log.warning(info);
    }

}
