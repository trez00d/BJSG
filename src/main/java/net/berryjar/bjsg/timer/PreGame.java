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
            System.out.println("pregame 1");
            int playerNum = arena.intPlayers.get(player);
            System.out.println(player + String.valueOf(playerNum));
            for (Arena a : plugin.activeArenas) {
                System.out.println("pregame 2");
                if (arena.getId().equals(a.getId())) {
                    plugin.arenaSpawns.put(a.getId(), a.getSpawns());
                    System.out.println("pregame 3");
                    if (a.getSpawns().containsKey(playerNum)) {
                        System.out.println("pregame 4");
                        Helper.clearInventoryAndEffects(Bukkit.getPlayer(player));
                        Helper.clearPotionEffects(Bukkit.getPlayer(player));
                        System.out.println(playerNum);
                        System.out.println(a.getSpawns());
                        System.out.println(a.getSpawn(playerNum));
                        Location spawnLoc = a.getSpawn(playerNum);
                        p.teleport(spawnLoc);
//                        Bukkit.getPlayer(player).teleport(a.getSpawn(playerNum));
                    }
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
        if (arena.getPlayers().isEmpty()) {
            cancel();
            arena.getPostGame().startPostGame(15);
        }

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
                arena.playSound(Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
            } else {
                arena.broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + "Game will start in " + time + " second.");
                arena.playSound(Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
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
