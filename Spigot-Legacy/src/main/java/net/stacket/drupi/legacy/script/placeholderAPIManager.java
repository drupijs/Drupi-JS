package net.stacket.drupi.legacy.script;

import net.stacket.drupi.legacy.MainPlugin;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class placeholderAPIManager {

    private MainPlugin plugin;

    public placeholderAPIManager(MainPlugin plugin){
        this.plugin = plugin;
    }

    public String translateString(Player player, String text){
        return PlaceholderAPI.setPlaceholders(player, text);
    }

}
