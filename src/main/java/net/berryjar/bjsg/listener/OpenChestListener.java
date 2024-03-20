package net.berryjar.bjsg.listener;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.GameState;
import net.berryjar.bjsg.chest.ChestManager;
import net.berryjar.bjsg.player.SGPlayer;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;

public class OpenChestListener implements Listener {

    private final BJSG plugin;
    public OpenChestListener(final BJSG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChestOpen(InventoryOpenEvent event) {
        System.out.println("LISTENER TEST");

        InventoryHolder holder = event.getInventory().getHolder();

        for (Arena a : plugin.activeArenas) {
            if (a.getState() == GameState.INGAME || a.getState() == GameState.PREDEATHMATCH || a.getState() == GameState.DEATHMATCH) {
                if (holder instanceof Chest) {
                    Chest chest = (Chest) holder;
                    ChestManager chestManager = new ChestManager(plugin.getConfig());
                    if (chestManager.hasBeenOpened(chest.getLocation())) return;
                    chestManager.markAsOpened(chest.getLocation());
                    chestManager.fill(chest.getBlockInventory());
                }
            }
        }

    }
}
