package me.helleo.cwp.items.weapons.knives;

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

public class StoneKnife extends BaseKnife{

    static ItemStack item = new ItemStack(Material.STONE_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String knifePath = "StoneKnife";
    static double attack_damage = 1.5;
    static double attack_speed = -1;


    public static ItemStack getKnife() {


        meta.setLore(getLore(getAttackDamage(attack_damage, knifePath),getAttackSpeed(attack_speed, knifePath)));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ATTACK_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                getAttackSpeed(attack_speed, knifePath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));
        modifiers.put(Attribute.ATTACK_DAMAGE,new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                getAttackDamage(attack_damage, knifePath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));

        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                ConfigLoader.getLang().getString(knifePath)));
        meta.setItemModel(new NamespacedKey("cwp","stone/stone_knife"));
        meta.setCustomModelData(1000006);
        item.setItemMeta(meta);
        return item;
    }

    public static double getAttackDamage(){
        return getAttackDamage(attack_damage, knifePath);
    }

    public static double getAttackSpeed(){
        return getAttackSpeed(attack_speed, knifePath);
    }

    public static void setKnifeRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                weapon_type,
                "stone_knife",
                getKnife(),
                Material.COBBLESTONE));
    }
}
