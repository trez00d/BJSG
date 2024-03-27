package net.berryjar.bjsg.listener;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.ArenaManager;
import net.berryjar.bjsg.arena.GameState;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.UUID;

public class PreventionListener implements Listener {

    private final BJSG plugin;
    public PreventionListener(final BJSG plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        ArenaManager arenaManager = new ArenaManager(plugin);
        Player player = event.getPlayer();
        Location action = event.getFrom();
        if (arenaManager.getArena(player.getUniqueId()).getState() == GameState.PREGAME) {
            if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY() || event.getFrom().getZ() != event.getTo().getZ()) {
                event.setCancelled(true);
                player.teleport(action);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ArenaManager arenaManager = new ArenaManager(plugin);
        if (!((arenaManager.getArena(player.getUniqueId())) == null)) {
            event.setCancelled(true);
        }

        for (Arena a : plugin.arenaCache) {
            if (a.getPlayers().contains(player.getUniqueId()))  {
                event.setCancelled(false);
            }

        }
        return;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ArenaManager arenaManager = new ArenaManager(plugin);

        if (!(arenaManager.getArena(player.getUniqueId()) == null)) {
            event.setCancelled(true);
        }


        for (Arena a : plugin.arenaCache) {
            if (a.getPlayers().contains(player.getUniqueId())) {
                event.setCancelled(false);
            }

        }
    }

}
