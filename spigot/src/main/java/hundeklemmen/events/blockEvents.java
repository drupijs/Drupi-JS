package hundeklemmen.events;

import hundeklemmen.main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;

public class blockEvents implements Listener {

    @EventHandler
    public void BlockBreak(BlockBreakEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockBurn(BlockBurnEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockCanBuild(BlockCanBuildEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockDamage(BlockDamageEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void BlockDispenseArmor(BlockDispenseArmorEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void BlockDispense(BlockDispenseEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void BlockDropItem(BlockDropItemEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void Block(BlockEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockExp(BlockExpEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockExplode(BlockExplodeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockFade(BlockFadeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void BlockFertilize(BlockFertilizeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void BlockForm(BlockFormEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockFromTo(BlockFromToEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockGrow(BlockGrowEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockIgnite(BlockIgniteEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockMultiPlace(BlockMultiPlaceEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockPhysics(BlockPhysicsEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockPiston(BlockPistonEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockPistonExtend(BlockPistonExtendEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockPistonRetract(BlockPistonRetractEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockPlace(BlockPlaceEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockRedstone(BlockRedstoneEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockSpread(BlockSpreadEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void CauldronLevelChange(CauldronLevelChangeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void EntityBlockForm(EntityBlockFormEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void FluidLevelChange(FluidLevelChangeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void LeavesDecay(LeavesDecayEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void MoistureChange(MoistureChangeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void NotePlay(NotePlayEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void SignChange(SignChangeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void SpongeAbsorb(SpongeAbsorbEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
}
