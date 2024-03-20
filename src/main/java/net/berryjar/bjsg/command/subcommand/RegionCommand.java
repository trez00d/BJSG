package net.berryjar.bjsg.command.subcommand;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.command.SubCommand;
import net.berryjar.bjsg.cuboid.CuboidManager;
import net.berryjar.bjsg.magicwand.WandManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RegionCommand extends SubCommand {

    private final BJSG plugin;

    public RegionCommand(BJSG plugin) {
        this.plugin = plugin;
    }




    @Override
    public String getName() {
        return "region";
    }

    @Override
    public String getDescription() {
        return "Region subcommand";
    }

    @Override
    public String getSyntax() {
        return "/bjhub region <args>";
    }


    @Override
    public void perform(Player player, String[] args) {
        WandManager wandManager = new WandManager(plugin);
        CuboidManager regionManager = new CuboidManager(plugin);
        if (args.length == 2) {

//            if (args[1].equalsIgnoreCase("edit")) {
//                if (!(player.hasPermission("bjhub.edit"))) {
//                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.noPerms);
//                } else if (player.hasPermission("bjhub.edit")) {
//                    MagicWand magicWand = new MagicWand();
//                    ItemStack wandStack = magicWand.getMagicWand();
//                    wandManager.setPrimed(player);
//                    player.getInventory().addItem(wandStack);
//                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + "You have entered region creation mode.");
//                    ConfigManager configManager = new ConfigManager(player.getUniqueId());
//                    configManager.getBankConfig().set("coins", 0);
//                    configManager.saveBankConfig();
//                }
//            }


//            if (args[1].equalsIgnoreCase("exit")) {
//
//                if (!(player.hasPermission("bjhub.edit"))) {
//                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.noPerms);
//                } if (player.hasPermission("bjhub.edit")) {
//                    wandManager.removePrimed(player);
//                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + "You have exited region creation mode.");
//                    MagicWand magicWand = new MagicWand();
//                    ItemStack wandStack = magicWand.getMagicWand();
//                    player.getInventory().remove(wandStack);
//
//                }
//
//            }
//            else if (args[1].equalsIgnoreCase("create")) {
//                player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "Not enough arguments.");
//            }
            if(args[0].equalsIgnoreCase("region")) {
                if (args[1].equalsIgnoreCase("list")) {
                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + regionManager.getActiveRegions());
                }
            }

//            else if (args[1].equalsIgnoreCase("delete")) {
//                player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "Not enough arguments.");
//            }

        }
//        if (args.length == 3) {
//
//            //bjhub region create <ID>
//            //Command <arg0> <arg1> <arg2>
////            if (args[1].equalsIgnoreCase("create")) {
////                if (!(player.hasPermission("bjhub.edit"))) {
////                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.noPerms);
////                } else if (player.hasPermission("bjhub.edit")) {
////                    if (wandManager.getPositionA() == null || wandManager.getPositionB() == null) {
////                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "You have not set any boundaries for the region.");
////                    } else {
////                        regionManager.createRegion(args[2], wandManager.getPositionA(), wandManager.getPositionB());
////                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + "Region " + args[2] + " created.");
////                    }
////                }
////
////            }
////            if (args[1].equalsIgnoreCase("delete")) {
////                if (!(player.hasPermission("bjhub.edit"))) {
////                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.noPerms);
////                } else if (player.hasPermission("bjhub.edit")) {
////                    regionManager.removeRegion(args[2]);
////                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + "Region " + args[2] + " deleted.");
////                }
////            }
//
//        }
//        if (args.length == 5) {
//            if (args[1].equalsIgnoreCase("setflag")) {
//                if (plugin.activeRegions.contains(args[2])) {
//                    Cuboid region = regionManager.getRegion(args[2]);
//                    if (args[3].equalsIgnoreCase("setAllowBlockBreaking")) {
//                        if (args[4].equalsIgnoreCase("true")) {
//                            player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + "Flag allowBlockBreaking set to true for region " + region.getID());
//                            region.setAllowBlockBreaking(true);
//                        } else if (args[4].equalsIgnoreCase("false")) {
//                            player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + "Flag allowBlockBreaking set to false for region " + region.getID());
//                            region.setAllowBlockBreaking(false);
//                        }
//                    }
//                }
//                else {
//                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "That region does not exist or is not active.");
//                }
//            }
//        }

    }
}
