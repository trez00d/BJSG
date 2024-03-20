package net.berryjar.bjsg.arena;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.cuboid.Cuboid;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.Bukkit;

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


    public void loadArenaSpawns() {

        System.out.println("0");
        for (String arenaID : plugin.getConfig().getConfigurationSection("spawns").getKeys(false)) {
            System.out.println("1");
            for (String spawnNum : plugin.getConfig().getConfigurationSection("spawns." + arenaID).getKeys(false)) {
                System.out.println("2");
                for (String atts : plugin.getConfig().getConfigurationSection("spawns." + arenaID + "." + spawnNum).getKeys(false)) {
                    System.out.println("3");
                    String stringWorld = plugin.getConfig().getString("spawns." + arenaID + "." + spawnNum + ".world");
                    World world = Bukkit.getWorld(stringWorld);
                    int x = plugin.getConfig().getInt("spawns." + arenaID + "." + spawnNum + ".x");
                    System.out.println(x);
                    int y = plugin.getConfig().getInt("spawns." + arenaID + "." + spawnNum + ".y");
                    System.out.println(y);
                    int z = plugin.getConfig().getInt("spawns." + arenaID + "." + spawnNum + ".z");
                    System.out.println(z);
                    float pitch = (float) plugin.getConfig().getDouble("regions." + arenaID + "." + spawnNum + ".pitch");
                    float yaw = (float) plugin.getConfig().getDouble("regions." + arenaID + "." + spawnNum + ".yaw");
                    Location spawnLoc = new Location(world, x, y, z, yaw, pitch);
                    System.out.println(spawnLoc);

                    for (Arena a : plugin.activeArenas) {
                        if (a.getId().equals(arenaID)) {
                            int sID = Integer.parseInt(spawnNum);
                            a.getSpawns().put(sID, spawnLoc);
                            System.out.println(a.getSpawns() + "being sent to arena map");


                        }
                    }
                    break;

                }

            }


        }
        for (String arenaID : plugin.getConfig().getConfigurationSection("lobby").getKeys(false)) {
            System.out.println("1");
            for (String spawnNum : plugin.getConfig().getConfigurationSection("lobby." + arenaID).getKeys(false)) {
                String stringWorld = plugin.getConfig().getString("lobby." + arenaID + ".world");
                World world = Bukkit.getWorld(stringWorld);
                int x = plugin.getConfig().getInt("lobby." + arenaID + "." + ".x");
                int y = plugin.getConfig().getInt("lobby." + arenaID + "." + ".y");
                int z = plugin.getConfig().getInt("lobby." + arenaID + "." + ".z");
                float pitch = (float) plugin.getConfig().getDouble("lobby." + arenaID + "." + ".pitch");
                float yaw = (float) plugin.getConfig().getDouble("lobby." + arenaID + "." + ".yaw");
                Location spawnLoc = new Location(world, x, y, z, yaw, pitch);

                for (Arena a : plugin.activeArenas) {
                    if (a.getId().equals(arenaID)) {
                        a.getLobbySpawnMap().clear();
                        a.getLobbySpawnMap().add(spawnLoc);
                    }
                }
                break;

//                System.out.println("2");
//                for (String atts : plugin.getConfig().getConfigurationSection("lobby." + arenaID + ".").getKeys(false)) {
//
//                }

            }


        }
    }

}
