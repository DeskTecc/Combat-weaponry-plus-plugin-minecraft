package me.helleo.cwp;
//Message: there's a lot of code and stuff with // before it, thats because those were things that were either there in a previous version, there to be tested, or didnt work and i didnt delete them for some reason
// also sorry if a lot of this stuff is messy, i dont really know what things are considered messy or not
// but it all works
// ctrl + f is very useful for finding things


import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsDouble;
import me.helleo.cwp.configurations.ConfigurationsString;
import me.helleo.cwp.items.armors.*;
import me.helleo.cwp.items.charms.*;
import me.helleo.cwp.items.misc.*;
import me.helleo.cwp.items.shields.DiamondShield;
import me.helleo.cwp.items.shields.NetheriteShield;
import me.helleo.cwp.items.tools.*;
import me.helleo.cwp.items.weapons.bows.*;
import me.helleo.cwp.items.weapons.cleavers.*;
import me.helleo.cwp.items.weapons.katanas.*;
import me.helleo.cwp.items.weapons.knives.*;
import me.helleo.cwp.items.weapons.longswords.*;
import me.helleo.cwp.items.weapons.misc.*;
import me.helleo.cwp.items.weapons.rapiers.*;
import me.helleo.cwp.items.weapons.sabers.*;
import me.helleo.cwp.items.weapons.scythes.*;
import me.helleo.cwp.items.weapons.spears.*;
import me.helleo.cwp.listeners.EntityDamage;
import me.helleo.cwp.listeners.PlayerClick;
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

    public static Plugin plugin;


    public static List<NamespacedKey> keys = new ArrayList<>();

    @Override
    public void onEnable() {
        plugin = this;
        Cooldown.setupCooldown();

        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new PlayerClick(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamage(), this);
        this.saveDefaultConfig();


        DragonBreath.setItemRecipe();

        //ARMORS
        if (ConfigurationsBool.Chainmail.getValue()) {
            ChainmailHelmet.setArmorPieceRecipe();
            ChainmailChestplate.setArmorPieceRecipe();
            ChainmailLeggings.setArmorPieceRecipe();
            ChainmailBoots.setArmorPieceRecipe();
        }

        if (ConfigurationsBool.PlatedChainmail.getValue()) {
            PlatedChainmailHelmet.setArmorPieceRecipe();
            PlatedChainmailChestplate.setArmorPieceRecipe();
            PlatedChainmailLeggings.setArmorPieceRecipe();
            PlatedChainmailBoots.setArmorPieceRecipe();
        }

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


        //CHARMS
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

        //BOWS
        if (ConfigurationsBool.Longbow.getValue()) {
            LongBow.setBowRecipe();
        }
        if (ConfigurationsBool.Recurvebow.getValue()) {
            RecurveBow.setBowRecipe();
        }
        if (ConfigurationsBool.Compoundbow.getValue()) {
            CompoundBow.setBowRecipe();
        }
        if (ConfigurationsBool.SwordBow.getValue()) {
            SwordBow.setBowRecipe();
        }
        if (ConfigurationsBool.HeavySwordBow.getValue()) {
            HeavySwordBow.setBowRecipe();
        }
        if (ConfigurationsBool.LongswordBow.getValue()) {
            LongswordBow.setBowRecipe();
        }
        if (ConfigurationsBool.RedstoneBow.getValue()) {
            RedstoneBow.setBowRecipe();
        }
        if (ConfigurationsBool.RepeatingCrossbow.getValue()) {
            RepeatingCrossbow.setBowRecipe();
        }
        if (ConfigurationsBool.BurstCrossbow.getValue()) {
            BurstCrossbow.setBowRecipe();
        }

        //CLEAVERS
        if (ConfigurationsBool.Cleavers.getValue()) {
            WoodenCleaver.setCleaverRecipe();
            StoneCleaver.setCleaverRecipe();
            GoldenCleaver.setCleaverRecipe();
            IronCleaver.setCleaverRecipe();
            DiamondCleaver.setCleaverRecipe();
            NetheriteCleaver.setCleaverRecipe();
            if(ConfigurationsBool.EmeraldGear.getValue()){
                EmeraldCleaver.setCleaverRecipe();
            }
        }

        //KATANAS
        if (ConfigurationsBool.Katanas.getValue()) {
            WoodenKatana.setKatanaRecipe();
            StoneKatana.setKatanaRecipe();
            GoldenKatana.setKatanaRecipe();
            IronKatana.setKatanaRecipe();
            DiamondKatana.setKatanaRecipe();
            NetheriteKatana.setKatanaRecipe();
            if(ConfigurationsBool.EmeraldGear.getValue()){
                EmeraldKatana.setKatanaRecipe();
            }
        }

        //KNIVES
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

        //LONGSWORDS
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

        //RAPIERS
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

        //SABERS
        if (ConfigurationsBool.Sabers.getValue()) {
            WoodenSaber.setSaberRecipe();
            StoneSaber.setSaberRecipe();
            GoldenSaber.setSaberRecipe();
            IronSaber.setSaberRecipe();
            DiamondSaber.setSaberRecipe();
            NetheriteSaber.setSaberRecipe();
            if(ConfigurationsBool.EmeraldGear.getValue()){
                EmeraldSaber.setSaberRecipe();
            }
        }

        //SCYTHES
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

        //SPEARS
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

        //SHIELDS
        if (ConfigurationsBool.DiamondShield.getValue()) {
            DiamondShield.setShieldRecipe();
        }
        if (ConfigurationsBool.NetheriteShield.getValue()) {
            NetheriteShield.setShieldRecipe();
        }

        //MISC
        if (ConfigurationsBool.ChorusBlade.getValue()) {
            ChorusBlade.setToolRecipe();
        }

        if (ConfigurationsBool.ObsidianPickaxe.getValue()) {
            ObsidianPickaxe.setToolRecipe();
        }

        //PRISMARINE ITEMS
        //temporary disabled
       /* if (ConfigurationsBool.Prismarine.getValue()) {
            PrismarineAlloy.setItemRecipe();

            Bukkit.addRecipe(getprisswordsrecipe());
            Bukkit.addRecipe(getprispickrecipe());
            Bukkit.addRecipe(getprisaxerecipe());
            Bukkit.addRecipe(getprisshovelrecipe());
            Bukkit.addRecipe(getprishoerecipe());
            Bukkit.addRecipe(getprishelmetrecipe());
            Bukkit.addRecipe(getprischestrecipe());
            Bukkit.addRecipe(getprislegrecipe());
            Bukkit.addRecipe(getprisbootsrecipe());
        }*/
        if (ConfigurationsBool.Eelytra.getValue()) {
            Eelytra.setItemRecipe();
        }

        if (ConfigurationsBool.ReallyGoodSword.getValue()) {
            ReallyGoodSword.setItemRecipe();
        }

        if (ConfigurationsBool.RedstoneCore.getValue()) {
            RedstoneCore.setItemRecipe();
        }

        if (ConfigurationsBool.TridentBow.getValue()) {
            TridentBow.setBowRecipe();
        }

        if (ConfigurationsBool.WitherArmor.getValue()) {
            WitherHelmet.setArmorRecipe();
            WitherChestplate.setArmorRecipe();
            WitherLeggings.setArmorRecipe();
            WitherBoots.setArmorRecipe();
        }
        if (ConfigurationsBool.JumpElytra.getValue()) {
            JumpElytra.setItemRecipe();
        }
        if (ConfigurationsBool.TestKatana.getValue()) {
            TestKatana.setItemRecipe();
        }
        if (ConfigurationsBool.TestScythe.getValue()) {
            TestScythe.setItemRecipe();
        }

        if (ConfigurationsBool.FishSword.getValue()) {
            FishSword.setItemRecipe();
        }
        if (ConfigurationsBool.WindBlade.getValue()) {
            WindBlade.setItemRecipe();
        }
        if (ConfigurationsBool.VolcanicBlade.getValue()) {
            VolcanicBlade.setItemRecipe();
        }
        if (ConfigurationsBool.VolcanicSpear.getValue()) {
            VolcanicSpear.setItemRecipe();
        }
        if (ConfigurationsBool.VolcanicAxe.getValue()) {
            VolcanicAxe.setItemRecipe();
        }
        if (ConfigurationsBool.VolcanicCleaver.getValue()) {
            VolcanicCleaver.setItemRecipe();
        }
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (ConfigurationsBool.ResourcePack.getValue()) {
            player.setResourcePack(ConfigurationsString.PackLink.getValue());
        }
        player.discoverRecipes(keys);
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



    //CHARMS




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
        assert resultm != null;
        resultm.setCustomModelData(1200003);
        //added damage (default is +1)
        double dmg = 1;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Scythes_PrismarineScythe_DamageAdded.getValue();
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineScythe_Name.getValue()));
        AttributeModifier modifier1 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_damage"), dmg,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionScythe_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionScythe_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionScythe_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionScythe_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionScythe_Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionScythe_Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionScythe_Line7.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineScythe_Line8.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineScythe_Line9.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineScythe_Line10.getValue()));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (ConfigurationsBool.Prismarine.getValue()) {
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
        assert resultm != null;
        resultm.setCustomModelData(1200005);

        double dmg = 1;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Rapiers_PrismarineRapier_DamageAdded.getValue();
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineRapier_Name.getValue()));
        AttributeModifier modifier1 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_damage"), dmg,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRapier_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRapier_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRapier_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRapier_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRapier_Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRapier_Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionRapier_Line7.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineRapier_Line8.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineRapier_Line9.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineRapier_Line10.getValue()));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (ConfigurationsBool.Prismarine.getValue()) {
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
        assert resultm != null;
        resultm.setCustomModelData(1200004);

        double dmg = 1;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Spears_PrismarineSpear_DamageAdded.getValue();
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineSpear_Name.getValue()));
        AttributeModifier modifier1 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_damage"), dmg,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionSpear_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionSpear_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionSpear_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionSpear_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionSpear_Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionSpear_Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionSpear_Line7.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionSpear_Line8.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionSpear_Line9.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineSpear_Line10.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineSpear_Line11.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineSpear_Line12.getValue()));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (ConfigurationsBool.Prismarine.getValue()) {
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
        assert resultm != null;
        resultm.setCustomModelData(1200002);

        double dmg = 1;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Katanas_PrismarineKatana_DamageAdded.getValue();
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineKatana_Name.getValue()));
        AttributeModifier modifier1 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_damage"), dmg,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionKatana_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionKatana_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionKatana_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionKatana_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionKatana_Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionKatana_Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionKatana_Line7.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionKatana_Line8.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionKatana_Line9.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionKatana_Line10.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionKatana_Line11.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineKatana_Line12.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineKatana_Line13.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineKatana_Line14.getValue()));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (ConfigurationsBool.Prismarine.getValue()) {
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
        assert resultm != null;
        resultm.setCustomModelData(1200006);

        double dmg = 1;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Knives_PrismarineKnife_DamageAdded.getValue();
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineKnife_Name.getValue()));
        AttributeModifier modifier1 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_damage"), dmg,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionKnife_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionKnife_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionKnife_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionKnife_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionKnife_Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionKnife_Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineKnife_Line7.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineKnife_Line8.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineKnife_Line9.getValue()));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (ConfigurationsBool.Prismarine.getValue()) {
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
        assert resultm != null;
        resultm.setCustomModelData(1200010);

        double dmg = 1;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Sabers_PrismarineSaber_DamageAdded.getValue();
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineSaber_Name.getValue()));
        AttributeModifier modifier1 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_damage"), dmg,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionSaber_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionSaber_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionSaber_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionSaber_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineSaber_Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineSaber_Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineSaber_Line7.getValue()));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (ConfigurationsBool.Prismarine.getValue()) {
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
        assert resultm != null;
        resultm.setCustomModelData(1200021);

        double dmg = 1;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Cleavers_PrismarineCleaver_DamageAdded.getValue();
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineCleaver_Name.getValue()));
        AttributeModifier modifier1 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_damage"), dmg,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCleaver_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCleaver_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCleaver_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCleaver_Line4.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCleaver_Line5.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCleaver_Line6.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCleaver_Line7.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCleaver_Line8.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionCleaver_Line9.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineCleaver_Line10.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineCleaver_Line11.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineCleaver_Line12.getValue()));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (ConfigurationsBool.Prismarine.getValue()) {
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
        assert resultm != null;
        resultm.setCustomModelData(1210002);
        double dmg = 6;
        double spd = -2.8;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Others_PrismarinePickaxe_Damage.getValue();
            spd = ConfigurationsDouble.Others_PrismarinePickaxe_Speed.getValue();
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarinePickaxe_Name.getValue()));
        AttributeModifier modifier1 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_damage"), dmg,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_speed"), spd,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2);

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarinePickaxe_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarinePickaxe_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarinePickaxe_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarinePickaxe_Line4.getValue()));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (ConfigurationsBool.Prismarine.getValue()) {
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
        assert resultm != null;
        resultm.setCustomModelData(1220001);
        double dmg = 10;
        double spd = -3;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Others_PrismarineAxe_Damage.getValue();
            spd = ConfigurationsDouble.Others_PrismarineAxe_Speed.getValue();
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineAxe_Name.getValue()));
        AttributeModifier modifier1 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_damage"), dmg,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(NamespacedKey.minecraft("generic.atack_speed"), spd,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2);

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineAxe_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineAxe_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineAxe_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineAxe_Line4.getValue()));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (ConfigurationsBool.Prismarine.getValue()) {
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
        assert resultm != null;
        resultm.setCustomModelData(1210004);
        double dmg = 6.5;
        double spd = -3;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Others_PrismarineShovel_Damage.getValue();
            spd = ConfigurationsDouble.Others_PrismarineShovel_Speed.getValue();
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineShovel_Name.getValue()));
        AttributeModifier modifier1 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_damage"), dmg,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_speed"), spd,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2);

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineShovel_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineShovel_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineShovel_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineShovel_Line4.getValue()));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (ConfigurationsBool.Prismarine.getValue()) {
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
        assert resultm != null;
        resultm.setCustomModelData(1210005);
        double dmg = 1;
        double spd = 0;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            dmg = ConfigurationsDouble.Others_PrismarineHoe_Damage.getValue();
            spd = ConfigurationsDouble.Others_PrismarineHoe_Speed.getValue();
        }
        resultm.setDisplayName(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineHoe_Name.getValue()));
        AttributeModifier modifier1 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_damage"), dmg,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_speed"), spd,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        resultm.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier2);


        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineHoe_Line1.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineHoe_Line2.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineHoe_Line3.getValue()));
        lore.add(ChatColor.translateAlternateColorCodes('&', ConfigurationsString.DescriptionPrismarineHoe_Line4.getValue()));
        resultm.setLore(lore);
        //important:
        resultm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        result.setItemMeta(resultm);

        if (ConfigurationsBool.Prismarine.getValue()) {
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
        assert resultm != null;
        resultm.setCustomModelData(1220001);
        double arm = 4;
        double armt = 3;
        double kbr = 0.1;
        double hp = 1;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            arm = ConfigurationsDouble.Armors_PrismarineHelmet_Armor.getValue();
            armt = ConfigurationsDouble.Armors_PrismarineHelmet_ArmorToughness.getValue();
            kbr = ConfigurationsDouble.Armors_PrismarineHelmet_KBResist.getValue() / 10;
            hp = ConfigurationsDouble.Armors_PrismarineHelmet_BonusHealth.getValue();
        }
        resultm.setDisplayName(ChatColor.GREEN + "Prismarine Helmet");
        AttributeModifier modifier1 = new AttributeModifier(NamespacedKey.minecraft("generic.armor"), arm,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);
        resultm.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(NamespacedKey.minecraft("generic.armor_toughness"), armt,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);
        resultm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(NamespacedKey.minecraft("generic.armor_knockback_resistance"), kbr,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);
        resultm.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifier3);
        AttributeModifier modifier4 = new AttributeModifier(NamespacedKey.minecraft("generic.armor_max_health"), hp,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);
        resultm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier4);

        result.setItemMeta(resultm);

        if (ConfigurationsBool.Prismarine.getValue()) {
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
        assert resultm != null;
        resultm.setCustomModelData(1220002);
        double arm = 9;
        double armt = 3;
        double kbr = 0.1;
        double hp = 2;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            arm = ConfigurationsDouble.Armors_PrismarineChestplate_Armor.getValue();
            armt = ConfigurationsDouble.Armors_PrismarineChestplate_ArmorToughness.getValue();
            kbr = ConfigurationsDouble.Armors_PrismarineChestplate_KBResist.getValue() / 10;
            hp = ConfigurationsDouble.Armors_PrismarineChestplate_BonusHealth.getValue();
        }
        resultm.setDisplayName(ChatColor.GREEN + "Prismarine Chestplate");
        AttributeModifier modifier1 = new AttributeModifier(NamespacedKey.minecraft("generic.armor"), arm,
                Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);
        resultm.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(NamespacedKey.minecraft("generic.armor_toughness"), armt,
                Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);
        resultm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(NamespacedKey.minecraft("generic.armor_knockback_resistance"), kbr,
                Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);
        resultm.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifier3);
        AttributeModifier modifier4 = new AttributeModifier(NamespacedKey.minecraft("generic.armor_max_health"), hp,
                Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);
        resultm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier4);
        result.setItemMeta(resultm);

        if (ConfigurationsBool.Prismarine.getValue()) {
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
        assert resultm != null;
        resultm.setCustomModelData(1220003);
        double arm = 7;
        double armt = 3;
        double kbr = 0.1;
        double hp = 2;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            arm = ConfigurationsDouble.Armors_PrismarineLeggings_Armor.getValue();
            armt = ConfigurationsDouble.Armors_PrismarineLeggings_ArmorToughness.getValue();
            kbr = ConfigurationsDouble.Armors_PrismarineLeggings_KBResist.getValue() / 10;
            hp = ConfigurationsDouble.Armors_PrismarineLeggings_BonusHealth.getValue();
        }
        resultm.setDisplayName(ChatColor.GREEN + "Prismarine Leggings");
        AttributeModifier modifier1 = new AttributeModifier(NamespacedKey.minecraft("generic.armor"), arm,
                Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);
        resultm.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(NamespacedKey.minecraft("generic.armor_toughness"), armt,
                Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);
        resultm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(NamespacedKey.minecraft("generic.armor_knockback_resistance"), kbr,
                Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);
        resultm.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifier3);
        AttributeModifier modifier4 = new AttributeModifier(NamespacedKey.minecraft("generic.armor_max_health"), hp,
                Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);
        resultm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier4);
        result.setItemMeta(resultm);

        if (ConfigurationsBool.Prismarine.getValue()) {
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
        assert resultm != null;
        resultm.setCustomModelData(1220004);
        double arm = 4;
        double armt = 3;
        double kbr = 0.1;
        double hp = 1;
        if (ConfigurationsBool.UseCustomValues.getValue()) {
            arm = ConfigurationsDouble.Armors_PrismarineBoots_Armor.getValue();
            armt = ConfigurationsDouble.Armors_PrismarineBoots_ArmorToughness.getValue();
            kbr = ConfigurationsDouble.Armors_PrismarineBoots_KBResist.getValue() / 10;
            hp = ConfigurationsDouble.Armors_PrismarineBoots_BonusHealth.getValue();
        }
        resultm.setDisplayName(ChatColor.GREEN + "Prismarine Boots");
        AttributeModifier modifier1 = new AttributeModifier(NamespacedKey.minecraft("generic.armor"), arm,
                Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);
        resultm.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(NamespacedKey.minecraft("generic.armor_toughness"), armt,
                Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);
        resultm.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, modifier2);
        AttributeModifier modifier3 = new AttributeModifier(NamespacedKey.minecraft("generic.armor_knockback_resistance"), kbr,
                Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);
        resultm.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, modifier3);
        AttributeModifier modifier4 = new AttributeModifier(NamespacedKey.minecraft("generic.armor_max_health"), hp,
                Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);
        resultm.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifier4);
        result.setItemMeta(resultm);

        if (ConfigurationsBool.Prismarine.getValue()) {
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
        assert meta != null;
        meta.setLore(lore);
        //important:
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        //modifier
        AttributeModifier modifier = new AttributeModifier(NamespacedKey.minecraft("generic.attack_speed"), -2.2,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
        AttributeModifier modifier2 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_damage"), 3,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
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

                        if (player.getInventory().getChestplate().getItemMeta().hasCustomModelData()) {
                            if (player.getInventory().getChestplate().getItemMeta().getCustomModelData() != 1231234) {

                                if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552001 &&
                                        player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
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
            if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                if (player.getInventory().getChestplate() != null) {


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

                                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 5552002 &&
                                                player.getInventory().getItemInOffHand().getType() == Material.REDSTONE) {
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
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_speed"), spd,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2);

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
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier1);
        AttributeModifier modifier2 = new AttributeModifier(NamespacedKey.minecraft("generic.attack_speed"), spd,
                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier2);

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

    //DUAL WIELDING
    @EventHandler
    public void onRightClickEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (event.getHand().equals(EquipmentSlot.HAND)) {
            if (!player.getInventory().getItemInOffHand().getType().equals(Material.AIR)) {
                if (player.getInventory().getItemInOffHand().hasItemMeta()) {
                    if (player.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) {
                        if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000010
                                || player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1200010
                                || player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 1000030) {

                            //stops dual wielding 2 different weapon type:
                            if (ConfigurationsBool.DualWieldSaberOnly.getValue()) {
                                //test the config thing, not sure if it works
                                if (!itemInHand.getType().equals(Material.AIR)) {
                                    if (itemInHand.hasItemMeta()) {
                                        if (itemInHand.getType().equals(Material.WOODEN_SWORD)) {
                                            assert itemInHand.getItemMeta() != null;
                                            if (itemInHand.getItemMeta().hasCustomModelData()) {
                                                if (itemInHand.getItemMeta().getCustomModelData() != 1000010
                                                        && itemInHand.getItemMeta().getCustomModelData() != 1200010
                                                        && itemInHand.getItemMeta().getCustomModelData() != 1000030) {
                                                    return;
                                                }
                                            }
                                            if (!itemInHand.getItemMeta().hasCustomModelData()) {
                                                return;
                                            }
                                        }
                                        if (itemInHand.getType().equals(Material.STONE_SWORD)) {
                                            assert itemInHand.getItemMeta() != null;
                                            if (itemInHand.getItemMeta().hasCustomModelData()) {
                                                if (itemInHand.getItemMeta().getCustomModelData() != 1000010
                                                        && itemInHand.getItemMeta().getCustomModelData() != 1200010
                                                        && itemInHand.getItemMeta().getCustomModelData() != 1000030) {
                                                    return;
                                                }
                                            }
                                            if (!itemInHand.getItemMeta().hasCustomModelData()) {
                                                return;
                                            }
                                        }
                                        if (itemInHand.getType().equals(Material.GOLDEN_SWORD)) {
                                            assert itemInHand.getItemMeta() != null;
                                            if (itemInHand.getItemMeta().hasCustomModelData()) {
                                                if (itemInHand.getItemMeta().getCustomModelData() != 1000010
                                                        && itemInHand.getItemMeta().getCustomModelData() != 1200010
                                                        && itemInHand.getItemMeta().getCustomModelData() != 1000030) {
                                                    return;
                                                }
                                            }
                                            if (!itemInHand.getItemMeta().hasCustomModelData()) {
                                                return;
                                            }
                                        }
                                        if (itemInHand.getType().equals(Material.IRON_SWORD)) {
                                            assert itemInHand.getItemMeta() != null;
                                            if (itemInHand.getItemMeta().hasCustomModelData()) {
                                                if (itemInHand.getItemMeta().getCustomModelData() != 1000010
                                                        && itemInHand.getItemMeta().getCustomModelData() != 1200010
                                                        && itemInHand.getItemMeta().getCustomModelData() != 1000030) {
                                                    return;
                                                }
                                            }
                                            if (!itemInHand.getItemMeta().hasCustomModelData()) {
                                                return;
                                            }
                                        }
                                        if (itemInHand.getType().equals(Material.DIAMOND_SWORD)) {
                                            assert itemInHand.getItemMeta() != null;
                                            if (itemInHand.getItemMeta().hasCustomModelData()) {
                                                if (itemInHand.getItemMeta().getCustomModelData() != 1000010
                                                        && itemInHand.getItemMeta().getCustomModelData() != 1200010
                                                        && itemInHand.getItemMeta().getCustomModelData() != 1000030) {
                                                    return;
                                                }
                                            }
                                            if (!itemInHand.getItemMeta().hasCustomModelData()) {
                                                return;
                                            }
                                        }
                                        if (itemInHand.getType().equals(Material.NETHERITE_SWORD)) {
                                            assert itemInHand.getItemMeta() != null;
                                            if (itemInHand.getItemMeta().hasCustomModelData()) {
                                                if (itemInHand.getItemMeta().getCustomModelData() != 1000010
                                                        && itemInHand.getItemMeta().getCustomModelData() != 1200010
                                                        && itemInHand.getItemMeta().getCustomModelData() != 1000030) {
                                                    return;
                                                }
                                            }
                                            if (!itemInHand.getItemMeta().hasCustomModelData()) {
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
                e.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 5, 10));
                e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 5, 10));
                //below gives other entities near target slowness too, except for the player
                List<Entity> nearbyEntities = e.getNearbyEntities(2, 2, 2);
                for (Entity entity : nearbyEntities) {
                    if (entity instanceof LivingEntity) {
                        LivingEntity livingen = (LivingEntity) entity;
                        if (livingen != player) {
                            livingen.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 5, 10));
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
            assert meta != null;
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
            }, 0);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            }, 1);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            }, 1);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            }, 2);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            }, 2);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            }, 3);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            }, 3);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            }, 4);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
            },4);
            Bukkit.getScheduler().runTaskLater(this, () -> {
                stand.remove();
                standd.remove();
                player.getWorld().playSound(ent.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 4);
                player.getWorld().createExplosion(ent.getLocation(), 2, false, false, player);
            }, 5); // Time in ticks (20 ticks = 1 second)

        }

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
    public void onCleaverDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player p = (Player) event.getDamager();
            if (p.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
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
                        AttributeModifier modifier = new AttributeModifier(new NamespacedKey (this, "generic.attack_speed"), 0.25,
                                Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
                        m.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, modifier);
                        p.getInventory().getItemInMainHand().setItemMeta(m);
                    }
                }
                // if player has no atk cooldown (100% charged)
                if (p.getAttackCooldown() == 1) {
                    ItemMeta m = p.getInventory().getItemInMainHand().getItemMeta();
                    m.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
                    AttributeModifier modifier = new AttributeModifier(new NamespacedKey(this,"generic.attack_speed"), -3.6,
                            Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
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
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
            if (player.getInventory().getItemInOffHand().getType() == Material.AIR) {
                if (player.getInventory().getItemInMainHand().hasItemMeta()) {
                    if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                        if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 205) {
                            Damageable e = (Damageable) event.getRightClicked();
                            ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                            meta.setCustomModelData(2000002);

                            player.getInventory().getItemInMainHand().setItemMeta(meta);
                            e.damage(4, player);
                            e.setVelocity(e.getLocation().getDirection().setX(0).setY(0.5).setZ(0));
                            World world = player.getWorld();
                            world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 2);
                            e.getWorld().spawnParticle(Particle.SWEEP_ATTACK, e.getLocation().getX(), e.getLocation().getY() + 1, e.getLocation().getZ(), 3);
                            e.getWorld().spawnParticle(Particle.CRIT, e.getLocation().getX(), e.getLocation().getY() + 1, e.getLocation().getZ(), 6);
                        }
                    }
                }
            }
            return;
        }
        if (player.getInventory().getItemInOffHand().getType() != Material.AIR) {
            if (player.getInventory().getItemInOffHand().hasItemMeta()) {
                if (player.getInventory().getItemInOffHand().getItemMeta().hasCustomModelData()) {

                    if (player.getInventory().getItemInOffHand().getItemMeta().getCustomModelData() == 205) {
                        //idea is once player hit thing 5 times, katana able do special attack by right click when in off hand
                        //do if cmd=1000002, set to 201
                        //if 201, then set to 202, then etc till 205
                        //oh i need seperate listener for entity dmg by ent event to do above

                        //if cmd = 205, 215, or 225, then do below

                        //placeholder
                        double dmg = 0;
                        if (player.getInventory().getItemInOffHand().getType().equals(Material.DIAMOND_SWORD)) {
                            dmg = 8; //diamond katana dmg is 6, but wanted to make this do some bonus dmg too
                        }
                        if (!(event.getRightClicked() instanceof Damageable)) {
                            return;
                        }
                        Damageable clickedEntity = (Damageable) event.getRightClicked();
                        ItemMeta meta = player.getInventory().getItemInOffHand().getItemMeta();
                        meta.setCustomModelData(2000002);
                        player.swingOffHand();
                        player.getInventory().getItemInOffHand().setItemMeta(meta);
                        clickedEntity.damage(dmg, player);
                        clickedEntity.setVelocity(player.getLocation().getDirection().setY(0.4).multiply(1.5));
                        World world = player.getWorld();

                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 10, 1);
                        clickedEntity.getWorld().spawnParticle(Particle.CRIT,
                                clickedEntity.getLocation().getX(),
                                clickedEntity.getLocation().getY(),
                                clickedEntity.getLocation().getZ(), 10);
                        getServer().getScheduler().runTaskLater(this, new Runnable() {
                            public void run() {
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                clickedEntity.getWorld().spawnParticle(Particle.EXPLOSION,
                                        clickedEntity.getLocation().getX(),
                                        clickedEntity.getLocation().getY(),
                                        clickedEntity.getLocation().getZ(), 1);
                            }
                        }, 2L); //the 2L is ticks, there are 20 ticks in a second so this is 1/10th of a second delay

                        getServer().getScheduler().runTaskLater(this, new Runnable() {
                            public void run() {
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                clickedEntity.getWorld().spawnParticle(Particle.EXPLOSION,
                                        clickedEntity.getLocation().getX(),
                                        clickedEntity.getLocation().getY(),
                                        clickedEntity.getLocation().getZ(), 1);
                            }
                        }, 4L);

                        getServer().getScheduler().runTaskLater(this, new Runnable() {
                            public void run() {
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                clickedEntity.getWorld().spawnParticle(Particle.EXPLOSION,
                                        clickedEntity.getLocation().getX(),
                                        clickedEntity.getLocation().getY(),
                                        clickedEntity.getLocation().getZ(), 1);
                            }
                        }, 6L);

                        getServer().getScheduler().runTaskLater(this, new Runnable() {
                            public void run() {
                                World world = player.getWorld();
                                world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 1);
                                clickedEntity.getWorld().spawnParticle(Particle.EXPLOSION,
                                        clickedEntity.getLocation().getX(),
                                        clickedEntity.getLocation().getY(),
                                        clickedEntity.getLocation().getZ(), 1);
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
            Player player = (Player) event.getDamager();
            if (player.getInventory().getItemInMainHand() == null) {
                return;
            }
            if (!player.getInventory().getItemInMainHand().hasItemMeta()) {
                return;
            }
            if (!player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                return;
            }

            ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2000002) {
                meta.setCustomModelData(201);
                player.getInventory().getItemInMainHand().setItemMeta(meta);
                return;
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 201) {
                meta.setCustomModelData(202);
                player.getInventory().getItemInMainHand().setItemMeta(meta);
                return;
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 202) {
                meta.setCustomModelData(203);
                player.getInventory().getItemInMainHand().setItemMeta(meta);
                return;
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 203) {
                meta.setCustomModelData(204);
                player.getInventory().getItemInMainHand().setItemMeta(meta);
                return;
            }
            if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 204) {
                World world = player.getWorld();
                world.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 10, 2);
                meta.setCustomModelData(205);
                player.getInventory().getItemInMainHand().setItemMeta(meta);
            }
        }
    }

    @EventHandler
    public void onScytheRightClick(PlayerInteractEntityEvent event) {
        //scythe test ability
        Player player = event.getPlayer();
        if (player.hasCooldown(Material.DIAMOND_SWORD)) {
            return;
        }
        if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
            //if (p.getInventory().getItemInOffHand().getType() == Material.AIR) {
            if (player.getInventory().getItemInMainHand().hasItemMeta()) {
                if (player.getInventory().getItemInMainHand().getItemMeta().hasCustomModelData()) {
                    if (player.getInventory().getItemInMainHand().getItemMeta().getCustomModelData() == 2000003) {
                        Damageable clickedEntity = (Damageable) event.getRightClicked();
                        player.setCooldown(Material.DIAMOND_SWORD, 60);
                        clickedEntity.damage(5, player);

                        Vector direction = player.getLocation().getDirection().multiply(1);
                        Location playerLocation = player.getLocation().add(direction);
                        Location location = new Location(player.getWorld(),
                                playerLocation.getX(),
                                playerLocation.getY(),
                                playerLocation.getZ());

                        if (clickedEntity instanceof LivingEntity) {
                            LivingEntity livingen = (LivingEntity) clickedEntity;
                            Location l = livingen.getLocation();
                            double x = l.getX() - location.getX();
                            double y = l.getY() - location.getY();
                            double z = l.getZ() - location.getZ();
                            Vector v = new Vector(x, y, z).normalize().multiply(-0.3).setY(0.5);
                            livingen.setVelocity(v);
                        }

                        World world = player.getWorld();
                        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 10, 2);
                        clickedEntity.getWorld().spawnParticle(Particle.SWEEP_ATTACK,
                                clickedEntity.getLocation().getX(),
                                clickedEntity.getLocation().getY() + 1,
                                clickedEntity.getLocation().getZ(), 3);
                        clickedEntity.getWorld().spawnParticle(Particle.CRIT,
                                clickedEntity.getLocation().getX(),
                                clickedEntity.getLocation().getY() + 1,
                                clickedEntity.getLocation().getZ(), 6);
                    }
                }
            }
        }
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
            Vector direction = player.getLocation().getDirection().multiply(3);
            Location playerLocation = player.getLocation().add(direction);
            Location location = new Location(player.getWorld(), playerLocation.getX(), playerLocation.getY(), playerLocation.getZ());
            player.getWorld().spawnParticle(Particle.EXPLOSION, location.getX(), location.getY() + 1.6, location.getZ(), 1);
            List<Entity> nearbyEntities = player.getNearbyEntities(4, 4, 4);
            for (Entity entity : nearbyEntities) {
                if (entity instanceof LivingEntity) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    if (livingEntity != player) {
                        Location livingEntity_location = livingEntity.getLocation();
                        double x = livingEntity_location.getX() - location.getX();
                        double y = livingEntity_location.getY() - location.getY();
                        double z = livingEntity_location.getZ() - location.getZ();
                        Vector v = new Vector(x, y, z).normalize().multiply(-0.5);
                        livingEntity.setVelocity(v);
                        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 20, 5));
                    }
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
    public ShapedRecipe getExStaffRecipe() {

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
    }

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
