package me.helleo.cwp.items.weapons.scythes;

import me.helleo.cwp.configurations.ConfigurationsBool;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class GoldenScythe extends BaseScythe{

    static ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String scytheType = "Golden";

    public static ItemStack getScythe() {
        double attack_damage = 6;
        double attack_speed = -2.8;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = getCustomDamage(scytheType);
            attack_speed = getCustomSpeed(scytheType);
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED).add(
                new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                        attack_speed,
                        AttributeModifier.Operation.ADD_NUMBER,
                        EquipmentSlotGroup.HAND
                )
        );
        meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).add(
                new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                        attack_damage,
                        AttributeModifier.Operation.ADD_NUMBER,
                        EquipmentSlotGroup.HAND
                )
        );

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName(Material.GOLD_INGOT)));
        meta.setCustomModelData(1000003);
        item.setItemMeta(meta);
        return item;
    }

    public static void setScytheRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                "scythe",
                "golden_scythe",
                getScythe(),
                Material.GOLD_INGOT));
    }
}
