package me.helleo.cwp.items.weapons.longswords;

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

public class PrismarineLongsword extends BaseLongsword implements Listener {

    static ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String longswordPath = "PrismarineLongsword";
    static double attack_damage = 10;
    static double attack_speed = -2.7;

    public static ItemStack getItem(){

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ATTACK_DAMAGE,new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                getAttackDamage(attack_damage, longswordPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));
        modifiers.put(Attribute.ATTACK_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                getAttackSpeed(attack_speed, longswordPath),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));

        meta.setAttributeModifiers(modifiers);

        meta.setLore(getLore(getAttackDamage(attack_damage, longswordPath),getAttackSpeed(attack_speed, longswordPath)));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                ConfigLoader.getLang().getString(longswordPath)));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setItemModel(new NamespacedKey("cwp","prismarine/prismarine_longsword"));
        meta.setCustomModelData(1200001);
        item.setItemMeta(meta);
        return item;
    }

    public static double getAttackDamage(){
        return getAttackDamage(attack_damage, longswordPath);
    }

    public static double getAttackSpeed(){
        return getAttackSpeed(attack_speed, longswordPath);
    }

    public static void setPrismarineLongSwordRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "prismarine_longsword");
        CombatWeaponryPlus.getRecipes().setKey(key);
        SmithingRecipe recipe = new SmithingTransformRecipe(key,
                new ItemStack(getItem()),
                new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), // template
                new RecipeChoice.ExactChoice(NetheriteLongsword.getLongsword()), // base
                new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD) // add
        );
        Bukkit.addRecipe(recipe);
    }
}
