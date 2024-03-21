package net.berryjar.bjsg.command.subcommand;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.command.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ListCommand extends SubCommand {

    private final BJSG plugin;
    public ListCommand(final BJSG plugin) { this.plugin = plugin;}

    @Override
    public String getName() {
        return "arena";
    }


    @Override
    public String getDescription() {
        return "Arena command.";
    }

    @Override
    public String getSyntax() {
        return "/bjhub arena <args>";
    }

    @Override
    public void perform(Player player, String[] args) {
//        if (plugin.sgPlayers.containsKey(player.getUniqueId())) {
//            player.sendMessage();
//        }
        if (args.length == 2) {
            if(args[0].equalsIgnoreCase("arena")) {
                if (args[1].equalsIgnoreCase("list")) {
                    for (Arena a : plugin.activeArenas) {
                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + a);
                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + a.getId());
                    }
                }
            }
        }
    }
}
