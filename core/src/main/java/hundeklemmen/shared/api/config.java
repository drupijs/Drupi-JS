package hundeklemmen.shared.api;

public class config {

    //VersionChecker
    public boolean VC_notifyOP = true;
    public boolean VC_checkOnLoad = true;
    public boolean VC_useBabel = true;

    public config(boolean _VC_notifyOP, boolean _VC_checkOnLoad, boolean _VC_useBabel){
        this.VC_notifyOP = _VC_notifyOP;
        this.VC_checkOnLoad = _VC_checkOnLoad;
        this.VC_useBabel = _VC_useBabel;
    };

    public config() {
    }
}
