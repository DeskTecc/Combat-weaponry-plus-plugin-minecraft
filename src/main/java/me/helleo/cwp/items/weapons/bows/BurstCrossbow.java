package me.helleo.cwp.items.weapons.bows;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsString;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BurstCrossbow extends BaseBow{

    static ItemStack item = new ItemStack(Material.CROSSBOW);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getBow() {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionBurstCrossbow_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionBurstCrossbow_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionBurstCrossbow_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionBurstCrossbow_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionBurstCrossbow_Line5.getValue()));

        meta.setLore(lore);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionBurstCrossbow_Name.getValue()));
        meta.setCustomModelData(5552002);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getBowRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "burst_crossbow");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getBow());

        recipe.shape(
                "IBI",
                "TRT",
                "rHr");

        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('B', Material.REDSTONE_BLOCK);
        recipe.setIngredient('r', Material.REDSTONE);
        recipe.setIngredient('T', Material.REDSTONE_TORCH);
        recipe.setIngredient('R', Material.REPEATER);
        recipe.setIngredient('H', Material.TRIPWIRE_HOOK);

        return recipe;
    }

    public static void setBowRecipe(){
        Bukkit.addRecipe(new BurstCrossbow().getBowRecipe());
    }
}
