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
        player.sendMessage("1");
        if (args.length == 2) {
            player.sendMessage("2");
            if(args[0].equalsIgnoreCase("arena")) {
                player.sendMessage("3");
                if (args[1].equalsIgnoreCase("list")) {
                    player.sendMessage("4");
                    for (Arena a : plugin.activeArenas) {
                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + a);
                        player.sendMessage(ChatHandler.chatPrefix + ChatColor.GREEN + a.getId());
                    }
                }
            }
        }
    }
}
