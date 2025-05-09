package me.helleo.cwp.items.weapons.spears;

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

public class EmeraldSpear extends BaseSpear{

    static ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String spearPath = "EmeraldSpear";
    static double attack_damage = 2;
    static double attack_speed = -1.2;

    public static ItemStack getSpear() {

        meta.setLore(getLore(getAttackDamage(attack_damage, spearPath), getAttackSpeed(attack_speed, spearPath)));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ATTACK_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                getAttackSpeed(attack_speed, spearPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));
        modifiers.put(Attribute.ATTACK_DAMAGE,new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                getAttackDamage(attack_damage, spearPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));

        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                ConfigLoader.getLang().getString(spearPath)));
        meta.setItemModel(new NamespacedKey("cwp","emerald/emerald_spear"));
        if (ConfigurationsBool.EnchantmentsOnEmeraldGear.getValue()) {
            int unbreakingValue = (int) ConfigurationsDouble.EmeraldGearEnchantLevels_Unbreaking.getValue();
            int mendingValue = (int) ConfigurationsDouble.EmeraldGearEnchantLevels_Mending.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, unbreakingValue, true);
            meta.addEnchant(Enchantment.MENDING, mendingValue, true);
        }
        meta.setCustomModelData(1000014);
        item.setItemMeta(meta);
        return item;
    }

    public static double getAttackDamage(){
        return getAttackDamage(attack_damage, spearPath);
    }

    public static double getAttackSpeed(){
        return getAttackSpeed(attack_speed, spearPath);
    }

    public static void setSpearRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(weapon_type,"emerald_spear",getSpear(),Material.EMERALD));
    }
}
