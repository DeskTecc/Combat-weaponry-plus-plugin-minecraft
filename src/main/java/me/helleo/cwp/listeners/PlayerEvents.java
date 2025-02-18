package me.helleo.cwp.listeners;

import me.helleo.cwp.CombatWeaponryPlus;
import me.helleo.cwp.configurations.ConfigurationsBool;
import me.helleo.cwp.configurations.ConfigurationsString;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (ConfigurationsBool.CustomResourcePack.getValue()) {
            player.setResourcePack(ConfigurationsString.PackLink.getValue());
        }else{
            if(Bukkit.getVersion().contains("1.21.2") || Bukkit.getVersion().contains("1.21.3")){
                player.setResourcePack("https://download.mc-packs.net/pack/c458ca435b4bc36ce1e0094fb8c4c07d60c2ba85.zip");
            }else if (Bukkit.getVersion().contains("1.21") || Bukkit.getVersion().contains("1.21.1")){
                player.setResourcePack("https://download.mc-packs.net/pack/74b6ef9eb3a726b6ce7469c4d316457a2c5c4f1d.zip");
            }else{
                player.setResourcePack("https://www.dropbox.com/scl/fi/dhgubahgx3z0phg4wnbw3/cwp-texture-pack-1.5.7.zip?rlkey=cxtqp9575bk28qr90vyxb2tdv&dl=1");
            }
        }

        player.discoverRecipes(CombatWeaponryPlus.getRecipes().getKeys());
    }
}
