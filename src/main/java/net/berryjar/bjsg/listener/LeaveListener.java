package net.berryjar.bjsg.listener;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.player.SGPlayer;
import net.berryjar.bjsg.util.Manager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class LeaveListener implements Listener {
    private final BJSG plugin;
    private UUID player;

    public LeaveListener(final BJSG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        for(Arena a : plugin.getActiveArenas()) {
            if (a.getPlayers().contains(event.getPlayer().getUniqueId())) {
                a.removePlayer(event.getPlayer().getUniqueId());
                plugin.sgPlayers.remove(event.getPlayer().getUniqueId());
                a.broadcast(ChatHandler.chatPrefix + ChatColor.RED + Bukkit.getPlayer(player).getName() + " left the game.");
                a.getArenaRegion().getWorld().strikeLightningEffect(Bukkit.getPlayer(player).getLocation());
                a.playSound(Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 1);
                event.setQuitMessage(null);
            }
            if (a.deadPlayers.contains(event.getPlayer().getUniqueId())) {
                a.removePlayer(event.getPlayer().getUniqueId());
                plugin.sgPlayers.remove(event.getPlayer().getUniqueId());

                event.setQuitMessage(null);
            }
        }
    }

}