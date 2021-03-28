package net.stacket.drupi.legacy.script;

import net.stacket.drupi.shared.api.Drupi;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.graalvm.polyglot.Value;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.plugin.Plugin;

public class commandManager {

    private final Drupi drupi;

    public commandManager(Drupi drupi){
        this.drupi = drupi;
    }

    public void create(String command, Value invokeFunction){
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(((Plugin) drupi.plugin).getName(), new drupiCommand(command, "", new ArrayList<String>(){}, "", "", "", false, invokeFunction));
            drupi.registeredCommands.add(command);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void create(String command, Value options, Value invokeFunction) {
        String prefix = options.hasMember("prefix") ? options.getMember("prefix").asString() : ((Plugin) drupi.plugin).getName();
        String description = options.hasMember("description") ? options.getMember("description").asString() : "";
        List<String> aliases = new ArrayList<>();
        String usage = options.hasMember("usage") ? options.getMember("usage").asString() : ("/" + command);
        String permission = "";
        boolean requireOP = false;
        String permissionMessage = options.hasMember("permissionMessage") ? options.getMember("permissionMessage").asString() : "";

        if(options.hasMember("aliases")){
            final Value alias = options.getMember("aliases");
            if(alias.hasArrayElements()){
                String[] strings = alias.as(String[].class);
                aliases = Arrays.asList(strings);
            } else {
                Bukkit.getLogger().warning("ERROR! Command aliases is not an array!");
            }
        }
        if(options.hasMember("permission")){
            final Value perm = options.getMember("permission");
            if(perm.isString()) permission = perm.asString();
            else if(perm.isBoolean()) requireOP = perm.asBoolean();
        }
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(prefix, new drupiCommand(command, description, aliases, usage, permission, permissionMessage, requireOP, invokeFunction));
            drupi.registeredCommands.add(command);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConsoleSender(CommandSender sender){
        return sender instanceof ConsoleCommandSender;
    }
    public boolean isPlayerSender(CommandSender sender){
        return sender instanceof Player;
    }

    public static class drupiCommand extends Command {

        private final Value function;
        private final String permissionMessage;
        private final Boolean requireOP;
        private String permission = null;

        public drupiCommand(String name, String description, List<String> aliases, String usage, String permission, String permissionMessage, Boolean requireOP, Value function) {
            super(name, description, usage, aliases);
            this.requireOP = requireOP;
            this.permissionMessage = permissionMessage;
            if(!permission.equalsIgnoreCase("")) this.permission = permission;
            this.function = function;
        }

        @Override
        public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            if(requireOP && !sender.isOp() || this.permission != null && !sender.hasPermission(this.permission)){
                sender.sendMessage(this.permissionMessage);
                return false;
            } else {
                if(function.canExecute()) function.executeVoid(sender, args);
            }
            return true;
        }
    }
}

