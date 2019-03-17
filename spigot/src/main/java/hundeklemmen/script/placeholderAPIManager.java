package hundeklemmen.script;

import hundeklemmen.main;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class placeholderAPIManager {

    private main plugin;

    public placeholderAPIManager(main plugin){
        this.plugin = plugin;
    }

    public String translateString(Player player, String text){
        return PlaceholderAPI.setPlaceholders(player, text);
    }

}
