package hundeklemmen.events;

import hundeklemmen.MainPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;

public class InventoryEvents implements Listener {

    @EventHandler
    public void Brew(BrewEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /* Not supported in 1.8 Need a fix shrug
    @EventHandler
    public void BrewingStandFuel(BrewingStandFuelEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void CraftItem(CraftItemEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void FurnaceBurn(FurnaceBurnEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void FurnaceExtract(FurnaceExtractEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void FurnaceSmelt(FurnaceSmeltEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void InventoryClick(InventoryClickEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void InventoryClose(InventoryCloseEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void InventoryCreative(InventoryCreativeEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void InventoryDrag(InventoryDragEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void InventoryInteract(InventoryInteractEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void InventoryMoveItem(InventoryMoveItemEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void InventoryOpen(InventoryOpenEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void InventoryPickupItem(InventoryPickupItemEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /* Not supported in 1.8 Need a fix shrug
    @EventHandler
    public void PrepareAnvil(PrepareAnvilEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void PrepareItemCraft(PrepareItemCraftEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
}
