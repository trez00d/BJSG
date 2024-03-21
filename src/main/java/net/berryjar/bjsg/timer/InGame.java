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

public class InGame extends BukkitRunnable {

    private final BJSG plugin;
    private final Arena arena;
    private int time;

    public InGame(final BJSG plugin, Arena arena) {
        this.plugin = plugin;
        this.arena = arena;
        this.time = 0;

    }

    public void startInGame(int time) {
        this.time = time;
        arena.setState(GameState.INGAME);
        this.runTaskTimer(plugin, 0L, 20L);
        arena.broadcast(ChatHandler.chatPrefix + ChatColor.GOLD + "The game has started. Good luck everyone!");
    }

    @Override
    public void run() {

        System.out.println("ingame time 0 AND getlplayers size greater than 1 cancel return start postgame 15");
        if (time == 0 && arena.getPlayers().size() > 1) {
            cancel();
            if (!arena.getPostGame().isRunning()) {
                arena.getPostGame().startPostGame(15);
                return;
            }

            arena.broadcast(ChatHandler.chatPrefix + ChatColor.GOLD + "The game ended in a draw.");
            arena.getPostGame().startPostGame(15);
            for (UUID player : arena.getPlayers()) {
                arena.removePlayer(player);
            }
            return; // Get out of the run method.

        }
        System.out.println("ingame getplayers size == 1 cancel return start postgame 15");
        if (arena.getPlayers().size() == 1) {
            cancel();
            if (!arena.getPostGame().isRunning()) {
                arena.getPostGame().startPostGame(15);
                return;
            }

            UUID sgWinner = arena.players.get(0);
            Player winner = Bukkit.getPlayer(sgWinner);
            arena.broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + winner.getName() + " won the game!");
            arena.removePlayer(sgWinner);
            return;
        }
        System.out.println("ingame time == 0 OR players size < 5");
        if (time == 0 || arena.getPlayers().size() < 5) {

            cancel();
            if (!arena.getPreDeathmatch().isRunning()) {
                arena.getPreDeathmatch().startPreDeathmatch(15);
                return;
            }
            System.out.println("13");
            System.out.println("14");
            return; // Get out of the run method.
        }

        if (time % 15 == 0 || time <= 10) {
            // If the time is divisible by 15 then broadcast a countdown
            // message.
            if (time != 1) {
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.DARK_PURPLE + "Deathmatch will begin soon.");
            } else {
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.DARK_PURPLE + "Deathmatch will begin soon.");
            }
        }

        time--;
    }

    public boolean isRunning() {
        return arena.getState() == GameState.INGAME;
    }

}
