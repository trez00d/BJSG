package net.berryjar.bjsg.arena;

import net.berryjar.bjsg.BJSG;
import net.berryjar.bjsg.chat.ChatHandler;
import net.berryjar.bjsg.chest.ChestManager;
import net.berryjar.bjsg.cuboid.Cuboid;
import net.berryjar.bjsg.player.SGPlayer;
import net.berryjar.bjsg.timer.*;
import net.berryjar.bjsg.util.Helper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
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
        System.out.println("ARENA CONST " + players);
        this.intPlayers = new HashMap<UUID, Integer>();
        System.out.println("ARENA CONST " + intPlayers);
        System.out.println("ARENA CONST " + playerID);
        this.plugin = plugin;
        System.out.println("ARENA CONST " + plugin);
        this.arenaRegion = arenaRegion;
        System.out.println("ARENA CONST " + arenaRegion);

//        this.playersLinkedID = new HashMap<Integer, UUID>();
        this.arenaID = arenaID;
        System.out.println("ARENA CONST " + arenaID);
        this.requiredPlayers = 2;
        System.out.println("ARENA CONST " + requiredPlayers);
        this.lobby = new Lobby(plugin, this);
        this.preGame = new PreGame(plugin, this);
        this.inGame = new InGame(plugin, this);
        this.preDeathmatch = new PreDeathmatch(plugin, this);
        this.deathmatch = new Deathmatch(plugin, this);
        this.postGame = new PostGame(plugin, this);

        this.deadPlayers = new ArrayList<UUID>();


        if (plugin.arenaSpawns.containsKey(arenaID)) {
            this.spawns = plugin.arenaSpawns.get(arenaID);
        } else {
            this.spawns = new LinkedHashMap<Integer, Location>();
        }

 //        if (this.spawns == null) {
//            this.spawns = plugin.getArenaSpawnsMap(this.arenaID);
//        }
//        if (this.lobbyLocation == null) {
//            this.lobbyLocation = plugin.getLobbySpawns(arenaID);
//        }
//        if (!(this.getId() == null)) {
//            return;
////            this.lobbyLocation = new ArrayList<Location>();
//        } else {
//            Location loc = plugin.getLobbySpawns(arenaID);
////            this.lobbyLocation = new ArrayList<Location>();
//            this.lobbyLocation = loc;
//            if (!(plugin.arenaLobbies.containsKey(this.arenaID))) {
//                plugin.arenaLobbies.put(this.arenaID, loc);
//            }
//
//        }
//        if (this.players == null) {
//            this.players = new ArrayList<SGPlayer>();
//        }







    }
//    public ArrayList<Location> getLobbySpawnMap() {
//        return lobbyLocation;
//    }

    public void startArena() {
//        plugin.activeArenas.add(this);


    }
//    public void stopArena(Arena arena) {
//        plugin.arenaCache.add(arena);
//
//        arena.stopArena();
//        arena.setState(GameState.STOPPED);
//
//    }
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
//    public Location getLobbySpawn() {
//        return lobbyLocation.get(0);
//    }
//    public int getLobbySetSize() {
//        return lobbyLocation.size();
//    }
//    public void removeLobbySpawns() {
//        lobbyLocation.clear();
//    }

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
        int i = playerID++;
        System.out.println("player joined arena " + arenaID);
        System.out.println(getSpawns());
        System.out.println(Bukkit.getPlayer(uuid) + String.valueOf(playerID));



//        System.out.println("SANSAN");
//        System.out.println(players);
        players.add(uuid);
        intPlayers.put(uuid, playerID);
        // Check whether to start the countdown. Make sure that the countdown
        // isn't already running.
//        System.out.println("add player test");
//        System.out.println(lobby.isRunning());
//        System.out.println(players.size());
        if (!lobby.isRunning() && players.size() >= requiredPlayers) {

//            System.out.println("add player test");
            lobby.startLobby(30);
        }
        Helper.clearInventoryAndEffects(Bukkit.getPlayer(uuid));

    }
    public void removePlayer(UUID player) {
        players.remove(player);
        Player p = Bukkit.getPlayer(player);
        Location loc = plugin.playerJoinSGEndTeleport.get(player);
        p.teleport(loc);
        plugin.playerJoinSGEndTeleport.remove(player);
        broadcast(ChatHandler.chatPrefix + ChatColor.GREEN + Bukkit.getPlayer(player).getName() + " has left the game.");
        for (Arena a : plugin.activeArenas) {
            a.deadPlayers.remove(p.getUniqueId());
        }
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

            removePlayer(players.get(0));

            // Reset the arena.
            reset();
        }
    }

    public LinkedHashMap<Integer, Location> getSpawns() {
        return spawns;
    }
    public Location getSpawn(Integer spawnID) {
        for (Integer ID : spawns.keySet()) {
//            System.out.println(spawns.keySet());
//            System.out.println("gs2");
//            System.out.println(ID);
            if (spawnID.equals(ID)) {
//                System.out.println("gs3");
//                System.out.println(spawns.get(ID));
                return spawns.get(ID);


            }
        }
        return null;
    }
    public void addSpawn(int spawnID, Location loc) {
        spawns.put(spawnID, loc);
//        this.lastSpawn = i;
    }
    public void removeSpawn() {
        int spawn = lastSpawn;
        spawns.remove(lastSpawn);
    }
    public void addLobby(Location loc) {
        lobbyLocation.add(loc);
    }
    public void stopArena() {
        System.out.println("stop 1");

        for (UUID player : getPlayers()) {
            System.out.println("stop 2");
            broadcast(ChatHandler.chatPrefix + ChatColor.DARK_RED + "The game has been stopped by an administrator.");
            removePlayer(player);
        }
        if (getState() == GameState.LOBBY) {
            System.out.println("stop 3");
            getLobby().cancel();
            System.out.println("stop 4");
        } else if (getState() == GameState.PREGAME) {
            System.out.println("stop 5");
            getPreGame().cancel();
            System.out.println("stop 6");
        } else if (getState() == GameState.INGAME) {
            System.out.println("stop 7");
            getInGame().cancel();
            System.out.println("stop 8");
        } else if (getState() == GameState.PREDEATHMATCH) {
            System.out.println("stop 9");
            getPreDeathmatch().cancel();
            System.out.println("stop 10");
        } else if (getState() == GameState.DEATHMATCH) {
            System.out.println("stop 11");
            getDeathmatch().cancel();
            System.out.println("stop 12");
        } else if (getState() == GameState.POSTGAME) {
            System.out.println("stop 13");
            getPostGame().cancel();
            System.out.println("stop 14");
        }
        setState(GameState.STOPPED);
        System.out.println("stop 15");
        plugin.arenaCache.add(this);
        System.out.println("stop 16");


    }

//    public void setLobbyNull() {
//        this.lobby = null;
//    }
//    public void setLobbyRunnable() {
//        this.lobby = new Lobby(plugin, this);
//    }
//    public int getLastSpawn() {
//        return lastSpawn;
//    }


}
