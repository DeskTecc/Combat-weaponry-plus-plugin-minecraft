package me.helleo.cwp.items.weapons.rapiers;

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

public class GoldenRapier extends BaseRapier{

    static ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String rapierPath = "GoldenRapier";
    static double attack_damage = 2;
    static double attack_speed = -1.6;

    public static ItemStack getRapier() {

        meta.setLore(getLore(getAttackDamage(attack_damage, rapierPath),getAttackSpeed(attack_speed, rapierPath)));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ATTACK_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                getAttackSpeed(attack_speed, rapierPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));
        modifiers.put(Attribute.ATTACK_DAMAGE,new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                getAttackDamage(attack_damage, rapierPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));

        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                ConfigLoader.getLang().getString(rapierPath)));
        meta.setItemModel(new NamespacedKey("cwp","gold/golden_rapier"));
        meta.setCustomModelData(1000005);
        item.setItemMeta(meta);
        return item;
    }

    public static double getAttackDamage(){
        return getAttackDamage(attack_damage, rapierPath);
    }

    public static double getAttackSpeed(){
        return getAttackSpeed(attack_speed, rapierPath);
    }

    public static void setRapierRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                weapon_type,
                "golden_rapier",
                getRapier(),
                Material.GOLD_INGOT));
    }
}
