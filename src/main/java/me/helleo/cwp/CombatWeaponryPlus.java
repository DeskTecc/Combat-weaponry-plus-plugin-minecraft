package me.helleo.cwp;
//Message: there's a lot of code and stuff with // before it, thats because those were things that were either there in a previous version, there to be tested, or didnt work and i didnt delete them for some reason
// also sorry if a lot of this stuff is messy, i dont really know what things are considered messy or not
// but it all works
// ctrl + f is very useful for finding things


import me.helleo.cwp.configurations.*;
import me.helleo.cwp.listeners.Commands;
import me.helleo.cwp.listeners.EntityDamage;
import me.helleo.cwp.listeners.PlayerClick;
import me.helleo.cwp.listeners.PlayerEvents;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.*;
import org.bukkit.entity.ArmorStand.LockType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.io.IOException;
import java.util.*;

import static me.helleo.cwp.configurations.ConfigLoader.setLang;


public class CombatWeaponryPlus extends JavaPlugin implements Listener {

    public static String pluginName = "CombatWeaponryPlus";

    private static Plugin plugin;

    private static final ConfigurationsRecipes recipes = new ConfigurationsRecipes();

    public static Plugin getPlugin(){
        return plugin;
    }

    public static ConfigurationsRecipes getRecipes(){
        return recipes;
    }

