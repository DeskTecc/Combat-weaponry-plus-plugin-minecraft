package me.helleo.cwp.items.weapons.katanas;

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

public abstract class BaseKatana extends WeaponBase {
    public enum description {
        Line1(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionKatana.Line1")),
        Line2(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionKatana.Line2")),
        Line3(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionKatana.Line3")),
        Line4(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionKatana.Line4")),
        Line5(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionKatana.Line5")),
        Line6(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionKatana.Line6")),
        Line7(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionKatana.Line7")),
        Line8(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionKatana.Line8")),
        Line9(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionKatana.Line9")),
        Line10(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionKatana.Line10")),
        Line11(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionKatana.Line11")),
        PrismarineKatana_Name(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionPrismarineKatana.Name")),
        PrismarineKatana_Line12(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionPrismarineKatana.Line12")),
        PrismarineKatana_Line13(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionPrismarineKatana.Line13")),
        PrismarineKatana_Line14(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getString("DescriptionPrismarineKatana.Line14"));

        private final String description_value;

        description(String value){
            this.description_value = value;
        }
        public String getValue(){
            return this.description_value;
        }
    }

    final static String weapon = "Katana";

    protected static Double getCustomDamage(String katanaType){
        String path = "Katanas_"+katanaType+weapon+".Damage";
        return Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getDouble(path);
    }

    public static Double getCustomDamageAdded(){
        String path = "Katanas_Prismarine"+weapon+".DamageAdded";
        return Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getDouble(path);
    }

    protected static Double getCustomSpeed(String katanaType){
        String path = "Katanas_"+katanaType+weapon+".Speed";
        return Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getDouble(path);
    }

    protected static Double getCustomMoveSpeed(String katanaType){
        String path = "Katanas_"+katanaType+weapon+".MoveSpeed";
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
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7When in Main Hand:"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 "+attack_damage+" Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 "+attack_speed+" Attack Speed"));
        return lore;
    }
}
