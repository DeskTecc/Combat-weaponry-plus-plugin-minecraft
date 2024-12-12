package me.helleo.cwp.items.weapons.rapiers;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;

import java.util.ArrayList;
import java.util.List;

import static me.helleo.cwp.ConfigLoader.getLang;

public abstract class BaseRapier extends WeaponBase {
    public enum description {
        Line1(getLang().getString("DescriptionRapier.Line1")),
        Line2(getLang().getString("DescriptionRapier.Line2")),
        Line3(getLang().getString("DescriptionRapier.Line3")),
        Line4(getLang().getString("DescriptionRapier.Line4")),
        Line5(getLang().getString("DescriptionRapier.Line5")),
        Line6(getLang().getString("DescriptionRapier.Line6")),
        Line7(getLang().getString("DescriptionRapier.Line7")),
        PrismarineRapier_Line8(getLang().getString("DescriptionPrismarineRapier.Line8")),
        PrismarineRapier_Line9(getLang().getString("DescriptionPrismarineRapier.Line9")),
        PrismarineRapier_Line10(getLang().getString("DescriptionPrismarineRapier.Line10")),
        WoodenRapier(getLang().getString("Wooden_Rapier")),
        GoldenRapier(getLang().getString("Golden_Rapier")),
        StoneRapier(getLang().getString("Stone_Rapier")),
        IronRapier(getLang().getString("Iron_Rapier")),
        EmeraldRapier(getLang().getString("Emerald_Rapier")),
        DiamondRapier(getLang().getString("Diamond_Rapier")),
        NetheriteRapier(getLang().getString("Netherite_Rapier")),
        PrismarineRapier(getLang().getString("Prismarine_Rapier"));

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
        return setLore(lore, attack_damage,attack_speed);
    }
}
