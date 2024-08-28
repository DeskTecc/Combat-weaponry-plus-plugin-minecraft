package me.helleo.cwp.items.weapons.rapiers;

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

public class WoodenRapier extends BaseRapier{

    static ItemStack item = new ItemStack(Material.WOODEN_SWORD);
    static ItemMeta meta = item.getItemMeta();


    public ItemStack getRapier() {
        List<String> lore = setLore();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWoodenRapier_Line8.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWoodenRapier_Line9.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWoodenRapier_Line10.getValue()));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier
        double dmg = 2;
        double spd = -2.1;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Rapiers_WoodenRapier_Damage.getValue();
            spd = ConfigurationsDouble.Rapiers_WoodenRapier_Speed.getValue();
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWoodenRapier_Name.getValue()));
        meta.setCustomModelData(1000005);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getRapierRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "wooden_rapier");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getRapier());

        recipe.shape(
                "  S",
                "SS ",
                "SS ");


        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public static void setRapierRecipe(){
        Bukkit.addRecipe(new WoodenRapier().getRapierRecipe());
    }
}
