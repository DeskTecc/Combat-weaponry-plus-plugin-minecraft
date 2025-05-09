package me.helleo.cwp.items.weapons.scythes;

import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseScythe extends WeaponBase {
    static String weapon_type = "scythe";
    public enum description {
        Line1("DescriptionScythe.Line1"),
        Line2("DescriptionScythe.Line2"),
        Line3("DescriptionScythe.Line3"),
        Line4("DescriptionScythe.Line4"),
        Line5("DescriptionScythe.Line5"),
        Line6("DescriptionScythe.Line6"),
        Line7("DescriptionScythe.Line7");

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

    public static double getAttackDamage(double attack_damage, String scythePath){
        if(ConfigurationsBool.UseCustomValues.getValue()){
            attack_damage = getCustomDamage(weapon_type, scythePath);
        }
        return attack_damage;
    }

    public static double getAttackSpeed(double attack_speed, String scythePath){
        if(ConfigurationsBool.UseCustomValues.getValue()){
            attack_speed = getCustomSpeed(weapon_type, scythePath);
        }
        return attack_speed;
    }

    public static boolean isScythe(ItemStack item){
        if(item.hasItemMeta()){
            if(item.getItemMeta().hasCustomModelData()) {
                return item.getItemMeta().getCustomModelData() == 1000003 ||
                        item.getItemMeta().getCustomModelData() == 1000013 ||
                        item.getItemMeta().getCustomModelData() == 1200003;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
