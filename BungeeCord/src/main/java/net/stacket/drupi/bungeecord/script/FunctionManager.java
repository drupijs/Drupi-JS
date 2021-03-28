package net.stacket.drupi.bungeecord.script;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.google.gson.JsonObject;
import net.stacket.drupi.bungeecord.MainPlugin;
import net.stacket.drupi.bungeecord.util;
import net.stacket.drupi.shared.api.Drupi;
import net.stacket.drupi.shared.api.DrupiScript;
import net.stacket.drupi.shared.api.interfaces.ScriptLoadMessage;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import org.apache.commons.io.FileUtils;
import org.graalvm.polyglot.Value;
import org.json.JSONArray;
import org.json.JSONObject;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;



import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;


public class FunctionManager {

    private MainPlugin plugin;
    private Drupi drupi;
    private static ClassLoader classLoader = FunctionManager.class.getClassLoader();

    public FunctionManager(MainPlugin plugin, Drupi drupi){
        this.plugin = plugin;
        this.drupi = drupi;
    }

    public int getPlayers(){
        return plugin.getProxy().getOnlineCount();
    }

    public void createFolder(String folderPath){
        File folder = new File(folderPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
    }

    public File getFile(String folder, String archive) {
        File file = new File(plugin.getDataFolder() + "/" + folder + "/");
        file.mkdirs();
        File yml = new File(plugin.getDataFolder() + "/" + folder + "/", archive);
        if (!yml.exists()) {
            try {
                yml.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return new File(plugin.getDataFolder() + "/" + folder + "/" + archive);
    };

    public void eval(final File loc){
        DrupiScript DS = new DrupiScript(loc);
        DS.Load(drupi, drupi.engine,true, new ScriptLoadMessage() {
            @Override
            public void onSuccess() {
                drupi.log.info("A drupi script have successfully loaded " + loc.getName());
                MainPlugin.loadedScripts++;
            }

            @Override
            public void onError(String error){
                drupi.log.info("Could not load " + loc.getName());
                drupi.log.info("[ERROR] " + error);
            }
        });
    }

    public void eval(final File loc, final Value run){
        DrupiScript DS = new DrupiScript(loc);
        DS.Load(drupi, drupi.engine,true, new ScriptLoadMessage() {
            @Override
            public void onSuccess() {
                if(run.canExecute()) run.executeVoid();
                drupi.log.info("A drupi script have successfully loaded " + loc.getName());
                MainPlugin.loadedScripts++;
            }

            @Override
            public void onError(String error){
                if(run.canExecute()) run.executeVoid();
                drupi.log.info("Could not load " + loc.getName());
                drupi.log.info("[ERROR] " + error);
            }
        });
    }

    public JsonObject toGson(Map<Object, Object> objectMap){
        JsonObject obj = new JsonObject();
        for(Map.Entry<Object, Object> entry : objectMap.entrySet()){
            if(entry.getValue() instanceof String){
                obj.addProperty(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            } else if(entry.getValue() instanceof Number){
                obj.addProperty(String.valueOf(entry.getKey()), (Number) entry.getValue());
            } else if(entry.getValue() instanceof Boolean){
                obj.addProperty(String.valueOf(entry.getKey()), (boolean) entry.getValue());
            } else if(entry.getValue() instanceof Character){
                obj.addProperty(String.valueOf(entry.getKey()), (Character) entry.getValue());
            } else {
                obj.addProperty(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
        };
        return obj;
    }

    public JSONObject toJson(Map<Object, Object> objectMap){
        JSONObject obj = new JSONObject();
        for(Map.Entry<Object, Object> entry : objectMap.entrySet()){
            obj.put(String.valueOf(entry.getKey()), entry.getValue());
        };
        return obj;
    }
    public JSONObject parseRawObject(String raw){
        return new JSONObject(raw);
    }
    public JSONArray parseRawArray(String raw){
        return new JSONArray(raw);
    }

    public void writeToFile(File file, String content){
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.write(content);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveFiles(File toDir, File currDir) {
        for (File file : currDir.listFiles()) {
            if (file.isDirectory()) {
                moveFiles(toDir, file);
            } else {
                file.renameTo(new File(toDir, file.getName()));
            }
        }
    }

    public void unzip(File zippingFile, File outputFile){
        try {
            ZipFile zipFile = new ZipFile(zippingFile);
            zipFile.extractAll(outputFile.getPath());
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
    public void zip(File input, File output){
        try {
            if(input.isDirectory()) {
                new ZipFile(output).addFolder(input);
            } else {
                new ZipFile(output).addFile(input);
            }
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

    public ClassLoader getClassLoader(){
        return this.classLoader;
    }

    public URLClassLoader loadExternal(File file){
        try {
            String jarURL = "jar:" + file.toURI() + "!/";
            URL urls [] = { new URL(jarURL) };
            URLClassLoader ucl = new URLClassLoader(urls, FunctionManager.classLoader);
            return ucl;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String readFile(File file){
        if(file.exists()) {
            try {
                return FileUtils.readFileToString(file, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public void cancelTask(int id){
        plugin.getProxy().getScheduler().cancel(id);
    }

    public Plugin getPlugin(String name){
        return plugin.getProxy().getPluginManager().getPlugin(name);
    }


    public void sendActionBar(ProxiedPlayer player, String Message){
        player.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(util.color(Message)));
    }

    public String color(String message){
        return util.color(message);
    }

    public String time(int seconds){
        int ss = seconds % 60;
        seconds /= 60;
        int min = seconds % 60;
        seconds /= 60;
        int hours = seconds % 24;
        return strzero(hours) + ":" + strzero(min) + ":" + strzero(ss);
    }

    public String format(String msg, Object... args){
        return String.format(msg, args);
    }

    private String strzero(int time){
        if(time < 10)
            return "0" + time;
        return String.valueOf(time);
    }

    public MainPlugin plugin(){
        return plugin;
    }



    public ByteArrayDataOutput newByteArrayDataOutput(){
        return ByteStreams.newDataOutput();
    }

    public void kickPlayer(ProxiedPlayer player){
        player.disconnect();
    }
    public void kickPlayer(ProxiedPlayer player, String reason){
        player.disconnect(new TextComponent(util.color(reason)));
    }

    public File getPluginFile(String pluginName){
        try {
            Plugin plugin = (Plugin) MainPlugin.instance.getProxy().getPluginManager().getPlugin(pluginName);
            Method getFileMethod = Plugin.class.getDeclaredMethod("getFile");
            getFileMethod.setAccessible(true);
            return (File) getFileMethod.invoke(plugin);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}
