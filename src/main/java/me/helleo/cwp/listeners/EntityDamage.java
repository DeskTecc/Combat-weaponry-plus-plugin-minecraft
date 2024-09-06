package me.helleo.cwp.listeners;

import me.helleo.cwp.CombatWeaponryPlus;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;
import java.util.stream.Collectors;

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
            if (itemInHand.hasItemMeta()) {
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

        if (event.getDamager().getType() == EntityType.PLAYER) {
            Player player = (Player) event.getDamager();
            if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                return;
            }
            if (player.getInventory().getItemInMainHand().getItemMeta() != null){
                if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {

                    int itemModelData = player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData();
                    /*int[] bone_models = {4000002, 4000001, 4000003, 4000004, 4000005, 4000006};
                    boolean bone_weapon_validator = Arrays.stream(bone_models).anyMatch(x-> x==itemModelData);*/

                    int[] rapier_models = {1000005,1200005, 1000015,4000005};
                    boolean rapier_weapon_validator = Arrays.stream(rapier_models).anyMatch(x-> x==itemModelData);

                    int[] longsword_models = {1000001, 1200001, 1000011, 4000001};
                    boolean longsword_weapon_validator = Arrays.stream(longsword_models).anyMatch(x-> x==itemModelData);

                    int[] scythe_models = {1000003,1200003,1000013, 4000003};
                    boolean scythe_weapon_validator = Arrays.stream(scythe_models).anyMatch(x-> x==itemModelData);

                    int[] spear_models = {1000004,1200004,1000014,4000004};
                    boolean spear_weapon_validator = Arrays.stream(spear_models).anyMatch(x-> x==itemModelData);

                    int[] katana_models = {1000002,1200002,1000012,4000002};
                    boolean katana_weapon_validator = Arrays.stream(katana_models).anyMatch(x-> x==itemModelData);

                    int[] knife_models = {1000006,1200006,1000016,4000006};
                    boolean knife_weapon_validator = Arrays.stream(knife_models).anyMatch(x-> x==itemModelData);


                    double damage = player.getInventory().getItemInMainHand().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).stream().filter(
                            attributeModifier -> attributeModifier.getKey().equals(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.attack_damage"))).map(
                            AttributeModifier::getAmount).collect(Collectors.toList()).get(0);

                    Bukkit.getConsoleSender().sendMessage("DAMAGE: " + damage); // REMOVE THIS

                    //bone weapon ability test (damage increases when durability gets lower)
                    /*if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                        if (bone_weapon_validator) {

                            double multiplierr = getMultiplierr(player);
                            event.setDamage(damage * multiplierr);
                            String string = String.valueOf(damage * multiplierr);
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
                    }*/

                    // RAPIER
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                        if (rapier_weapon_validator) {

                            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                        }
                    }
                    //LONGSWORD
                    if (player.getInventory().getItemInOffHand().getType() == Material.AIR) {
                        if (longsword_weapon_validator) {

                            //OK IT WORKS
                            //ok i think i fixed it but im not sure, need test
                            //before: if (player.getInventory().getItemInOffHand() == null) {
                            //doesnt work because the is air in offhand and air counts as item, figure out way to detect the air
                            double bonus = damage * 1.3;
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
                        //SCYTHE
                        if (scythe_weapon_validator) {

                            event.setDamage(damage * 1.3);

                        }
                        //SPEAR
                        if (spear_weapon_validator) {

                            event.setDamage(damage * 1.3);

                            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                        }
                        //KATANA
                        if (katana_weapon_validator) {
                            double bonus = damage * 1.3; //CHANGE THAT
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
                    }
                    //if PvP
                    if (event.getEntity() instanceof Player) {

                        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                            //RAPIER
                            if (rapier_weapon_validator) {

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
                                event.setDamage(getPierceDamage(player2,world, damage));
                                return;
                            }
                            //SPEAR
                            if (spear_weapon_validator) {

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

                                event.setDamage(getPierceDamage(player2,world, damage));
                                return;
                            }
                            //KNIFE
                            if (knife_weapon_validator) {
                                World world = player.getWorld();
                                Player player2 = (Player) event.getEntity();

                                if (player2.getInventory().getChestplate() == null || player2.getInventory().getChestplate().getType() == Material.ELYTRA) {
                                    event.setDamage(damage * 2);
                                    world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10, 1);
                                    return;
                                }
                            }
                            //SCYTHE
                            if (scythe_weapon_validator) {
                                World world = player.getWorld();
                                Player player2 = (Player) event.getEntity();

                                if (player2.getInventory().getChestplate() == null || player2.getInventory().getChestplate().getType() == Material.ELYTRA) {
                                    event.setDamage(damage * 1.5);
                                    world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10, 1);
                                }
                            }
                        }
                    }
                }
            }
            if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    //infused vessel
                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222224 ||
                            player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222224) {

                        LivingEntity entity = (LivingEntity) event.getEntity();
                        entity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
                    }

                    //cursed vessel
                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222225 ||
                            player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222225) {
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

                    //infvessel
                    if (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222224) {
                        ItemMeta meta = player2.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(2222224);
                        player2.getInventory().getItemInMainHand().setItemMeta(meta);

                        event.setCancelled(true);
                        world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10, 1);
                    }

                    //cursvessel
                    if (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222225) {
                        ItemMeta meta = player2.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(2222225);
                        player2.getInventory().getItemInMainHand().setItemMeta(meta);

                        event.setCancelled(true);
                        world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10, 1);

                    }

                    //awak ves
                    if (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222226) {
                        ItemMeta meta = player2.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(2222226);
                        player2.getInventory().getItemInMainHand().setItemMeta(meta);
                        //double dmg1 = event.getDamage();
                        event.setCancelled(true);
                        world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10, 1);
                    }

                    if (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222227) {
                        ItemMeta meta = player2.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(2222227);
                        player2.getInventory().getItemInMainHand().setItemMeta(meta);
                        //double dmg1 = event.getDamage();
                        event.setCancelled(true);
                        world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10, 1);
                    }

                    //awakves 2
                    if (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222228) {
                        ItemMeta meta = player2.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(2222228);
                        player2.getInventory().getItemInMainHand().setItemMeta(meta);
                        //double dmg1 = event.getDamage();
                        event.setCancelled(true);
                        world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10, 1);
                    }

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



    }

    /*private static double getMultiplierr(Player player) {
        org.bukkit.inventory.meta.Damageable test = (org.bukkit.inventory.meta.Damageable) player.getInventory().getItemInMainHand().getItemMeta();
        short timesused = (short) test.getDamage();
        short e = 250;
        short dur = (short) (e - timesused);

        double perc = (double) dur / e;
        double multiplier = 1 - perc;
        double q = 1;
        double multiplierr = multiplier + q;
        return multiplierr;
    }*/

    private static double getPierceDamage(Player player, World world, double damage){
        //only one part missing is necessary to apply the critical, if player has a piece of armor
        if (player.getInventory().getHelmet() != null ||
                player.getInventory().getChestplate() != null ||
                player.getInventory().getLeggings() != null ||
                player.getInventory().getBoots() != null
        ) {
            world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10, 1);
            return damage * 1.05;
        }
        return damage;
    }
}