    @Override
    public void onEnable() {
        plugin = this;
        Cooldown.setupCooldown();

        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new PlayerClick(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamage(), this);
        this.getCommand("cwp").setExecutor(new Commands());
        this.saveDefaultConfig();
        String lang = "en";
        if(!ConfigLoader.getConfig().contains("lang")){
            lang = ConfigLoader.getConfig().getString("lang");
        }
        try {
            setLang(lang);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        recipes.loadRecipes();
    }

    @Override
    public void onDisable() {

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
            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {

                //TEST BOW
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1069691) {//||player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1069691) {
                    Vector vector = player.getLocation().getDirection();
                    //these numbers make it around the same velocity as normal bows i think
                    //arrow.setVelocity(new Vector (vector.getX() * speed * 3.5, vector.getY() * speed * 4, vector.getZ()* speed * 3.5));

                    World world = player.getWorld();
                    arrow.setVelocity(new Vector
                            (vector.getX() * speed * 5,
                                    vector.getY() * speed * 5,
                                    vector.getZ() * speed * 5));

                    Trident trident = player.launchProjectile(Trident.class, arrow.getVelocity());
                    arrow.remove();
                    trident.setPierceLevel(20);
                    trident.setCritical(true);
                    trident.setFireTicks(100);
                    trident.setGravity(false);
                    trident.setPickupStatus(PickupStatus.DISALLOWED);
                    trident.setBounce(false);
                    trident.setCustomName("Bob");
                    trident.setCustomNameVisible(true);
                    trident.setKnockbackStrength(10);
                    world.playSound(player.getLocation(), Sound.ITEM_TRIDENT_THROW, 10, 1);

                    Entity pig = world.spawnEntity(player.getLocation().add(0, 9, 0), EntityType.PIG);
                    pig.setCustomName("Kevin");
                    pig.setCustomNameVisible(true);
                    Entity chicken = world.spawnEntity(player.getLocation().add(0, 9, 0), EntityType.CHICKEN);
                    chicken.setCustomName("Phil");
                    chicken.setCustomNameVisible(true);

                    pig.addPassenger(chicken);
                    trident.addPassenger(pig);

                    return;
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1069691) {// || player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() != 1069691) {

                    // LONG BOW
                    if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("longbow") || player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("longswordbow")) {
                        Vector vector = player.getLocation().getDirection();
                        //these numbers make it around the same velocity as normal bows
                        //arrow.setVelocity(new Vector (vector.getX() * speed * 3.5, vector.getY() * speed * 4, vector.getZ()* speed * 3.5));

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
                    if (!player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("longbow") || !player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("longswordbow")) {
                        // RECURVE BOW
                        if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("recurvebow")) {
                            Vector vector = player.getLocation().getDirection();
                            //these numbers make it around the same velocity as normal bows
                            //arrow.setVelocity(new Vector (vector.getX() * speed * 3.5, vector.getY() * speed * 4, vector.getZ()* speed * 3.5));

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
                        if (!player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("recurvebow")){
                            //CBow
                            if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("compoundbow")) {//||player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 3330003) {
                                Vector vector = player.getLocation().getDirection();
                                //these numbers make it around the same velocity as normal bows
                                //arrow.setVelocity(new Vector (vector.getX() * speed * 3.5, vector.getY() * speed * 4, vector.getZ()* speed * 3.5));

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
                            }
                        }
                    }
                }
            }
        }
    }

    /*
@EventHandler
public void onCraftingLbowevent(PrepareItemCraftEvent event) {
    CraftingInventory inventory = event.getInventory();
    ItemStack[] items = inventory.getMatrix();

    //Crafting table be like: 0, 1, 2
    //                        3, 4, 5
    //                        6, 7, 8


    if (items[0] != null && items[0].getType() == Material.IRON_INGOT && items[0].getAmount() == 1
            && items[1] != null && items[1].getType() == Material.STICK && items[1].getAmount() == 1
            && items[2] != null && items[2].getType() == Material.IRON_INGOT && items[2].getAmount() == 1
            && items[3] != null && items[3].getType() == Material.STICK && items[3].getAmount() == 1
            && items[4] != null && items[4].getType() == Material.BOW && items[4].getAmount() == 1
            && items[5] != null && items[5].getType() == Material.STICK && items[5].getAmount() == 1
            && items[6] != null && items[6].getType() == Material.IRON_INGOT && items[6].getAmount() == 1
            && items[7] != null && items[7].getType() == Material.STICK && items[7].getAmount() == 1
            && items[8] != null && items[8].getType() == Material.IRON_INGOT && items[8].getAmount() == 1) {
    	ItemMeta meta = items[4].getItemMeta();
    	ItemStack item = new ItemStack(Material.BOW);
    	item.setItemMeta(meta);

    	if (items[4].getType() == Material.BOW && items[4].getItemMeta().hasCustomModelData() == false) {
    		ItemMeta meta2 = item.getItemMeta();
    	meta2.setCustomModelData(3330001);
    	meta2.setDisplayName("Longbow");

    	List<String> lore = new ArrayList<String>();
    	lore.add("");
    	lore.add(ChatColor.translateAlternateColorCodes('&', "&6Strong Shot"));
    	lore.add(ChatColor.translateAlternateColorCodes('&', "&7- Slightly increased arrow velocity"));
    	lore.add("");
    	meta2.setLore(lore);
    	AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Speed", -0.01,
				Operation.ADD_NUMBER, EquipmentSlot.HAND);
		meta2.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
    	item.setItemMeta(meta2);
    	}
    	if (this.getConfig().getString("Longbow") == "true") {
			event.getInventory().setResult(item);
	}
    	 if (event.getInventory().getResult() != null) {
     		items[0].setAmount(0);
     		items[1].setAmount(0);
     		items[2].setAmount(0);
     		items[3].setAmount(0);
     		//items[4].setAmount(0);
     		items[5].setAmount(0);
     		items[6].setAmount(0);
     		items[7].setAmount(0);
     		items[8].setAmount(0);
     	}
    }
}
*/

    /*
@EventHandler
public void onCraftingRbowevent(PrepareItemCraftEvent event) {
    CraftingInventory inventory = event.getInventory();
    ItemStack[] items = inventory.getMatrix();

    //Crafting table be like: 0, 1, 2
    //                        3, 4, 5
    //                        6, 7, 8


    if (items[0] != null && items[0].getType() == Material.IRON_BARS && items[0].getAmount() == 1
            && items[1] != null && items[1].getType() == Material.CRIMSON_STEM && items[1].getAmount() == 1
            && items[2] != null && items[2].getType() == Material.IRON_BARS && items[2].getAmount() == 1
            && items[3] != null && items[3].getType() == Material.CRIMSON_STEM && items[3].getAmount() == 1
            && items[4] != null && items[4].getType() == Material.BOW && items[4].getItemMeta().hasCustomModelData() && items[4].getItemMeta().getCustomModelData() == 3330001
            && items[5] != null && items[5].getType() == Material.WARPED_STEM && items[5].getAmount() == 1
            && items[6] != null && items[6].getType() == Material.IRON_BARS && items[6].getAmount() == 1
            && items[7] != null && items[7].getType() == Material.WARPED_STEM && items[7].getAmount() == 1
            && items[8] != null && items[8].getType() == Material.IRON_BARS && items[8].getAmount() == 1) {
    	ItemMeta meta = items[4].getItemMeta();
    	ItemStack item = new ItemStack(Material.BOW);
    	item.setItemMeta(meta);

    	if (items[4].getType() == Material.BOW && items[4].getItemMeta().hasCustomModelData() == true && items[4].getItemMeta().getCustomModelData() == 3330001) {
    		ItemMeta meta2 = item.getItemMeta();
    	meta2.setCustomModelData(3330002);
    	meta2.setDisplayName("Recurve bow");

    	List<String> lore = new ArrayList<String>();
    	lore.add("");
    	lore.add(ChatColor.translateAlternateColorCodes('&', "&6Sharp Shot"));
    	lore.add(ChatColor.translateAlternateColorCodes('&', "&7- Increased arrow velocity"));
    	lore.add("");
    	meta2.setLore(lore);

    	AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Speed", -0.01,
				Operation.ADD_NUMBER, EquipmentSlot.HAND);
		meta2.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
    	item.setItemMeta(meta2);
    	}
    	if (this.getConfig().getString("Recurvebow") == "true") {
			event.getInventory().setResult(item);
	}
    	 if (event.getInventory().getResult() != null) {
     		items[0].setAmount(0);
     		items[1].setAmount(0);
     		items[2].setAmount(0);
     		items[3].setAmount(0);
     		//items[4].setAmount(0);
     		items[5].setAmount(0);
     		items[6].setAmount(0);
     		items[7].setAmount(0);
     		items[8].setAmount(0);
     	}
    }
}
*/

    /*
@EventHandler
public void onCraftingCbowevent(PrepareItemCraftEvent event) {
    CraftingInventory inventory = event.getInventory();
    ItemStack[] items = inventory.getMatrix();

    //Crafting table be like: 0, 1, 2
    //                        3, 4, 5
    //                        6, 7, 8


    if (items[0] != null && items[0].getType() == Material.IRON_BLOCK && items[0].getAmount() == 1
            && items[1] != null && items[1].getType() == Material.NETHERITE_INGOT && items[1].getAmount() == 1
            && items[2] != null && items[2].getType() == Material.IRON_BLOCK && items[2].getAmount() == 1
            && items[3] != null && items[3].getType() == Material.NETHERITE_INGOT && items[3].getAmount() == 1
            && items[4] != null && items[4].getType() == Material.BOW && items[4].getItemMeta().hasCustomModelData() && items[4].getItemMeta().getCustomModelData() == 3330002
            && items[5] != null && items[5].getType() == Material.NETHERITE_INGOT && items[5].getAmount() == 1
            && items[6] != null && items[6].getType() == Material.IRON_BLOCK && items[6].getAmount() == 1
            && items[7] != null && items[7].getType() == Material.NETHERITE_INGOT && items[7].getAmount() == 1
            && items[8] != null && items[8].getType() == Material.IRON_BLOCK && items[8].getAmount() == 1) {
    	ItemMeta meta = items[4].getItemMeta();
    	ItemStack item = new ItemStack(Material.BOW);
    	item.setItemMeta(meta);

    	if (items[4].getType() == Material.BOW && items[4].getItemMeta().hasCustomModelData() == true && items[4].getItemMeta().getCustomModelData() == 3330002) {
    		ItemMeta meta2 = item.getItemMeta();
    	meta2.setCustomModelData(3330003);
    	meta2.setDisplayName("Compound bow");

    	List<String> lore = new ArrayList<String>();
    	lore.add("");
    	lore.add(ChatColor.translateAlternateColorCodes('&', "&6Strike Shot"));
    	lore.add(ChatColor.translateAlternateColorCodes('&', "&7- Greatly increased arrow velocity"));
    	lore.add("");
    	meta2.setLore(lore);

    	AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Speed", -0.01,
				Operation.ADD_NUMBER, EquipmentSlot.HAND);
		meta2.addAttributeModifier(Attribute.MOVEMENT_SPEED, modifier3);
    	item.setItemMeta(meta2);
    	}
    	if (this.getConfig().getString("Compoundbow") == "true") {
			event.getInventory().setResult(item);
	}
    	 if (event.getInventory().getResult() != null) {
     		items[0].setAmount(0);
     		items[1].setAmount(0);
     		items[2].setAmount(0);
     		items[3].setAmount(0);
     		//items[4].setAmount(0);
     		items[5].setAmount(0);
     		items[6].setAmount(0);
     		items[7].setAmount(0);
     		items[8].setAmount(0);
     	}
    }
}
*/

    //unused item
    /*public ShapedRecipe getbonekatRecipe() {

        //bone

        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Cutting Edge"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7- +60% damage to players without a chestplate"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Two Handed"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7- +50% damage if there is no item in offhand"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Critical Hit"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7- 20% chance to deal 50% more damage when two handed"));

        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7When in Main Hand:"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 4 Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 1.8 Attack Speed"));
        assert meta != null;
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier
        AttributeModifier modifier = new AttributeModifier(NamespacedKey.minecraft("generic.attack_speed"), -2.2,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_damage"), 3,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier2);

        meta.setDisplayName("Bone Katana");
        meta.setCustomModelData(4000002);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "bone_katana");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("  M", " M ", "S  ");

        recipe.setIngredient('M', Material.BONE);
        recipe.setIngredient('S', Material.BEDROCK);

        return recipe;
    }*/


    @EventHandler
    public void playerCrossBowShoot(EntityShootBowEvent event) {

        //I'm looking at this whole part over a year since I wrote it
        //oh no
        //what is happening
        //it is a mess pls help

        Entity entity = event.getEntity();
        //Float speed = event.getForce();
        //Entity arrow = event.getProjectile();
        if (entity.getType().equals(EntityType.PLAYER)) {
            Player player = (Player) entity;
            //if (player.getInventory().getItemInOffHand().getType() == Material.BOW || player.getInventory().getItemInOffHand().getType() == Material.CROSSBOW) {
            //	return;
            //}


            //Vector playerDirection = player.getLocation().getDirection();
            //Arrow arrow = (Arrow) event.getProjectile();


            //Float speed = event.getForce();
            //arrow.setVelocity(new Vector
            //		(playerDirection.getX() * speed * 100,
            //		playerDirection.getY() * speed * 100,
            //		playerDirection.getZ()* speed * 100));
            //arrow.setDamage(arrow.getDamage()*0.05);
            //^^^that works to make damage less

            //repeating crossbow
            if (player.getInventory().getItemInMainHand().getItemMeta().hasItemModel()) {
                if (player.getInventory().getChestplate() != null)
                    if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("repeatingcrossbow") && player.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE) {
                        if (!player.getInventory().getChestplate().getItemMeta().hasItemModel()) {

                            if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("repeatingcrossbow") && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                                if (player.getInventory().getChestplate() != null) {
                                    if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                                        if (player.getInventory().getChestplate().getItemMeta().hasItemModel()) {
                                            if (player.getInventory().getChestplate().getItemMeta().getItemModel().getKey().contains("redstone_core")) {
                                                return;
                                            }
                                        }
                                    }
                                }
                                player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
                                getServer().getScheduler().runTaskLater(this, new Runnable() {
                                    public void run() {
                                        Vector playerDirection = player.getLocation().getDirection();
                                        Arrow arrow = player.launchProjectile(Arrow.class, playerDirection);
                                        arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);

                                        float speed = event.getForce();
                                        arrow.setVelocity(new Vector
                                                (playerDirection.getX() * speed * 4.5,
                                                        playerDirection.getY() * speed * 5,
                                                        playerDirection.getZ() * speed * 4.5));
                                        World world = player.getWorld();
                                        world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                                    }
                                }, 3L);

                                getServer().getScheduler().runTaskLater(this, new Runnable() {
                                    public void run() {
                                        Vector playerDirection = player.getLocation().getDirection();
                                        Arrow arrow = player.launchProjectile(Arrow.class, playerDirection);
                                        arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                                        float speed = event.getForce();
                                        arrow.setVelocity(new Vector
                                                (playerDirection.getX() * speed * 4.5,
                                                        playerDirection.getY() * speed * 5,
                                                        playerDirection.getZ() * speed * 4.5));
                                        World world = player.getWorld();
                                        world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                                    }
                                }, 6L);

                                getServer().getScheduler().runTaskLater(this, new Runnable() {
                                    public void run() {
                                        Vector playerDirection = player.getLocation().getDirection();
                                        Arrow arrow = player.launchProjectile(Arrow.class, playerDirection);
                                        arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                                        float speed = event.getForce();
                                        arrow.setVelocity(new Vector
                                                (playerDirection.getX() * speed * 4.5,
                                                        playerDirection.getY() * speed * 5,
                                                        playerDirection.getZ() * speed * 4.5));
                                        World world = player.getWorld();
                                        world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                                    }
                                }, 9L);

                                getServer().getScheduler().runTaskLater(this, new Runnable() {
                                    public void run() {

                                        Vector playerDirection = player.getLocation().getDirection();
                                        Arrow arrow = player.launchProjectile(Arrow.class, playerDirection);
                                        arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                                        float speed = event.getForce();
                                        arrow.setVelocity(new Vector
                                                (playerDirection.getX() * speed * 4.5,
                                                        playerDirection.getY() * speed * 5,
                                                        playerDirection.getZ() * speed * 4.5));
                                        World world = player.getWorld();
                                        world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                                    }
                                }, 12L);
                            }
                            return;
                        }

                        if (player.getInventory().getChestplate().getItemMeta().hasItemModel()) {
                            if (!player.getInventory().getChestplate().getItemMeta().getItemModel().getKey().contains("redstone_core")) {

                                if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("repeatingcrossbow") &&
                                        player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                                    if (player.getInventory().getChestplate() != null) {
                                        if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                                            if (player.getInventory().getChestplate().getItemMeta().hasItemModel()) {
                                                if (player.getInventory().getChestplate().getItemMeta().getItemModel().getKey().contains("redstone_core")) {
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                    player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
                                    getServer().getScheduler().runTaskLater(this, new Runnable() {
                                        public void run() {
                                            Vector playerDirection = player.getLocation().getDirection();
                                            Arrow arrow = player.launchProjectile(Arrow.class, playerDirection);
                                            arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);

                                            float speed = event.getForce();
                                            arrow.setVelocity(new Vector
                                                    (playerDirection.getX() * speed * 4.5,
                                                            playerDirection.getY() * speed * 5,
                                                            playerDirection.getZ() * speed * 4.5));
                                            World world = player.getWorld();
                                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                                        }
                                    }, 3L);

                                    getServer().getScheduler().runTaskLater(this, new Runnable() {
                                        public void run() {
                                            Vector playerDirection = player.getLocation().getDirection();
                                            Arrow arrow = player.launchProjectile(Arrow.class, playerDirection);
                                            arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                                            float speed = event.getForce();
                                            arrow.setVelocity(new Vector
                                                    (playerDirection.getX() * speed * 4.5,
                                                            playerDirection.getY() * speed * 5,
                                                            playerDirection.getZ() * speed * 4.5));
                                            World world = player.getWorld();
                                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                                        }
                                    }, 6L);

                                    getServer().getScheduler().runTaskLater(this, new Runnable() {
                                        public void run() {
                                            Vector playerDirection = player.getLocation().getDirection();
                                            Arrow arrow = player.launchProjectile(Arrow.class, playerDirection);
                                            arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                                            float speed = event.getForce();
                                            arrow.setVelocity(new Vector
                                                    (playerDirection.getX() * speed * 4.5,
                                                            playerDirection.getY() * speed * 5,
                                                            playerDirection.getZ() * speed * 4.5));
                                            World world = player.getWorld();
                                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                                        }
                                    }, 9L);

                                    getServer().getScheduler().runTaskLater(this, new Runnable() {
                                        public void run() {

                                            Vector playerDirection = player.getLocation().getDirection();
                                            Arrow arrow = player.launchProjectile(Arrow.class, playerDirection);
                                            arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                                            float speed = event.getForce();
                                            arrow.setVelocity(new Vector
                                                    (playerDirection.getX() * speed * 4.5,
                                                            playerDirection.getY() * speed * 5,
                                                            playerDirection.getZ() * speed * 4.5));
                                            World world = player.getWorld();
                                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                                        }
                                    }, 12L);
                                }
                                return;
                            }
                        }

                        getServer().getScheduler().runTaskLater(this, new Runnable() {
                            public void run() {
                                Vector playerDirection = player.getLocation().getDirection();
                                Arrow arrow = player.launchProjectile(Arrow.class, playerDirection);
                                arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);

                                float speed = event.getForce();
                                arrow.setVelocity(new Vector
                                        (playerDirection.getX() * speed * 4.5,
                                                playerDirection.getY() * speed * 5,
                                                playerDirection.getZ() * speed * 4.5));
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                            }
                        }, 3L);

                        getServer().getScheduler().runTaskLater(this, new Runnable() {
                            public void run() {
                                Vector playerDirection = player.getLocation().getDirection();
                                Arrow arrow = player.launchProjectile(Arrow.class, playerDirection);
                                arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                                float speed = event.getForce();
                                arrow.setVelocity(new Vector
                                        (playerDirection.getX() * speed * 4.5,
                                                playerDirection.getY() * speed * 5,
                                                playerDirection.getZ() * speed * 4.5));
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                            }
                        }, 6L);

                        getServer().getScheduler().runTaskLater(this, new Runnable() {
                            public void run() {
                                Vector playerDirection = player.getLocation().getDirection();
                                Arrow arrow = player.launchProjectile(Arrow.class, playerDirection);
                                arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                                float speed = event.getForce();
                                arrow.setVelocity(new Vector
                                        (playerDirection.getX() * speed * 4.5,
                                                playerDirection.getY() * speed * 5,
                                                playerDirection.getZ() * speed * 4.5));
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                            }
                        }, 9L);

                        getServer().getScheduler().runTaskLater(this, new Runnable() {
                            public void run() {

                                Vector playerDirection = player.getLocation().getDirection();
                                Arrow arrow = player.launchProjectile(Arrow.class, playerDirection);
                                arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                                float speed = event.getForce();
                                arrow.setVelocity(new Vector
                                        (playerDirection.getX() * speed * 4.5,
                                                playerDirection.getY() * speed * 5,
                                                playerDirection.getZ() * speed * 4.5));
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                            }
                        }, 12L);
                    }
                if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("repeatingcrossbow") && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                    if (player.getInventory().getChestplate() != null) {
                        if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                            if (player.getInventory().getChestplate().getItemMeta().hasItemModel()) {
                                if (player.getInventory().getChestplate().getItemMeta().getItemModel().getKey().contains("redstone_core")) {
                                    return;
                                }
                            }
                        }
                    }
                    player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);
                    getServer().getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            Vector playerDirection = player.getLocation().getDirection();
                            Arrow arrow = player.launchProjectile(Arrow.class, playerDirection);
                            arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);

                            float speed = event.getForce();
                            arrow.setVelocity(new Vector
                                    (playerDirection.getX() * speed * 4.5,
                                            playerDirection.getY() * speed * 5,
                                            playerDirection.getZ() * speed * 4.5));
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                        }
                    }, 3L);

                    getServer().getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            Vector playerDirection = player.getLocation().getDirection();
                            Arrow arrow = player.launchProjectile(Arrow.class, playerDirection);
                            arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                            float speed = event.getForce();
                            arrow.setVelocity(new Vector
                                    (playerDirection.getX() * speed * 4.5,
                                            playerDirection.getY() * speed * 5,
                                            playerDirection.getZ() * speed * 4.5));
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                        }
                    }, 6L);

                    getServer().getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            Vector playerDirection = player.getLocation().getDirection();
                            Arrow arrow = player.launchProjectile(Arrow.class, playerDirection);
                            arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                            float speed = event.getForce();
                            arrow.setVelocity(new Vector
                                    (playerDirection.getX() * speed * 4.5,
                                            playerDirection.getY() * speed * 5,
                                            playerDirection.getZ() * speed * 4.5));
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                        }
                    }, 9L);

                    getServer().getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {

                            Vector playerDirection = player.getLocation().getDirection();
                            Arrow arrow = player.launchProjectile(Arrow.class, playerDirection);
                            arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                            float speed = event.getForce();
                            arrow.setVelocity(new Vector
                                    (playerDirection.getX() * speed * 4.5,
                                            playerDirection.getY() * speed * 5,
                                            playerDirection.getZ() * speed * 4.5));
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                        }
                    }, 12L);
                }
            }

            //burst crossbow
            if (player.getInventory().getItemInMainHand().getItemMeta().hasItemModel()) {
                if (player.getInventory().getChestplate() != null) {

                    if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("burstcrossbow") && player.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE) {
                        Location loc = player.getLocation();

                        if (player.getInventory().getChestplate() != null) {
                            if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                                if (!player.getInventory().getChestplate().getItemMeta().hasItemModel()) {

                                    if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("burstcrossbow") && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                                        if (player.getInventory().getChestplate() != null) {
                                            if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                                                if (player.getInventory().getChestplate().getItemMeta().hasItemModel()) {
                                                    if (player.getInventory().getChestplate().getItemMeta().getItemModel().getKey().contains("redstone_core")) {

                                                        return;
                                                    }
                                                }
                                            }
                                        }
                                        player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);

                                        //up down
                                        double arrowAngle = 80;
                                        double arrowAngle2 = 100; //90 is forward(i think) 80 is 10 above, 100 is 10 below
                                        double totalAngle1 = loc.getPitch() + arrowAngle;
                                        double totalAngle2 = loc.getPitch() + arrowAngle2;
                                        double arrowDirY1 = Math.cos(Math.toRadians(totalAngle1));
                                        double arrowDirY2 = Math.cos(Math.toRadians(totalAngle2));
                                        Vector arrowDir1 = new Vector(loc.getDirection().getX() * 5, arrowDirY1 * 5, loc.getDirection().getZ() * 5);
                                        Vector arrowDir2 = new Vector(loc.getDirection().getX() * 5, arrowDirY2 * 5, loc.getDirection().getZ() * 5);
                                        Arrow arrow1 = player.launchProjectile(Arrow.class, arrowDir1);
                                        arrow1.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                                        Arrow arrow2 = player.launchProjectile(Arrow.class, arrowDir2);
                                        arrow2.setPickupStatus(Arrow.PickupStatus.DISALLOWED);

                                        //sounds
                                        getServer().getScheduler().runTaskLater(this, new Runnable() {
                                            public void run() {
                                                World world = player.getWorld();
                                                world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                                            }
                                        }, 2L);
                                        getServer().getScheduler().runTaskLater(this, new Runnable() {
                                            public void run() {
                                                World world = player.getWorld();
                                                world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                                            }
                                        }, 4L);
                                    }
                                    return;
                                }
                                if (player.getInventory().getChestplate().getItemMeta().hasItemModel()) {
                                    if (!player.getInventory().getChestplate().getItemMeta().getItemModel().getKey().contains("redstone_core")) {

                                        if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("burstcrossbow") &&
                                                player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                                            if (player.getInventory().getChestplate() != null) {
                                                if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                                                    if (player.getInventory().getChestplate().getItemMeta().hasItemModel()) {
                                                        if (player.getInventory().getChestplate().getItemMeta().getItemModel().getKey().contains("redstone_core")) {
                                                            return;
                                                        }
                                                    }
                                                }
                                            }
                                            player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);


                                            //up down
                                            double arrowAngle = 80;
                                            double arrowAngle2 = 100; //90 is forward(i think) 80 is 10 above, 100 is 10 below
                                            double totalAngle1 = loc.getPitch() + arrowAngle;
                                            double totalAngle2 = loc.getPitch() + arrowAngle2;
                                            double arrowDirY1 = Math.cos(Math.toRadians(totalAngle1));
                                            double arrowDirY2 = Math.cos(Math.toRadians(totalAngle2));
                                            Vector arrowDir1 = new Vector(loc.getDirection().getX() * 5, arrowDirY1 * 5, loc.getDirection().getZ() * 5);
                                            Vector arrowDir2 = new Vector(loc.getDirection().getX() * 5, arrowDirY2 * 5, loc.getDirection().getZ() * 5);
                                            Arrow arrow1 = player.launchProjectile(Arrow.class, arrowDir1);
                                            arrow1.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                                            Arrow arrow2 = player.launchProjectile(Arrow.class, arrowDir2);
                                            arrow2.setPickupStatus(Arrow.PickupStatus.DISALLOWED);

                                            //sounds
                                            getServer().getScheduler().runTaskLater(this, new Runnable() {
                                                public void run() {
                                                    World world = player.getWorld();
                                                    world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                                                }
                                            }, 2L);
                                            getServer().getScheduler().runTaskLater(this, new Runnable() {
                                                public void run() {
                                                    World world = player.getWorld();
                                                    world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                                                }
                                            }, 4L);
                                        }
                                        return;
                                    }
                                }
                            }
                        }
                        //up down
                        double arrowAngle = 80;
                        double arrowAngle2 = 100; //90 is forward(i think) 80 is 10 above, 100 is 10 below
                        double totalAngle1 = loc.getPitch() + arrowAngle;
                        double totalAngle2 = loc.getPitch() + arrowAngle2;
                        double arrowDirY1 = Math.cos(Math.toRadians(totalAngle1));
                        double arrowDirY2 = Math.cos(Math.toRadians(totalAngle2));
                        Vector arrowDir1 = new Vector(loc.getDirection().getX() * 5, arrowDirY1 * 5, loc.getDirection().getZ() * 5);
                        Vector arrowDir2 = new Vector(loc.getDirection().getX() * 5, arrowDirY2 * 5, loc.getDirection().getZ() * 5);
                        Arrow arrow1 = player.launchProjectile(Arrow.class, arrowDir1);

                        arrow1.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                        Arrow arrow2 = player.launchProjectile(Arrow.class, arrowDir2);
                        arrow2.setPickupStatus(Arrow.PickupStatus.DISALLOWED);

                        //sounds
                        getServer().getScheduler().runTaskLater(this, new Runnable() {
                            public void run() {
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                            }
                        }, 2L);
                        getServer().getScheduler().runTaskLater(this, new Runnable() {
                            public void run() {
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                            }
                        }, 4L);
                    }
                }

                if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("burstcrossbow") && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                    if (player.getInventory().getChestplate() != null) {
                        if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                            if (player.getInventory().getChestplate().getItemMeta().hasItemModel()) {
                                if (player.getInventory().getChestplate().getItemMeta().getItemModel().getKey().contains("redstone_core")) {

                                    return;
                                }
                            }
                        }
                    }
                    player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);

                    //arrow 1
                    //		Vector playerDirection1 = player.getLocation().getDirection();
                    //		Arrow arrow1 = player.launchProjectile(Arrow.class, playerDirection1);
                    //		arrow1.setPickupStatus(Arrow.PickupStatus.DISALLOWED);

                    //		Float speed1 = event.getForce();
                    //		arrow1.setVelocity(new Vector (playerDirection1.getX() * speed1 * 4.5, playerDirection1.getY() * speed1 * 6, playerDirection1.getZ()* speed1 * 4.5));

                    //arrow 2

                    //		Vector playerDirection2 = player.getLocation().getDirection();
                    //		Arrow arrow2 = player.launchProjectile(Arrow.class, playerDirection2);
                    //		arrow2.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                    ////		Float speed2 = event.getForce();
                    //		arrow2.setVelocity(new Vector (playerDirection2.getX() * speed2 * 4.5, playerDirection2.getY() * speed2 * 4, playerDirection2.getZ()* speed2 * 4.5));

                    Location loc = player.getLocation();

                    //left right
                    //  double arrowAngle = 45;
                    //   double totalAngle = loc.getYaw() + arrowAngle;
                    //  double arrowDirX = Math.cos(Math.toRadians(totalAngle));
                    //  double arrowDirZ = Math.sin(Math.toRadians(totalAngle));
                    // Vector arrowDir = new Vector(arrowDirX, loc.getDirection().getY(), arrowDirZ);

                    //up down
                    double arrowAngle = 80;
                    double arrowAngle2 = 100; //90 is forward(i think) 80 is 10 above, 100 is 10 below
                    double totalAngle1 = loc.getPitch() + arrowAngle;
                    double totalAngle2 = loc.getPitch() + arrowAngle2;
                    double arrowDirY1 = Math.cos(Math.toRadians(totalAngle1));
                    double arrowDirY2 = Math.cos(Math.toRadians(totalAngle2));
                    Vector arrowDir1 = new Vector(loc.getDirection().getX() * 5, arrowDirY1 * 5, loc.getDirection().getZ() * 5);
                    Vector arrowDir2 = new Vector(loc.getDirection().getX() * 5, arrowDirY2 * 5, loc.getDirection().getZ() * 5);
                    Arrow arrow1 = player.launchProjectile(Arrow.class, arrowDir1);
                    arrow1.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                    Arrow arrow2 = player.launchProjectile(Arrow.class, arrowDir2);
                    arrow2.setPickupStatus(Arrow.PickupStatus.DISALLOWED);

                    //sounds
                    getServer().getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                        }
                    }, 2L);
                    getServer().getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                        }
                    }, 4L);
                }
            }

            //redstone bow
            if (player.getInventory().getItemInMainHand().getItemMeta().hasItemModel()) {
                if (player.getInventory().getChestplate() != null)

                    if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("redstonebow") && player.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE) {
                        //Location loc = player.getLocation();

                        if (player.getInventory().getChestplate() != null) {
                            if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                                if (!player.getInventory().getChestplate().getItemMeta().hasItemModel()) {

                                    if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("redstonebow") && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                                        if (player.getInventory().getChestplate() != null) {
                                            if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                                                if (player.getInventory().getChestplate().getItemMeta().hasItemModel()) {
                                                    if (player.getInventory().getChestplate().getItemMeta().getItemModel().getKey().contains("redstone_core")) {

                                                        return;
                                                    }
                                                }
                                            }
                                        }
                                        player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);


                                        //stuff
                                        float speed = event.getForce();
                                        Arrow arrow = (Arrow) event.getProjectile();
                                        Vector vector = player.getLocation().getDirection();

                                        arrow.setVelocity(new Vector
                                                (vector.getX() * speed * 10,
                                                        vector.getY() * speed * 10,
                                                        vector.getZ() * speed * 10));
                                        //	arrow.addPassenger(player);
                                        arrow.setPierceLevel(5);
                                        arrow.setDamage(arrow.getDamage() * 0.2);

                                        return;
                                    }
                                    return;
                                }
                                if (player.getInventory().getChestplate().getItemMeta().hasItemModel()) {
                                    if (!player.getInventory().getChestplate().getItemMeta().getItemModel().getKey().contains("redstone_core")) {

                                        if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("redstonebow") && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                                            if (player.getInventory().getChestplate() != null) {
                                                if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                                                    if (player.getInventory().getChestplate().getItemMeta().hasItemModel()) {
                                                        if (player.getInventory().getChestplate().getItemMeta().getItemModel().getKey().contains("redstone_core")) {

                                                            return;
                                                        }
                                                    }
                                                }
                                            }
                                            player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);


                                            //stuff
                                            float speed = event.getForce();
                                            Arrow arrow = (Arrow) event.getProjectile();
                                            Vector vector = player.getLocation().getDirection();

                                            arrow.setVelocity(new Vector
                                                    (vector.getX() * speed * 10,
                                                            vector.getY() * speed * 10,
                                                            vector.getZ() * speed * 10));
                                            //	arrow.addPassenger(player);
                                            arrow.setPierceLevel(5);
                                            arrow.setDamage(arrow.getDamage() * 0.2);
                                            return;
                                        }
                                        return;
                                    }
                                }
                            }
                        }

                        //stuff
                        float speed = event.getForce();
                        Arrow arrow = (Arrow) event.getProjectile();
                        Vector vector = player.getLocation().getDirection();

                        arrow.setVelocity(new Vector
                                (vector.getX() * speed * 10,
                                        vector.getY() * speed * 10,
                                        vector.getZ() * speed * 10));
                        //	arrow.addPassenger(player);
                        arrow.setPierceLevel(5);
                        arrow.setDamage(arrow.getDamage() * 0.2);
                        return;
                    }

                if (player.getInventory().getItemInMainHand().getItemMeta().getItemModel().getKey().contains("redstonebow") && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                    if (player.getInventory().getChestplate() != null) {
                        if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                            if (player.getInventory().getChestplate().getItemMeta().hasItemModel()) {
                                if (player.getInventory().getChestplate().getItemMeta().getItemModel().getKey().contains("redstone_core")) {
                                    return;
                                }
                            }
                        }
                    }
                    player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);

                    //stuff
                    float speed = event.getForce();
                    Arrow arrow = (Arrow) event.getProjectile();
                    Vector vector = player.getLocation().getDirection();

                    arrow.setVelocity(new Vector
                            (vector.getX() * speed * 10,
                                    vector.getY() * speed * 10,
                                    vector.getZ() * speed * 10));
                    //	arrow.addPassenger(player);
                    arrow.setPierceLevel(5);
                    arrow.setDamage(arrow.getDamage() * 0.2);

                }
            }
        }
    }

    @EventHandler
    void onSmithingTableEventAWAKVES2(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();

        ItemStack templ = inventory.getItem(0); // new
        ItemStack tool = inventory.getItem(1); // was 0
        ItemStack modifier = inventory.getItem(2); // was 1

        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }

        if (tool == null || modifier == null) {
            return;
        }

        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.NETHERITE_SWORD) {
            return;
        }

        if (!tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        if ((tool.getItemMeta().getCustomModelData() != 2222225 &&
                tool.getItemMeta().getCustomModelData() != 1222225) ||
                (modifier.getItemMeta().getCustomModelData() != 2222224 &&
                        modifier.getItemMeta().getCustomModelData() != 1222224)) {
            return;
        }
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselPurple_Name.getValue()));
        meta.setCustomModelData(2222228);
        double dmg = 11;
        double spd = -2.6;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Others_AwakenedVesselPurple_Damage.getValue();
            spd = ConfigurationsDouble.Others_AwakenedVesselPurple_Speed.getValue();
        }
        AttributeModifier modifier1 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_damage"), dmg,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_speed"), spd,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2);

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselPurple_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselPurple_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselPurple_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselPurple_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselPurple_Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselPurple_Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselPurple_Line7.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselPurple_Line8.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselPurple_Line9.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselPurple_Line10.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselPurple_Line11.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselPurple_Line12.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselPurple_Line13.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselPurple_Line14.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselPurple_Line15.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselPurple_Line16.getValue()));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);

        if (ConfigurationsBool.AwakenedVesselPurple.getValue()) {
            event.setResult(item);
        }

    }

    @EventHandler
    void onSmithingTableEventAWAKVES(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();

        ItemStack templ = inventory.getItem(0); // new
        ItemStack tool = inventory.getItem(1); // was 0
        ItemStack modifier = inventory.getItem(2); // was 1

        if (templ == null) {
            return;
        }
        if (templ.getType() != Material.LAPIS_LAZULI) {
            return;
        }

        if (tool == null || modifier == null) {
            return;
        }

        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.NETHERITE_SWORD) {
            return;
        }

        if (!tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }
        if ((tool.getItemMeta().getCustomModelData() != 2222224 &&
                tool.getItemMeta().getCustomModelData() != 1222224) ||
                (modifier.getItemMeta().getCustomModelData() != 2222225 &&
                        modifier.getItemMeta().getCustomModelData() != 1222225)) {
            return;
        }
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselWhite_Name.getValue()));
        meta.setCustomModelData(2222226);
        double dmg = 11;
        double spd = -2.6;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Others_AwakenedVesselWhite_Damage.getValue();
            spd = ConfigurationsDouble.Others_AwakenedVesselWhite_Speed.getValue();
        }
        AttributeModifier modifier1 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_damage"), dmg,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_speed"), spd,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, modifier2);

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselWhite_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselWhite_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselWhite_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselWhite_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselWhite_Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselWhite_Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselWhite_Line7.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselWhite_Line8.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselWhite_Line9.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselWhite_Line10.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselWhite_Line11.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselWhite_Line12.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselWhite_Line13.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselWhite_Line14.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselWhite_Line15.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionAwakenedVesselWhite_Line16.getValue()));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);

        if (ConfigurationsBool.AwakenedVesselWhite.getValue()) {
            event.setResult(item);
        }

    }

    @EventHandler()
    public void onleftClick(PlayerInteractEvent event) {

	/*if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
	  if (event.getHand().equals(EquipmentSlot.HAND)) {
		  Player player = (Player) event.getPlayer();
		  if (player.getInventory().getItemInOffHand() != null) {
			  if (player.getInventory().getItemInOffHand().hasItemMeta()) {
				  if (player.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) {
					  if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010) {
						  if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
							  player.swingOffHand();
							  player.setCooldown(player.getInventory().getItemInOffHand().getType(), 12);

						  }
					  }
				  }
			  }
		  }
	  }
	  }
	if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.NETHER_STAR))
		if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData())
			if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
				Player player = (Player) event.getPlayer();
				if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4920001) {
					if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					World world = player.getWorld();
					ExperienceOrb orb = world.spawn(player.getLocation(), ExperienceOrb.class);
					orb.setExperience(1);
					}
				}
			} */
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            return;
        }
        if (!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
            return;
        }
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_SWORD)) {
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                    Player player = event.getPlayer();

                    if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                        //if (Cooldown.checkCooldown(event.getPlayer())) {
                        if (player.getAttackCooldown() == 1.0) {

                            //awakened vessel and awakened vessel 2
                            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222226 ||
                                    player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222228) {
                                List<Entity> nearbyEntities = player.getNearbyEntities(5, 5, 5);
                                //if (player.getLevel() > ee.size()) {
                                if (player.getLevel() > 1) {

                                    player.setLevel(player.getLevel() - 1);


                                    for (Entity entity : nearbyEntities) {
                                        if (entity instanceof LivingEntity) {
                                            LivingEntity livingen = (LivingEntity) entity;

                                            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222226) {
                                                if (livingen.getType().equals(EntityType.ZOMBIE)) {
                                                    livingen.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 0));
                                                } else {
                                                    if (!(entity instanceof Player)) {
                                                        livingen.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE, 1, 0));

                                                    }
                                                }
                                            }
                                            World world = player.getWorld();
                                            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222228) {
                                                world.spawnEntity(entity.getLocation(), EntityType.EVOKER_FANGS);
                                                //world.strikeLightning(entity.getLocation());
                                            }

                                            world.playSound(entity.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);

                                            world.spawnParticle(Particle.SWEEP_ATTACK, entity.getLocation().getX(), entity.getLocation().getY() + 1, entity.getLocation().getZ(), 1);

                                        }
                                    }

                                }
                            }
                        }
                    }
                    if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        if (player.isSneaking()) {
                            if (Cooldown.checkCooldown(event.getPlayer())) {
                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222226
                                        || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222226) {
                                    ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                                    meta.setCustomModelData(2222227);
                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                    player.sendMessage(ChatColor.RED + "Magic Aura: DISABLED");
                                    Cooldown.setCooldown(event.getPlayer(), 1);
                                }
                                if (Cooldown.checkCooldown(event.getPlayer())) {
                                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222227
                                            || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222227) {
                                        ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                                        meta.setCustomModelData(2222226);
                                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                                        player.sendMessage(ChatColor.GREEN + "Magic Aura: ENABLED");
                                        Cooldown.setCooldown(event.getPlayer(), 1);
                                    }
                                }
                            }
                        }
                    }
                    //another one
                    if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        if (player.isSneaking()) {
                            if (Cooldown.checkCooldown(event.getPlayer())) {
                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222228
                                        || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222228) {
                                    ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                                    meta.setCustomModelData(2222229);
                                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                                    player.sendMessage(ChatColor.RED + "Evocation: DISABLED");
                                    Cooldown.setCooldown(event.getPlayer(), 1);
                                }
                                if (Cooldown.checkCooldown(event.getPlayer())) {
                                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222229
                                            || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222229) {
                                        ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                                        meta.setCustomModelData(2222228);
                                        player.getInventory().getItemInMainHand().setItemMeta(meta);
                                        player.sendMessage(ChatColor.GREEN + "Evocation: ENABLED");
                                        Cooldown.setCooldown(event.getPlayer(), 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void doubleJump(EntityToggleGlideEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        if (player.isDead()) {
            return;
        }
        if (player.getInventory().getChestplate() == null) {
            return;
        }
        if (player.getInventory().getChestplate().getType() != Material.ELYTRA) {
            return;
        }

        if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
            if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1212121) {
                if (!player.isGliding()) {
                    //player.sendMessage("start");
                    //this is when elytra activates
                    if (!(player.hasCooldown(Material.ELYTRA))) {
                        player.setVelocity(player.getLocation().getDirection().multiply(1.1).setY(1));
                        player.setCooldown(Material.ELYTRA, 40);
                    }

                    event.setCancelled(true);

                }
                if (player.isGliding()) {
                    //this is when elytra lands on ground and deactivates
                    //player.sendMessage("end");
                }
            }
        }
    }

    @EventHandler
    public void onFCharmDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (player.getInventory().getItemInOffHand() == null) {
                return;
            }
            if (!player.getInventory().getItemInOffHand().hasItemMeta()) {
                return;
            }
            if (!player.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) {
                return;
            }

            ItemMeta meta = player.getInventory().getItemInOffHand().getItemMeta();
            if (meta.getCustomModelData() == 45) {
                if (event.getEntity() instanceof LivingEntity) {
                    LivingEntity entity = (LivingEntity) event.getEntity();
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 20, 0));
                }
            }

        }
    }

    @EventHandler
    public void flameParticleEffect(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();

            if (player.getInventory().getItemInMainHand().getType() != Material.NETHERITE_SWORD) {
                return;
            }
            if (!(player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData())) {
                return;
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 5000
                    && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 5001
                    && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 5002
                    && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 5003) {
                return;
            }
            if (event.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
                return;
            }
            if (!(event.getEntity() instanceof LivingEntity)) {
                return;
            }
            if (event.getEntity() instanceof Damageable) {
                Damageable entity = (Damageable) event.getEntity();

                if (event.getEntity() instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) event.getEntity();
                    livingEntity.setFireTicks(100);
                    player.getWorld().playSound(entity.getLocation(), Sound.ITEM_FIRECHARGE_USE, 10, 1);
                    //below finds other entities 1 block near, except for the player
                    List<Entity> nearbyEntities = livingEntity.getNearbyEntities(1, 1, 1);
                    for (Entity nearbyEntity : nearbyEntities) {
                        if (nearbyEntity instanceof LivingEntity) {
                            LivingEntity livingen = (LivingEntity) nearbyEntity;
                            if (livingen != player) {

                                livingen.damage(5);
                                player.getWorld().playSound(entity.getLocation(), Sound.ITEM_FIRECHARGE_USE, 10, 1);
                                livingen.setFireTicks(60);
                            }
                        }
                    }
                }


                //how high the armor stand spawns
                float standheight = 3;
                //below for spawn stand in front of player
                Vector direc = player.getLocation().getDirection().multiply(1.8); //was 2
                Location loc = player.getLocation().add(direc);
                Location loca = new Location(player.getWorld(), loc.getX(), loc.getY() + standheight, loc.getZ());
                ArmorStand stand = (ArmorStand) player.getLocation().getWorld().spawnEntity(loca.setDirection(player.getLocation().getDirection()), EntityType.ARMOR_STAND);


                stand.setVisible(false);
                stand.setInvulnerable(true);
                stand.setGravity(false);

                ItemStack item = new ItemStack(Material.POTATO);
                ItemMeta meta = item.getItemMeta();
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5000) {

                    assert meta != null;
                    meta.setCustomModelData(50);
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5001) {

                    assert meta != null;
                    meta.setCustomModelData(51);
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5002) {

                    assert meta != null;
                    meta.setCustomModelData(52);
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5003) {

                    assert meta != null;
                    meta.setCustomModelData(53);
                }
                item.setItemMeta(meta);

                stand.getEquipment().setHelmet(item);
                stand.addEquipmentLock(EquipmentSlot.HEAD, LockType.REMOVING_OR_CHANGING);
                stand.addEquipmentLock(EquipmentSlot.CHEST, LockType.REMOVING_OR_CHANGING);
                stand.addEquipmentLock(EquipmentSlot.LEGS, LockType.REMOVING_OR_CHANGING);
                stand.addEquipmentLock(EquipmentSlot.FEET, LockType.REMOVING_OR_CHANGING);
                stand.addEquipmentLock(EquipmentSlot.HAND, LockType.REMOVING_OR_CHANGING);
                stand.addEquipmentLock(EquipmentSlot.OFF_HAND, LockType.REMOVING_OR_CHANGING);

                Bukkit.getScheduler().runTaskLater(this, () -> {
                    stand.remove();

                }, 4); // Time in ticks (20 ticks = 1 second)

            }
        }
    }
