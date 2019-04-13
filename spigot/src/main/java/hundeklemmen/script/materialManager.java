package hundeklemmen.script;

import hundeklemmen.MainPlugin;
import jdk.nashorn.internal.objects.NativeArray;
import jdk.nashorn.internal.runtime.arrays.ArrayData;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class materialManager {

    private MainPlugin plugin;

    public materialManager(MainPlugin plugin){
        this.plugin = plugin;
    }


    public Material get(int search){
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
    public ItemStack getWoolColored(Color color){
        DyeColor col = DyeColor.getByColor(color);
        byte data = (byte) col.getData();
        return new ItemStack(Material.WOOL, 1, data);
    }

    public ItemStack newItemStack(Material obj){
        return new ItemStack(obj);
    }
    public ItemStack newItemStackWithData(Material obj, byte i){
        return new ItemStack(obj, i);
    }


    public Color getColor(String name){
        switch (name.toLowerCase().replaceAll("_", " ")) {
            case "black":
                return(Color.fromRGB(0, 0, 0));
            case "dark blue":
                return(Color.fromRGB(0, 0, 170));
            case "dark green":
                return(Color.fromRGB(0, 170, 0));
            case "dark aqua":
                return(Color.fromRGB(0, 170, 170));
            case "dark red":
                return(Color.fromRGB(170, 0, 0));
            case "dark purple":
                return(Color.fromRGB(170, 0, 170));
            case "gold":
                return(Color.fromRGB(255, 170, 0));
            case "gray":
                return(Color.fromRGB(170, 170, 170));
            case "dark gray":
                return(Color.fromRGB(85, 85, 85));
            case "blue":
                return(Color.fromRGB(85, 85, 255));
            case "green":
                return(Color.fromRGB(85, 255, 85));
            case "aqua":
                return(Color.fromRGB(85, 255, 255));
            case "red":
                return(Color.fromRGB(255, 85, 85));
            case "light purple":
                return(Color.fromRGB(255, 85, 255));
            case "yellow":
                return(Color.fromRGB(255, 255, 85));
            case "white":
                return(Color.fromRGB(255, 255, 255));
            default:
                return(Color.fromRGB(255, 255, 255));
        }
    };
}
