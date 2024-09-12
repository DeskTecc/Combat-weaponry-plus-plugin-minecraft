package me.helleo.cwp.items.tools;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import me.helleo.cwp.configurations.ConfigurationsString;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ObsidianPickaxe extends BaseTool{

    static ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getTool() {
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionObsidianPickaxe_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionObsidianPickaxe_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionObsidianPickaxe_Line3.getValue()));
        meta.setLore(lore);

        if (ConfigurationsBool.EnchantsObsidianPickaxe.getValue()) {
            int unbreakingValue = (int) ConfigurationsDouble.ObsidianEnchantLevels_Unbreaking.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, unbreakingValue, true);
        }

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionObsidianPickaxe_Name.getValue()));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getToolRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "obsidian_pickaxe");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getTool());

        recipe.shape(
                "OOO",
                "DBD",
                " B ");

        recipe.setIngredient('B', Material.BLAZE_ROD);
        recipe.setIngredient('O', Material.CRYING_OBSIDIAN);
        recipe.setIngredient('D', Material.DIAMOND);
        return recipe;
    }

    public static void setToolRecipe(){
        Bukkit.addRecipe(new ObsidianPickaxe().getToolRecipe());
    }
}
