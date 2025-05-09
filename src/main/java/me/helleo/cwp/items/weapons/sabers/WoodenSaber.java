package me.helleo.cwp.items.weapons.sabers;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.configurations.ConfigurationsBool;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class WoodenSaber extends BaseSaber{

    static ItemStack item = new ItemStack(Material.WOODEN_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String saberPath = "WoodenSaber";
    static double attack_damage = 3;
    static double attack_speed = -2.4;


    public static ItemStack getSaber(){

        meta.setLore(getLore(getAttackDamage(attack_damage, saberPath),getAttackSpeed(attack_speed, saberPath)));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();

        modifiers.put(Attribute.ATTACK_DAMAGE,new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                getAttackDamage(attack_damage, saberPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.HAND
        ));
        modifiers.put(Attribute.ATTACK_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                getAttackSpeed(attack_speed, saberPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.HAND
        ));

        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                ConfigLoader.getLang().getString(saberPath)));
        meta.setItemModel(new NamespacedKey("cwp","wood/wooden_saber"));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);
        return item;
    }

    public static double getAttackDamage(){
        return getAttackDamage(attack_damage, saberPath);
    }

    public static double getAttackSpeed(){
        return getAttackSpeed(attack_speed, saberPath);
    }

    public static void setSaberRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(weapon_type,"wooden_saber",getSaber(),Material.STICK));
    }
}
