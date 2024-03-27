package net.berryjar.bjsg.command.subcommand;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.command.SubCommand;
import net.berryjar.bjsg.cuboid.CuboidManager;
import net.berryjar.bjsg.magicwand.WandManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RegionCommand extends SubCommand {

    private final BJSG plugin;

    public RegionCommand(BJSG plugin) {
        this.plugin = plugin;
    }




    @Override
    public String getName() {
        return "region";
    }

    @Override
    public String getDescription() {
        return "Region subcommand";
    }

    @Override
    public String getSyntax() {
        return "/bjhub region <args>";
    }


    @Override
    public void perform(Player player, String[] args) {
        WandManager wandManager = new WandManager(plugin);
        CuboidManager regionManager = new CuboidManager(plugin);
        if (args.length == 2) {

            if(args[0].equalsIgnoreCase("region")) {
                if (args[1].equalsIgnoreCase("list")) {
                    player.sendMessage(ChatHandler.chatPrefix + ChatColor.GOLD + regionManager.getActiveRegions());
                }
            }

        }

    }
}
