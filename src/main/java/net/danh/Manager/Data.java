package net.danh.Manager;

import net.danh.bCore;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Data {

    private static File configFile, languageFile, dataFile, bankFile, cmdFile;
    private static FileConfiguration config, language, data, bank, cmd;

    public static void createconfig() {
        configFile = new File(bCore.getInstance().getDataFolder(), "config.yml");
        languageFile = new File(bCore.getInstance().getDataFolder(), "language.yml");
        dataFile = new File(bCore.getInstance().getDataFolder(), "data.yml");
        bankFile = new File(bCore.getInstance().getDataFolder(), "bank.yml");
        cmdFile = new File(bCore.getInstance().getDataFolder(), "cmd.yml");

        if (!configFile.exists()) bCore.getInstance().saveResource("config.yml", false);
        if (!languageFile.exists()) bCore.getInstance().saveResource("language.yml", false);
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!bankFile.exists()) {
            bankFile.getParentFile().mkdirs();
            try {
                bankFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!cmdFile.exists()) bCore.getInstance().saveResource("cmd.yml", false);
        config = new YamlConfiguration();
        language = new YamlConfiguration();
        data = new YamlConfiguration();
        bank = new YamlConfiguration();
        cmd = new YamlConfiguration();

        try {
            config.load(configFile);
            language.load(languageFile);
            data.load(dataFile);
            bank.load(bankFile);
            cmd.load(cmdFile);
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

    public static FileConfiguration getbankfile() {
        return bank;
    }

    public static FileConfiguration getcmdfile() {
        return cmd;
    }

    public static void reloadfiles() {
        config = YamlConfiguration.loadConfiguration(configFile);
        language = YamlConfiguration.loadConfiguration(languageFile);
        cmd = YamlConfiguration.loadConfiguration(cmdFile);
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

    public static void savebank() {
        try {
            bank.save(bankFile);
        } catch (IOException ignored) {
        }
    }


    public static void savecmd() {
        try {
            cmd.save(cmdFile);
        } catch (IOException ignored) {
        }
    }

    public static int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }

    public static int getInt(java.lang.String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return 0;
        }
    }

    public static double getDouble(java.lang.String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException | NullPointerException e) {
            return 0;
        }
    }

    public static boolean isDouble(java.lang.String s) {
        try {
            if (getDouble(s) > 0) {
                return true;
            }
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return false;
    }
}