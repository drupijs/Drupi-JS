package hundeklemmen.bungeecord.api;


import hundeklemmen.bungeecord.MainPlugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BungeeConfig extends hundeklemmen.shared.api.config {

    public Configuration config;

    public BungeeConfig(MainPlugin instance) {
        super(true, true);

        File confFile = new File(instance.getDataFolder(), "config.yml");
        MainPlugin.saveResource("config.yml", confFile);

        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(confFile);
            if(config.contains("versionChecker.notifyOP") == true) {
                this.VC_notifyOP = config.getBoolean("versionChecker.notifyOP");
            }
            if(config.contains("versionChecker.checkOnLoad") == true) {
                this.VC_checkOnLoad = config.getBoolean("versionChecker.checkOnLoad");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
