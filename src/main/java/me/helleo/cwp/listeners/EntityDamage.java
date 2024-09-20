package me.helleo.cwp.listeners;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.Items;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import me.helleo.cwp.configurations.ConfigurationsString;
import me.helleo.cwp.items.charms.FeatherCharm;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

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
                assert itemInHand.getItemMeta() != null;
                if (itemInHand.getItemMeta().hasCustomModelData()) {
                    if (itemInHand.getItemMeta().getCustomModelData() == 1000006
                            || itemInHand.getItemMeta().getCustomModelData() == 1200006
                            || itemInHand.getItemMeta().getCustomModelData() == 1000016
                            || itemInHand.getItemMeta().getCustomModelData() == 4000006) {
                        if(itemInHand.getType().name().contains("SWORD")) {
                            if (!player.hasCooldown(itemInHand.getType())) {
                                player.setCooldown(itemInHand.getType(), 15);
                            }
                            if (player.hasCooldown(itemInHand.getType())) {
                                if (player.getCooldown(itemInHand.getType()) <= 14) {
                                    player.setCooldown(itemInHand.getType(), 14);
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

                    int[] cleaver_models = {1000021, 1200021, 1000031};
                    boolean cleaver_weapon_validator = Arrays.stream(cleaver_models).anyMatch(x-> x==itemModelData);

                    int[] saber_models = {1000010, 1200010, 1000030};
                    boolean saber_weapon_validator = Arrays.stream(saber_models).anyMatch(x-> x==itemModelData);

                    int[] rapier_models = {1000005,1200005, 1000015};
                    boolean rapier_weapon_validator = Arrays.stream(rapier_models).anyMatch(x-> x==itemModelData);

                    int[] longsword_models = {1000001, 1200001, 1000011};
                    boolean longsword_weapon_validator = Arrays.stream(longsword_models).anyMatch(x-> x==itemModelData);

                    int[] scythe_models = {1000003,1200003,1000013};
                    boolean scythe_weapon_validator = Arrays.stream(scythe_models).anyMatch(x-> x==itemModelData);

                    int[] spear_models = {1000004,1200004,1000014};
                    boolean spear_weapon_validator = Arrays.stream(spear_models).anyMatch(x-> x==itemModelData);

                    int[] katana_models = {1000002,1200002,1000012};
                    boolean katana_weapon_validator = Arrays.stream(katana_models).anyMatch(x-> x==itemModelData);

                    int[] knife_models = {1000006,1200006,1000016};
                    boolean knife_weapon_validator = Arrays.stream(knife_models).anyMatch(x-> x==itemModelData);


                    double damage = player.getInventory().getItemInMainHand().getItemMeta().getAttributeModifiers(
                            Attribute.GENERIC_ATTACK_DAMAGE).stream()
                            .filter(attributeModifier -> attributeModifier.getKey().equals(
                                    new NamespacedKey(CombatWeaponryPlus.plugin,"generic.attack_damage")
                                    ))
                            .map(AttributeModifier::getAmount)
                            .collect(Collectors.toList())
                            .get(0);

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

                    // KNIFE
                    if (knife_weapon_validator) {
                        event.setDamage(damage*1.3);
                    }

                    // CLEAVER
                    if (cleaver_weapon_validator) {
                        event.setDamage(damage*1.3);
                    }

                    // SABER
                    if (saber_weapon_validator) {
                        event.setDamage(damage*1.3);
                    }

                    // RAPIER
                    if (rapier_weapon_validator) {
                        event.setDamage(damage*1.3);
                        event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
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
                            double bonus = damage * 1.3;
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
                        event.setCancelled(true);
                        world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10, 1);
                    }

                    if (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222227) {
                        ItemMeta meta = player2.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(2222227);
                        player2.getInventory().getItemInMainHand().setItemMeta(meta);
                        event.setCancelled(true);
                        world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10, 1);
                    }

                    //awakves 2
                    if (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222228) {
                        ItemMeta meta = player2.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(2222228);
                        player2.getInventory().getItemInMainHand().setItemMeta(meta);
                        event.setCancelled(true);
                        world1.playSound(player2.getLocation(), Sound.ITEM_SHIELD_BLOCK, 10, 1);
                    }

                    if (player2.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222229) {
                        ItemMeta meta = player2.getInventory().getItemInMainHand().getItemMeta();
                        meta.setCustomModelData(2222229);
                        player2.getInventory().getItemInMainHand().setItemMeta(meta);
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

    @EventHandler()
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity().getType().equals(EntityType.PLAYER)) {
            Player player = (Player) event.getEntity();

            if (player.isDead()) {
                return;
            }
            if (player.getInventory().getChestplate() != null) {
                if (player.getInventory().getChestplate().getItemMeta() != null) {
                    if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                        if (player.getInventory().getChestplate().getItemMeta().hasLore()) {
                            if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560001 ||
                                    player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560002) {
                                double dmg = event.getDamage();
                                int num = (int) dmg;
                                String string = String.valueOf(num);
                                player.sendMessage(string);
                                event.setDamage(dmg * 0.5);
                                if (event.getCause() == EntityDamageEvent.DamageCause.FALL && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560002) {
                                    Location loc = player.getLocation();
                                    if (player.isDead()) {
                                        return;
                                    }
                                    //player.sendMessage("www");

                                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 2));

                                    //for (int i = 1; i < 4; i++) {
                                    //AreaEffectCloud aec = (AreaEffectCloud) ((World) loc).spawnEntity(loc, EntityType.AREA_EFFECT_CLOUD);
                                    //PotionEffect pe = new PotionEffect(PotionEffectType.HARM, 1, 0, true, true);
                                    //aec.addCustomEffect(pe, true);
                                    player.setVelocity(new Vector(0, 0.5, 0));
                                    loc.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 2, false, false);

                                    loc.getWorld().spawnEntity(loc, EntityType.AREA_EFFECT_CLOUD);
                                    //	loc.setX(loc.getX() + i);
                                    //}
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void playerKillEntity(EntityDeathEvent event) {
        Entity killed = event.getEntity();

//	if (event.getEntity().getKiller().getType() == EntityType.PLAYER) {
        //	Player player = (Player) event.getEntity().getKiller();
        //	if (player.getInventory().getItemInMainHand().getType().equals(Material.ACACIA_BOAT)) {
        //		if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
        //if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 0) {
        //				ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
        //				meta.setCustomModelData(meta.getCustomModelData()+1);
        //				String string = String.valueOf(meta.getCustomModelData());
        //				List<String> lore = new ArrayList<String>();
        //				lore.add("");
        //				lore.add(ChatColor.translateAlternateColorCodes('&', "&6"+string));
        //				meta.setLore(lore);
        //				AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", 0.1,
        //						Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND);
        //				meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
//					player.getInventory().getItemInMainHand().setItemMeta(meta);
        //}
        //		}
        //	}
        //}

        if (killed.getType() == EntityType.WITHER_SKELETON) {
            World world = killed.getWorld();

            int random = getRandomInt(5);
            if (random == 1) {
                if (ConfigurationsBool.WitherBones.getValue()) {
                    world.dropItemNaturally(killed.getLocation(), Items.witherBone());
                }
            }
            if (random == 2) {
                if (ConfigurationsBool.WitherBones.getValue()) {
                    world.dropItemNaturally(killed.getLocation(), Items.witherBone());
                    world.dropItemNaturally(killed.getLocation(), Items.witherBone());
                }
            }
            int random2 = getRandomInt(100);
            if (random2 == 1) {
                if (ConfigurationsBool.Vessel.getValue()) {
                    world.dropItemNaturally(killed.getLocation(), Items.vessel());
                }
            }
        }
        if (ConfigurationsBool.InfusedVessel.getValue()) {
            if (killed.getType() == EntityType.WITHER) {
                if (event.getEntity().getKiller() != null) {
                    if (event.getEntity().getKiller().getType() == EntityType.PLAYER) {
                        Player player = event.getEntity().getKiller();
                        if (!player.getInventory().getItemInMainHand().hasItemMeta()) {
                            return;
                        } else {
                            if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                return;
                            } else {
                                //INFUSED VESSEL
                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222223
                                        || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222223) {
                                    World world = player.getWorld();
                                    world.spawnParticle(Particle.ENCHANT, player.getLocation().getX(), player.getLocation().getY() + 2, player.getLocation().getZ(), 500);
                                    world.spawnParticle(Particle.CLOUD, player.getLocation(), 100);

                                    ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                                    meta.setCustomModelData(2222224);
                                    meta.setDisplayName(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionInfusedVessel_Name.getValue()));
                                    double attack_damage = 9;
                                    double attack_speed = -2.4;
                                    if (ConfigurationsBool.UseCustomValues.getValue()) {
                                        attack_damage = ConfigurationsDouble.Others_InfusedVessel_Damage.getValue();
                                        attack_speed = ConfigurationsDouble.Others_InfusedVessel_Speed.getValue();
                                    }
                                    AttributeModifier modifierAttackDamage = new AttributeModifier(NamespacedKey.minecraft("generic.attack_damage"), attack_damage,
                                            AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
                                    meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifierAttackDamage);
                                    AttributeModifier modifierAttackSpeed = new AttributeModifier(NamespacedKey.minecraft("generic.attack_speed"), attack_speed,
                                            AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
                                    meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifierAttackSpeed);

                                    List<String> lore = new ArrayList<>();
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionInfusedVessel_Line1.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionInfusedVessel_Line2.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionInfusedVessel_Line3.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionInfusedVessel_Line4.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionInfusedVessel_Line5.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionInfusedVessel_Line6.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionInfusedVessel_Line7.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionInfusedVessel_Line8.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionInfusedVessel_Line9.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionInfusedVessel_Line10.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionInfusedVessel_Line11.getValue()));
                                    meta.setLore(lore);
                                    //important:
                                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                }
                            }
                        }
                    }

                }
            }
        }
        if (ConfigurationsBool.CursedVessel.getValue()) {
            if (killed.getType() == EntityType.ENDER_DRAGON) {
                if (event.getEntity().getKiller() != null) {
                    if (event.getEntity().getKiller().getType() == EntityType.PLAYER) {
                        Player player = event.getEntity().getKiller();
                        if (!player.getInventory().getItemInMainHand().hasItemMeta()) {
                            return;
                        } else {
                            if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                return;
                            } else {
                                //CURSED VESSEL
                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222223
                                        || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222223) {
                                    World world = player.getWorld();
                                    world.spawnParticle(Particle.ENCHANT, player.getLocation().getX(), player.getLocation().getY() + 2, player.getLocation().getZ(), 500);
                                    world.spawnParticle(Particle.CLOUD, player.getLocation(), 100);

                                    ItemMeta meta= player.getInventory().getItemInMainHand().getItemMeta();

                                    meta.setCustomModelData(2222225);
                                    meta.setDisplayName(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCursedVessel_Name.getValue()));
                                    double attack_damage = 9;
                                    double attack_speed = -2.4;
                                    if (ConfigurationsBool.UseCustomValues.getValue()) {
                                        attack_damage = ConfigurationsDouble.Others_CursedVessel_Damage.getValue();
                                        attack_speed = ConfigurationsDouble.Others_CursedVessel_Speed.getValue();
                                    }
                                    AttributeModifier modifierAttackDamage = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.attack_damage"), attack_damage,
                                            AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
                                    meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifierAttackDamage);
                                    AttributeModifier modifierAttackSpeed = new AttributeModifier(new NamespacedKey(CombatWeaponryPlus.plugin,"generic.attack_speed"), attack_speed,
                                            AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
                                    meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifierAttackSpeed);
                                    //AttributeModifier modifier3e = new AttributeModifier(UUID.randomUUID(), "Health", -0.5,
                                    //		Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND);
                                    //meta3.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier3e);

                                    List<String> lore = new ArrayList<>();
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCursedVessel_Line1.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCursedVessel_Line2.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCursedVessel_Line3.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCursedVessel_Line4.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCursedVessel_Line5.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCursedVessel_Line6.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCursedVessel_Line7.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCursedVessel_Line8.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCursedVessel_Line9.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCursedVessel_Line10.getValue()));
                                    lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCursedVessel_Line11.getValue()));
                                    meta.setLore(lore);
                                    //important:
                                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (event.getEntity().getKiller() != null) {
            if (event.getEntity().getKiller().getType() == EntityType.PLAYER) {
                Player player = event.getEntity().getKiller();
                if (player.getInventory().getItemInOffHand().getType().equals(Material.NETHER_STAR))
                    if (player.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData())
                        if (player.getInventory().getItemInOffHand().getItemMeta().hasLore()) {

                            if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 4920001) {

                                World world = player.getWorld();
                                ExperienceOrb orb = world.spawn(player.getLocation(), ExperienceOrb.class);
                                orb.setExperience(orb.getExperience() + getRandomInt(10) + 10);
                                //	orb.setExperience(100);
                                //	player.sendMessage(String.valueOf(orb.getExperience()));
                            }
                        }
            }
        }
    }

    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                //Feather Charm
                if (player.getInventory().getItemInOffHand().isSimilar(FeatherCharm.getCharm())) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
