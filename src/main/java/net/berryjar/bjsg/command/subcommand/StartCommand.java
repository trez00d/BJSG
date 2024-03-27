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
            }

        }
    }
}
