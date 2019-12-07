package hundeklemmen.shared.script;

import hundeklemmen.shared.api.Drupi;

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
}
