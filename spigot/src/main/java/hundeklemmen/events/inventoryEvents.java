package hundeklemmen.events;

import hundeklemmen.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;

public class inventoryEvents implements Listener {

    @EventHandler
    public void Brew(BrewEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /* Not supported in 1.8 Need a fix shrug
    @EventHandler
    public void BrewingStandFuel(BrewingStandFuelEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void CraftItem(CraftItemEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void FurnaceBurn(FurnaceBurnEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void FurnaceExtract(FurnaceExtractEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void FurnaceSmelt(FurnaceSmeltEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void InventoryClick(InventoryClickEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void InventoryClose(InventoryCloseEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void InventoryCreative(InventoryCreativeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void InventoryDrag(InventoryDragEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void Inventory(InventoryEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void InventoryInteract(InventoryInteractEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void InventoryMoveItem(InventoryMoveItemEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void InventoryOpen(InventoryOpenEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void InventoryPickupItem(InventoryPickupItemEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /* Not supported in 1.8 Need a fix shrug
    @EventHandler
    public void PrepareAnvil(PrepareAnvilEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void PrepareItemCraft(PrepareItemCraftEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
}
