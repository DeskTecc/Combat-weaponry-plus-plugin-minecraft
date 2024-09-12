package me.helleo.cwp.items.misc;

import me.helleo.cwp.CombatWeaponryPlus;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class DragonBreath {

    public ItemStack getItem(){
        return new ItemStack(Material.FIRE_CHARGE,4);
    }

    public ShapedRecipe getItemRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "dragon_breath");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape(
                " A ",
                "GBL",
                " A ");

        recipe.setIngredient('A', Material.AMETHYST_SHARD);
        recipe.setIngredient('G', Material.GLOWSTONE);
        recipe.setIngredient('L', Material.LAPIS_LAZULI);
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(new ItemStack(Material.GLASS_BOTTLE,4)));

        return recipe;
    }

    public static void setItemRecipe(){
        Bukkit.addRecipe(new DragonBreath().getItemRecipe());
    }
}
