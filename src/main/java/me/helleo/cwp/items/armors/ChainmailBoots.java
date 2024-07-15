package me.helleo.cwp.items.armors;

import me.helleo.cwp.CombatWeaponryPlus;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class ChainmailBoots extends BaseArmor {

    static ItemStack item = new ItemStack(Material.CHAINMAIL_BOOTS);
    //static ItemMeta meta = item.getItemMeta();

    /*public static ItemStack getArmorPiece() {

    } */


    public static ShapedRecipe getArmorPieceRecipe() {

        //chainmail boots

        ItemStack item = new ItemStack(Material.CHAINMAIL_BOOTS);

        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "chainmail_boots");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(
                "   ",
                "C C",
                "C C");

        recipe.setIngredient('C', Material.CHAIN);

        return recipe;
    }
}