package net.danh.Manager;

import net.danh.bCore;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import preponderous.ponder.minecraft.bukkit.nms.NMSAssistant;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data {

    public final static char COLOR_CHAR = ChatColor.COLOR_CHAR;
    private static File configFile, languageFile, dataFile, bankFile, cmdFile, discordFile;
    private static FileConfiguration config, language, data, bank, cmd, discord;

    public static void createconfig() {
        configFile = new File(bCore.getInstance().getDataFolder(), "config.yml");
        languageFile = new File(bCore.getInstance().getDataFolder(), "language.yml");
        dataFile = new File(bCore.getInstance().getDataFolder(), "data.yml");
        bankFile = new File(bCore.getInstance().getDataFolder(), "bank.yml");
        cmdFile = new File(bCore.getInstance().getDataFolder(), "cmd.yml");
        discordFile = new File(bCore.getInstance().getDataFolder(), "discord.yml");

        if (!configFile.exists()) bCore.getInstance().saveResource("config.yml", false);
        if (!languageFile.exists()) bCore.getInstance().saveResource("language.yml", false);
        if (!discordFile.exists()) bCore.getInstance().saveResource("discord.yml", false);
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
        discord = new YamlConfiguration();

        try {
            config.load(configFile);
            language.load(languageFile);
            data.load(dataFile);
            bank.load(bankFile);
            cmd.load(cmdFile);
            discord.load(discordFile);
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

    public static FileConfiguration getdiscordfile() {
        return discord;
    }

    public static void reloadfiles() {
        config = YamlConfiguration.loadConfiguration(configFile);
        language = YamlConfiguration.loadConfiguration(languageFile);
        cmd = YamlConfiguration.loadConfiguration(cmdFile);
        discord = YamlConfiguration.loadConfiguration(discordFile);
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

    public static void savediscord() {
        try {
            discord.save(discordFile);
        } catch (IOException ignored) {
        }
    }

    public static int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }

    public static void saveall(@NotNull Player p) {
        Data.getbankfile().set("banks." + p.getName() + ".money", Bank.getBankMoney(p));
        Data.getbankfile().set("banks." + p.getName() + ".level", Bank.getBankLevel(p));
        Data.getdatafile().set("players." + p.getName() + ".Credit", Credit.getCredit(p));
        Data.getdatafile().set("players." + p.getName() + ".Event.Blocks", Event.getBlockEvent(p));
        Data.getdatafile().set("players." + p.getName() + ".XP", PlayerData.getXP(p));
        Data.getdatafile().set("players." + p.getName() + ".Level", PlayerData.getLevel(p));
        Data.getdatafile().set("players." + p.getName() + ".LevelUpTimes", PlayerData.getleveluptimes(p));
        Data.getdatafile().set("players." + p.getName() + ".Blocks", PlayerData.getBlocks(p));
        Data.getdatafile().set("players." + p.getName() + ".Power", Power.getPower(p));
        Data.getdatafile().set("players." + p.getName() + ".MaxPower", Power.getMaxPower(p));
        Data.getdatafile().set("players." + p.getName() + ".RestorePower", Power.getRestorePower(p));
        Data.getdatafile().set("players." + p.getName() + ".Points", Points.getPvPPoints(p));
        Data.getdatafile().set("players." + p.getName() + ".Status", Status.getPvPStatus(p));
        Data.getdatafile().set("players." + p.getName() + ".BitCoins.BC", Bitcoin.getBitCoin(p));
        Data.getdatafile().set("BitCoinsMax.BC", Bitcoin.getBitCoinMax());
        Data.getdatafile().set("players" + p.getName() + ".QPoints", QPoints.getQP(p));
        Data.savedata();
        Data.savebank();
    }

    // Colorizes messages with preset colorcodes (&) and if using 1.16+, applies hex values via "&#hexvalue"
    public static String convert(String input) {

        input = ChatColor.translateAlternateColorCodes('&', input);
        NMSAssistant nms = new NMSAssistant();
        if (nms.isVersionGreaterThan(15)) {
            input = translateHexColorCodes("&#", "", input);
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