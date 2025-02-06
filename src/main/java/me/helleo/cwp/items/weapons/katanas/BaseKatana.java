package me.helleo.cwp.items.weapons.katanas;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;

import java.util.ArrayList;
import java.util.List;

import static me.helleo.cwp.configurations.ConfigLoader.getLang;

public abstract class BaseKatana extends WeaponBase {
    public enum description {
        Line1(getLang().getString("DescriptionKatana.Line1")),
        Line2(getLang().getString("DescriptionKatana.Line2")),
        Line3(getLang().getString("DescriptionKatana.Line3")),
        Line4(getLang().getString("DescriptionKatana.Line4")),
        Line5(getLang().getString("DescriptionKatana.Line5")),
        Line6(getLang().getString("DescriptionKatana.Line6")),
        Line7(getLang().getString("DescriptionKatana.Line7")),
        Line8(getLang().getString("DescriptionKatana.Line8")),
        Line9(getLang().getString("DescriptionKatana.Line9")),
        Line10(getLang().getString("DescriptionKatana.Line10")),
        Line11(getLang().getString("DescriptionKatana.Line11")),
        PrismarineKatana_Line12(getLang().getString("DescriptionPrismarineKatana.Line12")),
        PrismarineKatana_Line13(getLang().getString("DescriptionPrismarineKatana.Line13")),
        PrismarineKatana_Line14(getLang().getString("DescriptionPrismarineKatana.Line14")),
        WoodenKatana(getLang().getString("Wooden_Katana")),
        GoldenKatana(getLang().getString("Golden_Katana")),
        StoneKatana(getLang().getString("Stone_Katana")),
        IronKatana(getLang().getString("Iron_Katana")),
        EmeraldKatana(getLang().getString("Emerald_Katana")),
        DiamondKatana(getLang().getString("Diamond_Katana")),
        NetheriteKatana(getLang().getString("Netherite_Katana")),
        PrismarineKatana(getLang().getString("Prismarine_Katana"));

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
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line10.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line11.getValue()));
        return setLore(lore, attack_damage,attack_speed);
    }
}
