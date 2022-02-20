package net.danh.bCore.Manager;

import net.danh.bCore.bCore;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Data {
    private static File configFile, languageFile, dataFile, storageFile, priceFile, guiFile, bankFile;
    private static FileConfiguration config, language, data, storage, price, gui, bank;


    public static void createconfig() {
        configFile = new File(bCore.getInstance().getDataFolder(), "config.yml");
        languageFile = new File(bCore.getInstance().getDataFolder(), "language.yml");
        dataFile = new File(bCore.getInstance().getDataFolder(), "data.yml");
        storageFile = new File(bCore.getInstance().getDataFolder(), "storage.yml");
        priceFile = new File(bCore.getInstance().getDataFolder(), "price.yml");
        guiFile = new File(bCore.getInstance().getDataFolder(), "gui.yml");
        bankFile = new File(bCore.getInstance().getDataFolder(), "bank.yml");

        if (!configFile.exists()) bCore.getInstance().saveResource("config.yml", false);
        if (!languageFile.exists()) bCore.getInstance().saveResource("language.yml", false);
        if (!dataFile.exists()) bCore.getInstance().saveResource("data.yml", false);
        if (!storageFile.exists()) bCore.getInstance().saveResource("storage.yml", false);
        if (!priceFile.exists()) bCore.getInstance().saveResource("price.yml", false);
        if (!guiFile.exists()) bCore.getInstance().saveResource("gui.yml", false);
        if (!bankFile.exists()) bCore.getInstance().saveResource("bank.yml", false);
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


    public static FileConfiguration getconfigfile() {
        return config;
    }

    public static FileConfiguration getlanguagefile() {
        return language;
    }

    public static FileConfiguration getdatafile() {
        return data;
    }

    public static FileConfiguration getstoragefile() {
        return storage;
    }

    public static FileConfiguration getpricefile() {
        return price;
    }

    public static FileConfiguration getguifile() {
        return gui;
    }

    public static FileConfiguration getbankfile() {
        return bank;
    }

    public static void reloadconfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
        language = YamlConfiguration.loadConfiguration(languageFile);
        gui = YamlConfiguration.loadConfiguration(guiFile);
    }

    public static void saveconfig() {
        try {
            config.save(configFile);
        } catch (IOException ignored) {
        }
    }


    public static void savelanguage() {
        try {
            language.save(languageFile);
        } catch (IOException ignored) {
        }
    }


    public static void savedata() {
        try {
            data.save(dataFile);
        } catch (IOException ignored) {
        }
    }

    public static void savestorage() {
        try {
            storage.save(storageFile);
        } catch (IOException ignored) {
        }
    }

    public static void saveprice() {
        try {
            price.save(priceFile);
        } catch (IOException ignored) {
        }
    }

    public static void savegui() {
        try {
            gui.save(guiFile);
        } catch (IOException ignored) {
        }
    }

    public static void savebank() {
        try {
            bank.save(bankFile);
        } catch (IOException ignored) {
        }
    }

    public static double getRandomDouble(double min, double max) {
        Random r = new Random();
        return r.nextDouble(max - min) + min;
    }

    public static int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }

    public static String convert(String s) {
        return s.replaceAll("&", "§");
    }
}
