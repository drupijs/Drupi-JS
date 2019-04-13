package hundeklemmen;


import com.google.gson.Gson;
import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import javax.management.*;
import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.DecimalFormat;
import java.util.Map;

public class util {
    static void loadListeners(Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners)
            plugin.getServer().getPluginManager().registerEvents(listener, plugin);
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

    public static void checkVersion(){
        if(isUpdateAvailable() == true){
            MainPlugin.instance.getLogger().info("Whoups! It looks like Drupi is out of date!");
            MainPlugin.instance.getLogger().info("Pleaes go download the latest version of Drupi at https://www.spigotmc.org/resources/drupi-js.65706/");
            MainPlugin.instance.getLogger().info("!NOTE! If you don't have access to updating Drupi, please contact those who have.");
            MainPlugin.instance.update = true;
        } else {
            MainPlugin.instance.getLogger().info("You're running the latest version of Drupi!");
        }
    }

    public static boolean isUpdateAvailable(){
        String latestVersion = getLatestVersion();
        String currentVersion = MainPlugin.instance.getDescription().getVersion();
        if(!latestVersion.equalsIgnoreCase(currentVersion)){
           return true;
        } else {
            return false;
        }
    }

    public static String getLatestVersion(){
        MainPlugin.instance.getLogger().info("Checking for updates");
        try {
            String githubLatest = util.fireGet("https://api.github.com/repos/drupijs/Drupi-JS/releases/latest").toString();
            Map<String, Object> javaMap = new Gson().fromJson(githubLatest, Map.class);
            String latestVersion = javaMap.get("tag_name").toString();
            return latestVersion;
        } catch (IOException e) {
            MainPlugin.instance.getLogger().info("Something went wrong while trying to check for updates!");
            e.printStackTrace();
            return null;
        }
    }

    public static String fireGet(String urlParam) throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlParam);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    };

    private static void download(String urlStr, File file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }

    public static void Update(CommandSender sender){
        sender.sendMessage("Checking version..");
        String currentVersion = MainPlugin.instance.getDescription().getVersion();
        String latestVersion = getLatestVersion();
        sender.sendMessage("You're currently running " + currentVersion);
        sender.sendMessage("Latest Drupi version: " + latestVersion);
        if(currentVersion != latestVersion){
            sender.sendMessage("Downloading latest version of Drupi..");
            try {
                download("https://github.com/drupijs/Drupi-JS/releases/download/"+latestVersion+"/Drupi-Spigot.jar", new File("plugins", MainPlugin.DrupiFile.getName()));
                sender.sendMessage("Updated to version "+latestVersion+", please restart the server to finish the update!");
            } catch (IOException e) {
                e.printStackTrace();
                sender.sendMessage("Something went wrong?!");
            }
        } else {
            sender.sendMessage("You're already running the latest version, update cancelled!");
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
