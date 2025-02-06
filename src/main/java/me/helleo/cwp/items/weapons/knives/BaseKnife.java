package me.helleo.cwp.items.weapons.knives;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;

import java.util.ArrayList;
import java.util.List;

import static me.helleo.cwp.configurations.ConfigLoader.getLang;

public abstract class BaseKnife extends WeaponBase {
    public enum description {
        Line1(getLang().getString("DescriptionKnife.Line1")),
        Line2(getLang().getString("DescriptionKnife.Line2")),
        Line3(getLang().getString("DescriptionKnife.Line3")),
        Line4(getLang().getString("DescriptionKnife.Line4")),
        Line5(getLang().getString("DescriptionKnife.Line5")),
        Line6(getLang().getString("DescriptionKnife.Line6")),
        PrismarineKnife_Line7(getLang().getString("DescriptionPrismarineKnife.Line7")),
        PrismarineKnife_Line8(getLang().getString("DescriptionPrismarineKnife.Line8")),
        PrismarineKnife_Line9(getLang().getString("DescriptionPrismarineKnife.Line9")),
        WoodenKnife(getLang().getString("Wooden_Knife")),
        GoldenKnife(getLang().getString("Golden_Knife")),
        StoneKnife(getLang().getString("Stone_Knife")),
        IronKnife(getLang().getString("Iron_Knife")),
        EmeraldKnife(getLang().getString("Emerald_Knife")),
        DiamondKnife(getLang().getString("Diamond_Knife")),
        NetheriteKnife(getLang().getString("Netherite_Knife")),
        PrismarineKnife(getLang().getString("Prismarine_Knife"));

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
        lore.add(ChatColor.translateAlternateColorCodes('&', BaseKnife.description.Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', BaseKnife.description.Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', BaseKnife.description.Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', BaseKnife.description.Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', BaseKnife.description.Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', BaseKnife.description.Line6.getValue()));
        return setLore(lore, attack_damage,attack_speed);
    }
}
