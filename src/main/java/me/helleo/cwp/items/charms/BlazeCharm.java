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

public class BlazeCharm extends BaseCharm{

    static ItemStack item = new ItemStack(Material.BLAZE_ROD);
    static ItemMeta meta = item.getItemMeta();

    public ShapedRecipe getCharmRecipe() {
        double dmg = 4;
        double hp = -2;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Charms_BlazeCharm_BonusDamage.getValue();
            hp = ConfigurationsDouble.Charms_BlazeCharm_BonusHealth.getValue();
        }
        AttributeModifier modifier = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.attack_damage"), dmg,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        AttributeModifier modifier2 = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.max_health"), hp,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier2);

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionBlazeCharm_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionBlazeCharm_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionBlazeCharm_Line3.getValue()));
        meta.setLore(lore);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionBlazeCharm_Name.getValue()));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);


        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "blaze_charm");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(
                "dLd",
                "LBL",
                "dLd");

        recipe.setIngredient('B', Material.BLAZE_ROD);
        recipe.setIngredient('L', Material.LAPIS_BLOCK);
        recipe.setIngredient('d', Material.DIAMOND);

        return recipe;
    }
    public static void setCharmRecipe(){
        Bukkit.addRecipe(new BlazeCharm().getCharmRecipe());
    }
}
