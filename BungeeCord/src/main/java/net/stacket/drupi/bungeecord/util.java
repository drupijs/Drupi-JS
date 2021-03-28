package net.stacket.drupi.bungeecord;


import com.google.gson.Gson;
import net.stacket.drupi.bungeecord.script.commandManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class util {

    public static boolean unregisterCommands(){
        Collection<Map.Entry<String, Command>> knownCommands = MainPlugin.instance.getProxy().getPluginManager().getCommands();

        for (Iterator<Map.Entry<String, Command>> iterator = knownCommands.iterator(); iterator.hasNext();) {
            Map.Entry<String, Command> cmd = iterator.next();
            if (cmd.getValue() instanceof commandManager.drupiCommand) {
                MainPlugin.instance.getProxy().getPluginManager().unregisterCommand(cmd.getValue());
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
    public static void SaveResource(File f1, File f2){ ;
        try {
            InputStream confStream = new FileInputStream(f1);
            util.copy(confStream, f2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
                download("https://github.com/drupijs/Drupi-JS/releases/download/"+latestVersion+"/Drupi-Spigot-Legacy.jar", new File("plugins", MainPlugin.DrupiFile.getName()));
                sender.sendMessage("Updated to version "+latestVersion+", please restart the server to finish the update!");
            } catch (IOException e) {
                e.printStackTrace();
                sender.sendMessage("Something went wrong?!");
            }
        } else {
            sender.sendMessage("You're already running the latest version, update cancelled!");
        }
    }


    public static String color(String input){
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
