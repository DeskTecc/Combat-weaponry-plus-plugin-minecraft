package me.helleo.cwp.listeners;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.Cooldown;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import me.helleo.cwp.items.weapons.sabers.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import static org.bukkit.Bukkit.getServer;

public class PlayerCombat implements Listener {


    //DUAL WIELDING
    @EventHandler
    public void onRightClickEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();

        if (event.getHand().equals(EquipmentSlot.HAND)) {
            if (!itemInOffHand.getType().equals(Material.AIR)) {
                if (itemInOffHand.hasItemMeta()) {
                    ItemMeta itemMeta = player.getInventory().getItemInOffHand().getItemMeta();
                    assert itemMeta != null;
                    if (itemMeta.hasItemModel()) {
                        if (itemMeta.getItemModel().getKey().contains("saber")) {
                            //stops dual wielding 2 different weapon type:
                            if (ConfigurationsBool.DualWieldSaberOnly.getValue()) {
                                //test the config thing, not sure if it works
                                //check if exist saber in main hand
                                if (itemInHand.hasItemMeta()) {
                                    assert itemInHand.getItemMeta() != null;
                                    if(itemInHand.getItemMeta().hasItemModel()){
                                        if(!itemInHand.getItemMeta().getItemModel().getKey().contains("saber")){
                                            return;
                                        }
                                    }else{
                                        return;
                                    }
                                }else{
                                    return;
                                }

                            }
                            player.swingOffHand();

                            if (event.getRightClicked() instanceof Damageable) {
                                Damageable e = (Damageable) event.getRightClicked();
                                if (itemMeta.getItemModel().getKey().contains("saber")) {
                                    double attack_damage;
                                    double cooldown;

                                    switch(itemInOffHand.getType()){
                                        case WOODEN_SWORD:
                                            attack_damage = WoodenSaber.getAttackDamage();
                                            cooldown = player.getCooldown(Material.WOODEN_SWORD);
                                            if (player.hasCooldown(Material.WOODEN_SWORD)) {
                                                e.damage(cooldownSaberScaleDamage(cooldown, attack_damage),player);
                                            }
                                            else{
                                                e.damage(attack_damage, player);

                                                World world = player.getWorld();
                                                world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                            }
                                            break;
                                        case STONE_SWORD:
                                            attack_damage = StoneSaber.getAttackDamage();
                                            cooldown = player.getCooldown(Material.STONE_SWORD);
                                            if (player.hasCooldown(Material.STONE_SWORD)) {
                                                e.damage(cooldownSaberScaleDamage(cooldown, attack_damage),player);
                                            }
                                            else {
                                                e.damage(attack_damage, player);

                                                World world = player.getWorld();
                                                world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                            }
                                            break;
                                        case GOLDEN_SWORD:
                                            //if is a Emerald Saber
                                            if(itemInOffHand.isSimilar(EmeraldSaber.getSaber())){
                                                attack_damage = EmeraldSaber.getAttackDamage();
                                                cooldown = player.getCooldown(Material.GOLDEN_SWORD);
                                                if (player.hasCooldown(Material.GOLDEN_SWORD)) {
                                                    e.damage(cooldownSaberScaleDamage(cooldown, attack_damage), player);
                                                }else{
                                                    e.damage(attack_damage, player);

                                                    World world = player.getWorld();
                                                    world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                                }
                                            } else{
                                                attack_damage = GoldenSaber.getAttackDamage();
                                                cooldown = player.getCooldown(Material.GOLDEN_SWORD);
                                                if (player.hasCooldown(Material.GOLDEN_SWORD)) {
                                                    e.damage(cooldownSaberScaleDamage(cooldown, attack_damage), player);
                                                }else{
                                                    e.damage(attack_damage, player);

                                                    World world = player.getWorld();
                                                    world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                                }
                                            }
                                            break;
                                        case IRON_SWORD:
                                            attack_damage = IronSaber.getAttackDamage();
                                            cooldown = player.getCooldown(Material.IRON_SWORD);
                                            if (player.hasCooldown(Material.IRON_SWORD)) {
                                                e.damage(cooldownSaberScaleDamage(cooldown, attack_damage),player);
                                            }
                                            else {
                                                e.damage(attack_damage, player);

                                                World world = player.getWorld();
                                                world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                            }
                                            break;
                                        case DIAMOND_SWORD:
                                            attack_damage = DiamondSaber.getAttackDamage();
                                            cooldown = player.getCooldown(Material.DIAMOND_SWORD);
                                            if (player.hasCooldown(Material.DIAMOND_SWORD)) {
                                                e.damage(cooldownSaberScaleDamage(cooldown, attack_damage),player);
                                            }
                                            else {
                                                e.damage(attack_damage, player);

                                                World world = player.getWorld();
                                                world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                            }
                                            break;
                                        //Prismarine Cleaver is also a Netherite Sword
                                        case NETHERITE_SWORD:
                                            //if is a Prismarine Saber
                                            if(itemInOffHand.isSimilar(PrismarineSaber.getItem())){
                                                attack_damage = PrismarineSaber.getAttackDamage();
                                                cooldown = player.getCooldown(Material.NETHERITE_SWORD);
                                                if (player.hasCooldown(Material.NETHERITE_SWORD)) {
                                                    e.damage(cooldownSaberScaleDamage(cooldown, attack_damage), player);
                                                }else{
                                                    e.damage(attack_damage, player);

                                                    World world = player.getWorld();
                                                    world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                                }
                                            } else{
                                                attack_damage = NetheriteSaber.getAttackDamage();
                                                cooldown = player.getCooldown(Material.NETHERITE_SWORD);
                                                if (player.hasCooldown(Material.NETHERITE_SWORD)) {
                                                    e.damage(cooldownSaberScaleDamage(cooldown, attack_damage), player);
                                                }else{
                                                    e.damage(attack_damage, player);

                                                    World world = player.getWorld();
                                                    world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                                }
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                player.setCooldown(player.getInventory().getItemInOffHand().getType(), 12);
                            }
                        }
                    }
                }
            }
        }
    }

    private double cooldownSaberScaleDamage(double cooldown, double saber_damage){
        //12 is number of ticks left of the cooldown
        //less cooldown = more damage
        double attack_damage;
        if (cooldown <= 12 * 0.2) {
            attack_damage = saber_damage * 0.8;
        }
        else if (cooldown <= 12 * 0.4) {
            attack_damage = saber_damage * 0.6;
        }
        else if (cooldown <= 12 * 0.6) {
            attack_damage = saber_damage * 0.4;
        }
        else if (cooldown <= 12 * 0.8) {
            attack_damage = saber_damage * 0.2;
        }
        else{
            attack_damage = saber_damage * 0.1;
        }
        return attack_damage;
    }

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
                                getServer().getScheduler().runTaskLater(CombatWeaponryPlus.getPlugin(), new Runnable() {
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
                                getServer().getScheduler().runTaskLater(CombatWeaponryPlus.getPlugin(), new Runnable() {
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
                                getServer().getScheduler().runTaskLater(CombatWeaponryPlus.getPlugin(), new Runnable() {
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
                                getServer().getScheduler().runTaskLater(CombatWeaponryPlus.getPlugin(), new Runnable() {
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
                                getServer().getScheduler().runTaskLater(CombatWeaponryPlus.getPlugin(), new Runnable() {
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
                                getServer().getScheduler().runTaskLater(CombatWeaponryPlus.getPlugin(), new Runnable() {
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
                                getServer().getScheduler().runTaskLater(CombatWeaponryPlus.getPlugin(), new Runnable() {
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

        //EELYTRA
        if (player.getInventory().getChestplate() != null) {
            if (player.getInventory().getChestplate().getItemMeta() != null) {
                if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                    if (player.getInventory().getChestplate().getItemMeta().hasLore()) {
                        if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560001) {

                            if (event.getAction() == Action.RIGHT_CLICK_AIR && player.isGliding()) {

                                ItemMeta meta = player.getInventory().getChestplate().getItemMeta();
                                meta.setCustomModelData(1560002);
                                player.getInventory().getChestplate().setItemMeta(meta);
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ENTITY_PHANTOM_FLAP, 10, 1);
                                player.setVelocity(player.getLocation().getDirection().multiply(2));
                            }
                        }
                    }
                }
            }
        }

        //CHORUS BLADE
        if (player.getInventory().getItemInMainHand().getType().equals(Material.IRON_SWORD)){
            if (player.getInventory().getItemInMainHand().getItemMeta().hasItemModel()){
                if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()
                        && player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("chorus_blade")) {
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
            if (player.getInventory().getItemInMainHand().getItemMeta().hasItemModel()) {
                if (player.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                    if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("obsidian_pickaxe")) {
                        //left click
                        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 40, 2));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void playerBowShoot(EntityShootBowEvent event) {
        Entity entity = event.getEntity();
        float speed = event.getForce();
        Arrow arrow = (Arrow) event.getProjectile();
        if (entity.getType().equals(EntityType.PLAYER)) {
            Player player = (Player) entity;
            if (player.getInventory().getItemInOffHand().getType() == Material.BOW || player.getInventory().getItemInOffHand().getType() == Material.CROSSBOW) {
                return;
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().hasItemModel()) {

                // LONG BOW
                if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("longbow") || player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("longswordbow")) {
                    Vector vector = player.getLocation().getDirection();

                    double aspd = 4;
                    double x = 1;
                    if (ConfigurationsBool.UseCustomValues.getValue()) {
                        aspd = ConfigurationsDouble.Bows_LongBow_ArrowSpeed.getValue();
                        x = ConfigurationsDouble.Bows_LongBow_DmgMultiplier.getValue();
                    }
                    arrow.setVelocity(new Vector
                            (vector.getX() * speed * aspd,
                                    vector.getY() * speed * aspd,
                                    vector.getZ() * speed * aspd));
                    arrow.setDamage(arrow.getDamage() * x);
                    return;
                }
                // RECURVE BOW
                if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("recurvebow")) {
                    Vector vector = player.getLocation().getDirection();

                    double aspd = 5;
                    double x = 1;
                    if (ConfigurationsBool.UseCustomValues.getValue()) {
                        aspd = ConfigurationsDouble.Bows_RecurveBow_ArrowSpeed.getValue();
                        x = ConfigurationsDouble.Bows_RecurveBow_DmgMultiplier.getValue();
                    }
                    arrow.setVelocity(new Vector
                            (vector.getX() * speed * aspd,
                                    vector.getY() * speed * aspd,
                                    vector.getZ() * speed * aspd));
                    arrow.setDamage(arrow.getDamage() * x);
                    return;
                }
                //CompoundBow
                if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("compoundbow")) {
                    Vector vector = player.getLocation().getDirection();

                    double aspd = 6;
                    double x = 1;
                    if (ConfigurationsBool.UseCustomValues.getValue()) {
                        aspd = ConfigurationsDouble.Bows_CompoundBow_ArrowSpeed.getValue();
                        x = ConfigurationsDouble.Bows_CompoundBow_DmgMultiplier.getValue();
                    }
                    arrow.setVelocity(new Vector
                            (vector.getX() * speed * aspd,
                                    vector.getY() * speed * aspd,
                                    vector.getZ() * speed * aspd));
                    arrow.setDamage(arrow.getDamage() * x);
                    return;
                }
            }
        }
    }
}
