package net.berryjar.bjsg.timer;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.GameState;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.player.SGPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.units.qual.A;

import java.util.Locale;
import java.util.UUID;

public class PostGame extends BukkitRunnable {

    private int time;
    private final Arena arena;

    private final BJSG plugin;

    public PostGame(final BJSG plugin, Arena arena) {
        this.plugin = plugin;
        this.arena = arena;
        this.time = 0;
    }

    public void startPostGame(int time) {
        arena.setState(GameState.POSTGAME);
        this.time = time;
        this.runTaskTimer(plugin, 0L, 20L);
    }

    @Override
    public void run() {

        if (this.time == 0 && arena.getPlayers().size() == 1) {
            arena.broadcast(ChatHandler.chatPrefix + ChatColor.GOLD + "The game ended in a draw.");

            for (UUID player : arena.getPlayers()) {

                arena.removePlayer(player);
                for (UUID deadPlayer : arena.getDeadPlayers()) {
                    arena.removePlayer(deadPlayer);
                }
                Player p = Bukkit.getPlayer(player);
                plugin.sgPlayers.remove(player);
                return;
            }
            for (UUID player : arena.deadPlayers) {
                arena.removePlayer(player);
                Player p = Bukkit.getPlayer(player);
                plugin.sgPlayers.remove(player);
                return;
            }
            arena.getPlayers().clear();
            cancel();

            Location lobLoc = arena.getLobbyLocation();
            Arena arenaNew = new Arena(plugin, arena.getArenaRegion(), arena.getId());
            arenaNew.setLobby(lobLoc);
            arenaNew.startArena();

            arenaNew.getLobby().startLobby(15);

            arena.players.clear();
            arena.deadPlayers.clear();
            arena.intPlayers.clear();
            plugin.getActiveArenas().remove(arena);


            return;
        }
        if (this.time == 0 && arena.getPlayers().size() > 1) {
            arena.broadcast(ChatHandler.chatPrefix + ChatColor.GOLD + "The game ended in a draw.");

            for (UUID player : arena.getPlayers()) {

                arena.removePlayer(player);
                for (UUID deadPlayer : arena.getDeadPlayers()) {
                    arena.removePlayer(deadPlayer);
                }
                Player p = Bukkit.getPlayer(player);
                plugin.sgPlayers.remove(player);
                return;
            }
            for (UUID player : arena.deadPlayers) {
                arena.removePlayer(player);
                Player p = Bukkit.getPlayer(player);
                plugin.sgPlayers.remove(player);
                return;
            }
            arena.getPlayers().clear();
            cancel();

            Location lobLoc = arena.getLobbyLocation();
            Arena arenaNew = new Arena(plugin, arena.getArenaRegion(), arena.getId());
            arenaNew.setLobby(lobLoc);
            arenaNew.startArena();

            arenaNew.getLobby().startLobby(15);

            arena.players.clear();
            arena.deadPlayers.clear();
            arena.intPlayers.clear();
            plugin.getActiveArenas().remove(arena);


            return;
        }
        if (arena.getPlayers().size() > 1) {
            arena.broadcast(ChatHandler.chatPrefix + ChatColor.GOLD + "The game ended in a draw.");

            for (UUID player : arena.getPlayers()) {

                arena.removePlayer(player);
                for (UUID deadPlayer : arena.getDeadPlayers()) {
                    arena.removePlayer(deadPlayer);
                }
                Player p = Bukkit.getPlayer(player);
                plugin.sgPlayers.remove(player);
                return;
            }
            for (UUID player : arena.deadPlayers) {
                arena.removePlayer(player);
                Player p = Bukkit.getPlayer(player);
                plugin.sgPlayers.remove(player);
                return;
            }
            arena.getPlayers().clear();
            cancel();

            Location lobLoc = arena.getLobbyLocation();
            Arena arenaNew = new Arena(plugin, arena.getArenaRegion(), arena.getId());
            arenaNew.setLobby(lobLoc);
            arenaNew.startArena();

            arenaNew.getLobby().startLobby(15);

            arena.players.clear();
            arena.deadPlayers.clear();
            arena.intPlayers.clear();
            plugin.getActiveArenas().remove(arena);


            return;
        }


//
        if (time % 15 == 0 || time <= 10) {
            if (time != 1) {
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + "The arena will reset in " + time + " seconds.");
            } else {
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + "The arena will reset in " + time + " second.");
            }
        }

        time--;
    }
    public boolean isRunning() {
        return arena.getState() == GameState.LOBBY;
    }
}
