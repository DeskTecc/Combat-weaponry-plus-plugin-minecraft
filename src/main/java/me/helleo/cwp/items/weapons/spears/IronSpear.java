package me.helleo.cwp.items.weapons.spears;

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

public class IronSpear extends BaseSpear{

    static ItemStack item = new ItemStack(Material.IRON_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String spearPath = "IronSpear";

    public static ItemStack getSpear() {
        double attack_damage = 2;
        double attack_speed = -1.5;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = getCustomDamage(spearPath);
            attack_speed = getCustomSpeed(spearPath);
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ATTACK_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                attack_speed,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.HAND
        ));
        modifiers.put(Attribute.ATTACK_DAMAGE,new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                attack_damage,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.HAND
        ));

        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                ConfigLoader.getLang().getString(spearPath)));
        meta.setItemModel(new NamespacedKey("cwp","iron/iron_spear"));
        meta.setCustomModelData(1000004);
        item.setItemMeta(meta);
        return item;
    }

    public static void setSpearRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                "spear",
                "iron_spear",
                getSpear(),
                Material.IRON_INGOT));
    }
}
