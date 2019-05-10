package hundeklemmen.minehut;


import hundeklemmen.minehut.script.FunctionManager;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

import javax.management.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;

public class util {


    public static boolean unregisterCommands(){
        CommandMap commandMap = hundeklemmen.v1_8.utils.getCommandMap();
        Map<String, Command> knownCommands = null;
        if(MainPlugin.serverVersion.startsWith("v1_8")||MainPlugin.serverVersion.startsWith("v1_9")||MainPlugin.serverVersion.startsWith("v1_10")||MainPlugin.serverVersion.startsWith("v1_11")||MainPlugin.serverVersion.startsWith("v1_12")) {
            knownCommands = hundeklemmen.v1_8.utils.getField(commandMap, "knownCommands");
        } else {
            try {
                knownCommands = (Map<String, Command>) hundeklemmen.v1_13.utils.getPrivateField(commandMap, "knownCommands");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (knownCommands == null) {
            return false;
        }
        for (Iterator<Command> i = knownCommands.values().iterator(); i.hasNext(); ) {
            org.bukkit.command.Command cmd=i.next();
            if (cmd instanceof FunctionManager.EntryCommand) {
                i.remove();
            }
        }


        return true;
    }

    public static void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch(Exception exc) {
            exc.printStackTrace();
        }
    }


    public static String getTPS() {

        String name1 = Bukkit.getServer().getClass().getPackage().getName();
        String version = name1.substring(name1.lastIndexOf('.') + 1);

        Class<?> mcsclass = null;

        DecimalFormat format = new DecimalFormat("##.##");

        Object si = null;
        Field tpsField = null;

        try {
            mcsclass = Class.forName("net.minecraft.server." + version + "." + "MinecraftServer");

            si = mcsclass.getMethod("getServer").invoke(null);

            tpsField = si.getClass().getField("recentTps");

        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | NoSuchFieldException | InvocationTargetException e) {
            e.printStackTrace();
        }

        double[] tps = null;

        try {
            tps = ((double[]) tpsField.get(si));

        } catch (IllegalArgumentException e) { e.printStackTrace(); } catch (IllegalAccessException e) { e.printStackTrace();}

        String response = format.format(tps[0]);
        if (StringUtils.substring(response, 0, 1).equalsIgnoreCase("2")|| StringUtils.substring(response, 0, 1).equalsIgnoreCase("3")|| StringUtils.substring(response, 0, 1).equalsIgnoreCase("4")|| StringUtils.substring(response, 0, 1).equalsIgnoreCase("5")|| StringUtils.substring(response, 0, 1).equalsIgnoreCase("6")) {
            response = "20,00";
        }
        return response;
    }

    public static double getProcessCpuLoad() {
        Double response = null;
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
            AttributeList list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });

            if (list.isEmpty())     return Double.NaN;

            Attribute att = (Attribute)list.get(0);
            Double value  = (Double)att.getValue();

            // usually takes a couple of seconds before we get real values
            if (value == -1.0)      return Double.NaN;
            // returns a percentage value with 1 decimal point precision
            response = ((int)(value * 1000) / 10.0);
            return response;
        } catch (MalformedObjectNameException | InstanceNotFoundException | ReflectionException e) {
            e.printStackTrace();
        }
        return response;
    };
    public static String color(String input){
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
