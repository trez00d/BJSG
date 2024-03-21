package net.berryjar.bjsg.listener;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.magicwand.MagicWand;
import net.berryjar.bjsg.magicwand.WandManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerWandInteract implements Listener {

    private final BJSG plugin;

    public PlayerWandInteract(final BJSG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerWandInteractEvent(PlayerInteractEvent event) {
        Action leftClick = event.getAction();
        Action rightClick = event.getAction();
        WandManager wandManager = new WandManager(plugin);

        Player player = event.getPlayer();
        if (leftClick.equals(Action.LEFT_CLICK_BLOCK)) {
            if (event.getHand() == EquipmentSlot.HAND) {
                if (player.getInventory().getItemInMainHand().isSimilar(plugin.getMagicWand())) {
                    event.setCancelled(true);
                    Location blockLoc1 = event.getClickedBlock().getLocation();
                    wandManager.setPositionA(blockLoc1);
                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.LIGHT_PURPLE + "Location 1 set to world: " + blockLoc1.getWorld().getName() + ", X: " + blockLoc1.getBlockX() + ", Y: " + blockLoc1.getBlockY() + ", Z: " + blockLoc1.getBlockZ() + ".");
                }
            }
        } else if (rightClick.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getHand() == EquipmentSlot.HAND) {
                if (player.getInventory().getItemInMainHand().isSimilar(plugin.getMagicWand())) {
                    event.setCancelled(true);
                    Location blockLoc2 = event.getClickedBlock().getLocation();
                    wandManager.setPositionB(blockLoc2);
                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.LIGHT_PURPLE + "Location 2 set to world: " + blockLoc2.getWorld().getName() + ", X: " + blockLoc2.getBlockX() + ", Y: " + blockLoc2.getBlockY() + ", Z: " + blockLoc2.getBlockZ() + ".");
                }


//        if (wandManager.isPlayerPrimed(player)) {
//            if (leftClick.equals(Action.LEFT_CLICK_BLOCK)) {
//                if (event.getHand() == EquipmentSlot.HAND) {
//                    ItemStack mWand = magicWand.getMagicWand();
//                    if (player.getInventory().getItemInMainHand().isSimilar(mWand)) {
//                        event.setCancelled(true);
//                        Location blockLoc1 = event.getClickedBlock().getLocation();
//                        wandManager.setPositionA(blockLoc1);
//                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.LIGHT_PURPLE + "Location 1 set to world: " + blockLoc1.getWorld().getName() + ", X: " + blockLoc1.getBlockX() + ", Y: " + blockLoc1.getBlockY() + ", Z: " + blockLoc1.getBlockZ() + ".");
//                    } else {
//                        event.setCancelled(false);
//                    }
//                }
//            } else if (rightClick.equals(Action.RIGHT_CLICK_BLOCK)) {
//                if (event.getHand() == EquipmentSlot.HAND) {
//                    ItemStack mWand = magicWand.getMagicWand();
//                    if (player.getInventory().getItemInMainHand().isSimilar(mWand)) {
//                        event.setCancelled(true);
//                        Location blockLoc2 = event.getClickedBlock().getLocation();
//                        wandManager.setPositionB(blockLoc2);
//                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.LIGHT_PURPLE + "Location 2 set to world: " + blockLoc2.getWorld().getName() + ", X: " + blockLoc2.getBlockX() + ", Y: " + blockLoc2.getBlockY() + ", Z: " + blockLoc2.getBlockZ() + ".");
//                    } else {
//                        event.setCancelled(false);
//                    }
//                }
//            }
//        } else if (!(wandManager.isPlayerPrimed(player))) {
//            event.setCancelled(false);
//        }
            }
        }

    }
}
