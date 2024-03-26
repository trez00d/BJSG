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

    public void removeArena(Arena arena) {

    }

    public void loadArenaSpawns(FileConfiguration file) {
        ConfigurationSection spawnsSec = file.getConfigurationSection("spawns");
//        System.out.println("spawn load " + spawnsSec.getKeys(false));


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
                        System.out.println(spawnLoc);
                        System.out.println("arena spawn " + a.getId());
                        System.out.println("arena spawn " + arenaID);
                        a.addSpawn(spawnID, spawnLoc);
                        plugin.arenaSpawns.put(arenaID, a.getSpawns());
                        System.out.println(String.valueOf(spawnID) + spawnLoc);
//                        System.out.println(a.getSpawns());
                    }
                }



//                System.out.println("resulting location from load of arena " + key + ": " + spawnLoc.getWorld() + ", " + spawnID + ", " + spawnLoc);

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

//                ConfigurationSection atts = arenaNameSection.getConfigurationSection(key1);
//                world = Bukkit.getWorld(atts.getString("world"));
//                x = atts.getInt("x");
//                y = atts.getInt("y");
//                z = atts.getInt("z");
//                pitch = (float) atts.getDouble("pitch");
//                yaw = (float) atts.getDouble("yaw");

            }
            lobbyLoc = new Location(world, x, y, z, yaw, pitch);
//            System.out.println("lobby load " + arenaID + " " + lobbyLoc);
            for (Arena a : plugin.activeArenas) {
                if (a.getId().equals(arenaID)) {
                    a.setLobby(lobbyLoc);
                    plugin.arenaLobbies.put(arenaID, lobbyLoc);
                }

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

//            System.out.println(lobbyLoc);

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
