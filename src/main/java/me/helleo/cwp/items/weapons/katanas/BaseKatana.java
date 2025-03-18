package me.helleo.cwp.items.weapons.katanas;

import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseKatana extends WeaponBase {
    public enum description {
        Line1("DescriptionKatana.Line1"),
        Line2("DescriptionKatana.Line2"),
        Line3("DescriptionKatana.Line3"),
        Line4("DescriptionKatana.Line4"),
        Line5("DescriptionKatana.Line5"),
        Line6("DescriptionKatana.Line6"),
        Line7("DescriptionKatana.Line7"),
        Line8("DescriptionKatana.Line8"),
        Line9("DescriptionKatana.Line9"),
        Line10("DescriptionKatana.Line10"),
        Line11("DescriptionKatana.Line11"),
        WoodenKatana("Wooden_Katana"),
        GoldenKatana("Golden_Katana"),
        StoneKatana("Stone_Katana"),
        IronKatana("Iron_Katana"),
        EmeraldKatana("Emerald_Katana"),
        DiamondKatana("Diamond_Katana"),
        NetheriteKatana("Netherite_Katana"),
        PrismarineKatana("Prismarine_Katana");

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
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line6.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line7.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line8.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line9.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line10.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line11.getValue())));
        return setLore(lore, attack_damage,attack_speed);
    }
}
