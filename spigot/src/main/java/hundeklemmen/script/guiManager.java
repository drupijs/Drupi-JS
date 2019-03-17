package hundeklemmen.script;

import hundeklemmen.main;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class guiManager {

    private main plugin;

    public guiManager(main plugin){
        this.plugin = plugin;
    }

    public ItemStack newItemStack(Material obj){
        return new ItemStack(obj);
    }

}
