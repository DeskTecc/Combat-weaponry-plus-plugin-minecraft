package me.helleo.cwp.items.tools;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public abstract class BaseTool {

    abstract ItemStack getTool();
    abstract ShapedRecipe getToolRecipe();
}
