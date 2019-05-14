package hundeklemmen.legacy.expansions.placeholderapi;

import hundeklemmen.legacy.MainPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlaceholderAPIExtension extends PlaceholderExpansion {

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return "Hundeklemmen";
    }

    @Override
    public String getIdentifier(){
        return "drupi";
    }

    @Override
    public String getVersion(){
        return MainPlugin.instance.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier){
        PlaceholderAPIEvent event = new PlaceholderAPIEvent(identifier, player, "drupi");
        Bukkit.getServer().getPluginManager().callEvent(event);
        return event.getResult();
    }


}