package me.helleo.cwp.items.weapons.katanas;

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

public class WoodenKatana extends BaseKatana{

    static ItemStack item = new ItemStack(Material.WOODEN_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String katanaPath = "WoodenKatana";
    static double attack_damage = 2.5;
    static double attack_speed = -2.3;
    static double move_speed = 0.02;


    public static ItemStack getKatana(){

        meta.setLore(getLore(getAttackDamage(attack_damage, katanaPath),getAttackSpeed(attack_speed, katanaPath)));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ATTACK_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                getAttackSpeed(attack_speed, katanaPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));
        modifiers.put(Attribute.ATTACK_DAMAGE,new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                getAttackDamage(attack_damage, katanaPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));
        modifiers.put(Attribute.MOVEMENT_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.move_speed"),
                getMoveSpeed(move_speed, katanaPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));

        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                ConfigLoader.getLang().getString(katanaPath)));
        meta.setItemModel(new NamespacedKey("cwp","wood/wooden_katana"));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);
        return item;
    }

    public static double getAttackDamage(){
        return getAttackDamage(attack_damage, katanaPath);
    }

    public static double getAttackSpeed(){
        return getAttackSpeed(attack_speed, katanaPath);
    }

    public static double getMoveSpeed(){
        return getAttackSpeed(move_speed, katanaPath);
    }

    public static void setKatanaRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(weapon_type, "wooden_katana",getKatana(),Material.STICK));
    }
}
