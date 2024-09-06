package me.helleo.cwp.items.weapons.cleavers;

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

public class StoneCleaver extends BaseCleaver{

    static ItemStack item = new ItemStack(Material.STONE_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String material = "Stone";

    public ItemStack getCleaver() {
        double attack_damage = 9;
        double attack_speed = -3.6;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = ConfigurationsDouble.Cleavers_StoneCleaver_Damage.getValue();
            attack_speed = ConfigurationsDouble.Cleavers_StoneCleaver_Speed.getValue();
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, setModifier("generic.attack_speed",attack_speed));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, setModifier("generic.attack_damage", attack_damage));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', setName(material)));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getCleaverRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "stone_cleaver");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getCleaver());

        recipe.shape(
                " MM",
                "MM ",
                "S  ");

        recipe.setIngredient('M', Material.COBBLESTONE);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public static void setCleaverRecipe(){
        Bukkit.addRecipe(new StoneCleaver().getCleaverRecipe());
    }
}
