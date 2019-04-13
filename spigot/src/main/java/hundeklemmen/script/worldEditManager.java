package hundeklemmen.script;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import hundeklemmen.MainPlugin;
import hundeklemmen.worldedit.simpleManager;
import org.bukkit.Location;
import org.bukkit.World;

public class worldEditManager {

    private MainPlugin plugin;

    public worldEditManager(MainPlugin plugin){
        this.plugin = plugin;
    }

    public WorldEditPlugin advanced(){
        WorldEditPlugin WE = (WorldEditPlugin) plugin.getServer().getPluginManager().getPlugin("WorldEdit");
        return WE;
    }

    public Object simple(){
        return new simpleManager(plugin);
    }

    public Selection newCuboidSelection(World world, Location one, Location two){
        return new CuboidSelection(world, one, two);
    }


    public CuboidSelection castAsCuboidSelection(Selection selection){
        return (CuboidSelection) selection;
    }
}
