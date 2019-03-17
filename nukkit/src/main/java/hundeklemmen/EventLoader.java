package hundeklemmen;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.*;
import cn.nukkit.event.entity.*;
import cn.nukkit.event.inventory.*;
import cn.nukkit.event.player.*;
import cn.nukkit.event.potion.PotionApplyEvent;
import cn.nukkit.event.potion.PotionCollideEvent;
import cn.nukkit.event.redstone.RedstoneUpdateEvent;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.event.server.DataPacketSendEvent;
import cn.nukkit.event.server.QueryRegenerateEvent;
import cn.nukkit.event.server.ServerCommandEvent;
import cn.nukkit.form.response.FormResponseModal;

public class EventLoader implements Listener {

    private main plugin;

    public EventLoader(main plugin){
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onLogin(PlayerPreLoginEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onInventory(InventoryTransactionEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        plugin.callEventHandler(event, event.getEventName());
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onEntity(EntitySpawnEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onEntityDespawn(EntityDespawnEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onEntityTeleport(EntityTeleportEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onItemDespawnEvent(ItemDespawnEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onItemSpawnEvent(ItemSpawnEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onProjecttileHit(ProjectileHitEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onEntityLevelChangeEvent(EntityLevelChangeEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onEntityInventoryChange(EntityInventoryChangeEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onExplosionPrime(EntityExplosionPrimeEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onBlockFormEvent(BlockFormEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onBlockGrow(BlockGrowEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onSignChangeEvent(SignChangeEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onItemFrameDrop(ItemFrameDropItemEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onRedstoneUpdateEvent(RedstoneUpdateEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onCraft(CraftItemEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onInventoryClock(InventoryCloseEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onFurnaceBurn(FurnaceBurnEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onFurnaceSmelt(FurnaceSmeltEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onInventoryPickupArrow(InventoryPickupArrowEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onIntentoryPickupItem(InventoryPickupItemEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onPotionApply(PotionApplyEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onPotionCollideEvent(PotionCollideEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onDataPacketReceive(DataPacketReceiveEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onDataPacketSend(DataPacketSendEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onQueryRegenerate(QueryRegenerateEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void formResponse(PlayerFormRespondedEvent event){
        FormResponseModal res = (FormResponseModal) event.getWindow().getResponse();
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }
}
