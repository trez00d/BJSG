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
//            for (i                System.out.println("lobby time 0 cancel return start pregame 15");nt i : arena.getSpawns().keySet()) {
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
        arena.broadcast(arena.getPlayers().toString());


        if (time == 0) {
            cancel();
            if (!arena.getPostGame().isRunning()) {
                arena.getPostGame().startPostGame(15);
                return;
            }
//            arena.reset();
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
//            System.out.println(arena.getId() + "dm playersize isempty");
//            arena.reset();
//            cancel();
//            arena.getPostGame().startPostGame(15);
//        }


//        if (arena.getPlayers().size() == 1) {
//
//            if (!arena.getPostGame().isRunning()) {
//                arena.getPostGame().startPostGame(30);
//                arena.broadcast(arena.getPlayers().get(0).toString() + " has won the game!");
//                cancel();
//                return;
//
//            }
//
////            arena.broadcast(arena.getPlayers().get(0).toString() + " has won the game!");
//            arena.reset();
//            return;
//        }


//        if (time == 0 && arena.getPlayers().size() > 1) {
//            if (!arena.getPostGame().isRunning()) {
//                arena.broadcast(ChatHandler.chatPrefix + ChatColor.GOLD + "The game ended in a draw.");
//                arena.getPostGame().startPostGame(15);
//                cancel();
//                return;
//            }
//
//
//
//            return; // Get out of the run method.
//
//        }



        if (time % 15 == 0 || time <= 10) {
            // If the time is divisible by 15 then broadcast a countdown
            // message.
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
