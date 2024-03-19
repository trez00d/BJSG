package net.berryjar.bjsg.listener;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
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

public class PreventionListener implements Listener {

    private final BJSG plugin;
    public PreventionListener(final BJSG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location action = event.getFrom();
        for (Arena a : plugin.activeArenas) {
            if (a.getState() == GameState.PREGAME) {
                if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY() || event.getFrom().getZ() != event.getTo().getZ()) {
                    event.setCancelled(true);
                    player.teleport(action);
                }

            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }
    }

//    @EventHandler
//    public void onItemDrop(PlayerDropItemEvent event) {
//        Player player = event.getPlayer();
//        if (player.getGameMode() != GameMode.CREATIVE) {
//            event.setCancelled(true);
//        }
//    }

//    @EventHandler
//    public void onItemPickup(PlayerPickupItemEvent event) {
//        Player player = event.getPlayer();
//        if (player.getGameMode() != GameMode.CREATIVE) {
//            event.setCancelled(true);
//        }
//    }
//
//    @EventHandler
//    public void onFoodLevelChange(FoodLevelChangeEvent event) {
//        event.setCancelled(true);
//    }

}
