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

public class PlatedChainmailLeggings extends BaseArmor {

    static ItemStack item = new ItemStack(Material.IRON_LEGGINGS);
    static ItemMeta meta = item.getItemMeta();


    public ItemStack getArmorPiece() {

        //modifier
        double def = 6;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            def = ConfigurationsDouble.Armors_PlateChainLeggings_Armor.getValue();
        }
        AttributeModifier modifier = new AttributeModifier(NamespacedKey.minecraft("generic.armor"), def,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier);

        meta.setDisplayName(ChatColor.BOLD + "Plated Chainmail Leggings");
        if (ConfigurationsBool.EnchantsPlatedChainmail.getValue()) {
            int num = (int) ConfigurationsDouble.PlatedChainmailEnchantLevels_Unbreaking.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, num, true);
        }

        item.setItemMeta(meta);
        return item;
    }


    public ShapedRecipe getArmorPieceRecipe() {

        // plated chainmail leggings

        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "plated_chainmail_leggings");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getArmorPiece());

        recipe.shape(
                "III",
                "ILI",
                "III");

        recipe.setIngredient('L', Material.CHAINMAIL_LEGGINGS);
        recipe.setIngredient('I', Material.IRON_NUGGET);

        return recipe;
    }

    public static void setArmorPieceRecipe(){
        Bukkit.addRecipe(new PlatedChainmailLeggings().getArmorPieceRecipe());
    }
}
