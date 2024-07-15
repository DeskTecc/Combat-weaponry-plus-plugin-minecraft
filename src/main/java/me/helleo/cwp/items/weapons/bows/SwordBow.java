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

public class SwordBow {
    static ItemStack item = new ItemStack(Material.BOW);
    static ItemMeta meta = item.getItemMeta();

    public static ItemStack getTool() {
        //sword bow

        //modifier
        double dmg = 8;
        double spd = -3;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Bows_SwordBow_Damage.getValue();
            spd = ConfigurationsDouble.Bows_SwordBow_Speed.getValue();
        }
        AttributeModifier modifier = new AttributeModifier( new NamespacedKey(CombatWeaponryPlus.plugin, "Attack Speed"), spd,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin, "Attack Damage"), dmg,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionSwordBow_Name.getValue()));

        if (ConfigurationsBool.EnchantsSwordBow.getValue()) {
            int enchantmentSmite = (int) ConfigurationsDouble.SwordBowEnchantLevels_Smite.getValue();
            int enchantmentUnbreaking = (int) ConfigurationsDouble.SwordBowEnchantLevels_Unbreaking.getValue();

            int enchantmentMending = (int) ConfigurationsDouble.SwordBowEnchantLevels_Mending.getValue();
            meta.addEnchant(Enchantment.SMITE, enchantmentSmite, true);
            meta.addEnchant(Enchantment.UNBREAKING, enchantmentUnbreaking, true);
            meta.addEnchant(Enchantment.MENDING, enchantmentMending, true);
        }

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionSwordBow_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionSwordBow_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionSwordBow_Line3.getValue()));


        meta.setLore(lore);
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        return item;
    }

    public static ShapedRecipe getToolRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "sword_bow");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getTool());

        recipe.shape(
                "ISs",
                "SCs",
                "ISs");

        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('s', Material.STRING);
        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('C', Material.IRON_SWORD);

        return recipe;
    }
}
