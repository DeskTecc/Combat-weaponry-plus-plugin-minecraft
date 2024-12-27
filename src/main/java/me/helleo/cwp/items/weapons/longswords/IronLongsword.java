package me.helleo.cwp.items.weapons.longswords;

import me.helleo.cwp.configurations.ConfigurationsBool;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class IronLongsword extends BaseLongsword{

    static ItemStack item = new ItemStack(Material.IRON_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String longswordPath = "IronLongsword";

    public static ItemStack getLongsword() {
        double attack_damage = 6;
        double attack_speed = -2.8;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = getCustomDamage(longswordPath);
            attack_speed = getCustomSpeed(longswordPath);
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

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', description.IronLongsword.getValue()));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        return item;
    }

    public static void setLongswordRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                "longsword",
                "iron_longsword",
                getLongsword(),
                Material.IRON_INGOT));
    }
}
