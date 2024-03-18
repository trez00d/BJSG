package net.berryjar.bjsg.magicwand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MagicWand {

    public ItemStack magicWandStack;


    public MagicWand() {
        ItemStack magicWand = new ItemStack(Material.BLAZE_ROD);
        ItemMeta wandMeta = magicWand.getItemMeta();
        wandMeta.setDisplayName(ChatColor.GOLD + "Region Wand");
        magicWand.setItemMeta(wandMeta);
        magicWandStack = magicWand;
    }

    public ItemStack getMagicWand() { return magicWandStack; }



}
