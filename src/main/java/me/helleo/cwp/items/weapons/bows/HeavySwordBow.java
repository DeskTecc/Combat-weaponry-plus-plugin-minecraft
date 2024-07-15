package me.helleo.cwp.items.weapons.bows;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import me.helleo.cwp.configurations.ConfigurationsString;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class HeavySwordBow {
    static ItemStack item = new ItemStack(Material.BOW);
    static ItemMeta meta = item.getItemMeta();

    public static ItemStack getTool() {

        //modifier
        double damage = 10;
        double speed = -3.2;
        double moveSpeed = -0.05;
        double offHandMoveSpeed = -0.05;
        double KBResist = 0.5;
        double offHandKBResist = 0.5;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            damage = (int) ConfigurationsDouble.Bows_HeavySwordBow_Damage.getValue();
            speed = (int) ConfigurationsDouble.Bows_HeavySwordBow_Speed.getValue();
            moveSpeed = (int) ConfigurationsDouble.Bows_HeavySwordBow_MoveSpeed.getValue();
            offHandMoveSpeed = (int) ConfigurationsDouble.Bows_HeavySwordBow_OffhandMoveSpeed.getValue();
            KBResist = (int) ConfigurationsDouble.Bows_HeavySwordBow_KBResist.getValue();
            offHandKBResist = (int) ConfigurationsDouble.Bows_HeavySwordBow_OffhandKBResist.getValue();
        }


        if (ConfigurationsBool.EnchantsHeavySwordBow.getValue()) {
            int enchantmentsPower = (int) ConfigurationsDouble.HeavySwordBowEnchantLevels_Power.getValue();
            int enchantmentsUnbreaking = (int) ConfigurationsDouble.HeavySwordBowEnchantLevels_Unbreaking.getValue();
            int enchantmentsSmite = (int) ConfigurationsDouble.HeavySwordBowEnchantLevels_Smite.getValue();
            int enchantmentsMending = (int) ConfigurationsDouble.HeavySwordBowEnchantLevels_Mending.getValue();
            meta.addEnchant(Enchantment.POWER, enchantmentsPower, true);
            meta.addEnchant(Enchantment.UNBREAKING, enchantmentsUnbreaking, true);
            meta.addEnchant(Enchantment.SMITE, enchantmentsSmite, true);
            meta.addEnchant(Enchantment.MENDING, enchantmentsMending, true);
        }

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionHeavySwordBow_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionHeavySwordBow_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionHeavySwordBow_Line3.getValue()));
        meta.setLore(lore);

        AttributeModifier modifier = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin, "Attack Speed"), speed,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin, "Attack Damage"), damage,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        //speed
        AttributeModifier modifier3 = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin, "Speed"), moveSpeed,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier3);
        AttributeModifier modifier4 = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin, "Speed"), offHandMoveSpeed,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier4);

        //knockback res
        AttributeModifier modifier5 = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin, "KnockbackRes"), KBResist,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifier5);
        AttributeModifier modifier6 = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin, "KnockbackRes"), offHandKBResist,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND);
        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifier6);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionHeavySwordBow_Name.getValue()));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        return item;
    }

    public static ShapedRecipe getToolRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "heavy_sword_bow");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getTool());

        recipe.shape("ISs", "SCs", "ISs");

        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('s', Material.CHAIN);
        recipe.setIngredient('I', Material.NETHERITE_SCRAP);
        recipe.setIngredient('C', Material.NETHERITE_SWORD);

        return recipe;
    }
}
