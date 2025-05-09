package me.helleo.cwp.items.weapons.rapiers;

import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRapier extends WeaponBase {
    static String weapon_type = "rapier";
    public enum description {
        Line1("DescriptionRapier.Line1"),
        Line2("DescriptionRapier.Line2"),
        Line3("DescriptionRapier.Line3"),
        Line4("DescriptionRapier.Line4"),
        Line5("DescriptionRapier.Line5"),
        Line6("DescriptionRapier.Line6"),
        Line7("DescriptionRapier.Line7");

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
        return setLore(lore, attack_damage,attack_speed);
    }

    public static double getAttackDamage(double attack_damage, String rapierPath){
        if(ConfigurationsBool.UseCustomValues.getValue()){
            attack_damage = getCustomDamage(weapon_type, rapierPath);
        }
        return attack_damage;
    }

    public static double getAttackSpeed(double attack_speed, String rapierPath){
        if(ConfigurationsBool.UseCustomValues.getValue()){
            attack_speed = getCustomSpeed(weapon_type, rapierPath);
        }
        return attack_speed;
    }

    public static boolean isRapier(ItemStack item){
        if(item.hasItemMeta()){
            if(item.getItemMeta().hasCustomModelData()) {
                return item.getItemMeta().getCustomModelData() == 1000005 ||
                        item.getItemMeta().getCustomModelData() == 1000015 ||
                        item.getItemMeta().getCustomModelData() == 1200005;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
