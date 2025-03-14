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


public class EmeraldBoots extends BaseArmor{

    static ItemStack item = new ItemStack(Material.GOLDEN_BOOTS);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getArmorPiece(){

        //emerald boots

        double hp = 1;
        double def = 2;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            hp = ConfigurationsDouble.Armors_EmeraldBoots_BonusHealth.getValue();
            def = ConfigurationsDouble.Armors_EmeraldBoots_Armor.getValue();
        }

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.GENERIC_MAX_HEALTH,new AttributeModifier(NamespacedKey.fromString("generic.emerald_boots.max_health"), hp,
                        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
        modifiers.put(Attribute.GENERIC_ARMOR,new AttributeModifier(NamespacedKey.fromString("generic.emerald_boots.armor"), def,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName((ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("Emerald_Boots"))));
        if (ConfigurationsBool.EnchantmentsOnEmeraldArmor.getValue()) {
            int unbreakingLevel = (int) ConfigurationsDouble.EmeraldArmorEnchantLevels_Unbreaking.getValue();
            int mendingLevel = (int) ConfigurationsDouble.EmeraldArmorEnchantLevels_Mending.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, unbreakingLevel, true);
            meta.addEnchant(Enchantment.MENDING, mendingLevel, true);
        }
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getArmorPieceRecipe() {

        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "emerald_boots");
        CombatWeaponryPlus.getRecipes().setKey(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getArmorPiece());

        recipe.shape(
                "   ",
                "E E",
                "E E");

        recipe.setIngredient('E', Material.EMERALD);

        return recipe;
    }

    public static void setArmorPieceRecipe(){
        Bukkit.addRecipe(new EmeraldBoots().getArmorPieceRecipe());
    }
}
