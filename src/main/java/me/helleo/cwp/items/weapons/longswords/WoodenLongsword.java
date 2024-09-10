package me.helleo.cwp.items.weapons.longswords;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class WoodenLongsword extends BaseLongsword{

    static ItemStack item = new ItemStack(Material.WOODEN_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String material = "Wooden";

    public static ItemStack getLongsword(){
        double attack_damage = 4;
        double attack_speed = -2.8;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = ConfigurationsDouble.Longswords_WoodenLongsword_Damage.getValue();
            attack_speed = ConfigurationsDouble.Longswords_WoodenLongsword_Speed.getValue();
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, setModifier("generic.attack_speed", attack_speed));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, setModifier("generic.attack_damage", attack_damage));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', setName(material)));
        meta.setCustomModelData(1000001);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getLongswordRecipe(){
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "wooden_longsword");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getLongsword());

        recipe.shape(
                " S ",
                " S ",
                "SSS");


        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public static void setLongswordRecipe(){
        Bukkit.addRecipe(new WoodenLongsword().getLongswordRecipe());
    }

}
