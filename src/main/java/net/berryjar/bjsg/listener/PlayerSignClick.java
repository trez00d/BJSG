package net.berryjar.bjsg.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.material.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerSignClick implements Listener {

    @EventHandler
    public void onSignClick(PlayerInteractEvent e) {

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        Player p = e.getPlayer();
        if (p.hasPermission("sign.use")) {

            Block b = e.getClickedBlock();
            if (b.getType() == Material.OAK_SIGN || b.getType() == Material.OAK_WALL_SIGN || b.getType() == Material.OAK_HANGING_SIGN) {

                Sign sign = (Sign) b.getState();
                if (ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase("[SG]")) {
                    if(sign.getLine(2) != null && !sign.getLine(2).equals("")) {

                        String warp = ChatColor.stripColor(sign.getLine(2));
                        Bukkit.dispatchCommand(p, "sg join " + warp);

                    }
                }
            }

        }

    }




}
