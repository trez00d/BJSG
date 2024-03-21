package net.berryjar.bjsg.arena;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.cuboid.Cuboid;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.LinkedHashMap;
import java.util.Map;

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

    public int spawnNum;

//    public void loadArenaSpawns(ConfigurationSection section) {
//        for (String spawnNum : section.getKeys(false)) {
//
//            World world = Bukkit.getWorld(section.getString("world"));
//            int x = section.getInt("x");
//            int y = section.getInt("y");
//            int z = section.getInt("z");
//            float pitch = (float) section.getDouble("pitch");
//            float yaw = (float) section.getDouble("yaw");
//            Location spawnLoc = new Location(world, x, y, z, yaw, pitch);
//
//            for (Arena a : plugin.activeArenas) {
//                if (!(a.getId().equals(section.toString()))) {
//                    return;
//                }
//                int sID = Integer.parseInt(spawnNum);
//                a.getSpawns().put(sID, spawnLoc);
//            }
//        }
//    }

    public void loadArenaSpawns(FileConfiguration file) {
        ConfigurationSection spawnsSec = file.getConfigurationSection("spawns");
        System.out.println(spawnsSec.getKeys(false));
        for (String arenaID : spawnsSec.getKeys(false)) {
            ConfigurationSection arenas = spawnsSec.getConfigurationSection(arenaID);
            for (String spawnID : arenas.getKeys(false)) {
                ConfigurationSection spawnIDs = arenas.getConfigurationSection(spawnID);
                System.out.println("spawnID " + spawnID);
                String stringWorld = (spawnIDs.getString("world"));
                World world = Bukkit.getWorld(stringWorld);
                int x = spawnIDs.getInt("x");
                int y = spawnIDs.getInt("y");
                int z = spawnIDs.getInt("z");
                float pitch = (float) spawnIDs.getDouble("pitch");
                float yaw = (float) spawnIDs.getDouble("yaw");
                Location loc = new Location(world, x, y, z, yaw, pitch);
                for (Arena a : plugin.activeArenas) {
                    a.addSpawns(Integer.valueOf(spawnID), loc);
                    System.out.println(loc);
                }
                System.out.println("arenas " + arenas);

//            Location spawnLoc = new Location(world, x, y, z, yaw, pitch);
//            System.out.println(spawnLoc);
//            for (String spawnID : arenas.getKeys(true)) {
//                ConfigurationSection spawnsID = arenas.getConfigurationSection(spawnID);
//                System.out.println(spawnsID);
////                for (String atts : spawnsID.getKeys(false)) {
////                    World world = Bukkit.getWorld(spawnsID.getString("world"));
////                    int x = spawnsID.getInt("x");
////                    int y = spawnsID.getInt("y");
////                    int z = spawnsID.getInt("z");
////                    float pitch = (float) spawnsID.getDouble("pitch");
////                    float yaw = (float) spawnsID.getDouble("yaw");
////
////                    Location spawnLoc = new Location(world, x, y, z, yaw, pitch);
////                    for (Arena a : plugin.activeArenas) {
////                        a.addSpawns(Integer.valueOf(spawnID), spawnLoc);
////                        System.out.println(a.getSpawns());
////                        LinkedHashMap<Integer, Location> spawnsMap = a.getSpawns();
////                        System.out.println(spawnsMap);
//////                    for (Integer.valueOf(spawnID) : a.spawns.keySet()) {
//////
//////                        System.out.println(id);
//////                    }
//////                    System.out.println("Sanity A" + a.getId());
//////                    System.out.println("Sanity B " + spawnID);
//////                    if (a.getId().equals(arenaID)) {
//////                        a.spawns.put(Integer.valueOf(spawnID), spawnLoc);
//////                        System.out.println("Sanity" + a.getSpawns());
////////                        return;
//////                    }
//////                        a.spawns.put(Integer.valueOf(spawnID), spawnLoc)
////                    }
////
////
////                }
////                World world = builder.getWorldWorld();
////                int x = builder.getX();
////                int y = builder.getY();
////                int z = builder.getZ();
////                float pitch = (float) builder.getPitch();
////                float yaw = (float) builder.getYaw();
////
////                System.out.println(world + " " + x + " " + y + " " + z + " " + yaw + " " + pitch);
//
//            }
            }



        }
    }

    public void loadLobbySpawns(FileConfiguration file) {
        ConfigurationSection lobbySec = file.getConfigurationSection("lobby");
        for (String arenaID : lobbySec.getKeys(false)) {
            ConfigurationSection arenas = lobbySec.getConfigurationSection(arenaID);
            World world = Bukkit.getWorld(arenas.getString("world"));
            int x = arenas.getInt("x");
            int y = arenas.getInt("y");
            int z = arenas.getInt("z");
            float pitch = (float) arenas.getDouble("pitch");
            float yaw = (float) arenas.getDouble("yaw");
            Location lobbyLoc = new Location(world, x, y, z, yaw, pitch);
//            System.out.println(lobbyLoc);
            for (Arena a : plugin.activeArenas) {
                a.setLobby(lobbyLoc);
                plugin.arenaLobbies.put(arenaID, lobbyLoc);
//                System.out.println("arenamanager print arenalobbies after putting in");
//                System.out.println(plugin.arenaLobbies);

//            for (Arena a : plugin.activeArenas) {
//                if (a.getId().equals(arenaID)) {
//                    a.setLobby(lobbyLoc);
//                    plugin.arenaLobbies.put(arenaID, lobbyLoc);
//                    System.out.println("arenamanager print arenalobbies after putting in");
//                } else return;
//

            }
//            for (String spawnID : arenas.getKeys(false)) {
//                ConfigurationSection spawnsID = arenas.getConfigurationSection(spawnID);
//                World world = Bukkit.getWorld(spawnsID.getString("world"));
//                int x = spawnsID.getInt("x");
//                int y = spawnsID.getInt("y");
//                int z = spawnsID.getInt("z");
//                float pitch = (float) spawnsID.getDouble("pitch");
//                float yaw = (float) spawnsID.getDouble("yaw");
//                Location lobbyLoc = new Location(world, x, y, z, yaw, pitch);
//                for (Arena a : plugin.activeArenas) {
//                    if (!(a.getId().equals(spawnsID.toString()))) {
//                        return;
//                    }
//                    a.getLobbySpawnMap().clear();
//                    a.getLobbySpawnMap().add(lobbyLoc);
//                }
//            }
        }
    }

