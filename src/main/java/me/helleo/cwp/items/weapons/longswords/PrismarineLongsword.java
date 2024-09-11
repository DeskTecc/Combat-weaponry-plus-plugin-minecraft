package me.helleo.cwp.items.weapons.longswords;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import me.helleo.cwp.configurations.ConfigurationsString;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PrismarineLongsword implements Listener {

    public ItemStack getItem(ItemStack item){

        ItemMeta itemMeta = item.getItemMeta();

        assert itemMeta != null;

        double attack_damage = 1;
        double attack_speed = -2.8;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = ConfigurationsDouble.Longswords_PrismarineLongsword_DamageAdded.getValue();
        }

        AttributeModifier modifier1 = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.attack_damage"), attack_damage,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);

        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);

        AttributeModifier modifier2 = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.attack_speed"), attack_speed,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongsword_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongsword_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongsword_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongsword_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionLongsword_Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineLongsword_Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineLongsword_Line7.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineLongsword_Line8.getValue()));

        itemMeta.setLore(lore);

        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineLongsword_Name.getValue()));
        //important:
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.setCustomModelData(1200001);
        item.setItemMeta(itemMeta);
        return item;
    }

    @EventHandler
    void onSmithingTableEventLONGSWORD(PrepareSmithingEvent event) {

        //CRAFTING ON SMITHING TABLE:
        // LAPIS_LAZULI + NETHERITE LONGSWORD + PRISMARINE_SHARD

        SmithingInventory inventory = event.getInventory();

        ItemStack templ = inventory.getItem(0); // new
        ItemStack tool = inventory.getItem(1); // was 0
        ItemStack modifier = inventory.getItem(2); // was 1

        if(templ!=null && tool!=null && modifier!=null){
            if(templ.getType().equals(Material.LAPIS_LAZULI) &&
                tool.isSimilar(NetheriteLongsword.getLongsword()) &&
                modifier.getType().equals(Material.PRISMARINE_SHARD)
            ){
                ItemStack item = getItem(tool.clone());

                if (ConfigurationsBool.Prismarine.getValue()) {
                    event.setResult(item);
                }
            }
        }
    }
}
