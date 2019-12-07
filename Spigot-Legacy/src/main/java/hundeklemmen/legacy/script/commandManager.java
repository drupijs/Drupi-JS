package hundeklemmen.legacy.script;

import hundeklemmen.legacy.MainPlugin;
import hundeklemmen.shared.api.Drupi;
import jdk.nashorn.api.scripting.JSObject;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class commandManager {

    private Drupi drupi;

    public commandManager(Drupi drupi){
        this.drupi = drupi;
    }

    public void create(String command, JSObject invokeFunction){
        this.create(command, "drupi", "", invokeFunction);
    }

    public void create(String command, String prefix, JSObject invokeFunction){
        this.create(command, prefix, "", invokeFunction);
    }

    public void create(String command, String prefix, String description, JSObject invokeFunction){
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(prefix, new drupiCommand(command, description, invokeFunction));
            MainPlugin.drupi.registeredCommands.add(command);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConsoleSender(CommandSender sender){
        if(sender instanceof ConsoleCommandSender){
            return  true;
        } else {
            return false;
        }
    }
    public boolean isPlayerSender(CommandSender sender){
        if(sender instanceof Player){
            return  true;
        } else {
            return false;
        }
    }

    public class drupiCommand extends Command {

        private JSObject function;

        public drupiCommand(String name, String description, JSObject function) {
            super(name, description, "/"+name, new ArrayList<String>());
            this.function = function;
        }


        @Override
        public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            function.call(null, sender, args);
            return false;
        }
    }
}

