package me.helleo.cwp.items.armors;

import me.helleo.cwp.CombatWeaponryPlus;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class ChainmailLeggings extends BaseArmor {

    static ItemStack item = new ItemStack(Material.CHAINMAIL_LEGGINGS);
    //static ItemMeta meta = item.getItemMeta();


    /*public static ItemStack getArmorPiece() {

    } */


    public static ShapedRecipe getArmorPieceRecipe() {

        //chainmail leggings

        ItemStack item = new ItemStack(Material.CHAINMAIL_LEGGINGS);

        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "chainmail_leggings");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(
                "CCC",
                "C C",
                "C C");

        recipe.setIngredient('C', Material.CHAIN);

        return recipe;
    }
}