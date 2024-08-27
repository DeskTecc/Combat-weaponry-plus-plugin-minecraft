package me.helleo.cwp.items.charms;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsString;
import me.helleo.cwp.items.armors.PlatedChainmailBoots;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class FeatherCharm extends BaseCharm{

    static ItemStack item = new ItemStack(Material.FEATHER);
    static ItemMeta meta = item.getItemMeta();

    public ShapedRecipe getCharmRecipe(){
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionFeatherCharm_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionFeatherCharm_Line2.getValue()));
        meta.setLore(lore);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionFeatherCharm_Name.getValue()));
        meta.addEnchant(Enchantment.UNBREAKING, 5, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "feather_charm");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("dLd", "LFL", "dLd");

        recipe.setIngredient('F', Material.FEATHER);
        recipe.setIngredient('d', Material.DIAMOND);
        recipe.setIngredient('L', Material.LAPIS_BLOCK);

        return recipe;
    }

    public static void setCharmRecipe(){
        Bukkit.addRecipe(new FeatherCharm().getCharmRecipe());
    }
}
