package me.helleo.cwp.items.weapons.knives;

import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.items.weapons.WeaponBase;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseKnife extends WeaponBase {
    public enum description {
        Line1("DescriptionKnife.Line1"),
        Line2("DescriptionKnife.Line2"),
        Line3("DescriptionKnife.Line3"),
        Line4("DescriptionKnife.Line4"),
        Line5("DescriptionKnife.Line5"),
        Line6("DescriptionKnife.Line6");

        private final String description_value;

        description(String value){
            this.description_value = value;
        }
        public String getValue(){
            return this.description_value;
        }
    }

    public static List<String> getLore(double attack_damage, double attack_speed) {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line1.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line2.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line3.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line4.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line5.getValue())));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigLoader.getLang().getString(description.Line6.getValue())));
        return setLore(lore, attack_damage,attack_speed);
    }

    public static boolean isKnife(ItemStack item){
        if(item.hasItemMeta()){
            if(item.getItemMeta().hasCustomModelData()) {
                return item.getItemMeta().getCustomModelData() == 1000006 ||
                        item.getItemMeta().getCustomModelData() == 1000016 ||
                        item.getItemMeta().getCustomModelData() == 1200006;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
