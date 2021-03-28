package net.stacket.drupi.legacy.extra;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;


public class configHandler extends YamlConfiguration {
    public boolean exists(String index){
        return this.contains(index);
    }
    public void save(){
        try {
            this.save(new File(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
