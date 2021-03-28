package net.stacket.drupi.v1_14;

import net.stacket.drupi.shared.api.Drupi;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
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
import org.bukkit.plugin.Plugin;


public class loadEvents implements Listener{
    private Drupi drupi;

    public loadEvents(Drupi drupi) {
        this.drupi = drupi;
        Plugin plugin = Bukkit.getPluginManager().getPlugin("Drupi");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEnchantItemEvent(EnchantItemEvent event){
        drupi.callEvent("EnchantItemEvent", event);
    }


    @EventHandler
    public void onPrepareItemEnchantEvent(PrepareItemEnchantEvent event){
        drupi.callEvent("PrepareItemEnchantEvent", event);
    }


    @EventHandler
    public void onAreaEffectCloudApplyEvent(AreaEffectCloudApplyEvent event){
        drupi.callEvent("AreaEffectCloudApplyEvent", event);
    }


    @EventHandler
    public void onBatToggleSleepEvent(BatToggleSleepEvent event){
        drupi.callEvent("BatToggleSleepEvent", event);
    }


    @EventHandler
    public void onCreatureSpawnEvent(CreatureSpawnEvent event){
        drupi.callEvent("CreatureSpawnEvent", event);
    }


    @EventHandler
    public void onCreeperPowerEvent(CreeperPowerEvent event){
        drupi.callEvent("CreeperPowerEvent", event);
    }


    @EventHandler
    public void onEnderDragonChangePhaseEvent(EnderDragonChangePhaseEvent event){
        drupi.callEvent("EnderDragonChangePhaseEvent", event);
    }


    @EventHandler
    public void onEntityAirChangeEvent(EntityAirChangeEvent event){
        drupi.callEvent("EntityAirChangeEvent", event);
    }


    @EventHandler
    public void onEntityBreakDoorEvent(EntityBreakDoorEvent event){
        drupi.callEvent("EntityBreakDoorEvent", event);
    }


    @EventHandler
    public void onEntityBreedEvent(EntityBreedEvent event){
        drupi.callEvent("EntityBreedEvent", event);
    }


    @EventHandler
    public void onEntityChangeBlockEvent(EntityChangeBlockEvent event){
        drupi.callEvent("EntityChangeBlockEvent", event);
    }


    @EventHandler
    public void onEntityCombustByBlockEvent(EntityCombustByBlockEvent event){
        drupi.callEvent("EntityCombustByBlockEvent", event);
    }


    @EventHandler
    public void onEntityCombustByEntityEvent(EntityCombustByEntityEvent event){
        drupi.callEvent("EntityCombustByEntityEvent", event);
    }


    @EventHandler
    public void onEntityCombustEvent(EntityCombustEvent event){
        drupi.callEvent("EntityCombustEvent", event);
    }




    @EventHandler
    public void onEntityDamageByBlockEvent(EntityDamageByBlockEvent event){
        drupi.callEvent("EntityDamageByBlockEvent", event);
    }


    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event){
        drupi.callEvent("EntityDamageByEntityEvent", event);
    }


    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event){
        drupi.callEvent("EntityDamageEvent", event);
    }


    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event){
        drupi.callEvent("EntityDeathEvent", event);
    }


    @EventHandler
    public void onEntityDropItemEvent(EntityDropItemEvent event){
        drupi.callEvent("EntityDropItemEvent", event);
    }


    @EventHandler
    public void onEntityExplodeEvent(EntityExplodeEvent event){
        drupi.callEvent("EntityExplodeEvent", event);
    }


    @EventHandler
    public void onEntityInteractEvent(EntityInteractEvent event){
        drupi.callEvent("EntityInteractEvent", event);
    }


    @EventHandler
    public void onEntityPickupItemEvent(EntityPickupItemEvent event){
        drupi.callEvent("EntityPickupItemEvent", event);
    }


    @EventHandler
    public void onEntityPlaceEvent(EntityPlaceEvent event){
        drupi.callEvent("EntityPlaceEvent", event);
    }


    @EventHandler
    public void onEntityPortalEnterEvent(EntityPortalEnterEvent event){
        drupi.callEvent("EntityPortalEnterEvent", event);
    }


    @EventHandler
    public void onEntityPortalEvent(EntityPortalEvent event){
        drupi.callEvent("EntityPortalEvent", event);
    }


    @EventHandler
    public void onEntityPortalExitEvent(EntityPortalExitEvent event){
        drupi.callEvent("EntityPortalExitEvent", event);
    }


    @EventHandler
    public void onEntityPotionEffectEvent(EntityPotionEffectEvent event){
        drupi.callEvent("EntityPotionEffectEvent", event);
    }


    @EventHandler
    public void onEntityRegainHealthEvent(EntityRegainHealthEvent event){
        drupi.callEvent("EntityRegainHealthEvent", event);
    }


    @EventHandler
    public void onEntityResurrectEvent(EntityResurrectEvent event){
        drupi.callEvent("EntityResurrectEvent", event);
    }


    @EventHandler
    public void onEntityShootBowEvent(EntityShootBowEvent event){
        drupi.callEvent("EntityShootBowEvent", event);
    }


    @EventHandler
    public void onEntitySpawnEvent(EntitySpawnEvent event){
        drupi.callEvent("EntitySpawnEvent", event);
    }


    @EventHandler
    public void onEntityTameEvent(EntityTameEvent event){
        drupi.callEvent("EntityTameEvent", event);
    }


    @EventHandler
    public void onEntityTargetEvent(EntityTargetEvent event){
        drupi.callEvent("EntityTargetEvent", event);
    }


    @EventHandler
    public void onEntityTargetLivingEntityEvent(EntityTargetLivingEntityEvent event){
        drupi.callEvent("EntityTargetLivingEntityEvent", event);
    }


    @EventHandler
    public void onEntityTeleportEvent(EntityTeleportEvent event){
        drupi.callEvent("EntityTeleportEvent", event);
    }


    @EventHandler
    public void onEntityToggleGlideEvent(EntityToggleGlideEvent event){
        drupi.callEvent("EntityToggleGlideEvent", event);
    }


    @EventHandler
    public void onEntityToggleSwimEvent(EntityToggleSwimEvent event){
        drupi.callEvent("EntityToggleSwimEvent", event);
    }


    @EventHandler
    public void onEntityTransformEvent(EntityTransformEvent event){
        drupi.callEvent("EntityTransformEvent", event);
    }


    @EventHandler
    public void onEntityUnleashEvent(EntityUnleashEvent event){
        drupi.callEvent("EntityUnleashEvent", event);
    }


    @EventHandler
    public void onExpBottleEvent(ExpBottleEvent event){
        drupi.callEvent("ExpBottleEvent", event);
    }


    @EventHandler
    public void onExplosionPrimeEvent(ExplosionPrimeEvent event){
        drupi.callEvent("ExplosionPrimeEvent", event);
    }


    @EventHandler
    public void onFireworkExplodeEvent(FireworkExplodeEvent event){
        drupi.callEvent("FireworkExplodeEvent", event);
    }


    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent event){
        drupi.callEvent("FoodLevelChangeEvent", event);
    }


    @EventHandler
    public void onHorseJumpEvent(HorseJumpEvent event){
        drupi.callEvent("HorseJumpEvent", event);
    }


    @EventHandler
    public void onItemDespawnEvent(ItemDespawnEvent event){
        drupi.callEvent("ItemDespawnEvent", event);
    }


    @EventHandler
    public void onItemMergeEvent(ItemMergeEvent event){
        drupi.callEvent("ItemMergeEvent", event);
    }


    @EventHandler
    public void onItemSpawnEvent(ItemSpawnEvent event){
        drupi.callEvent("ItemSpawnEvent", event);
    }


    @EventHandler
    public void onLingeringPotionSplashEvent(LingeringPotionSplashEvent event){
        drupi.callEvent("LingeringPotionSplashEvent", event);
    }


    @EventHandler
    public void onPigZapEvent(PigZapEvent event){
        drupi.callEvent("PigZapEvent", event);
    }


    @EventHandler
    public void onPigZombieAngerEvent(PigZombieAngerEvent event){
        drupi.callEvent("PigZombieAngerEvent", event);
    }


    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event){
        drupi.callEvent("PlayerDeathEvent", event);
    }


    @EventHandler
    public void onPlayerLeashEntityEvent(PlayerLeashEntityEvent event){
        drupi.callEvent("PlayerLeashEntityEvent", event);
    }


    @EventHandler
    public void onPotionSplashEvent(PotionSplashEvent event){
        drupi.callEvent("PotionSplashEvent", event);
    }


    @EventHandler
    public void onProjectileHitEvent(ProjectileHitEvent event){
        drupi.callEvent("ProjectileHitEvent", event);
    }


    @EventHandler
    public void onProjectileLaunchEvent(ProjectileLaunchEvent event){
        drupi.callEvent("ProjectileLaunchEvent", event);
    }


    @EventHandler
    public void onSheepDyeWoolEvent(SheepDyeWoolEvent event){
        drupi.callEvent("SheepDyeWoolEvent", event);
    }


    @EventHandler
    public void onSheepRegrowWoolEvent(SheepRegrowWoolEvent event){
        drupi.callEvent("SheepRegrowWoolEvent", event);
    }


    @EventHandler
    public void onSlimeSplitEvent(SlimeSplitEvent event){
        drupi.callEvent("SlimeSplitEvent", event);
    }


    @EventHandler
    public void onVillagerAcquireTradeEvent(VillagerAcquireTradeEvent event){
        drupi.callEvent("VillagerAcquireTradeEvent", event);
    }


    @EventHandler
    public void onVillagerReplenishTradeEvent(VillagerReplenishTradeEvent event){
        drupi.callEvent("VillagerReplenishTradeEvent", event);
    }


    @EventHandler
    public void onHangingBreakByEntityEvent(HangingBreakByEntityEvent event){
        drupi.callEvent("HangingBreakByEntityEvent", event);
    }


    @EventHandler
    public void onHangingBreakEvent(HangingBreakEvent event){
        drupi.callEvent("HangingBreakEvent", event);
    }


    @EventHandler
    public void onHangingPlaceEvent(HangingPlaceEvent event){
        drupi.callEvent("HangingPlaceEvent", event);
    }


    @EventHandler
    public void onBrewEvent(BrewEvent event){
        drupi.callEvent("BrewEvent", event);
    }


    @EventHandler
    public void onBrewingStandFuelEvent(BrewingStandFuelEvent event){
        drupi.callEvent("BrewingStandFuelEvent", event);
    }


    @EventHandler
    public void onCraftItemEvent(CraftItemEvent event){
        drupi.callEvent("CraftItemEvent", event);
    }


    @EventHandler
    public void onFurnaceBurnEvent(FurnaceBurnEvent event){
        drupi.callEvent("FurnaceBurnEvent", event);
    }


    @EventHandler
    public void onFurnaceExtractEvent(FurnaceExtractEvent event){
        drupi.callEvent("FurnaceExtractEvent", event);
    }


    @EventHandler
    public void onFurnaceSmeltEvent(FurnaceSmeltEvent event){
        drupi.callEvent("FurnaceSmeltEvent", event);
    }


    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event){
        drupi.callEvent("InventoryClickEvent", event);
    }


    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event){
        drupi.callEvent("InventoryCloseEvent", event);
    }


    @EventHandler
    public void onInventoryCreativeEvent(InventoryCreativeEvent event){
        drupi.callEvent("InventoryCreativeEvent", event);
    }


    @EventHandler
    public void onInventoryDragEvent(InventoryDragEvent event){
        drupi.callEvent("InventoryDragEvent", event);
    }


    @EventHandler
    public void onInventoryInteractEvent(InventoryInteractEvent event){
        drupi.callEvent("InventoryInteractEvent", event);
    }


    @EventHandler
    public void onInventoryMoveItemEvent(InventoryMoveItemEvent event){
        drupi.callEvent("InventoryMoveItemEvent", event);
    }


    @EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent event){
        drupi.callEvent("InventoryOpenEvent", event);
    }


    @EventHandler
    public void onInventoryPickupItemEvent(InventoryPickupItemEvent event){
        drupi.callEvent("InventoryPickupItemEvent", event);
    }


    @EventHandler
    public void onPrepareAnvilEvent(PrepareAnvilEvent event){
        drupi.callEvent("PrepareAnvilEvent", event);
    }


    @EventHandler
    public void onPrepareItemCraftEvent(PrepareItemCraftEvent event){
        drupi.callEvent("PrepareItemCraftEvent", event);
    }


    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event){
        drupi.callEvent("AsyncPlayerChatEvent", event);
    }


    @EventHandler
    public void onAsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent event){
        drupi.callEvent("AsyncPlayerPreLoginEvent", event);
    }



    @EventHandler
    public void onPlayerAdvancementDoneEvent(PlayerAdvancementDoneEvent event){
        drupi.callEvent("PlayerAdvancementDoneEvent", event);
    }


    @EventHandler
    public void onPlayerAnimationEvent(PlayerAnimationEvent event){
        drupi.callEvent("PlayerAnimationEvent", event);
    }


    @EventHandler
    public void onPlayerArmorStandManipulateEvent(PlayerArmorStandManipulateEvent event){
        drupi.callEvent("PlayerArmorStandManipulateEvent", event);
    }


    @EventHandler
    public void onPlayerBedEnterEvent(PlayerBedEnterEvent event){
        drupi.callEvent("PlayerBedEnterEvent", event);
    }


    @EventHandler
    public void onPlayerBedLeaveEvent(PlayerBedLeaveEvent event){
        drupi.callEvent("PlayerBedLeaveEvent", event);
    }


    @EventHandler
    public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent event){
        drupi.callEvent("PlayerBucketEmptyEvent", event);
    }


    @EventHandler
    public void onPlayerBucketFillEvent(PlayerBucketFillEvent event){
        drupi.callEvent("PlayerBucketFillEvent", event);
    }


    @EventHandler
    public void onPlayerChangedMainHandEvent(PlayerChangedMainHandEvent event){
        drupi.callEvent("PlayerChangedMainHandEvent", event);
    }


    @EventHandler
    public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent event){
        drupi.callEvent("PlayerChangedWorldEvent", event);
    }


    @EventHandler
    public void onPlayerChannelEvent(PlayerChannelEvent event){
        drupi.callEvent("PlayerChannelEvent", event);
    }


    @EventHandler
    public void onPlayerChatEvent(PlayerChatEvent event){
        drupi.callEvent("PlayerChatEvent", event);
    }


    @EventHandler
    public void onPlayerChatTabCompleteEvent(PlayerChatTabCompleteEvent event){
        drupi.callEvent("PlayerChatTabCompleteEvent", event);
    }


    @EventHandler
    public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event){
        drupi.callEvent("PlayerCommandPreprocessEvent", event);
    }


    @EventHandler
    public void onPlayerCommandSendEvent(PlayerCommandSendEvent event){
        drupi.callEvent("PlayerCommandSendEvent", event);
    }


    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent event){
        drupi.callEvent("PlayerDropItemEvent", event);
    }


    @EventHandler
    public void onPlayerEditBookEvent(PlayerEditBookEvent event){
        drupi.callEvent("PlayerEditBookEvent", event);
    }


    @EventHandler
    public void onPlayerEggThrowEvent(PlayerEggThrowEvent event){
        drupi.callEvent("PlayerEggThrowEvent", event);
    }


    @EventHandler
    public void onPlayerExpChangeEvent(PlayerExpChangeEvent event){
        drupi.callEvent("PlayerExpChangeEvent", event);
    }


    @EventHandler
    public void onPlayerFishEvent(PlayerFishEvent event){
        drupi.callEvent("PlayerFishEvent", event);
    }


    @EventHandler
    public void onPlayerGameModeChangeEvent(PlayerGameModeChangeEvent event){
        drupi.callEvent("PlayerGameModeChangeEvent", event);
    }


    @EventHandler
    public void onPlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent event){
        drupi.callEvent("PlayerInteractAtEntityEvent", event);
    }


    @EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event){
        drupi.callEvent("PlayerInteractEntityEvent", event);
    }


    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event){
        drupi.callEvent("PlayerInteractEvent", event);
    }


    @EventHandler
    public void onPlayerItemBreakEvent(PlayerItemBreakEvent event){
        drupi.callEvent("PlayerItemBreakEvent", event);
    }


    @EventHandler
    public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent event){
        drupi.callEvent("PlayerItemConsumeEvent", event);
    }


    @EventHandler
    public void onPlayerItemDamageEvent(PlayerItemDamageEvent event){
        drupi.callEvent("PlayerItemDamageEvent", event);
    }


    @EventHandler
    public void onPlayerItemHeldEvent(PlayerItemHeldEvent event){
        drupi.callEvent("PlayerItemHeldEvent", event);
    }


    @EventHandler
    public void onPlayerItemMendEvent(PlayerItemMendEvent event){
        drupi.callEvent("PlayerItemMendEvent", event);
    }


    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        drupi.callEvent("PlayerJoinEvent", event);
    }


    @EventHandler
    public void onPlayerKickEvent(PlayerKickEvent event){
        drupi.callEvent("PlayerKickEvent", event);
    }


    @EventHandler
    public void onPlayerLevelChangeEvent(PlayerLevelChangeEvent event){
        drupi.callEvent("PlayerLevelChangeEvent", event);
    }


    @EventHandler
    public void onPlayerLocaleChangeEvent(PlayerLocaleChangeEvent event){
        drupi.callEvent("PlayerLocaleChangeEvent", event);
    }


    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent event){
        drupi.callEvent("PlayerLoginEvent", event);
    }


    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event){
        drupi.callEvent("PlayerMoveEvent", event);
    }


    @EventHandler
    public void onPlayerPickupArrowEvent(PlayerPickupArrowEvent event){
        drupi.callEvent("PlayerPickupArrowEvent", event);
    }


    @EventHandler
    public void onPlayerPickupItemEvent(PlayerPickupItemEvent event){
        drupi.callEvent("PlayerPickupItemEvent", event);
    }


    @EventHandler
    public void onPlayerPortalEvent(PlayerPortalEvent event){
        drupi.callEvent("PlayerPortalEvent", event);
    }


    @EventHandler
    public void onPlayerPreLoginEvent(PlayerPreLoginEvent event){
        drupi.callEvent("PlayerPreLoginEvent", event);
    }


    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){
        drupi.callEvent("PlayerQuitEvent", event);
    }


    @EventHandler
    public void onPlayerRecipeDiscoverEvent(PlayerRecipeDiscoverEvent event){
        drupi.callEvent("PlayerRecipeDiscoverEvent", event);
    }


    @EventHandler
    public void onPlayerRegisterChannelEvent(PlayerRegisterChannelEvent event){
        drupi.callEvent("PlayerRegisterChannelEvent", event);
    }


    @EventHandler
    public void onPlayerResourcePackStatusEvent(PlayerResourcePackStatusEvent event){
        drupi.callEvent("PlayerResourcePackStatusEvent", event);
    }


    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent event){
        drupi.callEvent("PlayerRespawnEvent", event);
    }


    @EventHandler
    public void onPlayerRiptideEvent(PlayerRiptideEvent event){
        drupi.callEvent("PlayerRiptideEvent", event);
    }


    @EventHandler
    public void onPlayerShearEntityEvent(PlayerShearEntityEvent event){
        drupi.callEvent("PlayerShearEntityEvent", event);
    }


    @EventHandler
    public void onPlayerStatisticIncrementEvent(PlayerStatisticIncrementEvent event){
        drupi.callEvent("PlayerStatisticIncrementEvent", event);
    }


    @EventHandler
    public void onPlayerSwapHandItemsEvent(PlayerSwapHandItemsEvent event){
        drupi.callEvent("PlayerSwapHandItemsEvent", event);
    }


    @EventHandler
    public void onPlayerTeleportEvent(PlayerTeleportEvent event){
        drupi.callEvent("PlayerTeleportEvent", event);
    }


    @EventHandler
    public void onPlayerToggleFlightEvent(PlayerToggleFlightEvent event){
        drupi.callEvent("PlayerToggleFlightEvent", event);
    }


    @EventHandler
    public void onPlayerToggleSneakEvent(PlayerToggleSneakEvent event){
        drupi.callEvent("PlayerToggleSneakEvent", event);
    }


    @EventHandler
    public void onPlayerToggleSprintEvent(PlayerToggleSprintEvent event){
        drupi.callEvent("PlayerToggleSprintEvent", event);
    }


    @EventHandler
    public void onPlayerUnleashEntityEvent(PlayerUnleashEntityEvent event){
        drupi.callEvent("PlayerUnleashEntityEvent", event);
    }


    @EventHandler
    public void onPlayerUnregisterChannelEvent(PlayerUnregisterChannelEvent event){
        drupi.callEvent("PlayerUnregisterChannelEvent", event);
    }


    @EventHandler
    public void onPlayerVelocityEvent(PlayerVelocityEvent event){
        drupi.callEvent("PlayerVelocityEvent", event);
    }


    @EventHandler
    public void onBroadcastMessageEvent(BroadcastMessageEvent event){
        drupi.callEvent("BroadcastMessageEvent", event);
    }


    @EventHandler
    public void onMapInitializeEvent(MapInitializeEvent event){
        drupi.callEvent("MapInitializeEvent", event);
    }


    @EventHandler
    public void onPluginDisableEvent(PluginDisableEvent event){
        drupi.callEvent("PluginDisableEvent", event);
    }


    @EventHandler
    public void onPluginEnableEvent(PluginEnableEvent event){
        drupi.callEvent("PluginEnableEvent", event);
    }


    @EventHandler
    public void onRemoteServerCommandEvent(RemoteServerCommandEvent event){
        drupi.callEvent("RemoteServerCommandEvent", event);
    }


    @EventHandler
    public void onServerCommandEvent(ServerCommandEvent event){
        drupi.callEvent("ServerCommandEvent", event);
    }


    @EventHandler
    public void onServerListPingEvent(ServerListPingEvent event){
        drupi.callEvent("ServerListPingEvent", event);
    }


    @EventHandler
    public void onServiceRegisterEvent(ServiceRegisterEvent event){
        drupi.callEvent("ServiceRegisterEvent", event);
    }


    @EventHandler
    public void onServiceUnregisterEvent(ServiceUnregisterEvent event){
        drupi.callEvent("ServiceUnregisterEvent", event);
    }


    @EventHandler
    public void onTabCompleteEvent(TabCompleteEvent event){
        drupi.callEvent("TabCompleteEvent", event);
    }


    @EventHandler
    public void onVehicleBlockCollisionEvent(VehicleBlockCollisionEvent event){
        drupi.callEvent("VehicleBlockCollisionEvent", event);
    }


    @EventHandler
    public void onVehicleCreateEvent(VehicleCreateEvent event){
        drupi.callEvent("VehicleCreateEvent", event);
    }


    @EventHandler
    public void onVehicleDamageEvent(VehicleDamageEvent event){
        drupi.callEvent("VehicleDamageEvent", event);
    }


    @EventHandler
    public void onVehicleDestroyEvent(VehicleDestroyEvent event){
        drupi.callEvent("VehicleDestroyEvent", event);
    }


    @EventHandler
    public void onVehicleEnterEvent(VehicleEnterEvent event){
        drupi.callEvent("VehicleEnterEvent", event);
    }


    @EventHandler
    public void onVehicleEntityCollisionEvent(VehicleEntityCollisionEvent event){
        drupi.callEvent("VehicleEntityCollisionEvent", event);
    }


    @EventHandler
    public void onVehicleExitEvent(VehicleExitEvent event){
        drupi.callEvent("VehicleExitEvent", event);
    }


    @EventHandler
    public void onVehicleMoveEvent(VehicleMoveEvent event){
        drupi.callEvent("VehicleMoveEvent", event);
    }


    @EventHandler
    public void onVehicleUpdateEvent(VehicleUpdateEvent event){
        drupi.callEvent("VehicleUpdateEvent", event);
    }


    @EventHandler
    public void onLightningStrikeEvent(LightningStrikeEvent event){
        drupi.callEvent("LightningStrikeEvent", event);
    }


    @EventHandler
    public void onThunderChangeEvent(ThunderChangeEvent event){
        drupi.callEvent("ThunderChangeEvent", event);
    }


    @EventHandler
    public void onWeatherChangeEvent(WeatherChangeEvent event){
        drupi.callEvent("WeatherChangeEvent", event);
    }


    @EventHandler
    public void onChunkLoadEvent(ChunkLoadEvent event){
        drupi.callEvent("ChunkLoadEvent", event);
    }


    @EventHandler
    public void onChunkPopulateEvent(ChunkPopulateEvent event){
        drupi.callEvent("ChunkPopulateEvent", event);
    }


    @EventHandler
    public void onChunkUnloadEvent(ChunkUnloadEvent event){
        drupi.callEvent("ChunkUnloadEvent", event);
    }


    @EventHandler
    public void onPortalCreateEvent(PortalCreateEvent event){
        drupi.callEvent("PortalCreateEvent", event);
    }


    @EventHandler
    public void onSpawnChangeEvent(SpawnChangeEvent event){
        drupi.callEvent("SpawnChangeEvent", event);
    }


    @EventHandler
    public void onStructureGrowEvent(StructureGrowEvent event){
        drupi.callEvent("StructureGrowEvent", event);
    }


    @EventHandler
    public void onWorldInitEvent(WorldInitEvent event){
        drupi.callEvent("WorldInitEvent", event);
    }


    @EventHandler
    public void onWorldLoadEvent(WorldLoadEvent event){
        drupi.callEvent("WorldLoadEvent", event);
    }


    @EventHandler
    public void onWorldSaveEvent(WorldSaveEvent event){
        drupi.callEvent("WorldSaveEvent", event);
    }


    @EventHandler
    public void onWorldUnloadEvent(WorldUnloadEvent event){
        drupi.callEvent("WorldUnloadEvent", event);
    }


    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event){
        drupi.callEvent("BlockBreakEvent", event);
    }


    @EventHandler
    public void onBlockBurnEvent(BlockBurnEvent event){
        drupi.callEvent("BlockBurnEvent", event);
    }


    @EventHandler
    public void onBlockCanBuildEvent(BlockCanBuildEvent event){
        drupi.callEvent("BlockCanBuildEvent", event);
    }


    @EventHandler
    public void onBlockCookEvent(BlockCookEvent event){
        drupi.callEvent("BlockCookEvent", event);
    }


    @EventHandler
    public void onBlockDamageEvent(BlockDamageEvent event){
        drupi.callEvent("BlockDamageEvent", event);
    }


    @EventHandler
    public void onBlockDispenseArmorEvent(BlockDispenseArmorEvent event){
        drupi.callEvent("BlockDispenseArmorEvent", event);
    }


    @EventHandler
    public void onBlockDispenseEvent(BlockDispenseEvent event){
        drupi.callEvent("BlockDispenseEvent", event);
    }


    @EventHandler
    public void onBlockDropItemEvent(BlockDropItemEvent event){
        drupi.callEvent("BlockDropItemEvent", event);
    }


    @EventHandler
    public void onBlockExpEvent(BlockExpEvent event){
        drupi.callEvent("BlockExpEvent", event);
    }


    @EventHandler
    public void onBlockExplodeEvent(BlockExplodeEvent event){
        drupi.callEvent("BlockExplodeEvent", event);
    }


    @EventHandler
    public void onBlockFadeEvent(BlockFadeEvent event){
        drupi.callEvent("BlockFadeEvent", event);
    }


    @EventHandler
    public void onBlockFertilizeEvent(BlockFertilizeEvent event){
        drupi.callEvent("BlockFertilizeEvent", event);
    }


    @EventHandler
    public void onBlockFormEvent(BlockFormEvent event){
        drupi.callEvent("BlockFormEvent", event);
    }


    @EventHandler
    public void onBlockFromToEvent(BlockFromToEvent event){
        drupi.callEvent("BlockFromToEvent", event);
    }


    @EventHandler
    public void onBlockGrowEvent(BlockGrowEvent event){
        drupi.callEvent("BlockGrowEvent", event);
    }


    @EventHandler
    public void onBlockIgniteEvent(BlockIgniteEvent event){
        drupi.callEvent("BlockIgniteEvent", event);
    }


    @EventHandler
    public void onBlockMultiPlaceEvent(BlockMultiPlaceEvent event){
        drupi.callEvent("BlockMultiPlaceEvent", event);
    }


    @EventHandler
    public void onBlockPhysicsEvent(BlockPhysicsEvent event){
        drupi.callEvent("BlockPhysicsEvent", event);
    }


    @EventHandler
    public void onBlockPistonExtendEvent(BlockPistonExtendEvent event){
        drupi.callEvent("BlockPistonExtendEvent", event);
    }


    @EventHandler
    public void onBlockPistonRetractEvent(BlockPistonRetractEvent event){
        drupi.callEvent("BlockPistonRetractEvent", event);
    }


    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event){
        drupi.callEvent("BlockPlaceEvent", event);
    }


    @EventHandler
    public void onBlockRedstoneEvent(BlockRedstoneEvent event){
        drupi.callEvent("BlockRedstoneEvent", event);
    }


    @EventHandler
    public void onBlockSpreadEvent(BlockSpreadEvent event){
        drupi.callEvent("BlockSpreadEvent", event);
    }


    @EventHandler
    public void onCauldronLevelChangeEvent(CauldronLevelChangeEvent event){
        drupi.callEvent("CauldronLevelChangeEvent", event);
    }


    @EventHandler
    public void onEntityBlockFormEvent(EntityBlockFormEvent event){
        drupi.callEvent("EntityBlockFormEvent", event);
    }


    @EventHandler
    public void onFluidLevelChangeEvent(FluidLevelChangeEvent event){
        drupi.callEvent("FluidLevelChangeEvent", event);
    }


    @EventHandler
    public void onLeavesDecayEvent(LeavesDecayEvent event){
        drupi.callEvent("LeavesDecayEvent", event);
    }


    @EventHandler
    public void onMoistureChangeEvent(MoistureChangeEvent event){
        drupi.callEvent("MoistureChangeEvent", event);
    }


    @EventHandler
    public void onNotePlayEvent(NotePlayEvent event){
        drupi.callEvent("NotePlayEvent", event);
    }


    @EventHandler
    public void onSignChangeEvent(SignChangeEvent event){
        drupi.callEvent("SignChangeEvent", event);
    }


    @EventHandler
    public void onSpongeAbsorbEvent(SpongeAbsorbEvent event){
        drupi.callEvent("SpongeAbsorbEvent", event);
    }


}
