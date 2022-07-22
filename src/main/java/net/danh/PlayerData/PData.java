package net.danh.PlayerData;

import net.danh.bCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class PData {

    private final String name;
    private File file;
    private FileConfiguration config;

    public PData(String name) {
        this.name = name;
        this.file = new File(bCore.getInstance().getDataFolder() + File.separator + "PlayerData", name + ".yml");
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void load() {
        this.file = new File(bCore.getInstance().getDataFolder() + File.separator + "PlayerData", this.name + ".yml");
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            try {
                this.file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public String getName() {
        return name;
    }

    public File getFile() {
        return this.file;
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public void save() {
        try {
            this.config.save(this.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
