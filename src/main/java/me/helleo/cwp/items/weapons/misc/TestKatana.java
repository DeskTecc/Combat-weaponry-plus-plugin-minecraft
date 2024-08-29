package me.helleo.cwp.items.weapons.misc;

import me.helleo.cwp.CombatWeaponryPlus;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestKatana {

    static ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getItem(){

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Charged Strike"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7- Hit 5 times to charge,"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  charge attacks require 2 hands"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6  - Slash"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  In main hand, right click to"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  launch target directly upwards"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9   4 Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6  - Thrust"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  In off hand, right click to"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  launch target further"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9   8 Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7When in Main Hand:"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 6 Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 1.7 Attack Speed"));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -2.3,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 5,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "Diamond Katana v2"));
        meta.setCustomModelData(2000002);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getItemRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "diamond_katana_test");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape(
                "  M",
                " M ",
                "S  ");

        recipe.setIngredient('M', Material.DIAMOND);
        recipe.setIngredient('S', Material.BEDROCK);

        return recipe;
    }

    public static void setItemRecipe(){
        Bukkit.addRecipe(new TestKatana().getItemRecipe());
    }

}
