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

import java.util.Iterator;
import java.util.Map;

public class StopCommand extends SubCommand {

    private final BJSG plugin;

    public StopCommand(final BJSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public String getDescription() {
        return "BJSG Stop arena command.";
    }

    @Override
    public String getSyntax() {
        return "/bjsg stop <arenaID>";
    }

    @Override
    public void perform(Player player, String[] args) {

        Manager manager = new Manager(plugin);
        if (args.length != 2) {
            player.sendMessage(ChatHandler.chatPrefix + ChatHandler.insuffArgs);
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("stop")) {
                if (!(player.hasPermission("bjsg.stop"))) {
                    player.sendMessage(ChatHandler.chatPrefix + ChatHandler.noPerms);
                } else {
                    player.sendMessage(ChatHandler.insuffArgs);
                }
            }
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("stop")) {
                String arenaID = args[1];
                ArenaManager arenaManager = new ArenaManager(plugin);
                Arena toDelete = arenaManager.getArenaByID(arenaID);
                toDelete.stopArena();
                System.out.println("Arena stop add to cache" + arenaManager.getArenaByID(arenaID));
                plugin.getActiveArenas().remove(toDelete);
                player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + "You stopped the arena." );


            }

        }

    }

}
