package me.helleo.cwp.items.armors;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class EmeraldHelmet extends BaseArmor {

    static ItemStack item = new ItemStack(Material.GOLDEN_HELMET);
    static ItemMeta meta = item.getItemMeta();


    public static ItemStack getArmorPiece() {
        double hp = 1;
        double def = 2;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            hp = ConfigurationsDouble.Armors_EmeraldHelmet_BonusHealth.getValue();
            def = ConfigurationsDouble.Armors_EmeraldHelmet_Armor.getValue();
        }

        AttributeModifier modifierHealth = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin, "Health"), hp,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifierHealth);
        AttributeModifier modifierDefense = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"Defense"), def,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifierDefense);

        meta.setDisplayName(ChatColor.DARK_GREEN + "Emerald Helmet");

        if (ConfigurationsBool.EnchantmentsOnEmeraldArmor.getValue()) {
            int unbreakingLevel = (int) ConfigurationsDouble.EmeraldArmorEnchantLevels_Unbreaking.getValue();
            int mendingLevel = (int) ConfigurationsDouble.EmeraldArmorEnchantLevels_Mending.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, unbreakingLevel, true);
            meta.addEnchant(Enchantment.MENDING, mendingLevel, true);
        }
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        return item;
    }


    public static ShapedRecipe getArmorPieceRecipe() {
        //emerald helmet

        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "emerald_helmet");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getArmorPiece());

        recipe.shape("EEE", "E E", "   ");

        recipe.setIngredient('E', Material.EMERALD);

        return recipe;
    }
}