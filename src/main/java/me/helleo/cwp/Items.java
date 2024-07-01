package me.helleo.cwp;

import me.helleo.cwp.configurations.ConfigurationsBool;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Items {

    public static ItemStack itemname() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = (ItemMeta) item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lsword."));

        ArrayList<String> lore = new ArrayList<String>();
        lore.add("wow");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack emeraldHelmet(FileConfiguration config) {

        ItemStack item = new ItemStack(Material.GOLDEN_HELMET);
        ItemMeta meta = item.getItemMeta();

        //modifier
        double hp = 1;
        double def = 2;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            hp = config.getDouble("aEmeraldHelmet.BonusHealth");
            def = config.getDouble("aEmeraldHelmet.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Defense", def,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier2);

        meta.setDisplayName(ChatColor.DARK_GREEN + "Emerald Helmet");

        if (config.getString("EnchantmentsOnEmeraldArmor") == "true") {
            int num = config.getInt("EmeraldArmorEnchantLevels.Unbreaking");
            int num2 = config.getInt("EmeraldArmorEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }

        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack witherBone(FileConfiguration config) {

        ItemStack item = new ItemStack(Material.BONE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta.setCustomModelData(2222222);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack vessel(FileConfiguration config) {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta vmeta = item.getItemMeta();
        vmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("dVessel.name")));
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', config.getString("dVessel.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', config.getString("dVessel.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', config.getString("dVessel.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', config.getString("dVessel.line4")));
        vmeta.setLore(lore);
        vmeta.setCustomModelData(2222223);
        item.setItemMeta(vmeta);
        return item;
    }
}
