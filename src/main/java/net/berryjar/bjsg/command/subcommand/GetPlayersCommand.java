package net.berryjar.bjsg.command.subcommand;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.ArenaManager;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.command.SubCommand;
import net.berryjar.bjsg.util.Manager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GetPlayersCommand extends SubCommand {

    private final BJSG plugin;

    public GetPlayersCommand(final BJSG plugin) {
        this.plugin = plugin;
    }
    @Override
    public String getName() {
        return "getplayers";
    }

    @Override
    public String getDescription() {
        return "Get state.";
    }

    @Override
    public String getSyntax() {
        return "/bjsg getstate <arenaID>";
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
            if (args[0].equalsIgnoreCase("getplayers")) {
                if (!(player.hasPermission("bjsg.getplayers"))) {
                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.noPerms);
                } else {
                    player.sendMessage(ChatHandler.insuffArgs);
                }
            }
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("getplayers")) {
                player.sendMessage("1");
                String arenaID = args[1];
                player.sendMessage("2");
                ArenaManager arenaManager = new ArenaManager();
                for (Arena a : plugin.activeArenas) {
                    if (arenaManager.getArenaID(a).equalsIgnoreCase(arenaID)) {
                        player.sendMessage("3");
                        if (a.getId().equalsIgnoreCase(arenaID)) {
                            player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + a.getPlayers());
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
