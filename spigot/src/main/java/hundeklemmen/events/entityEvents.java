package hundeklemmen.events;

import hundeklemmen.main;
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
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void CreeperPower(CreeperPowerEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
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
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void EntityBreed(EntityBreedEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void EntityChangeBlock(EntityChangeBlockEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityCombustByBlock(EntityCombustByBlockEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityCombustByEntity(EntityCombustByEntityEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityCombust(EntityCombustEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityCreatePortal(EntityCreatePortalEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityDamageByBlock(EntityDamageByBlockEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityDamageByEntity(EntityDamageByEntityEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityDamage(EntityDamageEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityDeath(EntityDeathEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void EntityDropItem(EntityDropItemEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void Entity(EntityEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityExplode(EntityExplodeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityInteract(EntityInteractEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
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
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityPortal(EntityPortalEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityPortalExit(EntityPortalExitEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void EntityPotionEffect(EntityPotionEffectEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void EntityRegainHealth(EntityRegainHealthEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void EntityResurrect(EntityResurrectEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void EntityShootBow(EntityShootBowEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntitySpawn(EntitySpawnEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityTame(EntityTameEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityTarget(EntityTargetEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityTargetLivingEntity(EntityTargetLivingEntityEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void EntityTeleport(EntityTeleportEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
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
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ExpBottle(ExpBottleEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ExplosionPrime(ExplosionPrimeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void FireworkExplode(FireworkExplodeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void FoodLevelChange(FoodLevelChangeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void HorseJump(HorseJumpEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ItemDespawn(ItemDespawnEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ItemMerge(ItemMergeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ItemSpawn(ItemSpawnEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void LingeringPotionSplash(LingeringPotionSplashEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void PigZap(PigZapEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void PigZombieAnger(PigZombieAngerEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void PlayerDeath(PlayerDeathEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void PlayerLeashEntity(PlayerLeashEntityEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void PotionSplash(PotionSplashEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ProjectileHit(ProjectileHitEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void ProjectileLaunch(ProjectileLaunchEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void SheepDyeWool(SheepDyeWoolEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void SheepRegrowWool(SheepRegrowWoolEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void SlimeSplit(SlimeSplitEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
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
