package hundeklemmen.script;

import hundeklemmen.MainPlugin;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class PlaceholderAPIManager {

    private MainPlugin plugin;

    public PlaceholderAPIManager(MainPlugin plugin){
        this.plugin = plugin;
    }

    public String translateString(Player player, String text){
        return PlaceholderAPI.setPlaceholders(player, text);
    }

}
