package net.berryjar.bjsg.timer;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.GameState;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.player.SGPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Deathmatch extends BukkitRunnable {

    private int time;
    private final Arena arena;

    private final BJSG plugin;

    public Deathmatch(final BJSG plugin, Arena arena) {
        this.plugin = plugin;
        this.arena = arena;
        this.time = 0;
    }

    public void startDeathmatch(int time) {
        arena.setState(GameState.DEATHMATCH);
        this.time = time;
        this.runTaskTimer(plugin, 0L, 20L);
    }
    @Override
    public void run() {

        System.out.println("dm time == 0 && players size greater than 1");
        if (time == 0 && arena.getPlayers().size() > 1) {
            System.out.println("30");
            cancel();
            System.out.println("31");
            if (!arena.getPostGame().isRunning()) {
                arena.getPostGame().startPostGame(15);
                return;
            }
            System.out.println("32");
            arena.broadcast(ChatHandler.chatPrefix + ChatColor.GOLD + "The game ended in a draw.");
            System.out.println("33");
            for (SGPlayer player : arena.getPlayers()) {
                System.out.println("34");
                arena.removePlayer(player);
                System.out.println("35");

            }
            return; // Get out of the run method.

        }
        System.out.println("dm players size == 1");
        if (arena.getPlayers().size() == 1) {
            System.out.println("36");
            cancel();
            System.out.println("37");
            if (!arena.getPostGame().isRunning()) {
                arena.getPostGame().startPostGame(30);
                return;
            }

            System.out.println("38");
            arena.broadcast(arena.getPlayers().get(0).toString() + " has won the game!");
            System.out.println("39");
            return;
        }

        if (time % 15 == 0 || time <= 10) {
            // If the time is divisible by 15 then broadcast a countdown
            // message.
            if (time != 1) {
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + "The game will end in " + time + " seconds.");
            } else {
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + "The game will end in " + time + " second.");
            }
        }

        time--;
    }

    public boolean isRunning() {
        return arena.getState() == GameState.DEATHMATCH;
    }
}
