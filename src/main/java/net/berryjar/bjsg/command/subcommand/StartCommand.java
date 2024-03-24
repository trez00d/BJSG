package net.berryjar.bjsg.command.subcommand;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.ArenaManager;
import net.berryjar.bjsg.arena.GameState;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.command.SubCommand;
import net.berryjar.bjsg.util.Manager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class StartCommand extends SubCommand {

    private final BJSG plugin;

    public StartCommand(final BJSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "start";
    }

    @Override
    public String getDescription() {
        return "Start an arena.";
    }

    @Override
    public String getSyntax() {
        return "/bjsg start <arenaID>";
    }

    @Override
    public void perform(Player player, String[] args) {
        Manager manager = new Manager(plugin);
        if (args.length != 2) {
            player.sendMessage(ChatHandler.chatPrefix + ChatHandler.insuffArgs);
        }
        //bjsg stop <ID>
        //Command <arg0> <arg1>


        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("start")) {
                if (!(player.hasPermission("bjsg.start"))) {
                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.noPerms);
                } else {
                    player.sendMessage(ChatHandler.insuffArgs);
                }
            }
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("start")) {
                String arenaID = args[1];
                ArenaManager arenaManager = new ArenaManager(plugin);
                Arena toStart = arenaManager.getStoppedArenaByID(arenaID);
                if (!(toStart == null)) {
                    plugin.arenaCache.remove(toStart);
                    Location loc = toStart.getLobbyLocation();
                    Arena arenaNew = new Arena(plugin, toStart.getArenaRegion(), toStart.getId());
                    arenaNew.setLobby(loc);
                    arenaNew.startArena();
                    arenaNew.getLobby().startLobby(15);
                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + "You started arena " + toStart);

                } else {
                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "Arena already started.");
                }
                player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + "You started the arena." );
//                String arenaID = args[1];
//                ArenaManager arenaManager = new ArenaManager(plugin);
//                Arena toStart = arenaManager.getStoppedArenaByID(arenaID);
//                if (!(toStart == null)) {
//                    plugin.arenaCache.remove(toStart);
//                    Location loc = toStart.getLobbyLocation();
//                    Arena arenaNew = new Arena(plugin, toStart.getArenaRegion(), toStart.getId());
//                    arenaNew.setLobby(loc);
//                    arenaNew.startArena();
//                    arenaNew.getLobby().startLobby(15);
//                    plugin.activeArenas.add(toStart);
//                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + "You started arena " + toStart);
//
//                } else {
//                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "Arena already started.");
//                }


//                for (Arena a : plugin.arenaCache) {
//                    if (a.getId().equals(arenaID)) {
//                        plugin.arenaCache.remove(a);
//                        Location loc = a.getLobbyLocation();
//                        Arena arenaNew = new Arena(plugin, a.getArenaRegion(), a.getId());
//                        arenaNew.setLobby(loc);
//                        arenaNew.startArena();
//                        arenaNew.getLobby().startLobby(15);
//                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + "You started arena " + arenaID + ".");
//                    }
//                }
//                for (Arena a : plugin.activeArenas) {
//                    System.out.println("StartCommand 61");
//                    if (arenaID.equalsIgnoreCase(a.getId())) {
//                        System.out.println("StartCommand 63");
//                        System.out.println("StartCommand 65");
//                        if (!(a.getState() == GameState.STOPPED)) {
//                            System.out.println("StartCommand 67");
//                            player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "This arena is already started.");
//                        } else {
//                            System.out.println("StartCommand 70");
//                            if (a.getState() == GameState.STOPPED) {
//                                System.out.println("StartCommand 72");
//                                arenaManager.removeArena(a);
//
//                                Location loc = a.getLobbyLocation();
//                                System.out.println("StartCommand 75");
//                                Arena arenaNew = new Arena(plugin, a.getArenaRegion(), a.getId());
//                                plugin.activeArenas.add(arenaNew);
//
//                                System.out.println("StartCommand 77");
//                                arenaNew.setLobby(loc);
//                                System.out.println("StartCommand 79");
//                                arenaNew.startArena();
//                                System.out.println("StartCommand 81");
//                                arenaNew.getLobby().startLobby(15);
//                                System.out.println("StartCommand 83");
//
//
//                                System.out.println("StartCommand 86");
//                                player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + "Arena " + arenaID + " started.");
//                            }
//
//                        }
//                    } else {
//                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "Arena ID not recognized.");
//                    }
//                }
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
    }
}
