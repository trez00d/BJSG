package net.berryjar.bjsg.listener;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.player.SGPlayer;
import net.berryjar.bjsg.util.Manager;
import org.bukkit.ChatColor;
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
        plugin.peelLooseWrapper(event.getPlayer().getUniqueId());
        Manager manager = new Manager(plugin);
        Arena arena = manager.getArena(player);
        if (arena != null) {
            arena.removePlayer(player);
            arena.broadcast(ChatHandler.chatPrefix + ChatColor.RED + event.getPlayer().getName() + " left the server, so has been removed from the arena.");
        }
    }

}