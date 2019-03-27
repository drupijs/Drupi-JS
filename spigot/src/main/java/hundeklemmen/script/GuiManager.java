package hundeklemmen.script;

import hundeklemmen.MainPlugin;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GuiManager {

    private MainPlugin plugin;

    public GuiManager(MainPlugin plugin){
        this.plugin = plugin;
    }

    public ItemStack newItemStack(Material obj){
        return new ItemStack(obj);
    }

}
