package me.helleo.cwp.items.weapons.cleavers;

import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseCleaver extends WeaponBase {

    public enum description{
        Line1("DescriptionCleaver.Line1"),
        Line2("DescriptionCleaver.Line2"),
        Line3("DescriptionCleaver.Line3"),
        Line4("DescriptionCleaver.Line4"),
        Line5("DescriptionCleaver.Line5"),
        Line6("DescriptionCleaver.Line6"),
        Line7("DescriptionCleaver.Line7"),
        Line8("DescriptionCleaver.Line8"),
        Line9("DescriptionCleaver.Line9");

        private String description_value;

        description(String value) {
            description_value = value;
        }

        public String getValue(){
            return description_value;
        }
    }


    public static List<String> getLore(double attack_damage, double attack_speed) {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line1.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line2.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line3.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line4.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line5.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line6.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line7.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line8.getValue())));
        return lore;
    }
}
