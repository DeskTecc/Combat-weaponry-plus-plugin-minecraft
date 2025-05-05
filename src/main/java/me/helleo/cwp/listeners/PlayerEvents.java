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
                if(Bukkit.getVersion().contains("1.21.4") || Bukkit.getVersion().contains("1.21.5")){
                    player.setResourcePack("https://download.mc-packs.net/pack/9ae934245c495cbb5de9bdbc0dd65fdf8e908960.zip");
                }
                else if(Bukkit.getVersion().contains("1.21.2") || Bukkit.getVersion().contains("1.21.3")){
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
                .filter(x-> x.getItemMeta().hasItemModel())
                .collect(Collectors.toList());

        //check if player have full custom armor
        if(playerArmorContents.size()==4){
            ItemStack playerHelmet = playerInventory.getHelmet();
            ItemStack playerChestplate = playerInventory.getChestplate();
            ItemStack playerLeggings = playerInventory.getLeggings();
            ItemStack playerBoots = playerInventory.getBoots();

            if (playerHelmet.getItemMeta().getItemModel().getKey().contains("wither_helmet")
                    && playerChestplate.getItemMeta().getItemModel().getKey().contains("wither_chestplate")
                    && playerLeggings.getItemMeta().getItemModel().getKey().contains("wither_leggings")
                    && playerBoots.getItemMeta().getItemModel().getKey().contains("wither_boots")) {

                if (player.getAttackCooldown() == 1) {
                    double damage = event.getFinalDamage();
                    double health = (0.5 * damage) + player.getHealth();
                    if (player.getAttribute(Attribute.MAX_HEALTH).getValue() >= health) {
                        player.setHealth(health);
                    }
                }
            }
        }



    }

    @EventHandler
    public void witherArmorEffect(EntityDamageEvent event) {
        // wither effect
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        PlayerInventory playerInventory = player.getInventory();

        List<ItemStack> playerArmorContents = Arrays.stream(playerInventory.getArmorContents())
                .filter(Objects::nonNull)
                .filter(x-> x.getItemMeta().hasItemModel())
                .collect(Collectors.toList());

        //check if player have full custom armor
        if(playerArmorContents.size()==4){
            ItemStack playerHelmet = playerInventory.getHelmet();
            ItemStack playerChestplate = playerInventory.getChestplate();
            ItemStack playerLeggings = playerInventory.getLeggings();
            ItemStack playerBoots = playerInventory.getBoots();

            if (playerHelmet.getItemMeta().getItemModel().getKey().contains("wither_helmet")
                    && playerChestplate.getItemMeta().getItemModel().getKey().contains("wither_chestplate")
                    && playerLeggings.getItemMeta().getItemModel().getKey().contains("wither_leggings")
                    && playerBoots.getItemMeta().getItemModel().getKey().contains("wither_boots")) {

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

                    if (player.getHealth() < (0.5 * player.getAttribute(Attribute.MAX_HEALTH).getValue())) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 2));
                    }
                }
            }
        }
    }

    //Prismarine Recipes
    @EventHandler
    void onSmithingTableEventSWORD(PrepareSmithingEvent event) {
        SmithingInventory inventory = event.getInventory();

        //CRAFTING ON SMITHING TABLE:
        // LAPIS_LAZULI + NETHERITE_TOOL + PRISMARINE_ALLOY
        if(inventory.getItem(0)!=null &&
                inventory.getItem(1)!=null &&
                inventory.getItem(2)!=null){

        ItemStack template = inventory.getItem(0); // needs to be Lapis Lazuli
        ItemStack tool = inventory.getItem(1); // needs to be a Netherite tool
        ItemStack modifier = inventory.getItem(2); // needs to be a Prismarine alloy

            if(modifier.hasItemMeta()) {
                if (template.getType().equals(Material.LAPIS_LAZULI)
                        && modifier.getItemMeta().getItemModel().getKey().contains("prismarine_alloy")) {
                    if (tool.hasItemMeta()) {
                        //Prismarine Longsword
                        if (tool.isSimilar(NetheriteLongsword.getLongsword())) {
                            ItemStack item = PrismarineLongsword.getItem();

                            if (ConfigurationsBool.Prismarine.getValue()) {
                                event.setResult(item);
                            }
                        }

                        //Prismarine Cleaver
                        if (tool.isSimilar(NetheriteCleaver.getCleaver())) {
                            ItemStack item = PrismarineCleaver.getCleaver();

                            if (ConfigurationsBool.Prismarine.getValue()) {
                                event.setResult(item);
                            }
                        }

                        //Prismarine Saber
                        if (tool.isSimilar(NetheriteSaber.getSaber())) {
                            ItemStack item = PrismarineSaber.getItem();

                            if (ConfigurationsBool.Prismarine.getValue()) {
                                event.setResult(item);
                            }
                        }

                        //Prismarine Knife
                        if (tool.isSimilar(NetheriteKnife.getKnife())) {
                            ItemStack item = PrismarineKnife.getItem();

                            if (ConfigurationsBool.Prismarine.getValue()) {
                                event.setResult(item);
                            }
                        }

                        //Prismarine Katana
                        if (tool.isSimilar(NetheriteKatana.getKatana())) {
                            ItemStack item = PrismarineKatana.getItem();

                            if (ConfigurationsBool.Prismarine.getValue()) {
                                event.setResult(item);
                            }
                        }

                        //Prismarine Spear
                        if (tool.isSimilar(NetheriteSpear.getSpear())) {
                            ItemStack item = PrismarineSpear.getItem();

                            if (ConfigurationsBool.Prismarine.getValue()) {
                                event.setResult(item);
                            }
                        }

                        //Prismarine Rapier
                        if (tool.isSimilar(NetheriteRapier.getRapier())) {
                            ItemStack item = PrismarineRapier.getItem();

                            if (ConfigurationsBool.Prismarine.getValue()) {
                                event.setResult(item);
                            }
                        }

                        //Prismarine Scythe
                        if (tool.isSimilar(NetheriteScythe.getScythe())) {
                            ItemStack item = PrismarineScythe.getItem();

                            if (ConfigurationsBool.Prismarine.getValue()) {
                                event.setResult(item);
                            }
                        }
                    } else {
                        //Prismarine Sword
                        if (tool.getType().equals(Material.NETHERITE_SWORD)) {
                            ItemStack item = PrismarineSword.getSword();

                            if (ConfigurationsBool.Prismarine.getValue()) {
                                event.setResult(item);
                            }
                        }

                        //Prismarine Pickaxe
                        if (tool.getType().equals(Material.NETHERITE_PICKAXE)) {
                            ItemStack item = PrismarinePickaxe.getTool();

                            if (ConfigurationsBool.Prismarine.getValue()) {
                                event.setResult(item);
                            }
                        }

                        //Prismarine Axe
                        if (tool.getType().equals(Material.NETHERITE_AXE)) {
                            ItemStack item = PrismarineAxe.getAxe();

                            if (ConfigurationsBool.Prismarine.getValue()) {
                                event.setResult(item);
                            }
                        }

                        //Prismarine Shovel
                        if (tool.getType().equals(Material.NETHERITE_SHOVEL)) {
                            ItemStack item = PrismarineShovel.getTool();

                            if (ConfigurationsBool.Prismarine.getValue()) {
                                event.setResult(item);
                            }
                        }

                        //Prismarine hoe
                        if (tool.getType().equals(Material.NETHERITE_HOE)) {
                            ItemStack item = PrismarineHoe.getTool();

                            if (ConfigurationsBool.Prismarine.getValue()) {
                                event.setResult(item);
                            }
                        }

                        //Prismarine helmet
                        if (tool.getType().equals(Material.NETHERITE_HELMET)) {
                            ItemStack item = PrismarineHelmet.getArmorPiece();

                            if (ConfigurationsBool.Prismarine.getValue()) {
                                event.setResult(item);
                            }
                        }

                        //Prismarine chestplate
                        if (tool.getType().equals(Material.NETHERITE_CHESTPLATE)) {
                            ItemStack item = PrismarineChestplate.getArmorPiece();

                            if (ConfigurationsBool.Prismarine.getValue()) {
                                event.setResult(item);
                            }
                        }

                        //Prismarine leggings
                        if (tool.getType().equals(Material.NETHERITE_LEGGINGS)) {
                            ItemStack item = PrismarineLeggings.getArmorPiece();

                            if (ConfigurationsBool.Prismarine.getValue()) {
                                event.setResult(item);
                            }
                        }

                        //Prismarine boots
                        if (tool.getType().equals(Material.NETHERITE_BOOTS)) {
                            ItemStack item = PrismarineBoots.getArmorPiece();

                            if (ConfigurationsBool.Prismarine.getValue()) {
                                event.setResult(item);
                            }
                        }
                    }
                }
            }
        }
    }
}
