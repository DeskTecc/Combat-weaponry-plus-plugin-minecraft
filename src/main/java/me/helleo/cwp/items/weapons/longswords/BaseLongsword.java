package me.helleo.cwp.items.weapons.longswords;

import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseLongsword extends WeaponBase {
    static String weapon_type = "longsword";
    public enum description {
        Line1("DescriptionLongsword.Line1"),
        Line2("DescriptionLongsword.Line2"),
        Line3("DescriptionLongsword.Line3"),
        Line4("DescriptionLongsword.Line4"),
        Line5("DescriptionLongsword.Line5");

        private final String description_value;

        description(String value){
            this.description_value = value;
        }
        public String getValue(){
            return this.description_value;
        }
    }

    public static List<String> getLore(double attack_damage, double attack_speed) {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line1.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line2.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line3.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line4.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line5.getValue())));
        return setLore(lore, attack_damage,attack_speed);
    }

    public static double getAttackDamage(double attack_damage, String longswordPath){
        if(ConfigurationsBool.UseCustomValues.getValue()){
            attack_damage = getCustomDamage(weapon_type, longswordPath);
        }
        return attack_damage;
    }

    public static double getAttackSpeed(double attack_speed, String longswordPath){
        if(ConfigurationsBool.UseCustomValues.getValue()){
            attack_speed = getCustomSpeed(weapon_type, longswordPath);
        }
        return attack_speed;
    }

    public static boolean isLongsword(ItemStack item){
        if(item.hasItemMeta()){
            if(item.getItemMeta().hasCustomModelData()) {
                return item.getItemMeta().getCustomModelData() == 1000001 ||
                        item.getItemMeta().getCustomModelData() == 1000011 ||
                        item.getItemMeta().getCustomModelData() == 1200001;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
