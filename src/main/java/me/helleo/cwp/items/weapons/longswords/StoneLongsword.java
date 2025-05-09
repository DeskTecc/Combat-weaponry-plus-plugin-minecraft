package me.helleo.cwp.items.weapons.longswords;

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

public class StoneLongsword extends BaseLongsword{

    static ItemStack item = new ItemStack(Material.STONE_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String longswordPath = "StoneLongsword";
    static double attack_damage = 5;
    static double attack_speed = -2.8;


    public static ItemStack getLongsword() {

        meta.setLore(getLore(getAttackDamage(attack_damage, longswordPath),getAttackSpeed(attack_speed, longswordPath)));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ATTACK_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                getAttackSpeed(attack_speed, longswordPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));
        modifiers.put(Attribute.ATTACK_DAMAGE,new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                getAttackDamage(attack_damage, longswordPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));

        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                ConfigLoader.getLang().getString(longswordPath)));
        meta.setItemModel(new NamespacedKey("cwp","stone/stone_longsword"));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        return item;
    }

    public static double getAttackDamage(){
        return getAttackDamage(attack_damage, longswordPath);
    }

    public static double getAttackSpeed(){
        return getAttackSpeed(attack_speed, longswordPath);
    }

    public static void setLongswordRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                weapon_type,
                "stone_longsword",
                getLongsword(),
                Material.COBBLESTONE));
    }

}
