package me.helleo.cwp.items.weapons.misc;

import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChorusBlade {

    static ItemStack item = new ItemStack(Material.IRON_SWORD);
    static ItemMeta meta = item.getItemMeta();

    public static ItemStack getTool(){


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dChorusBlade.name")));

        if (ConfigurationsBool.EnchantsChorusBlade.getValue()) {
            int unbreakingLevel = (int) ConfigurationsDouble.ChorusEnchantLevels_Unbreaking.getValue();
            int knockbackLevel = (int) ConfigurationsDouble.ChorusEnchantLevels_Knockback.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, unbreakingLevel, true);
            meta.addEnchant(Enchantment.KNOCKBACK, knockbackLevel, true);
        }
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dChorusBlade.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dChorusBlade.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dChorusBlade.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dChorusBlade.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dChorusBlade.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dChorusBlade.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dChorusBlade.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dChorusBlade.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dChorusBlade.line9")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);

        //modifier
        double dmg = 3;
        double spd = 6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aChorusBlade.damage") - 1;
            spd = this.getConfig().getDouble("aChorusBlade.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.setCustomModelData(1000007);
        item.setItemMeta(meta);
    }
}
