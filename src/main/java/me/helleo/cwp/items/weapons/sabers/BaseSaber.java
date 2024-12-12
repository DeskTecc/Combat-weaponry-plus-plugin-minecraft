package me.helleo.cwp.items.weapons.sabers;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import java.util.ArrayList;
import java.util.List;

import static me.helleo.cwp.ConfigLoader.getLang;

public abstract class BaseSaber extends WeaponBase {
    public enum description {
        Line1(getLang().getString("DescriptionSaber.Line1")),
        Line2(getLang().getString("DescriptionSaber.Line2")),
        Line3(getLang().getString("DescriptionSaber.Line3")),
        Line4(getLang().getString("DescriptionSaber.Line4")),
        PrismarineSaber_Line5(getLang().getString("DescriptionPrismarineSaber.Line5")),
        PrismarineSaber_Line6(getLang().getString("DescriptionPrismarineSaber.Line6")),
        PrismarineSaber_Line7(getLang().getString("DescriptionPrismarineSaber.Line7")),
        WoodenSaber(getLang().getString("Wooden_Saber")),
        GoldenSaber(getLang().getString("Golden_Saber")),
        StoneSaber(getLang().getString("Stone_Saber")),
        IronSaber(getLang().getString("Iron_Saber")),
        EmeraldSaber(getLang().getString("Emerald_Saber")),
        DiamondSaber(getLang().getString("Diamond_Saber")),
        NetheriteSaber(getLang().getString("Netherite_Saber")),
        PrismarineSaber(getLang().getString("Prismarine_Saber"));

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
        return setLore(lore, attack_damage,attack_speed);
    }
}
