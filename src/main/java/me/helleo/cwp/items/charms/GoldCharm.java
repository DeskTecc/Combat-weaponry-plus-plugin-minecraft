package me.helleo.cwp.items.charms;

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
import java.util.UUID;

public class GoldCharm extends BaseCharm{

    static ItemStack item = new ItemStack(Material.GOLD_INGOT);
    static ItemMeta meta = item.getItemMeta();

    public ShapedRecipe getCharmRecipe() {
        double atkspd = 0.3;
        double mvspd = -0.15;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            atkspd = ConfigurationsDouble.Charms_GoldCharm_BonusAttackSpeedPercent.getValue() / 100;
            mvspd = ConfigurationsDouble.Charms_GoldCharm_BonusMoveSpeedPercent.getValue() / 100;
        }
        AttributeModifier modifier = new AttributeModifier(NamespacedKey.minecraft("generic.attack_speed"), atkspd,
                AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.OFFHAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(NamespacedKey.minecraft("generic.move_speed"), mvspd,
                AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.OFFHAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier2);


        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionGoldCharm_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionGoldCharm_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionGoldCharm_Line3.getValue()));
        meta.setLore(lore);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionGoldCharm_Name.getValue()));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);


        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "gold_charm");
        CombatWeaponryPlus.keys.add(key);
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
