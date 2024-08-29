package me.helleo.cwp.items.shields;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class NetheriteShield {

    static ItemStack item = new ItemStack(Material.SHIELD);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getShield(){
        if (ConfigurationsBool.EnchantsNetheriteShield.getValue()) {
            int unbreakingValue = (int) ConfigurationsDouble.NetheriteShieldEnchantLevels_Unbreaking.getValue();

            meta.addEnchant(Enchantment.UNBREAKING, unbreakingValue, true);

        }
        meta.setDisplayName("Netherite Shield");
        meta.setCustomModelData(5430002);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getShieldRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "netheriteshield");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getShield());

        recipe.shape(
                "LeL",
                "LLL",
                " L ");

        recipe.setIngredient('L', Material.IRON_INGOT);
        recipe.setIngredient('e', Material.NETHERITE_INGOT);


        return recipe;
    }

    public static void setShieldRecipe(){
        Bukkit.addRecipe(new NetheriteShield().getShieldRecipe());
    }

}
