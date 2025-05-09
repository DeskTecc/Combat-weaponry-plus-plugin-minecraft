package me.helleo.cwp.items.armors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.Items;
import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
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

public class WitherBoots {

    static ItemStack item = new ItemStack(Material.IRON_BOOTS);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getArmorPiece(){
        double kbr = 0.2;
        double hp = 5;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            kbr = ConfigurationsDouble.Armors_WitherBoots_KBResist.getValue() / 10;
            hp = ConfigurationsDouble.Armors_WitherBoots_BonusHealth.getValue();
        }

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.MAX_HEALTH,new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.getPlugin(),"generic.max_health"), hp,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
        modifiers.put(Attribute.KNOCKBACK_RESISTANCE,new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.getPlugin(),"generic.knockback_resistance"), kbr,
                        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName((ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("WitheringBoots"))));
        meta.setItemModel(new NamespacedKey("cwp","wither/wither_boots"));
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("DescriptionWitheringArmorSet.Line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("DescriptionWitheringArmorSet.Line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("DescriptionWitheringArmorSet.Line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("DescriptionWitheringArmorSet.Line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("DescriptionWitheringArmorSet.Line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("DescriptionWitheringArmorSet.Line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("DescriptionWitheringArmorSet.Line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("DescriptionWitheringArmorSet.Line8")));

        meta.setLore(lore);
        meta.setCustomModelData(5553334);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getArmorRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "wither_boots");
        CombatWeaponryPlus.getRecipes().setKey(key);

        ItemStack wbone = new ItemStack(Material.BONE);
        ItemMeta meta2 = wbone.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta2.setCustomModelData(2222222);
        wbone.setItemMeta(meta2);

        @SuppressWarnings("deprecation")
        RecipeChoice wibone = new RecipeChoice.ExactChoice(Items.witherBone());
        ShapedRecipe recipe = new ShapedRecipe(key, getArmorPiece());
        recipe.shape(
                "   ",
                "BIB",
                "N N");

        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('B', wibone);
        return recipe;
    }

    public static void setArmorRecipe(){
        Bukkit.addRecipe(new WitherBoots().getArmorRecipe());
    }

}
