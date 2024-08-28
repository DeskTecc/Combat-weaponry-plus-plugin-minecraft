package me.helleo.cwp;
//Message: there's a lot of code and stuff with // before it, thats because those were things that were either there in a previous version, there to be tested, or didnt work and i didnt delete them for some reason
// also sorry if a lot of this stuff is messy, i dont really know what things are considered messy or not
// but it all works
// ctrl + f is very useful for finding things


import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.items.armors.*;
import me.helleo.cwp.items.charms.*;
import me.helleo.cwp.items.tools.*;
import me.helleo.cwp.items.weapons.bows.HeavySwordBow;
import me.helleo.cwp.items.weapons.bows.SwordBow;
import me.helleo.cwp.items.weapons.knives.*;
import me.helleo.cwp.items.weapons.longswords.*;
import me.helleo.cwp.items.weapons.misc.ChorusBlade;
import me.helleo.cwp.items.weapons.rapiers.*;
import me.helleo.cwp.items.weapons.scythes.*;
import me.helleo.cwp.items.weapons.spears.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.*;
import org.bukkit.entity.ArmorStand.LockType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
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

import java.util.*;


public class CombatWeaponryPlus extends JavaPlugin implements Listener {

    public static String pluginName = "CombatWeaponryPlus";
    public static Plugin plugin = Bukkit.getPluginManager().getPlugin("CombatWeaponryPlus");

    public static List<NamespacedKey> keys = new ArrayList<>();

    //Random number thing for  crit
    public static Integer getRandomInt(Integer max) {
        Random ran = new Random();
        return ran.nextInt(max);
    }


    @Override
    public void onEnable() {

        Cooldown.setupCooldown();

        this.getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig();

        //emeraldgear
        if (ConfigurationsBool.Emerald.getValue()) {
            EmeraldHelmet.setArmorPieceRecipe();
            EmeraldChestplate.setArmorPieceRecipe();
            EmeraldLeggings.setArmorPieceRecipe();
            EmeraldBoots.setArmorPieceRecipe();
        }
        if (ConfigurationsBool.EmeraldGear.getValue()) {
            EmeraldPickaxe.setToolRecipe();
            EmeraldSword.setToolRecipe();
            EmeraldAxe.setToolRecipe();
            EmeraldShovel.setToolRecipe();
            EmeraldHoe.setToolRecipe();
        }

        //swords
        if (ConfigurationsBool.ChorusBlade.getValue()) {
            ChorusBlade.setToolRecipe();
        }
        if (ConfigurationsBool.SwordBow.getValue()) {
            SwordBow.setBowRecipe();
        }
        if (ConfigurationsBool.HeavySwordBow.getValue()) {
            HeavySwordBow.setBowRecipe();
        }

        //chaimail
        if (ConfigurationsBool.Chainmail.getValue()) {
            ChainmailHelmet.setArmorPieceRecipe();
            ChainmailChestplate.setArmorPieceRecipe();
            ChainmailLeggings.setArmorPieceRecipe();
            ChainmailBoots.setArmorPieceRecipe();
        }

        //platedchain
        if (ConfigurationsBool.PlatedChainmail.getValue()) {
            PlatedChainmailHelmet.setArmorPieceRecipe();
            PlatedChainmailChestplate.setArmorPieceRecipe();
            PlatedChainmailLeggings.setArmorPieceRecipe();
            PlatedChainmailBoots.setArmorPieceRecipe();
        }

        //charms
        if (ConfigurationsBool.FeatherCharm.getValue()) {
            FeatherCharm.setCharmRecipe();
        }
        if (ConfigurationsBool.EmeraldCharm.getValue()) {
            EmeraldCharm.setCharmRecipe();
        }
        if (ConfigurationsBool.BlazeCharm.getValue()) {
            BlazeCharm.setCharmRecipe();
        }
        if (ConfigurationsBool.GoldCharm.getValue()) {
            GoldCharm.setCharmRecipe();
        }
        if (ConfigurationsBool.StarCharm.getValue()) {
            StarCharm.setCharmRecipe();
        }
        if (ConfigurationsBool.FrostCharm.getValue()) {
            FrostCharm.setCharmRecipe();
        }

        //scythes
        if (ConfigurationsBool.Scythes.getValue()) {
            WoodenScythe.setScytheRecipe();
            StoneScythe.setScytheRecipe();
            GoldenScythe.setScytheRecipe();
            IronScythe.setScytheRecipe();
            DiamondScythe.setScytheRecipe();
            NetheriteScythe.setScytheRecipe();
            if(ConfigurationsBool.EmeraldGear.getValue()){
                EmeraldScythe.setScytheRecipe();
            }
        }

        //obbypick

        if (ConfigurationsBool.ObsidianPickaxe.getValue()) {
            ObsidianPickaxe.setToolRecipe();
        }

        //rapiers
        if (ConfigurationsBool.Rapiers.getValue()) {
            WoodenRapier.setRapierRecipe();
            StoneRapier.setRapierRecipe();
            GoldenRapier.setRapierRecipe();
            IronRapier.setRapierRecipe();
            DiamondRapier.setRapierRecipe();
            NetheriteRapier.setRapierRecipe();
            if(ConfigurationsBool.EmeraldGear.getValue()){
                EmeraldRapier.setRapierRecipe();
            }
        }

        //longswords
        if (ConfigurationsBool.Longswords.getValue()) {
            WoodenLongsword.setLongswordRecipe();
            StoneLongsword.setLongswordRecipe();
            GoldenLongsword.setLongswordRecipe();
            IronLongsword.setLongswordRecipe();
            DiamondLongsword.setLongswordRecipe();
            NetheriteLongsword.setLongswordRecipe();
            if(ConfigurationsBool.EmeraldGear.getValue()){
                EmeraldLongsword.setLongswordRecipe();
            }
        }

        //knifes
        if (ConfigurationsBool.Knives.getValue()) {
            WoodenKnife.setKnifeRecipe();
            StoneKnife.setKnifeRecipe();
            GoldenKnife.setKnifeRecipe();
            IronKnife.setKnifeRecipe();
            DiamondKnife.setKnifeRecipe();
            NetheriteKnife.setKnifeRecipe();
            if(ConfigurationsBool.EmeraldGear.getValue()){
                EmeraldKnife.setKnifeRecipe();
            }
        }

        //spears
        if (ConfigurationsBool.Spears.getValue()) {
            WoodenSpear.setSpearRecipe();
            StoneSpear.setSpearRecipe();
            GoldenSpear.setSpearRecipe();
            IronSpear.setSpearRecipe();
            DiamondSpear.setSpearRecipe();
            NetheriteSpear.setSpearRecipe();
            if(ConfigurationsBool.EmeraldGear.getValue()){
                EmeraldSpear.setSpearRecipe();
            }
        }

        //katanas
        if (this.getConfig().getString("Katanas") == "true") {
            Bukkit.addRecipe(getwkatRecipe());
            Bukkit.addRecipe(getgkatRecipe());
            Bukkit.addRecipe(getskatRecipe());
            Bukkit.addRecipe(getikatRecipe());

            Bukkit.addRecipe(getdkatRecipe());
            Bukkit.addRecipe(getnkatRecipe());
        }
        if (this.getConfig().getString("EmeraldGear") == "true" && this.getConfig().getString("Katanas") == "true") {
            Bukkit.addRecipe(getekatRecipe());
        }

        if (this.getConfig().getString("Prismarine") == "true") {
            Bukkit.addRecipe(getinsttRecipe());
            Bukkit.addRecipe(getprisswordsrecipe());
            Bukkit.addRecipe(getprispickrecipe());
            Bukkit.addRecipe(getprisaxerecipe());
            Bukkit.addRecipe(getprisshovelrecipe());
            Bukkit.addRecipe(getprishoerecipe());
            Bukkit.addRecipe(getprishelmetrecipe());
            Bukkit.addRecipe(getprischestrecipe());
            Bukkit.addRecipe(getprislegrecipe());
            Bukkit.addRecipe(getprisbootsrecipe());
        }

        if (this.getConfig().getString("Longbow") == "true") {

            Bukkit.addRecipe(getLongBowRecipe());
        }
        if (this.getConfig().getString("Recurvebow") == "true") {

            Bukkit.addRecipe(getRecurveBowRecipe());
        }
        if (this.getConfig().getString("Compoundbow") == "true") {

            Bukkit.addRecipe(getCompoundBowRecipe());
        }

        //Bukkit.addRecipe(getTESTbowRecipe());
        if (this.getConfig().getString("Eelytra") == "true") {
            Bukkit.addRecipe(getEelytraRecipe());
        }

//Bukkit.addRecipe(geteaeaRecipe());
//Bukkit.addRecipe(gettestt());
        if (this.getConfig().getString("ReallyGoodSword") == "true") {
            Bukkit.addRecipe(getOPSWORDRecipe());
        }
        if (this.getConfig().getString("DiamondShield") == "true") {
            Bukkit.addRecipe(getDiaShieldRecipe());
        }
        if (this.getConfig().getString("NetheriteShield") == "true") {
            Bukkit.addRecipe(getNethShieldRecipe());
        }
        Bukkit.addRecipe(getawakswordsrecipe());
        if (this.getConfig().getString("Sabers") == "true") {
            Bukkit.addRecipe(getWSaberRecipe());
            Bukkit.addRecipe(getGSaberRecipe());
            Bukkit.addRecipe(getSSaberRecipe());
            Bukkit.addRecipe(getISaberRecipe());
            Bukkit.addRecipe(getDSaberRecipe());
            Bukkit.addRecipe(getNSaberRecipe());
        }
        if (this.getConfig().getString("EmeraldGear") == "true" && this.getConfig().getString("Sabers") == "true") {
            Bukkit.addRecipe(getESaberRecipe());
        }
        if (this.getConfig().getString("RepeatingCrossbow") == "true") {
            Bukkit.addRecipe(getrepcrossRecipe());

        }
        if (this.getConfig().getString("BurstCrossbow") == "true") {
            Bukkit.addRecipe(getburscrossRecipe());
        }
        if (this.getConfig().getString("RedstoneCore") == "true") {
            Bukkit.addRecipe(getRedPlateRecipe());
        }
        if (this.getConfig().getString("LongswordBow") == "true") {
            Bukkit.addRecipe(getLsBowRecipe());
        }
        if (this.getConfig().getString("RedstoneBow") == "true") {
            Bukkit.addRecipe(getRedstoneBowRecipe());
        }
        if (this.getConfig().getString("TridentBow") == "true") {
            Bukkit.addRecipe(getTridentBowRecipe());
        }

        if (this.getConfig().getString("WitherArmor") == "true") {
            Bukkit.addRecipe(getWitherHelmetRecipe());
            Bukkit.addRecipe(getWitherChestRecipe());
            Bukkit.addRecipe(getWitherLegRecipe());
            Bukkit.addRecipe(getWitherBootsRecipe());

        }
        if (this.getConfig().getString("JumpElytra") == "true") {
            Bukkit.addRecipe(jumpElytraRecipe());
        }
        if (this.getConfig().getString("TestKatana") == "true") {
            Bukkit.addRecipe(getTestKatanaRecipe());
        }
        if (this.getConfig().getString("TestScythe") == "true") {
            Bukkit.addRecipe(getTestScytheRecipe());
        }
        if (this.getConfig().getString("Cleavers") == "true") {
            Bukkit.addRecipe(getCleaverRecipe());
            Bukkit.addRecipe(getGoldCleaverRecipe());
            Bukkit.addRecipe(getStoneCleaverRecipe());
            Bukkit.addRecipe(getICleaverRecipe());
            Bukkit.addRecipe(getECleaverRecipe());
            Bukkit.addRecipe(getDCleaverRecipe());
            Bukkit.addRecipe(getNCleaverRecipe());
        }
        if (this.getConfig().getString("FishSword") == "true") {
            Bukkit.addRecipe(getTestFishRecipe());
        }

        if (this.getConfig().getString("WindBlade") == "true") {
            Bukkit.addRecipe(getWindBladeRecipe());
        }
        if (this.getConfig().getString("VolcanicBlade") == "true") {
            Bukkit.addRecipe(getFlameBladeRecipe());
        }
        if (this.getConfig().getString("VolcanicSpear") == "true") {
            Bukkit.addRecipe(getFlameSpearRecipe());
        }
        if (this.getConfig().getString("VolcanicAxe") == "true") {
            Bukkit.addRecipe(getFlameAxeRecipe());
        }
        if (this.getConfig().getString("VolcanicCleaver") == "true") {
            Bukkit.addRecipe(getFlameCleaverRecipe());
        }
        //Bukkit.addRecipe(testsmithingrecipe());
        //Bukkit.addRecipe(realtestrecipe());

    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String s = this.getConfig().getString("ResourcePack");
        if (s == "true") {
            player.setResourcePack(this.getConfig().getString("PackLink"));
        }

        player.discoverRecipes(keys);

    }

    @EventHandler()
    public void onClick(PlayerInteractEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.IRON_SWORD))
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData())
                if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore() && event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000007) {
                    Player player = event.getPlayer();
                    //Right click
                    if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                        if (Cooldown.checkCooldown(event.getPlayer())) {
                            player.launchProjectile(EnderPearl.class);
                            //player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 2));
                            Cooldown.setCooldown(event.getPlayer(), 2);

                        } else {

                        }


                    }


                }

    }

    @EventHandler()
    public void oncClick(PlayerInteractEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_PICKAXE))
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData())
                if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                    Player player = event.getPlayer();
                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000001) {
                        //left click
                        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {

                            player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 2));
                        }
                    }
                }
    }

    //longsword dash ability
    //unused

//	@EventHandler()
//	public void onccClick(PlayerInteractEvent event) {
//		Player player = event.getPlayer();
//		if (player.getInventory().getItemInMainHand().getItemMeta() != null)
//		if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData() == true)
//		if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
//			if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000001 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200001 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000011) {
//				if (player.getInventory().getItemInOffHand().getType() != Material.SHIELD) {
//					if (event.getAction() == Action.RIGHT_CLICK_AIR) {
//						if (Cooldown.checkCooldown(event.getPlayer())) {
//							World world = (World) player.getWorld();
//							world.playSound(player.getLocation(), Sound.ENTITY_PHANTOM_FLAP, 10, 1);
//							player.setVelocity(player.getLocation().getDirection().multiply(1.1));
//							Cooldown.setCooldown(event.getPlayer(), 5);
//
//						} else {
//							return;
//
//						}
//
///						return;
//				}
//			}
//			}

