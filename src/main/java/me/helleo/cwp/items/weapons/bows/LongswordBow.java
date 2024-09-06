package me.helleo.cwp.items.weapons.bows;

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

public class LongswordBow extends BaseBow{

    static ItemStack item = new ItemStack(Material.BOW);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getBow() {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongswordBow_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongswordBow_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongswordBow_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongswordBow_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongswordBow_Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongswordBow_Line6.getValue()));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        double dmg = 7;
        double spd = -2.6;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Bows_LongSwordBow_Damage.getValue();
            spd = ConfigurationsDouble.Bows_LongSwordBow_Speed.getValue();
        }
        AttributeModifier modifier = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.attack_damage"), dmg,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        AttributeModifier modifier2 = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.attack_speed"), spd,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongswordBow_Name.getValue()));
        meta.setCustomModelData(3330004);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getBowRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "longsword_bow");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getBow());

        recipe.shape(
                " b ",
                " b ",
                "bab");

        recipe.setIngredient('a', Material.BOW);
        recipe.setIngredient('b', Material.IRON_INGOT);

        return recipe;
    }

    public static void setBowRecipe(){
        Bukkit.addRecipe(new LongswordBow().getBowRecipe());
    }
}
