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
                String arenaID = args[1];
                ArenaManager arenaManager = new ArenaManager(plugin);
                for (Arena a : plugin.getActiveArenas()) {
                    if (arenaManager.getArenaID(a).equalsIgnoreCase(arenaID)) {
                        if (a.getId().equalsIgnoreCase(arenaID)) {
                            player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + a.getPlayers());
                        } else {
                            player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "Arena ID not recognized.");
                        }
                    } else {
                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.RED + "Arena ID not recognized.");
                    }
                }
            }

        }
    }
}
