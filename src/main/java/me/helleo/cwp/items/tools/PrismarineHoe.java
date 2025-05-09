package me.helleo.cwp.items.tools;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static me.helleo.cwp.configurations.ConfigLoader.getConfig;

public class PrismarineHoe extends WeaponBase {

    static ItemStack item = new ItemStack(Material.NETHERITE_HOE);
    static ItemMeta meta = item.getItemMeta();
    private static final String hoePath = "PrismarineHoe";

    public static ItemStack getTool(){
        double attack_damage = 1;
        double attack_speed = 0;
        if(ConfigurationsBool.UseCustomValues.getValue()){
            attack_damage = getConfig().getDouble(hoePath+".Damage");
            attack_speed = getConfig().getDouble(hoePath+".Speed");
        }

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ATTACK_DAMAGE, new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                attack_damage,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.HAND
        ));
        modifiers.put(Attribute.ATTACK_SPEED, new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                attack_speed,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.HAND
        ));

        meta.setAttributeModifiers(modifiers);

        List<String> lore = new ArrayList<>();

        meta.setLore(setLore(lore, attack_damage, attack_speed));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(hoePath)));

        meta.setItemModel(new NamespacedKey("cwp","prismarine/prismarine_hoe"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setCustomModelData(1210005);
        item.setItemMeta(meta);
        return item;
    }

    public static void setPrismarineHoeRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "prismarine_hoe");
        CombatWeaponryPlus.getRecipes().setKey(key);
        SmithingRecipe recipe = new SmithingTransformRecipe(key,
                new ItemStack(getTool()),
                new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), // template
                new RecipeChoice.MaterialChoice(Material.NETHERITE_HOE), // base
                new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD) // add
        );
        Bukkit.addRecipe(recipe);
    }
}
