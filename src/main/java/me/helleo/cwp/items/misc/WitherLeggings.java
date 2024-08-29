package me.helleo.cwp.items.misc;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.Items;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import me.helleo.cwp.configurations.ConfigurationsString;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Item;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WitherLeggings {

    static ItemStack item = new ItemStack(Material.IRON_LEGGINGS);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getArmorPiece(){

        double kbr = 0.2;
        double hp = 5;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            kbr = ConfigurationsDouble.Armors_WitherLeggings_KBResist.getValue() / 10;
            hp = ConfigurationsDouble.Armors_WitherLeggings_BonusHealth.getValue();
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "KnockbackResistance", kbr,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWitheringLeggings_Name.getValue()));
        meta.setCustomModelData(5553333);

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWitheringArmorSet_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWitheringArmorSet_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWitheringArmorSet_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWitheringArmorSet_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWitheringArmorSet_Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWitheringArmorSet_Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWitheringArmorSet_Line7.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWitheringArmorSet_Line8.getValue()));

        meta.setLore(lore);

        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getArmorRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "wither_bone_leggings");
        CombatWeaponryPlus.keys.add(key);

        ItemStack wbone = new ItemStack(Material.BONE);
        ItemMeta meta2 = wbone.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta2.setCustomModelData(2222222);
        wbone.setItemMeta(meta2);

        @SuppressWarnings("deprecation")
        RecipeChoice wibone = new RecipeChoice.ExactChoice(Items.witherBone());
        ShapedRecipe recipe = new ShapedRecipe(key, getArmorPiece());
        recipe.shape(
                "BNB",
                "B B",
                "B B");

        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('B', wibone);

        return recipe;
    }

    public static void setArmorRecipe(){
        Bukkit.addRecipe(new WitherLeggings().getArmorRecipe());
    }
}
