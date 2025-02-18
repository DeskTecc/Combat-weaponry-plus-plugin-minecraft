package me.helleo.cwp.items.weapons.rapiers;

import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRapier extends WeaponBase {
    public enum description {
        Line1("DescriptionRapier.Line1"),
        Line2("DescriptionRapier.Line2"),
        Line3("DescriptionRapier.Line3"),
        Line4("DescriptionRapier.Line4"),
        Line5("DescriptionRapier.Line5"),
        Line6("DescriptionRapier.Line6"),
        Line7("DescriptionRapier.Line7"),
        PrismarineRapier_Line8("DescriptionPrismarineRapier.Line8"),
        PrismarineRapier_Line9("DescriptionPrismarineRapier.Line9"),
        PrismarineRapier_Line10("DescriptionPrismarineRapier.Line10"),
        WoodenRapier("Wooden_Rapier"),
        GoldenRapier("Golden_Rapier"),
        StoneRapier("Stone_Rapier"),
        IronRapier("Iron_Rapier"),
        EmeraldRapier("Emerald_Rapier"),
        DiamondRapier("Diamond_Rapier"),
        NetheriteRapier("Netherite_Rapier"),
        PrismarineRapier("Prismarine_Rapier");

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
