package net.berryjar.bjsg.command.subcommand;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.ArenaManager;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.command.SubCommand;
import net.berryjar.bjsg.util.Manager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GetStateCommand extends SubCommand {

    private final BJSG plugin;

    public GetStateCommand(final BJSG plugin) {
        this.plugin = plugin;
    }
    @Override
    public String getName() {
        return "getstate";
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

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("getstate")) {
                if (!(player.hasPermission("bjsg.getstate"))) {
                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.noPerms);
                } else {
                    player.sendMessage(ChatHandler.insuffArgs);
                }
            }
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("getstate")) {
                String arenaID = args[1];
                ArenaManager arenaManager = new ArenaManager(plugin);
                for (Arena a : plugin.getActiveArenas()) {
                    if (arenaManager.getArenaID(a).equalsIgnoreCase(arenaID)) {
                        if (a.getId().equalsIgnoreCase(arenaID)) {
                            player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + a.getState());
                        } else {
                            player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "Arena ID not recognized.");
                        }
                    }
                }
                for (Arena a : plugin.arenaCache) {
                    if (arenaManager.getArenaID(a).equalsIgnoreCase(arenaID)) {
                        if (a.getId().equalsIgnoreCase(arenaID)) {
                            player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + a.getState());
                        } else {
                            player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "Arena ID not recognized.");
                        }
                    }
                }

            }

        }
    }
}
