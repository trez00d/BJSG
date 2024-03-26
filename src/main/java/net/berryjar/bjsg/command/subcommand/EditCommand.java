package net.berryjar.bjsg.command.subcommand;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.SpawnCreator;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.command.SubCommand;
import net.berryjar.bjsg.cuboid.CuboidManager;
import net.berryjar.bjsg.magicwand.MagicWand;
import net.berryjar.bjsg.magicwand.WandManager;
import net.berryjar.bjsg.player.LooseWrapper;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class EditCommand extends SubCommand {

    private final BJSG plugin;

    private int spawnInc = 0;


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
        UUID u = player.getUniqueId();


        SpawnCreator spawnCreator = new SpawnCreator(plugin);
        WandManager wandManager = new WandManager(plugin);
        CuboidManager regionManager = new CuboidManager(plugin);

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("edit")) {
                if (!(player.hasPermission("bjsg.edit"))) {
                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.noPerms);
                } else if (player.hasPermission("bjsg.edit")) {
                    plugin.grantMagicWand(player.getUniqueId());
//                    for (UUID lW : plugin.looseWrapperPlayer) {
//                        if (lW.equals(player.getUniqueId())) {
//
//                        }
//                    }
//                    MagicWand magicWand = new MagicWand();
//                    ItemStack wandStack = magicWand.getMagicWand();
//                    wandManager.setPrimed(player);
//                    player.getInventory().addItem(wandStack);
//                    System.out.printf("LOOT CONFIG CREATE");

                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + "You have been given the " + ChatColor.ITALIC + "Region Wand" + ChatColor.RESET + ChatColor.GOLD + ".");
                }
            }


        }
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("addspawn")) {
                player.sendMessage(ChatHandler.chatPrefix + ChatHandler.insuffArgs);
            }
            if (args[1].equalsIgnoreCase("exit")) {
                if (!(player.hasPermission("bjsg.edit"))) {
                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.noPerms);
                } if (player.hasPermission("bjsg.edit")) {
                    wandManager.removePrimed(player);
                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + "You have exited arena creation mode.");
                    MagicWand magicWand = new MagicWand();
                    ItemStack wandStack = magicWand.getMagicWand();
                    player.getInventory().remove(wandStack);

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
            if (args[1].equalsIgnoreCase("delspawn")) {
                player.sendMessage(ChatHandler.chatPrefix + ChatHandler.insuffArgs);
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
            if (args[0].equalsIgnoreCase("edit")) {
                if (args[1].equalsIgnoreCase("addlobby")) {

                    String arenaID = args[2];
                    Location lobbyLoc = player.getLocation();

                    for (Arena a : plugin.getActiveArenas()) {
                        if (a.getId().equalsIgnoreCase(arenaID)) {
                            String world = lobbyLoc.getWorld().getName();
                            int x = lobbyLoc.getBlockX();
                            int y = lobbyLoc.getBlockY();
                            int z = lobbyLoc.getBlockZ();
                            float pitch = lobbyLoc.getPitch();
                            float yaw = lobbyLoc.getYaw();
                            spawnCreator.createLobby(lobbyLoc, arenaID);
                            player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + "Lobby spawn set: " + arenaID + lobbyLoc);
                            plugin.getConfig().set("lobby." + arenaID + ".world", world);
                            plugin.getConfig().set("lobby." + arenaID + ".x", x);
                            plugin.getConfig().set("lobby." + arenaID + ".y", y);
                            plugin.getConfig().set("lobby." + arenaID + ".z", z);
                            plugin.getConfig().set("lobby." + arenaID + ".pitch", pitch);
                            plugin.getConfig().set("lobby." + arenaID + ".yaw", yaw);
                            plugin.saveConfig();
                        }
                    }


                }
                if (args[1].equalsIgnoreCase("addspawn")) {
                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.insuffArgs);
                }
                if (args[1].equalsIgnoreCase("delspawn")) {
                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.insuffArgs);
                }
                if (args[1].equalsIgnoreCase("listspawns")) {
                    String arenaID = args[2];
                    for (Arena a : plugin.getActiveArenas()) {
                        if (a.getId().equals(arenaID)) {
                            player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + a.getSpawns().toString());
                        }
                    }
                }
            }

        }
        if (args.length == 4) {
            if (args[1].equalsIgnoreCase("addspawn")) {
                String arenaID = args[2];
                int spawnID = Integer.parseInt(args[3]);
                Location spawnLoc = player.getLocation();
                spawnCreator.createSpawn(spawnLoc, arenaID, spawnID);
                player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + "Spawn point added: " + arenaID + ", " + spawnInc + ", " + spawnLoc);
//
            }
            if (args[1].equalsIgnoreCase("delspawn")) {
                String arenaID = args[2];
                int spawnID = Integer.parseInt(args[3]);
                spawnCreator.removeSpawn(arenaID, spawnID);
                player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + "Spawn point removed: " + arenaID + ", " + spawnID);
            }
        }

    }
}
