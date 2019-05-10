package hundeklemmen.minehut.script;

import hundeklemmen.minehut.MainPlugin;
import jdk.nashorn.internal.objects.NativeArray;
import jdk.nashorn.internal.runtime.arrays.ArrayData;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class materialManager {

    private MainPlugin plugin;

    public materialManager(MainPlugin plugin){
        this.plugin = plugin;
    }


    public Material get(String search){
        return Material.getMaterial(search);
    }


    public ItemStack setName(ItemStack it, String name){
        ItemMeta meta = it.getItemMeta();
        meta.setDisplayName(name);
        it.setItemMeta(meta);
        return it;
    };

    public ItemStack setLore(ItemStack it, NativeArray list){
        ItemMeta meta = it.getItemMeta();

        ArrayData data = list.getArray();
        int length = (int) data.length();
        ArrayList<String> listFixed = new ArrayList<String>(length);
        for (int i = 0; i < length; i++) {
            listFixed.add(String.valueOf(data.getObject(i)));
        }
        meta.setLore(listFixed);

        it.setItemMeta(meta);
        return it;
    }

    public ItemStack newItemStack(Material obj){
        return new ItemStack(obj);
    }
    public ItemStack newItemStackWithData(Material obj, byte i){
        return new ItemStack(obj, i);
    }
}
