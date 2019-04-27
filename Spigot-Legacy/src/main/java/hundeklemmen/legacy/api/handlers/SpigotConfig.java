package hundeklemmen.legacy.api.handlers;

import hundeklemmen.legacy.MainPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class SpigotConfig extends hundeklemmen.shared.api.config {

    public FileConfiguration config;

    public SpigotConfig(MainPlugin instance) {
        super(true, true);
        instance.saveDefaultConfig(); //Saves default config if it doesn't exist.

        config = instance.getConfig();
        if(config.contains("versionChecker.notifyOP") == true) {
            this.VC_notifyOP = config.getBoolean("versionChecker.notifyOP");
        }
        if(config.contains("versionChecker.checkOnLoad") == true) {
            this.VC_checkOnLoad = config.getBoolean("versionChecker.checkOnLoad");
        }
    }

}