//IDEA:
//theres 2 windows and then cooldown
//first time window if player block then it 'perfect parry' and give like speed and haste or smthn
//2nd window is after 1st and it is like regular minecraf shiled block
//adnd then after, it do coolodonw for like 1 sec
//1st window like 5f and 2nd window like 5f or maybe 10f

    @EventHandler
    public void shieldParry(EntityDamageByEntityEvent event) {

        if (ConfigurationsBool.ShieldParry.getValue()) {
            return;
        }


        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (player.hasCooldown(Material.SHIELD)) {
                //player.sendMessage("missed block");
                return;
            }
            if (player.getInventory().getItemInOffHand().getType() != Material.SHIELD) {
                return;
            }

            if (player.hasMetadata("test")) {
                List<MetadataValue> values = player.getMetadata("test");
                if (values.isEmpty()) {
                    return;
                }
                if (player.getMetadata("test").get(0).value().equals(1)) {
                    event.setCancelled(true);
                    //p.sendMessage("perfect parry");
                    if (player.hasMetadata("test")) {
                        player.removeMetadata("test", this);
                    }
                    player.setNoDamageTicks(10);
                    player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 10, 2);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 40, 0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 0));

                    ItemStack item = player.getInventory().getItemInOffHand();
                    if (player.hasCooldown(Material.SHIELD)) {
                        return;
                    }

                    player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                    Bukkit.getScheduler().runTaskLater(this, () -> {

                        player.getInventory().setItemInOffHand(item);
                        player.setCooldown(Material.SHIELD, 2);

                    }, 1L); // Time in ticks (20 ticks = 1 second)

                    return;
                }
                if (values.isEmpty()) {
                    return;
                }
                if (player.getMetadata("test").get(0).value().equals(2)) {
                    event.setCancelled(true);
                    //p.sendMessage("late parry");
                    if (player.hasMetadata("test")) {
                        player.removeMetadata("test", this);
                    }
                    player.getWorld().playSound(player.getLocation(), Sound.ITEM_SHIELD_BREAK, 10, 2);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 20, 0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20, 0));
                    ItemStack item = player.getInventory().getItemInOffHand();
                    if (player.hasCooldown(Material.SHIELD)) {
                        return;
                    }

                    player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                    Bukkit.getScheduler().runTaskLater(this, () -> {

                        player.getInventory().setItemInOffHand(item);
                        player.setCooldown(Material.SHIELD, 15);

                    }, 1L); // Time in ticks (20 ticks = 1 second)

                    return;
                }
            }
            if (player.isBlocking()) {
                //p.sendMessage("blocked");

                ItemStack item = player.getInventory().getItemInOffHand();
                if (player.hasCooldown(Material.SHIELD)) {
                    return;
                }

                player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                Bukkit.getScheduler().runTaskLater(this, () -> {

                    player.getInventory().setItemInOffHand(item);
                    player.setCooldown(Material.SHIELD, 20);

                }, 1L); // Time in ticks (20 ticks = 1 second)
            }
        }
    }

    @EventHandler
    public void shieldBlock(PlayerInteractEvent event) {

        if (ConfigurationsBool.ShieldParry.getValue()) {
            return;
        }

        //shield
        Player p = event.getPlayer();
        if (p.hasCooldown(Material.SHIELD)) {
            return;
        }
        if ((event.getAction() != Action.RIGHT_CLICK_AIR
                && event.getAction() != Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        if (p.hasMetadata("test")) {
            return;
        }
        //off hand
        if (p.getInventory().getItemInOffHand().getType() == Material.SHIELD) {
            p.setMetadata("test", new FixedMetadataValue(this, 1));
            //p.sendMessage(p.getMetadata("test").get(0).asString());
            Bukkit.getScheduler().runTaskLater(this, () -> {

                if (p.hasMetadata("test")) {
                    p.setMetadata("test", new FixedMetadataValue(this, 2));
                    //p.removeMetadata("test", this);
                }
            }, 4L); // Time in ticks (20 ticks = 1 second)
            Bukkit.getScheduler().runTaskLater(this, () -> {

                if (p.hasMetadata("test")) {
                    p.removeMetadata("test", this);
                }
            }, 10L); // Time in ticks (20 ticks = 1 second) WAS 6


            //5 long feels too long, prolly should make it 4

            //below is code for shield cooldown after right click with shield
            //instead of after blocking with shield
		/*
		ItemStack item = p.getInventory().getItemInOffHand();
		Bukkit.getScheduler().runTaskLater(this, () -> {
			if (p.hasCooldown(Material.SHIELD)) {
				return;
			}

            p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
        	}, 15L); // Time in ticks (20 ticks = 1 second)
		Bukkit.getScheduler().runTaskLater(this, () -> {

           p.getInventory().setItemInOffHand(item);
           p.setCooldown(Material.SHIELD, 15);
        	}, 16L); // Time in ticks (20 ticks = 1 second)
			*/
        }
    }

    //oh my god it finally works
    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        // Check if the entity shooting the bow is a player
        if (event.getEntityType() == EntityType.PLAYER) {
            // Check if the bow being used has the desired CustomModelData
            ItemStack bow = event.getBow();
            assert bow != null;
            if (bow.getItemMeta() != null && bow.getItemMeta().hasCustomModelData() && bow.getItemMeta().getCustomModelData() == 6767676) {

                //remove the arrow
                event.getProjectile().remove();
                Player player = (Player) event.getEntity();

                // Check if the player is holding a crossbow
                ItemStack item = player.getInventory().getItemInMainHand();
                if (item.getType() == Material.CROSSBOW) {
                    // Check if the crossbow has the desired CustomModelData
                    ItemMeta itemMeta = item.getItemMeta();
                    if (itemMeta != null && itemMeta.hasCustomModelData() && itemMeta.getCustomModelData() == 6767676) {
                        // Get the player's eye location
                        Vector eyeLocation = player.getEyeLocation().toVector();

                        // Get the direction the player is looking at
                        Vector direction = player.getEyeLocation().getDirection();

                        // Set the offset distance from the player's eye location
                        double offsetDistance = 0.7; // Adjust the offset distance as needed

                        // Calculate the starting position of the raycast
                        Vector start = eyeLocation.add(direction.multiply(offsetDistance));

                        // Set the maximum distance for the raycast
                        double maxDistance = 50.0; // Adjust the maximum distance as needed

                        // Perform the raycast for entity detection
                        RayTraceResult result = player.getWorld().rayTraceEntities(start.toLocation(player.getWorld()), direction, maxDistance);

                        if (result != null) {
                            Bukkit.getLogger().info("hit");
                            Entity hitEntity = result.getHitEntity();
                            if (hitEntity != null && hitEntity instanceof Arrow) {
                                Bukkit.getLogger().info("hit arrow");
                                // An arrow entity was hit, perform desired action
                                Arrow arrow = (Arrow) hitEntity;

                                // Spawn particles along the raycast path
                                spawnParticlesAlongPath(player, start, 20);

                                // Spawn an explosion at the arrow's location
                                arrow.getWorld().createExplosion(arrow.getLocation(), 2.0f, false, false); // Adjust the explosion parameters as needed

                                // Detect nearby arrows within the explosion's blast radius
                                double blastRadius = 4.0; // Adjust the blast radius as needed
                                List<Entity> nearbyEntities = arrow.getNearbyEntities(blastRadius, blastRadius, blastRadius);
                                for (Entity entity : nearbyEntities) {
                                    if (entity instanceof Arrow) {
                                        Arrow nearbyArrow = (Arrow) entity;

                                        // Apply a velocity to the nearby arrow
                                        Vector explosionDirection = nearbyArrow.getLocation().subtract(arrow.getLocation()).toVector().normalize();
                                        double explosionForce = 4.0; // Adjust the explosion force as needed
                                        Vector velocity = explosionDirection.multiply(explosionForce);
                                        nearbyArrow.setVelocity(velocity);
                                        nearbyArrow.setDamage(nearbyArrow.getDamage() * 3);
                                    }
                                }
                                arrow.remove();
                            }
                        } else {
                            Bukkit.getLogger().info("miss");
                            // No entity was hit, spawn different particles along the raycast path
                            spawnParticlesAlongPath(player, start, 60);

                        }
                    }
                }
            }
        }
    }

    private void spawnParticlesAlongPath(Player player, Vector start, double distance) {
        Vector direction = player.getLocation().getDirection().normalize();
        Vector end = start.clone().add(direction.multiply(distance));

        Vector particleOffset = direction.clone().multiply(0.1);

        for (int i = 0; i < 100; i++) {
            Location particleLocation = start.toLocation(player.getWorld());
            particleLocation.getWorld().spawnParticle(Particle.CRIT, particleLocation, 1);

            // Detect nearby entities at the particle's location
            double detectionRadius = 1.0; // Adjust the detection radius as needed
            Collection<Entity> nearbyEntities = particleLocation.getWorld().getNearbyEntities(particleLocation, detectionRadius, detectionRadius, detectionRadius);
            for (Entity entity : nearbyEntities) {
                if (entity != player) {
                    if (entity instanceof Arrow) {
                        Bukkit.getLogger().info("Arrow nearby");
                        // Perform actions specific to arrows
                        // ...

                        entity.getWorld().createExplosion(entity.getLocation(), 2.0f, false, false); // Adjust the explosion parameters as needed
                    }
                }
            }
            start.add(particleOffset);
        }
        Bukkit.getLogger().info("End Position: " + end);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Check if the player left-clicked
        if (event.getAction().name().startsWith("LEFT")) {

            // Check if the player is holding a crossbow with the desired CustomModelData
            ItemStack heldItem = player.getInventory().getItemInMainHand();
            if (heldItem.getType() == Material.CROSSBOW && heldItem.getItemMeta() instanceof CrossbowMeta) {
                CrossbowMeta crossbowMeta = (CrossbowMeta) heldItem.getItemMeta();
                if (crossbowMeta.hasCustomModelData() && crossbowMeta.getCustomModelData() == 6767676) {
                    shootArrow(player);
                }
            }
        }
    }

    private void shootArrow(Player player) {
        // Create a new arrow entity
        Arrow arrow = player.launchProjectile(Arrow.class);

        // Set the arrow's velocity in the desired direction
        Vector velocity = player.getLocation().getDirection().multiply(0.4); // Adjust the speed as needed
        velocity.setY(0.4); // Adjust the upward velocity as needed
        arrow.setVelocity(velocity);

        // Schedule a task to remove the arrow after a short delay
        new BukkitRunnable() {
            @Override
            public void run() {
                arrow.remove();
            }
        }.runTaskLater(this, 40L); // Adjust the delay (in ticks) as needed
    }

    //EXPLOSIVE STAFF
    /*public ShapedRecipe getExStaffRecipe() {

        //test

        ItemStack item = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Explosion"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7- Right click to create an explosion in the"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  direction you are facing"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7- The created explosion is able to"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  launch nearby entities, including arrows"));
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        assert meta != null;
        meta.setLore(lore);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "Explosive Staff"));
        meta.setCustomModelData(22);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "explosive_staff");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("GTG", " S ", " S ");

        recipe.setIngredient('G', Material.GOLD_INGOT);
        recipe.setIngredient('T', Material.TNT);
        recipe.setIngredient('S', Material.BEDROCK);

        return recipe;
    }*/

    @EventHandler
    public void explosion(EntityShootBowEvent event) {
        // Check if the entity shooting the bow is a player
        if (event.getEntityType() == EntityType.PLAYER) {
            // Check if the bow being used has the desired CustomModelData
            ItemStack bow = event.getBow();
            assert bow != null;
            if (bow.getItemMeta() != null && bow.getItemMeta().hasCustomModelData() && bow.getItemMeta().getCustomModelData() == 22) {

                //remove the arrow
                event.getProjectile().remove();
                Player player = (Player) event.getEntity();

                // Check if the player is holding a crossbow
                ItemStack item = player.getInventory().getItemInMainHand();
                if (item.getType() == Material.CROSSBOW) {
                    Vector direction = player.getLocation().getDirection().multiply(2.5);
                    Location playerLocation = player.getLocation().add(direction);
                    Location location = new Location(player.getWorld(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ());
                    player.getWorld().createExplosion(location, 2.0f, false, false);
                    Collection<Entity> nearent = player.getWorld().getNearbyEntities(location, 3, 3, 3);
                    for (Entity entity : nearent) {
                        if (entity instanceof Arrow) {
                            Arrow nearbyArrow = (Arrow) entity;

                            // Apply a velocity to the nearby arrow
                            Vector explosionDirection = nearbyArrow.getLocation().subtract(location).toVector().normalize();
                            double explosionForce = 4.0; // Adjust the explosion force as needed
                            Vector velocity = explosionDirection.multiply(explosionForce);
                            nearbyArrow.setVelocity(velocity.setY(0));
                            //nearbyArrow.setGravity(false);
                            //nearbyArrow.setVelocity(new Vector(0,0,0));
                            nearbyArrow.setDamage(nearbyArrow.getDamage() * 3);

                            nearbyArrow.getWorld().playSound(location, Sound.BLOCK_ANVIL_LAND, 10, 2);
                            nearbyArrow.getWorld().spawnParticle(Particle.CRIT, location, 10);

                 		/*Bukkit.getScheduler().runTaskLater(this, () -> {
                 			nearbyArrow.setVelocity(velocity.setY(0));

                         	}, 5L);
                         */
                        } else {
                            // Apply a velocity to the nearby entity
                            Vector explosionDirection = entity.getLocation().subtract(location).toVector().normalize();
                            double explosionForce = 3.0; // Adjust the explosion force as needed
                            Vector velocity = explosionDirection.multiply(explosionForce);
                            entity.setVelocity(velocity);
                        }
                    }
                }
            }
        }
    }
}
