package me.helleo.cwp.items.misc;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
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

public class RedstoneCore {

    static ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getItem(){
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("DescriptionRedstoneCore.Line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("DescriptionRedstoneCore.Line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("DescriptionRedstoneCore.Line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("DescriptionRedstoneCore.Line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("DescriptionRedstoneCore.Line5")));
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        //modifier
        double arm = 2;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            arm = ConfigurationsDouble.Armors_RedstoneCore_Armor.getValue();
        }

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.getPlugin(), "generic.armor"), arm,
                        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("DescriptionRedstoneCore.Name")));
        meta.setItemModel(new NamespacedKey("cwp","misc/redstone_core"));
        meta.setCustomModelData(1231234);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getItemRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "redstone_core");
        CombatWeaponryPlus.getRecipes().setKey(key);
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
