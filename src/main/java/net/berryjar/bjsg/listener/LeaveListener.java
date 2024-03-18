package net.berryjar.bjsg.listener;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.util.Manager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {
    private final BJSG plugin;

    public LeaveListener(final BJSG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Manager manager = new Manager(plugin);


        Arena arena = manager.getArena(player.getUniqueId());
        if (arena != null) {
            arena.removePlayer(player.getUniqueId());
            arena.broadcast(ChatHandler.chatPrefix + ChatColor.RED + player.getName() + " left the server, so has been removed from the arena.");
        }
    }

}