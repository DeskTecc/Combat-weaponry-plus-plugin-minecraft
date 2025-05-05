package me.helleo.cwp.items.weapons.cleavers;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

public class EmeraldCleaver extends BaseCleaver{

    static ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
    static ItemMeta meta = item.getItemMeta();
    static String cleaverPath = "EmeraldCleaver";
    private static double attack_damage = 10;
    private static double attack_speed = -3.6;

    public static ItemStack getCleaver() {

        meta.setLore(getLore(getAttackDamage(),getAttackSpeed()));
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ATTACK_DAMAGE,new AttributeModifier(NamespacedKey.fromString("generic.attack_damage"),
                getAttackDamage(),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));
        modifiers.put(Attribute.ATTACK_SPEED,new AttributeModifier(NamespacedKey.fromString("generic.attack_speed"),
                getAttackSpeed(),
                AttributeModifier.Operation.ADD_NUMBER,
                EquipmentSlotGroup.MAINHAND
        ));

        meta.setAttributeModifiers(modifiers);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                ConfigLoader.getLang().getString(cleaverPath)));
        meta.setItemModel(new NamespacedKey("cwp","emerald/emerald_cleaver"));

        //enchants
        if (ConfigurationsBool.EnchantmentsOnEmeraldGear.getValue()) {
            int unbreakingValue = (int) ConfigurationsDouble.EmeraldGearEnchantLevels_Unbreaking.getValue();
            int mendingValue = (int) ConfigurationsDouble.EmeraldGearEnchantLevels_Mending.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, unbreakingValue, true);
            meta.addEnchant(Enchantment.MENDING, mendingValue, true);
        }
        meta.setCustomModelData(1000031);
        item.setItemMeta(meta);
        return item;
    }

    public static double getAttackDamage(){
        if(ConfigurationsBool.UseCustomValues.getValue()){
            attack_damage = getCustomDamage(cleaverPath);
        }
        return attack_damage;
    }

    public static double getAttackSpeed(){
        if(ConfigurationsBool.UseCustomValues.getValue()){
            attack_speed = getCustomSpeed(cleaverPath);
        }
        return attack_speed;
    }

    public static void setCleaverRecipe(){
        Bukkit.addRecipe(getWeaponRecipe(
                "cleaver",
                "emerald_cleaver",
                getCleaver(),
                Material.EMERALD));
    }
}
