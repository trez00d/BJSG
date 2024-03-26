package net.berryjar.bjsg.util;

import net.berryjar.bjsg.BJSG;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.Location;

public class Helper {

    private final BJSG plugin;

    public Helper(final BJSG plugin) {
        this.plugin = plugin;
    }

    public void teleportToSpawn(Player player) {
        Location loc = plugin.playerJoinSGEndTeleport.get(player.getUniqueId());
        player.teleport(loc);
    }

    public static void clearInventoryAndEffects(Player player) {
        clearInventory(player);
        clearPotionEffects(player);
        player.setHealth(20);
        player.setFoodLevel(20);
    }

    public static void clearInventory(Player player) {
        player.getInventory().clear();
        player.getEquipment().clear();
    }

    public static void clearPotionEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }

}
