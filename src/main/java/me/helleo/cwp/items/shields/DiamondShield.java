package me.helleo.cwp.items.shields;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigLoader;
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

public class DiamondShield {

    static ItemStack item = new ItemStack(Material.SHIELD);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getShield(){

        if (ConfigurationsBool.EnchantsDiamondShield.getValue()) {
            int unbreakingValue = (int) ConfigurationsDouble.DiamondShieldEnchantLevels_Unbreaking.getValue();

            meta.addEnchant(Enchantment.UNBREAKING, unbreakingValue, true);
        }

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("DescriptionDiamondShield.Name")));
        meta.setCustomModelData(5430001);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getShieldRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "diamondshield");
        CombatWeaponryPlus.getRecipes().setKey(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getShield());

        recipe.shape(
                "LeL",
                "LLL",
                " L ");

        recipe.setIngredient('L', Material.IRON_INGOT);
        recipe.setIngredient('e', Material.DIAMOND);

        return recipe;
    }

    public static void setShieldRecipe(){
        Bukkit.addRecipe(new DiamondShield().getShieldRecipe());
    }

}
