package net.berryjar.bjsg.command.subcommand;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.command.SubCommand;
import net.berryjar.bjsg.config.ConfigManager;
import net.berryjar.bjsg.cuboid.CuboidManager;
import net.berryjar.bjsg.magicwand.MagicWand;
import net.berryjar.bjsg.magicwand.WandManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EditCommand extends SubCommand {

    private final BJSG plugin;

    public EditCommand(final BJSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "edit";
    }

    @Override
    public String getDescription() {
        return "Enter edit mode.";
    }

    @Override
    public String getSyntax() {
        return "/bjsg edit";
    }

    @Override
    public void perform(Player player, String[] args) {
        WandManager wandManager = new WandManager(plugin);
        CuboidManager regionManager = new CuboidManager(plugin);

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("edit")) {
                if (!(player.hasPermission("bjsg.edit"))) {
                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.noPerms);
                } else if (player.hasPermission("bjsg.edit")) {
                    MagicWand magicWand = new MagicWand();
                    ItemStack wandStack = magicWand.getMagicWand();
                    wandManager.setPrimed(player);
                    player.getInventory().addItem(wandStack);
                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + "You have entered arena creation mode.");
                }
            }


        }
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("exit")) {
                player.sendMessage("1");
                if (!(player.hasPermission("bjsg.edit"))) {
                    player.sendMessage("2");
                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.noPerms);
                } if (player.hasPermission("bjsg.edit")) {
                    player.sendMessage("3");
                    wandManager.removePrimed(player);
                    player.sendMessage("4");
                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + "You have exited arena creation mode.");
                    player.sendMessage("5");
                    MagicWand magicWand = new MagicWand();
                    player.sendMessage("6");
                    ItemStack wandStack = magicWand.getMagicWand();
                    player.sendMessage("7");
                    player.getInventory().remove(wandStack);
                    player.sendMessage("8");

                }

            }
            if (args[1].equalsIgnoreCase("create")) {
                if (!(player.hasPermission("bjsg.edit"))) {
                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.noPerms);
                }
                else if (player.hasPermission("bjsg.edit")) {
                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.insuffArgs);
                }
            }
//            if (args[0].equalsIgnoreCase("create")) {
//                if (!(player.hasPermission("bjsg.edit"))) {
//                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.noPerms);
//                } else if (player.hasPermission("bjsg.edit")) {
//                    if (wandManager.getPositionA() == null || wandManager.getPositionB() == null) {
//                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "You have not set any boundaries for the region.");
//                    } else {
//                        regionManager.createRegion(args[1], wandManager.getPositionA(), wandManager.getPositionB());
//                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + "Region " + args[1] + " created.");
//                    }
//                }
//
//            }
//            else if (args[0].equalsIgnoreCase("create")) {
//                player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "Not enough arguments.");
//            }
        }
        if (args.length == 3) {

            if (args[1].equalsIgnoreCase("create")) {
                if (!(player.hasPermission("bjsg.edit"))) {
                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.noPerms);
                } else if (player.hasPermission("bjsg.edit")) {
                    if (wandManager.getPositionA() == null || wandManager.getPositionB() == null) {
                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "You have not set any boundaries for the region.");
                    } else {
                        regionManager.createRegion(args[2], wandManager.getPositionA(), wandManager.getPositionB());
                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + "Region " + args[2] + " created.");
                    }
                }

            }
        }

    }
}
