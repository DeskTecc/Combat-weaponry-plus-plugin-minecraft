package me.helleo.cwp.items.charms;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import me.helleo.cwp.configurations.ConfigurationsString;
import me.helleo.cwp.items.weapons.WeaponBase;
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

public class EmeraldCharm extends BaseCharm{

    static ItemStack item = new ItemStack(Material.EMERALD);
    static ItemMeta meta = item.getItemMeta();

    public ShapedRecipe getCharmRecipe() {

        double max_health = 4;
        double armor = -2;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            max_health = ConfigurationsDouble.Charms_EmeraldCharm_BonusHealth.getValue();
            armor = ConfigurationsDouble.Charms_EmeraldCharm_BonusArmor.getValue();

        }

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.MAX_HEALTH,new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.getPlugin(),"generic.max_health"), max_health,
                        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
        modifiers.put(Attribute.ARMOR,new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.getPlugin(),"generic.armor"), armor,
                        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.OFFHAND));
        meta.setAttributeModifiers(modifiers);

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionEmeraldCharm_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionEmeraldCharm_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionEmeraldCharm_Line3.getValue()));
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7When in Off Hand:"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9+"+String.format("%.1f",max_health/2).replace(',','.')+" Max Health"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&c"+String.format("%.1f",armor/2).replace(',','.')+" Armor"));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionEmeraldCharm_Name.getValue()));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);


        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "emerald_charm");
        CombatWeaponryPlus.getRecipes().setKey(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(
                "dLd",
                "LFL",
                "dLd");

        recipe.setIngredient('F', Material.EMERALD);
        recipe.setIngredient('L', Material.LAPIS_BLOCK);
        recipe.setIngredient('d', Material.DIAMOND);

        return recipe;
    }

    public static void setCharmRecipe(){
        Bukkit.addRecipe(new EmeraldCharm().getCharmRecipe());
    }
}
