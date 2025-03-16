package me.helleo.cwp.items.weapons.bows;

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
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

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

        double attack_damage = 7;
        double attack_speed = -2.6;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = ConfigurationsDouble.Bows_LongSwordBow_Damage.getValue();
            attack_speed = ConfigurationsDouble.Bows_LongSwordBow_Speed.getValue();
        }

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.getPlugin(),"generic.attack_damage"), attack_damage,
                        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
        modifiers.put(Attribute.ATTACK_SPEED,new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.getPlugin(),"generic.attack_speed"), attack_speed,
                        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
        meta.setAttributeModifiers(modifiers);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongswordBow_Name.getValue()));
        meta.setCustomModelData(3330004);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getBowRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "longsword_bow");
        CombatWeaponryPlus.getRecipes().setKey(key);
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
