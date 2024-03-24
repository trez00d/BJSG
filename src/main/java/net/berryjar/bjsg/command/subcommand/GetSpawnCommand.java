package net.berryjar.bjsg.command.subcommand;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.command.SubCommand;
import org.bukkit.entity.Player;

public class GetSpawnCommand extends SubCommand {

    private final BJSG plugin;

    public GetSpawnCommand(final BJSG plugin) {
        this.plugin = plugin;
    }
    @Override
    public String getName() {
        return "getspawns";
    }

    @Override
    public String getDescription() {
        return "Get arena spawns";
    }

    @Override
    public String getSyntax() {
        return "/bjsg getspawns";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length == 1) {
            player.sendMessage(plugin.arenaSpawns.toString());
        }

    }
}
