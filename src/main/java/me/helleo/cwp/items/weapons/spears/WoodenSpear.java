package me.helleo.cwp.items.weapons.spears;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import me.helleo.cwp.configurations.ConfigurationsString;
import me.helleo.cwp.items.weapons.knives.WoodenKnife;
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

public class WoodenSpear extends BaseSpear {

    static ItemStack item = new ItemStack(Material.WOODEN_SWORD);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getSpear(){
        List<String> lore = setLore();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWoodenSpear_Line10.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWoodenSpear_Line11.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWoodenSpear_Line12.getValue()));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        double dmg = 1;
        double spd = -1.5;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Spears_WoodenSpear_Damage.getValue();
            spd = ConfigurationsDouble.Spears_WoodenSpear_Speed.getValue();
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionWoodenSpear_Name.getValue()));
        meta.setCustomModelData(1000004);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getSpearRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "wooden_spear");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getSpear());

        recipe.shape(
                " SS",
                " SS",
                "S  ");

        recipe.setIngredient('S', Material.STICK);
        return recipe;
    }

    public static void setSpearRecipe(){
        Bukkit.addRecipe(new WoodenKnife().getKnifeRecipe());
    }

}
