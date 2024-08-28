package me.helleo.cwp.items.misc;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsString;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ReallyGoodSword {

    static ItemStack item = new ItemStack(Material.NETHERITE_HOE);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getItem(){
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionReallyGoodSword_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionReallyGoodSword_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionReallyGoodSword_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionReallyGoodSword_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionReallyGoodSword_Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionReallyGoodSword_Line6.getValue()));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.setLore(lore);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionReallyGoodSword_Name.getValue()));
        meta.setCustomModelData(1234567);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getItemRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "reallygoodsword");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape(
                "LLL",
                "fef",
                "fsf");

        recipe.setIngredient('L', Material.LAPIS_BLOCK);
        recipe.setIngredient('e', Material.GOLD_BLOCK);
        recipe.setIngredient('s', Material.DIAMOND_BLOCK);
        recipe.setIngredient('f', Material.REDSTONE);


        return recipe;
    }

    public static void setItemRecipe(){
        Bukkit.addRecipe(new ReallyGoodSword().getItemRecipe());
    }
}
