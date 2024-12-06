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

public class WoodenScythe extends BaseScythe {
    static ItemStack item = new ItemStack(Material.WOODEN_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String scytheType = "Wooden";

    public static ItemStack getScythe(){
        double attack_damage = 6;
        double attack_speed = -3;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = getCustomDamage(scytheType);
            attack_speed = getCustomSpeed(scytheType);
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
                new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                        attack_speed,
                        AttributeModifier.Operation.ADD_NUMBER,
                        EquipmentSlotGroup.HAND
                )
        );
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                        attack_damage,
                        AttributeModifier.Operation.ADD_NUMBER,
                        EquipmentSlotGroup.HAND
                )
        );

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName(Material.OAK_WOOD)));
        meta.setCustomModelData(1000003);
        item.setItemMeta(meta);
        return item;
    }

    public static void setScytheRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                "scythe",
                "wooden_scythe",
                getScythe(),
                Material.STICK));
    }
}
