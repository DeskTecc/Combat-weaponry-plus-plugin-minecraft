package me.helleo.cwp.configurations;

import me.helleo.cwp.CombatWeaponryPlus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigLoader {

    private static final Plugin plugin = CombatWeaponryPlus.getPlugin();


    public static void setLang(String language) throws IOException {
        File langFile = new File(plugin.getDataFolder(), "/lang/lang-" + language + ".yml");
        if(!langFile.exists()){
            try{
                plugin.saveResource("lang/lang-"+language+".yml",false);
                return;
            }catch (Exception e){
                plugin.saveResource("lang/lang-en.yml",false);
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Specified language don't exist on plugin folder, English will be loaded."+ChatColor.RESET);
                return;
            }
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(langFile);
        config.save(langFile);
    }

    public static YamlConfiguration getLang(){
        String language = getConfig().getString("lang");
        try {
            File langYml = new File(plugin.getDataFolder() + "/lang/lang-" + language + ".yml");
            return YamlConfiguration.loadConfiguration(langYml);
        } catch (Exception e) {
            File langYml = new File(plugin.getDataFolder() + "/lang/lang-en.yml");
            return YamlConfiguration.loadConfiguration(langYml);
        }
    }

    public static void reloadConfig(){
        plugin.reloadConfig();
    }

    public static void setWeapon(String weapon) throws IOException {
        File weaponYml = new File(plugin.getDataFolder() + "/weapons/" + weapon + ".yml");
        if(!weaponYml.exists()){
            plugin.saveResource("weapons/" + weapon + ".yml",false);
        }
        YamlConfiguration config = YamlConfiguration.loadConfiguration(weaponYml);
        config.save(weaponYml);
    }

    public static YamlConfiguration getWeapon(String weapon){
        try {
            File langYml = new File(plugin.getDataFolder() + "/weapons/" + weapon + ".yml");
            return YamlConfiguration.loadConfiguration(langYml);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public static void loadWeapons() throws IOException {
        setWeapon("cleaver");
        setWeapon("katana");
        setWeapon("knife");
        setWeapon("longsword");
        setWeapon("rapier");
        setWeapon("saber");
        setWeapon("scythe");
        setWeapon("spear");
    }

    public static FileConfiguration getConfig(){
        return plugin.getConfig();
    }
}
