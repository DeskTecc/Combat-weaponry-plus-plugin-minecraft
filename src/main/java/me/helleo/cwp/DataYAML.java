package me.helleo.cwp;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static me.helleo.cwp.CombatWeaponryPlus.plugin;

public class DataYAML {

    private File file;
    private YamlConfiguration configuration;

    public void langFile() {
        this.file = new File(plugin.getDataFolder(), "lang/lang-en.yml");

        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
        }

        this.configuration = new YamlConfiguration();
        try {
            this.configuration.load(this.file);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }

        this.save();
    }

    public void save() {
        try {
            this.configuration.save(this.file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public File getFile() {
        return this.file;
    }

    public YamlConfiguration getConfiguration() {
        return this.configuration;
    }
}
