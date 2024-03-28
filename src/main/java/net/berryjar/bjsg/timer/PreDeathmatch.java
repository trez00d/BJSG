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

        if (time == 0) {
            cancel();
            if (!arena.getDeathmatch().isRunning()) {
                arena.getDeathmatch().startDeathmatch(15);
                return;
            }

            return; // Get out of the run method.
        }

        if (arena.getPlayers().size() == 1) {
            cancel();
            UUID sgWinner = arena.players.get(0);
            Player winner = Bukkit.getPlayer(sgWinner);
            arena.broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + winner.getName() + " won the game!");
            arena.getPostGame().startPostGame(15);
            return;
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
