package me.helleo.cwp.items.armors;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class PlatedChainmailBoots extends BaseArmor {

    static ItemStack item = new ItemStack(Material.IRON_BOOTS);
    static ItemMeta meta = item.getItemMeta();


    public ItemStack getArmorPiece() {

        //modifier
        double def = 4;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            def = ConfigurationsDouble.Armors_PlateChainBoots_Armor.getValue();
        }

        AttributeModifier modifier = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.armor"), def,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier);

        meta.setDisplayName(ChatColor.BOLD + "Plated Chainmail Boots");
        if (ConfigurationsBool.EnchantsPlatedChainmail.getValue()) {
            int num = (int) ConfigurationsDouble.PlatedChainmailEnchantLevels_Unbreaking.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
        }

        item.setItemMeta(meta);
        return item;
    }


    public ShapedRecipe getArmorPieceRecipe() {

        // plated chainmail boots

        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "plated_chainmail_boots");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getArmorPiece());

        recipe.shape(
                "III",
                "IBI",
                "III");

        recipe.setIngredient('B', Material.CHAINMAIL_BOOTS);
        recipe.setIngredient('I', Material.IRON_NUGGET);

        return recipe;
    }

    public static void setArmorPieceRecipe(){
        Bukkit.addRecipe(new PlatedChainmailBoots().getArmorPieceRecipe());
    }
}
