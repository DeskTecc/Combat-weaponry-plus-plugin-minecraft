package me.helleo.cwp.items.weapons.spears;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;

import java.util.ArrayList;
import java.util.List;

import static me.helleo.cwp.ConfigLoader.getLang;

public abstract class BaseSpear extends WeaponBase {
    public enum description {
        Line1(getLang().getString("DescriptionSpear.Line1")),
        Line2(getLang().getString("DescriptionSpear.Line2")),
        Line3(getLang().getString("DescriptionSpear.Line3")),
        Line4(getLang().getString("DescriptionSpear.Line4")),
        Line5(getLang().getString("DescriptionSpear.Line5")),
        Line6(getLang().getString("DescriptionSpear.Line6")),
        Line7(getLang().getString("DescriptionSpear.Line7")),
        Line8(getLang().getString("DescriptionSpear.Line8")),
        Line9(getLang().getString("DescriptionSpear.Line9")),
        PrismarineSpear_Line10(getLang().getString("DescriptionPrismarineSpear.Line10")),
        PrismarineSpear_Line11(getLang().getString("DescriptionPrismarineSpear.Line11")),
        PrismarineSpear_Line12(getLang().getString("DescriptionPrismarineSpear.Line12")),
        WoodenSpear(getLang().getString("Wooden_Spear")),
        GoldenSpear(getLang().getString("Golden_Spear")),
        StoneSpear(getLang().getString("Stone_Spear")),
        IronSpear(getLang().getString("Iron_Spear")),
        EmeraldSpear(getLang().getString("Emerald_Spear")),
        DiamondSpear(getLang().getString("Diamond_Spear")),
        NetheriteSpear(getLang().getString("Netherite_Spear")),
        PrismarineSpear(getLang().getString("Prismarine_Spear"));

        private final String description_value;

        description(String value){
            this.description_value = value;
        }
        public String getValue(){
            return this.description_value;
        }
    }

    protected static AttributeModifier setModifier(String key, double value){
        return new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,key),
                value, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
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
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line8.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line9.getValue()));
        return setLore(lore, attack_damage,attack_speed);
    }
}
