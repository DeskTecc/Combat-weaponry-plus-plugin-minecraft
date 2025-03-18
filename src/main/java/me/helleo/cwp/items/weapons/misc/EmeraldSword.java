package me.helleo.cwp.items.weapons.misc;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.items.weapons.WeaponBase;
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

import java.util.ArrayList;
import java.util.List;

public class EmeraldSword extends WeaponBase {

    static ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
    static ItemMeta meta = item.getItemMeta();
    private static final String swordPath = "EmeraldSword";
    private static double attack_damage = 5;
    private static double attack_speed = -2.2;


    public ItemStack getSword(){
        //modifier

        Multimap<Attribute,AttributeModifier> modifiers = ArrayListMultimap.create();
        modifiers.put(Attribute.ATTACK_DAMAGE,new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.getPlugin(),"generic.attack_damage"), getAttackDamage(),
                        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));
        modifiers.put(Attribute.ATTACK_SPEED,new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.getPlugin(),"generic.attack_speed"), getAttackSpeed(),
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND));

        meta.setAttributeModifiers(modifiers);

        List<String> lore = new ArrayList<String>();

        meta.setLore(WeaponBase.setLore(lore, getAttackDamage(), getAttackSpeed()));

        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString("Emerald_Sword")));
        meta.setItemModel(new NamespacedKey("cwp","emerald/emerald_sword"));

        if (ConfigurationsBool.EnchantmentsOnEmeraldGear.getValue()) {
            int unbreakingLevel = (int) ConfigurationsDouble.EmeraldGearEnchantLevels_Unbreaking.getValue();
            int mendingLevel = (int) ConfigurationsDouble.EmeraldGearEnchantLevels_Mending.getValue();
            meta.addEnchant(Enchantment.UNBREAKING, unbreakingLevel, true);
            meta.addEnchant(Enchantment.MENDING, mendingLevel, true);
        }
        item.setItemMeta(meta);
        return item;
    }

    public static double getAttackDamage(){
        if(ConfigurationsBool.UseCustomValues.getValue()){
            attack_damage = getCustomDamage(swordPath);
        }
        return attack_damage;
    }

    public static double getAttackSpeed(){
        if(ConfigurationsBool.UseCustomValues.getValue()){
            attack_speed = getCustomSpeed(swordPath);
        }
        return attack_speed;
    }

    public ShapedRecipe getToolRecipe(){

        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.getPlugin(), "emerald_sword");
        CombatWeaponryPlus.getRecipes().setKey(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getSword());

        recipe.shape(
                " E ",
                " E ",
                " S ");

        recipe.setIngredient('E', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public static void setToolRecipe(){
        Bukkit.addRecipe(new EmeraldSword().getToolRecipe());
    }

}
