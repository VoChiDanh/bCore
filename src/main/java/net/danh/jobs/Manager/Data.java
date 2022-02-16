package net.danh.jobs.Manager;

import net.danh.jobs.Jobs;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Data {
    private static Data instance;
    private File configFile, languageFile, dataFile, storageFile, priceFile, guiFile, bankFile;
    private FileConfiguration config, language, data, storage, price, gui, bank;

    public static Data getInstance() {

        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    public void createconfig() {
        configFile = new File(Jobs.getInstance().getDataFolder(), "config.yml");
        languageFile = new File(Jobs.getInstance().getDataFolder(), "language.yml");
        dataFile = new File(Jobs.getInstance().getDataFolder(), "data.yml");
        storageFile = new File(Jobs.getInstance().getDataFolder(), "storage.yml");
        priceFile = new File(Jobs.getInstance().getDataFolder(), "price.yml");
        guiFile = new File(Jobs.getInstance().getDataFolder(), "gui.yml");
        bankFile = new File(Jobs.getInstance().getDataFolder(), "bank.yml");

        if (!configFile.exists()) Jobs.getInstance().saveResource("config.yml", false);
        if (!languageFile.exists()) Jobs.getInstance().saveResource("language.yml", false);
        if (!dataFile.exists()) Jobs.getInstance().saveResource("data.yml", false);
        if (!storageFile.exists()) Jobs.getInstance().saveResource("storage.yml", false);
        if (!priceFile.exists()) Jobs.getInstance().saveResource("price.yml", false);
        if (!guiFile.exists()) Jobs.getInstance().saveResource("gui.yml", false);
        if (!bankFile.exists()) Jobs.getInstance().saveResource("bank.yml", false);
        config = new YamlConfiguration();
        language = new YamlConfiguration();
        data = new YamlConfiguration();
        storage = new YamlConfiguration();
        price = new YamlConfiguration();
        gui = new YamlConfiguration();
        bank = new YamlConfiguration();

        try {
            config.load(configFile);
            language.load(languageFile);
            data.load(dataFile);
            storage.load(storageFile);
            price.load(priceFile);
            gui.load(guiFile);
            bank.load(bankFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    public FileConfiguration getconfigfile() {
        return config;
    }

    public FileConfiguration getlanguagefile() {
        return language;
    }

    public FileConfiguration getdatafile() {
        return data;
    }

    public FileConfiguration getstoragefile() {
        return storage;
    }

    public FileConfiguration getpricefile() {
        return price;
    }

    public FileConfiguration getguifile() {
        return gui;
    }

    public FileConfiguration getbankfile() {
        return bank;
    }

    public void reloadconfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
        language = YamlConfiguration.loadConfiguration(languageFile);
        gui = YamlConfiguration.loadConfiguration(guiFile);
    }

    public void saveconfig() {
        try {
            config.save(configFile);
        } catch (IOException ignored) {
        }
    }


    public void savelanguage() {
        try {
            language.save(languageFile);
        } catch (IOException ignored) {
        }
    }


    public void savedata() {
        try {
            data.save(dataFile);
        } catch (IOException ignored) {
        }
    }

    public void savestorage() {
        try {
            storage.save(storageFile);
        } catch (IOException ignored) {
        }
    }

    public void saveprice() {
        try {
            price.save(priceFile);
        } catch (IOException ignored) {
        }
    }

    public void savegui() {
        try {
            gui.save(guiFile);
        } catch (IOException ignored) {
        }
    }
    public void savebank() {
        try {
            bank.save(bankFile);
        } catch (IOException ignored) {
        }
    }

    public int getRandomNumber(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }

    public String convert(String s) {
        return s.replaceAll("&", "§");
    }
}
