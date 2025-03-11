package me.helleo.cwp.items.weapons.sabers;

import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseSaber extends WeaponBase {
    public enum description {
        Line1("DescriptionSaber.Line1"),
        Line2("DescriptionSaber.Line2"),
        Line3("DescriptionSaber.Line3"),
        Line4("DescriptionSaber.Line4"),
        WoodenSaber("Wooden_Saber"),
        GoldenSaber("Golden_Saber"),
        StoneSaber("Stone_Saber"),
        IronSaber("Iron_Saber"),
        EmeraldSaber("Emerald_Saber"),
        DiamondSaber("Diamond_Saber"),
        NetheriteSaber("Netherite_Saber"),
        PrismarineSaber("Prismarine_Saber");

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
}
