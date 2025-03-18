package me.helleo.cwp.items.weapons.scythes;

import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseScythe extends WeaponBase {

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
}
