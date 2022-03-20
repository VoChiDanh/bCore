package net.danh.Manager;

import net.danh.bCore;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import preponderous.ponder.minecraft.bukkit.nms.NMSAssistant;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data {

    public final static char COLOR_CHAR = ChatColor.COLOR_CHAR;
    private static File configFile, languageFile, dataFile, storageFile, itemFile, bankFile, cmdFile;
    private static FileConfiguration config, language, data, storage, item, bank, cmd;

    public static void createconfig() {
        configFile = new File(bCore.getInstance().getDataFolder(), "config.yml");
        languageFile = new File(bCore.getInstance().getDataFolder(), "language.yml");
        dataFile = new File(bCore.getInstance().getDataFolder(), "data.yml");
        storageFile = new File(bCore.getInstance().getDataFolder(), "storage.yml");
        itemFile = new File(bCore.getInstance().getDataFolder(), "item.yml");
        bankFile = new File(bCore.getInstance().getDataFolder(), "bank.yml");
        cmdFile = new File(bCore.getInstance().getDataFolder(), "cmd.yml");

        if (!configFile.exists()) bCore.getInstance().saveResource("config.yml", false);
        if (!languageFile.exists()) bCore.getInstance().saveResource("language.yml", false);
        if (!dataFile.exists()) bCore.getInstance().saveResource("data.yml", false);
        if (!storageFile.exists()) bCore.getInstance().saveResource("storage.yml", false);
        if (!itemFile.exists()) bCore.getInstance().saveResource("item.yml", false);
        if (!bankFile.exists()) bCore.getInstance().saveResource("bank.yml", false);
        if (!cmdFile.exists()) bCore.getInstance().saveResource("cmd.yml", false);
        config = new YamlConfiguration();
        language = new YamlConfiguration();
        data = new YamlConfiguration();
        storage = new YamlConfiguration();
        item = new YamlConfiguration();
        bank = new YamlConfiguration();
        cmd = new YamlConfiguration();

        try {
            config.load(configFile);
            language.load(languageFile);
            data.load(dataFile);
            storage.load(storageFile);
            item.load(itemFile);
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

    public static FileConfiguration getstoragefile() {
        return storage;
    }

    public static FileConfiguration getitemfile() {
        return item;
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
        item = YamlConfiguration.loadConfiguration(itemFile);
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

    public static void savestorage() {
        try {
            storage.save(storageFile);
        } catch (IOException ignored) {
        }
    }

    public static void saveitems() {
        try {
            item.save(itemFile);
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

    // Colorizes messages with preset colorcodes (&) and if using 1.16+, applies hex values via "&#hexvalue"
    public static String convert(String input) {

        input = ChatColor.translateAlternateColorCodes('&', input);
        NMSAssistant nms = new NMSAssistant();
        if (nms.isVersionGreaterThan(15)) {
            input = translateHexColorCodes("\\&#", "", input);
        }

        return input;
    }

    public static @NotNull String translateHexColorCodes(String startTag, String endTag, String message) {

        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);

        while (matcher.find()) {

            String group = matcher.group(1);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x" + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1) + COLOR_CHAR
                    + group.charAt(2) + COLOR_CHAR + group.charAt(3) + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5));

        }

        return matcher.appendTail(buffer).toString();
    }
}
