package me.helleo.cwp.items.weapons.longswords;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;

import java.util.ArrayList;
import java.util.List;

import static me.helleo.cwp.configurations.ConfigLoader.getLang;

public abstract class BaseLongsword extends WeaponBase {
    public enum description {
        Line1(getLang().getString("DescriptionLongsword.Line1")),
        Line2(getLang().getString("DescriptionLongsword.Line2")),
        Line3(getLang().getString("DescriptionLongsword.Line3")),
        Line4(getLang().getString("DescriptionLongsword.Line4")),
        Line5(getLang().getString("DescriptionLongsword.Line5")),
        PrismarineLongsword_Line6(getLang().getString("DescriptionPrismarineLongsword.Line6")),
        PrismarineLongsword_Line7(getLang().getString("DescriptionPrismarineLongsword.Line7")),
        PrismarineLongsword_Line8(getLang().getString("DescriptionPrismarineLongsword.Line8")),
        WoodenLongsword(getLang().getString("Wooden_Longsword")),
        GoldenLongsword(getLang().getString("Golden_Longsword")),
        StoneLongsword(getLang().getString("Stone_Longsword")),
        IronLongsword(getLang().getString("Iron_Longsword")),
        EmeraldLongsword(getLang().getString("Emerald_Longsword")),
        DiamondLongsword(getLang().getString("Diamond_Longsword")),
        NetheriteLongsword(getLang().getString("Netherite_Longsword")),
        PrismarineLongsword(getLang().getString("Prismarine_Longsword"));

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

    public static List<String> getLore(double attack_damage, double attack_speed) {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line5.getValue()));
        return setLore(lore, attack_damage,attack_speed);
    }
}
