package net.berryjar.bjsg.cuboid;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import org.bukkit.Location;

import java.util.Set;

public class CuboidManager {

    private final BJSG plugin;

    public CuboidManager(final BJSG plugin) {
        this.plugin = plugin;
    }

    private final CuboidBuilder builder = new CuboidBuilder();

    public void removeRegion(String regionID) {

        plugin.getConfig().set("regions." + regionID, null);
        plugin.saveConfig();
        plugin.activeRegions.removeIf(c -> c.getID().equals(regionID));

    }

    public Cuboid getRegion(String regionID) {

        for (Cuboid c : plugin.activeRegions) {
            if (c.getID().equals(regionID)) {
                return c;
            }
        }
        return null;

    }
    public void createRegion(String regionID, Location blockLoc1, Location blockLoc2) {

        Cuboid cuboid = new Cuboid(regionID, blockLoc1, blockLoc2);
        plugin.activeRegions.add(cuboid);
        Arena arena = new Arena(plugin, cuboid, regionID);
        plugin.activeArenas.add(arena);
        plugin.getConfig().set("regions." + regionID + ".world", blockLoc1.getWorld().getName());
        plugin.getConfig().set("regions." + regionID + ".x1", blockLoc1.getBlockX());
        plugin.getConfig().set("regions." + regionID + ".y1", blockLoc1.getBlockY());
        plugin.getConfig().set("regions." + regionID + ".z1", blockLoc1.getBlockZ());
        plugin.getConfig().set("regions." + regionID + ".x2", blockLoc2.getBlockX());
        plugin.getConfig().set("regions." + regionID + ".y2", blockLoc2.getBlockY());
        plugin.getConfig().set("regions." + regionID + ".z2", blockLoc2.getBlockZ());
        plugin.saveConfig();

    }

    public void loadRegions() {

        for (String regionID : plugin.getConfig().getConfigurationSection("regions").getKeys(false)) {
            for (String atts : plugin.getConfig().getConfigurationSection("regions." + regionID).getKeys(false)) {
                String world = (String) plugin.getConfig().get("regions." + regionID + ".world");
                int x1 = plugin.getConfig().getInt("regions." + regionID + ".x1");
                int y1 = plugin.getConfig().getInt("regions." + regionID + ".y1");
                int z1 = plugin.getConfig().getInt("regions." + regionID + ".z1");
                int x2 = plugin.getConfig().getInt("regions." + regionID + ".x2");
                int y2 = plugin.getConfig().getInt("regions." + regionID + ".y2");
                int z2 = plugin.getConfig().getInt("regions." + regionID + ".z2");

                builder.setWorld(world);
                builder.setRegionID(regionID);
                builder.setX1(x1);
                builder.setY1(y1);
                builder.setZ1(z1);
                builder.setX2(x2);
                builder.setY2(y2);
                builder.setZ2(z2);
                break;

            }
            String world = builder.getWorld();
            String rID = builder.getRegionID();
            int x1 = builder.getX1();
            int y1 = builder.getY1();
            int z1 = builder.getZ1();
            int x2 = builder.getX2();
            int y2 = builder.getY2();
            int z2 = builder.getZ2();

            Cuboid cuboid = new Cuboid(rID, world, x1, y1, z1, x2, y2, z2);
            Arena arena = new Arena(plugin, cuboid, rID);
            arena.getLobby().startLobby(15);
            cuboid.setRegionID(rID);
            plugin.activeRegions.add(cuboid);
            plugin.activeArenas.add(arena);

        }
    }

    public Set<Cuboid> getActiveRegions() {
        return plugin.activeRegions;

    }

    public String getActiveRegionsAsString() {

        for (Cuboid c : plugin.activeRegions) {
            String regionID = c.getID();
            return regionID + ", ";
        }

        return null;
    }

}
