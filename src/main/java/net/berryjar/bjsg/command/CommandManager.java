package net.berryjar.bjsg.command;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.command.subcommand.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private final BJSG plugin;

    private ArrayList<SubCommand> subCommandList = new ArrayList<SubCommand>();

    public CommandManager(final BJSG plugin) {
        this.plugin = plugin;
        subCommandList.add(new JoinCommand(plugin));
        subCommandList.add(new EditCommand(plugin));
        subCommandList.add(new GetStateCommand(plugin));
        subCommandList.add(new GetPlayersCommand(plugin));
        subCommandList.add(new RegionCommand(plugin));
        subCommandList.add(new ListCommand(plugin));
        subCommandList.add(new StopCommand(plugin));
        subCommandList.add(new StartCommand(plugin));


    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length > 0) {
                for (int i = 0; i < getSubCommandList().size(); i++) {
                    if (args[0].equalsIgnoreCase(getSubCommandList().get(i).getName())) {
                        getSubCommandList().get(i).perform(player, args);
                    }
                }

            }
        }

        return true;
    }

    public ArrayList<SubCommand> getSubCommandList() {
        return subCommandList;
    }
}