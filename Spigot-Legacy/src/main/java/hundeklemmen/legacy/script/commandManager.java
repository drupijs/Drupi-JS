package hundeklemmen.legacy.script;

import hundeklemmen.legacy.MainPlugin;
import hundeklemmen.shared.api.Drupi;
import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

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

    public void create(String command, JSObject invokeFunction){
        this.create(command, "drupi", "", new ArrayList<String>(), "/"+command, "", "", invokeFunction);
    }

    public void create(String command, String prefix, JSObject invokeFunction){
        this.create(command, prefix, "", new ArrayList<String>(), "/"+command, "", "", invokeFunction);
    }
    public void create(String command, String prefix, List<String> aliases, JSObject invokeFunction){
        this.create(command, prefix, "", aliases, "/"+command, "", "", invokeFunction);
    }

    public void create(String command, JSObject options, JSObject invokeFunction) {
        String prefix = "Drupi";
        String description = "A drupi command";
        List<String> aliases = new ArrayList<String>();
        String usage = "/" + command;
        String permission = "";
        boolean requireOP = false;
        String permissionMessage = "";

        if(options.hasMember("prefix")){
            prefix = (String) options.getMember("prefix");
        }
        if(options.hasMember("description")){
            description = (String) options.getMember("description");
        }
        if(options.hasMember("aliases")){
            ScriptObjectMirror alias = (ScriptObjectMirror) options.getMember("aliases");
            if(alias.isArray()){
                String[] strings = alias.to(String[].class);
                aliases = Arrays.asList(strings);
            } else {
                Bukkit.getLogger().warning("ERROR! Command aliases is not an array!");
            }
        }
        if(options.hasMember("usage")){
            usage = (String) options.getMember("usage");
        }
        if(options.hasMember("permission")){
            if(options.getMember("permission") instanceof String) {
                permission = (String) options.getMember("permission");
            } else if(options.getMember("permission") instanceof Boolean) {
                if((Boolean) options.getMember("permission") == true){
                    requireOP = true;
                }
            }
        }
        if(options.hasMember("permissionMessage")){
            permissionMessage = (String) options.getMember("permissionMessage");
        }
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(prefix, new drupiCommand(command, description, aliases, usage, permission, permissionMessage, requireOP, invokeFunction));
            MainPlugin.drupi.registeredCommands.add(command);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void create(String command, String prefix, String description, List<String> aliases, String usage, String permission, String permissionMessage, JSObject invokeFunction){
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(prefix, new drupiCommand(command, description, aliases, usage, permission, permissionMessage, false, invokeFunction));
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
    public static Object[] toArray(ScriptObjectMirror scriptObjectMirror)
    {
        if (!scriptObjectMirror.isArray())
        {
            throw new IllegalArgumentException("ScriptObjectMirror is no array");
        }

        if (scriptObjectMirror.isEmpty())
        {
            return new Object[0];
        }

        Object[] array = new Object[scriptObjectMirror.size()];

        int i = 0;

        for (Map.Entry<String, Object> entry : scriptObjectMirror.entrySet())
        {
            Object result = entry.getValue();

            System.err.println(result.getClass());

            if (result instanceof ScriptObjectMirror && scriptObjectMirror.isArray())
            {
                array[i] = toArray((ScriptObjectMirror) result);
            }
            else
            {
                array[i] = result;
            }

            i++;
        }

        return array;
    }

    public class drupiCommand extends Command {

        private JSObject function;
        private String permission = null;
        private String permissionMessage = "";
        private Boolean requireOP = false;

        public drupiCommand(String name, String description, List<String> aliases, String usage, String permission, String permissionMessage, Boolean requireOP, JSObject function) {
            super(name, description, usage, aliases);
            this.requireOP = requireOP;
            this.permissionMessage = permissionMessage;
            if(!permission.equalsIgnoreCase("")) {
                this.permission = permission;
            }
            this.function = function;
        }


        @Override
        public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            if(requireOP == true && sender.isOp() == false || this.permission != null && !sender.hasPermission(this.permission)){
                sender.sendMessage(this.permissionMessage);
                return false;
            } else {
                function.call(null, sender, args);
            }
            return true;
        }
    }
}

