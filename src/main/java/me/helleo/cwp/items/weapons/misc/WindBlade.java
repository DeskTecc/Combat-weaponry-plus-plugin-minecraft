package me.helleo.cwp.items.weapons.misc;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.helleo.cwp.CombatWeaponryPlus;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class WindBlade {

    static ItemStack item = new ItemStack(Material.IRON_SWORD);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getItem(){

        double attack_damage = 5;
        double attack_speed = -2.3;

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Vacuum"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7- Hold right click to pull in entities"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  within a 4 block radius of you"));
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7When in Main Hand:"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 6 Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 1.7 Attack Speed"));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier
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

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "Wind Blade"));
        meta.setCustomModelData(21);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getItemRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "wind_sword");
        CombatWeaponryPlus.getRecipes().setKey(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape(
                "  M",
                " M ",
                "S  ");

        recipe.setIngredient('M', Material.BEE_SPAWN_EGG);
        recipe.setIngredient('S', Material.BEDROCK);

        return recipe;
    }

    public static void setItemRecipe(){
        Bukkit.addRecipe(new WindBlade().getItemRecipe());
    }
}
