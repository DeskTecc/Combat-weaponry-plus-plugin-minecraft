package me.helleo.cwp.listeners;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

import static me.helleo.cwp.CombatWeaponryPlus.langDef;
import static me.helleo.cwp.CombatWeaponryPlus.plugin;
import static me.helleo.cwp.configurations.ConfigLoader.getConfig;
import static me.helleo.cwp.configurations.ConfigurationsRecipes.loadRecipes;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("cwp.reload")) {
                if (Objects.equals(args[0], "reload")) {
                    try {
                        plugin.reloadConfig();
                        plugin.saveConfig();
                        //reload lang file
                        try{
                            langDef = getConfig().getString("lang");
                        }catch (Exception e){
                            langDef = "en";
                        }
                        boolean langFileLoad = new ConfigLoader().setLang(langDef);

                        if(!langFileLoad){
                            langDef= "en";
                        }
                        CombatWeaponryPlus.keys.clear();
                        Bukkit.clearRecipes();
                        Bukkit.resetRecipes();
                        loadRecipes();
                        player.sendMessage(ChatColor.GREEN + ConfigLoader.getLang().getString("Reload_Success") + ChatColor.RESET);
                        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + ConfigLoader.getLang().getString("Reload_Success") + ChatColor.RESET);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                        //player.sendMessage(ChatColor.RED + ConfigLoader.getLang().getString("Reload_Error") + ChatColor.RESET);
                        //Bukkit.getConsoleSender().sendMessage(ChatColor.RED + ConfigLoader.getLang().getString("Reload_Error") + ChatColor.RESET);
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + ConfigLoader.getLang().getString("You_Dont_Have_Permission") + ChatColor.RESET);
            }
        }else{
            try{
                plugin.reloadConfig();
                plugin.saveConfig();
                //reload lang file
                try{
                    langDef = getConfig().getString("lang");
                }catch (Exception e){
                    langDef = "en";
                }
                boolean langFileLoad = new ConfigLoader().setLang(langDef);

                if(!langFileLoad){
                    langDef= "en";
                }
                CombatWeaponryPlus.keys.clear();
                Bukkit.clearRecipes();
                Bukkit.resetRecipes();
                loadRecipes();
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + ConfigLoader.getLang().getString("Reload_Success") + ChatColor.RESET);
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + ConfigLoader.getConfig().getString("lang") + ChatColor.RESET);

            }catch (Exception e){
                throw new RuntimeException(e);
                //Bukkit.getConsoleSender().sendMessage(ChatColor.RED + ConfigLoader.getLang().getString("Reload_Error") + ChatColor.RESET);
            }
        }
        return true;
    }
}
