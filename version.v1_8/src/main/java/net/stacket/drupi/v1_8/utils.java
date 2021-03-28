package net.stacket.drupi.v1_8;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;

import java.lang.reflect.Field;

public class utils {


    public static <T> T getField(Object from, String name) {
        Class<?> checkClass = from.getClass();
        do {
            try {
                Field field = checkClass.getDeclaredField(name);
                field.setAccessible(true);
                return (T) field.get(from);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } while (checkClass.getSuperclass() != Object.class && ((checkClass = checkClass.getSuperclass()) != null));
        return null;
    }

    public static CommandMap getCommandMap(){
        CommandMap commandMap=getField(Bukkit.getServer().getPluginManager(),"commandMap");
        if (commandMap == null) {
            commandMap = new SimpleCommandMap(Bukkit.getServer());
        }
        return commandMap;
    }

}
