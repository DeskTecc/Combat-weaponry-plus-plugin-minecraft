package me.helleo.cwp.items.misc;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import me.helleo.cwp.configurations.ConfigurationsString;
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

public class RedstoneCore {

    static ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getItem(){
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRedstoneCore_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRedstoneCore_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRedstoneCore_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRedstoneCore_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRedstoneCore_Line5.getValue()));
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        //modifier
        double arm = 2;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            arm = ConfigurationsDouble.Armors_RedstoneCore_Armor.getValue();
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Armor", arm,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRedstoneCore_Name.getValue()));
        meta.setCustomModelData(1231234);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getItemRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "redstone_core");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getItem());

        recipe.shape(
                "ede",
                "dcd",
                "bab");

        recipe.setIngredient('a', Material.IRON_CHESTPLATE);
        recipe.setIngredient('b', Material.LEATHER);
        recipe.setIngredient('c', Material.REDSTONE_BLOCK);
        recipe.setIngredient('d', Material.COMPARATOR);
        recipe.setIngredient('e', Material.QUARTZ);

        return recipe;
    }

    public static void setItemRecipe(){
        Bukkit.addRecipe(new RedstoneCore().getItemRecipe());
    }

}
