package me.helleo.cwp.items.weapons.misc;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ChorusBlade {

    static ItemStack item = new ItemStack(Material.IRON_SWORD);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getTool(){

        //modifier
        double attack_damage = 3;
        double attack_speed = 6;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = ConfigurationsDouble.Swords_ChorusBlade_Damage.getValue();
            attack_speed = ConfigurationsDouble.Swords_ChorusBlade_Speed.getValue();
        }
        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ATTACK_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                attack_speed,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.HAND
        ));
        modifiers.put(Attribute.ATTACK_DAMAGE,new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                attack_damage,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.HAND
        ));

        meta.setAttributeModifiers(modifiers);

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionChorusBlade_Name.getValue()));

        if (ConfigurationsBool.EnchantsChorusBlade.getValue()) {
            int unbreakingLevel = (int) ConfigurationsDouble.ChorusEnchantLevels_Unbreaking.getValue();
            int knockbackLevel = (int) ConfigurationsDouble.ChorusEnchantLevels_Knockback.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, unbreakingLevel, true);
            meta.addEnchant(Enchantment.KNOCKBACK, knockbackLevel, true);
        }
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionChorusBlade_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionChorusBlade_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionChorusBlade_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionChorusBlade_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionChorusBlade_Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionChorusBlade_Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionChorusBlade_Line7.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&',"&9 "+attack_damage+" Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 "+attack_speed+" Attack Speed"));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        meta.setItemModel(new NamespacedKey("cwp","misc/chorus_blade"));
        meta.setCustomModelData(1000007);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getToolRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "chorus_blade");
        CombatWeaponryPlus.getRecipes().setKey(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getTool());

        recipe.shape(
                " E ",
                "PCP",
                "qBq");

        recipe.setIngredient('E', Material.END_ROD);
        recipe.setIngredient('P', Material.ENDER_EYE);
        recipe.setIngredient('C', Material.CHORUS_FLOWER);
        recipe.setIngredient('B', Material.BLAZE_ROD);
        recipe.setIngredient('q', Material.END_CRYSTAL);

        return recipe;
    }

    public static void setToolRecipe(){
        Bukkit.addRecipe(new ChorusBlade().getToolRecipe());
    }
}
