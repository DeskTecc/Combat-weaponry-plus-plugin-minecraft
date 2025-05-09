package me.helleo.cwp.items.weapons.sabers;

import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSaber extends WeaponBase {
    static String weapon_type = "saber";
    public enum description {
        Line1("DescriptionSaber.Line1"),
        Line2("DescriptionSaber.Line2"),
        Line3("DescriptionSaber.Line3"),
        Line4("DescriptionSaber.Line4");

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
        return setLore(lore, attack_damage,attack_speed);
    }

    public static double getAttackDamage(double attack_damage, String saberPath){
        if(ConfigurationsBool.UseCustomValues.getValue()){
            attack_damage = getCustomDamage(weapon_type, saberPath);
        }
        return attack_damage;
    }

    public static double getAttackSpeed(double attack_speed, String saberPath){
        if(ConfigurationsBool.UseCustomValues.getValue()){
            attack_speed = getCustomSpeed(weapon_type, saberPath);
        }
        return attack_speed;
    }

    public static boolean isSaber(ItemStack item){
        if(item.hasItemMeta()){
            if(item.getItemMeta().hasCustomModelData()) {
                return item.getItemMeta().getCustomModelData() == 1000010 ||
                        item.getItemMeta().getCustomModelData() == 1000030 ||
                        item.getItemMeta().getCustomModelData() == 1200010;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
