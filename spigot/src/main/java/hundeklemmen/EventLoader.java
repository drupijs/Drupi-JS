package hundeklemmen;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.ServerListPingEvent;

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
    public void onInventory(InventoryOpenEvent event){
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
    public void onEntityDespawn(EntityDeathEvent event){
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
    public void onEntityLevelChangeEvent(PlayerLevelChangeEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onEntityInventoryChange(InventoryOpenEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onExplosionPrime(ExplosionPrimeEvent event){
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
    public void onLeavesDecay(LeavesDecayEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void onRedstoneUpdateEvent(BlockRedstoneEvent event){
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
    public void onIntentoryPickupItem(InventoryPickupItemEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }



    @EventHandler
    public void onQueryRegenerate(ServerListPingEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void inventoryListener(InventoryClickEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ChatListener(PlayerChatEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ChatListenerAsync(AsyncPlayerChatEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ChatTabCompleteListener(PlayerChatTabCompleteEvent event){
        plugin.callEventHandler(event, event.getClass().getSimpleName());
    }
}
