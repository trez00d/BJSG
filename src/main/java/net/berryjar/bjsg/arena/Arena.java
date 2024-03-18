package net.berryjar.bjsg.arena;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.cuboid.Cuboid;
import net.berryjar.bjsg.timer.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

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
    public ArrayList<UUID> players;
    private ArrayList<Location> spawns;
    private final String arenaID;
    private final int requiredPlayers;

    public Arena(final BJSG plugin, Cuboid arenaRegion, String arenaID) {
        this.plugin = plugin;
        this.arenaRegion = arenaRegion;
        this.players = new ArrayList<UUID>();
        this.arenaID = arenaID;
        this.requiredPlayers = 2;
        this.lobby = new Lobby(plugin, this);
        this.preGame = new PreGame(plugin, this);
        this.inGame = new InGame(plugin, this);
        this.preDeathmatch = new PreDeathmatch(plugin, this);
        this.deathmatch = new Deathmatch(plugin, this);
        this.postGame = new PostGame(plugin, this);
        this.spawns = new ArrayList<Location>();


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
    public boolean contains(UUID uuid) {
        return players.contains(uuid);
    }
    public String getId() {
        return arenaID;
    }
    public void broadcast(String message) {

        for (UUID u : players) {
            Player player = Bukkit.getPlayer(u);
            player.sendMessage(message);
        }
//        for (int i = 0; i < players.size(); i++) {
//
//            Bukkit.getPlayer(players.get(i)).sendMessage(message);
//        }
    }
    public ArrayList<UUID> getPlayers() {
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
        players.add(uuid);

        // Check whether to start the countdown. Make sure that the countdown
        // isn't already running.
        if (!lobby.isRunning() && players.size() >= requiredPlayers) {
            lobby.startLobby(30);
        }
    }
    public void removePlayer(UUID uuid) {
        players.remove(uuid);

        // Remove the kit data for the UUID from the arena.



        // Win detection. Check if the game has actually started first, though,
        // because if we don't do this, errors could occur.
        if (state == GameState.INGAME && players.size() == 1) {
            inGame.cancel();

            // Get the last player.
            Player winner = Bukkit.getPlayer(players.get(0));
            broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + winner.getName() + " won the game!");
//            Bukkit.broadcastMessage(
//                    ChatColor.GREEN + "" + ChatColor.BOLD + winner.getName() + " won arena " + arenaID + "!");

            removePlayer(winner.getUniqueId());

            // Reset the arena.
            reset();
        }
    }

    public void setLobbyNull() {
        this.lobby = null;
    }
    public void setLobbyRunnable() {
        this.lobby = new Lobby(plugin, this);
    }


}
