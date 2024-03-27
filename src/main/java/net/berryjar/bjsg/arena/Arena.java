package net.berryjar.bjsg.arena;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.chest.ChestManager;
import net.berryjar.bjsg.cuboid.Cuboid;
import net.berryjar.bjsg.player.SGPlayer;
import net.berryjar.bjsg.timer.*;
import net.berryjar.bjsg.util.Helper;
import org.bukkit.*;
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
    public ChestManager chestManager;
    public ArrayList<UUID> players;
    public ArrayList<UUID> deadPlayers;
    public HashMap<UUID, Integer> intPlayers;
//    public HashMap<Integer, UUID> playersLinkedID;
    public LinkedHashMap<Integer, Location> spawns;



    Location lobbyLocation;
    private int lastSpawn;
    private final String arenaID;
    private final int requiredPlayers;
    public int playerID;

    public Arena(final BJSG plugin, Cuboid arenaRegion, String arenaID) {
        this.players = new ArrayList<UUID>();
        this.intPlayers = new HashMap<UUID, Integer>();
        this.plugin = plugin;
        this.arenaRegion = arenaRegion;
        this.arenaID = arenaID;
        this.requiredPlayers = 2;
        this.lobby = new Lobby(plugin, this);
        this.preGame = new PreGame(plugin, this);
        this.inGame = new InGame(plugin, this);
        this.preDeathmatch = new PreDeathmatch(plugin, this);
        this.deathmatch = new Deathmatch(plugin, this);
        this.postGame = new PostGame(plugin, this);
        this.state = GameState.STOPPED;
        this.deadPlayers = new ArrayList<UUID>();


        if (plugin.arenaSpawns.containsKey(arenaID)) {
            this.spawns = plugin.arenaSpawns.get(arenaID);
        } else {
            this.spawns = new LinkedHashMap<Integer, Location>();
        }

    }

    public void startArena() {

    }

    public Lobby getLobby() {
        return lobby;
    }
    public Location getLobbyLocation() {
        return lobbyLocation;
    }

    public void setLobby(Location loc) {
        this.lobbyLocation = loc;
    }

    public void addSpawns(int num, Location loc) {
        spawns.put(num, loc);
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
    public final Set<Location> openedChests = new HashSet<Location>();

    public void setState(GameState state) {
        this.state = state;
    }
    public boolean contains(UUID player) {
        return players.contains(player);
    }
    public String getId() {
        return arenaID;
    }

    public World getWorld() {
        return getArenaRegion().getWorld();
    }

    public void playSound(Sound sound, int v, int v1) {
        for (UUID player : players) {
            Player p = Bukkit.getPlayer(player);
            p.playSound(p, sound, v, v1);
        }
    }
    public void broadcast(String message) {


        for (UUID player : players) {
            if (player == null) {
                return;
            }
            Player p = Bukkit.getPlayer(player);
            p.sendMessage(message);
        }

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
        this.players.clear();
        this.state = GameState.LOBBY;
    }

    public void addDeadPlayer(UUID uuid) {
        deadPlayers.add(uuid);
    }
    public void removeDeadPlayer(UUID uuid) {
        deadPlayers.remove(uuid);
    }

    public ArrayList<UUID> getDeadPlayers() {
        return deadPlayers;
    }

    public void addPlayer(UUID uuid) {
        int i = playerID++;
        players.add(uuid);
        intPlayers.put(uuid, playerID);

        Helper.clearInventoryAndEffects(Bukkit.getPlayer(uuid));

    }
    public void removePlayer(UUID player) {

        Player p = Bukkit.getPlayer(player);
        Location loc = plugin.getPlayerJoinSGEndTeleport(player);
        p.teleport(loc);
        plugin.delPlayerJoinSGEndTeleport(player);
        broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + Bukkit.getPlayer(player).getName() + " has left the game.");
        deadPlayers.remove(player);
        players.remove(player);

    }

    public LinkedHashMap<Integer, Location> getSpawns() {
        return spawns;
    }
    public Location getSpawn(Integer spawnID) {
        for (Integer ID : spawns.keySet()) {
            if (spawnID.equals(ID)) {
                return spawns.get(ID);
            }
        }
        return null;
    }
    public void addSpawn(int spawnID, Location loc) {
        spawns.put(spawnID, loc);
    }
    public void removeSpawn() {
        int spawn = lastSpawn;
        spawns.remove(lastSpawn);
    }
    public void addLobby(Location loc) {
        lobbyLocation.add(loc);
    }
    public void stopArena() {

        for (UUID player : getPlayers()) {
            broadcast(ChatHandler.chatPrefix + ChatColor.DARK_RED + "The game has been stopped by an administrator.");
            removePlayer(player);
        }
        if (getState() == GameState.LOBBY) {
            getLobby().cancel();
        } else if (getState() == GameState.PREGAME) {
            getPreGame().cancel();
        } else if (getState() == GameState.INGAME) {
            getInGame().cancel();
        } else if (getState() == GameState.PREDEATHMATCH) {
            getPreDeathmatch().cancel();
        } else if (getState() == GameState.DEATHMATCH) {
            getDeathmatch().cancel();
        } else if (getState() == GameState.POSTGAME) {
            getPostGame().cancel();
        }
        setState(GameState.STOPPED);
        plugin.arenaCache.add(this);
    }

    public void checkTimeOutLogic() {

    }

}
