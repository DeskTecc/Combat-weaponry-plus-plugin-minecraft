package me.helleo.cwp.listeners;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsString;
import me.helleo.cwp.items.armors.PrismarineBoots;
import me.helleo.cwp.items.armors.PrismarineChestplate;
import me.helleo.cwp.items.armors.PrismarineHelmet;
import me.helleo.cwp.items.armors.PrismarineLeggings;
import me.helleo.cwp.items.tools.PrismarineAxe;
import me.helleo.cwp.items.tools.PrismarineHoe;
import me.helleo.cwp.items.tools.PrismarinePickaxe;
import me.helleo.cwp.items.tools.PrismarineShovel;
import me.helleo.cwp.items.weapons.cleavers.NetheriteCleaver;
import me.helleo.cwp.items.weapons.cleavers.PrismarineCleaver;
import me.helleo.cwp.items.weapons.katanas.NetheriteKatana;
import me.helleo.cwp.items.weapons.katanas.PrismarineKatana;
import me.helleo.cwp.items.weapons.knives.NetheriteKnife;
import me.helleo.cwp.items.weapons.knives.PrismarineKnife;
import me.helleo.cwp.items.weapons.longswords.NetheriteLongsword;
import me.helleo.cwp.items.weapons.longswords.PrismarineLongsword;
import me.helleo.cwp.items.weapons.misc.PrismarineSword;
import me.helleo.cwp.items.weapons.rapiers.NetheriteRapier;
import me.helleo.cwp.items.weapons.rapiers.PrismarineRapier;
import me.helleo.cwp.items.weapons.sabers.NetheriteSaber;
import me.helleo.cwp.items.weapons.sabers.PrismarineSaber;
import me.helleo.cwp.items.weapons.scythes.NetheriteScythe;
import me.helleo.cwp.items.weapons.scythes.PrismarineScythe;
import me.helleo.cwp.items.weapons.spears.NetheriteSpear;
import me.helleo.cwp.items.weapons.spears.PrismarineSpear;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.SmithingInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static me.helleo.cwp.CombatWeaponryPlus.getPlugin;
import static org.bukkit.Bukkit.getServer;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(ConfigurationsBool.ResourcePack.getValue()){
            if (ConfigurationsBool.CustomResourcePack.getValue()) {
                player.setResourcePack(ConfigurationsString.PackLink.getValue());
            }else{
                if(Bukkit.getVersion().contains("1.21.2") || Bukkit.getVersion().contains("1.21.3")){
                    player.setResourcePack("https://download.mc-packs.net/pack/c458ca435b4bc36ce1e0094fb8c4c07d60c2ba85.zip");
                }else if (Bukkit.getVersion().contains("1.21") || Bukkit.getVersion().contains("1.21.1")){
                    player.setResourcePack("https://download.mc-packs.net/pack/74b6ef9eb3a726b6ce7469c4d316457a2c5c4f1d.zip");
                }else{
                    player.setResourcePack("https://www.dropbox.com/scl/fi/dhgubahgx3z0phg4wnbw3/cwp-texture-pack-1.5.7.zip?rlkey=cxtqp9575bk28qr90vyxb2tdv&dl=1");
                }
            }
        }


        player.discoverRecipes(CombatWeaponryPlus.getRecipes().getKeys());
    }

    // Event modified by TinkyWinky
    @EventHandler
    public void toggleGlideEvent(EntityToggleGlideEvent event) {
        Player player = (Player) event.getEntity();

        // Ensure the player has a chestplate equipped
        if (player.getInventory().getChestplate() == null) {
            return; // Exit the method to prevent NullPointerException
        }

        if (player.getInventory().getChestplate().getType() == Material.ELYTRA) {
            ItemMeta meta = player.getInventory().getChestplate().getItemMeta();

            if (meta != null && meta.hasCustomModelData()) {
                int modelData = meta.getCustomModelData();
                if (modelData == 1560001 || modelData == 1560002) {

                    if (player.isGliding()) {

                        if (!player.isDead()) {
                            getServer().getScheduler().runTaskLater(getPlugin(), () -> {
                                if (player.getInventory().getChestplate() != null) {
                                    ItemMeta updatedMeta = player.getInventory().getChestplate().getItemMeta();
                                    if (updatedMeta != null) {
                                        updatedMeta.setCustomModelData(1560001);
                                        player.getInventory().getChestplate().setItemMeta(updatedMeta);
                                    }
                                }
                            }, 10L);
                        }
                    } else {

                        player.setVelocity(new Vector(0, 1, 0));

                        getServer().getScheduler().runTaskLater(getPlugin(), () -> {

                        }, 5L);
                    }
                }
            }
        }
    }

    //event of EElytra
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
                    if (!(player.hasCooldown(Material.ELYTRA))) {
                        player.setVelocity(player.getLocation().getDirection().multiply(1.1).setY(1));
                        player.setCooldown(Material.ELYTRA, 40);
                    }

                    event.setCancelled(true);
                }
            }
        }
    }

    //Wither Armor effects:

    @EventHandler
    public void witherArmorHealing(EntityDamageByEntityEvent event) {
        //healing
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getDamager();
        PlayerInventory playerInventory = player.getInventory();

        List<ItemStack> playerArmorContents = Arrays.stream(playerInventory.getArmorContents())
                .filter(Objects::nonNull)
                .filter(x-> x.getItemMeta().hasCustomModelData())
                .collect(Collectors.toList());

        //check if player have full custom armor
        if(playerArmorContents.size()==4) {
            ItemStack playerHelmet = playerInventory.getHelmet();
            ItemStack playerChestplate = playerInventory.getChestplate();
            ItemStack playerLeggings = playerInventory.getLeggings();
            ItemStack playerBoots = playerInventory.getBoots();

            if (playerHelmet.getItemMeta().getCustomModelData()==5553331
                    && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 5553332
                    && player.getInventory().getLeggings().getItemMeta().getCustomModelData() == 5553333
                    && player.getInventory().getBoots().getItemMeta().getCustomModelData() == 5553334){

            }
            if (player.getAttackCooldown() == 1) {
                double damage = event.getFinalDamage();
                double health = (0.5 * damage) + player.getHealth();
                if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() >= health) {
                    player.setHealth(health);
                }
            }
        }
    }

    @EventHandler
    public void witherArmorBonusThingTwo(EntityDamageEvent event) {
        // wither effect
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        if (player.getInventory().getHelmet() == null) {
            return;
        }
        if (player.getInventory().getChestplate() == null) {
            return;
        }
        if (player.getInventory().getLeggings() == null) {
            return;
        }
        if (player.getInventory().getBoots() == null) {
            return;
        }
        if (!(player.getInventory().getHelmet().getItemMeta().hasCustomModelData())) {
            return;
        }
        if (!(player.getInventory().getChestplate().getItemMeta().hasCustomModelData())) {
            return;
        }
        if (!(player.getInventory().getLeggings().getItemMeta().hasCustomModelData())) {
            return;
        }
        if (!(player.getInventory().getBoots().getItemMeta().hasCustomModelData())) {
            return;
        }

        if (player.getInventory().getHelmet().getItemMeta().getCustomModelData() == 5553331
                && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 5553332
                && player.getInventory().getLeggings().getItemMeta().getCustomModelData() == 5553333
                && player.getInventory().getBoots().getItemMeta().getCustomModelData() == 5553334) {

            World world = player.getWorld();
            if (!(event.getCause().equals(EntityDamageEvent.DamageCause.WITHER))) {
                if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)
                        || event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)
                        || event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK)
                        || event.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
                    if (player.isBlocking()) {
                        return;
                    }
                }
                world.playSound(player.getLocation(), Sound.ENTITY_WITHER_SKELETON_HURT, 4, 1);

                if (player.getHealth() < (0.5 * player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 2));
                }
            }
        }
    }

    //Prismarine Recipes
    @EventHandler
    void onSmithingTableEventSWORD(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();

        //CRAFTING ON SMITHING TABLE:
        // LAPIS_LAZULI + NETHERITE_SWORD + PRISMARINE_SHARD

        ItemStack template = inventory.getItem(0); // needs to be Lapis Lazuli
        ItemStack tool = inventory.getItem(1); // needs to be a Netherite Sword
        ItemStack modifier = inventory.getItem(2); // needs to be a Prismarine Shard
        if(template!=null && tool!=null && modifier!=null){

            if(tool.hasItemMeta()){
                //Prismarine Longsword
                if(template.getType().equals(Material.LAPIS_LAZULI) &&
                        tool.isSimilar(NetheriteLongsword.getLongsword()) &&
                        modifier.getType().equals(Material.PRISMARINE_SHARD)
                ){
                    ItemStack item = PrismarineLongsword.getItem();

                    if (ConfigurationsBool.Prismarine.getValue()) {
                        event.setResult(item);
                    }
                }

                //Prismarine Cleaver
                if(template.getType().equals(Material.LAPIS_LAZULI) &&
                        tool.isSimilar(NetheriteCleaver.getCleaver()) &&
                        modifier.getType().equals(Material.PRISMARINE_SHARD)
                ){
                    ItemStack item = PrismarineCleaver.getCleaver();

                    if (ConfigurationsBool.Prismarine.getValue()) {
                        event.setResult(item);
                    }
                }

                //Prismarine Saber
                if(template.getType().equals(Material.LAPIS_LAZULI) &&
                        tool.isSimilar(NetheriteSaber.getSaber()) &&
                        modifier.getType().equals(Material.PRISMARINE_SHARD)
                ){
                    ItemStack item = PrismarineSaber.getItem();

                    if (ConfigurationsBool.Prismarine.getValue()) {
                        event.setResult(item);
                    }
                }

                //Prismarine Knife
                if(template.getType().equals(Material.LAPIS_LAZULI) &&
                        tool.isSimilar(NetheriteKnife.getKnife()) &&
                        modifier.getType().equals(Material.PRISMARINE_SHARD)
                ){
                    ItemStack item = PrismarineKnife.getItem();

                    if (ConfigurationsBool.Prismarine.getValue()) {
                        event.setResult(item);
                    }
                }

                //Prismarine Katana
                if(template.getType().equals(Material.LAPIS_LAZULI) &&
                        tool.isSimilar(NetheriteKatana.getKatana()) &&
                        modifier.getType().equals(Material.PRISMARINE_SHARD)
                ){
                    ItemStack item = PrismarineKatana.getItem();

                    if (ConfigurationsBool.Prismarine.getValue()) {
                        event.setResult(item);
                    }
                }

                //Prismarine Spear
                if(template.getType().equals(Material.LAPIS_LAZULI) &&
                        tool.isSimilar(NetheriteSpear.getSpear()) &&
                        modifier.getType().equals(Material.PRISMARINE_SHARD)
                ){
                    ItemStack item = PrismarineSpear.getItem();

                    if (ConfigurationsBool.Prismarine.getValue()) {
                        event.setResult(item);
                    }
                }

                //Prismarine Rapier
                if(template.getType().equals(Material.LAPIS_LAZULI) &&
                        tool.isSimilar(NetheriteRapier.getRapier()) &&
                        modifier.getType().equals(Material.PRISMARINE_SHARD)
                ){
                    ItemStack item = PrismarineRapier.getItem();

                    if (ConfigurationsBool.Prismarine.getValue()) {
                        event.setResult(item);
                    }
                }

                //Prismarine Scythe
                if(template.getType().equals(Material.LAPIS_LAZULI) &&
                        tool.isSimilar(NetheriteScythe.getScythe()) &&
                        modifier.getType().equals(Material.PRISMARINE_SHARD)
                ){
                    ItemStack item = PrismarineScythe.getItem();

                    if (ConfigurationsBool.Prismarine.getValue()) {
                        event.setResult(item);
                    }
                }
            }else{
                //Prismarine Sword
                if(template.getType().equals(Material.LAPIS_LAZULI) &&
                        tool.getType().equals(Material.NETHERITE_SWORD) &&
                        modifier.getType().equals(Material.PRISMARINE_SHARD)
                ){
                    ItemStack item = PrismarineSword.getSword();

                    if (ConfigurationsBool.Prismarine.getValue()) {
                        event.setResult(item);
                    }
                }

                //Prismarine Pickaxe
                if(template.getType().equals(Material.LAPIS_LAZULI) &&
                        tool.isSimilar(new ItemStack(Material.NETHERITE_PICKAXE)) &&
                        modifier.getType().equals(Material.PRISMARINE_SHARD)
                ){
                    ItemStack item = PrismarinePickaxe.getTool();

                    if (ConfigurationsBool.Prismarine.getValue()) {
                        event.setResult(item);
                    }
                }

                //Prismarine Axe
                if(template.getType().equals(Material.LAPIS_LAZULI) &&
                        tool.isSimilar(new ItemStack(Material.NETHERITE_AXE)) &&
                        modifier.getType().equals(Material.PRISMARINE_SHARD)
                ){
                    ItemStack item = PrismarineAxe.getAxe();

                    if (ConfigurationsBool.Prismarine.getValue()) {
                        event.setResult(item);
                    }
                }

                //Prismarine Shovel
                if(template.getType().equals(Material.LAPIS_LAZULI) &&
                        tool.isSimilar(new ItemStack(Material.NETHERITE_SHOVEL)) &&
                        modifier.getType().equals(Material.PRISMARINE_SHARD)
                ){
                    ItemStack item = PrismarineShovel.getTool();

                    if (ConfigurationsBool.Prismarine.getValue()) {
                        event.setResult(item);
                    }
                }

                //Prismarine hoe
                if(template.getType().equals(Material.LAPIS_LAZULI) &&
                        tool.isSimilar(new ItemStack(Material.NETHERITE_HOE)) &&
                        modifier.getType().equals(Material.PRISMARINE_SHARD)
                ){
                    ItemStack item = PrismarineHoe.getTool();

                    if (ConfigurationsBool.Prismarine.getValue()) {
                        event.setResult(item);
                    }
                }

                //Prismarine helmet
                if(template.getType().equals(Material.LAPIS_LAZULI) &&
                        tool.isSimilar(new ItemStack(Material.NETHERITE_HELMET)) &&
                        modifier.getType().equals(Material.PRISMARINE_SHARD)
                ){
                    ItemStack item = PrismarineHelmet.getArmorPiece();

                    if (ConfigurationsBool.Prismarine.getValue()) {
                        event.setResult(item);
                    }
                }

                //Prismarine chestplate
                if(template.getType().equals(Material.LAPIS_LAZULI) &&
                        tool.isSimilar(new ItemStack(Material.NETHERITE_CHESTPLATE)) &&
                        modifier.getType().equals(Material.PRISMARINE_SHARD)
                ){
                    ItemStack item = PrismarineChestplate.getArmorPiece();

                    if (ConfigurationsBool.Prismarine.getValue()) {
                        event.setResult(item);
                    }
                }

                //Prismarine leggings
                if(template.getType().equals(Material.LAPIS_LAZULI) &&
                        tool.isSimilar(new ItemStack(Material.NETHERITE_LEGGINGS)) &&
                        modifier.getType().equals(Material.PRISMARINE_SHARD)
                ){
                    ItemStack item = PrismarineLeggings.getArmorPiece();

                    if (ConfigurationsBool.Prismarine.getValue()) {
                        event.setResult(item);
                    }
                }

                //Prismarine boots
                if(template.getType().equals(Material.LAPIS_LAZULI) &&
                        tool.isSimilar(new ItemStack(Material.NETHERITE_BOOTS)) &&
                        modifier.getType().equals(Material.PRISMARINE_SHARD)
                ){
                    ItemStack item = PrismarineBoots.getArmorPiece();

                    if (ConfigurationsBool.Prismarine.getValue()) {
                        event.setResult(item);
                    }
                }
            }
        }
    }
}
