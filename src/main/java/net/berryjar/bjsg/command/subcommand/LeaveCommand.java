package net.berryjar.bjsg.command.subcommand;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.ArenaManager;
import net.berryjar.bjsg.arena.GameState;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.command.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LeaveCommand extends SubCommand {


    private final BJSG plugin;

    public LeaveCommand(final BJSG plugin) {
        this.plugin = plugin;

    }
    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public String getDescription() {
        return "Leave an SG arena.";
    }

    @Override
    public String getSyntax() {
        return "/bjsg leave";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("leave")) {
                if (!(player.hasPermission("bjsg.leave"))) {
                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.noPerms);
                } else {
                    player.sendMessage(ChatHandler.insuffArgs);
                }
            }
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("leave")) {
                String arenaID = args[1];
                ArenaManager arenaManager = new ArenaManager(plugin);
                for (Arena a : plugin.getActiveArenas()) {
                    if (plugin.getActiveArenas().contains(a)) {
                        if (a.getId().equalsIgnoreCase(arenaID)) {
                            if (a.getPlayers().contains(player.getUniqueId())) {
                                a.removePlayer(player.getUniqueId());
                                plugin.sgPlayers.remove(player.getUniqueId());
                                player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + "You left arena " + arenaID + ".");
                            } else {
                                player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "You are not in an arena.");
                            }
                        }

                    } else {
                        return;
                    }
                }
            }
        }
    }
}
