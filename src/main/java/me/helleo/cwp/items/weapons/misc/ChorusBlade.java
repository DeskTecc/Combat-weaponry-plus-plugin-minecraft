package me.helleo.cwp.items.weapons.misc;

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
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ChorusBlade {

    static ItemStack item = new ItemStack(Material.IRON_SWORD);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getTool(){

        //modifier
        double dmg = 3;
        double spd = 6;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Swords_ChorusBlade_Damage.getValue();
            spd = ConfigurationsDouble.Swords_ChorusBlade_Speed.getValue();
        }
        AttributeModifier modifier = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.attack_speed"), spd,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.attack_damage"), dmg,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionChorusBlade_Name.getValue()));

        if (ConfigurationsBool.EnchantsChorusBlade.getValue()) {
            int unbreakingLevel = (int) ConfigurationsDouble.ChorusEnchantLevels_Unbreaking.getValue();
            int knockbackLevel = (int) ConfigurationsDouble.ChorusEnchantLevels_Knockback.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, unbreakingLevel, true);
            meta.addEnchant(Enchantment.KNOCKBACK, knockbackLevel, true);
        }
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionChorusBlade_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionChorusBlade_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionChorusBlade_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionChorusBlade_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionChorusBlade_Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionChorusBlade_Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionChorusBlade_Line7.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&',"&9 "+dmg+" Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 "+spd+" Attack Speed"));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        meta.setCustomModelData(1000007);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getToolRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "chorusblade");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getTool());

        recipe.shape(
                " E ",
                "PCP",
                "qBq");

        recipe.setIngredient('E', Material.END_ROD);
        recipe.setIngredient('P', Material.ENDER_EYE);
        recipe.setIngredient('C', Material.CHORUS_FLOWER);
        recipe.setIngredient('B', Material.BLAZE_ROD);
        recipe.setIngredient('q', Material.END_CRYSTAL);

        return recipe;
    }

    public static void setToolRecipe(){
        Bukkit.addRecipe(new ChorusBlade().getToolRecipe());
    }
}
