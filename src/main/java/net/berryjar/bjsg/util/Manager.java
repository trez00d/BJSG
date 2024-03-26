package net.berryjar.bjsg.util;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.player.SGPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

public class Manager {

    private final BJSG plugin;


    public final static Location SPAWN_POINT = new Location(Bukkit.getWorld("world"), 0.0, 10.0, 0.0);

    public static void setup() {
        /*
         * Create 10 new arena objects (you could obviously replace this number
         * with a number from an configuration file etc.), it is just for the
         * sake of this tutorial.
         */

//        File folder = new File(Bukkit.getServer().getPluginManager().getPlugin("BerryJarHubWitDI").getDataFolder(), "users");
//        File[] directoryListing = folder.listFiles();
//        if (directoryListing != null) {
//            for (File child : directoryListing) {
//                ConfigManager config = new ConfigManager(child.getName().replace(".yml", ""));
//
//            }
//        }

//        for (int i = 1; i <= 10; i++) {
//            // Create a new arena object with the id from the value of integer i
//            // from the current iteration of the for loop.
//            new Arena(i, new Location(Bukkit.getWorld("world"), 50.0, 10.0,
//                    50.0) /*
//             * Obviously change this location. This location is
//             * for the purposes of this tutorial only. You might
//             * want to read yours from a config file.
//             */);
//            // The arena is added to the arenas list in the constructor of the
//            // arena class.
//        }
    }

    private static Manager manager;

    // Constructor with private visibility prevents class being instantiated
    // outside of this class.
    public Manager(final BJSG plugin) {
        this.plugin = plugin;
    }

    public void addArena(Arena arena) {
        plugin.getActiveArenas().add(arena);
    }

    public void removeArena(Arena arena) {
        plugin.getActiveArenas().remove(arena);
    }

    public Arena getArena(String id) {
        for (int i = 0; i < plugin.getActiveArenas().size(); i++) {
            final Arena arena = plugin.getActiveArenas().get(i);

            if (arena.getId().equals(id)) {
                return arena;
            }
        }
        return null;
    }

    public boolean isArena(String id) {
        return getArena(id) != null;
    }

    public Arena getArena(UUID player) {
        for (int i = 0; i < plugin.getActiveArenas().size(); i++) {
            final Arena arena = plugin.getActiveArenas().get(i);

            if (arena.contains(player)) {
                return arena;
            }
        }
        return null;
    }

}
