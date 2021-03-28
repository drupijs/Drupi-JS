package net.stacket.drupi.v1_8.expansions.worldguard;


import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.stacket.drupi.shared.api.Drupi;
import net.stacket.drupi.v1_8.expansions.worldguard.customEvents.RegionEnterEvent;
import net.stacket.drupi.v1_8.expansions.worldguard.customEvents.RegionEnteredEvent;
import net.stacket.drupi.v1_8.expansions.worldguard.customEvents.RegionLeaveEvent;
import net.stacket.drupi.v1_8.expansions.worldguard.customEvents.RegionLeftEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import java.util.*;

/**
 *
 * @author mewin
 */
public class WGRegionEventsListener implements Listener
{
    private WorldGuardPlugin wgPlugin;
    private Drupi drupi;

    private Map<Player, Set<ProtectedRegion>> playerRegions;

    public WGRegionEventsListener(Drupi drupi)
    {
        this.drupi = drupi;
        this.wgPlugin = (WorldGuardPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");

        playerRegions = new HashMap<Player, Set<ProtectedRegion>>();
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent e)
    {
        Set<ProtectedRegion> regions = playerRegions.remove(e.getPlayer());
        if (regions != null)
        {
            for(ProtectedRegion region : regions)
            {
                RegionLeaveEvent leaveEvent = new RegionLeaveEvent(region, e.getPlayer(), MovementWay.DISCONNECT, e);
                RegionLeftEvent leftEvent = new RegionLeftEvent(region, e.getPlayer(), MovementWay.DISCONNECT, e);

                Bukkit.getServer().getPluginManager().callEvent(leaveEvent);
                Bukkit.getServer().getPluginManager().callEvent(leftEvent);
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        Set<ProtectedRegion> regions = playerRegions.remove(e.getPlayer());
        if (regions != null)
        {
            for(ProtectedRegion region : regions)
            {
                RegionLeaveEvent leaveEvent = new RegionLeaveEvent(region, e.getPlayer(), MovementWay.DISCONNECT, e);
                RegionLeftEvent leftEvent = new RegionLeftEvent(region, e.getPlayer(), MovementWay.DISCONNECT, e);

                Bukkit.getServer().getPluginManager().callEvent(leaveEvent);
                Bukkit.getServer().getPluginManager().callEvent(leftEvent);
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e)
    {
        e.setCancelled(updateRegions(e.getPlayer(), MovementWay.MOVE, e.getTo(), e));
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent e)
    {
        e.setCancelled(updateRegions(e.getPlayer(), MovementWay.TELEPORT, e.getTo(), e));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        updateRegions(e.getPlayer(), MovementWay.SPAWN, e.getPlayer().getLocation(), e);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e)
    {
        updateRegions(e.getPlayer(), MovementWay.SPAWN, e.getRespawnLocation(), e);
    }

    private synchronized boolean updateRegions(final Player player, final MovementWay movement, Location to, final PlayerEvent event)
    {
        Set<ProtectedRegion> regions;
        Set<ProtectedRegion> oldRegions;

        if (playerRegions.get(player) == null)
        {
            regions = new HashSet<ProtectedRegion>();
        }
        else
        {
            regions = new HashSet<ProtectedRegion>(playerRegions.get(player));
        }

        oldRegions = new HashSet<ProtectedRegion>(regions);

        RegionManager rm = wgPlugin.getRegionManager(to.getWorld());

        if (rm == null)
        {
            return false;
        }

        HashSet<ProtectedRegion> appRegions = new HashSet<ProtectedRegion>(
                rm.getApplicableRegions(to).getRegions());
        ProtectedRegion globalRegion = rm.getRegion("__global__");
        if (globalRegion != null) // just to be sure
        {
            appRegions.add(globalRegion);
        }

        for (final ProtectedRegion region : appRegions)
        {
            if (!regions.contains(region))
            {
                RegionEnterEvent e = new RegionEnterEvent(region, player, movement, event);

                Bukkit.getServer().getPluginManager().callEvent(e);

                if (e.isCancelled())
                {
                    regions.clear();
                    regions.addAll(oldRegions);

                    return true;
                }
                else
                {
                    Bukkit.getScheduler().runTaskLater(Bukkit.getServer().getPluginManager().getPlugin("Drupi"), new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            {}
                            RegionEnteredEvent e = new RegionEnteredEvent(region, player, movement, event);

                            Bukkit.getServer().getPluginManager().callEvent(e);
                        }
                    }, 1L);
                    regions.add(region);
                }
            }
        }

        Iterator<ProtectedRegion> itr = regions.iterator();
        while(itr.hasNext())
        {
            final ProtectedRegion region = itr.next();
            if (!appRegions.contains(region))
            {
                if (rm.getRegion(region.getId()) != region)
                {
                    itr.remove();
                    continue;
                }
                RegionLeaveEvent e = new RegionLeaveEvent(region, player, movement, event);

                Bukkit.getServer().getPluginManager().callEvent(e);

                if (e.isCancelled())
                {
                    regions.clear();
                    regions.addAll(oldRegions);
                    return true;
                }
                else
                {
                    Bukkit.getScheduler().runTaskLater(Bukkit.getServer().getPluginManager().getPlugin("Drupi"), new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            RegionLeftEvent e = new RegionLeftEvent(region, player, movement, event);

                            Bukkit.getServer().getPluginManager().callEvent(e);
                        }
                    }, 1L);
                    itr.remove();
                }
            }
        }
        playerRegions.put(player, regions);
        return false;
    }
}