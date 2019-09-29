package hundeklemmen.v1_12;

import hundeklemmen.shared.api.Drupi;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
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



public class loadEvents implements Listener{
    private Drupi drupi;

    public loadEvents(Drupi drupi) {
        this.drupi = drupi;
        Plugin plugin = Bukkit.getPluginManager().getPlugin("Drupi");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEnchantItemEvent(EnchantItemEvent event){
        drupi.callFunction("EnchantItemEvent", event);
    }


    @EventHandler
    public void onPrepareItemEnchantEvent(PrepareItemEnchantEvent event){
        drupi.callFunction("PrepareItemEnchantEvent", event);
    }


    @EventHandler
    public void onAreaEffectCloudApplyEvent(AreaEffectCloudApplyEvent event){
        drupi.callFunction("AreaEffectCloudApplyEvent", event);
    }


    @EventHandler
    public void onCreatureSpawnEvent(CreatureSpawnEvent event){
        drupi.callFunction("CreatureSpawnEvent", event);
    }


    @EventHandler
    public void onCreeperPowerEvent(CreeperPowerEvent event){
        drupi.callFunction("CreeperPowerEvent", event);
    }


    @EventHandler
    public void onEnderDragonChangePhaseEvent(EnderDragonChangePhaseEvent event){
        drupi.callFunction("EnderDragonChangePhaseEvent", event);
    }


    @EventHandler
    public void onEntityAirChangeEvent(EntityAirChangeEvent event){
        drupi.callFunction("EntityAirChangeEvent", event);
    }


    @EventHandler
    public void onEntityBreakDoorEvent(EntityBreakDoorEvent event){
        drupi.callFunction("EntityBreakDoorEvent", event);
    }


    @EventHandler
    public void onEntityBreedEvent(EntityBreedEvent event){
        drupi.callFunction("EntityBreedEvent", event);
    }


    @EventHandler
    public void onEntityChangeBlockEvent(EntityChangeBlockEvent event){
        drupi.callFunction("EntityChangeBlockEvent", event);
    }


    @EventHandler
    public void onEntityCombustByBlockEvent(EntityCombustByBlockEvent event){
        drupi.callFunction("EntityCombustByBlockEvent", event);
    }


    @EventHandler
    public void onEntityCombustByEntityEvent(EntityCombustByEntityEvent event){
        drupi.callFunction("EntityCombustByEntityEvent", event);
    }


    @EventHandler
    public void onEntityCombustEvent(EntityCombustEvent event){
        drupi.callFunction("EntityCombustEvent", event);
    }


    @EventHandler
    public void onEntityCreatePortalEvent(EntityCreatePortalEvent event){
        drupi.callFunction("EntityCreatePortalEvent", event);
    }


    @EventHandler
    public void onEntityDamageByBlockEvent(EntityDamageByBlockEvent event){
        drupi.callFunction("EntityDamageByBlockEvent", event);
    }


    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event){
        drupi.callFunction("EntityDamageByEntityEvent", event);
    }


    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event){
        drupi.callFunction("EntityDamageEvent", event);
    }


    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event){
        drupi.callFunction("EntityDeathEvent", event);
    }


    @EventHandler
    public void onEntityExplodeEvent(EntityExplodeEvent event){
        drupi.callFunction("EntityExplodeEvent", event);
    }


    @EventHandler
    public void onEntityInteractEvent(EntityInteractEvent event){
        drupi.callFunction("EntityInteractEvent", event);
    }


    @EventHandler
    public void onEntityPickupItemEvent(EntityPickupItemEvent event){
        drupi.callFunction("EntityPickupItemEvent", event);
    }


    @EventHandler
    public void onEntityPortalEnterEvent(EntityPortalEnterEvent event){
        drupi.callFunction("EntityPortalEnterEvent", event);
    }


    @EventHandler
    public void onEntityPortalEvent(EntityPortalEvent event){
        drupi.callFunction("EntityPortalEvent", event);
    }


    @EventHandler
    public void onEntityPortalExitEvent(EntityPortalExitEvent event){
        drupi.callFunction("EntityPortalExitEvent", event);
    }


    @EventHandler
    public void onEntityRegainHealthEvent(EntityRegainHealthEvent event){
        drupi.callFunction("EntityRegainHealthEvent", event);
    }


    @EventHandler
    public void onEntityResurrectEvent(EntityResurrectEvent event){
        drupi.callFunction("EntityResurrectEvent", event);
    }


    @EventHandler
    public void onEntityShootBowEvent(EntityShootBowEvent event){
        drupi.callFunction("EntityShootBowEvent", event);
    }


    @EventHandler
    public void onEntityTameEvent(EntityTameEvent event){
        drupi.callFunction("EntityTameEvent", event);
    }


    @EventHandler
    public void onEntityTargetEvent(EntityTargetEvent event){
        drupi.callFunction("EntityTargetEvent", event);
    }


    @EventHandler
    public void onEntityTargetLivingEntityEvent(EntityTargetLivingEntityEvent event){
        drupi.callFunction("EntityTargetLivingEntityEvent", event);
    }


    @EventHandler
    public void onEntityTeleportEvent(EntityTeleportEvent event){
        drupi.callFunction("EntityTeleportEvent", event);
    }


    @EventHandler
    public void onEntityToggleGlideEvent(EntityToggleGlideEvent event){
        drupi.callFunction("EntityToggleGlideEvent", event);
    }


    @EventHandler
    public void onEntityUnleashEvent(EntityUnleashEvent event){
        drupi.callFunction("EntityUnleashEvent", event);
    }


    @EventHandler
    public void onExpBottleEvent(ExpBottleEvent event){
        drupi.callFunction("ExpBottleEvent", event);
    }


    @EventHandler
    public void onExplosionPrimeEvent(ExplosionPrimeEvent event){
        drupi.callFunction("ExplosionPrimeEvent", event);
    }


    @EventHandler
    public void onFireworkExplodeEvent(FireworkExplodeEvent event){
        drupi.callFunction("FireworkExplodeEvent", event);
    }


    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent event){
        drupi.callFunction("FoodLevelChangeEvent", event);
    }


    @EventHandler
    public void onHorseJumpEvent(HorseJumpEvent event){
        drupi.callFunction("HorseJumpEvent", event);
    }


    @EventHandler
    public void onItemDespawnEvent(ItemDespawnEvent event){
        drupi.callFunction("ItemDespawnEvent", event);
    }


    @EventHandler
    public void onItemMergeEvent(ItemMergeEvent event){
        drupi.callFunction("ItemMergeEvent", event);
    }


    @EventHandler
    public void onItemSpawnEvent(ItemSpawnEvent event){
        drupi.callFunction("ItemSpawnEvent", event);
    }


    @EventHandler
    public void onLingeringPotionSplashEvent(LingeringPotionSplashEvent event){
        drupi.callFunction("LingeringPotionSplashEvent", event);
    }


    @EventHandler
    public void onPigZapEvent(PigZapEvent event){
        drupi.callFunction("PigZapEvent", event);
    }


    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event){
        drupi.callFunction("PlayerDeathEvent", event);
    }


    @EventHandler
    public void onPlayerLeashEntityEvent(PlayerLeashEntityEvent event){
        drupi.callFunction("PlayerLeashEntityEvent", event);
    }


    @EventHandler
    public void onPotionSplashEvent(PotionSplashEvent event){
        drupi.callFunction("PotionSplashEvent", event);
    }


    @EventHandler
    public void onProjectileHitEvent(ProjectileHitEvent event){
        drupi.callFunction("ProjectileHitEvent", event);
    }


    @EventHandler
    public void onProjectileLaunchEvent(ProjectileLaunchEvent event){
        drupi.callFunction("ProjectileLaunchEvent", event);
    }


    @EventHandler
    public void onSheepDyeWoolEvent(SheepDyeWoolEvent event){
        drupi.callFunction("SheepDyeWoolEvent", event);
    }


    @EventHandler
    public void onSheepRegrowWoolEvent(SheepRegrowWoolEvent event){
        drupi.callFunction("SheepRegrowWoolEvent", event);
    }


    @EventHandler
    public void onSlimeSplitEvent(SlimeSplitEvent event){
        drupi.callFunction("SlimeSplitEvent", event);
    }


    @EventHandler
    public void onVillagerAcquireTradeEvent(VillagerAcquireTradeEvent event){
        drupi.callFunction("VillagerAcquireTradeEvent", event);
    }


    @EventHandler
    public void onVillagerReplenishTradeEvent(VillagerReplenishTradeEvent event){
        drupi.callFunction("VillagerReplenishTradeEvent", event);
    }


    @EventHandler
    public void onHangingBreakByEntityEvent(HangingBreakByEntityEvent event){
        drupi.callFunction("HangingBreakByEntityEvent", event);
    }


    @EventHandler
    public void onHangingBreakEvent(HangingBreakEvent event){
        drupi.callFunction("HangingBreakEvent", event);
    }


    @EventHandler
    public void onHangingPlaceEvent(HangingPlaceEvent event){
        drupi.callFunction("HangingPlaceEvent", event);
    }


    @EventHandler
    public void onBrewEvent(BrewEvent event){
        drupi.callFunction("BrewEvent", event);
    }


    @EventHandler
    public void onBrewingStandFuelEvent(BrewingStandFuelEvent event){
        drupi.callFunction("BrewingStandFuelEvent", event);
    }


    @EventHandler
    public void onCraftItemEvent(CraftItemEvent event){
        drupi.callFunction("CraftItemEvent", event);
    }


    @EventHandler
    public void onFurnaceBurnEvent(FurnaceBurnEvent event){
        drupi.callFunction("FurnaceBurnEvent", event);
    }


    @EventHandler
    public void onFurnaceExtractEvent(FurnaceExtractEvent event){
        drupi.callFunction("FurnaceExtractEvent", event);
    }


    @EventHandler
    public void onFurnaceSmeltEvent(FurnaceSmeltEvent event){
        drupi.callFunction("FurnaceSmeltEvent", event);
    }


    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event){
        drupi.callFunction("InventoryClickEvent", event);
    }


    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event){
        drupi.callFunction("InventoryCloseEvent", event);
    }


    @EventHandler
    public void onInventoryCreativeEvent(InventoryCreativeEvent event){
        drupi.callFunction("InventoryCreativeEvent", event);
    }


    @EventHandler
    public void onInventoryDragEvent(InventoryDragEvent event){
        drupi.callFunction("InventoryDragEvent", event);
    }


    @EventHandler
    public void onInventoryInteractEvent(InventoryInteractEvent event){
        drupi.callFunction("InventoryInteractEvent", event);
    }


    @EventHandler
    public void onInventoryMoveItemEvent(InventoryMoveItemEvent event){
        drupi.callFunction("InventoryMoveItemEvent", event);
    }


    @EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent event){
        drupi.callFunction("InventoryOpenEvent", event);
    }


    @EventHandler
    public void onInventoryPickupItemEvent(InventoryPickupItemEvent event){
        drupi.callFunction("InventoryPickupItemEvent", event);
    }


    @EventHandler
    public void onPrepareAnvilEvent(PrepareAnvilEvent event){
        drupi.callFunction("PrepareAnvilEvent", event);
    }


    @EventHandler
    public void onPrepareItemCraftEvent(PrepareItemCraftEvent event){
        drupi.callFunction("PrepareItemCraftEvent", event);
    }


    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event){
        drupi.callFunction("AsyncPlayerChatEvent", event);
    }


    @EventHandler
    public void onAsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent event){
        drupi.callFunction("AsyncPlayerPreLoginEvent", event);
    }


    @EventHandler
    public void onPlayerAdvancementDoneEvent(PlayerAdvancementDoneEvent event){
        drupi.callFunction("PlayerAdvancementDoneEvent", event);
    }


    @EventHandler
    public void onPlayerAnimationEvent(PlayerAnimationEvent event){
        drupi.callFunction("PlayerAnimationEvent", event);
    }


    @EventHandler
    public void onPlayerArmorStandManipulateEvent(PlayerArmorStandManipulateEvent event){
        drupi.callFunction("PlayerArmorStandManipulateEvent", event);
    }


    @EventHandler
    public void onPlayerBedEnterEvent(PlayerBedEnterEvent event){
        drupi.callFunction("PlayerBedEnterEvent", event);
    }


    @EventHandler
    public void onPlayerBedLeaveEvent(PlayerBedLeaveEvent event){
        drupi.callFunction("PlayerBedLeaveEvent", event);
    }


    @EventHandler
    public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent event){
        drupi.callFunction("PlayerBucketEmptyEvent", event);
    }


    @EventHandler
    public void onPlayerBucketFillEvent(PlayerBucketFillEvent event){
        drupi.callFunction("PlayerBucketFillEvent", event);
    }


    @EventHandler
    public void onPlayerChangedMainHandEvent(PlayerChangedMainHandEvent event){
        drupi.callFunction("PlayerChangedMainHandEvent", event);
    }


    @EventHandler
    public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent event){
        drupi.callFunction("PlayerChangedWorldEvent", event);
    }


    @EventHandler
    public void onPlayerChannelEvent(PlayerChannelEvent event){
        drupi.callFunction("PlayerChannelEvent", event);
    }


    @EventHandler
    public void onPlayerChatTabCompleteEvent(PlayerChatTabCompleteEvent event){
        drupi.callFunction("PlayerChatTabCompleteEvent", event);
    }


    @EventHandler
    public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event){
        drupi.callFunction("PlayerCommandPreprocessEvent", event);
    }


    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent event){
        drupi.callFunction("PlayerDropItemEvent", event);
    }


    @EventHandler
    public void onPlayerEditBookEvent(PlayerEditBookEvent event){
        drupi.callFunction("PlayerEditBookEvent", event);
    }


    @EventHandler
    public void onPlayerEggThrowEvent(PlayerEggThrowEvent event){
        drupi.callFunction("PlayerEggThrowEvent", event);
    }


    @EventHandler
    public void onPlayerExpChangeEvent(PlayerExpChangeEvent event){
        drupi.callFunction("PlayerExpChangeEvent", event);
    }


    @EventHandler
    public void onPlayerFishEvent(PlayerFishEvent event){
        drupi.callFunction("PlayerFishEvent", event);
    }


    @EventHandler
    public void onPlayerGameModeChangeEvent(PlayerGameModeChangeEvent event){
        drupi.callFunction("PlayerGameModeChangeEvent", event);
    }


    @EventHandler
    public void onPlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent event){
        drupi.callFunction("PlayerInteractAtEntityEvent", event);
    }


    @EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event){
        drupi.callFunction("PlayerInteractEntityEvent", event);
    }


    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event){
        drupi.callFunction("PlayerInteractEvent", event);
    }


    @EventHandler
    public void onPlayerItemBreakEvent(PlayerItemBreakEvent event){
        drupi.callFunction("PlayerItemBreakEvent", event);
    }


    @EventHandler
    public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent event){
        drupi.callFunction("PlayerItemConsumeEvent", event);
    }


    @EventHandler
    public void onPlayerItemHeldEvent(PlayerItemHeldEvent event){
        drupi.callFunction("PlayerItemHeldEvent", event);
    }


    @EventHandler
    public void onPlayerItemMendEvent(PlayerItemMendEvent event){
        drupi.callFunction("PlayerItemMendEvent", event);
    }


    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        drupi.callFunction("PlayerJoinEvent", event);
    }


    @EventHandler
    public void onPlayerKickEvent(PlayerKickEvent event){
        drupi.callFunction("PlayerKickEvent", event);
    }


    @EventHandler
    public void onPlayerLevelChangeEvent(PlayerLevelChangeEvent event){
        drupi.callFunction("PlayerLevelChangeEvent", event);
    }


    @EventHandler
    public void onPlayerLocaleChangeEvent(PlayerLocaleChangeEvent event){
        drupi.callFunction("PlayerLocaleChangeEvent", event);
    }


    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent event){
        drupi.callFunction("PlayerLoginEvent", event);
    }


    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event){
        drupi.callFunction("PlayerMoveEvent", event);
    }


    @EventHandler
    public void onPlayerPickupArrowEvent(PlayerPickupArrowEvent event){
        drupi.callFunction("PlayerPickupArrowEvent", event);
    }


    @EventHandler
    public void onPlayerPickupItemEvent(PlayerPickupItemEvent event){
        drupi.callFunction("PlayerPickupItemEvent", event);
    }


    @EventHandler
    public void onPlayerPortalEvent(PlayerPortalEvent event){
        drupi.callFunction("PlayerPortalEvent", event);
    }


    @EventHandler
    public void onPlayerPreLoginEvent(PlayerPreLoginEvent event){
        drupi.callFunction("PlayerPreLoginEvent", event);
    }


    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){
        drupi.callFunction("PlayerQuitEvent", event);
    }


    @EventHandler
    public void onPlayerRegisterChannelEvent(PlayerRegisterChannelEvent event){
        drupi.callFunction("PlayerRegisterChannelEvent", event);
    }


    @EventHandler
    public void onPlayerResourcePackStatusEvent(PlayerResourcePackStatusEvent event){
        drupi.callFunction("PlayerResourcePackStatusEvent", event);
    }


    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent event){
        drupi.callFunction("PlayerRespawnEvent", event);
    }


    @EventHandler
    public void onPlayerShearEntityEvent(PlayerShearEntityEvent event){
        drupi.callFunction("PlayerShearEntityEvent", event);
    }


    @EventHandler
    public void onPlayerStatisticIncrementEvent(PlayerStatisticIncrementEvent event){
        drupi.callFunction("PlayerStatisticIncrementEvent", event);
    }


    @EventHandler
    public void onPlayerSwapHandItemsEvent(PlayerSwapHandItemsEvent event){
        drupi.callFunction("PlayerSwapHandItemsEvent", event);
    }


    @EventHandler
    public void onPlayerTeleportEvent(PlayerTeleportEvent event){
        drupi.callFunction("PlayerTeleportEvent", event);
    }


    @EventHandler
    public void onPlayerToggleFlightEvent(PlayerToggleFlightEvent event){
        drupi.callFunction("PlayerToggleFlightEvent", event);
    }


    @EventHandler
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event){
        drupi.callFunction("PlayerToggleSneakEvent", event);
    }


    @EventHandler
    public void onPlayerToggleSprintEvent(PlayerToggleSprintEvent event){
        drupi.callFunction("PlayerToggleSprintEvent", event);
    }


    @EventHandler
    public void onPlayerUnleashEntityEvent(PlayerUnleashEntityEvent event){
        drupi.callFunction("PlayerUnleashEntityEvent", event);
    }


    @EventHandler
    public void onPlayerUnregisterChannelEvent(PlayerUnregisterChannelEvent event){
        drupi.callFunction("PlayerUnregisterChannelEvent", event);
    }


    @EventHandler
    public void onPlayerVelocityEvent(PlayerVelocityEvent event){
        drupi.callFunction("PlayerVelocityEvent", event);
    }


    @EventHandler
    public void onBroadcastMessageEvent(BroadcastMessageEvent event){
        drupi.callFunction("BroadcastMessageEvent", event);
    }


    @EventHandler
    public void onMapInitializeEvent(MapInitializeEvent event){
        drupi.callFunction("MapInitializeEvent", event);
    }


    @EventHandler
    public void onPluginDisableEvent(PluginDisableEvent event){
        drupi.callFunction("PluginDisableEvent", event);
    }


    @EventHandler
    public void onPluginEnableEvent(PluginEnableEvent event){
        drupi.callFunction("PluginEnableEvent", event);
    }


    @EventHandler
    public void onRemoteServerCommandEvent(RemoteServerCommandEvent event){
        drupi.callFunction("RemoteServerCommandEvent", event);
    }


    @EventHandler
    public void onServerCommandEvent(ServerCommandEvent event){
        drupi.callFunction("ServerCommandEvent", event);
    }


    @EventHandler
    public void onServerListPingEvent(ServerListPingEvent event){
        drupi.callFunction("ServerListPingEvent", event);
    }


    @EventHandler
    public void onServiceRegisterEvent(ServiceRegisterEvent event){
        drupi.callFunction("ServiceRegisterEvent", event);
    }


    @EventHandler
    public void onServiceUnregisterEvent(ServiceUnregisterEvent event){
        drupi.callFunction("ServiceUnregisterEvent", event);
    }


    @EventHandler
    public void onTabCompleteEvent(TabCompleteEvent event){
        drupi.callFunction("TabCompleteEvent", event);
    }


    @EventHandler
    public void onVehicleBlockCollisionEvent(VehicleBlockCollisionEvent event){
        drupi.callFunction("VehicleBlockCollisionEvent", event);
    }


    @EventHandler
    public void onVehicleCreateEvent(VehicleCreateEvent event){
        drupi.callFunction("VehicleCreateEvent", event);
    }


    @EventHandler
    public void onVehicleDamageEvent(VehicleDamageEvent event){
        drupi.callFunction("VehicleDamageEvent", event);
    }


    @EventHandler
    public void onVehicleDestroyEvent(VehicleDestroyEvent event){
        drupi.callFunction("VehicleDestroyEvent", event);
    }


    @EventHandler
    public void onVehicleEnterEvent(VehicleEnterEvent event){
        drupi.callFunction("VehicleEnterEvent", event);
    }


    @EventHandler
    public void onVehicleEntityCollisionEvent(VehicleEntityCollisionEvent event){
        drupi.callFunction("VehicleEntityCollisionEvent", event);
    }


    @EventHandler
    public void onVehicleExitEvent(VehicleExitEvent event){
        drupi.callFunction("VehicleExitEvent", event);
    }


    @EventHandler
    public void onVehicleMoveEvent(VehicleMoveEvent event){
        drupi.callFunction("VehicleMoveEvent", event);
    }


    @EventHandler
    public void onVehicleUpdateEvent(VehicleUpdateEvent event){
        drupi.callFunction("VehicleUpdateEvent", event);
    }


    @EventHandler
    public void onLightningStrikeEvent(LightningStrikeEvent event){
        drupi.callFunction("LightningStrikeEvent", event);
    }


    @EventHandler
    public void onThunderChangeEvent(ThunderChangeEvent event){
        drupi.callFunction("ThunderChangeEvent", event);
    }


    @EventHandler
    public void onWeatherChangeEvent(WeatherChangeEvent event){
        drupi.callFunction("WeatherChangeEvent", event);
    }


    @EventHandler
    public void onChunkLoadEvent(ChunkLoadEvent event){
        drupi.callFunction("ChunkLoadEvent", event);
    }


    @EventHandler
    public void onChunkPopulateEvent(ChunkPopulateEvent event){
        drupi.callFunction("ChunkPopulateEvent", event);
    }


    @EventHandler
    public void onChunkUnloadEvent(ChunkUnloadEvent event){
        drupi.callFunction("ChunkUnloadEvent", event);
    }


    @EventHandler
    public void onPortalCreateEvent(PortalCreateEvent event){
        drupi.callFunction("PortalCreateEvent", event);
    }


    @EventHandler
    public void onSpawnChangeEvent(SpawnChangeEvent event){
        drupi.callFunction("SpawnChangeEvent", event);
    }


    @EventHandler
    public void onStructureGrowEvent(StructureGrowEvent event){
        drupi.callFunction("StructureGrowEvent", event);
    }


    @EventHandler
    public void onWorldInitEvent(WorldInitEvent event){
        drupi.callFunction("WorldInitEvent", event);
    }


    @EventHandler
    public void onWorldLoadEvent(WorldLoadEvent event){
        drupi.callFunction("WorldLoadEvent", event);
    }


    @EventHandler
    public void onWorldSaveEvent(WorldSaveEvent event){
        drupi.callFunction("WorldSaveEvent", event);
    }


    @EventHandler
    public void onWorldUnloadEvent(WorldUnloadEvent event){
        drupi.callFunction("WorldUnloadEvent", event);
    }


    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event){
        drupi.callFunction("BlockBreakEvent", event);
    }


    @EventHandler
    public void onBlockBurnEvent(BlockBurnEvent event){
        drupi.callFunction("BlockBurnEvent", event);
    }


    @EventHandler
    public void onBlockCanBuildEvent(BlockCanBuildEvent event){
        drupi.callFunction("BlockCanBuildEvent", event);
    }


    @EventHandler
    public void onBlockDamageEvent(BlockDamageEvent event){
        drupi.callFunction("BlockDamageEvent", event);
    }


    @EventHandler
    public void onBlockDispenseEvent(BlockDispenseEvent event){
        drupi.callFunction("BlockDispenseEvent", event);
    }


    @EventHandler
    public void onBlockExpEvent(BlockExpEvent event){
        drupi.callFunction("BlockExpEvent", event);
    }


    @EventHandler
    public void onBlockExplodeEvent(BlockExplodeEvent event){
        drupi.callFunction("BlockExplodeEvent", event);
    }


    @EventHandler
    public void onBlockFadeEvent(BlockFadeEvent event){
        drupi.callFunction("BlockFadeEvent", event);
    }


    @EventHandler
    public void onBlockFormEvent(BlockFormEvent event){
        drupi.callFunction("BlockFormEvent", event);
    }


    @EventHandler
    public void onBlockFromToEvent(BlockFromToEvent event){
        drupi.callFunction("BlockFromToEvent", event);
    }


    @EventHandler
    public void onBlockGrowEvent(BlockGrowEvent event){
        drupi.callFunction("BlockGrowEvent", event);
    }


    @EventHandler
    public void onBlockIgniteEvent(BlockIgniteEvent event){
        drupi.callFunction("BlockIgniteEvent", event);
    }


    @EventHandler
    public void onBlockMultiPlaceEvent(BlockMultiPlaceEvent event){
        drupi.callFunction("BlockMultiPlaceEvent", event);
    }


    @EventHandler
    public void onBlockPhysicsEvent(BlockPhysicsEvent event){
        drupi.callFunction("BlockPhysicsEvent", event);
    }


    @EventHandler
    public void onBlockPistonExtendEvent(BlockPistonExtendEvent event){
        drupi.callFunction("BlockPistonExtendEvent", event);
    }


    @EventHandler
    public void onBlockPistonRetractEvent(BlockPistonRetractEvent event){
        drupi.callFunction("BlockPistonRetractEvent", event);
    }


    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event){
        drupi.callFunction("BlockPlaceEvent", event);
    }


    @EventHandler
    public void onBlockRedstoneEvent(BlockRedstoneEvent event){
        drupi.callFunction("BlockRedstoneEvent", event);
    }


    @EventHandler
    public void onBlockSpreadEvent(BlockSpreadEvent event){
        drupi.callFunction("BlockSpreadEvent", event);
    }


    @EventHandler
    public void onCauldronLevelChangeEvent(CauldronLevelChangeEvent event){
        drupi.callFunction("CauldronLevelChangeEvent", event);
    }


    @EventHandler
    public void onEntityBlockFormEvent(EntityBlockFormEvent event){
        drupi.callFunction("EntityBlockFormEvent", event);
    }


    @EventHandler
    public void onLeavesDecayEvent(LeavesDecayEvent event){
        drupi.callFunction("LeavesDecayEvent", event);
    }


    @EventHandler
    public void onNotePlayEvent(NotePlayEvent event){
        drupi.callFunction("NotePlayEvent", event);
    }


    @EventHandler
    public void onSignChangeEvent(SignChangeEvent event){
        drupi.callFunction("SignChangeEvent", event);
    }


}
