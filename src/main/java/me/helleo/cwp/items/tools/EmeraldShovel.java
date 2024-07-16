package me.helleo.cwp.items.tools;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class EmeraldShovel extends BaseTool{

    static ItemStack item = new ItemStack(Material.GOLDEN_SHOVEL);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getTool() {

        //emerald shovel

        meta.setDisplayName(ChatColor.DARK_GREEN + "Emerald Shovel");
        if (ConfigurationsBool.EnchantmentsOnEmeraldGear.getValue()){
            int unbreakingLevel = (int) ConfigurationsDouble.EmeraldGearEnchantLevels_Unbreaking.getValue();
            int mendingLevel = (int) ConfigurationsDouble.EmeraldGearEnchantLevels_Mending.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, unbreakingLevel, true);
            meta.addEnchant(Enchantment.MENDING, mendingLevel, true);
        }
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getToolRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "emerald_shovel");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getTool());

        recipe.shape(" E ", " S ", " S ");

        recipe.setIngredient('E', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public static void setToolRecipe(){
        Bukkit.addRecipe(new EmeraldShovel().getToolRecipe());
    }
}
