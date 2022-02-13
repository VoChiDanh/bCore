package net.danh.jobs.Manager;

import net.danh.jobs.Jobs;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Files {
    private static Files instance;
    private File configFile, languageFile, dataFile, itemsFile, storageFile, priceFile;
    private FileConfiguration config, language, data, items, storage, price;

    public static Files getInstance() {

        if (instance == null) {
            instance = new Files();
        }
        return instance;
    }

    public void createconfig() {
        configFile = new File(Jobs.getInstance().getDataFolder(), "config.yml");
        languageFile = new File(Jobs.getInstance().getDataFolder(), "language.yml");
        dataFile = new File(Jobs.getInstance().getDataFolder(), "data.yml");
        itemsFile = new File(Jobs.getInstance().getDataFolder(), "items.yml");
        storageFile = new File(Jobs.getInstance().getDataFolder(), "storage.yml");
        priceFile = new File(Jobs.getInstance().getDataFolder(), "price.yml");

        if (!configFile.exists()) Jobs.getInstance().saveResource("config.yml", false);
        if (!languageFile.exists()) Jobs.getInstance().saveResource("language.yml", false);
        if (!dataFile.exists()) Jobs.getInstance().saveResource("data.yml", false);
        if (!itemsFile.exists()) Jobs.getInstance().saveResource("items.yml", false);
        if (!storageFile.exists()) Jobs.getInstance().saveResource("storage.yml", false);
        if (!priceFile.exists()) Jobs.getInstance().saveResource("price.yml", false);
        config = new YamlConfiguration();
        language = new YamlConfiguration();
        data = new YamlConfiguration();
        items = new YamlConfiguration();
        storage = new YamlConfiguration();
        price = new YamlConfiguration();

        try {
            config.load(configFile);
            language.load(languageFile);
            data.load(dataFile);
            items.load(itemsFile);
            storage.load(storageFile);
            price.load(priceFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    public FileConfiguration getconfig() {
        return config;
    }

    public FileConfiguration getlanguage() {
        return language;
    }

    public FileConfiguration getdata() {
        return data;
    }

    public FileConfiguration getitems() {
        return items;
    }

    public FileConfiguration getstorage() {
        return storage;
    }

    public FileConfiguration getprice() {
        return price;
    }

    public void reloadconfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
        language = YamlConfiguration.loadConfiguration(languageFile);
        items = YamlConfiguration.loadConfiguration(itemsFile);
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

    public void saveitems() {
        try {
            items.save(itemsFile);
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

    public int getRandomNumber(int min, int max) {
        Random r = new Random();
        int randomNumber = r.nextInt(max - min) + min;
        return randomNumber;
    }

    public String getJobs(Player p) {
        return getdata().getString("players." + p.getName() + ".Jobs");
    }

    public int getPower(Player p) {
        return getdata().getInt("players." + p.getName() + ".Power");
    }

    public void setJobs(Player p, String name) {
        getdata().set("players." + p.getName() + ".Jobs", name);
        if (getXP(p) == 0) {
            setXP(p, 0);
        }
        savedata();
    }

    public int getAge(Player p) {
        return getdata().getInt("players." + p.getName() + ".Age");
    }

    public int getXP(Player p) {
        return getdata().getInt("players." + p.getName() + ".XP");
    }

    public void setAge(Player p, int number) {
        getdata().set("players." + p.getName() + ".Age", number);
        savedata();
    }

    public void setXP(Player p, int number) {
        getdata().set("players." + p.getName() + ".XP", number);
        savedata();
    }

    public void addAge(Player p, int number) {
        getdata().set("players." + p.getName() + ".Age", getAge(p) + number);
        savedata();
        addMaxPower(p, getconfig().getInt("Per_Max_Power"));
        addRestorePower(p, getconfig().getInt("Per_Restore_Power"));
        for (String age_up : Files.getInstance().getlanguage().getStringList("AGE_UP")) {
            p.sendMessage(Files.getInstance().convert(age_up
                    .replaceAll("%mpower%", String.valueOf(getMaxPower(p)))
                    .replaceAll("%age%", String.valueOf(getAge(p)))
                    .replaceAll("%rpower%", String.valueOf(getRestorePower(p)))));
        }
    }

    public void addXP(Player p, int number) {
        getdata().set("players." + p.getName() + ".XP", getXP(p) + number);
        checkLevelUp(p);
        savedata();
    }

    public void removeAge(Player p, int number) {
        if (getAge(p) > number) {
            getdata().set("players." + p.getName() + ".Age", getAge(p) - number);
            savedata();
        } else {
            getdata().set("players." + p.getName() + ".Age", 1);
            savedata();
        }
    }

    public void removeXP(Player p, int number) {
        if (getXP(p) > number) {
            getdata().set("players." + p.getName() + ".XP", getXP(p) - number);
            savedata();
        } else {
            getdata().set("players." + p.getName() + ".XP", 0);
            savedata();
        }
    }

    public void setPower(Player p, int number) {
        getdata().set("players." + p.getName() + ".Power", number);
        savedata();
    }

    public void addPower(Player p, int number) {
        getdata().set("players." + p.getName() + ".Power", getPower(p) + number);
        savedata();
    }


    public void removePower(Player p, int number) {
        if (getPower(p) > number) {
            getdata().set("players." + p.getName() + ".Power", getPower(p) - number);
            savedata();
        } else {
            getdata().set("players." + p.getName() + ".Power", 0);
            savedata();
        }
    }

    public String getGang(Player p) {
        return getdata().getString("players." + p.getName() + ".Gang");
    }

    public void selectGang(Player p, String name) {
        if (getGang(p) != null) {
            removeMemberGang(p);
        }
        getdata().set("players." + p.getName() + ".Gang", name.toUpperCase());
        if (getLevelGang(p) == 0) {
            setLevelGang(p);
        }
        addMemberGang(p);
        if (getleveluptimes(p) == 0) {
            setLeveluptimes(p, 0);
        }
        savedata();
    }

    public int getMemberGang(Player p) {
        return getdata().getInt("gangs." + getGang(p) + ".Member");
    }

    public void removeMemberGang(Player p) {
        getdata().set("gangs." + getGang(p) + ".Member", getMemberGang(p) - 1);
        savedata();
    }

    public void addMemberGang(Player p) {
        getdata().set("gangs." + getGang(p) + ".Member", getMemberGang(p) + 1);
        savedata();
    }

    public int getLevelGang(Player p) {
        return getdata().getInt("gangs." + getGang(p) + ".Level");
    }

    public void addLevelGang(Player p, Integer number) {
        getdata().set("gangs." + getGang(p) + ".Level", getLevelGang(p) + number);
        savedata();
    }

    public void setLevelGang(Player p) {
        getdata().set("gangs." + getGang(p) + ".Level", 1);
        savedata();
    }


    public void setLevelGang(Player p, Integer number) {
        getdata().set("gangs." + getGang(p) + ".Level", number);
        savedata();
    }

    public void removeLevelGang(Player p, Integer number) {
        if (getLevelGang(p) > 1) {
            getdata().set("gangs." + getGang(p) + ".Level", getLevelGang(p) - number);
        } else {
            getdata().set("gangs." + getGang(p) + ".Level", 1);
        }
        savedata();
    }

    public void checkLevelUp(Player p) {
        if (getXP(p) >= (((getAge(p) + getLevelGang(p)) / 2)) * 1000) {
            addLevelGang(p, 1);
            setXP(p, 0);
            addLeveluptimes(p, 1);
            p.sendMessage(convert(Objects.requireNonNull(getlanguage().getString("LEVEL_UP_GANG"))));
        }
    }

    public int getleveluptimes(Player p) {
        return getdata().getInt("gangs." + getGang(p) + ".LevelUpTimes." + p.getName());
    }

    public void addLeveluptimes(Player p, Integer number) {
        getdata().set("gangs." + getGang(p) + ".LevelUpTimes." + p.getName(), getleveluptimes(p) + number);
        savedata();
    }

    public void removeLeveluptimes(Player p, Integer number) {
        if (getleveluptimes(p) > 1) {
            getdata().set("gangs." + getGang(p) + ".LevelUpTimes." + p.getName(), getleveluptimes(p) - number);
        } else {
            getdata().set("gangs." + getGang(p) + ".LevelUpTimes." + p.getName(), 0);
        }
        savedata();
    }

    public void setLeveluptimes(Player p, Integer number) {
        getdata().set("gangs." + getGang(p) + ".LevelUpTimes." + p.getName(), number);
        savedata();
    }

    public int getMaxPower(Player p) {
        return getdata().getInt("players." + p.getName() + ".MaxPower");
    }

    public void setMaxPower(Player p, int number) {
        getdata().set("players." + p.getName() + ".MaxPower", number);
        savedata();
    }

    public void addMaxPower(Player p, int number) {
        getdata().set("players." + p.getName() + ".MaxPower", getMaxPower(p) + number);
        savedata();
    }

    public void removeMaxPower(Player p, int number) {
        if (getMaxPower(p) > getconfig().getInt("Max_Power")) {
            getdata().set("players." + p.getName() + ".MaxPower", getMaxPower(p) - number);
        } else {
            getdata().set("players." + p.getName() + ".MaxPower", getconfig().getInt("Max_Power"));
        }
        savedata();
    }

    public int getRestorePower(Player p) {
        return getdata().getInt("players." + p.getName() + ".RestorePower");
    }

    public void setRestorePower(Player p, int number) {
        getdata().set("players." + p.getName() + ".RestorePower", number);
        savedata();
    }

    public void addRestorePower(Player p, int number) {
        getdata().set("players." + p.getName() + ".RestorePower", getRestorePower(p) + number);
        savedata();
    }

    public void removeRestorePower(Player p, int number) {
        if (getMaxPower(p) > getconfig().getInt("Restore_Power")) {
            getdata().set("players." + p.getName() + ".RestorePower", getRestorePower(p) - number);
        } else {
            getdata().set("players." + p.getName() + ".RestorePower", getconfig().getInt("Restore_Power"));
        }
        savedata();
    }

    public int getLuckyChance(Player p) {
        return getdata().getInt("players." + p.getName() + ".LuckyChance");
    }

    public void setLuckyChance(Player p, int number) {
        getdata().set("players." + p.getName() + ".LuckyChance", number);
        savedata();
    }

    public void addLuckyChance(Player p, int number) {
        getdata().set("players." + p.getName() + ".LuckyChance", getLuckyChance(p) + number);
        savedata();
    }

    public void removeLuckyChance(Player p, int number) {
        if (getLuckyChance(p) > getconfig().getInt("Default_Lucky_Chance")) {
            getdata().set("players." + p.getName() + ".LuckyChance", getLuckyChance(p) - number);
        } else {
            getdata().set("players." + p.getName() + ".LuckyChance", getconfig().getInt("Default_Lucky_Chance"));
        }
        savedata();
    }

    public int getWeight(Player p) {
        return getdata().getInt("players." + p.getName() + ".Weight");
    }

    public void setWeight(Player p, int number) {
        getdata().set("players." + p.getName() + ".Weight", number);
        savedata();
    }

    public void addWeight(Player p, int number) {
        getdata().set("players." + p.getName() + ".Weight", getWeight(p) + number);
        savedata();
    }

    public void removeWeight(Player p, int number) {
        if (getWeight(p) > 1) {
            getdata().set("players." + p.getName() + ".Weight", getWeight(p) - number);
        } else {
            getdata().set("players." + p.getName() + ".Weight", 0);
        }
        savedata();
    }

    public int getMaxWeight(Player p) {
        return getdata().getInt("players." + p.getName() + ".MaxWeight");
    }


    public void setMaxWeight(Player p, int number) {
        getdata().set("players." + p.getName() + ".MaxWeight", number);
        savedata();
    }

    public void addMaxWeight(Player p, int number) {
        getdata().set("players." + p.getName() + ".MaxWeight", getMaxWeight(p) + number);
        savedata();
    }

    public void removeMaxWeight(Player p, int number) {
        if (getMaxWeight(p) > 10) {
            getdata().set("players." + p.getName() + ".MaxWeight", getMaxWeight(p) - number);
        } else {
            getdata().set("players." + p.getName() + ".MaxWeight", 10);
        }
        savedata();
    }

    public int getStorage(Player p, String name) {
        return getstorage().getInt("players." + p.getName() + ".Blocks." + name);
    }

    public void setStorage(Player p, String name, Integer number) {
        getstorage().set("players." + p.getName() + ".Blocks." + name, number);
        savestorage();
    }

    public void addStorage(Player p, String name, Integer number) {
        getstorage().set("players." + p.getName() + ".Blocks." + name, getStorage(p, name) + number);
        savestorage();
    }

    public void removeStorage(Player p, String name, Integer number) {
        getstorage().set("players." + p.getName() + ".Blocks." + name, getStorage(p, name) - number);
        savestorage();
    }

    public String convert(String s) {
        return s.replaceAll("&", "§");
    }
}
