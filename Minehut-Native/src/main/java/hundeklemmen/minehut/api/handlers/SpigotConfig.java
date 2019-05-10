package hundeklemmen.minehut.api.handlers;

import hundeklemmen.minehut.MainPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class SpigotConfig extends hundeklemmen.shared.api.config {

    public FileConfiguration config;

    public SpigotConfig(MainPlugin instance) {
        super(true, true);
        this.VC_checkOnLoad = false;
        this.VC_notifyOP = false;
    }

}
