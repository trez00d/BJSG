package net.berryjar.bjsg.timer;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.GameState;
import net.berryjar.bjsg.chat.ChatHandler;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

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
    }
    @Override
    public void run() {

        if (time == 0) {

            System.out.println("pregame time 0 cancel return start ingame 15");
            cancel();
            if (!arena.getInGame().isRunning()) {
                arena.getInGame().startInGame(15);
                return;
            }

            return; // Get out of the run method.
        }

        if (time % 15 == 0 || time <= 10) {
            // If the time is divisible by 15 then broadcast a countdown
            // message.
            if (time != 1) {
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + "Game will start in " + time + " seconds.");
            } else {
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + "Game will start in " + time + " second.");
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
        return arena.getState() == GameState.PREGAME;
    }
}
