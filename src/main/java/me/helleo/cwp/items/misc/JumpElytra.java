package me.helleo.cwp.items.misc;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JumpElytra {

    static ItemStack item = new ItemStack(Material.ELYTRA);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getItem(){
        AttributeModifier modifier = new AttributeModifier(NamespacedKey.minecraft("generic.armor"), 3,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier);

        meta.setDisplayName(ChatColor.YELLOW + "Jump Elytra");
        meta.setCustomModelData(1212121);

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Double Jump"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7- Press jump in midair to jump"));
        lore.add("");

        meta.setLore(lore);

        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getItemRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "jump_elytra");
        CombatWeaponryPlus.keys.add(key);

        ShapedRecipe recipe = new ShapedRecipe(key, getItem());
        recipe.shape(
                "NNN",
                "   ",
                "   ");

        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        return recipe;
    }

    public static void setItemRecipe(){
        Bukkit.addRecipe(new JumpElytra().getItemRecipe());
    }
}
