package net.berryjar.bjsg.listener;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    private final BJSG plugin;

    public DamageListener(final BJSG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PlayerDamageEvent(EntityDamageByEntityEvent event) {

        Player attacker = (Player) event.getDamager();
        for (Arena a : plugin.activeArenas) {
            if (a.getState() == GameState.LOBBY || a.getState() == GameState.PREGAME || a.getState() == GameState.POSTGAME) {
                event.setCancelled(true);
            }
        }

    }

}
