package hundeklemmen.legacy.commands;

import hundeklemmen.legacy.MainPlugin;
import hundeklemmen.legacy.util;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class drupiCommand implements CommandExecutor {

    MainPlugin plugin;
    public drupiCommand(MainPlugin plugin){
        this.plugin = plugin;
    }

    private String prefix = ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "DRUPI" + ChatColor.DARK_AQUA + "] ";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(ChatColor.DARK_AQUA + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.DARK_AQUA + "]--------------");
            sender.sendMessage(ChatColor.AQUA + "/drupi reload " + ChatColor.DARK_AQUA + "Reload your scripts");
            sender.sendMessage(ChatColor.AQUA + "/drupi modules " + ChatColor.DARK_AQUA + "View all modules");
            sender.sendMessage(ChatColor.AQUA + "/drupi install <module> " + ChatColor.DARK_AQUA + "Install a module");
            sender.sendMessage(ChatColor.AQUA + "/drupi update " + ChatColor.DARK_AQUA + "Check for updates");
            sender.sendMessage(ChatColor.AQUA + "/drupi info " + ChatColor.DARK_AQUA + "View information");
            sender.sendMessage(ChatColor.AQUA + "/drupi help " + ChatColor.DARK_AQUA + "List commands");
            return true;
        }
        if(args[0].equalsIgnoreCase("reload")){
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if(!player.isOp() || !player.hasPermission("drupi.reload")){
                    sender.sendMessage(prefix + ChatColor.RED + "Whoops, it looks like you don't have access to this command!");
                    return true;
                }
            }
            plugin.reloadDrupi(sender);
            return true;
        } else if(args[0].equalsIgnoreCase("modules") || args[0].equalsIgnoreCase("module")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.isOp() || !player.hasPermission("drupi.modules")) {
                    sender.sendMessage(prefix + ChatColor.RED + "Whoops, it looks like you don't have access to this command!");
                    return true;
                }
            }
            sender.sendMessage(ChatColor.DARK_AQUA + "--------------[" + ChatColor.AQUA + "DRUPI MODULES" + ChatColor.DARK_AQUA + "]--------------");
            for (String module : plugin.loadedModules) {
                sender.sendMessage(ChatColor.AQUA + "- " + ChatColor.DARK_AQUA + module);
            }
        } else if(args[0].equalsIgnoreCase("install") || args[0].equalsIgnoreCase("i")){
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.isOp() || !player.hasPermission("drupi.install")) {
                    sender.sendMessage(prefix + ChatColor.RED + "Whoops, it looks like you don't have access to this command!");
                    return true;
                }
            }
            if(args.length <= 1) {
                sender.sendMessage(prefix + ChatColor.RED + "Usage: /drupi install <module>");
                return true;
            }
            if(args[1].equals("")||args[1].equals(" ")) return true;
            sender.sendMessage(ChatColor.DARK_AQUA + "--------------[" + ChatColor.AQUA + "DRUPI MODULES" + ChatColor.DARK_AQUA + "]--------------");
            this.installModule(args[1].toLowerCase(), sender);
        } else if(args[0].equalsIgnoreCase("update")){
            //Coming soon! :thonk:
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if(!player.isOp() || !player.hasPermission("drupi.update")){
                    sender.sendMessage(prefix + ChatColor.RED + "Whoops, it looks like you don't have access to this command!");
                    return true;
                }
            }
            util.Update(sender);

        } else if(args[0].equalsIgnoreCase("info")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if(!player.isOp() || !player.hasPermission("drupi.info")){
                    sender.sendMessage(prefix + ChatColor.RED + "Whoops, it looks like you don't have access to this command!");
                    return true;
                }
            }
            sender.sendMessage(ChatColor.DARK_AQUA + "--------------[" + ChatColor.AQUA + "DRUPI" + ChatColor.DARK_AQUA + "]--------------");
            sender.sendMessage(ChatColor.AQUA + "Platform: " + ChatColor.DARK_AQUA + plugin.drupi.platform);
            sender.sendMessage(ChatColor.AQUA + "Version: " + ChatColor.DARK_AQUA + plugin.drupi.version);
            sender.sendMessage(ChatColor.AQUA + "Loaded scripts: " + ChatColor.DARK_AQUA + plugin.loadedScripts);
            sender.sendMessage(ChatColor.AQUA + "Loaded modules: " + ChatColor.DARK_AQUA + plugin.loadedModules.size());

        } else {
            sender.sendMessage(prefix + ChatColor.RED + "Unknown command, view all commands using " + ChatColor.DARK_RED + "/drupi help");
        }
        return true;
    }

    private void installModule(String name, CommandSender sender){
        name = name.toLowerCase(); //To be sure.
        sender.sendMessage(prefix + ChatColor.AQUA + "Searching for " + ChatColor.DARK_AQUA + name);
        String content = httpGetRequest("https://api.stacket.net/modules/" + name);
        try {
            JSONObject module = (JSONObject) new JSONParser().parse(content);
            if(module.containsKey("package") && module.get("package") != null){
                JSONObject moduleObject = (JSONObject) module.get("package");
                sender.sendMessage(prefix + ChatColor.AQUA + "Found " + ChatColor.DARK_AQUA + moduleObject.get("name") + "@" + moduleObject.get("version"));
                sender.sendMessage(prefix + ChatColor.AQUA + "Starting download");
                String downloadURL = httpGetRequest("https://api.stacket.net/modules/" + moduleObject.get("name") + "/zip");
                File modulePath = new File(plugin.getDataFolder(), "scripts/modules/" + moduleObject.get("name"));
                File cloneDirectoryPath = new File(plugin.getDataFolder(), "scripts/modules/" + moduleObject.get("name") + ".zip");

                if(modulePath.exists()) modulePath.delete();
                if(cloneDirectoryPath.exists()) cloneDirectoryPath.delete();

                if(!downloadURL.equalsIgnoreCase("")){

                    new FileOutputStream(cloneDirectoryPath).getChannel().transferFrom(Channels.newChannel(new URL(downloadURL).openStream()), 0, Long.MAX_VALUE);
                    sender.sendMessage(prefix + ChatColor.AQUA + "Downloaded " + ChatColor.DARK_AQUA + moduleObject.get("name") + ".zip");
                    if(modulePath.exists()) modulePath.delete();
                    sender.sendMessage(prefix + ChatColor.AQUA + "Unzipping " + ChatColor.DARK_AQUA + (String) moduleObject.get("name") + ".zip ...");
                    this.unZipIt(cloneDirectoryPath, modulePath);
                    sender.sendMessage(prefix + ChatColor.AQUA + "Unzipped " + ChatColor.DARK_AQUA + (String) moduleObject.get("name") + ".zip");
                    cloneDirectoryPath.delete();
                    File[] fileList = modulePath.listFiles(File::isDirectory);
                    File folder = fileList[0];
                    this.move(modulePath, folder);
                    folder.delete();
                    sender.sendMessage(prefix + ChatColor.AQUA + "Module installation complete!");
                    checkForDependencies(name, sender);
                } else {
                    sender.sendMessage(prefix + ChatColor.RED + "Module missing download link!");
                }
            } else {
                sender.sendMessage(prefix + ChatColor.RED + "Unknown module!");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            sender.sendMessage(prefix + ChatColor.RED + "ERROR, Couldn't request Drupi module.");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void checkForDependencies(String module, CommandSender sender){
        try {
            File modulePath = new File(plugin.getDataFolder(), "scripts/modules/" + module.toLowerCase());
            File packageJSON = new File(modulePath, "package.json");
            String content = this.readFile(packageJSON);
            JSONObject packageJSONParsed = (JSONObject) new JSONParser().parse(content);
            if(packageJSONParsed.containsKey("dependencies")){
                JSONArray dependencies = (JSONArray) packageJSONParsed.get("dependencies");
                if(dependencies.size() == 0) return;

                sender.sendMessage(prefix + ChatColor.AQUA + "Installing dependencies for " + ChatColor.DARK_AQUA + module);
                for (int i = 0; i < dependencies.size(); i++) {
                    String name = (String) dependencies.get(i);
                    sender.sendMessage(prefix + ChatColor.AQUA + "- " + ChatColor.DARK_AQUA + name);
                }
                for (int i = 0; i < dependencies.size(); i++) {
                    String name = (String) dependencies.get(i);
                    File dependencyFile = new File(plugin.getDataFolder(), "scripts/modules/" + name.toLowerCase());
                    if(dependencyFile.exists()){
                        checkForDependencies(name, sender);
                    } else {
                        installModule(name, sender);
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String httpGetRequest(String urlAddress){
        try {
            URL url = new URL(urlAddress);
            Scanner a = new Scanner(url.openStream());
            String str = "";
            boolean first = true;
            while(a.hasNext()){
                if(first) str = a.next();
                else str += " " + a.next();
                first = false;
            }
            return str;
        } catch(IOException ex) {
            return null;
        }
    }

     private String readFile(File file){
        if(file.exists()) {
            try {
                return FileUtils.readFileToString(file, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void unZipIt(File zippingFile, File outputFile){
        try {
            ZipFile zipFile = new ZipFile(zippingFile);
            zipFile.extractAll(outputFile.getPath());
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }

    private void move(File toDir, File currDir) {
        for (File file : currDir.listFiles()) {
            if (file.isDirectory()) {
                move(toDir, file);
            } else {
                file.renameTo(new File(toDir, file.getName()));
            }
        }
    }
}
