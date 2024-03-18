package net.berryjar.bjsg.listener;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.util.Helper;
import net.berryjar.bjsg.util.Manager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.ChatColor;

public class DeathListener implements Listener {

    private final BJSG plugin;

    public DeathListener(final BJSG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        event.setDeathMessage(null);
        Manager manager = new Manager(plugin);

        Arena arena = manager.getArena(player.getUniqueId());
        if (arena != null) {
            // Remove the player from the arena.
            arena.removePlayer(player.getUniqueId());

            if (player.getKiller() != null) {
                Player killer = player.getKiller();
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.RED + player.getName() + " was killed by " + killer.getName() + ".");
            } else {
                // You could create death messages here depending on the cause
                // of the death, but I'm not going to do that in this tutorial.
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.RED + player.getName() + " died.");
            }

            // Stop the player from dropping any items.
            event.getDrops().clear();

            Helper.teleportToSpawn(player);
            Helper.clearInventoryAndEffects(player);
        }
    }

}