//	}

    @EventHandler()
    public void onccccClick(PlayerInteractEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_HOE))
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData())
                if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                    Player player = event.getPlayer();

                    if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1234567) {
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.MUSIC_DISC_CAT, 10, 1);
                            ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                            meta.setDisplayName("GOTTEM");
                            List<String> lore = new ArrayList<String>();
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

    @EventHandler
    public void eeeeee(EntityDamageByEntityEvent event) {
        //KNIFE combo thing
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (player.getInventory().getItemInMainHand().hasItemMeta())
                if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000006 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200006 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000016 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000006) {
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
                                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 15, 0));

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
                            org.bukkit.inventory.meta.Damageable test = (org.bukkit.inventory.meta.Damageable) player.getInventory().getItemInMainHand().getItemMeta();
                            short timesused = (short) test.getDamage();
                            short e = 250;
                            short dur = (short) (e - timesused);

                            double perc = (double) dur / e;
                            double multiplier = 1 - perc;
                            double q = 1;
                            double multiplierr = multiplier + q;
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

                            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                        }

                    //spear
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasLore())
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000004 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200004 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000014 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 4000004) {

                            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
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
                                    getServer().getScheduler().runTaskLater(this, new Runnable() {
                                        public void run() {
                                            World world = player.getWorld();
                                            world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                                        }
                                    }, 2L); //the 2L is ticks, there are 20 ticks in a second so this is 1/10th of a second delay

                                    getServer().getScheduler().runTaskLater(this, new Runnable() {
                                        public void run() {
                                            World world = player.getWorld();
                                            world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                                        }
                                    }, 4L);

                                    getServer().getScheduler().runTaskLater(this, new Runnable() {
                                        public void run() {
                                            World world = player.getWorld();
                                            world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                                        }
                                    }, 6L);

                                    getServer().getScheduler().runTaskLater(this, new Runnable() {
                                        public void run() {
                                            World world = player.getWorld();
                                            world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                            event.getEntity().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getEntity().getLocation().getX(), event.getEntity().getLocation().getY(), event.getEntity().getLocation().getZ(), 1);
                                        }
                                    }, 8L);


                                }


                            }

                    if (event.getEntity() instanceof Player) {

                        if (event.getCause() == DamageCause.ENTITY_ATTACK) {
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
    //KATANAS
    public ShapedRecipe getwkatRecipe() {

        //wood

        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWoodenKatana.line12")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWoodenKatana.line13")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWoodenKatana.line14")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 3.5
         * spd: 1.7
         * mspd: 0.02
         */
        double dmg = 2.5;
        double spd = -2.3;
        double mspd = 0.02;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aWoodenKatana.damage") - 1;
            spd = this.getConfig().getDouble("aWoodenKatana.speed") - 4;
            mspd = this.getConfig().getDouble("aWoodenKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier3);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWoodenKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "wooden_katana");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("  S", " S ", "S  ");


        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getgkatRecipe() {

        //gold

        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dGoldenKatana.line12")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dGoldenKatana.line13")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dGoldenKatana.line14")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 3.5
         * spd: 2
         * mspd: 0.02
         */
        double dmg = 2.5;
        double spd = -2;
        double mspd = 0.02;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aGoldenKatana.damage") - 1;
            spd = this.getConfig().getDouble("aGoldenKatana.speed") - 4;
            mspd = this.getConfig().getDouble("aGoldenKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dGoldenKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "golden_katana");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("  M", " M ", "S  ");

        recipe.setIngredient('M', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getskatRecipe() {

        //stone

        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dStoneKatana.line12")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dStoneKatana.line13")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dStoneKatana.line14")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 4
         * spd: 1.7
         * mspd: 0.02
         */
        double dmg = 3;
        double spd = -2.3;
        double mspd = 0.02;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aStoneKatana.damage") - 1;
            spd = this.getConfig().getDouble("aStoneKatana.speed") - 4;
            mspd = this.getConfig().getDouble("aStoneKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dStoneKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "stone_katana");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("  M", " M ", "S  ");

        recipe.setIngredient('M', Material.COBBLESTONE);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getikatRecipe() {

        //iron

        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dIronKatana.line12")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dIronKatana.line13")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dIronKatana.line14")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 5
         * spd: 1.7
         * mspd: 0.02
         */
        double dmg = 4;
        double spd = -2.3;
        double mspd = 0.02;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aIronKatana.damage") - 1;
            spd = this.getConfig().getDouble("aIronKatana.speed") - 4;
            mspd = this.getConfig().getDouble("aIronKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dIronKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "iron_katana");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("  M", " M ", "S  ");

        recipe.setIngredient('M', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getekatRecipe() {

        //em

        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dEmeraldKatana.line12")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dEmeraldKatana.line13")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dEmeraldKatana.line14")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 5
         * spd: 2
         * mspd: 0.02
         */
        double dmg = 4;
        double spd = -2;
        double mspd = 0.02;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aEmeraldKatana.damage") - 1;
            spd = this.getConfig().getDouble("aEmeraldKatana.speed") - 4;
            mspd = this.getConfig().getDouble("aEmeraldKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dEmeraldKatana.name")));
        meta.setCustomModelData(1000012);
        if (this.getConfig().getString("EnchantsOnEmeraldGear") == "true") {
            int num = this.getConfig().getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "emerald_katana");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("  M", " M ", "S  ");

        recipe.setIngredient('M', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getdkatRecipe() {

        //diamon

        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dDiamondKatana.line12")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dDiamondKatana.line13")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dDiamondKatana.line14")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 6
         * spd: 1.7
         * mspd: 0.02
         */
        double dmg = 5;
        double spd = -2.3;
        double mspd = 0.02;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aDiamondKatana.damage") - 1;
            spd = this.getConfig().getDouble("aDiamondKatana.speed") - 4;
            mspd = this.getConfig().getDouble("aDiamondKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dDiamondKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "diamond_katana");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("  M", " M ", "S  ");

        recipe.setIngredient('M', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getnkatRecipe() {

        //nethrite

        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dNetheriteKatana.line12")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dNetheriteKatana.line13")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dNetheriteKatana.line14")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 7
         * spd: 1.7
         * mspd: 0.02
         */
        double dmg = 6;
        double spd = -2.3;
        double mspd = 0.02;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aNetheriteKatana.damage") - 1;
            spd = this.getConfig().getDouble("aNetheriteKatana.speed") - 4;
            mspd = this.getConfig().getDouble("aNetheriteKatana.moveSpeed");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Move SPeed", mspd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dNetheriteKatana.name")));
        meta.setCustomModelData(1000002);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "netherite_katana");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("  M", " M ", "S  ");
        String n = this.getConfig().getString("NetheriteIngots");
        if (n == "true") {
            recipe.setIngredient('M', Material.NETHERITE_INGOT);
        } else {
            recipe.setIngredient('M', Material.NETHERITE_SCRAP);
        }
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }


    //CHARMS


    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == DamageCause.FALL) {
                if (player.getInventory().getItemInOffHand() != null)
                    if (player.getInventory().getItemInOffHand().getItemMeta() != null)
                        if (player.getInventory().getItemInOffHand().getItemMeta().getDisplayName().contains("Feather Charm"))
                            if (player.getInventory().getItemInOffHand().getItemMeta().hasLore()) {
                                event.setCancelled(true);
                            }
            }
        }
    }

    // below is test stuff, i dont remember what it was for

    //furnace recipe??

    //public FurnaceRecipe getffrecipe() {
    //	ItemStack result = new ItemStack(Material.PRISMARINE_CRYSTALS);
    //	ItemMeta meta = result.getItemMeta();

    //	List<String> lore = new ArrayList<String>();
    //	lore.add("");
    //	lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "The shards of diamonds");
    //	lore.add("");
    //	meta.setLore(lore);

    //	meta.setDisplayName("Diamond Shard");
    //	meta.addEnchant(Enchantment.DURABILITY, 69, true);
    //	meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    //	result.setItemMeta(meta);
    //	NamespacedKey key = new NamespacedKey(this, "diamond_shard");
    //	keys.add(key);
    //	FurnaceRecipe recipe = new FurnaceRecipe(key, result, Material.DIAMOND, 70, 200);
    //second last number is exp, last number is cooking time
    //	return recipe;
    //}


    //public SmithingRecipe getssssrecipe() {

    //	ItemStack result = new ItemStack(Material.PRISMARINE_CRYSTALS);
    //	ItemMeta meta = result.getItemMeta();
    //	meta.setDisplayName("Diamond Shard");
    //	meta.addEnchant(Enchantment.DAMAGE_ALL, 69, true);
    //	result.setItemMeta(meta);
    //	NamespacedKey key = new NamespacedKey(this, "diamond_shard");
    //keys.add(key);
    //	SmithingRecipe recipe = new SmithingRecipe(key, result, null, null);
    //
    //	return recipe;
    //}

//public SmithingRecipe getsrecipe() {
    //	ItemStack result = new ItemStack(Material.WOODEN_SWORD);

    //	NamespacedKey key = new NamespacedKey(this, "diamond_bruh");
    //	keys.add(key);

//		ItemMeta meta = result.getItemMeta();
//		List<String> lore = new ArrayList<String>();
//		lore.add("");
//		lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "eee");
    //	lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "gfhdfdghdhg");
    ///	meta.setLore(lore);

    //	result.setItemMeta(meta);
    //	RecipeChoice bruh = new RecipeChoice.MaterialChoice(Material.NETHERITE_SWORD);
    //	RecipeChoice bruh2 = new RecipeChoice.MaterialChoice(Material.ACACIA_BOAT);
    //bruh is base, bruh2 is upgrade
//SmithingRecipe recipe = new SmithingRecipe(key, result, bruh, bruh2);
//		recipe.getResult().addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 21);
//		recipe.getResult().setAmount(34);
//		//second last is base, last is add thingy??
//		return recipe;
//	}

    @EventHandler
    void onSmithingTableEventSWORD(PrepareSmithingEvent event) {
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

        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }

        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }

        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(1210001);
        double dmg = 8;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineSword.damage") - 1;
            spd = this.getConfig().getDouble("aPrismarineSword.speed") - 4;
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineSword.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2);

        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineSword.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineSword.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineSword.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineSword.line4")));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }

    }

    @EventHandler
    void onSmithingTableEventLONGSWORD(PrepareSmithingEvent event) {
        //LONGSWORD
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

        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }

        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }

        if (tool.getItemMeta().getCustomModelData() != 1000001 || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }

        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(1200001);
        double dmg = 1;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineLongsword.damageAdded");
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineLongsword.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("LongswordDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("LongswordDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("LongswordDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("LongswordDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("LongswordDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineLongsword.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineLongsword.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineLongsword.line8")));

        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    @EventHandler
    void onSmithingTableEventSCYTHE(PrepareSmithingEvent event) {
        //SCYTHE
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

        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }

        if (tool.getItemMeta().getCustomModelData() != 1000003 || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }

        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(1200003);
        //added damage (default is +1)
        double dmg = 1;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineScythe.damageAdded");
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineScythe.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("ScytheDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("ScytheDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("ScytheDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("ScytheDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("ScytheDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("ScytheDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("ScytheDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineScythe.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineScythe.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineScythe.line10")));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    @EventHandler
    void onSmithingTableEventRAPIER(PrepareSmithingEvent event) {
        //RAPIER
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

        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }

        if (tool.getItemMeta().getCustomModelData() != 1000005 || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }

        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(1200005);

        double dmg = 1;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineRapier.damageAdded");
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineRapier.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("RapierDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("RapierDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("RapierDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("RapierDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("RapierDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("RapierDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("RapierDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineRapier.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineRapier.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineRapier.line10")));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    @EventHandler
    void onSmithingTableEventSPEAR(PrepareSmithingEvent event) {
        //SPEAR
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

        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }

        if (tool.getItemMeta().getCustomModelData() != 1000004 || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }

        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(1200004);

        double dmg = 1;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineSpear.damageAdded");
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineSpear.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SpearDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SpearDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SpearDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SpearDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SpearDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SpearDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SpearDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SpearDescription.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SpearDescription.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineSpear.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineSpear.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineSpear.line12")));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    @EventHandler
    void onSmithingTableEventKATANA(PrepareSmithingEvent event) {
        //KATANA
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

        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }

        if (tool.getItemMeta().getCustomModelData() != 1000002 || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }

        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(1200002);

        double dmg = 1;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineKatana.damageAdded");
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineKatana.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KatanaDescription.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineKatana.line12")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineKatana.line13")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineKatana.line14")));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    @EventHandler
    void onSmithingTableEventKNIFE(PrepareSmithingEvent event) {
        //KATANA
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

        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }

        if (tool.getItemMeta().getCustomModelData() != 1000006 || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }

        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(1200006);

        double dmg = 1;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineKnife.damageAdded");
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineKnife.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KnifeDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KnifeDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KnifeDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KnifeDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KnifeDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("KnifeDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineKnife.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineKnife.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineKnife.line9")));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    @EventHandler
    void onSmithingTableEventSABER(PrepareSmithingEvent event) {
        //saber
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

        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }

        if (tool.getItemMeta().getCustomModelData() != 1000010 || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }

        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(1200010);

        double dmg = 1;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineSaber.damageAdded");
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineSaber.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineSaber.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineSaber.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineSaber.line7")));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    @EventHandler
    void onSmithingTableEventCLEAVER(PrepareSmithingEvent event) {
        //cleaver
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

        if (tool.getType() != Material.NETHERITE_SWORD || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }
        if (!tool.getItemMeta().hasCustomModelData()) {
            return;
        }

        if (tool.getItemMeta().getCustomModelData() != 1000021 || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }

        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(1200021);

        double dmg = 1;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineCleaver.damageAdded");
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineCleaver.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineCleaver.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineCleaver.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineCleaver.line12")));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprisswordsrecipe() {
        //this is important or else other recipe no worky
        SmithingRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_sword"),
                new ItemStack(Material.AIR), // any material seems fine
                new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), // template
                new RecipeChoice.MaterialChoice(Material.NETHERITE_SWORD), // base
                new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD) // add
        );

        return recipe;
    }
    public SmithingRecipe testsmithingrecipe() {
        //this is important or else other recipe no worky
        SmithingRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "test_item"),
                new ItemStack(Material.ACACIA_SAPLING), // any material seems fine
                new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), // template
                new RecipeChoice.MaterialChoice(Material.GOLDEN_SWORD), // base
                new RecipeChoice.MaterialChoice(Material.DIAMOND_SWORD) // addition
        );

        return recipe;
    }

    @EventHandler
    void onSmithingTableEventPICK(PrepareSmithingEvent event) {
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

        if (tool.getType() != Material.NETHERITE_PICKAXE || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }

        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }

        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(1210002);
        double dmg = 6;
        double spd = -2.8;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarinePickaxe.damage") - 1;
            spd = this.getConfig().getDouble("aPrismarinePickaxe.speed") - 4;
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarinePickaxe.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2);

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarinePickaxe.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarinePickaxe.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarinePickaxe.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarinePickaxe.line4")));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprispickrecipe() {
        //this is important or else other recipe no work
        SmithingRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_pick"),
                new ItemStack(Material.AIR), // any material seems fine
                new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), // template
                new RecipeChoice.MaterialChoice(Material.NETHERITE_PICKAXE),
                new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD)
        );

        return recipe;
    }

    @EventHandler
    void onSmithingTableEventAXE(PrepareSmithingEvent event) {
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

        if (tool.getType() != Material.NETHERITE_AXE || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }

        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }

        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(1220001);
        double dmg = 10;
        double spd = -3;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineAxe.damage") - 1;
            spd = this.getConfig().getDouble("aPrismarineAxe.speed") - 4;
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineAxe.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2);

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineAxe.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineAxe.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineAxe.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineAxe.line4")));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprisaxerecipe() {
        //this is important or else other recipe no work
        SmithingRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_axe"),
                new ItemStack(Material.AIR), // any material seems fine
                new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), // template
                new RecipeChoice.MaterialChoice(Material.NETHERITE_AXE),
                new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD)
        );

        return recipe;
    }

    @EventHandler
    void onSmithingTableEventSHOVEL(PrepareSmithingEvent event) {
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

        if (tool.getType() != Material.NETHERITE_SHOVEL || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }

        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }

        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(1210004);
        double dmg = 6.5;
        double spd = -3;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineShovel.damage") - 1;
            spd = this.getConfig().getDouble("aPrismarineShovel.speed") - 4;
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineShovel.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2);

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineShovel.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineShovel.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineShovel.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineShovel.line4")));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprisshovelrecipe() {
        //this is important or else other recipe no work
        SmithingRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_shovel"),
                new ItemStack(Material.AIR), // any material seems fine
                new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), // template
                new RecipeChoice.MaterialChoice(Material.NETHERITE_SHOVEL),
                new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD)
        );

        return recipe;
    }

    @EventHandler
    void onSmithingTableEventHOE(PrepareSmithingEvent event) {
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

        if (tool.getType() != Material.NETHERITE_HOE || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }

        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }

        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(1210005);
        double dmg = 1;
        double spd = 0;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aPrismarineHoe.damage") - 1;
            spd = this.getConfig().getDouble("aPrismarineHoe.speed") - 4;
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineHoe.name")));
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);


        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineHoe.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineHoe.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineHoe.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineHoe.line4")));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprishoerecipe() {
        //this is important or else other recipe no work
        SmithingRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_shoe"),
                new ItemStack(Material.AIR), // any material seems fine
                new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), // template
                new RecipeChoice.MaterialChoice(Material.NETHERITE_HOE),
                new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD)
        );

        return recipe;
    }

    @EventHandler
    void onSmithingTableEventHELMET(PrepareSmithingEvent event) {
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

        if (tool.getType() != Material.NETHERITE_HELMET || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }

        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }

        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(1220001);
        double arm = 4;
        double armt = 3;
        double kbr = 0.1;
        double hp = 1;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            arm = this.getConfig().getDouble("aPrismarineHelmet.Armor");
            armt = this.getConfig().getDouble("aPrismarineHelmet.ArmorToughness");
            kbr = this.getConfig().getDouble("aPrismarineHelmet.KBResist") / 10;
            hp = this.getConfig().getDouble("aPrismarineHelmet.BonusHealth");
        }
        resultm.setDisplayName(ChatColor.GREEN + "Prismarine Helmet");
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Armor", arm,
                Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        resultm.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Armor", armt,
                Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        resultm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Armor", kbr,
                Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        resultm.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifier3);
        AttributeModifier modifier4 = new AttributeModifier(UUID.randomUUID(), "Armor", hp,
                Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        resultm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier4);

        result.setItemMeta(resultm);

        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprishelmetrecipe() {
        //this is important or else other recipe no work
        SmithingRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_helmet"),
                new ItemStack(Material.AIR), // any material seems fine
                new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), // template
                new RecipeChoice.MaterialChoice(Material.NETHERITE_HELMET),
                new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD)
        );

        return recipe;
    }

    @EventHandler
    void onSmithingTableEventCHESTPLATE(PrepareSmithingEvent event) {
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

        if (tool.getType() != Material.NETHERITE_CHESTPLATE || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }

        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }

        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(1220002);
        double arm = 9;
        double armt = 3;
        double kbr = 0.1;
        double hp = 2;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            arm = this.getConfig().getDouble("aPrismarineChestplate.Armor");
            armt = this.getConfig().getDouble("aPrismarineChestplate.ArmorToughness");
            kbr = this.getConfig().getDouble("aPrismarineChestplate.KBResist") / 10;
            hp = this.getConfig().getDouble("aPrismarineChestplate.BonusHealth");
        }
        resultm.setDisplayName(ChatColor.GREEN + "Prismarine Chestplate");
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Armor", arm,
                Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        resultm.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Armor", armt,
                Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        resultm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Armor", kbr,
                Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        resultm.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifier3);
        AttributeModifier modifier4 = new AttributeModifier(UUID.randomUUID(), "Armor", hp,
                Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        resultm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier4);
        result.setItemMeta(resultm);

        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprischestrecipe() {
        //this is important or else other recipe no work
        SmithingRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_chest"),
                new ItemStack(Material.AIR), // any material seems fine
                new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), // template
                new RecipeChoice.MaterialChoice(Material.NETHERITE_CHESTPLATE),
                new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD)
        );

        return recipe;
    }

    @EventHandler
    void onSmithingTableEventLEGGINGS(PrepareSmithingEvent event) {
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

        if (tool.getType() != Material.NETHERITE_LEGGINGS || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }

        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }

        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(1220003);
        double arm = 7;
        double armt = 3;
        double kbr = 0.1;
        double hp = 2;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            arm = this.getConfig().getDouble("aPrismarineLeggings.Armor");
            armt = this.getConfig().getDouble("aPrismarineLeggings.ArmorToughness");
            kbr = this.getConfig().getDouble("aPrismarineLeggings.KBResist") / 10;
            hp = this.getConfig().getDouble("aPrismarineLeggings.BonusHealth");
        }
        resultm.setDisplayName(ChatColor.GREEN + "Prismarine Leggings");
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Armor", arm,
                Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        resultm.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Armor", armt,
                Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        resultm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Armor", kbr,
                Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        resultm.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifier3);
        AttributeModifier modifier4 = new AttributeModifier(UUID.randomUUID(), "Armor", hp,
                Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        resultm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier4);
        result.setItemMeta(resultm);

        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprislegrecipe() {
        //this is important or else other recipe no work
        SmithingRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_leg"),
                new ItemStack(Material.AIR), // any material seems fine
                new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), // template
                new RecipeChoice.MaterialChoice(Material.NETHERITE_LEGGINGS),
                new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD)
        );

        return recipe;
    }

    @EventHandler
    void onSmithingTableEventBOOTS(PrepareSmithingEvent event) {
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

        if (tool.getType() != Material.NETHERITE_BOOTS || modifier.getType() != Material.PRISMARINE_SHARD) {
            return;
        }

        if (tool.getItemMeta().hasCustomModelData() || !modifier.getItemMeta().hasCustomModelData()) {
            return;
        }

        ItemStack result = tool.clone();
        ItemMeta resultm = result.getItemMeta();
        resultm.setCustomModelData(1220004);
        double arm = 4;
        double armt = 3;
        double kbr = 0.1;
        double hp = 1;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            arm = this.getConfig().getDouble("aPrismarineBoots.Armor");
            armt = this.getConfig().getDouble("aPrismarineBoots.ArmorToughness");
            kbr = this.getConfig().getDouble("aPrismarineBoots.KBResist") / 10;
            hp = this.getConfig().getDouble("aPrismarineBoots.BonusHealth");
        }
        resultm.setDisplayName(ChatColor.GREEN + "Prismarine Boots");
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Armor", arm,
                Operation.ADD_NUMBER, EquipmentSlot.FEET);
        resultm.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Armor", armt,
                Operation.ADD_NUMBER, EquipmentSlot.FEET);
        resultm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Armor", kbr,
                Operation.ADD_NUMBER, EquipmentSlot.FEET);
        resultm.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifier3);
        AttributeModifier modifier4 = new AttributeModifier(UUID.randomUUID(), "Armor", hp,
                Operation.ADD_NUMBER, EquipmentSlot.FEET);
        resultm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier4);
        result.setItemMeta(resultm);

        if (this.getConfig().getString("Prismarine") == "true") {
            event.setResult(result);
        }
    }

    public SmithingRecipe getprisbootsrecipe() {
        //this is important or else other recipe no work
        SmithingRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "pris_boots"),
                new ItemStack(Material.AIR), // any material seems fine
                new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI), // template
                new RecipeChoice.MaterialChoice(Material.NETHERITE_BOOTS),
                new RecipeChoice.MaterialChoice(Material.PRISMARINE_SHARD)
        );

        return recipe;
    }


    public ShapedRecipe getinsttRecipe() {


        ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(9999901);
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineAlloy.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineAlloy.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineAlloy.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineAlloy.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineAlloy.line5")));

        meta.setLore(lore);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dPrismarineAlloy.name")));
        meta.addEnchant(Enchantment.DURABILITY, 5, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "prisupgrade");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("LCL", "IBI", "LDL");

        recipe.setIngredient('B', Material.NETHERITE_INGOT);
        recipe.setIngredient('L', Material.PRISMARINE_SHARD);
        recipe.setIngredient('D', Material.DIAMOND_BLOCK);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('C', Material.PRISMARINE_CRYSTALS);

        return recipe;
    }


