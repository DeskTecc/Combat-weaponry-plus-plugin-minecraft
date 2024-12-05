package me.helleo.cwp.items.weapons.spears;

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

public abstract class BaseSpear extends WeaponBase {
    public enum description {
        Line1(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionSpear.Line1")),
        Line2(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionSpear.Line2")),
        Line3(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionSpear.Line3")),
        Line4(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionSpear.Line4")),
        Line5(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionSpear.Line5")),
        Line6(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionSpear.Line6")),
        Line7(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionSpear.Line7")),
        Line8(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionSpear.Line8")),
        Line9(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionSpear.Line9")),
        PrismarineSpear_Name(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionPrismarineSpear.Name")),
        PrismarineSpear_Line10(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionPrismarineSpear.Line10")),
        PrismarineSpear_Line11(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionPrismarineSpear.Line11")),
        PrismarineSpear_Line12(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionPrismarineSpear.Line12"));

        private final String description_value;

        description(String value){
            this.description_value = value;
        }
        public String getValue(){
            return this.description_value;
        }
    }
    final static String weapon = "Spear";

    protected static Double getCustomDamage(String spearType){
        String path = "Spears_"+spearType+weapon+".Damage";
        return Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getDouble(path);
    }

    public static Double getCustomDamageAdded(){
        String path = "Spears_PrismarineSpear.DamageAdded";
        return Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getDouble(path);
    }

    protected static Double getCustomSpeed(String spearType){
        String path = "Spears_"+spearType+weapon+".Speed";
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
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line7.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line8.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', description.Line9.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7When in Main Hand:"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 "+attack_damage+" Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 "+attack_speed+" Attack Speed"));
        return lore;
    }
}
