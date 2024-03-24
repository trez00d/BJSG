package net.berryjar.bjsg.cuboid;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

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
        cuboid.setRegionID(regionID);
        Arena arena = new Arena(plugin, cuboid, regionID);
        arena.startArena();
        plugin.activeArenas.add(arena);
        plugin.getConfig().set("regions." + regionID + ".world", blockLoc1.getWorld().getName());
        plugin.getConfig().set("regions." + regionID + ".x1", blockLoc1.getX());
        plugin.getConfig().set("regions." + regionID + ".y1", blockLoc1.getY());
        plugin.getConfig().set("regions." + regionID + ".z1", blockLoc1.getZ());
        plugin.getConfig().set("regions." + regionID + ".x2", blockLoc2.getX());
        plugin.getConfig().set("regions." + regionID + ".y2", blockLoc2.getY());
        plugin.getConfig().set("regions." + regionID + ".z2", blockLoc2.getZ());
        plugin.saveConfig();

    }

    public void loadRegions(FileConfiguration config) {

        ConfigurationSection regionsSection = config.getConfigurationSection("regions");


        for (String key : regionsSection.getKeys(false)) {
            ConfigurationSection cuboidNameSection = regionsSection.getConfigurationSection(key);
            String world = null;
            int x1 = 0;
            int y1 = 0;
            int z1 = 0;
            int x2 = 0;
            int y2 = 0;
            int z2 = 0;


            for (String keys : cuboidNameSection.getKeys(false)) {
                ConfigurationSection atts = cuboidNameSection.getConfigurationSection(keys);
                world = cuboidNameSection.getString("world");
                x1 = cuboidNameSection.getInt("x1");
                y1 = cuboidNameSection.getInt("y1");
                z1 = cuboidNameSection.getInt("z1");
                x2 = cuboidNameSection.getInt("x1");
                y2 = cuboidNameSection.getInt("y1");
                z2 = cuboidNameSection.getInt("z1");

            }
            Cuboid cuboid = new Cuboid(key, world, x1, y1, z1, x2, y2, z2);
            cuboid.setRegionID(key);
            Arena arena = new Arena(plugin, cuboid, key);
            arena.startArena();
            plugin.activeRegions.add(cuboid);

            arena.getLobby().startLobby(15);

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
