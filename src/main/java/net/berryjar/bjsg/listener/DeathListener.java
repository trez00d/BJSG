package net.berryjar.bjsg.listener;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.player.SGPlayer;
import net.berryjar.bjsg.util.Helper;
import net.berryjar.bjsg.util.Manager;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.ChatColor;

public class DeathListener implements Listener {

    private final BJSG plugin;
    private SGPlayer sgPlayer;

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
            arena.deadPlayers.add(player.getUniqueId());
            player.setGameMode(GameMode.SPECTATOR);

            if (player.getKiller() != null) {
                Player killer = player.getKiller();
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.RED + player.getName() + " was killed by " + killer.getName() + ".");
                arena.getArenaRegion().getWorld().strikeLightningEffect(player.getLocation());
                arena.playSound(Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 1);
            } else {
                // You could create death messages here depending on the cause
                // of the death, but I'm not going to do that in this tutorial.
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.RED + player.getName() + " died.");
                arena.getArenaRegion().getWorld().strikeLightningEffect(player.getLocation());
                arena.playSound(Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 1);
            }

            // Stop the player from dropping any items.

            Helper helper = new Helper(plugin);
            helper.teleportToSpawn(player);
            Helper.clearInventoryAndEffects(player);

        }
    }

}
