package hundeklemmen.events;

import hundeklemmen.MainPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

public class entityEvents implements Listener {

   /* @EventHandler
    public void AreaEffectCloudApply(AreaEffectCloudApplyEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BatToggleSleep(BatToggleSleepEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void CreatureSpawn(CreatureSpawnEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void CreeperPower(CreeperPowerEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void EnderDragonChangePhase(EnderDragonChangePhaseEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityAirChange(EntityAirChangeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void EntityBreakDoor(EntityBreakDoorEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void EntityBreed(EntityBreedEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void EntityChangeBlock(EntityChangeBlockEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityCombustByBlock(EntityCombustByBlockEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityCombustByEntity(EntityCombustByEntityEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityCombust(EntityCombustEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityCreatePortal(EntityCreatePortalEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityDamageByBlock(EntityDamageByBlockEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityDamageByEntity(EntityDamageByEntityEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityDamage(EntityDamageEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityDeath(EntityDeathEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void EntityDropItem(EntityDropItemEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void EntityExplode(EntityExplodeEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityInteract(EntityInteractEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void EntityPickupItem(EntityPickupItemEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityPlace(EntityPlaceEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void EntityPortalEnter(EntityPortalEnterEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityPortal(EntityPortalEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityPortalExit(EntityPortalExitEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void EntityPotionEffect(EntityPotionEffectEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void EntityRegainHealth(EntityRegainHealthEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void EntityResurrect(EntityResurrectEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void EntityShootBow(EntityShootBowEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntitySpawn(EntitySpawnEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityTame(EntityTameEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityTarget(EntityTargetEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityTargetLivingEntity(EntityTargetLivingEntityEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityTeleport(EntityTeleportEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void EntityToggleGlide(EntityToggleGlideEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityToggleSwim(EntityToggleSwimEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityTransform(EntityTransformEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void EntityUnleash(EntityUnleashEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ExpBottle(ExpBottleEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ExplosionPrime(ExplosionPrimeEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void FireworkExplode(FireworkExplodeEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void FoodLevelChange(FoodLevelChangeEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void HorseJump(HorseJumpEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ItemDespawn(ItemDespawnEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ItemMerge(ItemMergeEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ItemSpawn(ItemSpawnEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void LingeringPotionSplash(LingeringPotionSplashEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void PigZap(PigZapEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void PigZombieAnger(PigZombieAngerEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void PlayerDeath(PlayerDeathEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void PlayerLeashEntity(PlayerLeashEntityEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void PotionSplash(PotionSplashEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ProjectileHit(ProjectileHitEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ProjectileLaunch(ProjectileLaunchEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void SheepDyeWool(SheepDyeWoolEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void SheepRegrowWool(SheepRegrowWoolEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void SlimeSplit(SlimeSplitEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void VillagerAcquireTrade(VillagerAcquireTradeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void VillagerReplenishTrade(VillagerReplenishTradeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
}
