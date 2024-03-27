package net.berryjar.bjsg.timer;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.arena.GameState;
import net.berryjar.bjsg.player.SGPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class Lobby extends BukkitRunnable{

    private int time;
    private final Arena arena;

    private final BJSG plugin;

    public Lobby(final BJSG plugin, Arena arena) {
        this.plugin = plugin;
        this.arena = arena;
        this.time = 0;
    }

    public void startLobby(int time) {
        arena.setState(GameState.LOBBY);
        this.time = time;
        this.runTaskTimer(plugin, 0L, 20L);
        if (plugin.getActiveArenas().contains(arena)) {
            return;
        } else {
            plugin.getActiveArenas().add(arena);
        }


    }

    @Override
    public void run() {

        arena.broadcast(arena.getPlayers().toString());

        if (this.time == 0) {
            cancel();
            if (!arena.getPreGame().isRunning()) {
                arena.getPreGame().startPreGame(15);
                return;
            }
            return; // Get out of the run method.
        }
//
        if (time % 15 == 0 || time <= 10) {

            if (time != 1) {
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + "Game will start in " + time + " seconds.");
            } else {
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + "Game will start in " + time + " second.");
            }
        }
//
//
        if (arena.getPlayers().size() < arena.getRequiredPlayers()) {
            arena.setState(GameState.LOBBY);
            arena.broadcast(ChatHandler.chatPrefix + ChatColor.RED + "There are too few players. Countdown stopped.");
            return;
        }

        time--;
    }

    public boolean isRunning() {
        return arena.getState() == GameState.LOBBY;
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

    private void resetLobby() {
        this.time = 0;
    }

}
