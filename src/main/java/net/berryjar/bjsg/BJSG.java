package net.berryjar.bjsg;

import net.berryjar.bjsg.arena.Arena;
import net.berryjar.bjsg.command.CommandManager;
import net.berryjar.bjsg.cuboid.Cuboid;
import net.berryjar.bjsg.cuboid.CuboidManager;
import net.berryjar.bjsg.listener.*;
import net.berryjar.bjsg.magicwand.Tuple;
import net.berryjar.bjsg.magicwand.WandManager;
import net.berryjar.bjsg.util.Manager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Level;

public final class BJSG extends JavaPlugin {
    public CommandManager commandManager;
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
        playerWandInteract = new PlayerWandInteract(this);
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getServer().getPluginManager().registerEvents(new LeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new PreventionListener(), this);
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
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
