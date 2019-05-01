package hundeklemmen.v1_8.expansions.worldguard.customEvents;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import hundeklemmen.v1_8.expansions.worldguard.MovementWay;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.player.PlayerEvent;

/**
 * event that is triggered before a player leaves a WorldGuard region, can be cancelled sometimes
 * @author mewin
 */
public class RegionLeaveEvent extends RegionEvent  implements Cancellable {
    private boolean cancelled, cancellable;
    /**
     * creates a new RegionLeaveEvent
     * @param region the region the player is leaving
     * @param player the player who triggered the event
     * @param movement the type of movement how the player leaves the region
     */
    public RegionLeaveEvent(ProtectedRegion region, Player player, MovementWay movement, PlayerEvent parent)
    {
        super(region, player, movement, parent);
        cancelled = false;
        cancellable = true;

        if (movement == MovementWay.SPAWN
                || movement == MovementWay.DISCONNECT)
        {
            cancellable = false;
        }
    }

    /**
     * sets whether this event should be cancelled
     * when the event is cancelled the player will not be able to move out of the region
     * @param cancelled true if the player should be stopped from moving out of the region
     */
    @Override
    public void setCancelled(boolean cancelled)
    {
        if (!this.cancellable)
        {
            return;
        }

        this.cancelled = cancelled;
    }

    /**
     * retrieves whether this event will be cancelled/has been cancelled by any plugin
     * @return true if this event will be cancelled and the player will be stopped from moving
     */
    @Override
    public boolean isCancelled()
    {
        return this.cancelled;
    }


    /**
     * sometimes you can not cancel an event, i.e. if a player left a region by dying inside of it
     * @return true, if you can cancel this event
     */
    public boolean isCancellable()
    {
        return this.cancellable;
    }

    protected void setCancellable(boolean cancellable)
    {
        this.cancellable = cancellable;

        if (!this.cancellable)
        {
            this.cancelled = false;
        }
    }
}