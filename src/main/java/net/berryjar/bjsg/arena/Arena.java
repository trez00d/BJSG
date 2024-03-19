package net.berryjar.bjsg.arena;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.cuboid.Cuboid;
import net.berryjar.bjsg.player.SGPlayer;
import net.berryjar.bjsg.timer.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class Arena {

    private final BJSG plugin;
    private Cuboid arenaRegion;
    private Lobby lobby;
    private PreGame preGame;
    private InGame inGame;
    private PreDeathmatch preDeathmatch;
    private Deathmatch deathmatch;
    private PostGame postGame;
    private GameState state;
    public ArrayList<SGPlayer> players;
//    public HashMap<Integer, UUID> playersLinkedID;
    private LinkedHashMap<Integer, Location> spawns;
    private int lastSpawn;
    private final String arenaID;
    private final int requiredPlayers;
    private int playerID;

    public Arena(final BJSG plugin, Cuboid arenaRegion, String arenaID) {
        this.playerID = 0;
        this.plugin = plugin;
        this.arenaRegion = arenaRegion;
//        this.playersLinkedID = new HashMap<Integer, UUID>();
        this.arenaID = arenaID;
        this.requiredPlayers = 2;
        this.lobby = new Lobby(plugin, this);
        this.preGame = new PreGame(plugin, this);
        this.inGame = new InGame(plugin, this);
        this.preDeathmatch = new PreDeathmatch(plugin, this);
        this.deathmatch = new Deathmatch(plugin, this);
        this.postGame = new PostGame(plugin, this);
        if (plugin.getArenaSpawnsMap(arenaID) == null) {
            this.spawns = new LinkedHashMap<Integer, Location>();
        } else {
            this.spawns = plugin.getArenaSpawnsMap(arenaID);
        }
        if (this.getPlayers() == null) {
            this.players = new ArrayList<SGPlayer>();
        }



    }

    public void startArena() {

    }
    public Lobby getLobby() {
        return lobby;
    }

    public InGame getInGame() {
        return inGame;
    }
    public PreGame getPreGame() {
        return preGame;
    }
    public PreDeathmatch getPreDeathmatch() {
        return preDeathmatch;
    }
    public Deathmatch getDeathmatch() {
        return deathmatch;
    }
    public PostGame getPostGame() {
        return postGame;
    }
    public Cuboid getArenaRegion() { return arenaRegion; }

    public void setState(GameState state) {
        this.state = state;
    }
    public boolean contains(SGPlayer player) {
        return players.contains(player);
    }
    public String getId() {
        return arenaID;
    }
    public void broadcast(String message) {

        for (SGPlayer player : players) {
            Player p = player.getPlayer();
            p.sendMessage(message);
        }
//        for (int i = 0; i < players.size(); i++) {
//
//            Bukkit.getPlayer(players.get(i)).sendMessage(message);
//        }
    }
    public ArrayList<SGPlayer> getPlayers() {
        return players;
    }

    public int getRequiredPlayers() {
        return requiredPlayers;
    }
    public GameState getState() {
        return state;
    }
    public void reset() {
        // Clear all variables that should not continue their values into the
        // next round played in this arena.
        this.players.clear();
        this.state = GameState.LOBBY;
    }

    public void addPlayer(UUID uuid) {
        int i = playerID++;
        SGPlayer player = new SGPlayer(plugin, this.arenaID,uuid, i);
        players.add(player);
        // Check whether to start the countdown. Make sure that the countdown
        // isn't already running.
        if (!lobby.isRunning() && players.size() >= requiredPlayers) {
            lobby.startLobby(30);
        }
    }
    public void removePlayer(SGPlayer player) {
        players.remove(player);
        // Remove the kit data for the UUID from the arena.



        // Win detection. Check if the game has actually started first, though,
        // because if we don't do this, errors could occur.
        if (state == GameState.INGAME && players.size() == 1) {
            inGame.cancel();

            // Get the last player.
            Player winner = Bukkit.getPlayer(players.get(0).getPlayerUUID());
            broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + winner.getName() + " won the game!");
//            Bukkit.broadcastMessage(
//                    ChatColor.GREEN + "" + ChatColor.BOLD + winner.getName() + " won arena " + arenaID + "!");

            removePlayer(players.get(0));

            // Reset the arena.
            reset();
        }
    }

    public LinkedHashMap<Integer, Location> getSpawns() {
        return spawns;
    }
    public Location getSpawns(int input) {
        for (int i : spawns.keySet()) {
            if (i == input) {
                return spawns.get(i);
            }
        }
        return null;
    }
    public void addSpawn(int i, Location loc) {
        spawns.put(i, loc);
        this.lastSpawn = i;
    }
    public void removeSpawn() {
        int spawn = lastSpawn;
        spawns.remove(lastSpawn);
    }

    public void setLobbyNull() {
        this.lobby = null;
    }
    public void setLobbyRunnable() {
        this.lobby = new Lobby(plugin, this);
    }
    public int getLastSpawn() {
        return lastSpawn;
    }


}
