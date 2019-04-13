package hundeklemmen.events;

import hundeklemmen.MainPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;

public class enchantmentEvents implements Listener {

    @EventHandler
    public void EnchantItem(EnchantItemEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void PrepareItemEnchant(PrepareItemEnchantEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
}