//public ShapedRecipe getTESTbowRecipe() {

    //TEST bow

    //ItemStack item = new ItemStack(Material.BOW);
    //ItemMeta meta = item.getItemMeta();

//	meta.setDisplayName(ChatColor.GOLD + "Test Bow");

    //List<String> lore = new ArrayList<String>();
//	lore.add("");
//	lore.add(ChatColor.translateAlternateColorCodes('&', "&7Increases arrow velocity"));
//	lore.add("");
//	meta.setLore(lore);

    //modifier
    //AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -3,
    //				Operation.ADD_NUMBER, EquipmentSlot.HAND);
    //meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
    //AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 8,
    //				Operation.ADD_NUMBER, EquipmentSlot.HAND);
    //meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);


//meta.setCustomModelData(1069691);
//	item.setItemMeta(meta);

    //NamespacedKey key = new NamespacedKey(this, "test_bow");
    //keys.add(key);
//	ShapedRecipe recipe = new ShapedRecipe(key, item);

//	recipe.shape("SSS", "SBS", "SSS");

    //recipe.setIngredient('S', Material.EXPERIENCE_BOTTLE);
    //recipe.setIngredient('B', Material.BOW);

    //return recipe;
//}

    @EventHandler
    public void playerBowShoot(EntityShootBowEvent event) {
        Entity entity = event.getEntity();
        Float speed = event.getForce();
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
                    //arrow.setVelocity(new Vector
                    //(vector.getX() * speed * 3.5,
                    //vector.getY() * speed * 4,
                    //vector.getZ()* speed * 3.5));
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
                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3330001 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3330004) {//||player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 3330001) {
                        Vector vector = player.getLocation().getDirection();
                        //these numbers make it around the same velocity as normal bows
                        //arrow.setVelocity(new Vector
                        //(vector.getX() * speed * 3.5,
                        //vector.getY() * speed * 4,
                        //vector.getZ()* speed * 3.5));
                        double aspd = 4;
                        double x = 1;
                        if (this.getConfig().getString("UseCustomValues") == "true") {
                            aspd = this.getConfig().getDouble("aLongBow.arrowSpeed");
                            x = this.getConfig().getDouble("aLongBow.dmgMultiplier");
                        }
                        arrow.setVelocity(new Vector
                                (vector.getX() * speed * aspd,
                                        vector.getY() * speed * aspd,
                                        vector.getZ() * speed * aspd));
                        arrow.setDamage(arrow.getDamage() * x);
                        return;
                    }
                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 3330001 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 3330004) {// || player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() != 3330001) {
                        // RBOW
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3330002) {//||player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 3330002) {
                            Vector vector = player.getLocation().getDirection();
                            //these numbers make it around the same velocity as normal bows
                            //arrow.setVelocity(new Vector
                            //(vector.getX() * speed * 3.5,
                            //vector.getY() * speed * 4,
                            //vector.getZ()* speed * 3.5));
                            double aspd = 5;
                            double x = 1;
                            if (this.getConfig().getString("UseCustomValues") == "true") {
                                aspd = this.getConfig().getDouble("aRecurveBow.arrowSpeed");
                                x = this.getConfig().getDouble("aRecurveBow.dmgMultiplier");
                            }
                            arrow.setVelocity(new Vector
                                    (vector.getX() * speed * aspd,
                                            vector.getY() * speed * aspd,
                                            vector.getZ() * speed * aspd));
                            arrow.setDamage(arrow.getDamage() * x);
                            return;
                        }
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 3330002) {//|| player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() != 3330002) {
                            //CBow
                            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3330003) {//||player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 3330003) {
                                Vector vector = player.getLocation().getDirection();
                                //these numbers make it around the same velocity as normal bows
                                //arrow.setVelocity(new Vector
                                //(vector.getX() * speed * 3.5,
                                //vector.getY() * speed * 4,
                                //vector.getZ()* speed * 3.5));
                                double aspd = 6;
                                double x = 1;
                                if (this.getConfig().getString("UseCustomValues") == "true") {
                                    aspd = this.getConfig().getDouble("aCompoundBow.arrowSpeed");
                                    x = this.getConfig().getDouble("aCompoundBow.dmgMultiplier");
                                }
                                arrow.setVelocity(new Vector
                                        (vector.getX() * speed * aspd,
                                                vector.getY() * speed * aspd,
                                                vector.getZ() * speed * aspd));
                                arrow.setDamage(arrow.getDamage() * x);
                                return;
                            }
                            return;
                        }
                        return;
                    }


                    return;
                }
                return;
            }


        } else {
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
		meta2.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier3);
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
    public ShapedRecipe getLongBowRecipe() {

        //long bow

        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dLongBow.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dLongBow.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dLongBow.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dLongBow.line4")));

        meta.setLore(lore);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dLongBow.name")));
        meta.setCustomModelData(3330001);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Speed", -0.01,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier3);


        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "longbow");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("LCL", "CBC", "LCL");

        recipe.setIngredient('B', Material.BOW);
        recipe.setIngredient('L', Material.IRON_INGOT);
        recipe.setIngredient('C', Material.STICK);

        return recipe;
    }

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
		meta2.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier3);
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
    public ShapedRecipe getRecurveBowRecipe() {

        // recurve bow

        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRecurveBow.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRecurveBow.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRecurveBow.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRecurveBow.line4")));

        meta.setLore(lore);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Speed", -0.02,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier3);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRecurveBow.name")));
        meta.setCustomModelData(3330002);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "recurvebow");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("LCL", "CBQ", "LQL");

        recipe.setIngredient('B', Material.BOW);
        recipe.setIngredient('C', Material.CRIMSON_STEM);
        recipe.setIngredient('Q', Material.WARPED_STEM);
        recipe.setIngredient('L', Material.IRON_BARS);

        return recipe;
    }

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
		meta2.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier3);
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
    public ShapedRecipe getCompoundBowRecipe() {

        // compound bow

        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dCompoundBow.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dCompoundBow.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dCompoundBow.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dCompoundBow.line4")));

        meta.setLore(lore);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dCompoundBow.name")));
        meta.setCustomModelData(3330003);
        AttributeModifier modifier3 = new AttributeModifier(UUID.randomUUID(), "Speed", -0.03,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier3);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "compoundbow");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("LCL", "CBC", "LCL");

        recipe.setIngredient('B', Material.BOW);
        recipe.setIngredient('C', Material.NETHERITE_SCRAP);
        recipe.setIngredient('L', Material.IRON_BLOCK);

        return recipe;
    }

    public ShapedRecipe getEelytraRecipe() {

        //test elytra

        ItemStack item = new ItemStack(Material.ELYTRA);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();

        lore.add("");

        lore.add(ChatColor.GRAY + "test");
        lore.add(ChatColor.GRAY + "(not really meant to be");
        lore.add(ChatColor.GRAY + "obtainable yet but you can");
        lore.add(ChatColor.GRAY + "test it in creative or something)");
        lore.add("");

        meta.setLore(lore);

        meta.setDisplayName(ChatColor.GOLD + "Eelytra");
        meta.setCustomModelData(1560001);

        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "eelytra");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("LCL", "CBC", "LCL");

        recipe.setIngredient('B', Material.ELYTRA);
        recipe.setIngredient('C', Material.EXPERIENCE_BOTTLE);
        recipe.setIngredient('L', Material.BEDROCK);

        return recipe;
    }

    @EventHandler()
    public void oncccClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getChestplate() != null)
            if (player.getInventory().getChestplate().getItemMeta() != null)
                if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData())
                    if (player.getInventory().getChestplate().getItemMeta().hasLore())
                        if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560001) {

                            if (event.getAction() == Action.RIGHT_CLICK_AIR && player.isGliding()) {
                                //player.sendMessage("qqq");

                                ItemMeta meta = player.getInventory().getChestplate().getItemMeta();
                                meta.setCustomModelData(1560002);
                                player.getInventory().getChestplate().setItemMeta(meta);
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ENTITY_PHANTOM_FLAP, 10, 1);
                                player.setVelocity(player.getLocation().getDirection().multiply(2));
                                getServer().getScheduler().runTaskLater(this, new Runnable() {
                                    public void run() {
                                        //player.setVelocity(player.getLocation().getDirection().multiply(0.5));

                                    }
                                }, 10L);
                            }
                        }

    }

    @EventHandler
    public void toggleGlideEvent(EntityToggleGlideEvent event) {
        Player player = (Player) event.getEntity();
        if (player.getInventory().getChestplate().getType() == Material.ELYTRA) {
            if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560001 || player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560002) {

                    if (player.isGliding()) {
                        //gliding end
                        ///player.sendMessage("eee");
                        if (!player.isDead()) {
                            getServer().getScheduler().runTaskLater(this, new Runnable() {
                                public void run() {
                                    if (player.getInventory().getChestplate() != null) {
                                        ItemMeta meta = player.getInventory().getChestplate().getItemMeta();
                                        meta.setCustomModelData(1560001);
                                        player.getInventory().getChestplate().setItemMeta(meta);
                                    }


                                }
                            }, 10L);
                        }


                    } else {
                        //gliding start
                        ///player.sendMessage("aaa");

                        //player.setVelocity(player.getLocation().getDirection().multiply(1.1));

                        //Vector vector = player.getLocation().getDirection();
                        //player.setVelocity(new Vector
                        //		(vector.getX() * 0.5,
                        //		vector.getY() * 10,
                        //		vector.getZ() * 0.5));

                        player.setVelocity(new Vector(0, 1, 0));

                        getServer().getScheduler().runTaskLater(this, new Runnable() {
                            public void run() {
                                //player.setVelocity(player.getLocation().getDirection().multiply(0.5));

                            }
                        }, 5L);
                    }

                }
            }
        }

    }


    @EventHandler()
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity().getType().equals(EntityType.PLAYER)) {
            Player player = (Player) event.getEntity();

            if (player.isDead()) {
                return;
            }
            if (player.getInventory().getChestplate() != null)
                if (player.getInventory().getChestplate().getItemMeta() != null)
                    if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData())
                        if (player.getInventory().getChestplate().getItemMeta().hasLore())
                            if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560001 || player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560002) {
                                double dmg = event.getDamage();
                                int num = (int) dmg;
                                String string = String.valueOf(num);
                                player.sendMessage(string);
                                event.setDamage(dmg * 0.5);
                                if (event.getCause() == DamageCause.FALL && player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1560002) {
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

    public ShapedRecipe geteaeaRecipe() {

        //test

        ItemStack item = new ItemStack(Material.ACACIA_BOAT);
        ItemMeta meta = item.getItemMeta();


        meta.setDisplayName(ChatColor.GOLD + "aaa ingot");
        meta.setCustomModelData(3330001);
        meta.addEnchant(Enchantment.MENDING, 43, true);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "aaaingot");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("LLL", "LBe", "LLL");

        recipe.setIngredient('B', Material.ACACIA_BOAT);
        recipe.setIngredient('L', Material.IRON_INGOT);
        recipe.setIngredient('e', Material.BEDROCK);

        return recipe;
    }

    public ShapedRecipe gettestt() {
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "aaa sword");
        item.setItemMeta(meta);

        ItemStack item2 = new ItemStack(Material.ACACIA_BOAT);
        ItemMeta meta2 = item.getItemMeta();
        meta2.setDisplayName(ChatColor.GOLD + "aaa ingot");
        meta2.setCustomModelData(3330001);
        meta2.addEnchant(Enchantment.MENDING, 43, true);
        item2.setItemMeta(meta2);

        @SuppressWarnings("deprecation")
        RecipeChoice custom1Choice = new RecipeChoice.ExactChoice(item2);
        NamespacedKey key = new NamespacedKey(this, "eeaaeeeaea");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item); //custom item that will be crafted
        recipe.shape(" H ", " H ", " I ");
        recipe.setIngredient('I', Material.BEDROCK);
        recipe.setIngredient('H', custom1Choice); // usage of the RecipeChoice
        return recipe;
    }

    //unused item
    public ShapedRecipe getbonekatRecipe() {

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
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -2.2,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 3,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

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
    }

    public ShapedRecipe getOPSWORDRecipe() {

        ItemStack item = new ItemStack(Material.NETHERITE_HOE);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dReallyGoodSword.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dReallyGoodSword.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dReallyGoodSword.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dReallyGoodSword.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dReallyGoodSword.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dReallyGoodSword.line6")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.setLore(lore);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dReallyGoodSword.name")));
        meta.setCustomModelData(1234567);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "reallygoodsword");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("LLL", "fef", "fsf");
        recipe.setIngredient('L', Material.LAPIS_BLOCK);
        recipe.setIngredient('e', Material.GOLD_BLOCK);
        recipe.setIngredient('s', Material.DIAMOND_BLOCK);
        recipe.setIngredient('f', Material.REDSTONE);


        return recipe;
    }

    public ShapedRecipe getrepcrossRecipe() {

        //repeater crossbow

        ItemStack item = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRepeatingCrossbow.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRepeatingCrossbow.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRepeatingCrossbow.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRepeatingCrossbow.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRepeatingCrossbow.line5")));
        meta.setLore(lore);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRepeatingCrossbow.name")));
        meta.setCustomModelData(5552001);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "repeater_crossbow");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("SIS", "sRs", "rSr");

        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('r', Material.REDSTONE);
        recipe.setIngredient('s', Material.STRING);
        recipe.setIngredient('R', Material.REPEATER);

        return recipe;
    }

    public ShapedRecipe getburscrossRecipe() {

        //burst crossbow

        ItemStack item = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dBurstCrossbow.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dBurstCrossbow.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dBurstCrossbow.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dBurstCrossbow.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dBurstCrossbow.line5")));

        meta.setLore(lore);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dBurstCrossbow.name")));
        meta.setCustomModelData(5552002);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "burst_crossbow");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("SIS", "sRs", "rSr");

        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('r', Material.REDSTONE);
        recipe.setIngredient('s', Material.STRING);
        recipe.setIngredient('R', Material.COMPARATOR);

        return recipe;
    }

    public ShapedRecipe getRedstoneBowRecipe() {

        //redstone bow

        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRedstoneBow.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRedstoneBow.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRedstoneBow.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRedstoneBow.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRedstoneBow.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRedstoneBow.line6")));

        meta.setLore(lore);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRedstoneBow.name")));
        meta.setCustomModelData(3330005);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "redstone_bow");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("rIs", "SRs", "rIs");

        recipe.setIngredient('I', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);
        recipe.setIngredient('r', Material.REDSTONE);
        recipe.setIngredient('s', Material.STRING);
        recipe.setIngredient('R', Material.COMPARATOR);

        return recipe;
    }


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
            if (player.getInventory().getItemInMainHand() == null) {
                return;
            }
            if (!player.getInventory().getItemInMainHand().hasItemMeta()) {
                return;
            }
            //repeating crossbow
            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                if (player.getInventory().getChestplate() != null)
                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552001 && player.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE) {
                        if (!player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {

                            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552001 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                                if (player.getInventory().getChestplate() != null) {
                                    if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                                        if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                                            if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {

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

                                        Float speed = event.getForce();
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
                                        Float speed = event.getForce();
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
                                        Float speed = event.getForce();
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
                                        Float speed = event.getForce();
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
                        if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                            if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() != 1231234) {

                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552001 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                                    if (player.getInventory().getChestplate() != null) {
                                        if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                                            if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                                                if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {

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

                                            Float speed = event.getForce();
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
                                            Float speed = event.getForce();
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
                                            Float speed = event.getForce();
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
                                            Float speed = event.getForce();
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

                                Float speed = event.getForce();
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
                                Float speed = event.getForce();
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
                                Float speed = event.getForce();
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
                                Float speed = event.getForce();
                                arrow.setVelocity(new Vector
                                        (playerDirection.getX() * speed * 4.5,
                                                playerDirection.getY() * speed * 5,
                                                playerDirection.getZ() * speed * 4.5));
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ITEM_CROSSBOW_SHOOT, 10, 1);
                            }
                        }, 12L);
                    }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552001 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                    if (player.getInventory().getChestplate() != null) {
                        if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                            if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                                if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {

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

                            Float speed = event.getForce();
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
                            Float speed = event.getForce();
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
                            Float speed = event.getForce();
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
                            Float speed = event.getForce();
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
            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                if (player.getInventory().getChestplate() != null)


                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552002 && player.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE) {
                        Location loc = player.getLocation();

                        if (player.getInventory().getChestplate() != null) {
                            if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                                if (!player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {


                                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552002 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                                        if (player.getInventory().getChestplate() != null) {
                                            if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                                                if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                                                    if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {

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
                                if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                                    if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() != 1231234) {

                                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552002 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                                            if (player.getInventory().getChestplate() != null) {
                                                if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                                                    if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                                                        if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {

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

                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552002 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                    if (player.getInventory().getChestplate() != null) {
                        if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                            if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                                if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {

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
                    //		arrow1.setVelocity(new Vector
                    //				(playerDirection1.getX() * speed1 * 4.5,
                    //				playerDirection1.getY() * speed1 * 6,
                    //				playerDirection1.getZ()* speed1 * 4.5));


                    //arrow 2

                    //		Vector playerDirection2 = player.getLocation().getDirection();
                    //		Arrow arrow2 = player.launchProjectile(Arrow.class, playerDirection2);
                    //		arrow2.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                    ////		Float speed2 = event.getForce();
                    //		arrow2.setVelocity(new Vector
                    //				(playerDirection2.getX() * speed2 * 4.5,
                    //				playerDirection2.getY() * speed2 * 4,
                    //				playerDirection2.getZ()* speed2 * 4.5));

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
            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                if (player.getInventory().getChestplate() != null)


                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3330005 && player.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE) {
                        //Location loc = player.getLocation();

                        if (player.getInventory().getChestplate() != null) {
                            if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                                if (!player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {


                                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3330005 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                                        if (player.getInventory().getChestplate() != null) {
                                            if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                                                if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                                                    if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {

                                                        return;
                                                    }
                                                }
                                            }
                                        }
                                        player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);


                                        //stuff
                                        Float speed = event.getForce();
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
                                if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                                    if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() != 1231234) {

                                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3330005 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                                            if (player.getInventory().getChestplate() != null) {
                                                if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                                                    if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                                                        if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {

                                                            return;
                                                        }
                                                    }
                                                }
                                            }
                                            player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);


                                            //stuff
                                            Float speed = event.getForce();
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
                        Float speed = event.getForce();
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

                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 3330005 && player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
                    if (player.getInventory().getChestplate() != null) {
                        if (player.getInventory().getChestplate().getType().equals(Material.IRON_CHESTPLATE)) {
                            if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                                if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() == 1231234) {

                                    return;
                                }
                            }
                        }
                    }
                    player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount() - 1);

                    //stuff
                    Float speed = event.getForce();
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
                if (this.getConfig().getString("WitherBones") == "true") {
                    world.dropItemNaturally(killed.getLocation(), Items.witherBone(this.getConfig()));
                }
            }
            if (random == 2) {
                if (this.getConfig().getString("WitherBones") == "true") {
                    world.dropItemNaturally(killed.getLocation(), Items.witherBone(this.getConfig()));
                    world.dropItemNaturally(killed.getLocation(), Items.witherBone(this.getConfig()));
                }
            }
            int random2 = getRandomInt(100);
            if (random2 == 1) {
                if (this.getConfig().getString("Vessel") == "true") {
                    world.dropItemNaturally(killed.getLocation(), Items.vessel(this.getConfig()));
                }
            }
        }
        if (this.getConfig().getString("InfusedVessel") == "true") {
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
                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222223
                                        || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222223) {
                                    World world = player.getWorld();
                                    world.spawnParticle(Particle.ENCHANTMENT_TABLE, player.getLocation().getX(), player.getLocation().getY() + 2, player.getLocation().getZ(), 500);
                                    world.spawnParticle(Particle.CLOUD, player.getLocation(), 100);

                                    ItemMeta meta2 = player.getInventory().getItemInMainHand().getItemMeta();
                                    meta2.setCustomModelData(2222224);
                                    meta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dInfusedVessel.name")));
                                    double dmg = 9;
                                    double spd = -2.4;
                                    if (this.getConfig().getString("UseCustomValues") == "true") {
                                        dmg = this.getConfig().getDouble("aInfusedVessel.damage") - 1;
                                        spd = this.getConfig().getDouble("aInfusedVessel.speed") - 4;
                                    }
                                    AttributeModifier modifier1a = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                                            Operation.ADD_NUMBER, EquipmentSlot.HAND);
                                    meta2.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1a);
                                    AttributeModifier modifier2a = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd,
                                            Operation.ADD_NUMBER, EquipmentSlot.HAND);
                                    meta2.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2a);

                                    List<String> lore2 = new ArrayList<String>();
                                    lore2.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dInfusedVessel.line1")));
                                    lore2.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dInfusedVessel.line2")));
                                    lore2.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dInfusedVessel.line3")));
                                    lore2.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dInfusedVessel.line4")));
                                    lore2.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dInfusedVessel.line5")));
                                    lore2.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dInfusedVessel.line6")));
                                    lore2.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dInfusedVessel.line7")));
                                    lore2.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dInfusedVessel.line8")));
                                    lore2.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dInfusedVessel.line9")));
                                    lore2.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dInfusedVessel.line10")));
                                    lore2.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dInfusedVessel.line11")));
                                    meta2.setLore(lore2);
                                    //important:
                                    meta2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                    player.getInventory().getItemInMainHand().setItemMeta(meta2);
                                }
                            }
                        }
                    }

                }
            }
        }
        if (this.getConfig().getString("CursedVessel") == "true") {
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
                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222223
                                        || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1222223) {
                                    World world = player.getWorld();
                                    world.spawnParticle(Particle.ENCHANTMENT_TABLE, player.getLocation().getX(), player.getLocation().getY() + 2, player.getLocation().getZ(), 500);
                                    world.spawnParticle(Particle.CLOUD, player.getLocation(), 100);
                                    ItemMeta meta3 = player.getInventory().getItemInMainHand().getItemMeta();
                                    meta3.setCustomModelData(2222225);
                                    meta3.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dCursedVessel.name")));
                                    double dmg = 9;
                                    double spd = -2.4;
                                    if (this.getConfig().getString("UseCustomValues") == "true") {
                                        dmg = this.getConfig().getDouble("aCursedVessel.damage") - 1;
                                        spd = this.getConfig().getDouble("aCursedVessel.speed") - 4;
                                    }
                                    AttributeModifier modifier1e = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                                            Operation.ADD_NUMBER, EquipmentSlot.HAND);
                                    meta3.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1e);
                                    AttributeModifier modifier2e = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd,
                                            Operation.ADD_NUMBER, EquipmentSlot.HAND);
                                    meta3.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2e);
                                    //AttributeModifier modifier3e = new AttributeModifier(UUID.randomUUID(), "Health", -0.5,
                                    //		Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND);
                                    //meta3.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier3e);

                                    List<String> lore3 = new ArrayList<String>();
                                    lore3.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dCursedVessel.line1")));
                                    lore3.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dCursedVessel.line2")));
                                    lore3.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dCursedVessel.line3")));
                                    lore3.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dCursedVessel.line4")));
                                    lore3.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dCursedVessel.line5")));
                                    lore3.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dCursedVessel.line6")));
                                    lore3.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dCursedVessel.line7")));
                                    lore3.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dCursedVessel.line8")));
                                    lore3.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dCursedVessel.line9")));
                                    lore3.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dCursedVessel.line10")));
                                    lore3.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dCursedVessel.line11")));
                                    meta3.setLore(lore3);
                                    //important:
                                    meta3.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                                    player.getInventory().getItemInMainHand().setItemMeta(meta3);
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


    public ShapedRecipe getDiaShieldRecipe() {

        ItemStack item = new ItemStack(Material.SHIELD);
        ItemMeta meta = item.getItemMeta();
        if (this.getConfig().getString("EnchantsDiamondShield") == "true") {
            int num = this.getConfig().getInt("DShieldEnchantLevels.Unbreaking");

            meta.addEnchant(Enchantment.DURABILITY, num, true);

        }

        meta.setDisplayName("Diamond Shield");
        meta.setCustomModelData(5430001);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "diamondshield");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("LeL", "LLL", " L ");
        recipe.setIngredient('L', Material.IRON_INGOT);
        recipe.setIngredient('e', Material.DIAMOND);


        return recipe;
    }

    public ShapedRecipe getNethShieldRecipe() {

        ItemStack item = new ItemStack(Material.SHIELD);
        ItemMeta meta = item.getItemMeta();
        if (this.getConfig().getString("EnchantsNetheriteShield") == "true") {
            int num = this.getConfig().getInt("NShieldEnchantLevels.Unbreaking");

            meta.addEnchant(Enchantment.DURABILITY, num, true);

        }
        meta.setDisplayName("Netherite Shield");
        meta.setCustomModelData(5430002);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "netheriteshield");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("LeL", "LLL", " L ");
        recipe.setIngredient('L', Material.IRON_INGOT);
        recipe.setIngredient('e', Material.NETHERITE_INGOT);


        return recipe;
    }


    @EventHandler()
    public void onParryClick(PlayerInteractEvent event) {
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_SWORD))
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData())
                if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                    Player player = event.getPlayer();

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
                                getServer().getScheduler().runTaskLater(this, new Runnable() {
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
                                getServer().getScheduler().runTaskLater(this, new Runnable() {
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
                                getServer().getScheduler().runTaskLater(this, new Runnable() {
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
                                getServer().getScheduler().runTaskLater(this, new Runnable() {
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
                                getServer().getScheduler().runTaskLater(this, new Runnable() {
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
                                getServer().getScheduler().runTaskLater(this, new Runnable() {
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
                                getServer().getScheduler().runTaskLater(this, new Runnable() {
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

                            } else {

                            }

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
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselPurple.name")));
        meta.setCustomModelData(2222228);
        double dmg = 11;
        double spd = -2.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aAwakenedVesselPurple.damage") - 1;
            spd = this.getConfig().getDouble("aAwakenedVesselPurple.speed") - 4;
        }
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2);

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselPurple.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselPurple.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselPurple.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselPurple.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselPurple.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselPurple.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselPurple.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselPurple.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselPurple.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselPurple.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselPurple.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselPurple.line12")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselPurple.line13")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselPurple.line14")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselPurple.line15")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselPurple.line16")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);

        if (this.getConfig().getString("AwakenedVesselPurple") == "true") {
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
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselWhite.name")));
        meta.setCustomModelData(2222226);
        double dmg = 11;
        double spd = -2.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aAwakenedVesselWhite.damage") - 1;
            spd = this.getConfig().getDouble("aAwakenedVesselWhite.speed") - 4;
        }
        AttributeModifier modifier1 = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Atackspeed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2);

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselWhite.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselWhite.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselWhite.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselWhite.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselWhite.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselWhite.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselWhite.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselWhite.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselWhite.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselWhite.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselWhite.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselWhite.line12")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselWhite.line13")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselWhite.line14")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselWhite.line15")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dAwakenedVesselWhite.line16")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);

        if (this.getConfig().getString("AwakenedVesselWhite") == "true") {
            event.setResult(item);
        }

    }

    public SmithingRecipe getawakswordsrecipe() {
        //this is important or else other recipe no work
        SmithingRecipe recipe = new SmithingTransformRecipe(new NamespacedKey(this, "tesfergvergtt"),
                new ItemStack(Material.AIR),
                new RecipeChoice.MaterialChoice(Material.LAPIS_LAZULI),
                new RecipeChoice.MaterialChoice(Material.NETHERITE_SWORD),
                new RecipeChoice.MaterialChoice(Material.NETHERITE_SWORD)
        );

        return recipe;
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
        if (event.getPlayer().getInventory().getItemInMainHand() == null) {
            return;
        }
        if (!event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
            return;
        }
        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_SWORD))
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasCustomModelData())
                if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                    Player player = event.getPlayer();

                    if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                        //if (Cooldown.checkCooldown(event.getPlayer())) {
                        if (player.getAttackCooldown() == 1.0) {


                            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222226 ||
                                    player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222228) {
                                List<Entity> ee = player.getNearbyEntities(5, 5, 5);
                                //if (player.getLevel() > ee.size()) {
                                if (player.getLevel() > 1) {

                                    player.setLevel(player.getLevel() - 1);


                                    for (int e = 0; e < ee.size(); e++) {
                                        Entity entity = ee.get(e);


                                        if (entity instanceof LivingEntity) {
                                            LivingEntity livingen = (LivingEntity) entity;

                                            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2222226) {
                                                if (livingen.getCategory().equals(EntityCategory.UNDEAD)) {
                                                    livingen.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 0));
                                                } else {
                                                    if (!(entity instanceof Player)) {
                                                        livingen.addPotionEffect(new PotionEffect(PotionEffectType.HARM, 1, 0));

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

                                //}
                                //player.setCooldown(Material.NETHERITE_SWORD, 20);
                                //}

                                //Cooldown.setCooldown(event.getPlayer(), 1);
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

    //DUAL WIELDING
    @EventHandler
    public void onRightClickEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getHand().equals(EquipmentSlot.HAND)) {
            if (player.getInventory().getItemInOffHand() != null) {
                if (player.getInventory().getItemInOffHand().hasItemMeta()) {
                    if (player.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) {
                        if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010
                                || player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1200010
                                || player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000030) {

                            //stops dual wielding 2 different weapon type:
                            if (this.getConfig().getString("DualWieldSaberOnly") == "true") {
                                //test the config thing, not sure if it works
                                if (player.getInventory().getItemInMainHand() != null) {
                                    if (player.getInventory().getItemInMainHand().hasItemMeta()) {
                                        if (player.getInventory().getItemInMainHand().getType().equals(Material.WOODEN_SWORD)) {
                                            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000010
                                                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1200010
                                                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000030) {
                                                    return;
                                                }
                                            }
                                            if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                return;
                                            }
                                        }
                                        if (player.getInventory().getItemInMainHand().getType().equals(Material.STONE_SWORD)) {
                                            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000010
                                                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1200010
                                                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000030) {
                                                    return;
                                                }
                                            }
                                            if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                return;
                                            }
                                        }
                                        if (player.getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_SWORD)) {
                                            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000010
                                                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1200010
                                                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000030) {
                                                    return;
                                                }
                                            }
                                            if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                return;
                                            }
                                        }
                                        if (player.getInventory().getItemInMainHand().getType().equals(Material.IRON_SWORD)) {
                                            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000010
                                                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1200010
                                                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000030) {
                                                    return;
                                                }
                                            }
                                            if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                return;
                                            }
                                        }
                                        if (player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_SWORD)) {
                                            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000010
                                                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1200010
                                                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000030) {
                                                    return;
                                                }
                                            }
                                            if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                return;
                                            }
                                        }
                                        if (player.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_SWORD)) {
                                            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000010
                                                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1200010
                                                        && player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 1000030) {
                                                    return;
                                                }
                                            }
                                            if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                            player.swingOffHand();

                            if (event.getRightClicked() instanceof Damageable) {
                                Damageable e = (Damageable) event.getRightClicked();
                                if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010
                                        && player.getInventory().getItemInOffHand().getType().equals(Material.WOODEN_SWORD)) {
                                    double dmg = 4;
                                    double cooldown = player.getCooldown(Material.WOODEN_SWORD);

                                    if (player.hasCooldown(Material.WOODEN_SWORD)) {
                                        //12 is number of ticks left of the cooldown
                                        if (cooldown <= 12 * 0.2) {
                                            double dmg2 = dmg * 0.8;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.4
                                                && cooldown > 12 * 0.2) {
                                            double dmg2 = dmg * 0.6;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.6
                                                && cooldown > 12 * 0.4) {
                                            double dmg2 = dmg * 0.4;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.8
                                                && cooldown > 12 * 0.6) {
                                            double dmg2 = dmg * 0.2;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12
                                                && cooldown > 12 * 0.8) {
                                            double dmg2 = dmg * 0.1;
                                            e.damage(dmg2, player);
                                        }
                                    }
                                    if (!player.hasCooldown(Material.WOODEN_SWORD)) {
                                        e.damage(dmg, player);
                                        //.damage(amount, source of the damage (entity));
                                        World world = player.getWorld();
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                    }
                                }
                                if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010
                                        && player.getInventory().getItemInOffHand().getType().equals(Material.STONE_SWORD)) {
                                    double dmg = 5;
                                    double cooldown = player.getCooldown(Material.STONE_SWORD);
                                    if (player.hasCooldown(Material.STONE_SWORD)) {
                                        if (cooldown <= 12 * 0.2) {
                                            double dmg2 = dmg * 0.8;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.4
                                                && cooldown > 12 * 0.2) {
                                            double dmg2 = dmg * 0.6;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.6
                                                && cooldown > 12 * 0.4) {
                                            double dmg2 = dmg * 0.4;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.8
                                                && cooldown > 12 * 0.6) {
                                            double dmg2 = dmg * 0.2;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12
                                                && cooldown > 12 * 0.8) {
                                            double dmg2 = dmg * 0.1;
                                            e.damage(dmg2, player);
                                        }
                                    }
                                    if (!player.hasCooldown(Material.STONE_SWORD)) {
                                        e.damage(dmg, player);
                                        World world = player.getWorld();
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                    }
                                }
                                if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010
                                        && player.getInventory().getItemInOffHand().getType().equals(Material.IRON_SWORD)) {
                                    double dmg = 6;
                                    double cooldown = player.getCooldown(Material.IRON_SWORD);
                                    if (cooldown != 1) {
                                        if (cooldown <= 1
                                                && cooldown > 0.8) {
                                            double dmg2 = dmg * 0.9;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 0.8
                                                && cooldown > 0.6) {
                                            double dmg2 = dmg * 0.8;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 0.6
                                                && cooldown > 0.4) {
                                            double dmg2 = dmg * 0.6;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 0.4
                                                && cooldown > 0.2) {
                                            double dmg2 = dmg * 0.4;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 0.2) {
                                            double dmg2 = dmg * 0.2;
                                            e.damage(dmg2, player);
                                        }
                                    }
                                    if (!player.hasCooldown(Material.IRON_SWORD)) {
                                        e.damage(dmg, player);
                                        World world = player.getWorld();
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                    }
                                }
                                if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010
                                        && player.getInventory().getItemInOffHand().getType().equals(Material.GOLDEN_SWORD)) {
                                    double dmg = 4;
                                    double cooldown = player.getCooldown(Material.GOLDEN_SWORD);
                                    if (player.hasCooldown(Material.GOLDEN_SWORD)) {
                                        if (cooldown <= 12 * 0.2) {
                                            double dmg2 = dmg * 0.8;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.4
                                                && cooldown > 12 * 0.2) {
                                            double dmg2 = dmg * 0.6;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.6
                                                && cooldown > 12 * 0.4) {
                                            double dmg2 = dmg * 0.4;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.8
                                                && cooldown > 12 * 0.6) {
                                            double dmg2 = dmg * 0.2;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12
                                                && cooldown > 12 * 0.8) {
                                            double dmg2 = dmg * 0.1;
                                            e.damage(dmg2, player);
                                        }
                                    }
                                    if (!player.hasCooldown(Material.GOLDEN_SWORD)) {
                                        e.damage(dmg, player);
                                        World world = player.getWorld();
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                    }
                                }
                                if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000030
                                        && player.getInventory().getItemInOffHand().getType().equals(Material.GOLDEN_SWORD)) {
                                    double dmg = 6;
                                    double cooldown = player.getCooldown(Material.GOLDEN_SWORD);
                                    if (player.hasCooldown(Material.GOLDEN_SWORD)) {
                                        if (cooldown <= 12 * 0.2) {
                                            double dmg2 = dmg * 0.8;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.4
                                                && cooldown > 12 * 0.2) {
                                            double dmg2 = dmg * 0.6;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.6
                                                && cooldown > 12 * 0.4) {
                                            double dmg2 = dmg * 0.4;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.8
                                                && cooldown > 12 * 0.6) {
                                            double dmg2 = dmg * 0.2;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12
                                                && cooldown > 12 * 0.8) {
                                            double dmg2 = dmg * 0.1;
                                            e.damage(dmg2, player);
                                        }
                                    }
                                    if (!player.hasCooldown(Material.GOLDEN_SWORD)) {
                                        e.damage(dmg, player);
                                        World world = player.getWorld();
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                    }
                                }
                                if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010
                                        && player.getInventory().getItemInOffHand().getType().equals(Material.DIAMOND_SWORD)) {
                                    double dmg = 7;
                                    double cooldown = player.getCooldown(Material.DIAMOND_SWORD);
                                    if (player.hasCooldown(Material.DIAMOND_SWORD)) {
                                        if (cooldown <= 12 * 0.2) {
                                            double dmg2 = dmg * 0.8;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.4
                                                && cooldown > 12 * 0.2) {
                                            double dmg2 = dmg * 0.6;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.6
                                                && cooldown > 12 * 0.4) {
                                            double dmg2 = dmg * 0.4;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.8
                                                && cooldown > 12 * 0.6) {
                                            double dmg2 = dmg * 0.2;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12
                                                && cooldown > 12 * 0.8) {
                                            double dmg2 = dmg * 0.1;
                                            e.damage(dmg2, player);
                                        }
                                    }
                                    if (!player.hasCooldown(Material.DIAMOND_SWORD)) {
                                        e.damage(dmg, player);
                                        World world = player.getWorld();
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                    }
                                }
                                if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010
                                        && player.getInventory().getItemInOffHand().getType().equals(Material.NETHERITE_SWORD)) {
                                    double dmg = 8;
                                    double cooldown = player.getCooldown(Material.NETHERITE_SWORD);
                                    if (player.hasCooldown(Material.NETHERITE_SWORD)) {
                                        if (cooldown <= 12 * 0.2) {
                                            double dmg2 = dmg * 0.8;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.4
                                                && cooldown > 12 * 0.2) {
                                            double dmg2 = dmg * 0.6;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.6
                                                && cooldown > 12 * 0.4) {
                                            double dmg2 = dmg * 0.4;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.8
                                                && cooldown > 12 * 0.6) {
                                            double dmg2 = dmg * 0.2;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12
                                                && cooldown > 12 * 0.8) {
                                            double dmg2 = dmg * 0.1;
                                            e.damage(dmg2, player);
                                        }
                                    }
                                    if (!player.hasCooldown(Material.NETHERITE_SWORD)) {
                                        e.damage(dmg, player);
                                        World world = player.getWorld();
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                    }
                                }
                                if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1200010
                                        && player.getInventory().getItemInOffHand().getType().equals(Material.NETHERITE_SWORD)) {
                                    double dmg = 9;
                                    double cooldown = player.getCooldown(Material.NETHERITE_SWORD);
                                    if (player.hasCooldown(Material.NETHERITE_SWORD)) {
                                        if (cooldown <= 12 * 0.2) {
                                            double dmg2 = dmg * 0.8;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.4
                                                && cooldown > 12 * 0.2) {
                                            double dmg2 = dmg * 0.6;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.6
                                                && cooldown > 12 * 0.4) {
                                            double dmg2 = dmg * 0.4;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12 * 0.8
                                                && cooldown > 12 * 0.6) {
                                            double dmg2 = dmg * 0.2;
                                            e.damage(dmg2, player);
                                        }
                                        if (cooldown <= 12
                                                && cooldown > 12 * 0.8) {
                                            double dmg2 = dmg * 0.1;
                                            e.damage(dmg2, player);
                                        }
                                    }
                                    if (!player.hasCooldown(Material.NETHERITE_SWORD)) {
                                        e.damage(dmg, player);
                                        World world = player.getWorld();
                                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                    }
                                }


                                player.setCooldown(player.getInventory().getItemInOffHand().getType(), 12);
                            }
                            //i think this part below was the old way for saber dual wielding
                            // Vector playerDirection = player.getLocation().getDirection();