//    public void loadArenaSpawns() {
//
//        System.out.println("0");
//        for (String arenaID : plugin.getConfig().getConfigurationSection("spawns").getKeys(false)) {
//            for (String spawnNum : plugin.getConfig().getConfigurationSection("spawns." + arenaID).getKeys(false)) {
//                System.out.println("2");
//                for (String atts : plugin.getConfig().getConfigurationSection("spawns." + arenaID + "." + spawnNum).getKeys(false)) {
//                    System.out.println("3");
//                    String stringWorld = plugin.getConfig().getString("spawns." + arenaID + "." + spawnNum + ".world");
//                    World world = Bukkit.getWorld(stringWorld);
//                    int x = plugin.getConfig().getInt("spawns." + arenaID + "." + spawnNum + ".x");
//                    System.out.println(x);
//                    int y = plugin.getConfig().getInt("spawns." + arenaID + "." + spawnNum + ".y");
//                    System.out.println(y);
//                    int z = plugin.getConfig().getInt("spawns." + arenaID + "." + spawnNum + ".z");
//                    System.out.println(z);
//                    float pitch = (float) plugin.getConfig().getDouble("regions." + arenaID + "." + spawnNum + ".pitch");
//                    float yaw = (float) plugin.getConfig().getDouble("regions." + arenaID + "." + spawnNum + ".yaw");
//                    Location spawnLoc = new Location(world, x, y, z, yaw, pitch);
//                    System.out.println(spawnLoc);
//
//                    for (Arena a : plugin.activeArenas) {
//                        if (a.getId().equals(arenaID)) {
//                            int sID = Integer.parseInt(spawnNum);
//                            a.getSpawns().put(sID, spawnLoc);
//                            System.out.println(a.getSpawns() + "being sent to arena map");
//
//
//                        }
//                    }
//                    break;
//
//                }
//
//            }
//
//
//        }
//        for (String arenaID : plugin.getConfig().getConfigurationSection("lobby").getKeys(false)) {
//            System.out.println("1");
//            for (String spawnNum : plugin.getConfig().getConfigurationSection("lobby." + arenaID).getKeys(false)) {
//                String stringWorld = plugin.getConfig().getString("lobby." + arenaID + ".world");
//                World world = Bukkit.getWorld(stringWorld);
//                int x = plugin.getConfig().getInt("lobby." + arenaID + "." + ".x");
//                int y = plugin.getConfig().getInt("lobby." + arenaID + "." + ".y");
//                int z = plugin.getConfig().getInt("lobby." + arenaID + "." + ".z");
//                float pitch = (float) plugin.getConfig().getDouble("lobby." + arenaID + "." + ".pitch");
//                float yaw = (float) plugin.getConfig().getDouble("lobby." + arenaID + "." + ".yaw");
//                Location spawnLoc = new Location(world, x, y, z, yaw, pitch);
//
////                for (Arena a : plugin.activeArenas) {
////                    if (a.getId().equals(arenaID)) {
////                        a.getLobbySpawnMap().clear();
////                        a.getLobbySpawnMap().add(spawnLoc);
////                    }
////                }
//                break;
//
////                System.out.println("2");
////                for (String atts : plugin.getConfig().getConfigurationSection("lobby." + arenaID + ".").getKeys(false)) {
////
////                }
//
//            }
//
//
//        }
//    }

}
