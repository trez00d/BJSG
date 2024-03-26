package net.berryjar.bjsg.listener;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.ArenaManager;
import net.berryjar.bjsg.arena.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.UUID;

public class DamageListener implements Listener {

    private final BJSG plugin;

    public DamageListener(final BJSG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerDamageEvent(EntityDamageByEntityEvent event) {

        Player attacker = (Player) event.getDamager();
        ArenaManager arenaManager = new ArenaManager(plugin);
        Arena playerArena = arenaManager.getArena(attacker.getUniqueId());

        event.setCancelled(playerArena.getState() == GameState.LOBBY || playerArena.getState() == GameState.PREGAME || playerArena.getState() == GameState.POSTGAME);

//        for (Arena a : plugin.getActiveArenas()) {
//            System.out.println("damage2");
//            if (a.getState() == GameState.LOBBY || a.getState() == GameState.PREGAME || a.getState() == GameState.POSTGAME) {
//                for (UUID u : a.getPlayers()) {
//                    System.out.println("damage3");
//                    if (a.getPlayers().contains(u)) {
//                        if (a.getState() == GameState.LOBBY || a.getState() == GameState.PREGAME || a.getState() == GameState.POSTGAME) {
//                            System.out.println("damage5");
//                            event.setCancelled(true);
//                        } else {
//                            System.out.println("damage4");
//                            return;
//                        }
//                    }
//                }
//            }
//        }

    }

}
