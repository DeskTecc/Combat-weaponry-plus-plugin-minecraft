package me.helleo.cwp.items.weapons.spears;

import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class DiamondSpear extends BaseSpear{

    static ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
    static ItemMeta meta = item.getItemMeta();

    public static ItemStack getSpear() {
        double attack_damage = 3;
        double attack_speed = -1.5;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = ConfigurationsDouble.Spears_DiamondSpear_Damage.getValue();
            attack_speed = ConfigurationsDouble.Spears_DiamondSpear_Speed.getValue();
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, setModifier("generic.attack_speed",attack_speed));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, setModifier("generic.attack_damage",attack_damage));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName(Material.DIAMOND)));
        meta.setCustomModelData(1000004);
        item.setItemMeta(meta);
        return item;
    }

    public static void setSpearRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                "spear",
                "diamond_spear",
                getSpear(),
                Material.DIAMOND));
    }
}
