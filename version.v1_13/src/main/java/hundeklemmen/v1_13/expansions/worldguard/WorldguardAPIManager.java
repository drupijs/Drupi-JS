package hundeklemmen.v1_13.expansions.worldguard;

import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.managers.RemovalStrategy;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import hundeklemmen.shared.api.Drupi;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WorldguardAPIManager {

    private Drupi drupi;

    public WorldguardAPIManager(Drupi drupi){
        this.drupi = drupi;
    }

    public WorldGuard get(){
        return com.sk89q.worldguard.WorldGuard.getInstance();
    };


    public void addMember(Player player, String region, World world){
        String name = region;
        RegionManager regionManager = get().getInstance().getPlatform().getRegionContainer().get((com.sk89q.worldedit.world.World) world);
        if (!regionManager.hasRegion(name)) {
            drupi.log.warning("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
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
        RegionManager regionManager = get().getInstance().getPlatform().getRegionContainer().get((com.sk89q.worldedit.world.World) world);
        if (!regionManager.hasRegion(name)) {
            drupi.log.warning("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
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
        RegionManager regionManager = get().getInstance().getPlatform().getRegionContainer().get((com.sk89q.worldedit.world.World) world);
        if (!regionManager.hasRegion(name)) {
            drupi.log.warning("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
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
        RegionManager regionManager = get().getInstance().getPlatform().getRegionContainer().get((com.sk89q.worldedit.world.World) world);
        if (!regionManager.hasRegion(name)) {
            drupi.log.warning("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
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

        BlockVector3 p1 = BlockVector3.at(x1, y1, z1);
        BlockVector3 p2 = BlockVector3.at(x2, y2, z2);
        RegionManager regionManager = get().getInstance().getPlatform().getRegionContainer().get((com.sk89q.worldedit.world.World) world);
        ProtectedCuboidRegion region = new ProtectedCuboidRegion(name, p1, p2);
        regionManager.addRegion(region);
        try {
            regionManager.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRegion(String name, World world){
        RegionManager regionManager = get().getInstance().getPlatform().getRegionContainer().get((com.sk89q.worldedit.world.World) world);
        if (!regionManager.hasRegion(name)) {
            drupi.log.warning("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
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
        RegionManager regionManager = get().getInstance().getPlatform().getRegionContainer().get((com.sk89q.worldedit.world.World) world);
        if (player == null) {
            return new String[] {};
        }
        LocalPlayer wpl = null;
        if (player.isOnline()) {
            try {
                wpl = (LocalPlayer) get().checkPlayer((Actor) player.getPlayer());
            } catch (CommandException e) {
                e.printStackTrace();
            }
        } else {
            try {
                wpl = (LocalPlayer) get().checkPlayer((Actor) player.getPlayer());
            } catch (CommandException e) {
                e.printStackTrace();
            }
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
        RegionManager regionManager = get().getInstance().getPlatform().getRegionContainer().get((com.sk89q.worldedit.world.World) loc.getWorld());

        for (ProtectedRegion reg : regionManager.getApplicableRegions(BlockVector3.at(loc.getX(), loc.getY(), loc.getZ()))) {

            if (reg.contains(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ())) {
                a = reg.getId().toString();
            }
        }
        return new String[] { a };
    }


    public String[] allFlagOfRegion(String region, World world){
        RegionManager regionManager = get().getInstance().getPlatform().getRegionContainer().get((com.sk89q.worldedit.world.World) world);
        ProtectedRegion pregion = regionManager.getRegion(region);
        List<String> rfl = new ArrayList<String>();

        for (Map.Entry<Flag<?>, Object> ra : pregion.getFlags().entrySet()) {
            rfl.add(ra.getKey().getName());
        }

        String[] s = new String[rfl.size()];
        return (String[]) rfl.toArray(s);
    }

    public String[] allMembers(String region, World world){
        RegionManager regionManager = get().getInstance().getPlatform().getRegionContainer().get((com.sk89q.worldedit.world.World) world);
        ProtectedRegion pregion = regionManager.getRegion(region);
        List<String> list = new ArrayList<String>(pregion.getMembers().getPlayers());

        String[] s = new String[list.size()];
        return (String[]) list.toArray(s);
    }

    public String[] allOwners(String region, World world){
        RegionManager rm = get().getInstance().getPlatform().getRegionContainer().get((com.sk89q.worldedit.world.World) world);
        ProtectedRegion pregion = rm.getRegion(region);
        List<String> list = new ArrayList<String>(pregion.getOwners().getPlayers());

        String[] s = new String[list.size()];
        return (String[]) list.toArray(s);
    }

    public String[] allRegionsInWorld(World world){
        RegionManager set = get().getInstance().getPlatform().getRegionContainer().get((com.sk89q.worldedit.world.World) world);
        Map<String, ProtectedRegion> regions = set.getRegions();
        List<String> list = new ArrayList<String>(regions.keySet());

        String[] s = new String[list.size()];
        return (String[]) list.toArray(s);
    }

    public Location[] aetPoint1(String region, World world){
        String name = region;
        RegionManager regionManager = get().getInstance().getPlatform().getRegionContainer().get((com.sk89q.worldedit.world.World) world);
        if (!regionManager.hasRegion(name)) {
            drupi.log.warning("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
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
        RegionManager regionManager = get().getInstance().getPlatform().getRegionContainer().get((com.sk89q.worldedit.world.World) world);
        if (!regionManager.hasRegion(name)) {
            drupi.log.warning("Region \"" + name + "\" in world \"" + world.getName() + "\" does not exists.");
            return null;
        }

        double x = regionManager.getRegion(name).getMaximumPoint().getX();
        double y = regionManager.getRegion(name).getMaximumPoint().getY();
        double z = regionManager.getRegion(name).getMaximumPoint().getZ();

        Location pos1 = new Location(world, x, y, z);

        return new Location[] { pos1 };
    }
}
