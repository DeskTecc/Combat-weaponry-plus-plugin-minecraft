package me.helleo.cwp.items.weapons.katanas;

import me.helleo.cwp.configurations.ConfigurationsBool;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class NetheriteKatana extends BaseKatana{

    static ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String katanaPath = "NetheriteKatana";

    private static Material getNetherite(){
        if(ConfigurationsBool.NetheriteIngots.getValue()){
            return Material.NETHERITE_INGOT;
        }else{
            return Material.NETHERITE_SCRAP;
        }
    }


    public static ItemStack getKatana() {
        double attack_damage = 6;
        double attack_speed = -2.3;
        double move_speed = 0.02;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = getCustomDamage(katanaPath);
            attack_speed = getCustomSpeed(katanaPath);
            move_speed = getCustomMoveSpeed(katanaPath);
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, setModifier("generic.attack_speed",attack_speed));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, setModifier("generic.attack_damage",attack_damage));
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, setModifier("generic.move_speed",move_speed));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', description.NetheriteKatana.getValue()));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        return item;
    }

    public static void setKatanaRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                "katana",
                "netherite_katana",
                getKatana(),
                getNetherite()));
    }
}
