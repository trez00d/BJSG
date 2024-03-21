package net.berryjar.bjsg.timer;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.GameState;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.player.SGPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
        arena.players.clear();
    }

    @Override
    public void run() {

        System.out.println("postgame time == 0");
        if (time == 0) {
            cancel();
            for (UUID player : arena.getPlayers()) {
                arena.removePlayer(player);
            }
            arena.players.clear();
            plugin.activeArenas.remove(arena);
            Location lobLoc = arena.getLobbyLocation();
            Arena arenaNew = new Arena(plugin, arena.getArenaRegion(), arena.getId());
            arenaNew.setLobby(lobLoc);
            arenaNew.startArena();

            arenaNew.getLobby().startLobby(15);

            return;
        }
        if (time == 0 && arena.getPlayers().size() > 1) {
            cancel();
            plugin.activeArenas.remove(arena);
            Location lobLoc = arena.getLobbyLocation();
            Arena arenaNew = new Arena(plugin, arena.getArenaRegion(), arena.getId());
            arenaNew.setLobby(lobLoc);
            arenaNew.startArena();
            arenaNew.getLobby().startLobby(15);



            return; // Get out of the run method.

        }
        if (arena.getPlayers().size() == 1) {
            cancel();
            plugin.activeArenas.remove(arena);
            Location lobLoc = arena.getLobbyLocation();
            Arena arenaNew = new Arena(plugin, arena.getArenaRegion(), arena.getId());
            arenaNew.setLobby(lobLoc);
            arenaNew.startArena();
            arenaNew.getLobby().startLobby(15);
            return;
        }

        if (time % 15 == 0 || time <= 10) {
            // If the time is divisible by 15 then broadcast a countdown
            // message.
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
