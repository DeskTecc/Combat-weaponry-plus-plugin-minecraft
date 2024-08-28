package me.helleo.cwp.items.weapons.bows;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsString;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CompoundBow extends BaseBow{

    static ItemStack item = new ItemStack(Material.BOW);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getBow() {
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCompoundBow_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCompoundBow_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCompoundBow_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCompoundBow_Line4.getValue()));

        meta.setLore(lore);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCompoundBow_Name.getValue()));
        meta.setCustomModelData(3330003);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Speed", -0.03,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier3);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getBowRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "compoundbow");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getBow());

        recipe.shape(
                "LCL",
                "CBC",
                "LCL");

        recipe.setIngredient('B', Material.BOW);
        recipe.setIngredient('C', Material.NETHERITE_SCRAP);
        recipe.setIngredient('L', Material.IRON_BLOCK);

        return recipe;
    }

    public static void setBowRecipe(){
        Bukkit.addRecipe(new CompoundBow().getBowRecipe());
    }
}
