package net.berryjar.bjsg.command.subcommand;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.ArenaManager;
import net.berryjar.bjsg.arena.SpawnCreator;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.command.SubCommand;
import net.berryjar.bjsg.config.ConfigManager;
import net.berryjar.bjsg.cuboid.CuboidManager;
import net.berryjar.bjsg.magicwand.MagicWand;
import net.berryjar.bjsg.magicwand.WandManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.A;

import java.util.HashMap;
import java.util.LinkedHashMap;

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
            if (args[1].equalsIgnoreCase("addspawn")) {
                player.sendMessage(ChatHandler.chatPrefix + ChatHandler.insuffArgs);
            }
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
            if (args[0].equalsIgnoreCase("edit")) {
                if (args[1].equalsIgnoreCase("addlobby")) {
                    SpawnCreator spawnCreator = new SpawnCreator(plugin);

                    String arenaID = args[2];
                    Location lobbyLoc = player.getLocation();

                    for (Arena a : plugin.activeArenas) {
                        if (a.getId().equalsIgnoreCase(arenaID)) {

                            a.removeLobbySpawns();
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
                    spawnInc++;
                    SpawnCreator spawnCreator = new SpawnCreator(plugin);

                    String arenaID = args[2];
                    Location spawnLoc = player.getLocation();
                    spawnCreator.createSpawn(spawnLoc, arenaID);
                    for (Arena a : plugin.activeArenas) {
                        if (a.getId().equalsIgnoreCase(arenaID)) {
                            a.addSpawn(spawnInc, spawnLoc);
                            Location loc = a.getSpawns().get(spawnInc);
                            String world = loc.getWorld().getName();
                            int x = loc.getBlockX();
                            int y = loc.getBlockY();
                            int z = loc.getBlockZ();
                            float pitch = loc.getPitch();
                            float yaw = loc.getYaw();
                            LinkedHashMap<Integer, Location> arenaSpawn = a.getSpawns();
                            plugin.arenaSpawns.put(arenaID, arenaSpawn);
                            player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + "Spawn point set: " + arenaID + ", " + spawnInc + ", " + loc);
                            plugin.getConfig().set("spawns." + arenaID + "." + spawnInc + ".world", world);
                            plugin.getConfig().set("spawns." + arenaID + "." + spawnInc + ".x", x);
                            plugin.getConfig().set("spawns." + arenaID + "." + spawnInc + ".y", y);
                            plugin.getConfig().set("spawns." + arenaID + "." + spawnInc + ".z", z);
                            plugin.getConfig().set("spawns." + arenaID + "." + spawnInc + ".pitch", pitch);
                            plugin.getConfig().set("spawns." + arenaID + "." + spawnInc + ".yaw", yaw);
                            plugin.saveConfig();
                        }
                    }


                }
                if (args[1].equalsIgnoreCase("delspawn")) {
                    String arenaID = args[2];
                    Location spawnLoc = player.getLocation();
                    spawnInc--;
                    for (Arena a : plugin.activeArenas) {
                        if (a.getId().equalsIgnoreCase(arenaID)) {
                            a.removeSpawn();
                        }
                    }
                }
                if (args[1].equalsIgnoreCase("listspawns")) {
                    String arenaID = args[2];
                    for (Arena a : plugin.activeArenas) {
                        if (a.getId().equals(arenaID)) {
                            player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + a.getSpawns().toString());
                        }
                    }
                }
            }

        }

    }
}
