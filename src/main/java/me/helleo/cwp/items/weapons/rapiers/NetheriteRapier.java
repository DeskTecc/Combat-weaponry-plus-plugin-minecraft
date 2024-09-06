package me.helleo.cwp.items.weapons.rapiers;

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

public class NetheriteRapier extends BaseRapier{

    static ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String material = "Netherite";

    public ItemStack getRapier() {
        double attack_damage = 5;
        double attack_speed = -2.1;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = ConfigurationsDouble.Rapiers_NetheriteRapier_Damage.getValue();
            attack_speed = ConfigurationsDouble.Rapiers_NetheriteRapier_Speed.getValue();
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, setModifier("generic.attack_speed", attack_speed));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, setModifier("generic.attack_damage", attack_damage));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', setName(material)));
        meta.setCustomModelData(1000005);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getRapierRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "netherite_rapier");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getRapier());

        recipe.shape(
                "  C",
                "CC ",
                "SC ");

        recipe.setIngredient('S', Material.STICK);

        if (ConfigurationsBool.NetheriteIngots.getValue()) {
            recipe.setIngredient('C', Material.NETHERITE_INGOT);
        } else {
            recipe.setIngredient('C', Material.NETHERITE_SCRAP);
        }

        return recipe;
    }

    public static void setRapierRecipe(){
        Bukkit.addRecipe(new NetheriteRapier().getRapierRecipe());
    }

}
