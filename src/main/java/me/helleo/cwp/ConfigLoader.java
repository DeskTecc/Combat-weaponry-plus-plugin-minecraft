package me.helleo.cwp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static me.helleo.cwp.CombatWeaponryPlus.langDef;
import static me.helleo.cwp.CombatWeaponryPlus.plugin;

public class ConfigLoader {

    public boolean setLang(String language) {
        File langFile = new File(plugin.getDataFolder(), "/lang/lang-" + language + ".yml");
        if(!langFile.exists()){
            try{
                plugin.saveResource("lang/lang-"+language+".yml",false);
                return true;
            }catch (Exception e){
                plugin.saveResource("lang/lang-en.yml",false);
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Specified language don't exist on plugin folder, English will be loaded."+ChatColor.RESET);
                return false;
            }
        }
        return true;
    }

    public static YamlConfiguration getLang(){
        File langYml = new File(plugin.getDataFolder() + "/lang/lang-"+langDef+".yml");
        return YamlConfiguration.loadConfiguration(langYml);
    }

    public static FileConfiguration getConfig(){
        return plugin.getConfig();
    }
}
