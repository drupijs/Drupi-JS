package hundeklemmen.shared.api;

public class config {

    //VersionChecker
    public boolean VC_notifyOP = true;
    public boolean VC_checkOnLoad = true;
    public int configVersion = 1;
    public String compileMethod = "modern";


    public config(int VC_configVersion, String VC_compileMethod, boolean _VC_notifyOP, boolean _VC_checkOnLoad){
        this.configVersion = VC_configVersion;
        this.compileMethod = VC_compileMethod;
        this.VC_notifyOP = _VC_notifyOP;
        this.VC_checkOnLoad = _VC_checkOnLoad;
    };

    public config() {
    }
}
