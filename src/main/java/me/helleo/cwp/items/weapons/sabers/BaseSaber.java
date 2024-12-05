package me.helleo.cwp.items.weapons.sabers;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import java.util.ArrayList;
import java.util.List;

import static me.helleo.cwp.CombatWeaponryPlus.pluginName;

public abstract class BaseSaber extends WeaponBase {
    public enum description {
        Line1(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionSaber.Line1")),
        Line2(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionSaber.Line2")),
        Line3(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionSaber.Line3")),
        Line4(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionSaber.Line4")),
        PrismarineSaber_Name(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionPrismarineSaber.Name")),
        PrismarineSaber_Line5(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionPrismarineSaber.Line5")),
        PrismarineSaber_Line6(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionPrismarineSaber.Line6")),
        PrismarineSaber_Line7(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionPrismarineSaber.Line7"));

        private final String description_value;

        description(String value){
            this.description_value = value;
        }
        public String getValue(){
            return this.description_value;
        }
    }
    final static String weapon = "Saber";

    protected static Double getCustomDamage(String saberType){
        String path = "Sabers_"+saberType+weapon+".Damage";
        return Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getDouble(path);
    }

    public static Double getCustomDamageAdded(){
        String path = "Sabers_Prismarine"+weapon+".DamageAdded";
        return Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getDouble(path);
    }

    protected static Double getCustomSpeed(String saberType){
        String path = "Sabers_"+saberType+weapon+".Speed";
        return Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getDouble(path);
    }

    protected static String getName(Material material){
        return setName(material, weapon);
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
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7When in Main Hand:"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 "+attack_damage+" Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 "+attack_speed+" Attack Speed"));
        return lore;
    }
}
