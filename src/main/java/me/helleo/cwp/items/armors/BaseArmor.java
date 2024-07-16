package me.helleo.cwp.items.armors;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class BaseArmor {

    abstract ItemStack getArmorPiece();

    abstract ShapedRecipe getArmorPieceRecipe();
}
