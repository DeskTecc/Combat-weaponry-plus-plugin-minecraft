package me.helleo.cwp.items.weapons.scythes;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;

import java.util.ArrayList;
import java.util.List;

import static me.helleo.cwp.configurations.ConfigLoader.getLang;

public abstract class BaseScythe extends WeaponBase {

    public enum description {
        Line1(getLang().getString("DescriptionScythe.Line1")),
        Line2(getLang().getString("DescriptionScythe.Line2")),
        Line3(getLang().getString("DescriptionScythe.Line3")),
        Line4(getLang().getString("DescriptionScythe.Line4")),
        Line5(getLang().getString("DescriptionScythe.Line5")),
        Line6(getLang().getString("DescriptionScythe.Line6")),
        Line7(getLang().getString("DescriptionScythe.Line7")),
        PrismarineScythe_Line8(getLang().getString("DescriptionPrismarineScythe.Line8")),
        PrismarineScythe_Line9(getLang().getString("DescriptionPrismarineScythe.Line9")),
        PrismarineScythe_Line10(getLang().getString("DescriptionPrismarineScythe.Line10")),
        WoodenScythe(getLang().getString("Wooden_Scythe")),
        GoldenScythe(getLang().getString("Golden_Scythe")),
        StoneScythe(getLang().getString("Stone_Scythe")),
        IronScythe(getLang().getString("Iron_Scythe")),
        EmeraldScythe(getLang().getString("Emerald_Scythe")),
        DiamondScythe(getLang().getString("Diamond_Scythe")),
        NetheriteScythe(getLang().getString("Netherite_Scythe")),
        PrismarineScythe(getLang().getString("Prismarine_Scythe"));

        private final String description_value;

        description(String value){
            this.description_value = value;
        }
        public String getValue(){
            return this.description_value;
        }
    }

    protected static AttributeModifier setModifier(String key, double value){
        return new AttributeModifier(key, value, AttributeModifier.Operation.ADD_NUMBER);
    }

    public static List<String> getLore(double attack_damage, double attack_speed){
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line7.getValue()));
        return setLore(lore, attack_damage,attack_speed);
    }
}
