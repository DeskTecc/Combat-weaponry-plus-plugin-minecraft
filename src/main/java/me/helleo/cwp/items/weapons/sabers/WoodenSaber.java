package me.helleo.cwp.items.weapons.sabers;

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
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WoodenSaber extends BaseSaber{

    static ItemStack item = new ItemStack(Material.WOODEN_SWORD);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getSaber(){
        List<String> lore = setLore();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWoodenSaber_Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWoodenSaber_Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWoodenSaber_Line7.getValue()));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        double dmg = 3;
        double spd = -2.4;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Sabers_WoodenSaber_Damage.getValue();
            spd = ConfigurationsDouble.Sabers_WoodenSaber_Speed.getValue();
        }
        AttributeModifier modifier = new AttributeModifier(NamespacedKey.minecraft("generic.attack_speed"), spd,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_damage"), dmg,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWoodenSaber_Name.getValue()));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getSaberRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "wooden_saber");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getSaber());

        recipe.shape(
                " SS",
                " S ",
                "S  ");


        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public static void setSaberRecipe(){
        Bukkit.addRecipe(new WoodenSaber().getSaberRecipe());
    }

}
