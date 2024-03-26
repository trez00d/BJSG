package net.berryjar.bjsg.listener;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
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

    private final BJSG plugin;

    public PlayerSignClick(final BJSG plugin) {
        this.plugin = plugin;
    }

//    @EventHandler
//    public void onSignClick(PlayerInteractEvent e) {
//
//        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
//            return;
//        }
//
//        Player p = e.getPlayer();
//        if (p.hasPermission("sign.use")) {
//
//            Block b = e.getClickedBlock();
//            if (b.getType() == Material.OAK_WALL_SIGN) {
//
//                Sign sign = (Sign) b.getState();
//                if (ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase("[SG]")) {
//                    for (Arena a : plugin.activeArenas) {
//                        if(sign.getLine(1) != null && !sign.getLine(1).equals(a.getId())) {
//                            String warp = ChatColor.stripColor(sign.getLine(1));
//                            Bukkit.dispatchCommand(p, "bjsg join " + warp);
//
//                        }
//                    }
//
//                }
//            }
//
//        }
//
//    }




}
