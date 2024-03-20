package net.berryjar.bjsg.chest;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class ChestManager {

    private final Set<Location> openedChests = new HashSet<Location>();
    private final List<LootItem> lootItems = new ArrayList<LootItem>();

    public ChestManager(FileConfiguration lootConfig) {
        ConfigurationSection itemsSection = lootConfig.getConfigurationSection("lootItems");

        if (itemsSection == null) {
            Bukkit.getLogger().severe("Please setup your 'lootItems' in the 'loot.yml'");
        }

        for (String key : itemsSection.getKeys(false)) {
            ConfigurationSection section = itemsSection.getConfigurationSection(key);
            lootItems.add(new LootItem(section));
        }
    }

    public void markAsOpened(Location location) {
        openedChests.add(location);
    }

    public boolean hasBeenOpened(Location location) {
        return openedChests.contains(location);
    }

    public void resetChests() {
        openedChests.clear();
    }

    public void fill(Inventory inventory) {
        inventory.clear();

        ThreadLocalRandom random = ThreadLocalRandom.current();
        Set<LootItem> used = new HashSet<>();

        for (int slotIndex = 0; slotIndex < inventory.getSize(); slotIndex++) {
            LootItem randomItem = lootItems.get(random.nextInt(lootItems.size()));
            if (used.contains(randomItem)) continue;
            used.add(randomItem);

            if (randomItem.shouldFill(random)) {
                ItemStack itemStack = randomItem.make(random);
                inventory.setItem(slotIndex, itemStack);
            }
        }

    }


}
