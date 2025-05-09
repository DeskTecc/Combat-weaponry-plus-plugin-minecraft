package me.helleo.cwp.items.weapons.katanas;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class EmeraldKatana extends BaseKatana{

    static ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String katanaPath = "EmeraldKatana";
    static double attack_damage = 4;
    static double attack_speed = -2;
    static double move_speed = 0.02;

    public static ItemStack getKatana() {


        meta.setLore(getLore(getAttackDamage(attack_damage, katanaPath), getAttackSpeed(attack_speed, katanaPath)));
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
        meta.setItemModel(new NamespacedKey("cwp","emerald/emerald_katana"));
        if (ConfigurationsBool.EnchantmentsOnEmeraldGear.getValue()) {
            int unbreakingValue = (int) ConfigurationsDouble.EmeraldGearEnchantLevels_Unbreaking.getValue();
            int mendingValue = (int) ConfigurationsDouble.EmeraldGearEnchantLevels_Mending.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, unbreakingValue, true);
            meta.addEnchant(Enchantment.MENDING, mendingValue, true);
        }
        meta.setCustomModelData(1000012);
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
        Bukkit.addRecipe(getWeaponRecipe(weapon_type,"emerald_katana",getKatana(),Material.EMERALD));
    }
}
