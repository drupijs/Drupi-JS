package hundeklemmen.extra;

import hundeklemmen.MainPlugin;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlaceholderAPIExtension extends EZPlaceholderHook {

    private MainPlugin plugin;
    private String prefix;

    public PlaceholderAPIExtension(MainPlugin plugin, String prefix) {
        super(plugin, prefix);
        this.prefix = prefix;
        this.plugin = plugin;
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        PlaceholderAPIEvent event = new PlaceholderAPIEvent(identifier, player, prefix);
        Bukkit.getServer().getPluginManager().callEvent(event);
        return event.getResult();
    }


}
