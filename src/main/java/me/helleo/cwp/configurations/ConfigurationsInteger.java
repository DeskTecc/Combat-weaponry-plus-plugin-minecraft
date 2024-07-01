package me.helleo.cwp.configurations;

import org.bukkit.Bukkit;

import static me.helleo.cwp.CombatWeaponryPlus.pluginName;

public enum ConfigurationsInteger {
    EmeraldArmorEnchantLevels_Unbreaking(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("EmeraldArmorEnchantLevels.Unbreaking")),
    EmeraldArmorEnchantLevels_Mending(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("EmeraldArmorEnchantLevels.Mending")),
    EmeraldGearEnchantLevels_Unbreaking(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("EmeraldArmorEnchantLevels.Unbreaking")),
    EmeraldGearEnchantLevels_Mending(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("EmeraldArmorEnchantLevels.Mending")),
    ChorusEnchantLevels_Unbreaking(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("ChorusEnchantLevels.Unbreaking")),
    ChorusEnchantLevels_Knockback(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("ChorusEnchantLevels.KnockBack")),
    SwordBowEnchantLevels_Smite(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("SwordBowEnchantLevels.Smite")),
    SwordBowEnchantLevels_Unbreaking(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("SwordBowEnchantLevels.Unbreaking")),
    SwordBowEnchantLevels_Mending(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("SwordBowEnchantLevels.Mending")),
    HeavySwordBowEnchantLevels_Smite(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("HeavySwordBowEnchantLevels.Smite")),
    HeavySwordBowEnchantLevels_Power(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("HeavySwordBowEnchantLevels.Power")),
    HeavySwordBowEnchantLevels_Unbreaking(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("HeavySwordBowEnchantLevels.Unbreaking")),
    HeavySwordBowEnchantLevels_Mending(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("HeavySwordBowEnchantLevels.Mending")),
    PlatedChainmailEnchantLevels_Unbreaking(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("PlatedChainmailEnchantLevels.Unbreaking")),
    ObsidianEnchantLevels_Unbreaking(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("ObsidianEnchantLevels.Unbreaking")),
    DiamondShieldEnchantLevels_Unbreaking(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("DiamondShieldEnchantLevels.Unbreaking")),
    NetheriteShieldEnchantLevels_Unbreaking(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("NetheriteShieldEnchantLevels.Unbreaking")),
    MultiplierKnives(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("MultiplierKnives")),
    MultiplierRapiers(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("MultiplierRapiers")),
    MultiplierKatanas(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("MultiplierKatanas")),
    MultiplierScythes(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("MultiplierScythes")),
    MultiplierLongswords(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("MultiplierLongswords")),
    MultiplierSpears(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("MultiplierSpears")),
    MultiplierSabers(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("MultiplierSabers")),
    MultiplierCleavers(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("MultiplierCleavers")),
    ArmorRedstoneCore_Armor(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("ArmorRedstoneCore.Armor")),
    ArmorEmeraldHelmet_Armor(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("ArmorEmeraldHelmet.Armor")),
    ArmorEmeraldHelmet_BonusHealth(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("ArmorEmeraldHelmet.BonusHealth")),
    ArmorEmeraldChestplate_Armor(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("ArmorEmeraldChestplate.Armor")),
    ArmorEmeraldChestplate_BonusHealth(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("ArmorEmeraldChestplate.BonusHealth")),
    ArmorEmeraldLeggings_Armor(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("ArmorEmeraldLeggings.Armor")),
    ArmorEmeraldLeggings_BonusHealth(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("ArmorEmeraldLeggings.BonusHealth")),
    ArmorEmeraldBoots_Armor(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("ArmorEmeraldBoots.Armor")),
    ArmorEmeraldBoots_BonusHealth(Bukkit.getPluginManager().getPlugin(pluginName).getConfig().getInt("ArmorEmeraldBoots.BonusHealth"))
    //adicionar o resto das armaduras
    ;


    private final int value;

    ConfigurationsInteger(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
