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

        for (SGPlayer player : arena.getPlayers()) {
            int playerNum = player.getID();
            System.out.println(arena.getSpawns());
            System.out.println(player.getPlayer().getName() + " " + playerNum);
            for (Arena a : plugin.activeArenas) {
                if (a.getSpawns().containsKey(playerNum)) {
                    Helper.clearInventoryAndEffects(player.getPlayer());
                    Helper.clearPotionEffects(player.getPlayer());
                    player.getPlayer().teleport(a.getSpawn(playerNum));
                }
//            for (int i = 0; i < arena.getSpawns().size(); i++) {
//                Location loc = arena.getSpawn(i);
//                System.out.println(loc);
//            }
//            for (int i = 0; i < arena.getSpawns().size(); i++) {
//                Location loc = arena.getSpawn(i);
//
//                System.out.println(player.getPlayer().getName() + playerNum);
//                System.out.println(loc + "loading from map");
//                if (playerNum == i) {
//                    Player p = player.getPlayer();
//                    p.teleport(loc);
//                }
//                System.out.println("test");
//            }
//            for (int i : arena.getSpawns().keySet()) {
//                Location loc = arena.getSpawns().get(i);
//                if (playerNum == i) {
//                    player.getPlayer().teleport(loc);
//                }
//            }
//            for (int i = 0; i < arena.getSpawns().size(); i++) {
//                Location loc  = arena.getSpawns(i);
//                Player p = player.getPlayer();
//                p.teleport(loc);
//            }
//            int numSpawns = arena.getSpawns();
//            int randomIndex = new Random().nextInt(numSpawns);
//            if (randomIndex < 0 || randomIndex >= 2) {
//                Location loc = arena.getSpawns().get(randomIndex);
//                p.teleport(loc);
//            } else {
//                continue;
//            }

//            for (int i = 0; i < arena.getSpawns().keySet().size(); i++) {
//                Location loc = arena.getSpawns().get(i);
//                player.teleport(loc);
//            }
//            for (int i : arena.getSpawns().keySet()) {
//                Location loc = arena.getSpawns().get(i);
//                player.teleport(loc);
//            }
            }
        }
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
