package me.helleo.cwp.items.armors;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;


public class EmeraldChestplate extends BaseArmor{

    static ItemStack item = new ItemStack(Material.GOLDEN_CHESTPLATE);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getArmorPiece(){

        //emerald chestplate

        double hp = 1;
        double def = 6;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            hp = ConfigurationsDouble.Armors_EmeraldChestplate_BonusHealth.getValue();
            def = ConfigurationsDouble.Armors_EmeraldChestplate_Armor.getValue();
        }
        AttributeModifier modifier = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.max_health"), hp,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.armor"), def,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier2);

        meta.setDisplayName(ChatColor.DARK_GREEN + "Emerald Chestplate");
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

    public ShapedRecipe getArmorPieceRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "emerald_chestplate");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getArmorPiece());

        recipe.shape(
                "E E",
                "EEE",
                "EEE");

        recipe.setIngredient('E', Material.EMERALD);

        return recipe;
    }

    public static void setArmorPieceRecipe(){
        Bukkit.addRecipe(new EmeraldChestplate().getArmorPieceRecipe());
    }
}
