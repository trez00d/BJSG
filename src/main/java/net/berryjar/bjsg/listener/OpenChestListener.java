package net.berryjar.bjsg.listener;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.GameState;
import net.berryjar.bjsg.chest.ChestManager;
import net.berryjar.bjsg.player.SGPlayer;
import org.bukkit.block.Chest;
import org.bukkit.entity.HumanEntity;
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

    @EventHandler()
    public void onPlayerChestOpen(InventoryOpenEvent event) {
        System.out.println("LISTENER TEST");
        ChestManager chestManager = new ChestManager(plugin.getConfig());
        InventoryHolder holder = event.getInventory().getHolder();
        Chest chest = (Chest) holder;
        for (Arena a : plugin.getActiveArenas()) {
            if (a.getState() == GameState.INGAME || a.getState() == GameState.PREDEATHMATCH || a.getState() == GameState.DEATHMATCH) {
                Player player = (Player) event.getPlayer();
                if (a.getPlayers().contains(player.getUniqueId())) {
                    if (holder instanceof Chest) {
                        if (chestManager.hasBeenOpened(a, chest.getLocation())) {
                            return;
                        }
                        chestManager.markAsOpened(a, chest.getLocation());
                        System.out.println("has been opened " + a.openedChests);
                        chestManager.fill(chest.getBlockInventory());
                    }
                }

            }
        }

    }
}
