package me.helleo.cwp.items.weapons.rapiers;

import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class WoodenRapier extends BaseRapier{

    static ItemStack item = new ItemStack(Material.WOODEN_SWORD);
    static ItemMeta meta = item.getItemMeta();

    public static ItemStack getRapier() {
        double attack_damage = 2;
        double attack_speed = -2.1;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = ConfigurationsDouble.Rapiers_WoodenRapier_Damage.getValue();
            attack_speed = ConfigurationsDouble.Rapiers_WoodenRapier_Speed.getValue();
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, setModifier("generic.attack_speed", attack_speed));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, setModifier("generic.attack_damage", attack_damage));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName(Material.OAK_WOOD)));
        meta.setCustomModelData(1000005);
        item.setItemMeta(meta);
        return item;
    }

    public static void setRapierRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                "rapier",
                "wooden_rapier",
                getRapier(),
                Material.STICK));
    }
}
