package hundeklemmen.script;

import hundeklemmen.extra.ActionBar;
import hundeklemmen.extra.Title;
import hundeklemmen.extra.configHandler;
import hundeklemmen.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;



public class FunctionManager {

    private main plugin;

    public FunctionManager(main plugin){
        this.plugin = plugin;
    }


    public File getFile(String folder, String archive){
        File file = new File(plugin.getDataFolder() + "/" + folder + "/");
        file.mkdir();
        File yml =  new File(plugin.getDataFolder() + "/" + folder + "/", archive);
        if (!yml.exists()) {
            try {
                yml.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new File(plugin.getDataFolder() + "/" + folder + "/" + archive);
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
        try {
            ActionBar.sendHotBarMessage(player, Message);
        } catch (Exception e) {
            player.sendMessage(Message);
            e.printStackTrace();
        }
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

    public main plugin(){
        return plugin;
    }

    public class EntryCommand extends Command {

        private String functionName;

        public EntryCommand(String name, String description, String functionName) {
            super(name);
            this.functionName = functionName;
        }


        @Override
        public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            plugin.callCommand(sender, args, functionName);
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
            plugin.call(functionName);
        }
    }

    public void kickPlayer(Player player){
        player.kickPlayer("");
    }

}
