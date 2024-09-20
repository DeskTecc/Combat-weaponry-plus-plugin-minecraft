package me.helleo.cwp.listeners;

import me.helleo.cwp.CombatWeaponryPlus;
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

import static org.bukkit.Bukkit.getServer;

public class PlayerClick implements Listener {

    @EventHandler()
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        //parry
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_SWORD)){
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {

                    if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        //vessel
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222223) {
                            if (Cooldown.checkCooldown(event.getPlayer())) {
                                player.setCooldown(Material.NETHERITE_SWORD, 20);
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 10, 1);
                                ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();

                                meta.setCustomModelData(1222223);
                                player.getInventory().getItemInMainHand().setItemMeta(meta);
                                getServer().getScheduler().runTaskLater(CombatWeaponryPlus.plugin, new Runnable() {
                                    public void run() {
                                        if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                                            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222223) {
                                                    meta.setCustomModelData(2222223);
                                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                                }
                                            }
                                        }

                                    }
                                }, 10L);
                                Cooldown.setCooldown(event.getPlayer(), 1);
                            } else {
                                return;
                            }
                        }

                        //infvessel
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222224) {
                            if (Cooldown.checkCooldown(event.getPlayer())) {
                                player.setCooldown(Material.NETHERITE_SWORD, 20);
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 10, 1);
                                ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();

                                meta.setCustomModelData(1222224);
                                player.getInventory().getItemInMainHand().setItemMeta(meta);
                                getServer().getScheduler().runTaskLater(CombatWeaponryPlus.plugin, new Runnable() {
                                    public void run() {
                                        if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                                            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222224) {
                                                    meta.setCustomModelData(2222224);
                                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                                }
                                            }
                                        }
                                    }
                                }, 10L);
                                Cooldown.setCooldown(event.getPlayer(), 1);
                            } else {
                                return;
                            }
                        }

                        //cursvessel
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222225) {
                            if (Cooldown.checkCooldown(event.getPlayer())) {
                                player.setCooldown(Material.NETHERITE_SWORD, 20);
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 10, 1);
                                ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();

                                meta.setCustomModelData(1222225);
                                player.getInventory().getItemInMainHand().setItemMeta(meta);
                                getServer().getScheduler().runTaskLater(CombatWeaponryPlus.plugin, new Runnable() {
                                    public void run() {
                                        if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                                            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222225) {
                                                    meta.setCustomModelData(2222225);
                                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                                }
                                            }
                                        }
                                    }
                                }, 10L);
                                Cooldown.setCooldown(event.getPlayer(), 1);

                            } else {
                                return;
                            }
                        }

                        //awak ves
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222226) {
                            if (player.isSneaking()) {
                                return;
                            }
                            if (Cooldown.checkCooldown(event.getPlayer())) {
                                player.setCooldown(Material.NETHERITE_SWORD, 20);
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 10, 1);
                                ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();

                                meta.setCustomModelData(1222226);
                                player.getInventory().getItemInMainHand().setItemMeta(meta);
                                getServer().getScheduler().runTaskLater(CombatWeaponryPlus.plugin, new Runnable() {
                                    public void run() {
                                        if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                                            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222226) {
                                                    meta.setCustomModelData(2222226);
                                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                                }
                                            }
                                        }

                                    }
                                }, 10L);
                                Cooldown.setCooldown(event.getPlayer(), 1);

                            } else {
                                return;
                            }
                        }
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222227) {
                            if (player.isSneaking()) {
                                return;
                            }
                            if (Cooldown.checkCooldown(event.getPlayer())) {
                                player.setCooldown(Material.NETHERITE_SWORD, 20);
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 10, 1);
                                ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();

                                meta.setCustomModelData(1222227);
                                player.getInventory().getItemInMainHand().setItemMeta(meta);
                                getServer().getScheduler().runTaskLater(CombatWeaponryPlus.plugin, new Runnable() {
                                    public void run() {
                                        if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                                            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222227) {
                                                    meta.setCustomModelData(2222227);
                                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                                }
                                            }
                                        }

                                    }
                                }, 10L);
                                Cooldown.setCooldown(event.getPlayer(), 1);

                            } else {
                                return;
                            }
                        }

                        //
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222228) {
                            if (player.isSneaking()) {
                                return;
                            }

                            if (Cooldown.checkCooldown(event.getPlayer())) {

                                player.setCooldown(Material.NETHERITE_SWORD, 20);
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 10, 1);
                                ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();

                                meta.setCustomModelData(1222228);
                                player.getInventory().getItemInMainHand().setItemMeta(meta);
                                getServer().getScheduler().runTaskLater(CombatWeaponryPlus.plugin, new Runnable() {
                                    public void run() {
                                        if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                                            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222228) {
                                                    meta.setCustomModelData(2222228);
                                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                                }
                                            }
                                        }

                                    }
                                }, 10L);
                                Cooldown.setCooldown(event.getPlayer(), 1);

                            } else {
                                return;
                            }
                        }

                        //
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222229) {
                            if (player.isSneaking()) {
                                return;
                            }
                            if (Cooldown.checkCooldown(event.getPlayer())) {
                                player.setCooldown(Material.NETHERITE_SWORD, 20);
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 10, 1);
                                ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();

                                meta.setCustomModelData(1222229);
                                player.getInventory().getItemInMainHand().setItemMeta(meta);
                                getServer().getScheduler().runTaskLater(CombatWeaponryPlus.plugin, new Runnable() {
                                    public void run() {
                                        if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                                            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222229) {
                                                    meta.setCustomModelData(2222229);
                                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                                }
                                            }
                                        }

                                    }
                                }, 10L);
                                Cooldown.setCooldown(event.getPlayer(), 1);
                            }
                        }
                    }
                }
            }
        }
        //

        //EELYTRA
        if (player.getInventory().getChestplate() != null) {
            if (player.getInventory().getChestplate().getItemMeta() != null) {
                if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                    if (player.getInventory().getChestplate().getItemMeta().hasLore()) {
                        if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560001) {

                            if (event.getAction() == Action.RIGHT_CLICK_AIR && player.isGliding()) {
                                //player.sendMessage("qqq");

                                ItemMeta meta = player.getInventory().getChestplate().getItemMeta();
                                meta.setCustomModelData(1560002);
                                player.getInventory().getChestplate().setItemMeta(meta);
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ENTITY_PHANTOM_FLAP, 10, 1);
                                player.setVelocity(player.getLocation().getDirection().multiply(2));
                                getServer().getScheduler().runTaskLater(CombatWeaponryPlus.plugin, new Runnable() {
                                    public void run() {
                                        //player.setVelocity(player.getLocation().getDirection().multiply(0.5));

                                    }
                                }, 10L);
                            }
                        }
                    }
                }
            }
        }

        //CHORUS BLADE
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
        //OBSIDIAN PICKAXE
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
        //REALLY GOOD SWORD
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
