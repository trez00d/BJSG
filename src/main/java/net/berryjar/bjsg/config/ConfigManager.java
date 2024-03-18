package net.berryjar.bjsg.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ConfigManager {

    private static File folder;
    private static File bankFile;
    private FileConfiguration bankConfig;

    public ConfigManager(String arenaID) {
        folder = new File(Bukkit.getServer().getPluginManager().getPlugin("BJSG").getDataFolder(), "arenas");
        bankFile =  new File(folder, arenaID + ".yml");
        bankConfig = YamlConfiguration.loadConfiguration(bankFile);
    }


    public void createFile() {

        if (!folder.exists()) {
            folder.mkdirs();

        }
        if (!bankFile.exists()) {
            try {
                bankFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public FileConfiguration getBankConfig() {

        return bankConfig;

    }

    public void saveBankConfig() {

        try {

            bankConfig.save(bankFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}