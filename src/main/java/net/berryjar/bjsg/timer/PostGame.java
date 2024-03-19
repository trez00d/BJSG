package net.berryjar.bjsg.timer;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.GameState;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.player.SGPlayer;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.units.qual.A;

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

        System.out.println("postgame time == 0");
        if (time == 0) {
            cancel();
            for (SGPlayer player : arena.getPlayers()) {
                arena.removePlayer(player);
            }
            Arena arenaNew = new Arena(plugin, arena.getArenaRegion(), arena.getId());
            arenaNew.getLobby().startLobby(15);
            plugin.activeArenas.remove(arena);

            plugin.activeArenas.add(arenaNew);
            return;
        }
        if (time == 0 && arena.getPlayers().size() > 1) {
            cancel();
            Arena arenaNew = new Arena(plugin, arena.getArenaRegion(), arena.getId());
            arenaNew.getLobby().startLobby(15);
            plugin.activeArenas.remove(arena);

            plugin.activeArenas.add(arenaNew);

            return; // Get out of the run method.

        }
        if (arena.getPlayers().size() == 1) {
            cancel();
            Arena arenaNew = new Arena(plugin, arena.getArenaRegion(), arena.getId());
            arenaNew.getLobby().startLobby(15);
            plugin.activeArenas.remove(arena);

            plugin.activeArenas.add(arenaNew);
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
