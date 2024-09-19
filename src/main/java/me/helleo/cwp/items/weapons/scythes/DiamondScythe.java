package me.helleo.cwp.items.weapons.scythes;

import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class DiamondScythe extends BaseScythe{

    static ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
    static ItemMeta meta = item.getItemMeta();

    public static ItemStack getScythe() {
        double attack_damage = 8;
        double attack_speed = -3;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = ConfigurationsDouble.Scythes_DiamondScythe_Damage.getValue();
            attack_speed = ConfigurationsDouble.Scythes_DiamondScythe_Speed.getValue();
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, setModifier("generic.attack_speed", attack_speed));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, setModifier("generic.attack_damage", attack_damage));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName(Material.DIAMOND)));
        meta.setCustomModelData(1000003);
        item.setItemMeta(meta);
        return item;
    }

    public static void setScytheRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                "scythe",
                "diamond_scythe",
                getScythe(),
                Material.DIAMOND));
    }
}
