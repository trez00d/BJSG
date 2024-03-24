package net.berryjar.bjsg;

import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.arena.ArenaManager;
import net.berryjar.bjsg.chest.ChestManager;
import net.berryjar.bjsg.chest.LootItem;
import net.berryjar.bjsg.command.CommandManager;
import net.berryjar.bjsg.cuboid.Cuboid;
import net.berryjar.bjsg.cuboid.CuboidManager;
import net.berryjar.bjsg.listener.*;
import net.berryjar.bjsg.magicwand.MagicWand;
import net.berryjar.bjsg.magicwand.Tuple;
import net.berryjar.bjsg.magicwand.WandManager;
import net.berryjar.bjsg.player.LooseWrapper;
import net.berryjar.bjsg.player.SGPlayer;
import net.berryjar.bjsg.util.Manager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

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
    public OpenChestListener openChestListener;
    public ChestManager chestManager;
    public WandManager wandManager;
    public MagicWand magicWand;
    public ArrayList<Arena> activeArenas = new ArrayList<Arena>();
    public HashSet<Cuboid> activeRegions = new HashSet<Cuboid>();
    public HashSet<UUID> wandPrime = new HashSet<UUID>();
    public Tuple<Location, Location> clipboardMap = new Tuple<Location, Location>(null, null);
    public HashMap<String, LinkedHashMap<Integer, Location>> arenaSpawns = new HashMap<String, LinkedHashMap<Integer, Location>>();
    public HashMap<String, Location> arenaLobbies = new HashMap<String, Location>();
    public HashSet<SGPlayer> sgPlayers = new HashSet<SGPlayer>();
    public HashSet<UUID> looseWrapperPlayer = new HashSet<UUID>();
    public HashMap<UUID, Location> playerJoinSGEndTeleport = new HashMap<UUID, Location>();

    public ArrayList<Arena> arenaCache = new ArrayList<Arena>();



    public void grantMagicWand(UUID u) {
        Bukkit.getPlayer(u).getInventory().addItem(magicWand.getMagicWand());
    }
    public ItemStack getMagicWand() {
        return this.magicWand.getMagicWand();
    }

    public void createLooseWrapper(UUID u) {
        looseWrapperPlayer.add(u);
    }
    public void peelLooseWrapper(UUID u) {
        looseWrapperPlayer.remove(u);
    }

    public LinkedHashMap<Integer, Location> getArenaSpawnsMap(String arenaID) {
        for (String ID : arenaSpawns.keySet()) {
            if (ID.equalsIgnoreCase(arenaID)) {
                return arenaSpawns.get(arenaID);
            }

        }
        return null;
    }
    public Location getLobbySpawns(String arenaID) {
        if (arenaLobbies.containsKey(arenaID)) {
            return arenaLobbies.get(arenaID);
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
        openChestListener = new OpenChestListener(this);
        magicWand = new MagicWand();
//        conMan = new ConMan(this);
//        saveResource("loot.yml", false);
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getServer().getPluginManager().registerEvents(new LeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new PreventionListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerWandInteract(this), this);
        getServer().getPluginManager().registerEvents(new DamageListener(this), this);
        getServer().getPluginManager().registerEvents(new OpenChestListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerSignClick(), this);


        File config = new File(this.getDataFolder(), "config.yml");

        if (!(config.exists())) {
            saveDefaultConfig();
            saveConfig();
            getLogger().log(Level.INFO, "Default config.yml generated.");
        } else {
            getLogger().log(Level.INFO, "Default config.yml already exists, not generating a new one.");
        }
//        saveResource("loot.yml", false);

//        configMan = new ConfigMan(this);
        chestManager = new ChestManager(getConfig());
//

//        FileConfiguration conf = conMan.getLootConfig();



//        System.out.println("test" + spawns.toString());
        regionManager.loadRegions(getConfig());
        arenaManager.loadArenaSpawns(getConfig());
        arenaManager.loadLobbySpawns(getConfig());





//        System.out.println("from BJSG: " + arenaLobbies);
//
//        System.out.println(arenaSpawns);
//        for (String arenaID : spawnsa.getKeys(false)) {
//            ConfigurationSection arena = getConfig().getConfigurationSection(arenaID);
//            System.out.println(arena);
//        }
//        for (String spawns : getConfig().getConfigurationSection("spawns").getKeys(false)) {
//            System.out.println("test " + spawns);
//
//            ConfigurationSection spawnsSec = getConfig().getConfigurationSection(spawns);
//            System.out.println("test test " + spawnsSec);
////            for (String arenaID : spawnsSec.getKeys(false)) {
////                ConfigurationSection arenaSec = getConfig().getConfigurationSection(arenaID);
////                arenaManager.loadArenaSpawns(arenaSec);
////            }
////        }
////        for (String spawns : getConfig().getConfigurationSection("spawns").getKeys(false)) {
////            ConfigurationSection lobbySec = getConfig().getConfigurationSection(spawns);
////            for (String arenaID : lobbySec.getKeys(false)) {
////                ConfigurationSection arenaSec = getConfig().getConfigurationSection(arenaID);
////                arenaManager.loadLobbySpawns(arenaSec);
////            }
//        }


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
