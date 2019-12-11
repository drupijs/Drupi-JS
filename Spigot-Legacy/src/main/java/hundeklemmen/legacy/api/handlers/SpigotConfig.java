package hundeklemmen.legacy.api.handlers;

import hundeklemmen.legacy.MainPlugin;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class SpigotConfig extends hundeklemmen.shared.api.config {

    public FileConfiguration config;

    public SpigotConfig(MainPlugin instance) {
        super(1, "modern", true, true);
        instance.saveDefaultConfig(); //Saves default config if it doesn't exist.

        config = instance.getConfig();

        if(!config.contains("config.version")&&!config.getString("config.version").equalsIgnoreCase("2.0")){
            File defaultJS = new File(instance.getDataFolder(), "config.yml");
            defaultJS.delete();
            instance.saveDefaultConfig();
            config = instance.getConfig();
        }

        if(config.contains("settings.compileMethod") == true){
            String method = config.getString("settings.compileMethod");
            if(method.equalsIgnoreCase("none")||method.equalsIgnoreCase("legacy")||method.equalsIgnoreCase("modern")){
                this.compileMethod = method.toLowerCase();
            } else {
                instance.getLogger().warning("Invalid compile method! Defaulting to modern");
            }
        }

        if(config.contains("settings.notifyOP") == true) {
            this.VC_notifyOP = config.getBoolean("versionChecker.notifyOP");
        }
        if(config.contains("settings.checkOnLoad") == true) {
            this.VC_checkOnLoad = config.getBoolean("versionChecker.checkOnLoad");
        }
    }

}
