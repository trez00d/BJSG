package net.berryjar.bjsg.arena;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.cuboid.Cuboid;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class ArenaManager {

    private ArenaBuilder builder = new ArenaBuilder();

    private ArenaManager arenaManager;
    private final BJSG plugin;


    public ArenaManager(final BJSG plugin) {
        this.plugin = plugin;
    }

    public String getArenaID(Arena arena) {

        return arena.getId();
    }

    public Arena getArena(UUID uuid) {
        for (Arena a : getArenas()) {
            if (a.getPlayers().contains(uuid)) {
                return a;
            }
        }
        return null;
    }

    public ArrayList<Arena> getArenas() {
        return plugin.activeArenas;

    }
    public Arena getArenaByID(String arenaID) {
        for (Arena a : plugin.activeArenas) {
            if (a.getId().equals(arenaID)) {
                return a;
            }
        }
        return null;
    }
    public Arena getStoppedArenaByID(String arenaID) {
        for (Arena a : plugin.arenaCache) {
            if (a.getId().equalsIgnoreCase(arenaID)) {
                return a;
            }
        }
        return null;
    }

    public int spawnNum;


    public void removeArena(Arena arena) {

    }

    public void loadArenaSpawns(FileConfiguration file) {
        ConfigurationSection spawnsSec = file.getConfigurationSection("spawns");


        for (String key : spawnsSec.getKeys(false)) {
            String arenaID = null;
            String stringWorld = null;
            int spawnID = 0;
            double x = 0;
            double y = 0;
            double z = 0;
            float pitch = 0;
            float yaw = 0;
            Location spawnLoc = null;
            arenaID = key;
            ConfigurationSection arenaNameSection = spawnsSec.getConfigurationSection(key);
            for (String keys1 : arenaNameSection.getKeys(false)) {
                spawnID = Integer.parseInt(keys1);
                ConfigurationSection spawnIDs = arenaNameSection.getConfigurationSection(keys1);
                for (String keys2 : spawnIDs.getKeys(false)) {
                    ConfigurationSection atts = spawnIDs.getConfigurationSection(keys2);
                    stringWorld = spawnIDs.getString("world");
                    x = spawnIDs.getDouble("x");
                    y = spawnIDs.getDouble("y");
                    z = spawnIDs.getDouble("z");
                    x = spawnIDs.getDouble("x");
                    pitch = (float) spawnIDs.getDouble("pitch");
                    yaw = (float) spawnIDs.getDouble("yaw");


                }
                World world = Bukkit.getWorld(stringWorld);
                spawnLoc = new Location(world, x, y, z, yaw, pitch);
                for (Arena a : plugin.activeArenas) {
                    if (a.getId().equals(arenaID)) {
                        a.addSpawn(spawnID, spawnLoc);
                        plugin.arenaSpawns.put(arenaID, a.getSpawns());
                    }
                }
            }
            for (Arena a : plugin.activeArenas) {
                System.out.println(a.getId() + a.getSpawns());
            }
        }
    }

    public void loadLobbySpawns(FileConfiguration file) {
        ConfigurationSection lobbySec = file.getConfigurationSection("lobby");

        for (String key : lobbySec.getKeys(false)) {
            String arenaID = key;
            World world = null;
            int x = 0;
            int y = 0;
            int z = 0;
            float pitch = 0;
            float yaw = 0;
            Location lobbyLoc = null;
            ConfigurationSection arenaNameSection = lobbySec.getConfigurationSection(key);
            for (String key1 : arenaNameSection.getKeys(false)) {

                world = Bukkit.getWorld(arenaNameSection.getString("world"));
                x = arenaNameSection.getInt("x");
                y = arenaNameSection.getInt("y");
                z = arenaNameSection.getInt("z");
                pitch = (float) arenaNameSection.getDouble("pitch");
                yaw = (float) arenaNameSection.getDouble("yaw");

            }
            lobbyLoc = new Location(world, x, y, z, yaw, pitch);
            for (Arena a : plugin.activeArenas) {
                if (a.getId().equals(arenaID)) {
                    a.setLobby(lobbyLoc);
                    plugin.arenaLobbies.put(arenaID, lobbyLoc);
                }
            }


        }

    }


}
