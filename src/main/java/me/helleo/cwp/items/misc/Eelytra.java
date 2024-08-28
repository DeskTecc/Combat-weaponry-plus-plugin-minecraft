package me.helleo.cwp.items.misc;

import me.helleo.cwp.CombatWeaponryPlus;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Eelytra {

    static ItemStack item = new ItemStack(Material.ELYTRA);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getItem(){
        List<String> lore = new ArrayList<String>();

        lore.add("");

        lore.add(ChatColor.GRAY + "test");
        lore.add(ChatColor.GRAY + "(not really meant to be");
        lore.add(ChatColor.GRAY + "obtainable yet but you can");
        lore.add(ChatColor.GRAY + "test it in creative or something)");
        lore.add("");

        meta.setLore(lore);

        meta.setDisplayName(ChatColor.GOLD + "Eelytra");
        meta.setCustomModelData(1560001);

        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getItemRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "eelytra");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape(
                "LCL",
                "CBC",
                "LCL");

        recipe.setIngredient('B', Material.ELYTRA);
        recipe.setIngredient('C', Material.EXPERIENCE_BOTTLE);
        recipe.setIngredient('L', Material.BEDROCK);

        return recipe;
    }

    public static void setItemRecipe(){
        Bukkit.addRecipe(new Eelytra().getItemRecipe());
    }

}
