package me.helleo.cwp.items.weapons.spears;

import me.helleo.cwp.configurations.ConfigurationsBool;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class NetheriteSpear extends BaseSpear{

    static ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String spearPath = "NetheriteSpear";

    private static Material getNetherite(){
        if(ConfigurationsBool.NetheriteIngots.getValue()){
            return Material.NETHERITE_INGOT;
        }else{
            return Material.NETHERITE_SCRAP;
        }
    }
    public static ItemStack getSpear() {
        double attack_damage = 4;
        double attack_speed = -1.5;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = getCustomDamage(spearPath);
            attack_speed = getCustomSpeed(spearPath);
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED,
                new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                        attack_speed,
                        AttributeModifier.Operation.ADD_NUMBER,
                        EquipmentSlotGroup.HAND
                )
        );
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,
                new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                        attack_damage,
                        AttributeModifier.Operation.ADD_NUMBER,
                        EquipmentSlotGroup.HAND
                )
        );

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', description.NetheriteSpear.getValue()));
        meta.setCustomModelData(1000004);
        item.setItemMeta(meta);
        return item;
    }

    public static void setSpearRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                "spear",
                "netherite_spear",
                getSpear(),
                getNetherite()));
    }
}
