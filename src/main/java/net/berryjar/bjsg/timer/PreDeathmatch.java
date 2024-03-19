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

public class PreDeathmatch extends BukkitRunnable {
    private int time;
    private final Arena arena;

    private final BJSG plugin;

    public PreDeathmatch(final BJSG plugin, Arena arena) {
        this.plugin = plugin;
        this.arena = arena;
        this.time = 0;
    }

    public void startPreDeathmatch(int time) {
        arena.setState(GameState.PREDEATHMATCH);
        this.time = time;
        this.runTaskTimer(plugin, 0L, 20L);
    }

    @Override
    public void run() {

        System.out.println("predm time == 0 && players size greater than 1");
        if (time == 0 && arena.getPlayers().size() > 1) {
            cancel();
            arena.broadcast(ChatHandler.chatPrefix + ChatColor.GOLD + "The game ended in a draw.");
            for (SGPlayer player : arena.getPlayers()) {
                System.out.println("18");
                arena.removePlayer(player);
                System.out.println("19");
            }
            if (!arena.getPostGame().isRunning()) {
                arena.getPostGame().startPostGame(15);
                return;
            }

            System.out.println("20");
            return; // Get out of the run method.

        }

        System.out.println("predm players size == 1");
        if (arena.getPlayers().size() == 1) {
            SGPlayer sgWinner = (arena.players.get(0));
            Player winner = sgWinner.getPlayer();
            arena.broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + winner.getName() + " won the game!");
            arena.removePlayer(sgWinner);
            cancel();
            if (!arena.getPostGame().isRunning()) {
                arena.getPostGame().startPostGame(15);
                return;
            }

            return;

        }

        System.out.println("predm time 0");
        if (time == 0) {


            cancel();
            if (!arena.getDeathmatch().isRunning()) {
                arena.getDeathmatch().startDeathmatch(15);
                return;
            }
            return; // Get out of the run method.
        }

        if (time % 15 == 0 || time <= 10) {
            // If the time is divisible by 15 then broadcast a countdown
            // message.
            if (time != 1) {
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.DARK_PURPLE + "Deathmatch will start in " + time + " seconds.");
            } else {
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.DARK_PURPLE + "Deathmatch will start in " + time + " second.");
            }
        }

//        if (arena.getPlayers().size() < arena.getRequiredPlayers()) {
//            cancel();
//            arena.setState(GameState.LOBBY);
//            arena.broadcast(ChatHandler.chatPrefix + ChatColor.RED + "There are too few players. Countdown stopped.");
//            return; // Get out of the run method.
//        }

        time--;
    }

    public boolean isRunning() {
        return arena.getState() == GameState.PREDEATHMATCH;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Arena getArena() {
        return arena;
    }
}
