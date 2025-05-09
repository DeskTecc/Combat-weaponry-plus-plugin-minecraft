package me.helleo.cwp.items.weapons.spears;

import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSpear extends WeaponBase {
    static String weapon_type = "spear";
    public enum description {
        Line1("DescriptionSpear.Line1"),
        Line2("DescriptionSpear.Line2"),
        Line3("DescriptionSpear.Line3"),
        Line4("DescriptionSpear.Line4"),
        Line5("DescriptionSpear.Line5"),
        Line6("DescriptionSpear.Line6"),
        Line7("DescriptionSpear.Line7"),
        Line8("DescriptionSpear.Line8"),
        Line9("DescriptionSpear.Line9");

        private final String description_value;

        description(String value){
            this.description_value = value;
        }
        public String getValue(){
            return this.description_value;
        }
    }

    public static List<String> getLore(double attack_damage, double attack_speed){
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line1.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line2.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line3.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line4.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line5.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line6.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line7.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line8.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line9.getValue())));
        return setLore(lore, attack_damage,attack_speed);
    }

    public static double getAttackDamage(double attack_damage, String spearPath){
        if(ConfigurationsBool.UseCustomValues.getValue()){
            attack_damage = getCustomDamage(weapon_type, spearPath);
        }
        return attack_damage;
    }

    public static double getAttackSpeed(double attack_speed, String spearPath){
        if(ConfigurationsBool.UseCustomValues.getValue()){
            attack_speed = getCustomSpeed(weapon_type, spearPath);
        }
        return attack_speed;
    }

    public static boolean isSpear(ItemStack item){
        if(item.hasItemMeta()){
            if(item.getItemMeta().hasCustomModelData()) {
                return item.getItemMeta().getCustomModelData() == 1000004 ||
                        item.getItemMeta().getCustomModelData() == 1000014 ||
                        item.getItemMeta().getCustomModelData() == 1200004;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
