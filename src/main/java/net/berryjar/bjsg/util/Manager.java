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
