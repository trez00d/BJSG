package net.berryjar.bjsg.timer;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.GameState;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.player.SGPlayer;
import net.berryjar.bjsg.util.Helper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;
import java.util.UUID;

public class PreGame extends BukkitRunnable {

    private int time;
    private final BJSG plugin;
    private final Arena arena;

    public PreGame(final BJSG plugin, Arena arena) {
        this.plugin = plugin;
        this.arena = arena;
        this.time = 0;
    }

    public void startPreGame(int time) {


        arena.setState(GameState.PREGAME);
        this.time = time;
        this.runTaskTimer(plugin, 0L, 20L);
        arena.broadcast(ChatHandler.chatPrefix + ChatColor.GOLD + "Lobby time has ended. The game will start soon.");

        for (UUID player : arena.getPlayers()) {
            Player p = Bukkit.getPlayer(player);
            int playerNum = arena.intPlayers.get(player);
            for (Arena a : plugin.getActiveArenas()) {
                if (arena.getId().equals(a.getId())) {
                    plugin.arenaSpawns.put(a.getId(), a.getSpawns());
                    if (a.getSpawns().containsKey(playerNum)) {
                        Helper.clearInventoryAndEffects(Bukkit.getPlayer(player));
                        Helper.clearPotionEffects(Bukkit.getPlayer(player));
                        Location spawnLoc = a.getSpawn(playerNum);
                        p.teleport(spawnLoc);
//                        Bukkit.getPlayer(player).teleport(a.getSpawn(playerNum));
                    }
                }


            }
        }
    }
    @Override
    public void run() {
        arena.broadcast("in pregame");
        arena.broadcast(arena.getPlayers().toString());

        if (time == 0) {

            cancel();
            if (!arena.getInGame().isRunning()) {
                arena.getInGame().startInGame(15);
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
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + "Game will start in " + time + " seconds.");
                arena.playSound(Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
            } else {
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + "Game will start in " + time + " second.");
                arena.playSound(Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
            }
        }

        time--;
    }
    public boolean isRunning() {
        return arena.getState() == GameState.PREGAME;
    }

}
