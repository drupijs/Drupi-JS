package hundeklemmen.v1_12;

import hundeklemmen.shared.api.Drupi;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.server.*;
import org.bukkit.event.vehicle.*;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.*;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.List;

public class loadEvents {
    public loadEvents(Drupi drupi) {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("Drupi");
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        Listener listener = new Listener() {};
        EventExecutor executor = new EventExecutor() {
            public void execute(Listener listener, Event event) {
                drupi.callFunction(event.getEventName(), event);
            }
        };


        List<Class<? extends Event>> eventsToListenFor = new ArrayList<Class<? extends Event>>();

        //Enchantment:
        eventsToListenFor.add(EnchantItemEvent.class);
        eventsToListenFor.add(PrepareItemEnchantEvent.class);
        //Entity:
        eventsToListenFor.add(AreaEffectCloudApplyEvent.class);
        eventsToListenFor.add(CreatureSpawnEvent.class);
        eventsToListenFor.add(CreeperPowerEvent.class);
        eventsToListenFor.add(EnderDragonChangePhaseEvent.class);
        eventsToListenFor.add(EntityAirChangeEvent.class);
        eventsToListenFor.add(EntityBreakDoorEvent.class);
        eventsToListenFor.add(EntityBreedEvent.class);
        eventsToListenFor.add(EntityChangeBlockEvent.class);
        eventsToListenFor.add(EntityCombustByBlockEvent.class);
        eventsToListenFor.add(EntityCombustByEntityEvent.class);
        eventsToListenFor.add(EntityCombustEvent.class);
        eventsToListenFor.add(EntityCreatePortalEvent.class);
        eventsToListenFor.add(EntityDamageByBlockEvent.class);
        eventsToListenFor.add(EntityDamageByEntityEvent.class);
        eventsToListenFor.add(EntityDamageEvent.class);
        eventsToListenFor.add(EntityDeathEvent.class);
        //eventsToListenFor.add(EntityEvent.class);
        eventsToListenFor.add(EntityExplodeEvent.class);
        eventsToListenFor.add(EntityInteractEvent.class);
        eventsToListenFor.add(EntityPickupItemEvent.class);
        eventsToListenFor.add(EntityPortalEnterEvent.class);
        eventsToListenFor.add(EntityPortalEvent.class);
        eventsToListenFor.add(EntityPortalExitEvent.class);
        eventsToListenFor.add(EntityRegainHealthEvent.class);
        eventsToListenFor.add(EntityResurrectEvent.class);
        eventsToListenFor.add(EntityShootBowEvent.class);
        eventsToListenFor.add(EntityTameEvent.class);
        eventsToListenFor.add(EntityTargetEvent.class);
        eventsToListenFor.add(EntityTargetLivingEntityEvent.class);
        eventsToListenFor.add(EntityTeleportEvent.class);
        eventsToListenFor.add(EntityToggleGlideEvent.class);
        eventsToListenFor.add(EntityUnleashEvent.class);
        eventsToListenFor.add(ExpBottleEvent.class);
        eventsToListenFor.add(ExplosionPrimeEvent.class);
        eventsToListenFor.add(FireworkExplodeEvent.class);
        eventsToListenFor.add(FoodLevelChangeEvent.class);
        eventsToListenFor.add(HorseJumpEvent.class);
        eventsToListenFor.add(ItemDespawnEvent.class);
        eventsToListenFor.add(ItemMergeEvent.class);
        eventsToListenFor.add(ItemSpawnEvent.class);
        eventsToListenFor.add(LingeringPotionSplashEvent.class);
        eventsToListenFor.add(PigZapEvent.class);
        eventsToListenFor.add(PlayerDeathEvent.class);
        eventsToListenFor.add(PlayerLeashEntityEvent.class);
        eventsToListenFor.add(PotionSplashEvent.class);
        eventsToListenFor.add(ProjectileHitEvent.class);
        eventsToListenFor.add(ProjectileLaunchEvent.class);
        eventsToListenFor.add(SheepDyeWoolEvent.class);
        eventsToListenFor.add(SheepRegrowWoolEvent.class);
        eventsToListenFor.add(SlimeSplitEvent.class);
        eventsToListenFor.add(VillagerAcquireTradeEvent.class);
        eventsToListenFor.add(VillagerReplenishTradeEvent.class);


        //Hanging:
        eventsToListenFor.add(HangingBreakByEntityEvent.class);
        eventsToListenFor.add(HangingBreakEvent.class);
        //eventsToListenFor.add(HangingEvent.class);
        eventsToListenFor.add(HangingPlaceEvent.class);


        //Inventory:
        eventsToListenFor.add(BrewEvent.class);
        eventsToListenFor.add(BrewingStandFuelEvent.class);
        eventsToListenFor.add(CraftItemEvent.class);
        eventsToListenFor.add(FurnaceBurnEvent.class);
        eventsToListenFor.add(FurnaceExtractEvent.class);
        eventsToListenFor.add(FurnaceSmeltEvent.class);
        eventsToListenFor.add(InventoryClickEvent.class);
        eventsToListenFor.add(InventoryCloseEvent.class);
        eventsToListenFor.add(InventoryCreativeEvent.class);
        eventsToListenFor.add(InventoryDragEvent.class);
        //eventsToListenFor.add(InventoryEvent.class);
        eventsToListenFor.add(InventoryInteractEvent.class);
        eventsToListenFor.add(InventoryMoveItemEvent.class);
        eventsToListenFor.add(InventoryOpenEvent.class);
        eventsToListenFor.add(InventoryPickupItemEvent.class);
        eventsToListenFor.add(PrepareAnvilEvent.class);
        eventsToListenFor.add(PrepareItemCraftEvent.class);

        //Painting: Removed in 1.9. JavaDoc message; "Events relating to paintings, but deprecated for more general hanging events."
        //Player:
        eventsToListenFor.add(AsyncPlayerChatEvent.class);
        eventsToListenFor.add(AsyncPlayerPreLoginEvent.class);
        eventsToListenFor.add(PlayerAchievementAwardedEvent.class);
        eventsToListenFor.add(PlayerAdvancementDoneEvent.class);
        eventsToListenFor.add(PlayerAnimationEvent.class);
        eventsToListenFor.add(PlayerArmorStandManipulateEvent.class);
        eventsToListenFor.add(PlayerBedEnterEvent.class);
        eventsToListenFor.add(PlayerBedLeaveEvent.class);
        eventsToListenFor.add(PlayerBucketEmptyEvent.class);
        //eventsToListenFor.add(PlayerBucketEvent.class);
        eventsToListenFor.add(PlayerBucketFillEvent.class);
        eventsToListenFor.add(PlayerChangedMainHandEvent.class);
        eventsToListenFor.add(PlayerChangedWorldEvent.class);
        eventsToListenFor.add(PlayerChannelEvent.class);
        // eventsToListenFor.add(PlayerChatEvent.class);
        eventsToListenFor.add(PlayerChatTabCompleteEvent.class);
        eventsToListenFor.add(PlayerCommandPreprocessEvent.class);
        eventsToListenFor.add(PlayerDropItemEvent.class);
        eventsToListenFor.add(PlayerEditBookEvent.class);
        eventsToListenFor.add(PlayerEggThrowEvent.class);
        //eventsToListenFor.add(PlayerEvent.class);
        eventsToListenFor.add(PlayerExpChangeEvent.class);
        eventsToListenFor.add(PlayerFishEvent.class);
        eventsToListenFor.add(PlayerGameModeChangeEvent.class);
        eventsToListenFor.add(PlayerInteractAtEntityEvent.class);
        eventsToListenFor.add(PlayerInteractEntityEvent.class);
        eventsToListenFor.add(PlayerInteractEvent.class);
        eventsToListenFor.add(PlayerItemBreakEvent.class);
        eventsToListenFor.add(PlayerItemConsumeEvent.class);
        eventsToListenFor.add(PlayerItemHeldEvent.class);
        eventsToListenFor.add(PlayerItemMendEvent.class);
        eventsToListenFor.add(PlayerJoinEvent.class);
        eventsToListenFor.add(PlayerKickEvent.class);
        eventsToListenFor.add(PlayerLevelChangeEvent.class);
        eventsToListenFor.add(PlayerLocaleChangeEvent.class);
        eventsToListenFor.add(PlayerLoginEvent.class);
        eventsToListenFor.add(PlayerMoveEvent.class);
        eventsToListenFor.add(PlayerPickupArrowEvent.class);
        eventsToListenFor.add(PlayerPickupItemEvent.class);
        eventsToListenFor.add(PlayerPortalEvent.class);
        eventsToListenFor.add(PlayerPreLoginEvent.class);
        eventsToListenFor.add(PlayerQuitEvent.class);
        eventsToListenFor.add(PlayerRegisterChannelEvent.class);
        eventsToListenFor.add(PlayerResourcePackStatusEvent.class);
        eventsToListenFor.add(PlayerRespawnEvent.class);
        eventsToListenFor.add(PlayerShearEntityEvent.class);
        eventsToListenFor.add(PlayerStatisticIncrementEvent.class);
        eventsToListenFor.add(PlayerSwapHandItemsEvent.class);
        eventsToListenFor.add(PlayerTeleportEvent.class);
        eventsToListenFor.add(PlayerToggleFlightEvent.class);
        eventsToListenFor.add(PlayerToggleSneakEvent.class);
        eventsToListenFor.add(PlayerToggleSprintEvent.class);
        eventsToListenFor.add(PlayerUnleashEntityEvent.class);
        eventsToListenFor.add(PlayerUnregisterChannelEvent.class);
        eventsToListenFor.add(PlayerVelocityEvent.class);

        //Server
        eventsToListenFor.add(BroadcastMessageEvent.class);
        eventsToListenFor.add(MapInitializeEvent.class);
        eventsToListenFor.add(PluginDisableEvent.class);
        eventsToListenFor.add(PluginEnableEvent.class);
        eventsToListenFor.add(RemoteServerCommandEvent.class);
        eventsToListenFor.add(ServerCommandEvent.class);
        eventsToListenFor.add(ServerListPingEvent.class);
        eventsToListenFor.add(ServiceRegisterEvent.class);
        eventsToListenFor.add(ServiceUnregisterEvent.class);
        eventsToListenFor.add(TabCompleteEvent.class);
        //Vehicle
        eventsToListenFor.add(VehicleBlockCollisionEvent.class);
        eventsToListenFor.add(VehicleCreateEvent.class);
        eventsToListenFor.add(VehicleDamageEvent.class);
        eventsToListenFor.add(VehicleDestroyEvent.class);
        eventsToListenFor.add(VehicleEnterEvent.class);
        eventsToListenFor.add(VehicleEntityCollisionEvent.class);
        eventsToListenFor.add(VehicleExitEvent.class);
        eventsToListenFor.add(VehicleMoveEvent.class);
        eventsToListenFor.add(VehicleUpdateEvent.class);
        //Weather
        eventsToListenFor.add(LightningStrikeEvent.class);
        eventsToListenFor.add(ThunderChangeEvent.class);
        eventsToListenFor.add(WeatherChangeEvent.class);
        //World
        eventsToListenFor.add(ChunkLoadEvent.class);
        eventsToListenFor.add(ChunkPopulateEvent.class);
        eventsToListenFor.add(ChunkUnloadEvent.class);
        eventsToListenFor.add(PortalCreateEvent.class);
        eventsToListenFor.add(SpawnChangeEvent.class);
        eventsToListenFor.add(StructureGrowEvent.class);
        eventsToListenFor.add(WorldInitEvent.class);
        eventsToListenFor.add(WorldLoadEvent.class);
        eventsToListenFor.add(WorldSaveEvent.class);
        eventsToListenFor.add(WorldUnloadEvent.class);
        //Block
        eventsToListenFor.add(BlockBreakEvent.class);
        eventsToListenFor.add(BlockBurnEvent.class);
        eventsToListenFor.add(BlockCanBuildEvent.class);
        eventsToListenFor.add(BlockDamageEvent.class);
        eventsToListenFor.add(BlockDispenseEvent.class);
        //eventsToListenFor.add(BlockEvent.class);
        eventsToListenFor.add(BlockExpEvent.class);
        eventsToListenFor.add(BlockExplodeEvent.class);
        eventsToListenFor.add(BlockFadeEvent.class);
        eventsToListenFor.add(BlockFormEvent.class);
        eventsToListenFor.add(BlockFromToEvent.class);
        eventsToListenFor.add(BlockGrowEvent.class);
        eventsToListenFor.add(BlockIgniteEvent.class);
        eventsToListenFor.add(BlockMultiPlaceEvent.class);
        eventsToListenFor.add(BlockPhysicsEvent.class);
        eventsToListenFor.add(BlockPistonExtendEvent.class);
        eventsToListenFor.add(BlockPistonRetractEvent.class);
        eventsToListenFor.add(BlockPlaceEvent.class);
        eventsToListenFor.add(BlockRedstoneEvent.class);
        eventsToListenFor.add(BlockSpreadEvent.class);
        eventsToListenFor.add(CauldronLevelChangeEvent.class);
        eventsToListenFor.add(EntityBlockFormEvent.class);
        eventsToListenFor.add(LeavesDecayEvent.class);
        eventsToListenFor.add(NotePlayEvent.class);
        eventsToListenFor.add(SignChangeEvent.class);


        for (Class<? extends Event> iClass : eventsToListenFor) {
            try {
                pluginManager.registerEvent(iClass, listener, EventPriority.HIGHEST, executor, plugin, true);
            } catch (IllegalPluginAccessException e){
                drupi.log.warning("Error while enabling " + iClass.getName() + "!");
                drupi.log.warning("[ERROR] " + e.getMessage());
            }
        }
    }
}
