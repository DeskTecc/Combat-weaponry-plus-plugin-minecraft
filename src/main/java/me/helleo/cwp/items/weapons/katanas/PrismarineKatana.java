package me.helleo.cwp.items.weapons.katanas;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.configurations.ConfigurationsBool;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.Listener;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class PrismarineKatana extends BaseKatana implements Listener {

    //The prismarine items are based on upgrade of netherite items
    static ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String katanaPath = "PrismarineKatana";
    static double attack_damage = 8;
    static double attack_speed = -2.2;
    static double move_speed = 0.03;

    public static ItemStack getItem(){

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ATTACK_DAMAGE,new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                getAttackDamage(attack_damage, katanaPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));
        modifiers.put(Attribute.ATTACK_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                getAttackSpeed(attack_speed, katanaPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));
        modifiers.put(Attribute.MOVEMENT_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.move_speed"),
                getMoveSpeed(move_speed, katanaPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));

        meta.setAttributeModifiers(modifiers);

        meta.setLore(getLore(getAttackDamage(attack_damage, katanaPath),getAttackSpeed(attack_speed, katanaPath)));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                ConfigLoader.getLang().getString(katanaPath)));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setItemModel(new NamespacedKey("cwp","prismarine/prismarine_katana"));
        meta.setCustomModelData(1200002);
        item.setItemMeta(meta);
        return item;
    }

    public static double getAttackDamage(){
        return getAttackDamage(attack_damage, katanaPath);
    }

    public static double getAttackSpeed(){
        return getAttackSpeed(attack_speed, katanaPath);
    }

    public static double getMoveSpeed(){
        return getAttackSpeed(move_speed, katanaPath);
    }

    public static void setPrismarineKatanaRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "prismarine_katana");
        CombatWeaponryPlus.getRecipes().setKey(key);
        SmithingRecipe recipe = new SmithingTransformRecipe(key,
                new ItemStack(getItem()),
                new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), // template
                new RecipeChoice.ExactChoice(NetheriteKatana.getKatana()), // base
                new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD) // add
        );
        Bukkit.addRecipe(recipe);
    }
}
