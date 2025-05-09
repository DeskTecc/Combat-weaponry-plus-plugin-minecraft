package me.helleo.cwp.items.weapons.sabers;

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

public class EmeraldSaber extends BaseSaber{

    static ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String saberPath = "EmeraldSaber";
    static final double attack_damage = 5;
    static final double attack_speed = -2.4;

    public static ItemStack getSaber() {

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
        meta.setItemModel(new NamespacedKey("cwp","emerald/emerald_saber"));

        //enchants
        if (ConfigurationsBool.EnchantmentsOnEmeraldGear.getValue()) {
            int unbreakingValue = (int) ConfigurationsDouble.EmeraldGearEnchantLevels_Unbreaking.getValue();
            int mendingValue = (int) ConfigurationsDouble.EmeraldGearEnchantLevels_Mending.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, unbreakingValue, true);
            meta.addEnchant(Enchantment.MENDING, mendingValue, true);
        }
        meta.setCustomModelData(1000030);
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
        Bukkit.addRecipe(getWeaponRecipe(
                weapon_type,
                "emerald_saber",
                getSaber(),
                Material.EMERALD));
    }
}
