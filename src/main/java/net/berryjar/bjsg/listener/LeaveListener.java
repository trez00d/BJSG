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

public class LeaveListener implements Listener {
    private final BJSG plugin;
    private SGPlayer sgPlayer;

    public LeaveListener(final BJSG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        plugin.peelLooseWrapper(event.getPlayer().getUniqueId());
        sgPlayer = sgPlayer.getSGPlayer(event.getPlayer().getUniqueId());
        Manager manager = new Manager(plugin);
        Arena arena = manager.getArena(sgPlayer);
        if (arena != null) {
            arena.removePlayer(sgPlayer);
            arena.broadcast(ChatHandler.chatPrefix + ChatColor.RED + event.getPlayer().getName() + " left the server, so has been removed from the arena.");
        }
    }

}