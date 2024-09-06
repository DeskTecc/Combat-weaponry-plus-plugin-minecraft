package me.helleo.cwp.items.weapons.katanas;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class EmeraldKatana extends BaseKatana{

    static ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String material = "Emerald";

    public ItemStack getKatana() {
        double attack_damage = 4;
        double attack_speed = -2;
        double move_speed = 0.02;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = ConfigurationsDouble.Katanas_EmeraldKatana_Damage.getValue();
            attack_speed = ConfigurationsDouble.Katanas_EmeraldKatana_Speed.getValue();
            move_speed = ConfigurationsDouble.Katanas_EmeraldKatana_MoveSpeed.getValue();
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, setModifier("generic.attack_speed", attack_speed));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, setModifier("generic.attack_damage", attack_damage));
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, setModifier("generic.move_speed", move_speed));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', setName(material)));
        meta.setCustomModelData(1000012);
        if (ConfigurationsBool.EnchantmentsOnEmeraldGear.getValue()) {
            int unbreakingValue = (int) ConfigurationsDouble.EmeraldGearEnchantLevels_Unbreaking.getValue();
            int mendingValue = (int) ConfigurationsDouble.EmeraldGearEnchantLevels_Mending.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, unbreakingValue, true);
            meta.addEnchant(Enchantment.MENDING, mendingValue, true);
        }
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getKatanaRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "emerald_katana");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getKatana());

        recipe.shape(
                "  M",
                " M ",
                "S  ");

        recipe.setIngredient('M', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public static void setKatanaRecipe(){
        Bukkit.addRecipe(new EmeraldKatana().getKatanaRecipe());
    }
}
