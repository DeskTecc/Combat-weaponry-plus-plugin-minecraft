package me.helleo.cwp.listeners;

import me.helleo.cwp.Cooldown;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class PlayerClick implements Listener {

    @EventHandler()
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType().equals(Material.IRON_SWORD)){
            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()){
                if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()
                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000007) {
                    //Right click
                    if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                        if (Cooldown.checkCooldown(event.getPlayer())) {
                            player.launchProjectile(EnderPearl.class);
                            Cooldown.setCooldown(event.getPlayer(), 2);
                        }
                    }
                }
            }
        }
        if (player.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_PICKAXE)) {
            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000001) {
                        //left click
                        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 40, -2));
                        }
                    }
                }
            }
        }
        if (player.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_HOE)){
            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                    if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1234567) {
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.MUSIC_DISC_CAT, 10, 1);
                            ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                            meta.setDisplayName("GOTTEM");
                            List<String> lore = new ArrayList<>();
                            lore.add("");
                            lore.add(ChatColor.translateAlternateColorCodes('&', "&6im sorry"));
                            lore.add("");
                            meta.setLore(lore);
                            meta.setCustomModelData(6969420);
                            player.getInventory().getItemInMainHand().setItemMeta(meta);
                        }
                    }
                }
            }
        }
    }
}
