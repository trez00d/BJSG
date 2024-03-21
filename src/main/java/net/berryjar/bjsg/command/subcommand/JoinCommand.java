package net.berryjar.bjsg.command.subcommand;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.ArenaManager;
import net.berryjar.bjsg.arena.GameState;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.command.SubCommand;
import net.berryjar.bjsg.util.Manager;
import net.berryjar.bjsg.arena.Arena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class JoinCommand extends SubCommand {

    private final BJSG plugin;

    public JoinCommand(final BJSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "join";
    }

    @Override
    public String getDescription() {
        return "Join an SG arena.";
    }

    @Override
    public String getSyntax() {
        return "/bjsg join <arenaID>";
    }

    @Override
    public void perform(Player player, String[] args) {

        Manager manager = new Manager(plugin);
        if (args.length != 2) {
            player.sendMessage(ChatHandler.chatPrefix + ChatHandler.insuffArgs);
        }
        //bjsg join <ID>
        //Command <arg0> <arg1>


        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("join")) {
                if (!(player.hasPermission("bjsg.join"))) {
                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.noPerms);
                } else {
                    player.sendMessage(ChatHandler.insuffArgs);
                }
            }
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("join")) {
                System.out.println("JoinCommand 59");
                String arenaID = args[1];
                ArenaManager arenaManager = new ArenaManager(plugin);
                for (Arena a : plugin.activeArenas) {
                    System.out.println("JoinCommand 63");
                    if (plugin.activeArenas.contains(a)) {
                        System.out.println("JoinCommand 65");
                        if (a.getId().equalsIgnoreCase(arenaID)) {
                            System.out.println("JoinCommand 67");
                            if (a.getState() == GameState.LOBBY) {
                                System.out.println("JoinCommand 69");
                                a.addPlayer(player.getUniqueId());
                                player.teleport(a.getLobbyLocation());
                                player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + "You joined arena " + arenaID + ".");
                            } else {
                                player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "This game has already started.");
                            }


                        }

                    } else {
                        return;
                    }
                }
            }
//                    player.sendMessage("3");
//                    if (a.getId().equalsIgnoreCase(arenaID)) {
//                        player.sendMessage("4");
//                        a.addPlayer(player.getUniqueId());
//                        player.sendMessage("5");
//                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + "You joined arena " + arenaID);
//                        player.sendMessage("6");
//                    } else {
//                        player.sendMessage("7");
//                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "Arena ID not recognized.");
//                        player.sendMessage("8");
//                    }
//                }
        }

    }
//        else if (args.length == 1) {
//            if (!manager.isArena(args[1])) {
//                player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + args[1] + " doesn't ring a bell.");
//            } else {
//                String arenaID = args[1];
//                final Arena arena = manager.getArena(arenaID);
//
//                final Arena current = manager.getArena(player.getUniqueId());
//                if (current != null) {
//                    Bukkit.dispatchCommand(player, "bjsg leave");
//                }
//                if (arena.getState() == GameState.PREGAME || arena.getState() == GameState.INGAME || arena.getState() == GameState.PREDEATHMATCH || arena.getState() == GameState.DEATHMATCH || arena.getState() == GameState.POSTGAME) {
//                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "You cannot join this arena because the game is active.");
//                }
//                arena.addPlayer(player.getUniqueId());
//
//            }
//
//        }


}

