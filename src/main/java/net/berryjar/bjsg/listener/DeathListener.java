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



//        for (Arena arena : plugin.getActiveArenas()) {
//            System.out.println("DeathListener 37");
//            UUID u = player.getUniqueId();
//            if (arena.getPlayers().contains(u)) {
//                System.out.println("DeathListener 47");
//                if(player.getHealth() - event.getFinalDamage() <= 0.0D) {
//                    System.out.println("DeathListener 49");
//                    arena.removePlayer(player.getUniqueId());
//                    System.out.println("DeathListener 51");
//                    arena.deadPlayers.add(player.getUniqueId());
//                    System.out.println("DeathListener 53");
//                    player.setGameMode(GameMode.SPECTATOR);
//                    System.out.println("DeathListener 55");
//                    arena.broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + player.getName() + " was " + ChatColor.RED + "killed" + ChatColor.GREEN + " by " + killer.getName() + "!");
//                    System.out.println("DeathListener 57");
//                    arena.getArenaRegion().getWorld().strikeLightningEffect(player.getLocation());
//                    System.out.println("DeathListener 59");
//                    arena.playSound(Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 1);
//                    System.out.println("DeathListener 61");
//                }
//                System.out.println("DeathListener 63");
//                return;
//            }
//            System.out.println("DeathListener 65");
//            return;
//
//
//
//        }
        System.out.println("DeathListener 71");
//        if (arena.getPlayers().contains(p.getUniqueId())) {
//            if (p.getHealth() - event.getFinalDamage() <= 0.0D) {
////            p.setGameMode(GameMode.SPECTATOR);
////        }
//            }
//        }
//
    }
//    @EventHandler
//    public void onPlayerDeath(PlayerDeathEvent event) {
//        Player player = event.getEntity();
//        event.setDeathMessage(null);
//        Manager manager = new Manager(plugin);
//
//        Arena arena = manager.getArena(player.getUniqueId());
//        if (arena != null) {
//            // Remove the player from the arena.
////
//            arena.removePlayer(player.getUniqueId());
//            arena.deadPlayers.add(player.getUniqueId());
////            player.setGameMode(GameMode.SPECTATOR);
//
//            if (player.getKiller() != ) {
//                Player killer = player.getKiller();
//                arena.broadcast(ChatHandler.chatPrefix + ChatColor.RED + player.getName() + " was killed by " + killer.getName() + ".");
//                arena.getArenaRegion().getWorld().strikeLightningEffect(player.getLocation());
//                arena.playSound(Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 1);
//
//            } else {
//                // You could create death messages here depending on the cause
//                // of the death, but I'm not going to do that in this tutorial.
//                arena.broadcast(ChatHandler.chatPrefix + ChatColor.RED + player.getName() + " died.");
//                arena.getArenaRegion().getWorld().strikeLightningEffect(player.getLocation());
//                arena.playSound(Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 1);
//            }
//
//            // Stop the player from dropping any items.
//
//            Helper helper = new Helper(plugin);
////            helper.teleportToSpawn(player);
//            Helper.clearInventoryAndEffects(player);
//
//        }
//
//    }

}
