package net.berryjar.bjsg.util;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class Helper {

    public static void teleportToSpawn(Player player) {
        player.teleport(Manager.SPAWN_POINT);
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
