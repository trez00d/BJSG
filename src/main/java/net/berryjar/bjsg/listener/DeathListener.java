package net.berryjar.bjsg.listener;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.ArenaManager;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.player.SGPlayer;
import net.berryjar.bjsg.util.Helper;
import net.berryjar.bjsg.util.Manager;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.ChatColor;
import org.checkerframework.checker.units.qual.A;

import java.util.UUID;

public class DeathListener implements Listener {

    private final BJSG plugin;
    private SGPlayer sgPlayer;

    public DeathListener(final BJSG plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayerDeath(EntityDamageEvent event) {


        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        Player killer = (Player) ((Player) event.getEntity()).getKiller();
        ArenaManager arenaManager = new ArenaManager(plugin);
        Arena playerArena = arenaManager.getArena(player.getUniqueId());

        if (playerArena == null) {
            return;
        }
        if (player.getHealth() - event.getFinalDamage() <= 0.0D) {
            playerArena.removePlayer(player.getUniqueId());
            playerArena.addDeadPlayer(player.getUniqueId());
            player.setGameMode(GameMode.SPECTATOR);
            playerArena.broadcast(player.getName() + " was killed by " + killer.getName() + "!");
            playerArena.getWorld().strikeLightningEffect(player.getLocation());
            playerArena.playSound(Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 1);
        }

    }

}
