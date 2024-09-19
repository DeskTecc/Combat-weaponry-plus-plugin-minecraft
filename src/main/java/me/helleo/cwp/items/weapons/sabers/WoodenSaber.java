package me.helleo.cwp.items.weapons.sabers;

import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class WoodenSaber extends BaseSaber{

    static ItemStack item = new ItemStack(Material.WOODEN_SWORD);
    static ItemMeta meta = item.getItemMeta();

    public static ItemStack getSaber(){
        double attack_damage = 3;
        double attack_speed = -2.4;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = ConfigurationsDouble.Sabers_WoodenSaber_Damage.getValue();
            attack_speed = ConfigurationsDouble.Sabers_WoodenSaber_Speed.getValue();
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, setModifier("generic.attack_speed", attack_speed));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, setModifier("generic.attack_damage", attack_damage));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName(Material.OAK_WOOD)));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);
        return item;
    }

    public static void setSaberRecipe(){
        Bukkit.addRecipe(getWeaponRecipe("saber","wooden_saber",getSaber(),Material.STICK));
    }

}
