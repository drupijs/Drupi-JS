package hundeklemmen.worldedit;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;
import hundeklemmen.MainPlugin;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class simpleManager {

    private MainPlugin plugin;
    private WorldEditPlugin WE;
    private static Plugin WEP;

    public simpleManager(MainPlugin plugin){
        this.plugin = plugin;
        this.WEP = plugin.getServer().getPluginManager().getPlugin("WorldEdit");
        this.WE = (WorldEditPlugin) WEP;
    }

    public void pasteSchematic(String schematicName, Location pasteLoc) {
        try {
            File dir = new File(WEP.getDataFolder(), "/schematics/" + schematicName + ".schematic");

            EditSession editSession = new EditSession(new BukkitWorld(pasteLoc.getWorld()), 999999999);
            editSession.enableQueue();

            SchematicFormat schematic = SchematicFormat.getFormat(dir);
            CuboidClipboard clipboard = schematic.load(dir);

            clipboard.paste(editSession, BukkitUtil.toVector(pasteLoc), true);
            editSession.flushQueue();
        } catch (IOException | DataException ex) {
            ex.printStackTrace();
        } catch (MaxChangedBlocksException ex) {
            ex.printStackTrace();
        }
    }
    public void pasteSchematicFromFile(String schematicName, Location pasteLoc, File dir) {
        try {
            EditSession editSession = new EditSession(new BukkitWorld(pasteLoc.getWorld()), 999999999);
            editSession.enableQueue();

            SchematicFormat schematic = SchematicFormat.getFormat(dir);
            CuboidClipboard clipboard = schematic.load(dir);

            clipboard.paste(editSession, BukkitUtil.toVector(pasteLoc), true);
            editSession.flushQueue();
        } catch (IOException | DataException ex) {
            ex.printStackTrace();
        } catch (MaxChangedBlocksException ex) {
            ex.printStackTrace();
        }
    }
}
