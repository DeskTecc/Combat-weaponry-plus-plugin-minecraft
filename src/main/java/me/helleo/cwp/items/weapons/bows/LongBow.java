package me.helleo.cwp.items.weapons.bows;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsString;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LongBow extends BaseBow{

    static ItemStack item = new ItemStack(Material.BOW);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getBow() {
        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongBow_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongBow_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongBow_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongBow_Line4.getValue()));

        meta.setLore(lore);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongBow_Name.getValue()));
        meta.setCustomModelData(3330001);
        AttributeModifier modifier3 = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.move_speed"), -0.01,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier3);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getBowRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "longbow");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getBow());

        recipe.shape(
                " SI",
                "IBI",
                " SI");

        recipe.setIngredient('B', Material.BOW);
        recipe.setIngredient('I', Material.IRON_NUGGET);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public static void setBowRecipe(){
        Bukkit.addRecipe(new LongBow().getBowRecipe());
    }
}
