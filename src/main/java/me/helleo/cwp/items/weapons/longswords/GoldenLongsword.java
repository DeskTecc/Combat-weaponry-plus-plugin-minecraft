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

public class GoldenLongsword extends BaseLongsword{

    static ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String longswordPath = "GoldenLongsword";

    public static ItemStack getLongsword() {
        double attack_damage = 4;
        double attack_speed = -2.6;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = getCustomDamage(longswordPath);
            attack_speed = getCustomSpeed(longswordPath);
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.GENERIC_ATTACK_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                attack_speed,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.HAND
        ));
        modifiers.put(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                attack_damage,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.HAND
        ));

        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                ConfigLoader.getLang().getString(description.GoldenLongsword.getValue())));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        return item;
    }

    public static void setLongswordRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                "longsword",
                "golden_longsword",
                getLongsword(),
                Material.GOLD_INGOT));
    }
}
