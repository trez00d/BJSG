package net.berryjar.bjsg.magicwand;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.chat.ChatHandler;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class WandManager {

    private final BJSG plugin;


    public WandManager(final BJSG plugin) {
        this.plugin = plugin;
    }

    public void setPrimed(Player player) {
        UUID u = player.getUniqueId();
        if (!(plugin.wandPrime.contains(u))) {
            plugin.wandPrime.add(u);
            player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + "You're now in edit mode.");
        } else {
            player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "You're already in edit mode.");
        }
    }

    public void removePrimed(Player player) {
        UUID u = player.getUniqueId();
        if (!(plugin.wandPrime.contains(u))) {
            player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "You're not in edit mode, so you can't exit.");
        } else {
            player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + "You left edit mode.");
            plugin.wandPrime.remove(u);
        }
    }

    public boolean isPlayerPrimed(Player player) {
        UUID u = player.getUniqueId();
        return plugin.wandPrime.contains(u);
    }

    public void setPositionA(Location location) {
        plugin.clipboardMap.setFirst(location);
    }

    public void setPositionB(Location location) {
        plugin.clipboardMap.setSecond(location);
    }

    public Location getPositionA() {
        return plugin.clipboardMap.getFirst();
    }

    public Location getPositionB() {
        return plugin.clipboardMap.getSecond();
    }

}