package net.berryjar.bjsg.command.subcommand;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.ArenaManager;
import net.berryjar.bjsg.arena.GameState;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.command.SubCommand;
import net.berryjar.bjsg.util.Manager;
import org.bukkit.ChatColor;
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
                for (Arena a : plugin.activeArenas) {
                    if (arenaManager.getArenaID(a).equalsIgnoreCase(arenaID)) {
                        if (a.getId().equalsIgnoreCase(arenaID)) {
                            if (!(a.getState() == GameState.STOPPED)) {
                                player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "This arena is already started.");
                            } else {
                                if (a.getState() == GameState.STOPPED) {
                                    Arena arenaNew = new Arena(plugin, a.getArenaRegion(), a.getId());
                                    arenaNew.getLobby().startLobby(15);
                                    plugin.activeArenas.remove(a);

                                    plugin.activeArenas.add(arenaNew);
                                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + "Arena " + arenaID + " started.");
                                }

                            }

                        } else {
                            player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "Arena ID not recognized.");
                        }
                    } else {
                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "Arena ID not recognized.");
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
    }
}