//	Arrow arrow = player.launchProjectile(Arrow.class, playerDirection);
//	arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
//	arrow.setSilent(true);

//	arrow.setVelocity(new Vector
//			(playerDirection.getX() * 45,
//			playerDirection.getY() * 50,
//			playerDirection.getZ()* 45));
//	if (player.getInventory().getItemInMainHand().hasItemMeta()) {
//		if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
//			if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000010
//					 || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200010) {
//
//				if (player.hasCooldown(player.getInventory().getItemInOffHand().getType())) {
//					  int num = 12-player.getCooldown(player.getInventory().getItemInOffHand().getType());
//					  double dmg = num * 0.083;
//					  player.sendMessage(String.valueOf(dmg));

//					  arrow.setDamage(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue()/100*dmg);
//				  } else {
//					  if (player.hasCooldown(player.getInventory().getItemInOffHand().getType()) != true) {
//						  arrow.setDamage(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue()/100);
//					  }
//				  }


//				World world = player.getWorld();
//				world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);

//							player.setCooldown(player.getInventory().getItemInOffHand().getType(), 12);
//							return;
//			}
//		}
//	}
//	if (player.hasCooldown(player.getInventory().getItemInOffHand().getType())) {
//		int num = 12-player.getCooldown(player.getInventory().getItemInOffHand().getType());
//		  double dmg = num * 0.083;
//		  player.sendMessage(String.valueOf(dmg));
//		  arrow.setDamage(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue()/50*dmg);
//	  } else {
//		  if (player.hasCooldown(player.getInventory().getItemInOffHand().getType()) != true) {
//			  arrow.setDamage(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue()/50);
//		  }
//	  }
                        }
                    }
                }
            }
        }
    }

    public ShapedRecipe getWSaberRecipe() {

        //wood

        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWoodenSaber.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWoodenSaber.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWoodenSaber.line7")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 4
         * spd: 1.6
         */
        double dmg = 3;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aWoodenSaber.damage") - 1;
            spd = this.getConfig().getDouble("aWoodenSaber.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWoodenSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "wooden_saber");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" SS", " S ", "S  ");


        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getGSaberRecipe() {

        //gold

        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dGoldenSaber.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dGoldenSaber.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dGoldenSaber.line7")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 4
         * spd: 1.6
         */
        double dmg = 3;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aGoldenSaber.damage") - 1;
            spd = this.getConfig().getDouble("aGoldenSaber.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dGoldenSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "golden_saber");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" aa", " a ", "S  ");

        recipe.setIngredient('a', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getSSaberRecipe() {

        //stone

        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dStoneSaber.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dStoneSaber.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dStoneSaber.line7")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 5
         * spd: 1.6
         */
        double dmg = 4;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aStoneSaber.damage") - 1;
            spd = this.getConfig().getDouble("aStoneSaber.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dStoneSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "stone_saber");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" aa", " a ", "S  ");

        recipe.setIngredient('a', Material.COBBLESTONE);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getISaberRecipe() {

        //iron

        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dIronSaber.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dIronSaber.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dIronSaber.line7")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 6
         * spd: 1.6
         */
        double dmg = 5;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aIronSaber.damage") - 1;
            spd = this.getConfig().getDouble("aIronSaber.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dIronSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "iron_saber");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" aa", " a ", "S  ");

        recipe.setIngredient('a', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getESaberRecipe() {

        //emerald

        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dEmeraldSaber.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dEmeraldSaber.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dEmeraldSaber.line7")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 6
         * spd: 1.6
         */
        double dmg = 5;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aEmeraldSaber.damage") - 1;
            spd = this.getConfig().getDouble("aEmeraldSaber.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dEmeraldSaber.name")));
        meta.setCustomModelData(1000030);

        //enchants
        if (this.getConfig().getString("EnchantsOnEmeraldGear") == "true") {
            int num = this.getConfig().getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }

        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "emerald_saber");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" aa", " a ", "S  ");

        recipe.setIngredient('a', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getDSaberRecipe() {

        //diamond

        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dDiamondSaber.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dDiamondSaber.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dDiamondSaber.line7")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 7
         * spd: 1.6
         */
        double dmg = 6;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aDiamondSaber.damage") - 1;
            spd = this.getConfig().getDouble("aDiamondSaber.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dDiamondSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "diamond_saber");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" aa", " a ", "S  ");

        recipe.setIngredient('a', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getNSaberRecipe() {

        //netherite

        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("SaberDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dNetheriteSaber.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dNetheriteSaber.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dNetheriteSaber.line7")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 8
         * spd: 1.6
         */
        double dmg = 7;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aNetheriteSaber.damage") - 1;
            spd = this.getConfig().getDouble("aNetheriteSaber.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dNetheriteSaber.name")));
        meta.setCustomModelData(1000010);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "netherite_saber");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" aa", " a ", "S  ");

        recipe.setIngredient('a', Material.NETHERITE_SCRAP);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getRedPlateRecipe() {


        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRedstoneCore.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRedstoneCore.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRedstoneCore.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRedstoneCore.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRedstoneCore.line5")));
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        //modifier
        double arm = 2;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            arm = this.getConfig().getDouble("aRedstoneCore.Armor");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Armor", arm,
                Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dRedstoneCore.name")));
        meta.setCustomModelData(1231234);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "redstone_core");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("ede", "dcd", "bab");

        recipe.setIngredient('a', Material.IRON_CHESTPLATE);
        recipe.setIngredient('b', Material.LEATHER);
        recipe.setIngredient('c', Material.REDSTONE_BLOCK);
        recipe.setIngredient('d', Material.COMPARATOR);
        recipe.setIngredient('e', Material.QUARTZ);

        return recipe;
    }

    public ShapedRecipe getLsBowRecipe() {

        //longsword bow

        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dLongswordBow.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dLongswordBow.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dLongswordBow.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dLongswordBow.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dLongswordBow.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dLongswordBow.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dLongswordBow.line7")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        double dmg = 7;
        double spd = -2.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aLongswordBow.damage") - 1;
            spd = this.getConfig().getDouble("aLongswordBow.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2);


        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dLongswordBow.name")));
        meta.setCustomModelData(3330004);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "longsword_bow");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" b ", " b ", "bab");

        recipe.setIngredient('a', Material.BOW);
        recipe.setIngredient('b', Material.IRON_INGOT);
//	recipe.setIngredient('c', Material.IRON_BLOCK);

        return recipe;
    }

    public ShapedRecipe getTridentBowRecipe() {

        //trident bow

        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add("");

        lore.add(ChatColor.translateAlternateColorCodes('&', "&6I made this for fun"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7- Shoots tridents (converts arrows to tridents)"));
        lore.add("");
        meta.setLore(lore);


        meta.setDisplayName("Trident Bow");
        meta.setCustomModelData(1069691);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "trident_bow");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" b ", "bab", " b ");

        recipe.setIngredient('a', Material.TRIDENT);
        recipe.setIngredient('b', Material.BEDROCK);
//	recipe.setIngredient('c', Material.IRON_BLOCK);

        return recipe;
    }

// test for spawning custom particle
// spawns armor stand with item with custom texture
// doesn't really work because the armor stand is sometimes visible for a split second before becoming invis

//solved that by spawning stand around 3 blocks below so it's not visible most of the time
//and then making the texture appear around 3 blocks above the stand

    @EventHandler
    public void onRightClickEntity2(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();

        if (player.getInventory().getItemInMainHand().getType() != Material.IRON_SWORD) {
            return;
        }
        if (!(player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData())) {
            return;
        }
        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 38) {
            return;
        }
        if (event.getRightClicked().getType().equals(EntityType.ARMOR_STAND)) {
            return;
        }
        if (!(event.getRightClicked() instanceof LivingEntity)) {
            return;
        }
        if (event.getRightClicked() instanceof Damageable) {
            Damageable ent = (Damageable) event.getRightClicked();

            if (event.getRightClicked() instanceof LivingEntity) {
                LivingEntity e = (LivingEntity) event.getRightClicked();
                e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 10));
                e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 5, 10));
                //below gives other entities near target slowness too, except for the player
                List<Entity> ee = e.getNearbyEntities(2, 2, 2);
                for (int en = 0; en < ee.size(); en++) {
                    Entity entity = ee.get(en);

                    if (entity instanceof LivingEntity) {
                        LivingEntity livingen = (LivingEntity) entity;
                        if (livingen != player) {

                            livingen.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5, 10));
                            livingen.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 5, 10));

                        }
                    }
                }
            }
            //ent.damage(10, player);

            //how high the armor stand spawns
            float standheight = -3;
            //below for spawn stand infront player
            Vector direc = player.getLocation().getDirection().multiply(1.7); //was 2
            Location loc = player.getLocation().add(direc);
            Location loca = new Location(player.getWorld(), loc.getX(), loc.getY() + standheight, loc.getZ());
            ArmorStand stand = (ArmorStand) player.getLocation().getWorld().spawnEntity(loca.setDirection(player.getLocation().getDirection()), EntityType.ARMOR_STAND);

            //below for spawn stand at entity
            Location loc3 = new Location(player.getWorld(), ent.getLocation().getX(), ent.getLocation().getY() + standheight, ent.getLocation().getZ());
            Location loc4 = loc3.setDirection(player.getLocation().getDirection());
            ArmorStand standd = (ArmorStand) player.getLocation().getWorld().spawnEntity(loc4, EntityType.ARMOR_STAND);
            stand.setVisible(false);
            stand.setInvulnerable(true);
            stand.setGravity(false);
            standd.setVisible(false);
            standd.setInvulnerable(true);
            standd.setGravity(false);
            ItemStack item = new ItemStack(Material.POTATO);
            ItemMeta meta = item.getItemMeta();
            meta.setCustomModelData(37);
            item.setItemMeta(meta);

            stand.getEquipment().setHelmet(item);
            stand.addEquipmentLock(EquipmentSlot.HEAD, LockType.REMOVING_OR_CHANGING);
            stand.addEquipmentLock(EquipmentSlot.CHEST, LockType.REMOVING_OR_CHANGING);
            stand.addEquipmentLock(EquipmentSlot.LEGS, LockType.REMOVING_OR_CHANGING);
            stand.addEquipmentLock(EquipmentSlot.FEET, LockType.REMOVING_OR_CHANGING);
            stand.addEquipmentLock(EquipmentSlot.HAND, LockType.REMOVING_OR_CHANGING);
            stand.addEquipmentLock(EquipmentSlot.OFF_HAND, LockType.REMOVING_OR_CHANGING);
            standd.getEquipment().setHelmet(item);
            standd.addEquipmentLock(EquipmentSlot.HEAD, LockType.REMOVING_OR_CHANGING);
            standd.addEquipmentLock(EquipmentSlot.CHEST, LockType.REMOVING_OR_CHANGING);
            standd.addEquipmentLock(EquipmentSlot.LEGS, LockType.REMOVING_OR_CHANGING);
            standd.addEquipmentLock(EquipmentSlot.FEET, LockType.REMOVING_OR_CHANGING);
            standd.addEquipmentLock(EquipmentSlot.HAND, LockType.REMOVING_OR_CHANGING);
            standd.addEquipmentLock(EquipmentSlot.OFF_HAND, LockType.REMOVING_OR_CHANGING);

            //i hope there is a better way to do this than below
            player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            }, (long) 0.5);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            }, 1);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            }, (long) 1.5);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            }, 2);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            }, (long) 2.5);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            }, 3);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            }, (long) 3.5);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            }, 4);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            }, (long) 4.5);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                stand.remove();
                standd.remove();
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
                player.getWorld().createExplosion(ent.getLocation(), 2, false, false, player);
            }, 5); // Time in ticks (20 ticks = 1 second)

        }

    }


    //test
    public ShapedRecipe getWitherHelmetRecipe() {

        //wither bone helmet

        ItemStack item = new ItemStack(Material.IRON_HELMET);
        ItemMeta meta = item.getItemMeta();

        double kbr = 0.2;
        double hp = 5;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            kbr = this.getConfig().getDouble("aWitherHelmet.KBResist") / 10;
            hp = this.getConfig().getDouble("aWitherHelmet.BonusHealth");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp,
                Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "KnockbackResistance", kbr,
                Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringHelmet.name")));
        meta.setCustomModelData(5553331);

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line8")));

        meta.setLore(lore);

        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "wither_bone_helmet");
        keys.add(key);

        ItemStack wbone = new ItemStack(Material.BONE);
        ItemMeta meta2 = wbone.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta2.setCustomModelData(2222222);
        wbone.setItemMeta(meta2);

        @SuppressWarnings("deprecation")
        RecipeChoice wibone = new RecipeChoice.ExactChoice(Items.witherBone(this.getConfig()));
        ShapedRecipe recipe = new ShapedRecipe(key, item); //custom item that will be crafted
        recipe.shape("BBB", "B B", " N ");
        //recipe.setIngredient('I', Material.BEDROCK);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('B', wibone); // usage of the RecipeChoice
        return recipe;
    }

    public ShapedRecipe getWitherChestRecipe() {

        //wither bone chestplate

        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();

        double kbr = 0.2;
        double hp = 5;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            kbr = this.getConfig().getDouble("aWitherChestplate.KBResist") / 10;
            hp = this.getConfig().getDouble("aWitherChestplate.BonusHealth");
        }

        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp,
                Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "KnockbackResistance", kbr,
                Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringChestplate.name")));
        meta.setCustomModelData(5553332);

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line8")));

        meta.setLore(lore);

        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "wither_bone_chestplate");
        keys.add(key);

        ItemStack wbone = new ItemStack(Material.BONE);
        ItemMeta meta2 = wbone.getItemMeta();

        meta2.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta2.setCustomModelData(2222222);
        wbone.setItemMeta(meta2);

        @SuppressWarnings("deprecation")
        RecipeChoice wibone = new RecipeChoice.ExactChoice(Items.witherBone(this.getConfig()));
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("N N", "B B", "BBB");
        //recipe.setIngredient('I', Material.BEDROCK);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('B', wibone);
        return recipe;
    }

    public ShapedRecipe getWitherLegRecipe() {

        //wither bone leggings

        ItemStack item = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta meta = item.getItemMeta();

        double kbr = 0.2;
        double hp = 5;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            kbr = this.getConfig().getDouble("aWitherLeggings.KBResist") / 10;
            hp = this.getConfig().getDouble("aWitherLeggings.BonusHealth");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp,
                Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "KnockbackResistance", kbr,
                Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringLeggings.name")));
        meta.setCustomModelData(5553333);

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line8")));

        meta.setLore(lore);

        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "wither_bone_leggings");
        keys.add(key);

        ItemStack wbone = new ItemStack(Material.BONE);
        ItemMeta meta2 = wbone.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta2.setCustomModelData(2222222);
        wbone.setItemMeta(meta2);

        @SuppressWarnings("deprecation")
        RecipeChoice wibone = new RecipeChoice.ExactChoice(Items.witherBone(this.getConfig()));
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("BNB", "B B", "B B");
        //recipe.setIngredient('I', Material.BEDROCK);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('B', wibone);

        return recipe;
    }

    public ShapedRecipe getWitherBootsRecipe() {

        //wither bone boots

        ItemStack item = new ItemStack(Material.IRON_BOOTS);
        ItemMeta meta = item.getItemMeta();
        double kbr = 0.2;
        double hp = 5;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            kbr = this.getConfig().getDouble("aWitherBoots.KBResist") / 10;
            hp = this.getConfig().getDouble("aWitherBoots.BonusHealth");
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Health", hp,
                Operation.ADD_NUMBER, EquipmentSlot.FEET);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "KnockbackResistance", kbr,
                Operation.ADD_NUMBER, EquipmentSlot.FEET);
        meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringBoots.name")));
        meta.setCustomModelData(5553334);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWitheringArmorSet.line8")));

        meta.setLore(lore);

        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "wither_bone_boots");
        keys.add(key);

        ItemStack wbone = new ItemStack(Material.BONE);
        ItemMeta meta2 = wbone.getItemMeta();
        meta2.setDisplayName(ChatColor.YELLOW + "Wither Bone");
        meta2.setCustomModelData(2222222);
        wbone.setItemMeta(meta2);

        @SuppressWarnings("deprecation")
        RecipeChoice wibone = new RecipeChoice.ExactChoice(Items.witherBone(this.getConfig()));
        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("   ", "BIB", "N N");
        //recipe.setIngredient('I', Material.BEDROCK);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('B', wibone);
        return recipe;
    }

    @EventHandler
    public void witherArmorBonusThing(EntityDamageByEntityEvent event) {
        //healing
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getDamager();
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
            if (!(event.getCause().equals(DamageCause.WITHER))) {
                if (event.getCause().equals(DamageCause.ENTITY_ATTACK)
                        || event.getCause().equals(DamageCause.ENTITY_EXPLOSION)
                        || event.getCause().equals(DamageCause.ENTITY_SWEEP_ATTACK)
                        || event.getCause().equals(DamageCause.PROJECTILE)) {
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

    public ShapedRecipe jumpElytraRecipe() {

        //test item for double jump

        ItemStack item = new ItemStack(Material.ELYTRA);
        ItemMeta meta = item.getItemMeta();

        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Armor", 3,
                Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        meta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier);

        meta.setDisplayName(ChatColor.YELLOW + "Jump Elytra");
        meta.setCustomModelData(1212121);

        List<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Double Jump"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7- Press jump in midair to jump"));
        lore.add("");

        meta.setLore(lore);

        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "jump_elytra");
        keys.add(key);

        ShapedRecipe recipe = new ShapedRecipe(key, item);
        recipe.shape("NNN", "   ", "   ");
        //recipe.setIngredient('I', Material.BEDROCK);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        return recipe;
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

    public ShapedRecipe getCleaverRecipe() {

        //wood

        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWoodenCleaver.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWoodenCleaver.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWoodenCleaver.line12")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 9
         * spd: 0.4
         */
        double dmg = 8;
        double spd = -3.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aWoodenCleaver.damage") - 1;
            spd = this.getConfig().getDouble("aWoodenCleaver.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dWoodenCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "wooden_cleaver");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" SS", "SS ", "S  ");


        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getGoldCleaverRecipe() {

        //gold

        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dGoldenCleaver.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dGoldenCleaver.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dGoldenCleaver.line12")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 9
         * spd: 0.4
         */
        double dmg = 8;
        double spd = -3.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aGoldenCleaver.damage") - 1;
            spd = this.getConfig().getDouble("aGoldenCleaver.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dGoldenCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "golden_cleaver");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" MM", "MM ", "S  ");

        recipe.setIngredient('M', Material.GOLD_INGOT);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getStoneCleaverRecipe() {

        //stone

        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dStoneCleaver.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dStoneCleaver.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dStoneCleaver.line12")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 10
         * spd: 0.4
         */
        double dmg = 9;
        double spd = -3.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aStoneCleaver.damage") - 1;
            spd = this.getConfig().getDouble("aStoneCleaver.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dStoneCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "stone_cleaver");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" MM", "MM ", "S  ");

        recipe.setIngredient('M', Material.COBBLESTONE);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getICleaverRecipe() {

        //iron

        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dIronCleaver.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dIronCleaver.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dIronCleaver.line12")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 11
         * spd: 0.4
         */
        double dmg = 10;
        double spd = -3.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aIronCleaver.damage") - 1;
            spd = this.getConfig().getDouble("aIronCleaver.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dIronCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "iron_cleaver");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" MM", "MM ", "S  ");

        recipe.setIngredient('M', Material.IRON_INGOT);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getECleaverRecipe() {

        //em

        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dEmeraldCleaver.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dEmeraldCleaver.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dEmeraldCleaver.line12")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 11
         * spd: 0.4
         */
        double dmg = 10;
        double spd = -3.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aEmeraldCleaver.damage") - 1;
            spd = this.getConfig().getDouble("aEmeraldCleaver.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dEmeraldCleaver.name")));
        meta.setCustomModelData(1000031);

        //enchants
        if (this.getConfig().getString("EnchantsOnEmeraldGear") == "true") {
            int num = this.getConfig().getInt("EmeraldGearEnchantLevels.Unbreaking");
            int num2 = this.getConfig().getInt("EmeraldGearEnchantLevels.Mending");
            meta.addEnchant(Enchantment.DURABILITY, num, true);
            meta.addEnchant(Enchantment.MENDING, num2, true);
        }
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "emerald_cleaver");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" MM", "MM ", "S  ");

        recipe.setIngredient('M', Material.EMERALD);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getDCleaverRecipe() {

        //diamond

        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dDiamondCleaver.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dDiamondCleaver.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dDiamondCleaver.line12")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 12
         * spd: 0.4
         */
        double dmg = 11;
        double spd = -3.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aDiamondCleaver.damage") - 1;
            spd = this.getConfig().getDouble("aDiamondCleaver.speed") - 4;
        }

        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dDiamondCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "diamond_cleaver");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" MM", "MM ", "S  ");

        recipe.setIngredient('M', Material.DIAMOND);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getNCleaverRecipe() {

        //netherite

        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("CleaverDescription.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dNetheriteCleaver.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dNetheriteCleaver.line11")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dNetheriteCleaver.line12")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier

        /*actual stats:
         * dmg: 13
         * spd: 0.4
         */
        double dmg = 12;
        double spd = -3.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aNetheriteCleaver.damage") - 1;
            spd = this.getConfig().getDouble("aNetheriteCleaver.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dNetheriteCleaver.name")));
        meta.setCustomModelData(1000021);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "netherite_cleaver");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" MM", "MM ", "S  ");

        recipe.setIngredient('M', Material.NETHERITE_SCRAP);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    @EventHandler
    public void onCleaverDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player p = (Player) event.getDamager();
            if (p.getInventory().getItemInMainHand() == null) {
                return;
            }
            if (!p.getInventory().getItemInMainHand().hasItemMeta()) {
                return;
            }
            if (!p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                return;
            }

            if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000021
                    || p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200021
                    || p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000031) {
                //if player has atk cooldown 60% or more but less than 100%
                if (p.getAttackCooldown() >= 0.6 && p.getAttackCooldown() < 1) {
                    //if player's atk speed less than 1.9 (default 4 for fist, and 0.4 for cleaver)
                    if (p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getValue() < 1.9) {
                        ItemMeta m = p.getInventory().getItemInMainHand().getItemMeta();
                        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", 0.25,
                                Operation.ADD_NUMBER, EquipmentSlot.HAND);
                        m.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
                        p.getInventory().getItemInMainHand().setItemMeta(m);
                    }
                }
                // if player has no atk cooldown (100% charged)
                if (p.getAttackCooldown() == 1) {
                    ItemMeta m = p.getInventory().getItemInMainHand().getItemMeta();
                    m.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
                    AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -3.6,
                            Operation.ADD_NUMBER, EquipmentSlot.HAND);
                    m.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
                    p.getInventory().getItemInMainHand().setItemMeta(m);

                }
            }
        }
    }

    @EventHandler
    public void onKatanaRightClick(PlayerInteractEntityEvent event) {
        //katana test ability
        //idea for this ability came from when i did the texture wrong somehow which made the texture appear sideways when held in offhand
        //
        Player p = event.getPlayer();
        if (p.getInventory().getItemInMainHand().getType() != Material.AIR) {
            if (p.getInventory().getItemInOffHand().getType() == Material.AIR) {
                if (p.getInventory().getItemInMainHand().hasItemMeta()) {
                    if (p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                        if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 205) {
                            Damageable e = (Damageable) event.getRightClicked();
                            ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
                            meta.setCustomModelData(2000002);

                            p.getInventory().getItemInMainHand().setItemMeta(meta);
                            e.damage(4, p);
                            e.setVelocity(e.getLocation().getDirection().setX(0).setY(0.5).setZ(0));
                            World world = p.getWorld();
                            world.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 2);
                            e.getWorld().spawnParticle(Particle.SWEEP_ATTACK, e.getLocation().getX(), e.getLocation().getY() + 1, e.getLocation().getZ(), 3);
                            e.getWorld().spawnParticle(Particle.CRIT, e.getLocation().getX(), e.getLocation().getY() + 1, e.getLocation().getZ(), 6);
                        }
                    }
                }
            }
            return;
        }
        if (p.getInventory().getItemInOffHand().getType() != Material.AIR) {
            if (p.getInventory().getItemInOffHand().hasItemMeta()) {
                if (p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) {

                    if (p.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 205) {
                        //idea is once player hit thing 5 times, katana able do special attack by right click when in off hand
                        //do if cmd=1000002, set to 201
                        //if 201, then set to 202, then etc till 205
                        //oh i need seperate listener for entity dmg by ent event to do above

                        //if cmd = 205, 215, or 225, then do below

                        //placeholder
                        double dmg = 0;
                        if (p.getInventory().getItemInOffHand().getType().equals(Material.DIAMOND_SWORD)) {
                            dmg = 8; //diamond katana dmg is 6, but wanted to make this do some bonus dmg too
                        }
                        if (!(event.getRightClicked() instanceof Damageable)) {
                            return;
                        }
                        Damageable e = (Damageable) event.getRightClicked();
                        ItemMeta meta = p.getInventory().getItemInOffHand().getItemMeta();
                        meta.setCustomModelData(2000002);
                        p.swingOffHand();
                        p.getInventory().getItemInOffHand().setItemMeta(meta);
                        e.damage(dmg, p);
                        e.setVelocity(p.getLocation().getDirection().setY(0.4).multiply(1.5));
                        World world = p.getWorld();

                        world.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10, 1);
                        e.getWorld().spawnParticle(Particle.CRIT, e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), 10);
                        getServer().getScheduler().runTaskLater(this, new Runnable() {
                            public void run() {
                                World world = p.getWorld();
                                world.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                e.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), 1);
                            }
                        }, 2L); //the 2L is ticks, there are 20 ticks in a second so this is 1/10th of a second delay

                        getServer().getScheduler().runTaskLater(this, new Runnable() {
                            public void run() {
                                World world = p.getWorld();
                                world.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                e.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), 1);
                            }
                        }, 4L);

                        getServer().getScheduler().runTaskLater(this, new Runnable() {
                            public void run() {
                                World world = p.getWorld();
                                world.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                e.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), 1);
                            }
                        }, 6L);

                        getServer().getScheduler().runTaskLater(this, new Runnable() {
                            public void run() {
                                World world = p.getWorld();
                                world.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                e.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), 1);
                            }
                        }, 8L);

                        //plan below (if ability on all katana, for now Ill only put it on a test item)
                        //if 1000002 is wood sword, then dmg = dmg of wood, if is stone, the make dmg stone, etc
                        //if 1000012 then set dmg = to dmg of pris
                        //if 1200012 then set dmg = dmg of emer
                        //below and outside the if things, put if entity right clicked instanceof damagable, damageable var = ent, then make ent take damage = to dmg, then do particle and sound

                        //this really complex, could just scrap idea and give this ability to a unique weapon thats one of the base ones
                        //ok yeah ill prob do that because just realized changing cmd to diff num interferes with diff things that need that specific katana cmd like pris upgrade recipe and the two handed bonus

                    }
                }
            }
        }
    }

    @EventHandler
    public void onKatanaDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player p = (Player) event.getDamager();
            if (p.getInventory().getItemInMainHand() == null) {
                return;
            }
            if (!p.getInventory().getItemInMainHand().hasItemMeta()) {
                return;
            }
            if (!p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                return;
            }

            ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
            if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2000002) {
                meta.setCustomModelData(201);
                p.getInventory().getItemInMainHand().setItemMeta(meta);
                return;
            }
            if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 201) {
                meta.setCustomModelData(202);
                p.getInventory().getItemInMainHand().setItemMeta(meta);
                return;
            }
            if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 202) {
                meta.setCustomModelData(203);
                p.getInventory().getItemInMainHand().setItemMeta(meta);
                return;
            }
            if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 203) {
                meta.setCustomModelData(204);
                p.getInventory().getItemInMainHand().setItemMeta(meta);
                return;
            }
            if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 204) {
                World world = p.getWorld();
                world.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 10, 2);
                meta.setCustomModelData(205);
                p.getInventory().getItemInMainHand().setItemMeta(meta);
            }

        }
    }

    public ShapedRecipe getTestKatanaRecipe() {

        //test

        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Charged Strike"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7- Hit 5 times to charge,"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  charge attacks require 2 hands"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6  - Slash"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  In main hand, right click to"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  launch target directly upwards"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9   4 Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6  - Thrust"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  In off hand, right click to"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  launch target further"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9   8 Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7When in Main Hand:"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 6 Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 1.7 Attack Speed"));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -2.3,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 5,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "Diamond Katana v2"));
        meta.setCustomModelData(2000002);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "diamond_katana_test");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("  M", " M ", "S  ");

        recipe.setIngredient('M', Material.DIAMOND);
        recipe.setIngredient('S', Material.BEDROCK);

        return recipe;
    }

    public ShapedRecipe getTestScytheRecipe() {

        //test

        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Hook"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7- Right click opponent to pull"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  them toward you (CD: 3s)"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9   5 Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7When in Main Hand:"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 9 Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 1 Attack Speed"));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -3,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 8,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "Diamond Scythe v2"));
        meta.setCustomModelData(2000003);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "diamond_scythe_test");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("MMM", "  S", "  S");

        recipe.setIngredient('M', Material.DIAMOND);
        recipe.setIngredient('S', Material.BEDROCK);

        return recipe;
    }

    @EventHandler
    public void onScytheRightClick(PlayerInteractEntityEvent event) {
        //scythe test ability
        Player p = event.getPlayer();
        if (p.hasCooldown(Material.DIAMOND_SWORD)) {
            return;
        }
        if (p.getInventory().getItemInMainHand().getType() != Material.AIR) {
            //if (p.getInventory().getItemInOffHand().getType() == Material.AIR) {
            if (p.getInventory().getItemInMainHand().hasItemMeta()) {
                if (p.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    if (p.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2000003) {
                        Damageable e = (Damageable) event.getRightClicked();
                        p.setCooldown(Material.DIAMOND_SWORD, 60);
                        e.damage(5, p);

                        Vector direc = p.getLocation().getDirection().multiply(1);
                        Location loc = p.getLocation().add(direc);
                        Location loca = new Location(p.getWorld(), loc.getX(), loc.getY(), loc.getZ());

                        if (e instanceof LivingEntity) {
                            LivingEntity livingen = (LivingEntity) e;
                            Location l = livingen.getLocation();
                            double x = l.getX() - loca.getX();
                            double y = l.getY() - loca.getY();
                            double z = l.getZ() - loca.getZ();
                            Vector v = new Vector(x, y, z).normalize().multiply(-0.3).setY(0.5);
                            livingen.setVelocity(v);

                        }

                        World world = p.getWorld();
                        world.playSound(p.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 2);
                        e.getWorld().spawnParticle(Particle.SWEEP_ATTACK, e.getLocation().getX(), e.getLocation().getY() + 1, e.getLocation().getZ(), 3);
                        e.getWorld().spawnParticle(Particle.CRIT, e.getLocation().getX(), e.getLocation().getY() + 1, e.getLocation().getZ(), 6);
                    }
                }
            }
            //}
        }
    }

    public ShapedRecipe getTestFishRecipe() {

        //test

        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Fish"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7- In main hand, right click"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  entity"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9   29 Explosion Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7When in Main Hand:"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 6 Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 1.7 Attack Speed"));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -2.3,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 5,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "Fish"));
        meta.setCustomModelData(38);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "fish_test");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("  M", " M ", "S  ");

        recipe.setIngredient('M', Material.SALMON);
        recipe.setIngredient('S', Material.BEDROCK);

        return recipe;
    }

    public ShapedRecipe getWindBladeRecipe() {

        //test

        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Vacuum"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7- Hold right click to pull in entities"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  within a 4 block radius of you"));
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7When in Main Hand:"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 6 Attack Damage"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&9 1.7 Attack Speed"));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", -2.3,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", 5,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "Wind Blade"));
        meta.setCustomModelData(21);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "wind_sword");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("  M", " M ", "S  ");

        recipe.setIngredient('M', Material.BEE_SPAWN_EGG);
        recipe.setIngredient('S', Material.BEDROCK);

        return recipe;
    }

    @EventHandler
    public void wind(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType() != Material.IRON_SWORD) {
            return;
        }
        if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
            return;
        }
        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() != 21) {
            return;
        }
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Vector direc = player.getLocation().getDirection().multiply(3);
            Location loc = player.getLocation().add(direc);
            Location loca = new Location(player.getWorld(), loc.getX(), loc.getY(), loc.getZ());
            player.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, loca.getX(), loca.getY() + 1.6, loca.getZ(), 1);
            List<Entity> ee = player.getNearbyEntities(4, 4, 4);
            for (int en = 0; en < ee.size(); en++) {
                Entity entity = ee.get(en);

                if (entity instanceof LivingEntity) {
                    LivingEntity livingen = (LivingEntity) entity;
                    if (livingen != player) {
                        Location l = livingen.getLocation();
                        double x = l.getX() - loca.getX();
                        double y = l.getY() - loca.getY();
                        double z = l.getZ() - loca.getZ();
                        Vector v = new Vector(x, y, z).normalize().multiply(-0.5);
                        livingen.setVelocity(v);
                        livingen.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20, 5));
                    }
                }
            }

        }
    }

    @EventHandler
    public void onFCharmDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player p = (Player) event.getDamager();
            if (p.getInventory().getItemInOffHand() == null) {
                return;
            }
            if (!p.getInventory().getItemInOffHand().hasItemMeta()) {
                return;
            }
            if (!p.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) {
                return;
            }

            ItemMeta meta = p.getInventory().getItemInOffHand().getItemMeta();
            if (meta.getCustomModelData() == 45) {
                if (event.getEntity() instanceof LivingEntity) {
                    LivingEntity e = (LivingEntity) event.getEntity();
                    e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 0));
                }
            }

        }
    }

    public ShapedRecipe getFlameBladeRecipe() {

        //fire sword

        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicBlade.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicBlade.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicBlade.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicBlade.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicBlade.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicBlade.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicBlade.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicBlade.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicBlade.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicBlade.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicBlade.line11")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier
        double dmg = 7;
        double spd = -2.4;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aVolcanicBlade.damage") - 1;
            spd = this.getConfig().getDouble("aVolcanicBlade.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicBlade.name")));
        meta.setCustomModelData(5000);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "fire_sword");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" M ", " M ", " N ");

        recipe.setIngredient('M', Material.MAGMA_BLOCK);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);

        return recipe;
    }

    public ShapedRecipe getFlameSpearRecipe() {

        //fire spear

        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicSpear.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicSpear.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicSpear.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicSpear.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicSpear.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicSpear.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicSpear.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicSpear.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicSpear.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicSpear.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicSpear.line11")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier
        double dmg = 4;
        double spd = -1.5;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aVolcanicSpear.damage") - 1;
            spd = this.getConfig().getDouble("aVolcanicSpear.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicSpear.name")));
        meta.setCustomModelData(5001);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "fire_spear");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" MM", " NM", "S  ");

        recipe.setIngredient('M', Material.MAGMA_BLOCK);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getFlameAxeRecipe() {

        //fire axe

        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicAxe.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicAxe.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicAxe.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicAxe.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicAxe.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicAxe.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicAxe.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicAxe.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicAxe.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicAxe.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicAxe.line11")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier
        double dmg = 9;
        double spd = -3;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aVolcanicAxe.damage") - 1;
            spd = this.getConfig().getDouble("aVolcanicAxe.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicAxe.name")));
        meta.setCustomModelData(5002);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "fire_axe");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("MM ", "MN ", " S ");

        recipe.setIngredient('M', Material.MAGMA_BLOCK);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
    }

    public ShapedRecipe getFlameCleaverRecipe() {

        //fire cleaver

        ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicCleaver.line1")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicCleaver.line2")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicCleaver.line3")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicCleaver.line4")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicCleaver.line5")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicCleaver.line6")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicCleaver.line7")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicCleaver.line8")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicCleaver.line9")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicCleaver.line10")));
        lore.add(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicCleaver.line11")));
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier
        double dmg = 12;
        double spd = -3.6;
        if (this.getConfig().getString("UseCustomValues") == "true") {
            dmg = this.getConfig().getDouble("aVolcanicCleaver.damage") - 1;
            spd = this.getConfig().getDouble("aVolcanicCleaver.speed") - 4;
        }
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "Attack Speed", spd,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(UUID.randomUUID(), "Attack Damage", dmg,
                Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dVolcanicCleaver.name")));
        meta.setCustomModelData(5003);
        item.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "fire_cleaver");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" MM", "MNM", "SM ");

        recipe.setIngredient('M', Material.MAGMA_BLOCK);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('S', Material.STICK);

        return recipe;
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
                Damageable ent = (Damageable) event.getEntity();

                if (event.getEntity() instanceof LivingEntity) {
                    LivingEntity e = (LivingEntity) event.getEntity();
                    e.setFireTicks(100);
                    player.getWorld().playSound(ent.getLocation(), Sound.ITEM_FIRECHARGE_USE, 10, 1);
                    //below finds other entities 1 block near, except for the player
                    List<Entity> ee = e.getNearbyEntities(1, 1, 1);
                    for (int en = 0; en < ee.size(); en++) {
                        Entity entity = ee.get(en);

                        if (entity instanceof LivingEntity) {
                            LivingEntity livingen = (LivingEntity) entity;
                            if (livingen != player) {

                                livingen.damage(5);
                                player.getWorld().playSound(ent.getLocation(), Sound.ITEM_FIRECHARGE_USE, 10, 1);
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

                    meta.setCustomModelData(50);
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5001) {

                    meta.setCustomModelData(51);
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5002) {

                    meta.setCustomModelData(52);
                }
                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5003) {

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

    @EventHandler
    public void damageMultipliers(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (player.getInventory().getItemInMainHand() == null) {
                return;
            }
            if (player.getInventory().getItemInMainHand().getType() != Material.WOODEN_SWORD
                    && player.getInventory().getItemInMainHand().getType() != Material.GOLDEN_SWORD
                    && player.getInventory().getItemInMainHand().getType() != Material.STONE_SWORD
                    && player.getInventory().getItemInMainHand().getType() != Material.IRON_SWORD
                    && player.getInventory().getItemInMainHand().getType() != Material.DIAMOND_SWORD
                    && player.getInventory().getItemInMainHand().getType() != Material.NETHERITE_SWORD) {
                return;
            }
            if (!(player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData())) {
                return;
            }
            double dmg = event.getDamage();

            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000006
                    || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000016
                    || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200006) {
                double m = this.getConfig().getDouble("mKnives");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000005
                    || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000015
                    || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200005) {
                double m = this.getConfig().getDouble("mRapiers");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000002
                    || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000012
                    || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200002) {
                double m = this.getConfig().getDouble("mKatanas");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000003
                    || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000013
                    || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200003) {
                double m = this.getConfig().getDouble("mScythes");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000001
                    || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000011
                    || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200001) {
                double m = this.getConfig().getDouble("mLongswords");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000004
                    || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000014
                    || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200004) {
                double m = this.getConfig().getDouble("mSpears");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000010
                    || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000030
                    || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200010) {
                double m = this.getConfig().getDouble("mSabers");
                event.setDamage(dmg * m);
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000021
                    || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1000031
                    || player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 1200021) {
                double m = this.getConfig().getDouble("mCleavers");
                event.setDamage(dmg * m);
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

        if (this.getConfig().getString("ShieldParry") != "true") {
            return;
        }


        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if (p.hasCooldown(Material.SHIELD)) {
                //p.sendMessage("missed block");
                return;
            }
            if (p.getInventory().getItemInOffHand().getType() != Material.SHIELD) {
                return;
            }

            if (p.hasMetadata("test")) {
                List<MetadataValue> values = p.getMetadata("test");
                if (values.isEmpty()) {
                    return;
                }
                if (p.getMetadata("test").get(0).value().equals(1)) {
                    event.setCancelled(true);
                    //p.sendMessage("perfect parry");
                    if (p.hasMetadata("test")) {
                        p.removeMetadata("test", this);
                    }
                    p.setNoDamageTicks(10);
                    p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 10, 2);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 0));

                    ItemStack item = p.getInventory().getItemInOffHand();
                    if (p.hasCooldown(Material.SHIELD)) {
                        return;
                    }

                    p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                    Bukkit.getScheduler().runTaskLater(this, () -> {

                        p.getInventory().setItemInOffHand(item);
                        p.setCooldown(Material.SHIELD, 2);

                    }, 1L); // Time in ticks (20 ticks = 1 second)

                    return;
                }
                if (values.isEmpty()) {
                    return;
                }
                if (p.getMetadata("test").get(0).value().equals(2)) {
                    event.setCancelled(true);
                    //p.sendMessage("late parry");
                    if (p.hasMetadata("test")) {
                        p.removeMetadata("test", this);
                    }
                    p.getWorld().playSound(p.getLocation(), Sound.ITEM_SHIELD_BREAK, 10, 2);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20, 0));
                    ItemStack item = p.getInventory().getItemInOffHand();
                    if (p.hasCooldown(Material.SHIELD)) {
                        return;
                    }

                    p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                    Bukkit.getScheduler().runTaskLater(this, () -> {

                        p.getInventory().setItemInOffHand(item);
                        p.setCooldown(Material.SHIELD, 15);

                    }, 1L); // Time in ticks (20 ticks = 1 second)

                    return;
                }
            }
            if (p.isBlocking()) {
                //p.sendMessage("blocked");

                ItemStack item = p.getInventory().getItemInOffHand();
                if (p.hasCooldown(Material.SHIELD)) {
                    return;
                }

                p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                Bukkit.getScheduler().runTaskLater(this, () -> {

                    p.getInventory().setItemInOffHand(item);
                    p.setCooldown(Material.SHIELD, 20);

                }, 1L); // Time in ticks (20 ticks = 1 second)
            }

        }
    }

    @EventHandler
    public void shieldBlock(PlayerInteractEvent event) {

        if (this.getConfig().getString("ShieldParry") != "true") {
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
                                spawnParticlesAlongPath(player, start, 20, Particle.CRIT_MAGIC, 100, 0.1);

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
                            spawnParticlesAlongPath(player, start, 60, Particle.CRIT, 100, 0.1);

                        }
                    }
                }

            }
        }
    }

    private void spawnParticlesAlongPath(Player player, Vector start, double distance, Particle particle, int particleCount, double particleSpacing) {
        Vector direction = player.getLocation().getDirection().normalize();
        Vector end = start.clone().add(direction.multiply(distance));

        Vector particleOffset = direction.clone().multiply(particleSpacing);

        for (int i = 0; i < particleCount; i++) {
            Location particleLocation = start.toLocation(player.getWorld());
            particleLocation.getWorld().spawnParticle(particle, particleLocation, 1);

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


    public ShapedRecipe getExStaffRecipe() {

        //test

        ItemStack item = new ItemStack(Material.CROSSBOW);
        ItemMeta meta = item.getItemMeta();

        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&6Explosion"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7- Right click to create an explosion in the"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  direction you are facing"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7- The created explosion is able to"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7  launch nearby entities, including arrows"));
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
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
    }

    @EventHandler
    public void explosion(EntityShootBowEvent event) {
        // Check if the entity shooting the bow is a player
        if (event.getEntityType() == EntityType.PLAYER) {
            // Check if the bow being used has the desired CustomModelData
            ItemStack bow = event.getBow();
            if (bow.getItemMeta() != null && bow.getItemMeta().hasCustomModelData() && bow.getItemMeta().getCustomModelData() == 22) {

                //remove the arrow
                event.getProjectile().remove();
                Player player = (Player) event.getEntity();

                // Check if the player is holding a crossbow
                ItemStack item = player.getInventory().getItemInMainHand();
                if (item.getType() == Material.CROSSBOW) {
                    Vector direc = player.getLocation().getDirection().multiply(2.5);
                    Location loc = player.getLocation().add(direc);
                    Location loca = new Location(player.getWorld(), loc.getX(), loc.getY(), loc.getZ());
                    player.getWorld().createExplosion(loca, 2.0f, false, false);
                    Collection<Entity> nearent = player.getWorld().getNearbyEntities(loca, 3, 3, 3);
                    for (Entity entity : nearent) {
                        if (entity instanceof Arrow) {
                            Arrow nearbyArrow = (Arrow) entity;

                            // Apply a velocity to the nearby arrow
                            Vector explosionDirection = nearbyArrow.getLocation().subtract(loca).toVector().normalize();
                            double explosionForce = 4.0; // Adjust the explosion force as needed
                            Vector velocity = explosionDirection.multiply(explosionForce);
                            nearbyArrow.setVelocity(velocity.setY(0));
                            //nearbyArrow.setGravity(false);
                            //nearbyArrow.setVelocity(new Vector(0,0,0));
                            nearbyArrow.setDamage(nearbyArrow.getDamage() * 3);

                            nearbyArrow.getWorld().playSound(loca, Sound.BLOCK_ANVIL_LAND, 10, 2);
                            nearbyArrow.getWorld().spawnParticle(Particle.CRIT_MAGIC, loca, 10);

                 		/*Bukkit.getScheduler().runTaskLater(this, () -> {
                 			nearbyArrow.setVelocity(velocity.setY(0));

                         	}, 5L);
                         */
                        } else {
                            // Apply a velocity to the nearby entity
                            Vector explosionDirection = entity.getLocation().subtract(loca).toVector().normalize();
                            double explosionForce = 3.0; // Adjust the explosion force as needed
                            Vector velocity = explosionDirection.multiply(explosionForce);
                            entity.setVelocity(velocity);
                        }
                    }

                }

            }
        }
    }



    public ShapedRecipe realtestrecipe() {

        //test
        NamespacedKey key = new NamespacedKey(this, "rrrreal");
        keys.add(key);
        ShapedRecipe recipe = new ShapedRecipe(key, Items.itemname());

        recipe.shape(" B ", " B ", " A ");

        recipe.setIngredient('B', Material.GOLD_INGOT);
        recipe.setIngredient('A', Material.DIAMOND);

        return recipe;
    }
}
