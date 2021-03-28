package net.stacket.drupi.v1_16;

import net.stacket.drupi.shared.api.Drupi;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.enchantment.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.hanging.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.raid.*;
import org.bukkit.event.server.*;
import org.bukkit.event.vehicle.*;
import org.bukkit.event.weather.*;
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
    public void ChunkLoadEvent(ChunkLoadEvent event){
        drupi.callEvent("ChunkLoadEvent", event);
    }


    @EventHandler
    public void ChunkPopulateEvent(ChunkPopulateEvent event){
        drupi.callEvent("ChunkPopulateEvent", event);
    }


    @EventHandler
    public void ChunkUnloadEvent(ChunkUnloadEvent event){
        drupi.callEvent("ChunkUnloadEvent", event);
    }


    @EventHandler
    public void PortalCreateEvent(PortalCreateEvent event){
        drupi.callEvent("PortalCreateEvent", event);
    }


    @EventHandler
    public void SpawnChangeEvent(SpawnChangeEvent event){
        drupi.callEvent("SpawnChangeEvent", event);
    }


    @EventHandler
    public void StructureGrowEvent(StructureGrowEvent event){
        drupi.callEvent("StructureGrowEvent", event);
    }


    @EventHandler
    public void WorldInitEvent(WorldInitEvent event){
        drupi.callEvent("WorldInitEvent", event);
    }


    @EventHandler
    public void WorldLoadEvent(WorldLoadEvent event){
        drupi.callEvent("WorldLoadEvent", event);
    }


    @EventHandler
    public void WorldSaveEvent(WorldSaveEvent event){
        drupi.callEvent("WorldSaveEvent", event);
    }


    @EventHandler
    public void WorldUnloadEvent(WorldUnloadEvent event){
        drupi.callEvent("WorldUnloadEvent", event);
    }

    @EventHandler
    public void LightningStrikeEvent(LightningStrikeEvent event){
        drupi.callEvent("LightningStrikeEvent", event);
    }


    @EventHandler
    public void ThunderChangeEvent(ThunderChangeEvent event){
        drupi.callEvent("ThunderChangeEvent", event);
    }


    @EventHandler
    public void WeatherChangeEvent(WeatherChangeEvent event){
        drupi.callEvent("WeatherChangeEvent", event);
    }


    @EventHandler
    public void VehicleBlockCollisionEvent(VehicleBlockCollisionEvent event){
        drupi.callEvent("VehicleBlockCollisionEvent", event);
    }



    @EventHandler
    public void VehicleCreateEvent(VehicleCreateEvent event){
        drupi.callEvent("VehicleCreateEvent", event);
    }


    @EventHandler
    public void VehicleDamageEvent(VehicleDamageEvent event){
        drupi.callEvent("VehicleDamageEvent", event);
    }


    @EventHandler
    public void VehicleDestroyEvent(VehicleDestroyEvent event){
        drupi.callEvent("VehicleDestroyEvent", event);
    }


    @EventHandler
    public void VehicleEnterEvent(VehicleEnterEvent event){
        drupi.callEvent("VehicleEnterEvent", event);
    }


    @EventHandler
    public void VehicleEntityCollisionEvent(VehicleEntityCollisionEvent event){
        drupi.callEvent("VehicleEntityCollisionEvent", event);
    }



    @EventHandler
    public void VehicleExitEvent(VehicleExitEvent event){
        drupi.callEvent("VehicleExitEvent", event);
    }


    @EventHandler
    public void VehicleMoveEvent(VehicleMoveEvent event){
        drupi.callEvent("VehicleMoveEvent", event);
    }


    @EventHandler
    public void VehicleUpdateEvent(VehicleUpdateEvent event){
        drupi.callEvent("VehicleUpdateEvent", event);
    }

    @EventHandler
    public void BroadcastMessageEvent(BroadcastMessageEvent event){
        drupi.callEvent("BroadcastMessageEvent", event);
    }


    @EventHandler
    public void MapInitializeEvent(MapInitializeEvent event){
        drupi.callEvent("MapInitializeEvent", event);
    }


    @EventHandler
    public void PluginDisableEvent(PluginDisableEvent event){
        drupi.callEvent("PluginDisableEvent", event);
    }


    @EventHandler
    public void PluginEnableEvent(PluginEnableEvent event){
        drupi.callEvent("PluginEnableEvent", event);
    }



    @EventHandler
    public void RemoteServerCommandEvent(RemoteServerCommandEvent event){
        drupi.callEvent("RemoteServerCommandEvent", event);
    }


    @EventHandler
    public void ServerCommandEvent(ServerCommandEvent event){
        drupi.callEvent("ServerCommandEvent", event);
    }



    @EventHandler
    public void ServerListPingEvent(ServerListPingEvent event){
        drupi.callEvent("ServerListPingEvent", event);
    }


    @EventHandler
    public void ServerLoadEvent(ServerLoadEvent event){
        drupi.callEvent("ServerLoadEvent", event);
    }



    @EventHandler
    public void ServiceRegisterEvent(ServiceRegisterEvent event){
        drupi.callEvent("ServiceRegisterEvent", event);
    }


    @EventHandler
    public void ServiceUnregisterEvent(ServiceUnregisterEvent event){
        drupi.callEvent("ServiceUnregisterEvent", event);
    }


    @EventHandler
    public void TabCompleteEvent(TabCompleteEvent event){
        drupi.callEvent("TabCompleteEvent", event);
    }


    @EventHandler
    public void RaidFinishEvent(RaidFinishEvent event){
        drupi.callEvent("RaidFinishEvent", event);
    }


    @EventHandler
    public void RaidSpawnWaveEvent(RaidSpawnWaveEvent event){
        drupi.callEvent("RaidSpawnWaveEvent", event);
    }


    @EventHandler
    public void RaidStopEvent(RaidStopEvent event){
        drupi.callEvent("RaidStopEvent", event);
    }


    @EventHandler
    public void RaidTriggerEvent(RaidTriggerEvent event){
        drupi.callEvent("RaidTriggerEvent", event);
    }

    @EventHandler
    public void AsyncPlayerChatEvent(AsyncPlayerChatEvent event){
        drupi.callEvent("AsyncPlayerChatEvent", event);
    }


    @EventHandler
    public void AsyncPlayerPreLoginEvent(AsyncPlayerPreLoginEvent event){
        drupi.callEvent("AsyncPlayerPreLoginEvent", event);
    }


    @EventHandler
    public void PlayerAdvancementDoneEvent(PlayerAdvancementDoneEvent event){
        drupi.callEvent("PlayerAdvancementDoneEvent", event);
    }


    @EventHandler
    public void PlayerAnimationEvent(PlayerAnimationEvent event){
        drupi.callEvent("PlayerAnimationEvent", event);
    }


    @EventHandler
    public void PlayerArmorStandManipulateEvent(PlayerArmorStandManipulateEvent event){
        drupi.callEvent("PlayerArmorStandManipulateEvent", event);
    }


    @EventHandler
    public void PlayerBedEnterEvent(PlayerBedEnterEvent event){
        drupi.callEvent("PlayerBedEnterEvent", event);
    }


    @EventHandler
    public void PlayerBedLeaveEvent(PlayerBedLeaveEvent event){
        drupi.callEvent("PlayerBedLeaveEvent", event);
    }


    @EventHandler
    public void PlayerBucketEmptyEvent(PlayerBucketEmptyEvent event){
        drupi.callEvent("PlayerBucketEmptyEvent", event);
    }



    @EventHandler
    public void PlayerBucketFillEvent(PlayerBucketFillEvent event){
        drupi.callEvent("PlayerBucketFillEvent", event);
    }


    @EventHandler
    public void PlayerChangedMainHandEvent(PlayerChangedMainHandEvent event){
        drupi.callEvent("PlayerChangedMainHandEvent", event);
    }


    @EventHandler
    public void PlayerChangedWorldEvent(PlayerChangedWorldEvent event){
        drupi.callEvent("PlayerChangedWorldEvent", event);
    }


    @EventHandler
    public void PlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event){
        drupi.callEvent("PlayerCommandPreprocessEvent", event);
    }


    @EventHandler
    public void PlayerCommandSendEvent(PlayerCommandSendEvent event){
        drupi.callEvent("PlayerCommandSendEvent", event);
    }


    @EventHandler
    public void PlayerDropItemEvent(PlayerDropItemEvent event){
        drupi.callEvent("PlayerDropItemEvent", event);
    }


    @EventHandler
    public void PlayerEditBookEvent(PlayerEditBookEvent event){
        drupi.callEvent("PlayerEditBookEvent", event);
    }


    @EventHandler
    public void PlayerEggThrowEvent(PlayerEggThrowEvent event){
        drupi.callEvent("PlayerEggThrowEvent", event);
    }



    @EventHandler
    public void PlayerExpChangeEvent(PlayerExpChangeEvent event){
        drupi.callEvent("PlayerExpChangeEvent", event);
    }


    @EventHandler
    public void PlayerFishEvent(PlayerFishEvent event){
        drupi.callEvent("PlayerFishEvent", event);
    }


    @EventHandler
    public void PlayerGameModeChangeEvent(PlayerGameModeChangeEvent event){
        drupi.callEvent("PlayerGameModeChangeEvent", event);
    }


    @EventHandler
    public void PlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent event){
        drupi.callEvent("PlayerInteractAtEntityEvent", event);
    }



    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event){
        drupi.callEvent("PlayerInteractEvent", event);
    }


    @EventHandler
    public void PlayerItemBreakEvent(PlayerItemBreakEvent event){
        drupi.callEvent("PlayerItemBreakEvent", event);
    }


    @EventHandler
    public void PlayerItemConsumeEvent(PlayerItemConsumeEvent event){
        drupi.callEvent("PlayerItemConsumeEvent", event);
    }


    @EventHandler
    public void PlayerItemDamageEvent(PlayerItemDamageEvent event){
        drupi.callEvent("PlayerItemDamageEvent", event);
    }


    @EventHandler
    public void PlayerItemHeldEvent(PlayerItemHeldEvent event){
        drupi.callEvent("PlayerItemHeldEvent", event);
    }


    @EventHandler
    public void PlayerItemMendEvent(PlayerItemMendEvent event){
        drupi.callEvent("PlayerItemMendEvent", event);
    }


    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event){
        drupi.callEvent("PlayerJoinEvent", event);
    }


    @EventHandler
    public void PlayerKickEvent(PlayerKickEvent event){
        drupi.callEvent("PlayerKickEvent", event);
    }


    @EventHandler
    public void PlayerLevelChangeEvent(PlayerLevelChangeEvent event){
        drupi.callEvent("PlayerLevelChangeEvent", event);
    }


    @EventHandler
    public void PlayerLocaleChangeEvent(PlayerLocaleChangeEvent event){
        drupi.callEvent("PlayerLocaleChangeEvent", event);
    }


    @EventHandler
    public void PlayerLoginEvent(PlayerLoginEvent event){
        drupi.callEvent("PlayerLoginEvent", event);
    }


    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event){
        drupi.callEvent("PlayerMoveEvent", event);
    }


    @EventHandler
    public void PlayerPickupArrowEvent(PlayerPickupArrowEvent event){
        drupi.callEvent("PlayerPickupArrowEvent", event);
    }




    @EventHandler
    public void PlayerPortalEvent(PlayerPortalEvent event){
        drupi.callEvent("PlayerPortalEvent", event);
    }



    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent event){
        drupi.callEvent("PlayerQuitEvent", event);
    }


    @EventHandler
    public void PlayerRecipeDiscoverEvent(PlayerRecipeDiscoverEvent event){
        drupi.callEvent("PlayerRecipeDiscoverEvent", event);
    }


    @EventHandler
    public void PlayerRegisterChannelEvent(PlayerRegisterChannelEvent event){
        drupi.callEvent("PlayerRegisterChannelEvent", event);
    }


    @EventHandler
    public void PlayerResourcePackStatusEvent(PlayerResourcePackStatusEvent event){
        drupi.callEvent("PlayerResourcePackStatusEvent", event);
    }


    @EventHandler
    public void PlayerRespawnEvent(PlayerRespawnEvent event){
        drupi.callEvent("PlayerRespawnEvent", event);
    }


    @EventHandler
    public void PlayerRiptideEvent(PlayerRiptideEvent event){
        drupi.callEvent("PlayerRiptideEvent", event);
    }


    @EventHandler
    public void PlayerShearEntityEvent(PlayerShearEntityEvent event){
        drupi.callEvent("PlayerShearEntityEvent", event);
    }


    @EventHandler
    public void PlayerStatisticIncrementEvent(PlayerStatisticIncrementEvent event){
        drupi.callEvent("PlayerStatisticIncrementEvent", event);
    }


    @EventHandler
    public void PlayerSwapHandItemsEvent(PlayerSwapHandItemsEvent event){
        drupi.callEvent("PlayerSwapHandItemsEvent", event);
    }


    @EventHandler
    public void PlayerTakeLecternBookEvent(PlayerTakeLecternBookEvent event){
        drupi.callEvent("PlayerTakeLecternBookEvent", event);
    }


    @EventHandler
    public void PlayerTeleportEvent(PlayerTeleportEvent event){
        drupi.callEvent("PlayerTeleportEvent", event);
    }


    @EventHandler
    public void PlayerToggleFlightEvent(PlayerToggleFlightEvent event){
        drupi.callEvent("PlayerToggleFlightEvent", event);
    }


    @EventHandler
    public void PlayerToggleSneakEvent(PlayerToggleSneakEvent event){
        drupi.callEvent("PlayerToggleSneakEvent", event);
    }


    @EventHandler
    public void PlayerToggleSprintEvent(PlayerToggleSprintEvent event){
        drupi.callEvent("PlayerToggleSprintEvent", event);
    }


    @EventHandler
    public void PlayerUnleashEntityEvent(PlayerUnleashEntityEvent event){
        drupi.callEvent("PlayerUnleashEntityEvent", event);
    }


    @EventHandler
    public void PlayerUnregisterChannelEvent(PlayerUnregisterChannelEvent event){
        drupi.callEvent("PlayerUnregisterChannelEvent", event);
    }


    @EventHandler
    public void PlayerVelocityEvent(PlayerVelocityEvent event){
        drupi.callEvent("PlayerVelocityEvent", event);
    }

    @EventHandler
    public void BrewEvent(BrewEvent event){
        drupi.callEvent("BrewEvent", event);
    }


    @EventHandler
    public void BrewingStandFuelEvent(BrewingStandFuelEvent event){
        drupi.callEvent("BrewingStandFuelEvent", event);
    }


    @EventHandler
    public void CraftItemEvent(CraftItemEvent event){
        drupi.callEvent("CraftItemEvent", event);
    }


    @EventHandler
    public void FurnaceBurnEvent(FurnaceBurnEvent event){
        drupi.callEvent("FurnaceBurnEvent", event);
    }


    @EventHandler
    public void FurnaceExtractEvent(FurnaceExtractEvent event){
        drupi.callEvent("FurnaceExtractEvent", event);
    }


    @EventHandler
    public void FurnaceSmeltEvent(FurnaceSmeltEvent event){
        drupi.callEvent("FurnaceSmeltEvent", event);
    }


    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event){
        drupi.callEvent("InventoryClickEvent", event);
    }


    @EventHandler
    public void InventoryCloseEvent(InventoryCloseEvent event){
        drupi.callEvent("InventoryCloseEvent", event);
    }


    @EventHandler
    public void InventoryCreativeEvent(InventoryCreativeEvent event){
        drupi.callEvent("InventoryCreativeEvent", event);
    }


    @EventHandler
    public void InventoryDragEvent(InventoryDragEvent event){
        drupi.callEvent("InventoryDragEvent", event);
    }




    @EventHandler
    public void InventoryInteractEvent(InventoryInteractEvent event){
        drupi.callEvent("InventoryInteractEvent", event);
    }


    @EventHandler
    public void InventoryMoveItemEvent(InventoryMoveItemEvent event){
        drupi.callEvent("InventoryMoveItemEvent", event);
    }


    @EventHandler
    public void InventoryOpenEvent(InventoryOpenEvent event){
        drupi.callEvent("InventoryOpenEvent", event);
    }


    @EventHandler
    public void InventoryPickupItemEvent(InventoryPickupItemEvent event){
        drupi.callEvent("InventoryPickupItemEvent", event);
    }


    @EventHandler
    public void PrepareAnvilEvent(PrepareAnvilEvent event){
        drupi.callEvent("PrepareAnvilEvent", event);
    }


    @EventHandler
    public void PrepareItemCraftEvent(PrepareItemCraftEvent event){
        drupi.callEvent("PrepareItemCraftEvent", event);
    }


    @EventHandler
    public void TradeSelectEvent(TradeSelectEvent event){
        drupi.callEvent("TradeSelectEvent", event);
    }

    @EventHandler
    public void HangingBreakByEntityEvent(HangingBreakByEntityEvent event){
        drupi.callEvent("HangingBreakByEntityEvent", event);
    }


    @EventHandler
    public void HangingBreakEvent(HangingBreakEvent event){
        drupi.callEvent("HangingBreakEvent", event);
    }



    @EventHandler
    public void HangingPlaceEvent(HangingPlaceEvent event){
        drupi.callEvent("HangingPlaceEvent", event);
    }



    @EventHandler
    public void AreaEffectCloudApplyEvent(AreaEffectCloudApplyEvent event){
        drupi.callEvent("AreaEffectCloudApplyEvent", event);
    }


    @EventHandler
    public void BatToggleSleepEvent(BatToggleSleepEvent event){
        drupi.callEvent("BatToggleSleepEvent", event);
    }


    @EventHandler
    public void CreatureSpawnEvent(CreatureSpawnEvent event){
        drupi.callEvent("CreatureSpawnEvent", event);
    }


    @EventHandler
    public void CreeperPowerEvent(CreeperPowerEvent event){
        drupi.callEvent("CreeperPowerEvent", event);
    }


    @EventHandler
    public void EnderDragonChangePhaseEvent(EnderDragonChangePhaseEvent event){
        drupi.callEvent("EnderDragonChangePhaseEvent", event);
    }


    @EventHandler
    public void EntityAirChangeEvent(EntityAirChangeEvent event){
        drupi.callEvent("EntityAirChangeEvent", event);
    }


    @EventHandler
    public void EntityBreakDoorEvent(EntityBreakDoorEvent event){
        drupi.callEvent("EntityBreakDoorEvent", event);
    }


    @EventHandler
    public void EntityBreedEvent(EntityBreedEvent event){
        drupi.callEvent("EntityBreedEvent", event);
    }


    @EventHandler
    public void EntityChangeBlockEvent(EntityChangeBlockEvent event){
        drupi.callEvent("EntityChangeBlockEvent", event);
    }


    @EventHandler
    public void EntityCombustByBlockEvent(EntityCombustByBlockEvent event){
        drupi.callEvent("EntityCombustByBlockEvent", event);
    }


    @EventHandler
    public void EntityCombustByEntityEvent(EntityCombustByEntityEvent event){
        drupi.callEvent("EntityCombustByEntityEvent", event);
    }


    @EventHandler
    public void EntityCombustEvent(EntityCombustEvent event){
        drupi.callEvent("EntityCombustEvent", event);
    }


    @EventHandler
    public void EntityDamageByBlockEvent(EntityDamageByBlockEvent event){
        drupi.callEvent("EntityDamageByBlockEvent", event);
    }


    @EventHandler
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent event){
        drupi.callEvent("EntityDamageByEntityEvent", event);
    }


    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent event){
        drupi.callEvent("EntityDamageEvent", event);
    }


    @EventHandler
    public void EntityDeathEvent(EntityDeathEvent event){
        drupi.callEvent("EntityDeathEvent", event);
    }


    @EventHandler
    public void EntityDropItemEvent(EntityDropItemEvent event){
        drupi.callEvent("EntityDropItemEvent", event);
    }


    @EventHandler
    public void EntityExplodeEvent(EntityExplodeEvent event){
        drupi.callEvent("EntityExplodeEvent", event);
    }


    @EventHandler
    public void EntityInteractEvent(EntityInteractEvent event){
        drupi.callEvent("EntityInteractEvent", event);
    }


    @EventHandler
    public void EntityPickupItemEvent(EntityPickupItemEvent event){
        drupi.callEvent("EntityPickupItemEvent", event);
    }



    @EventHandler
    public void EntityPortalEnterEvent(EntityPortalEnterEvent event){
        drupi.callEvent("EntityPortalEnterEvent", event);
    }


    @EventHandler
    public void EntityPortalEvent(EntityPortalEvent event){
        drupi.callEvent("EntityPortalEvent", event);
    }


    @EventHandler
    public void EntityPortalExitEvent(EntityPortalExitEvent event){
        drupi.callEvent("EntityPortalExitEvent", event);
    }


    @EventHandler
    public void EntityPoseChangeEvent(EntityPoseChangeEvent event){
        drupi.callEvent("EntityPoseChangeEvent", event);
    }


    @EventHandler
    public void EntityPotionEffectEvent(EntityPotionEffectEvent event){
        drupi.callEvent("EntityPotionEffectEvent", event);
    }


    @EventHandler
    public void EntityRegainHealthEvent(EntityRegainHealthEvent event){
        drupi.callEvent("EntityRegainHealthEvent", event);
    }


    @EventHandler
    public void EntityResurrectEvent(EntityResurrectEvent event){
        drupi.callEvent("EntityResurrectEvent", event);
    }


    @EventHandler
    public void EntityShootBowEvent(EntityShootBowEvent event){
        drupi.callEvent("EntityShootBowEvent", event);
    }


    @EventHandler
    public void EntitySpawnEvent(EntitySpawnEvent event){
        drupi.callEvent("EntitySpawnEvent", event);
    }


    @EventHandler
    public void EntityTameEvent(EntityTameEvent event){
        drupi.callEvent("EntityTameEvent", event);
    }


    @EventHandler
    public void EntityTargetEvent(EntityTargetEvent event){
        drupi.callEvent("EntityTargetEvent", event);
    }


    @EventHandler
    public void EntityTargetLivingEntityEvent(EntityTargetLivingEntityEvent event){
        drupi.callEvent("EntityTargetLivingEntityEvent", event);
    }


    @EventHandler
    public void EntityTeleportEvent(EntityTeleportEvent event){
        drupi.callEvent("EntityTeleportEvent", event);
    }


    @EventHandler
    public void EntityToggleGlideEvent(EntityToggleGlideEvent event){
        drupi.callEvent("EntityToggleGlideEvent", event);
    }


    @EventHandler
    public void EntityToggleSwimEvent(EntityToggleSwimEvent event){
        drupi.callEvent("EntityToggleSwimEvent", event);
    }


    @EventHandler
    public void EntityTransformEvent(EntityTransformEvent event){
        drupi.callEvent("EntityTransformEvent", event);
    }


    @EventHandler
    public void EntityUnleashEvent(EntityUnleashEvent event){
        drupi.callEvent("EntityUnleashEvent", event);
    }


    @EventHandler
    public void ExpBottleEvent(ExpBottleEvent event){
        drupi.callEvent("ExpBottleEvent", event);
    }


    @EventHandler
    public void ExplosionPrimeEvent(ExplosionPrimeEvent event){
        drupi.callEvent("ExplosionPrimeEvent", event);
    }


    @EventHandler
    public void FireworkExplodeEvent(FireworkExplodeEvent event){
        drupi.callEvent("FireworkExplodeEvent", event);
    }


    @EventHandler
    public void FoodLevelChangeEvent(FoodLevelChangeEvent event){
        drupi.callEvent("FoodLevelChangeEvent", event);
    }


    @EventHandler
    public void HorseJumpEvent(HorseJumpEvent event){
        drupi.callEvent("HorseJumpEvent", event);
    }


    @EventHandler
    public void ItemDespawnEvent(ItemDespawnEvent event){
        drupi.callEvent("ItemDespawnEvent", event);
    }


    @EventHandler
    public void ItemMergeEvent(ItemMergeEvent event){
        drupi.callEvent("ItemMergeEvent", event);
    }


    @EventHandler
    public void ItemSpawnEvent(ItemSpawnEvent event){
        drupi.callEvent("ItemSpawnEvent", event);
    }


    @EventHandler
    public void LingeringPotionSplashEvent(LingeringPotionSplashEvent event){
        drupi.callEvent("LingeringPotionSplashEvent", event);
    }


    @EventHandler
    public void PigZapEvent(PigZapEvent event){
        drupi.callEvent("PigZapEvent", event);
    }


    @EventHandler
    public void PigZombieAngerEvent(PigZombieAngerEvent event){
        drupi.callEvent("PigZombieAngerEvent", event);
    }


    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event){
        drupi.callEvent("PlayerDeathEvent", event);
    }


    @EventHandler
    public void PlayerLeashEntityEvent(PlayerLeashEntityEvent event){
        drupi.callEvent("PlayerLeashEntityEvent", event);
    }


    @EventHandler
    public void PotionSplashEvent(PotionSplashEvent event){
        drupi.callEvent("PotionSplashEvent", event);
    }


    @EventHandler
    public void ProjectileHitEvent(ProjectileHitEvent event){
        drupi.callEvent("ProjectileHitEvent", event);
    }


    @EventHandler
    public void ProjectileLaunchEvent(ProjectileLaunchEvent event){
        drupi.callEvent("ProjectileLaunchEvent", event);
    }


    @EventHandler
    public void SheepDyeWoolEvent(SheepDyeWoolEvent event){
        drupi.callEvent("SheepDyeWoolEvent", event);
    }


    @EventHandler
    public void SheepRegrowWoolEvent(SheepRegrowWoolEvent event){
        drupi.callEvent("SheepRegrowWoolEvent", event);
    }


    @EventHandler
    public void SlimeSplitEvent(SlimeSplitEvent event){
        drupi.callEvent("SlimeSplitEvent", event);
    }


    @EventHandler
    public void VillagerAcquireTradeEvent(VillagerAcquireTradeEvent event){
        drupi.callEvent("VillagerAcquireTradeEvent", event);
    }


    @EventHandler
    public void VillagerCareerChangeEvent(VillagerCareerChangeEvent event){
        drupi.callEvent("VillagerCareerChangeEvent", event);
    }


    @EventHandler
    public void VillagerReplenishTradeEvent(VillagerReplenishTradeEvent event){
        drupi.callEvent("VillagerReplenishTradeEvent", event);
    }


    @EventHandler
    public void EnchantItemEvent(EnchantItemEvent event){
        drupi.callEvent("EnchantItemEvent", event);
    }


    @EventHandler
    public void PrepareItemEnchantEvent(PrepareItemEnchantEvent event){
        drupi.callEvent("PrepareItemEnchantEvent", event);
    }

    @EventHandler
    public void BlockBreakEvent(BlockBreakEvent event){
        drupi.callEvent("BlockBreakEvent", event);
    }


    @EventHandler
    public void BlockBurnEvent(BlockBurnEvent event){
        drupi.callEvent("BlockBurnEvent", event);
    }


    @EventHandler
    public void BlockCanBuildEvent(BlockCanBuildEvent event){
        drupi.callEvent("BlockCanBuildEvent", event);
    }


    @EventHandler
    public void BlockCookEvent(BlockCookEvent event){
        drupi.callEvent("BlockCookEvent", event);
    }


    @EventHandler
    public void BlockDamageEvent(BlockDamageEvent event){
        drupi.callEvent("BlockDamageEvent", event);
    }


    @EventHandler
    public void BlockDispenseArmorEvent(BlockDispenseArmorEvent event){
        drupi.callEvent("BlockDispenseArmorEvent", event);
    }


    @EventHandler
    public void BlockDispenseEvent(BlockDispenseEvent event){
        drupi.callEvent("BlockDispenseEvent", event);
    }


    @EventHandler
    public void BlockDropItemEvent(BlockDropItemEvent event){
        drupi.callEvent("BlockDropItemEvent", event);
    }



    @EventHandler
    public void BlockExpEvent(BlockExpEvent event){
        drupi.callEvent("BlockExpEvent", event);
    }


    @EventHandler
    public void BlockExplodeEvent(BlockExplodeEvent event){
        drupi.callEvent("BlockExplodeEvent", event);
    }


    @EventHandler
    public void BlockFadeEvent(BlockFadeEvent event){
        drupi.callEvent("BlockFadeEvent", event);
    }


    @EventHandler
    public void BlockFertilizeEvent(BlockFertilizeEvent event){
        drupi.callEvent("BlockFertilizeEvent", event);
    }


    @EventHandler
    public void BlockFormEvent(BlockFormEvent event){
        drupi.callEvent("BlockFormEvent", event);
    }


    @EventHandler
    public void BlockFromToEvent(BlockFromToEvent event){
        drupi.callEvent("BlockFromToEvent", event);
    }


    @EventHandler
    public void BlockGrowEvent(BlockGrowEvent event){
        drupi.callEvent("BlockGrowEvent", event);
    }


    @EventHandler
    public void BlockIgniteEvent(BlockIgniteEvent event){
        drupi.callEvent("BlockIgniteEvent", event);
    }


    @EventHandler
    public void BlockMultiPlaceEvent(BlockMultiPlaceEvent event){
        drupi.callEvent("BlockMultiPlaceEvent", event);
    }


    @EventHandler
    public void BlockPhysicsEvent(BlockPhysicsEvent event){
        drupi.callEvent("BlockPhysicsEvent", event);
    }



    @EventHandler
    public void BlockPistonExtendEvent(BlockPistonExtendEvent event){
        drupi.callEvent("BlockPistonExtendEvent", event);
    }


    @EventHandler
    public void BlockPistonRetractEvent(BlockPistonRetractEvent event){
        drupi.callEvent("BlockPistonRetractEvent", event);
    }


    @EventHandler
    public void BlockPlaceEvent(BlockPlaceEvent event){
        drupi.callEvent("BlockPlaceEvent", event);
    }


    @EventHandler
    public void BlockRedstoneEvent(BlockRedstoneEvent event){
        drupi.callEvent("BlockRedstoneEvent", event);
    }


    @EventHandler
    public void BlockShearEntityEvent(BlockShearEntityEvent event){
        drupi.callEvent("BlockShearEntityEvent", event);
    }


    @EventHandler
    public void BlockSpreadEvent(BlockSpreadEvent event){
        drupi.callEvent("BlockSpreadEvent", event);
    }


    @EventHandler
    public void CauldronLevelChangeEvent(CauldronLevelChangeEvent event){
        drupi.callEvent("CauldronLevelChangeEvent", event);
    }


    @EventHandler
    public void EntityBlockFormEvent(EntityBlockFormEvent event){
        drupi.callEvent("EntityBlockFormEvent", event);
    }


    @EventHandler
    public void FluidLevelChangeEvent(FluidLevelChangeEvent event){
        drupi.callEvent("FluidLevelChangeEvent", event);
    }


    @EventHandler
    public void LeavesDecayEvent(LeavesDecayEvent event){
        drupi.callEvent("LeavesDecayEvent", event);
    }


    @EventHandler
    public void MoistureChangeEvent(MoistureChangeEvent event){
        drupi.callEvent("MoistureChangeEvent", event);
    }


    @EventHandler
    public void NotePlayEvent(NotePlayEvent event){
        drupi.callEvent("NotePlayEvent", event);
    }


    @EventHandler
    public void SignChangeEvent(SignChangeEvent event){
        drupi.callEvent("SignChangeEvent", event);
    }


    @EventHandler
    public void SpongeAbsorbEvent(SpongeAbsorbEvent event){
        drupi.callEvent("SpongeAbsorbEvent", event);
    }
}
