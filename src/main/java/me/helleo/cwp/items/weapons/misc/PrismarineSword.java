package me.helleo.cwp.items.weapons.misc;

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

public class PrismarineSword extends WeaponBase {

    static ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
    static ItemMeta meta = item.getItemMeta();
    private static final String swordPath = "PrismarineSword";

    public static ItemStack getSword(){
        double attack_damage = 8;
        double attack_speed = -2.4;
        if(ConfigurationsBool.UseCustomValues.getValue()){
            attack_damage = getConfig().getDouble(swordPath+".Damage");
            attack_speed = getConfig().getDouble(swordPath+".Speed");
        }

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ATTACK_DAMAGE,new AttributeModifier(NamespacedKey.fromString("generic.prismarine_sword.attack_damage"),
                attack_damage,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.HAND
        ));
        modifiers.put(Attribute.ATTACK_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.prismarine_sword.attack_speed"),
                attack_speed,
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.HAND
        ));

        meta.setAttributeModifiers(modifiers);

        List<String> lore = new ArrayList<>();

        meta.setLore(setLore(lore,attack_damage,attack_speed));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(swordPath)));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setItemModel(new NamespacedKey("cwp","prismarine/prismarine_sword"));
        meta.setCustomModelData(1210001);
        item.setItemMeta(meta);
        return item;
    }

    public static void setPrismarineSwordRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "prismarine_sword");
        CombatWeaponryPlus.getRecipes().setKey(key);
        SmithingRecipe recipe = new SmithingTransformRecipe(key,
                new ItemStack(getSword()),
                new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), // template
                new RecipeChoice.MaterialChoice(Material.NETHERITE_SWORD), // base
                new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD) // add
        );
        Bukkit.addRecipe(recipe);
    }
}
