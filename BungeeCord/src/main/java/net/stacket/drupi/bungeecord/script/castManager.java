package net.stacket.drupi.bungeecord.script;

import net.stacket.drupi.shared.script.CastManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class castManager extends CastManager {

    public ProxiedPlayer asPlayer(CommandSender sender){
        return (ProxiedPlayer) sender;
    }

    public Object as(String type, Object obj){
        try {
            return Class.forName(type).cast(obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
            //(CraftPlayer) obj
        }
    }
}
