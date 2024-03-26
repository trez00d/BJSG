package net.berryjar.bjsg.timer;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.GameState;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.player.SGPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
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
        arena.playSound(Sound.BLOCK_NOTE_BLOCK_BELL, 1, 5);
    }

    @Override
    public void run() {

        arena.broadcast(arena.getPlayers().toString());


        if (time == 0) {
            cancel();
            if (!arena.getPreDeathmatch().isRunning()) {
                arena.getPreDeathmatch().startPreDeathmatch(15);
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


//        if (arena.getPlayers().isEmpty()) {
//            cancel();
//            arena.getPostGame().startPostGame(15);
//        }


//        if (arena.getPlayers().size() == 1) {
//            arena.reset();
//            cancel();
//            if (!arena.getPostGame().isRunning()) {
//                arena.getPostGame().startPostGame(15);
//                return;
//            }
//
//            UUID sgWinner = arena.players.get(0);
//            Player winner = Bukkit.getPlayer(sgWinner);
//            arena.broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + winner.getName() + " won the game!");
//            arena.removePlayer(sgWinner);
//            return;
//        }
//        System.out.println("ingame time 0 AND getlplayers size greater than 1 cancel return start postgame 15");
//        if (time == 0 && arena.getPlayers().size() > 1) {
//            cancel();
//            arena.reset();
//            if (!arena.getPreDeathmatch().isRunning()) {
//                arena.getPreDeathmatch().startPreDeathmatch(15);
//                return;
//            }
//            return; // Get out of the run method.
//
//        }


//        if (arena.getPlayers().size() < 5) {
//
//            cancel();
//            if (!arena.getPreDeathmatch().isRunning()) {
//                arena.getPreDeathmatch().startPreDeathmatch(15);
//                return;
//            }
//            return; // Get out of the run method.
//        }

        if (time % 15 == 0 || time <= 10) {
            // If the time is divisible bya 15 then broadcast a countdown
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
