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

public class RepeatingCrossbow extends BaseBow{

    static ItemStack item = new ItemStack(Material.CROSSBOW);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getBow() {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRepeatingCrossbow_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRepeatingCrossbow_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRepeatingCrossbow_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRepeatingCrossbow_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRepeatingCrossbow_Line5.getValue()));
        meta.setLore(lore);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRepeatingCrossbow_Name.getValue()));
        meta.setCustomModelData(5552001);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getBowRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "repeater_crossbow");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getBow());

        recipe.shape(
                "III",
                "rRr",
                " H ");

        recipe.setIngredient('I', Material.IRON_NUGGET);
        recipe.setIngredient('H', Material.TRIPWIRE_HOOK);
        recipe.setIngredient('r', Material.REDSTONE_TORCH);
        recipe.setIngredient('R', Material.REPEATER);

        return recipe;
    }

    public static void setBowRecipe(){
        Bukkit.addRecipe(new RepeatingCrossbow().getBowRecipe());
    }
}
