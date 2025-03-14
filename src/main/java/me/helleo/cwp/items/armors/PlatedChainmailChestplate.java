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

public class PlatedChainmailChestplate extends BaseArmor {

    static ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
    static ItemMeta meta = item.getItemMeta();


    public ItemStack getArmorPiece() {

        //modifier
        double def = 6;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            def = ConfigurationsDouble.Armors_PlateChainChestplate_Armor.getValue();
        }

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.GENERIC_ARMOR,new AttributeModifier(NamespacedKey.fromString("generic.plated_chainmail_chestplate.armor"), def,
                        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName((ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("PlatedChainmail_Chestplate"))));
        if (ConfigurationsBool.EnchantsPlatedChainmail.getValue()) {
            int num = (int) ConfigurationsDouble.PlatedChainmailEnchantLevels_Unbreaking.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
        }

        item.setItemMeta(meta);
        return item;
    }


    public ShapedRecipe getArmorPieceRecipe() {

        // plated chainmail chestplate

        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "plated_chainmail_chestplate");
        CombatWeaponryPlus.getRecipes().setKey(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getArmorPiece());

        recipe.shape(
                "III",
                "ICI",
                "III");

        recipe.setIngredient('C', Material.CHAINMAIL_CHESTPLATE);
        recipe.setIngredient('I', Material.IRON_NUGGET);

        return recipe;
    }

    public static void setArmorPieceRecipe(){
        Bukkit.addRecipe(new PlatedChainmailChestplate().getArmorPieceRecipe());
    }
}
