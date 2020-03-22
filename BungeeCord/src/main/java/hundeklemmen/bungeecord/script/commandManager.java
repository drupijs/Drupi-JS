package hundeklemmen.bungeecord.script;
;
import hundeklemmen.bungeecord.MainPlugin;
import hundeklemmen.bungeecord.util;
import hundeklemmen.shared.api.Drupi;
import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import javax.activation.CommandMap;
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

    public void create(String command, JSObject invokeFunction) {
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
        public drupiCommand(String name, JSObject function) {
            super(name);
            this.function = function;
        }

        @Override
        public void execute(CommandSender sender, String[] args) {
            function.call(null, sender, args);
        }
    }
}

