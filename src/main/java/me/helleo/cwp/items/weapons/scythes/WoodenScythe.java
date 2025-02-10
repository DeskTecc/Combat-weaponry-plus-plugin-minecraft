package me.helleo.cwp.items.weapons.scythes;

import me.helleo.cwp.configurations.ConfigurationsBool;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class WoodenScythe extends BaseScythe {
    static ItemStack item = new ItemStack(Material.WOODEN_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String scythePath = "WoodenScythe";

    public static ItemStack getScythe(){
        double attack_damage = 6;
        double attack_speed = -3;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = getCustomDamage(scythePath);
            attack_speed = getCustomSpeed(scythePath);
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, setModifier("generic.attack_speed",attack_speed));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, setModifier("generic.attack_damage",attack_damage));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', description.WoodenScythe.getValue()));
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
