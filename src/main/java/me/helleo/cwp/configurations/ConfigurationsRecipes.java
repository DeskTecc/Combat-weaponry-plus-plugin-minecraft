package me.helleo.cwp.configurations;

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
import org.bukkit.NamespacedKey;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationsRecipes {

    private List<NamespacedKey> keys = new ArrayList<>();

    public void setKey(NamespacedKey key){
        keys.add(key);
    }
    public List<NamespacedKey> getKeys(){
        return keys;
    }

    public void clearRecipes(){
        keys.clear();
    }

    public void loadRecipes() {
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
            if (ConfigurationsBool.EmeraldGear.getValue()) {
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
            if (ConfigurationsBool.EmeraldGear.getValue()) {
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
            if (ConfigurationsBool.EmeraldGear.getValue()) {
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
            if (ConfigurationsBool.EmeraldGear.getValue()) {
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
            if (ConfigurationsBool.EmeraldGear.getValue()) {
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
            if (ConfigurationsBool.EmeraldGear.getValue()) {
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
            if (ConfigurationsBool.EmeraldGear.getValue()) {
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
            if (ConfigurationsBool.EmeraldGear.getValue()) {
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
}
