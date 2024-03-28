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
        arena.broadcast(ChatHandler.chatPrefix + ChatColor.DARK_PURPLE + "Deathmatch has begun!");
        arena.setState(GameState.DEATHMATCH);
        this.time = time;
        this.runTaskTimer(plugin, 0L, 20L);

        for (UUID player : arena.getPlayers()) {
            Player p = Bukkit.getPlayer(player);
            int playerNum = arena.intPlayers.get(player);
            if (arena.getSpawns().containsKey(playerNum)) {
                Location spawnLoc = arena.getSpawn(playerNum);
                p.teleport(spawnLoc);
            }
        }
    }

    @Override
    public void run() {

        if (time == 0) {
            cancel();
            if (!arena.getPostGame().isRunning()) {
                arena.getPostGame().startPostGame(15);
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
