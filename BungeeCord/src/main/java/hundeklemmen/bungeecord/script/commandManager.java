package hundeklemmen.bungeecord.script;
;
import hundeklemmen.bungeecord.MainPlugin;
import hundeklemmen.bungeecord.util;
import hundeklemmen.shared.api.Drupi;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.graalvm.polyglot.Value;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class commandManager {

    private Drupi drupi;

    public commandManager(Drupi drupi){
        this.drupi = drupi;
    }

    public void create(String command, Value invokeFunction) {
        try {
            MainPlugin.instance.getProxy().getPluginManager().registerCommand(MainPlugin.instance, new drupiCommand(command, invokeFunction));
            MainPlugin.drupi.registeredCommands.add(command);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConsoleSender(CommandSender sender){
        if(!(sender instanceof ProxiedPlayer)){
            return  true;
        } else {
            return false;
        }
    }
    public boolean isPlayerSender(CommandSender sender){
        if(sender instanceof ProxiedPlayer){
            return  true;
        } else {
            return false;
        }
    }

    public class drupiCommand extends Command {

        private Value function;
        public drupiCommand(String name, Value function) {
            super(name);
            this.function = function;
        }

        @Override
        public void execute(CommandSender sender, String[] args) {
            function.executeVoid(null, sender, args);
        }
    }
}

