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

public class GoldenKnife extends BaseKnife{

    static ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String knifePath = "GoldenKnife";

    public static ItemStack getKnife() {
        double attack_damage = 1;
        double attack_speed = 0;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = getCustomDamage(knifePath);
            attack_speed = getCustomSpeed(knifePath);
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ATTACK_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                attack_speed,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));
        modifiers.put(Attribute.ATTACK_DAMAGE,new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                attack_damage,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));

        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                ConfigLoader.getLang().getString(knifePath)));
        meta.setItemModel(new NamespacedKey("cwp","gold/golden_knife"));
        meta.setCustomModelData(1000006);
        item.setItemMeta(meta);
        return item;
    }

    public static void setKnifeRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                "knife",
                "golden_knife",
                getKnife(),
                Material.GOLD_INGOT));
    }
}
