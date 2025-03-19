package me.helleo.cwp;

import me.helleo.cwp.configurations.ConfigurationsString;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Items {

    public static ItemStack witherBone() {

        ItemStack item = new ItemStack(Material.BONE);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta.setItemModel(new NamespacedKey("cwp","misc/wither_bone"));
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack vessel() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta vmeta = item.getItemMeta();
        assert vmeta != null;
        vmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionVessel_Name.getValue()));
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionVessel_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionVessel_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionVessel_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionVessel_Line4.getValue()));
        vmeta.setLore(lore);
        vmeta.setItemModel(new NamespacedKey("cwp","misc/vessel"));
        item.setItemMeta(vmeta);
        return item;
    }
}
