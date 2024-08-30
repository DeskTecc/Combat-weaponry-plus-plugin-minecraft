package me.helleo.cwp.listeners;

import me.helleo.cwp.CombatWeaponryPlus;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class EntityDamage implements Listener {

    public static Integer getRandomInt(Integer max) {
        Random ran = new Random();
        return ran.nextInt(max);
    }

    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent event) {
        //KNIFE combo thing
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            ItemStack itemInHand = player.getInventory().getItemInMainHand();
            if (itemInHand.hasItemMeta())
                if (itemInHand.getItemMeta().hasCustomModelData()) {
                    if (itemInHand.getItemMeta().getCustomModelData() == 1000006
                            || itemInHand.getItemMeta().getCustomModelData() == 1200006
                            || itemInHand.getItemMeta().getCustomModelData() == 1000016
                            || itemInHand.getItemMeta().getCustomModelData() == 4000006) {
                        if (!player.hasCooldown(Material.NETHERITE_SWORD)
                                || !player.hasCooldown(Material.DIAMOND_SWORD)
                                || !player.hasCooldown(Material.IRON_SWORD)
                                || !player.hasCooldown(Material.GOLDEN_SWORD)
                                || !player.hasCooldown(Material.STONE_SWORD)
                                || !player.hasCooldown(Material.WOODEN_SWORD)) {
                            player.setCooldown(Material.NETHERITE_SWORD, 15);
                            player.setCooldown(Material.DIAMOND_SWORD, 15);
                            player.setCooldown(Material.IRON_SWORD, 15);
                            player.setCooldown(Material.GOLDEN_SWORD, 15);
                            player.setCooldown(Material.STONE_SWORD, 15);
                            player.setCooldown(Material.WOODEN_SWORD, 15);
                        }
                        if (player.hasCooldown(Material.NETHERITE_SWORD)
                                || player.hasCooldown(Material.DIAMOND_SWORD)
                                || player.hasCooldown(Material.IRON_SWORD)
                                || player.hasCooldown(Material.GOLDEN_SWORD)
                                || player.hasCooldown(Material.STONE_SWORD)
                                || player.hasCooldown(Material.WOODEN_SWORD)) {

                            if (player.getCooldown(Material.NETHERITE_SWORD) <= 14
                                    || player.getCooldown(Material.DIAMOND_SWORD) <= 14
                                    || player.getCooldown(Material.IRON_SWORD) <= 14
                                    || player.getCooldown(Material.GOLDEN_SWORD) <= 14
                                    || player.getCooldown(Material.STONE_SWORD) <= 14
                                    || player.getCooldown(Material.WOODEN_SWORD) <= 14) {

                                player.setCooldown(Material.NETHERITE_SWORD, 14);
                                player.setCooldown(Material.DIAMOND_SWORD, 14);
                                player.setCooldown(Material.IRON_SWORD, 14);
                                player.setCooldown(Material.GOLDEN_SWORD, 14);
                                player.setCooldown(Material.STONE_SWORD, 14);
                                player.setCooldown(Material.WOODEN_SWORD, 14);

                                if (player.getAttackCooldown() <= 0.9) {
                                    return;
                                }
                                player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 15, 0));

                            }
                        }

                    }
                }
        }
        //saber dual wield thing
        //unused (made the sabers work a different way) i also dont really remember what this was specifically for
        //if (event.getDamager().getType().equals(EntityType.PLAYER)) {
        //if (player.getInventory().getItemInMainHand().hasItemMeta()) {
        //	if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
        //		if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000010
        //				|| player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200010) {
        //			if (player.getInventory().getItemInOffHand() != null)
        //			if (player.getInventory().getItemInOffHand().hasItemMeta())
        //				if (player.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
        //					if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010
        //							|| player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1200010) {
        //			if (player.getAttackCooldown() == 1.0) {
        //				event.setDamage(event.getDamage()/2);
        //			}
        //			if (player.getAttackCooldown() < 1.0 && player.getAttackCooldown() >= 0.9) {
        //				event.setDamage(event.getDamage()/1.8);
        //			}
        //			if (player.getAttackCooldown() < 0.9 && player.getAttackCooldown() >= 0.8) {
        //				event.setDamage(event.getDamage()/1.6);
        //			}
        //			if (player.getAttackCooldown() < 0.8 && player.getAttackCooldown() >= 0.7) {
        //				event.setDamage(event.getDamage()/1.4);
        //			}
        //			if (player.getAttackCooldown() < 0.7 && player.getAttackCooldown() >= 0.6) {
        //				event.setDamage(event.getDamage()/1.2);
        //			}
        //			if (player.getAttackCooldown() < 0.6 && player.getAttackCooldown() >= 0.5) {
        //				event.setDamage(event.getDamage()/1);
        //			}
        //					}
        //		}
        //	}
        //		}
        //}
        if (event.getEntity().getType().equals(EntityType.PLAYER)) {
            Player damaged = (Player) event.getEntity();
            if (damaged.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                if (damaged.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    if (damaged.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222225 ||
                            damaged.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222225) {
                        event.setDamage(event.getDamage() * 1.5);
                    }
                }
            }
        }
        if (event.getDamager().getType() == EntityType.PLAYER) {
            Player damager = (Player) event.getDamager();
            if (damager.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                if (damager.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    if (damager.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222224 ||
                            damager.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222224) {

                        LivingEntity entity = (LivingEntity) event.getEntity();
                        entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));

                    }
                }
            }
        }
        if (event.getDamager().getType() == EntityType.PLAYER) {
            Player damager = (Player) event.getDamager();
            if (damager.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                if (damager.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    if (damager.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222225 ||
                            damager.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222225) {
                        event.setDamage(event.getDamage() * 1.5);
                    }
                }
            }
        }

        //parry
        World world1 = event.getEntity().getWorld();
        if (event.getEntity().getType() == EntityType.PLAYER) {
            Player player2 = (Player) event.getEntity();
            //vessel
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                if (player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    if (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222223) {
                        ItemMeta meta = player2.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(2222223);
                        player2.getInventory().getItemInMainHand().setItemMeta(meta);

                        event.setCancelled(true);
                        world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10, 1);
                    }
                }
            }
            //infvessel
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                if (player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    if (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222224) {
                        ItemMeta meta = player2.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(2222224);
                        player2.getInventory().getItemInMainHand().setItemMeta(meta);

                        event.setCancelled(true);
                        world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10, 1);
                    }
                }
            }
            //cursvessel
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                if (player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    if (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222225) {
                        ItemMeta meta = player2.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(2222225);
                        player2.getInventory().getItemInMainHand().setItemMeta(meta);

                        event.setCancelled(true);
                        world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10, 1);

                    }
                }
            }
            //awak ves
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                if (player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    if (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222226) {
                        ItemMeta meta = player2.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(2222226);
                        player2.getInventory().getItemInMainHand().setItemMeta(meta);
                        //double dmg1 = event.getDamage();
                        event.setCancelled(true);
                        world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10, 1);


                    }
                }
            }
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                if (player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    if (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222227) {
                        ItemMeta meta = player2.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(2222227);
                        player2.getInventory().getItemInMainHand().setItemMeta(meta);
                        //double dmg1 = event.getDamage();
                        event.setCancelled(true);
                        world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10, 1);
                    }
                }
            }
            //awakves 2
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                if (player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    if (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222228) {
                        ItemMeta meta = player2.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(2222228);
                        player2.getInventory().getItemInMainHand().setItemMeta(meta);
                        //double dmg1 = event.getDamage();
                        event.setCancelled(true);
                        world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10, 1);
                    }
                }
            }
            if (player2.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                if (player2.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    if (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222229) {
                        ItemMeta meta = player2.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(2222229);
                        player2.getInventory().getItemInMainHand().setItemMeta(meta);
                        //double dmg1 = event.getDamage();
                        event.setCancelled(true);
                        world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10, 1);
                    }
                }
            }
        }


        //	}

        if (event.getDamager().getType() == EntityType.PLAYER) {
            Player player = (Player) event.getDamager();
            if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                return;
            }
            if (player.getInventory().getItemInMainHand().getItemMeta() != null)
                if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {

                    //bone weapon ability test (damage increases when durability gets lower)
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000002
                                || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000001
                                || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000003
                                || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000004
                                || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000005
                                || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000006) {
                            //Player playerr = (Player) event.getDamager();
                            double dmg = event.getDamage();
                            double multiplierr = getMultiplierr(player);
                            event.setDamage(dmg * multiplierr);
                            String string = String.valueOf(dmg * multiplierr);
                            player.sendMessage(string);

                            //BELOW: older version of above, newer version doesn't use .getDurability()
                            //i changed it because .getDurability is deprecated, the newer version hasn't been tested yet, but the older version works correctly i think


                            //double dmg = event.getDamage();
                            //short timesused = player.getInventory().getItemInMainHand().getDurability();
                            //short e = 250;
                            //short dur = (short) (e-timesused);

                            //double perc = (double) dur/e;
                            //double multiplier = 1-perc;
                            //	double q = 1;
                            //	double multiplierr = multiplier + q;
                            //	event.setDamage(dmg*multiplierr);
                            //	String string = String.valueOf(dmg*multiplierr);
                            //	player.sendMessage(string);

                        }

                    //rapeir
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000005 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200005 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000015 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000005) {

                            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                        }

                    //spear
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000004 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200004 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000014 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000004) {

                            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                        }
                    //longsword
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000001 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200001 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000011 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000001)
                            if (player.getInventory().getItemInOffHand().getType() == Material.AIR) {
                                //OK IT WORKS
                                //ok i think i fixed it but im not sure, need test
                                //before: if (player.getInventory().getItemInOffHand() == null) {
                                //doesnt work because the is air in offhand and air counts as item, figure out way to detect the air
                                double dmg1 = event.getDamage();
                                double bonus = dmg1 * 1.3;
                                event.setDamage(bonus);
                                //RNG CRIT
                                //int random = getRandomInt(2);
                                ///if (random == 1) {
                                //	double crit = bonus * 1.1;
                                //	event.setDamage(crit);
                                //	World world = (World) player.getWorld();

                                //	world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10, 1);
                                //	event.getEntity().getWorld().spawnParticle(Particle.SWEEP_ATTACK, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY() + 1, event.getEntity().getLocation().getZ(), 5);

                                //}
                            }
                    //scythe
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000003 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200003 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000013 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000003)
                            if (player.getInventory().getItemInOffHand().getType() == Material.AIR) {
                                double dmg1 = event.getDamage();
                                event.setDamage(dmg1 * 1.3);
                            }
                    //spear
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000004 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200004 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000014 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000004)
                            if (player.getInventory().getItemInOffHand().getType() == Material.AIR) {
                                double dmg1 = event.getDamage();
                                event.setDamage(dmg1 * 1.3);
                            }
                    //katana
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000002 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200002 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000012 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000002)
                            if (player.getInventory().getItemInOffHand().getType() == Material.AIR) {
                                double dmg1 = event.getDamage();
                                double bonus = dmg1 * 1.3;
                                event.setDamage(bonus);
                                //RNG CRIT
                                int random = getRandomInt(5);
                                if (random == 1) {
                                    double crit = bonus * 1.1;
                                    event.setDamage(crit);
                                    getServer().getScheduler().runTaskLater(CombatWeaponryPlus.plugin, new Runnable() {
                                        public void run() {
                                            World world = player.getWorld();
                                            world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                                        }
                                    }, 2L); //the 2L is ticks, there are 20 ticks in a second so this is 1/10th of a second delay

                                    getServer().getScheduler().runTaskLater(CombatWeaponryPlus.plugin, new Runnable() {
                                        public void run() {
                                            World world = player.getWorld();
                                            world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                                        }
                                    }, 4L);

                                    getServer().getScheduler().runTaskLater(CombatWeaponryPlus.plugin, new Runnable() {
                                        public void run() {
                                            World world = player.getWorld();
                                            world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                                        }
                                    }, 6L);

                                    getServer().getScheduler().runTaskLater(CombatWeaponryPlus.plugin, new Runnable() {
                                        public void run() {
                                            World world = player.getWorld();
                                            world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                                        }
                                    }, 8L);


                                }


                            }

                    if (event.getEntity() instanceof Player) {

                        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                            //rapier
                            if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000005 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200005 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000015 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000005) {

                                    //event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);

                                    World world = player.getWorld();
                                    Player player2 = (Player) event.getEntity();
                                    if (player2.isBlocking()) {
                                        //double dmg1 = event.getDamage();
                                        //event.setDamage(dmg1 * 3);
                                        if (player.getAttackCooldown() == 1.0) {
                                            player2.setCooldown(Material.SHIELD, 40);
                                        }

                                        world.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BREAK, 10, 1);
                                        return;
                                    }

                                    if (player2.getInventory().getHelmet() != null) {
                                        double dmg1 = event.getDamage();
                                        event.setDamage(dmg1 * 1.05);
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10, 1);
                                        return;
                                    }
                                    if (player2.getInventory().getChestplate() != null) {
                                        double dmg1 = event.getDamage();
                                        event.setDamage(dmg1 * 1.05);
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10, 1);
                                        return;
                                    }
                                    if (player2.getInventory().getLeggings() != null) {
                                        double dmg1 = event.getDamage();
                                        event.setDamage(dmg1 * 1.05);
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10, 1);
                                        return;
                                    }
                                    if (player2.getInventory().getBoots() != null) {
                                        double dmg1 = event.getDamage();
                                        event.setDamage(dmg1 * 1.05);
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10, 1);
                                        return;
                                    }


                                }
                            //SPEAR
                            if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000004 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200004 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000014 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000004) {

                                    //event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);

                                    World world = player.getWorld();
                                    Player player2 = (Player) event.getEntity();
                                    if (player2.isBlocking()) {
                                        //double dmg1 = event.getDamage();
                                        //event.setDamage(dmg1 * 3);
                                        if (player.getAttackCooldown() == 1.0) {
                                            player2.setCooldown(Material.SHIELD, 20);
                                        }
                                        world.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BREAK, 10, 1);
                                        return;
                                    }

                                    if (player2.getInventory().getHelmet() != null) {
                                        double dmg1 = event.getDamage();
                                        event.setDamage(dmg1 * 1.05);
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10, 1);
                                        return;
                                    }
                                    if (player2.getInventory().getChestplate() != null) {
                                        double dmg1 = event.getDamage();
                                        event.setDamage(dmg1 * 1.05);
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10, 1);
                                        return;
                                    }
                                    if (player2.getInventory().getLeggings() != null) {
                                        double dmg1 = event.getDamage();
                                        event.setDamage(dmg1 * 1.05);
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10, 1);
                                        return;
                                    }
                                    if (player2.getInventory().getBoots() != null) {
                                        double dmg1 = event.getDamage();
                                        event.setDamage(dmg1 * 1.05);
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10, 1);
                                        return;
                                    }


                                }
                            //KNIFE
                            if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000006 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200006 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000016 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000006) {
                                    World world = player.getWorld();
                                    Player player2 = (Player) event.getEntity();


                                    if (player2.getInventory().getChestplate() == null || player2.getInventory().getChestplate().getType() == Material.ELYTRA) {
                                        double dmg1 = event.getDamage();
                                        event.setDamage(dmg1 * 2);
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10, 1);
                                        return;
                                    }


                                }
                            //scythe
                            if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000003 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200003 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000013 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000003) {
                                    World world = player.getWorld();
                                    Player player2 = (Player) event.getEntity();


                                    if (player2.getInventory().getChestplate() == null || player2.getInventory().getChestplate().getType() == Material.ELYTRA) {
                                        double dmg1 = event.getDamage();
                                        event.setDamage(dmg1 * 1.5);
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10, 1);
                                    }


                                }


                        }

                    }


                }
        }
    }

    private static double getMultiplierr(Player player) {
        org.bukkit.inventory.meta.Damageable test = (org.bukkit.inventory.meta.Damageable) player.getInventory().getItemInMainHand().getItemMeta();
        short timesused = (short) test.getDamage();
        short e = 250;
        short dur = (short) (e - timesused);

        double perc = (double) dur / e;
        double multiplier = 1 - perc;
        double q = 1;
        double multiplierr = multiplier + q;
        return multiplierr;
    }
}
