package hundeklemmen.bungeecord.api;


import hundeklemmen.bungeecord.MainPlugin;
import hundeklemmen.bungeecord.util;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BungeeConfig extends hundeklemmen.shared.api.config {

    public Configuration config;

    public BungeeConfig(MainPlugin instance) {
        super(1, "modern", true, true);

        File configFile = new File(instance.getDataFolder(), "config.yml");
        util.copy(instance.getResourceAsStream("config.yml"), configFile);


        try {
            if(!configFile.exists()){
                util.copy(instance.getResourceAsStream("config.yml"), configFile);
            }
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
            if(!config.contains("config.version")){
                instance.drupi.log.warning("Outdated config, resetting it!");
                configFile.delete();
                util.copy(instance.getResourceAsStream("config.yml"), configFile);
            }
            if(!config.get("config.version").toString().equalsIgnoreCase("2.1")){
                instance.drupi.log.warning("Outdated config, resetting it!");
                configFile.delete();
                util.copy(instance.getResourceAsStream("config.yml"), configFile);

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
