package net.berryjar.bjsg.arena;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.chat.ChatHandler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;

public class SpawnCreator {

    private final BJSG plugin;
    public SpawnCreator(final BJSG plugin) {
        this.plugin = plugin;
    }

    public void createSpawn(Location loc, String arenaID, int spawnID) {
        for (Arena a : plugin.activeArenas) {
            if (a.getId().equals(arenaID)) {
                a.addSpawn(spawnID, loc);
                Location spawnLoc = a.getSpawns().get(spawnID);
                String world = loc.getWorld().getName();
                double x = loc.getX();
                double y = loc.getY();
                double z = loc.getZ();
                float pitch = loc.getPitch();
                float yaw = loc.getYaw();
                LinkedHashMap<Integer, Location> arenaSpawn = a.getSpawns();
                plugin.arenaSpawns.put(arenaID, arenaSpawn);
                plugin.getConfig().set("spawns." + arenaID + "." + spawnID + ".world", world);
                plugin.getConfig().set("spawns." + arenaID + "." + spawnID + ".x", x);
                plugin.getConfig().set("spawns." + arenaID + "." + spawnID + ".y", y);
                plugin.getConfig().set("spawns." + arenaID + "." + spawnID + ".z", z);
                plugin.getConfig().set("spawns." + arenaID + "." + spawnID + ".pitch", pitch);
                plugin.getConfig().set("spawns." + arenaID + "." + spawnID + ".yaw", yaw);
                plugin.saveConfig();
            }
        }
//
    }

    public void removeSpawn(String arenaID, int spawnInc) {
        for (Arena a : plugin.activeArenas) {
            if (a.getId().equals(arenaID)) {
                for (String ID : plugin.getConfig().getConfigurationSection("spawns").getKeys(false)) {
                    for (String num : plugin.getConfig().getConfigurationSection("spawns." + ID).getKeys(false)) {
                        int numasnum = Integer.parseInt(num);
                        if (numasnum == spawnInc) {
                            plugin.getConfig().set("spawns." + ID + "." + num, null);
                        }
                    }
                }
                plugin.saveConfig();
            }
        }
//
    }


    public void createLobby(Location loc, String arenaID) {
        for (Arena a : plugin.activeArenas) {
            if (a.getId().equals(arenaID)) {
                a.lobbyLocation = loc;
            }
        }
        plugin.arenaLobbies.put(arenaID, loc);

    }

}
