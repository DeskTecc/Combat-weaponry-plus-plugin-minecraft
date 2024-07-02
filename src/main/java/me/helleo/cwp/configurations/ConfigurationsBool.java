package me.helleo.cwp.configurations;

import org.bukkit.Bukkit;

import static me.helleo.cwp.CombatWeaponryPlus.pluginName;

public enum ConfigurationsBool {
    Emerald(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("Emerald")),
    ResourcePack(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("ResourcePack")),
    ShieldParry(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("ShieldParry")),
    Chainmail(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("Chainmail")),
    RedstoneCore(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("RedstoneCore")),
    EmeraldGear(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("EmeraldGear")),
    ChorusBlade(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("ChorusBlade")),
    ObsidianPickaxe(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("ObsidianPickaxe")),
    FeatherCharm(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("FeatherCharm")),
    BlazeCharm(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("BlazeCharm")),
    EmeraldCharm(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("EmeraldCharm")),
    GoldCharm(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("GoldCharm")),
    StarCharm(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("StarCharm")),
    FrostCharm(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("FrostCharm")),
    Knives(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("Knives")),
    Rapiers(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("Rapiers")),
    Katanas(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("Katanas")),
    Scythes(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("Scythes")),
    Longswords(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("Longswords")),
    Spears(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("Spears")),
    Sabers(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("Sabers")),
    Cleavers(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("Cleavers")),
    DualWieldSaberOnly(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("DualWieldSaberOnly")),
    Prismarine(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("Prismarine")),
    SwordBow(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("SwordBow")),
    HeavySwordBow(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("HeavySwordBow")),
    Longbow(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("Longbow")),
    Recurvebow(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("Recurvebow")),
    Compoundbow(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("Compoundbow")),
    RepeatingCrossbow(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("RepeatingCrossbow")),
    LongswordBow(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("LongswordBow")),
    BurstCrossbow(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("BurstCrossbow")),
    RedstoneBow(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("RedstoneBow")),
    DiamondShield(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("DiamondShield")),
    NetheriteShield(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("NetheriteShield")),
    Vessel(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("Vessel")),
    InfusedVessel(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("InfusedVessel")),
    CursedVessel(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("CursedVessel")),
    ReallyGoodSword(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("ReallyGoodSword")),
    WitherArmor(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("WitherArmor")),
    Eelytra(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("Eelytra")),
    TridentBow(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("TridentBow")),
    JumpElytra(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("JumpElytra")),
    FishSword(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("FishSword")),
    WindBlade(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("WindBlade")),
    VolcanicBlade(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("VolcanicBlade")),
    VolcanicSpear(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("VolcanicSpear")),
    VolcanicAxe(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("VolcanicAxe")),
    VolcanicCleaver(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("VolcanicCleaver")),
    TestKatana(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("TestKatana")),
    TestScythe(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("TestScythe")),
    WitherBones(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("WitherBones")),
    AwakenedVesselWhite(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("AwakenedVesselWhite")),
    AwakenedVesselPurple(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("AwakenedVesselPurple")),
    EnchantmentsOnEmeraldArmor(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("EnchantmentsOnEmeraldArmor")),
    EnchantmentsOnEmeraldGear(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("EnchantmentsOnEmeraldGear")),
    EnchantsChorusBlade(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("EnchantsChorusBlade")),
    EnchantsSwordBow(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("EnchantsSwordBow")),
    EnchantsHeavySwordBow(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("EnchantsHeavySwordBow")),
    EnchantsPlatedChainmail(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("EnchantsPlatedChainmail")),
    EnchantsObsidianPickaxe(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("EnchantsObsidianPickaxe")),
    EnchantsDiamondShield(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("EnchantsDiamondShield")),
    EnchantsNetheriteShield(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("EnchantsNetheriteShield")),
    UseCustomValues(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("UseCustomValues")),
    NetheriteIngots(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getBoolean("NetheriteIngots"));


    private final boolean value;

    ConfigurationsBool(boolean value){
        this.value = value;
    }
    public boolean getValue(){
        return this.value;
    }
}
