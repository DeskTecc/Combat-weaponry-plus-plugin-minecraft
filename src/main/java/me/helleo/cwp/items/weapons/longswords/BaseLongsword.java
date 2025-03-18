package me.helleo.cwp.items.weapons.longswords;

import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseLongsword extends WeaponBase {
    public enum description {
        Line1("DescriptionLongsword.Line1"),
        Line2("DescriptionLongsword.Line2"),
        Line3("DescriptionLongsword.Line3"),
        Line4("DescriptionLongsword.Line4"),
        Line5("DescriptionLongsword.Line5"),
        WoodenLongsword("Wooden_Longsword"),
        GoldenLongsword("Golden_Longsword"),
        StoneLongsword("Stone_Longsword"),
        IronLongsword("Iron_Longsword"),
        EmeraldLongsword("Emerald_Longsword"),
        DiamondLongsword("Diamond_Longsword"),
        NetheriteLongsword("Netherite_Longsword"),
        PrismarineLongsword("Prismarine_Longsword");

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
}
