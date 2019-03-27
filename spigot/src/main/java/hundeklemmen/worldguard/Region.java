package hundeklemmen.worldguard;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.flags.*;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.RemovalStrategy;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import hundeklemmen.MainPlugin;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Region {

    private MainPlugin plugin;

    public Region(MainPlugin plugin){
        this.plugin = plugin;
    }

    public WorldGuardPlugin get(){
        return (WorldGuardPlugin) plugin.getServer().getPluginManager().getPlugin("WorldGuard");
    };

    public void addMember(Player player, String region, World world){
        String name = region;
        RegionManager regionManager = WGBukkit.getRegionManager(world);
        if (!regionManager.hasRegion(name)) {
            plugin.getLogger().warning("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
            return;
        }
        DefaultDomain members = regionManager.getRegion(name).getMembers();
        members.addPlayer(player.getName());
        regionManager.getRegion(name).setMembers(members);
        try {
            regionManager.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeMember(Player player, String region, World world){
        String name = region;
        RegionManager regionManager = WGBukkit.getRegionManager(world);
        if (!regionManager.hasRegion(name)) {
            plugin.getLogger().warning("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
            return;
        }

        DefaultDomain members = regionManager.getRegion(name).getMembers();
        members.removePlayer(player.getName());
        regionManager.getRegion(name).setMembers(members);
        try {
            regionManager.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addOwner(Player player, String region, World world){
        String name = region;
        RegionManager regionManager = WGBukkit.getRegionManager(world);
        if (!regionManager.hasRegion(name)) {
            plugin.getLogger().warning("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
            return;
        }
        DefaultDomain owners = regionManager.getRegion(name).getOwners();
        owners.addPlayer(player.getName());
        regionManager.getRegion(name).setMembers(owners);
        try {
            regionManager.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeOwner(Player player, String region, World world){
        String name = region;
        RegionManager regionManager = WGBukkit.getRegionManager(world);
        if (!regionManager.hasRegion(name)) {
            plugin.getLogger().warning("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
            return;
        }

        DefaultDomain owners = regionManager.getRegion(name).getOwners();
        owners.removePlayer(player.getName());
        regionManager.getRegion(name).setOwners(owners);
        try {
            regionManager.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createRegion(String name, Location loc1, Location loc2, World world){
        int x1 = loc1.getBlockX();
        int y1 = loc1.getBlockY();
        int z1 = loc1.getBlockZ();

        int x2 = loc2.getBlockX();
        int y2 = loc2.getBlockY();
        int z2 = loc2.getBlockZ();

        Vector p1 = new Vector(x1, y1, z1);
        Vector p2 = new Vector(x2, y2, z2);
        RegionManager regionManager = WGBukkit.getRegionManager(world);
        ProtectedCuboidRegion region = new ProtectedCuboidRegion(name, p1.toBlockVector(), p2.toBlockVector());
        regionManager.addRegion(region);
        try {
            regionManager.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRegion(String name, World world){
        RegionManager regionManager = WGBukkit.getRegionManager(world);
        if (!regionManager.hasRegion(name)) {
            plugin.getLogger().warning("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
            return;
        }
        regionManager.removeRegion(name, RemovalStrategy.REMOVE_CHILDREN);
        try {
            regionManager.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] regionsOfPlayer(Player player, World world){
        WorldGuardPlugin wg = (WorldGuardPlugin) plugin.getServer().getPluginManager().getPlugin("WorldGuard");
        RegionManager regionManager = wg.getRegionManager(world);
        if (player == null) {
            return new String[] {};
        }
        LocalPlayer wpl = null;
        if (player.isOnline()) {
            wpl = wg.wrapPlayer(player.getPlayer());
        } else {
            wpl = wg.wrapOfflinePlayer(player);
        }
        ArrayList<String> pregions = new ArrayList<String>();
        for (Map.Entry<String, ProtectedRegion> reg : regionManager.getRegions().entrySet()) {
            if (reg.getValue().isMember(wpl)) {
                pregions.add(reg.getValue().getId());
            }
        }
        return pregions.toArray(new String[pregions.size()]);
    }

    public String[] regionAt(Location loc){
        String a = null;
        WorldGuardPlugin wg = (WorldGuardPlugin) plugin.getServer().getPluginManager().getPlugin("WorldGuard");
        RegionManager regionManager = wg.getRegionContainer().get(loc.getWorld());

        for (ProtectedRegion reg : regionManager.getApplicableRegions(loc)) {

            if (reg.contains(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ())) {
                a = reg.getId().toString();
            }
        }
        return new String[] { a };
    }

    public void setFlag(String region, World world, String flag, Object value){
        WorldGuardPlugin wg = (WorldGuardPlugin) plugin.getServer().getPluginManager().getPlugin("WorldGuard");
        RegionManager set = wg.getRegionManager(world);
        Flag<?> fl = null;
        for (Flag<?> f : DefaultFlag.getFlags()) {
            if (f.getName().equalsIgnoreCase(flag)) {
                fl = f;
                break;
            }
        }
        try {
            try {
                if (value instanceof Boolean) {
                    if ((Boolean) value) {
                        set.getRegion(region).setFlag((StateFlag) fl, StateFlag.State.ALLOW);
                    } else {
                        set.getRegion(region).setFlag((StateFlag) fl, StateFlag.State.DENY);
                    }
                } else if (value instanceof String) {
                    set.getRegion(region).setFlag((StringFlag) fl, (String) value);
                } else if (value instanceof Integer) {
                    set.getRegion(region).setFlag((IntegerFlag) fl, (int) value);
                } else if (value instanceof Double) {
                    set.getRegion(region).setFlag((DoubleFlag) fl, (double) value);
                } else {
                    plugin.getLogger().warning("Region flag " + "\"" + fl.getName() + "\"" + " cannot be set to: " + value);
                }
            } catch (ClassCastException ex) {
                plugin.getLogger().warning("Region flag " + "\"" + fl.getName() + "\"" + " cannot be set to: " + value);
            }
        } catch (NullPointerException ex) {
            plugin.getLogger().warning("Region flag " + "\"" + flag + "\"" + " does not exist");
        }
    }

    public String[] allFlagOfRegion(String region, World world){
        RegionManager rm = WGBukkit.getRegionManager(world);
        ProtectedRegion pregion = rm.getRegion(region);
        List<String> rfl = new ArrayList<String>();

        for (Map.Entry<Flag<?>, Object> ra : pregion.getFlags().entrySet()) {
            rfl.add(ra.getKey().getName());
        }

        String[] s = new String[rfl.size()];
        return (String[]) rfl.toArray(s);
    }

    public String[] allMembers(String region, World world){
        RegionManager rm = WGBukkit.getRegionManager(world);
        ProtectedRegion pregion = rm.getRegion(region);
        List<String> list = new ArrayList<String>(pregion.getMembers().getPlayers());

        String[] s = new String[list.size()];
        return (String[]) list.toArray(s);
    }

    public String[] allOwners(String region, World world){
        RegionManager rm = WGBukkit.getRegionManager(world);
        ProtectedRegion pregion = rm.getRegion(region);
        List<String> list = new ArrayList<String>(pregion.getOwners().getPlayers());

        String[] s = new String[list.size()];
        return (String[]) list.toArray(s);
    }

    public String[] allRegionsInWorld(World world){
        RegionManager set = WGBukkit.getRegionManager(world);
        Map<String, ProtectedRegion> regions = set.getRegions();
        List<String> list = new ArrayList<String>(regions.keySet());

        String[] s = new String[list.size()];
        return (String[]) list.toArray(s);
    }

    public Location[] aetPoint1(String region, World world){
        String name = region;
        RegionManager regionManager = WGBukkit.getRegionManager(world);
        if (!regionManager.hasRegion(name)) {
            plugin.getLogger().warning("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
            return null;
        }

        double x = regionManager.getRegion(name).getMinimumPoint().getX();
        double y = regionManager.getRegion(name).getMinimumPoint().getY();
        double z = regionManager.getRegion(name).getMinimumPoint().getZ();

        Location pos1 = new Location(world, x, y, z);

        return new Location[] { pos1 };
    }

    public Location[] aetPoint2(String region, World world){
        String name = region;
        RegionManager regionManager = WGBukkit.getRegionManager(world);
        if (!regionManager.hasRegion(name)) {
            plugin.getLogger().warning("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
            return null;
        }

        double x = regionManager.getRegion(name).getMaximumPoint().getX();
        double y = regionManager.getRegion(name).getMaximumPoint().getY();
        double z = regionManager.getRegion(name).getMaximumPoint().getZ();

        Location pos1 = new Location(world, x, y, z);

        return new Location[] { pos1 };
    }


}
