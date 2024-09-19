package me.helleo.cwp.items.weapons.knives;

import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class EmeraldKnife extends BaseKnife{

    static ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
    static ItemMeta meta = item.getItemMeta();

    public static ItemStack getKnife() {
        double attack_damage = 2;
        double attack_speed = 0;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            attack_damage = ConfigurationsDouble.Knives_EmeraldKnife_Damage.getValue();
            attack_speed = ConfigurationsDouble.Knives_EmeraldKnife_Speed.getValue();
        }

        meta.setLore(getLore(attack_damage,attack_speed));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, setModifier("generic.attack_speed", attack_speed));
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, setModifier("generic.attack_damage", attack_damage));

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName(Material.EMERALD)));

        if (ConfigurationsBool.EmeraldGear.getValue()) {
            int unbreakingValue = (int) ConfigurationsDouble.EmeraldGearEnchantLevels_Unbreaking.getValue();
            int mendingValue = (int) ConfigurationsDouble.EmeraldGearEnchantLevels_Mending.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, unbreakingValue, true);
            meta.addEnchant(Enchantment.MENDING, mendingValue, true);
        }
        meta.setCustomModelData(1000016);
        item.setItemMeta(meta);
        return item;
    }

    public static void setKnifeRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                "knife",
                "emerald_knife",
                getKnife(),
                Material.EMERALD));
    }
}
