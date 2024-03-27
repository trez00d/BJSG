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
        if (args.length == 2) {
            if(args[0].equalsIgnoreCase("arena")) {
                if (args[1].equalsIgnoreCase("list")) {
                    StringBuilder sBActive = new StringBuilder(ChatHandler.chatPrefix + ChatColor.GREEN + "Active arenas: " );
                    StringBuilder sBStopped = new StringBuilder(ChatHandler.chatPrefix + ChatColor.GRAY + "Stopped arenas: ");

                    for (Arena a : plugin.activeArenas) {
                        player.sendMessage(a.getId());
                        sBActive.append(a.getId()).append(", ");

                    }
                    for (Arena a : plugin.arenaCache) {
                        sBStopped.append(a.getId()).append(", ");

                    }
                    player.sendMessage(String.valueOf(sBActive));
                    player.sendMessage(String.valueOf(sBStopped));

                }
            }
        }
    }
}
