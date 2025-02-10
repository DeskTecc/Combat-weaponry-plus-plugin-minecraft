package me.helleo.cwp.configurations;

import static me.helleo.cwp.configurations.ConfigLoader.getConfig;

public enum ConfigurationsBool {
    Emerald(getConfig().getBoolean("Emerald")),
    CustomResourcePack(getConfig().getBoolean("CustomResourcePack")),
    ShieldParry(getConfig().getBoolean("ShieldParry")),
    Chainmail(getConfig().getBoolean("Chainmail")),
    PlatedChainmail(getConfig().getBoolean("PlatedChainmail")),
    RedstoneCore(getConfig().getBoolean("RedstoneCore")),
    EmeraldGear(getConfig().getBoolean("EmeraldGear")),
    ChorusBlade(getConfig().getBoolean("ChorusBlade")),
    ObsidianPickaxe(getConfig().getBoolean("ObsidianPickaxe")),
    FeatherCharm(getConfig().getBoolean("FeatherCharm")),
    BlazeCharm(getConfig().getBoolean("BlazeCharm")),
    EmeraldCharm(getConfig().getBoolean("EmeraldCharm")),
    GoldCharm(getConfig().getBoolean("GoldCharm")),
    StarCharm(getConfig().getBoolean("StarCharm")),
    FrostCharm(getConfig().getBoolean("FrostCharm")),
    Knives(getConfig().getBoolean("Knives")),
    Rapiers(getConfig().getBoolean("Rapiers")),
    Katanas(getConfig().getBoolean("Katanas")),
    Scythes(getConfig().getBoolean("Scythes")),
    Longswords(getConfig().getBoolean("Longswords")),
    Spears(getConfig().getBoolean("Spears")),
    Sabers(getConfig().getBoolean("Sabers")),
    Cleavers(getConfig().getBoolean("Cleavers")),
    DualWieldSaberOnly(getConfig().getBoolean("DualWieldSaberOnly")),
    Prismarine(getConfig().getBoolean("Prismarine")),
    SwordBow(getConfig().getBoolean("SwordBow")),
    HeavySwordBow(getConfig().getBoolean("HeavySwordBow")),
    Longbow(getConfig().getBoolean("Longbow")),
    Recurvebow(getConfig().getBoolean("Recurvebow")),
    Compoundbow(getConfig().getBoolean("Compoundbow")),
    RepeatingCrossbow(getConfig().getBoolean("RepeatingCrossbow")),
    LongswordBow(getConfig().getBoolean("LongswordBow")),
    BurstCrossbow(getConfig().getBoolean("BurstCrossbow")),
    RedstoneBow(getConfig().getBoolean("RedstoneBow")),
    DiamondShield(getConfig().getBoolean("DiamondShield")),
    NetheriteShield(getConfig().getBoolean("NetheriteShield")),
    Vessel(getConfig().getBoolean("Vessel")),
    InfusedVessel(getConfig().getBoolean("InfusedVessel")),
    CursedVessel(getConfig().getBoolean("CursedVessel")),
    WitherArmor(getConfig().getBoolean("WitherArmor")),
    Eelytra(getConfig().getBoolean("Eelytra")),
    TridentBow(getConfig().getBoolean("TridentBow")),
    JumpElytra(getConfig().getBoolean("JumpElytra")),
    FishSword(getConfig().getBoolean("FishSword")),
    WindBlade(getConfig().getBoolean("WindBlade")),
    VolcanicBlade(getConfig().getBoolean("VolcanicBlade")),
    VolcanicSpear(getConfig().getBoolean("VolcanicSpear")),
    VolcanicAxe(getConfig().getBoolean("VolcanicAxe")),
    VolcanicCleaver(getConfig().getBoolean("VolcanicCleaver")),
    WitherBones(getConfig().getBoolean("WitherBones")),
    AwakenedVesselWhite(getConfig().getBoolean("AwakenedVesselWhite")),
    AwakenedVesselPurple(getConfig().getBoolean("AwakenedVesselPurple")),
    EnchantmentsOnEmeraldArmor(getConfig().getBoolean("EnchantmentsOnEmeraldArmor")),
    EnchantmentsOnEmeraldGear(getConfig().getBoolean("EnchantmentsOnEmeraldGear")),
    EnchantsChorusBlade(getConfig().getBoolean("EnchantsChorusBlade")),
    EnchantsSwordBow(getConfig().getBoolean("EnchantsSwordBow")),
    EnchantsHeavySwordBow(getConfig().getBoolean("EnchantsHeavySwordBow")),
    EnchantsPlatedChainmail(getConfig().getBoolean("EnchantsPlatedChainmail")),
    EnchantsObsidianPickaxe(getConfig().getBoolean("EnchantsObsidianPickaxe")),
    EnchantsDiamondShield(getConfig().getBoolean("EnchantsDiamondShield")),
    EnchantsNetheriteShield(getConfig().getBoolean("EnchantsNetheriteShield")),
    UseCustomValues(getConfig().getBoolean("UseCustomValues")),
    NetheriteIngots(getConfig().getBoolean("NetheriteIngots"));


    private final boolean value;

    ConfigurationsBool(boolean value){
        this.value = value;
    }
    public boolean getValue(){
        return this.value;
    }
}
