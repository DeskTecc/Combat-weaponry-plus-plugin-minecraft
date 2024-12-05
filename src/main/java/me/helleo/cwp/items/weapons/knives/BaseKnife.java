package me.helleo.cwp.items.weapons.knives;

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

public abstract class BaseKnife extends WeaponBase {
    public enum description {
        Line1(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionKnife.Line1")),
        Line2(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionKnife.Line2")),
        Line3(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionKnife.Line3")),
        Line4(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionKnife.Line4")),
        Line5(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionKnife.Line5")),
        Line6(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionKnife.Line6")),
        PrismarineKnife_Name(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionPrismarineKnife.Name")),
        PrismarineKnife_Line7(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionPrismarineKnife.Line7")),
        PrismarineKnife_Line8(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionPrismarineKnife.Line8")),
        PrismarineKnife_Line9(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionPrismarineKnife.Line9"));

        private final String description_value;

        description(String value){
            this.description_value = value;
        }
        public String getValue(){
            return this.description_value;
        }
    }
    final static String weapon = "Knife";

    protected static Double getCustomDamage(String knifeType){
        String path = "Knives_"+knifeType+weapon+".Damage";
        return Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getDouble(path);
    }

    public static Double getCustomDamageAdded(){
        String path = "Knives_Prismarine"+weapon+".DamageAdded";
        return Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getDouble(path);
    }

    protected static Double getCustomSpeed(String knifeType){
        String path = "Knives_"+knifeType+weapon+".Speed";
        return Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getDouble(path);
    }

    protected static String getName(Material material){
        return setName(material, weapon);
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
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7When in Main Hand:"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 "+attack_damage+" Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 "+attack_speed+" Attack Speed"));
        return lore;
    }
}
