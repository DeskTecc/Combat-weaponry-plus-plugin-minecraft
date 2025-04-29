package me.helleo.cwp.items.charms;

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

public class GoldCharm extends BaseCharm{

    static ItemStack item = new ItemStack(Material.GOLD_INGOT);
    static ItemMeta meta = item.getItemMeta();

    public ShapedRecipe getCharmRecipe() {
        double attack_speed = 0.3;
        double move_speed = -0.15;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_speed = ConfigurationsDouble.Charms_GoldCharm_BonusAttackSpeedPercent.getValue() / 100;
            move_speed = ConfigurationsDouble.Charms_GoldCharm_BonusMoveSpeedPercent.getValue() / 100;
        }

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.GENERIC_ATTACK_SPEED,new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.getPlugin(),"generic.attack_speed"), attack_speed,
                        AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.OFFHAND));
        modifiers.put(Attribute.GENERIC_MOVEMENT_SPEED,new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.getPlugin(),"generic.move_speed"), move_speed,
                        AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.OFFHAND));
        meta.setAttributeModifiers(modifiers);


        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionGoldCharm_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionGoldCharm_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionGoldCharm_Line3.getValue()));
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7When in Off Hand:"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&c +"+String.format("%.1f",attack_speed*100).replace(',','.')+"% Attack Speed"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 "+String.format("%.1f",move_speed*100).replace(',','.')+"% Move Speed"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.setLore(lore);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionGoldCharm_Name.getValue()));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);


        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "gold_charm");
        CombatWeaponryPlus.getRecipes().setKey(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(
                "dLd",
                "LBL",
                "dLd");

        recipe.setIngredient('B', Material.GOLD_INGOT);
        recipe.setIngredient('L', Material.LAPIS_BLOCK);
        recipe.setIngredient('d', Material.DIAMOND);

        return recipe;
    }
    public static void setCharmRecipe(){
        Bukkit.addRecipe(new GoldCharm().getCharmRecipe());
    }
}
