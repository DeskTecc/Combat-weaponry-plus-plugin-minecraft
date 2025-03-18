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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class EmeraldHelmet extends BaseArmor {

    static ItemStack item = new ItemStack(Material.GOLDEN_HELMET);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getArmorPiece() {
        double hp = 1;
        double def = 2;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            hp = ConfigurationsDouble.Armors_EmeraldHelmet_BonusHealth.getValue();
            def = ConfigurationsDouble.Armors_EmeraldHelmet_Armor.getValue();
        }

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.MAX_HEALTH,new AttributeModifier(NamespacedKey.fromString("generic.emerald_helmet.max_health"), hp,
                        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
        modifiers.put(Attribute.ARMOR, new AttributeModifier(NamespacedKey.fromString("generic.emerald_helmet.armor"), def,
                        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));

        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName((ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("EmeraldHelmet"))));

        if (ConfigurationsBool.EnchantmentsOnEmeraldArmor.getValue()) {
            int unbreakingLevel = (int) ConfigurationsDouble.EmeraldArmorEnchantLevels_Unbreaking.getValue();
            int mendingLevel = (int) ConfigurationsDouble.EmeraldArmorEnchantLevels_Mending.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, unbreakingLevel, true);
            meta.addEnchant(Enchantment.MENDING, mendingLevel, true);
        }
        meta.setItemModel(new NamespacedKey("cwp","emerald/emerald_helmet"));
        item.setItemMeta(meta);
        return item;
    }


    public ShapedRecipe getArmorPieceRecipe() {
        //emerald helmet

        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "emerald_helmet");
        CombatWeaponryPlus.getRecipes().setKey(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getArmorPiece());

        recipe.shape(
                "EEE",
                "E E",
                "   ");

        recipe.setIngredient('E', Material.EMERALD);
        return recipe;
    }
    public static void setArmorPieceRecipe(){
        Bukkit.addRecipe(new EmeraldHelmet().getArmorPieceRecipe());
    }
}
