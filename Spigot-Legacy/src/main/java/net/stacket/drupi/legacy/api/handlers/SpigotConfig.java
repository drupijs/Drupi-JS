package net.stacket.drupi.legacy.api.handlers;

import net.stacket.drupi.legacy.MainPlugin;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SpigotConfig extends net.stacket.drupi.shared.api.config {

    public YamlConfiguration config;
    public MainPlugin instance;

    public SpigotConfig(MainPlugin instance) {
        super(1, "modern", true, true);
        this.instance = instance;
        File configFile = new File(instance.getDataFolder(), "config.yml");

        try {
            if(!configFile.exists()){
                instance.saveResource("config.yml", false);
            }
            config = new YamlConfiguration();
            config.load(configFile);
            if(!config.contains("config.version")){
                instance.drupi.log.warning("Outdated config, resetting it!");
                instance.saveResource("config.yml", true);
            }
            if(!config.get("config.version").toString().equalsIgnoreCase("2.1")){
                instance.drupi.log.warning("Outdated config, resetting it!");
                instance.saveResource("config.yml", true);

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
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
