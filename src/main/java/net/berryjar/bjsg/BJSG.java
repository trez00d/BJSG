package net.berryjar.bjsg;

import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.ArenaManager;
import net.berryjar.bjsg.command.CommandManager;
import net.berryjar.bjsg.cuboid.Cuboid;
import net.berryjar.bjsg.cuboid.CuboidManager;
import net.berryjar.bjsg.listener.*;
import net.berryjar.bjsg.magicwand.Tuple;
import net.berryjar.bjsg.magicwand.WandManager;
import net.berryjar.bjsg.player.SGPlayer;
import net.berryjar.bjsg.util.Manager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;
import java.util.logging.Level;

public final class BJSG extends JavaPlugin {
    public CommandManager commandManager;
    public ArenaManager arenaManager;
    public Manager manager;
    public CuboidManager regionManager;
    public DeathListener deathListener;
    public LeaveListener leaveListener;
    public PlayerWandInteract playerWandInteract;
    public WandManager wandManager;
    public ArrayList<Arena> activeArenas = new ArrayList<Arena>();
    public HashSet<Cuboid> activeRegions = new HashSet<Cuboid>();
    public HashSet<UUID> wandPrime = new HashSet<UUID>();
    public Tuple<Location, Location> clipboardMap = new Tuple<Location, Location>(null, null);
    public HashMap<String, LinkedHashMap<Integer, Location>> arenaSpawns = new HashMap<String, LinkedHashMap<Integer, Location>>();
    public HashSet<SGPlayer> sgPlayers = new HashSet<SGPlayer>();

    public LinkedHashMap<Integer, Location> getArenaSpawnsMap(String arenaID) {
        for (String ID : arenaSpawns.keySet()) {
            if (ID.equalsIgnoreCase(arenaID)) {
                return arenaSpawns.get(arenaID);
            }

        }
        return null;
    }

    @Override
    public void onEnable() {

        // Plugin startup logic
        getCommand("bjsg").setExecutor(new CommandManager(this));
        manager = new Manager(this);
        commandManager = new CommandManager(this);
        regionManager = new CuboidManager(this);
        deathListener = new DeathListener(this);
        leaveListener = new LeaveListener(this);
        wandManager = new WandManager(this);
        arenaManager = new ArenaManager(this);
        playerWandInteract = new PlayerWandInteract(this);
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getServer().getPluginManager().registerEvents(new LeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new PreventionListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerWandInteract(this), this);
        getServer().getPluginManager().registerEvents(new DamageListener(this), this);
        File config = new File(this.getDataFolder(), "config.yml");

        if (!(config.exists())) {
            saveDefaultConfig();
            saveConfig();
            getLogger().log(Level.INFO, "Default config.yml generated.");
        } else {
            getLogger().log(Level.INFO, "Default config.yml already exists, not generating a new one.");
        }

        regionManager.loadRegions();
        arenaManager.loadArenaSpawns();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
