package me.helleo.cwp.items.weapons.sabers;

import me.helleo.cwp.configurations.ConfigurationsBool;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class StoneSaber extends BaseSaber{

    static ItemStack item = new ItemStack(Material.STONE_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String saberPath = "StoneSaber";

    public static ItemStack getSaber() {
        double attack_damage = 4;
        double attack_speed = -2.4;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = getCustomDamage(saberPath);
            attack_speed = getCustomSpeed(saberPath);
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, setModifier("generic.attack_speed", attack_speed));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, setModifier("generic.attack_damage", attack_damage));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', description.StoneSaber.getValue()));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);
        return item;
    }

    public static void setSaberRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                "saber",
                "stone_saber",
                getSaber(),
                Material.COBBLESTONE));
    }
}
