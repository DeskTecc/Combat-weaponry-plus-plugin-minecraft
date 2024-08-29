package me.helleo.cwp.items.weapons.misc;

import me.helleo.cwp.CombatWeaponryPlus;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TridentBow{

    static ItemStack item = new ItemStack(Material.BOW);
    static ItemMeta meta = item.getItemMeta();

    public ItemStack getBow() {
        List<String> lore = new ArrayList<>();
        lore.add("");

        lore.add(ChatColor.translateAlternateColorCodes('&', "&6I made this for fun"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7- Shoots tridents (converts arrows to tridents)"));
        lore.add("");
        meta.setLore(lore);


        meta.setDisplayName("Trident Bow");
        meta.setCustomModelData(1069691);
        item.setItemMeta(meta);
        return item;
    }

    public ShapedRecipe getBowRecipe() {
        NamespacedKey key = new NamespacedKey(CombatWeaponryPlus.plugin, "trident_bow");
        CombatWeaponryPlus.keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, getBow());

        recipe.shape(
                " b ",
                "bab",
                " b ");

        recipe.setIngredient('a', Material.TRIDENT);
        recipe.setIngredient('b', Material.BEDROCK);

        return recipe;
    }

    public static void setBowRecipe(){
        Bukkit.addRecipe(new TridentBow().getBowRecipe());
    }
}
