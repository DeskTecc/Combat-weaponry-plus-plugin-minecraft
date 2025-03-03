package me.helleo.cwp.items.armors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.helleo.cwp.CombatWeaponryPlus;
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

public class PrismarineHelmet {

    static ItemStack item = new ItemStack(Material.NETHERITE_HELMET);
    static ItemMeta meta = item.getItemMeta();


    public static ItemStack getArmorPiece() {

        //modifier
        double def = 4;
        double toughness = 3;
        double KBResistance = 0.1;
        double hp = 1;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            def = ConfigurationsDouble.Armors_PrismarineHelmet_Armor.getValue();
            toughness = ConfigurationsDouble.Armors_PrismarineHelmet_ArmorToughness.getValue();
            KBResistance = ConfigurationsDouble.Armors_PrismarineHelmet_KBResist.getValue() / 10;
            hp = ConfigurationsDouble.Armors_PrismarineHelmet_BonusHealth.getValue();
        }

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.GENERIC_ARMOR,new AttributeModifier(NamespacedKey.fromString("generic.prismarine_helmet.armor"), def,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        modifiers.put(Attribute.GENERIC_ARMOR_TOUGHNESS,new AttributeModifier(NamespacedKey.fromString("generic.prismarine_helmet.armor_toughness"), toughness,
                        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        modifiers.put(Attribute.GENERIC_KNOCKBACK_RESISTANCE,new AttributeModifier(NamespacedKey.fromString("generic.prismarine_helmet.knockback_resistance"), KBResistance,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        modifiers.put(Attribute.GENERIC_MAX_HEALTH,new AttributeModifier(NamespacedKey.fromString("generic.prismarine_helmet.max_health"), hp,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));

        meta.setCustomModelData(1220001);
        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName((ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("DescriptionPrismarineHelmet.Name"))));
        item.setItemMeta(meta);
        return item;
    }

    public static void setArmorPieceRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "prismarine_helmet");
        CombatWeaponryPlus.getRecipes().setKey(key);
        SmithingRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(CombatWeaponryPlus.getPlugin(), "prismarine_helmet"),
                new ItemStack(getArmorPiece()),
                new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), // template
                new RecipeChoice.MaterialChoice(Material.NETHERITE_HELMET),
                new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD)
        );
        Bukkit.addRecipe(recipe);
    }
}
