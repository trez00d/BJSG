package net.berryjar.bjsg.arena;

import net.berryjar.bjsg.BJSG;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SpawnCreator {

    private final BJSG plugin;

    public SpawnCreator(final BJSG plugin) {
        this.plugin = plugin;
    }

    public Location createSpawn(Location loc, String arenaID) {
        for (Arena a : plugin.activeArenas) {
            if (a.getId().equals(arenaID)) {
                int i = a.getLastSpawn();
                a.addSpawn(i, loc);
            }
        }
        return null;
    }

}
