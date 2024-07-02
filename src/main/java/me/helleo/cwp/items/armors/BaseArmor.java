package me.helleo.cwp.items.armors;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class BaseArmor {
    ItemStack item;
    ItemMeta meta;

    public static ItemStack getArmorPiece(){
        return null;
    }

    public static ShapedRecipe getArmorPieceRecipe() {
        return null;
    }
}
