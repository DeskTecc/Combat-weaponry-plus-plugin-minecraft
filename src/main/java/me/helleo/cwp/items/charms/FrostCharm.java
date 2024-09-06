package me.helleo.cwp.items.charms;

import me.helleo.cwp.CombatWeaponryPlus;
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

public class FrostCharm extends BaseCharm{

    static ItemStack item = new ItemStack(Material.WOODEN_SWORD);
    static ItemMeta meta = item.getItemMeta();

    public ShapedRecipe getCharmRecipe() {
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionFrostCharm_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionFrostCharm_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionFrostCharm_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionFrostCharm_Line4.getValue()));
        meta.setLore(lore);
        meta.setCustomModelData(45);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionFrostCharm_Name.getValue()));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        AttributeModifier modifier = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.attack_speed"), 0,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.attack_damage"), 0,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);

        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "frost_charm");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(
                "dLd",
                "LBL",
                "dLd");

        recipe.setIngredient('B', Material.BLUE_ICE);
        recipe.setIngredient('L', Material.LAPIS_BLOCK);
        recipe.setIngredient('d', Material.DIAMOND);

        return recipe;
    }

    public static void setCharmRecipe(){
        Bukkit.addRecipe(new FrostCharm().getCharmRecipe());
    }
}
