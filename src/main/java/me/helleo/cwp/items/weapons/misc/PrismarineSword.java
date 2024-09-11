package me.helleo.cwp.items.weapons.misc;

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

public class PrismarineSword implements Listener {

    public ItemStack getItem(ItemStack item){
        ItemMeta itemMeta = item.getItemMeta();

        assert itemMeta != null;

        double attack_damage = 8;
        double attack_speed = -2.4;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = ConfigurationsDouble.Swords_PrismarineSword_Damage.getValue();
            attack_speed = ConfigurationsDouble.Swords_PrismarineSword_Speed.getValue();
        }

        AttributeModifier modifier1 = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.atack_damage"), attack_damage,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.atack_speed"), attack_speed,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2);

        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineSword_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineSword_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineSword_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineSword_Line4.getValue()));
        itemMeta.setLore(lore);

        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineSword_Name.getValue()));
        //important:
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.setCustomModelData(1210001);
        item.setItemMeta(itemMeta);
        return item;
    }

    @EventHandler
    void onSmithingTableEventSWORD(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();

        //CRAFTING ON SMITHING TABLE:
        // LAPIS_LAZULI + NETHERITE_SWORD + PRISMARINE_SHARD

        ItemStack templ = inventory.getItem(0); // needs to be Lapis Lazuli
        ItemStack tool = inventory.getItem(1); // needs to be a Netherite Sword
        ItemStack modifier = inventory.getItem(2); // needs to be a Prismarine Shard
        if(templ!=null && tool!=null && modifier!=null){
            if(templ.getType().equals(Material.LAPIS_LAZULI) &&
                    tool.getType().equals(Material.NETHERITE_SWORD) &&
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
