package me.helleo.cwp.items.weapons.cleavers;

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

public class IronCleaver extends BaseCleaver{

    static ItemStack item = new ItemStack(Material.IRON_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String cleaverPath = "IronCleaver";
    static double attack_damage = 10;
    static double attack_speed = -3.6;

    public static ItemStack getCleaver() {


        meta.setLore(getLore(getAttackDamage(attack_damage, cleaverPath),getAttackSpeed(attack_speed, cleaverPath)));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ATTACK_DAMAGE,new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                getAttackDamage(attack_damage, cleaverPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));
        modifiers.put(Attribute.ATTACK_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                getAttackSpeed(attack_speed, cleaverPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));

        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                ConfigLoader.getLang().getString(cleaverPath)));
        meta.setItemModel(new NamespacedKey("cwp","iron/iron_cleaver"));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);
        return item;
    }

    public static double getAttackDamage(){
        return getAttackDamage(attack_damage, cleaverPath);
    }

    public static double getAttackSpeed(){
        return getAttackSpeed(attack_speed, cleaverPath);
    }

    public static void setCleaverRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                weapon_type,
                "iron_cleaver",
                getCleaver(),
                Material.IRON_INGOT));
    }
}
