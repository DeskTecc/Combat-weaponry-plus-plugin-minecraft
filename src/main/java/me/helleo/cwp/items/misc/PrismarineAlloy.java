package me.helleo.cwp.items.misc;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsString;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PrismarineAlloy {

    static ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getItem(){

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineAlloy_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineAlloy_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineAlloy_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineAlloy_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineAlloy_Line5.getValue()));

        meta.setLore(lore);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineAlloy_Name.getValue()));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setCustomModelData(9999901);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getItemRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "prisupgrade");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape(
                "LCL",
                "IBI",
                "LDL");

        recipe.setIngredient('B', Material.NETHERITE_INGOT);
        recipe.setIngredient('L', Material.PRISMARINE_SHARD);
        recipe.setIngredient('D', Material.DIAMOND_BLOCK);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('C', Material.PRISMARINE_CRYSTALS);

        return recipe;
    }

    public static void setItemRecipe(){
        Bukkit.addRecipe(new PrismarineAlloy().getItemRecipe());
    }

}
