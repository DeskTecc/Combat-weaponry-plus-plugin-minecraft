package me.helleo.cwp.items.armors;

import me.helleo.cwp.CombatWeaponryPlus;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class ChainmailChestplate extends BaseArmor {

    static ItemStack item = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
    //static ItemMeta meta = item.getItemMeta();


    public ItemStack getArmorPiece() {
        return null;
    }


    public ShapedRecipe getArmorPieceRecipe() {

        //chainmail chestplate

        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "chainmail_chestplate");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(
                "C C",
                "CCC",
                "CCC");

        recipe.setIngredient('C', Material.CHAIN);

        return recipe;
    }

    public static void setArmorPieceRecipe(){
        Bukkit.addRecipe(new ChainmailChestplate().getArmorPieceRecipe());
    }
}