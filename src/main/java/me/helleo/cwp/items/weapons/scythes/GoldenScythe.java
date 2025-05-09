package me.helleo.cwp.items.weapons.scythes;

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

public class GoldenScythe extends BaseScythe{

    static ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String scythePath = "GoldenScythe";
    static double attack_damage = 6;
    static double attack_speed = -2.8;

    public static ItemStack getScythe() {

        meta.setLore(getLore(getAttackDamage(attack_damage, scythePath),getAttackSpeed(attack_speed, scythePath)));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ATTACK_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                getAttackSpeed(attack_speed, scythePath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));
        modifiers.put(Attribute.ATTACK_DAMAGE,new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                getAttackDamage(attack_damage, scythePath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));

        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                ConfigLoader.getLang().getString(scythePath)));
        meta.setItemModel(new NamespacedKey("cwp","gold/golden_scythe"));
        meta.setCustomModelData(1000003);
        item.setItemMeta(meta);
        return item;
    }

    public static double getAttackDamage(){
        return getAttackDamage(attack_damage, scythePath);
    }

    public static double getAttackSpeed(){
        return getAttackSpeed(attack_speed, scythePath);
    }

    public static void setScytheRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                weapon_type,
                "golden_scythe",
                getScythe(),
                Material.GOLD_INGOT));
    }
}
