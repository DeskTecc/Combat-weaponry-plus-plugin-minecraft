package me.helleo.cwp.items.weapons.bows;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import me.helleo.cwp.configurations.ConfigurationsString;
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

import java.util.ArrayList;
import java.util.List;

public class HeavySwordBow extends BaseBow{
    static ItemStack item = new ItemStack(Material.BOW);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getBow() {

        //modifier
        double attack_damage = 10;
        double attack_speed = -3.2;
        double move_speed = -0.05;
        double offhand_move_speed = -0.05;
        double knockback_resistance = 0.5;
        double offhand_knockback_resistance = 0.5;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = (int) ConfigurationsDouble.Bows_HeavySwordBow_Damage.getValue();
            attack_speed = (int) ConfigurationsDouble.Bows_HeavySwordBow_Speed.getValue();
            move_speed = (int) ConfigurationsDouble.Bows_HeavySwordBow_MoveSpeed.getValue();
            offhand_move_speed = (int) ConfigurationsDouble.Bows_HeavySwordBow_OffhandMoveSpeed.getValue();
            knockback_resistance = (int) ConfigurationsDouble.Bows_HeavySwordBow_KBResist.getValue();
            offhand_knockback_resistance = (int) ConfigurationsDouble.Bows_HeavySwordBow_OffhandKBResist.getValue();
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

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionHeavySwordBow_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionHeavySwordBow_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionHeavySwordBow_Line3.getValue()));
        meta.setLore(lore);

        AttributeModifier modifierAttackDamage = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.attack_damage"), attack_damage,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifierAttackDamage);
        AttributeModifier modifierAttackSpeed = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.attack_speed"), attack_speed,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifierAttackSpeed);


        //speed
        AttributeModifier modifierMoveSpeed = new AttributeModifier(NamespacedKey.minecraft("generic.move_speed"), move_speed,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifierMoveSpeed);
        AttributeModifier modifierOffHandMoveSpeed = new AttributeModifier(NamespacedKey.minecraft("generic.move_speed_offhand"), offhand_move_speed,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifierOffHandMoveSpeed);

        //knockback res
        AttributeModifier modifierKnockbackResistance = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin, "knockback_resistance"), knockback_resistance,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifierKnockbackResistance);
        AttributeModifier modifierOffHandKnockbackResistance = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin, "knockback_resistance_offhand"), offhand_knockback_resistance,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND);
        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifierOffHandKnockbackResistance);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionHeavySwordBow_Name.getValue()));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getBowRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "heavy_sword_bow");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getBow());

        recipe.shape(
                "ISs",
                "SCs",
                "ISs");

        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('s', Material.CHAIN);
        recipe.setIngredient('I', Material.NETHERITE_SCRAP);
        recipe.setIngredient('C', Material.NETHERITE_SWORD);

        return recipe;
    }

    public static void setBowRecipe(){
        Bukkit.addRecipe(new HeavySwordBow().getBowRecipe());
    }
}
