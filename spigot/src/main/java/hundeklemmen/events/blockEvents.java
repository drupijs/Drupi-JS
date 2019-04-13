package hundeklemmen.events;

import hundeklemmen.MainPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;

public class blockEvents implements Listener {

    @EventHandler
    public void BlockBreak(BlockBreakEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockBurn(BlockBurnEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockCanBuild(BlockCanBuildEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockDamage(BlockDamageEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void BlockDispenseArmor(BlockDispenseArmorEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void BlockDispense(BlockDispenseEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void BlockDropItem(BlockDropItemEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void BlockExp(BlockExpEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void BlBlockBurnEventockExplode(BlockExplodeEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockFade(BlockFadeEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void BlockFertilize(BlockFertilizeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void BlockForm(BlockFormEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockFromTo(BlockFromToEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockGrow(BlockGrowEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockIgnite(BlockIgniteEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockMultiPlace(BlockMultiPlaceEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockPhysics(BlockPhysicsEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }

    @EventHandler
    public void BlockPistonExtend(BlockPistonExtendEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockPistonRetract(BlockPistonRetractEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockPlace(BlockPlaceEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockRedstone(BlockRedstoneEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void BlockSpread(BlockSpreadEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void CauldronLevelChange(CauldronLevelChangeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void EntityBlockForm(EntityBlockFormEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void FluidLevelChange(FluidLevelChangeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void LeavesDecay(LeavesDecayEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void MoistureChange(MoistureChangeEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
    @EventHandler
    public void NotePlay(NotePlayEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    @EventHandler
    public void SignChange(SignChangeEvent event){
        MainPlugin.instance.callEventHandler(event, event.getClass().getSimpleName());
    }
    /*@EventHandler
    public void SpongeAbsorb(SpongeAbsorbEvent event){
        main.instance.callEventHandler(event, event.getClass().getSimpleName());
    }*/
}
