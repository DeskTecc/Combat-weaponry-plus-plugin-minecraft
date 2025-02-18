package me.helleo.cwp.listeners;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigLoader;
import me.helleo.cwp.configurations.ConfigurationsRecipes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

import static me.helleo.cwp.configurations.ConfigLoader.getConfig;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("cwp.reload")) {
                if (Objects.equals(args[0], "reload")) {
                    try {
                        ConfigLoader.reloadConfig();
                        ConfigLoader.setLang(getConfig().getString("lang"));

                        Bukkit.clearRecipes();
                        Bukkit.resetRecipes();

                        ConfigurationsRecipes recipes = CombatWeaponryPlus.getRecipes();
                        recipes.clearRecipes();
                        recipes.loadRecipes();
                        player.sendMessage(ChatColor.GREEN + ConfigLoader.getLang().getString("Reload_Success") + ChatColor.RESET);
                        player.sendMessage(ChatColor.YELLOW + ConfigLoader.getLang().getString("Reload_Advice") + ChatColor.RESET);
                        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + ConfigLoader.getLang().getString("Reload_Success") + ChatColor.RESET);
                        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + ConfigLoader.getLang().getString("Reload_Advice") + ChatColor.RESET);
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
                ConfigLoader.reloadConfig();
                ConfigLoader.setLang(getConfig().getString("lang"));

                Bukkit.clearRecipes();
                Bukkit.resetRecipes();

                ConfigurationsRecipes recipes = CombatWeaponryPlus.getRecipes();
                recipes.clearRecipes();
                recipes.loadRecipes();

                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + ConfigLoader.getLang().getString("Reload_Success") + ChatColor.RESET);
                Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + ConfigLoader.getLang().getString("Reload_Advice") + ChatColor.RESET);

            }catch (Exception e){
                throw new RuntimeException(e);
                //Bukkit.getConsoleSender().sendMessage(ChatColor.RED + ConfigLoader.getLang().getString("Reload_Error") + ChatColor.RESET);
            }
        }

        return true;
    }
}
