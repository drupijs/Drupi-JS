package hundeklemmen.legacy.script;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import hundeklemmen.legacy.MainPlugin;
import hundeklemmen.legacy.extra.ActionBar;
import hundeklemmen.legacy.extra.Title;
import hundeklemmen.legacy.extra.VersionSupportUtils;
import hundeklemmen.legacy.extra.configHandler;
import hundeklemmen.legacy.util;
import hundeklemmen.shared.api.Drupi;
import hundeklemmen.shared.api.DrupiScript;
import hundeklemmen.shared.api.interfaces.ScriptLoadMessage;
import jdk.nashorn.api.scripting.JSObject;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.ArrayList;


public class FunctionManager {

    private MainPlugin plugin;
    private Drupi drupi;

    public FunctionManager(MainPlugin plugin, Drupi drupi){
        this.plugin = plugin;
        this.drupi = drupi;
    }

    public int getPlayers(){
        return plugin.getServer().getOnlinePlayers().size();
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

    public void eval(File loc){
        DrupiScript DS = new DrupiScript(loc);
        DS.Load(drupi, true, new ScriptLoadMessage() {
            @Override
            public void onSuccess() {
                drupi.log.info("A drupi script have successfully loaded " + loc.getName());
            }

            @Override
            public void onError(String error){
                drupi.log.info("Could not load " + loc.getName());
                drupi.log.info("[ERROR] " + error);
            }
        });
    }

    public void eval(File loc, Runnable run){
        DrupiScript DS = new DrupiScript(loc);
        DS.Load(drupi, true, new ScriptLoadMessage() {
            @Override
            public void onSuccess() {
                run.run();
                drupi.log.info("A drupi script have successfully loaded " + loc.getName());
            }

            @Override
            public void onError(String error){
                run.run();
                drupi.log.info("Could not load " + loc.getName());
                drupi.log.info("[ERROR] " + error);
            }
        });
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

    public World getWorld(String worldName){
        return plugin.getServer().getWorld(worldName);
    }

    public FileConfiguration createConfig(File file){
        FileConfiguration Config = new configHandler() {
            @Override
            public boolean exists(String index) {
                return this.contains(index);
            }
            @Override
            public void save() {
                try {
                    this.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        try {
            Config.load(file);
            return Config;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createCommand(String name, String description, String functionName){

        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(name, new EntryCommand(name, description, functionName));
            MainPlugin.drupi.registeredCommands.add(name);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Location newLocation(double x, double y, double z){
        Location loc = null;
        loc.setX(x);
        loc.setY(y);
        loc.setZ(z);
        return loc;
    }
    public Location newLocationWorld(World world, double x, double y, double z){
        Location loc = new Location(world, x, y, z);
        return loc;
    }
    public int createTask(String functionName, int delay){
        return plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new ModTask(functionName), delay);
    }

    public int createLoopTask(String functionName, int delay){
        return plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new ModTask(functionName), 20, delay);
    }

    public void cancelTask(int id){
        plugin.getServer().getScheduler().cancelTask(id);
    }

    public Plugin getPlugin(String name){
        return plugin.getServer().getPluginManager().getPlugin(name);
    }

    public void sendTitle(Player player, String titleMessage, String subtitle, int fadeIn, int stay, int fadeOut){
        Title titleAPI = new Title(String.valueOf(titleMessage), String.valueOf(subtitle), fadeIn, stay, fadeOut);
        titleAPI.send(player);
    }

    public void sendActionBar(Player player, String Message){
        if(MainPlugin.serverVersion.startsWith("v1_8")) {
            hundeklemmen.v1_8.ActionBar ac = new hundeklemmen.v1_8.ActionBar(color(Message));
            ac.sendToPlayer(player);
        } else {
            try {
                ActionBar.sendHotBarMessage(player, Message);
            } catch (Exception e) {
                player.sendMessage(Message);
                e.printStackTrace();
            }
        }
    }

    public String getTPS(){
        return util.getTPS();
    }
    public double getCPU(){
        return util.getProcessCpuLoad();
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

    public class EntryCommand extends Command {

        private String functionName;

        public EntryCommand(String name, String description, String functionName) {
            super(name, description, "/"+name, new ArrayList<String>());
            this.functionName = functionName;
        }


        @Override
        public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            plugin.drupi.callFunction(functionName, sender, args);
            return false;
        }
    }



    public class ModTask implements Runnable {

        private String functionName;

        public ModTask(String functionName){
            this.functionName = functionName;
        }

        @Override
        public void run() {
            plugin.drupi.callFunction(functionName);
        }
    }
    public ByteArrayDataOutput newByteArrayDataOutput(){
        return ByteStreams.newDataOutput();
    }

    public void renameInventory(Player player, String title){
        VersionSupportUtils.getInstance().updateInventoryName(title, player);
    }


    public void kickPlayer(Player player){
        player.kickPlayer("");
    }
    public void kickPlayer(Player player, String reason){
        player.kickPlayer(reason);
    }

}
