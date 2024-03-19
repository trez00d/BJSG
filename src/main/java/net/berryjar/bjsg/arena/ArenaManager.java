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

//                    builder.setWorld(world);
//                    builder.setX(x);
//                    builder.setY(y);
//                    builder.setZ(z);
//                    builder.setYaw(yaw);
//                    builder.setPitch(pitch);
//                    World world1 = Bukkit.getWorld(builder.getWorld());
//                    int spawnID = Integer.parseInt(spawnNum);
//                    int x1 = builder.getX();
//                    int y1 = builder.getY();
//                    int z1 = builder.getZ();
//                    float pitch1= (float) builder.getPitch();
//                    float yaw1 = (float) builder.getYaw();
//                    System.out.println(world1);
//                    System.out.println(spawnID);
//                    System.out.println(x1);
//                    System.out.println(y1);
//                    System.out.println(z1);
//                    System.out.println(pitch1);
//                    System.out.println(yaw1);

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


//            String world = builder.getWorld();
//            String rID = builder.getRegionID();
//            int x1 = builder.getX1();
//            int y1 = builder.getY1();
//            int z1 = builder.getZ1();
//            int x2 = builder.getX2();
//            int y2 = builder.getY2();
//            int z2 = builder.getZ2();
//
//            Cuboid cuboid = new Cuboid(rID, world, x1, y1, z1, x2, y2, z2);
//            Arena arena = new Arena(plugin, cuboid, rID);
//            arena.getLobby().startLobby(15);
//            cuboid.setRegionID(rID);
//            plugin.activeRegions.add(cuboid);
//            plugin.activeArenas.add(arena);

        }
    }

}
