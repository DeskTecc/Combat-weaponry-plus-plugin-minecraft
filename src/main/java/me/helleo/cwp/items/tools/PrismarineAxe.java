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

public class PrismarineAxe extends WeaponBase {

    static ItemStack item = new ItemStack(Material.NETHERITE_AXE);
    static ItemMeta meta = item.getItemMeta();
    private static final String axePath = "PrismarineAxe";

    public static ItemStack getAxe(){
        double attack_damage = 10;
        double attack_speed = -3;
        if(ConfigurationsBool.UseCustomValues.getValue()){
            attack_damage = getConfig().getDouble(axePath+".Damage");
            attack_speed = getConfig().getDouble(axePath+".Speed");
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

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(axePath)));

        meta.setItemModel(new NamespacedKey("cwp","prismarine/prismarine_axe"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setCustomModelData(1220001);
        item.setItemMeta(meta);
        return item;
    }

    public static void setPrismarineAxeRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "prismarine_axe");
        CombatWeaponryPlus.getRecipes().setKey(key);
        SmithingRecipe recipe = new SmithingTransformRecipe(key,
                new ItemStack(getAxe()),
                new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), // template
                new RecipeChoice.MaterialChoice(Material.NETHERITE_AXE), // base
                new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD) // add
        );
        Bukkit.addRecipe(recipe);
    }
}
