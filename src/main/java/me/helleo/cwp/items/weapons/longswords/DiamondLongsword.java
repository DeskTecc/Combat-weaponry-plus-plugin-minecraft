package me.helleo.cwp.items.weapons.longswords;

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

import java.util.List;
import java.util.UUID;

public class DiamondLongsword extends BaseLongsword{

    static ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getLongsword() {
        List<String> lore = setLore();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionDiamondLongsword_Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionDiamondLongsword_Line7.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionDiamondLongsword_Line8.getValue()));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier
        double dmg = 7;
        double spd = -2.8;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Longswords_DiamondLongsword_Damage.getValue();
            spd = ConfigurationsDouble.Longswords_DiamondLongsword_Speed.getValue();
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionDiamondLongsword_Name.getValue()));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getLongswordRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "diamond_longsword");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getLongsword());

        recipe.shape(
                " C ",
                " C ",
                "CSC");

        recipe.setIngredient('C', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public static void setLongswordRecipe(){
        Bukkit.addRecipe(new DiamondLongsword().getLongswordRecipe());
    }
}
