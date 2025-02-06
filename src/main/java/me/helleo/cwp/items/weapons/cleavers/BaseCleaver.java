package me.helleo.cwp.items.weapons.cleavers;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;

import java.util.ArrayList;
import java.util.List;

import static me.helleo.cwp.configurations.ConfigLoader.getLang;

public abstract class BaseCleaver extends WeaponBase {
    public enum description {
        Line1(getLang().getString("DescriptionCleaver.Line1")),
        Line2(getLang().getString("DescriptionCleaver.Line2")),
        Line3(getLang().getString("DescriptionCleaver.Line3")),
        Line4(getLang().getString("DescriptionCleaver.Line4")),
        Line5(getLang().getString("DescriptionCleaver.Line5")),
        Line6(getLang().getString("DescriptionCleaver.Line6")),
        Line7(getLang().getString("DescriptionCleaver.Line7")),
        Line8(getLang().getString("DescriptionCleaver.Line8")),
        Line9(getLang().getString("DescriptionCleaver.Line9")),
        PrismarineCleaver_Line10(getLang().getString("DescriptionPrismarineCleaver.Line10")),
        PrismarineCleaver_Line11(getLang().getString("DescriptionPrismarineCleaver.Line11")),
        PrismarineCleaver_Line12(getLang().getString("DescriptionPrismarineCleaver.Line12")),
        WoodenCleaver(getLang().getString("Wooden_Cleaver")),
        GoldenCleaver(getLang().getString("Golden_Cleaver")),
        StoneCleaver(getLang().getString("Stone_Cleaver")),
        IronCleaver(getLang().getString("Iron_Cleaver")),
        EmeraldCleaver(getLang().getString("Emerald_Cleaver")),
        DiamondCleaver(getLang().getString("Diamond_Cleaver")),
        NetheriteCleaver(getLang().getString("Netherite_Cleaver")),
        PrismarineCleaver(getLang().getString("Prismarine_Cleaver"));

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
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line7.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line8.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line9.getValue()));
        return setLore(lore, attack_damage,attack_speed);
    }
}
