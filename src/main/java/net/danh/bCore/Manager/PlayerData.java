package net.danh.bCore.Manager;

import org.bukkit.entity.Player;

public class PlayerData {

    public static int getLevel(Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".Level");
    }

    public static int getXP(Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".XP");
    }

    public static void setLevel(Player p, int number) {
        Data.getdatafile().set("players." + p.getName() + ".Level", number);
        Data.savedata();
    }

    public static void setXP(Player p, int number) {
        Data.getdatafile().set("players." + p.getName() + ".XP", number);
        Data.savedata();
    }

    public static void addLevel(Player p, int number) {
        Data.getdatafile().set("players." + p.getName() + ".Level", getLevel(p) + number);
        Data.savedata();
        Power.addMaxPower(p, Data.getconfigfile().getInt("Per_Max_Power"));
        Power.addRestorePower(p, Data.getconfigfile().getInt("Per_Restore_Power"));
    }

    public static void addXP(Player p, int number) {
        Data.getdatafile().set("players." + p.getName() + ".XP", getXP(p) + number);
        checkLevelUp(p);
        Data.savedata();
    }

    public static void removeLevel(Player p, int number) {
        if (getLevel(p) > number) {
            Data.getdatafile().set("players." + p.getName() + ".Level", getLevel(p) - number);
            Data.savedata();
        } else {
            Data.getdatafile().set("players." + p.getName() + ".Level", 1);
            Data.savedata();
        }
    }

    public static void removeXP(Player p, int number) {
        if (getXP(p) > number) {
            Data.getdatafile().set("players." + p.getName() + ".XP", getXP(p) - number);
            Data.savedata();
        } else {
            Data.getdatafile().set("players." + p.getName() + ".XP", 0);
            Data.savedata();
        }
    }


    public static void checkLevelUp(Player p) {
        if (getXP(p) > getLevel(p) * 10000) {
            setXP(p, 0);
            addLevel(p, 1);
            addLeveluptimes(p, 1);
            for (String age_up : Data.getlanguagefile().getStringList("LEVEL_UP")) {
                p.sendMessage(Data.convert(age_up
                        .replaceAll("%mpower%", String.valueOf(Power.getMaxPower(p)))
                        .replaceAll("%level%", String.valueOf(getLevel(p)))
                        .replaceAll("%rpower%", String.valueOf(Power.getRestorePower(p)))));
            }
        }
    }

    public static int getleveluptimes(Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".LevelUpTimes");
    }

    public static void addLeveluptimes(Player p, Integer number) {
        Data.getdatafile().set("players." + p.getName() + ".LevelUpTimes", getleveluptimes(p) + number);
        Data.savedata();
    }

    public static void removeLeveluptimes(Player p, Integer number) {
        if (getleveluptimes(p) > 1) {
            Data.getdatafile().set("players." + p.getName() + ".LevelUpTimes", getleveluptimes(p) - number);
        } else {
            Data.getdatafile().set("players." + p.getName() + ".LevelUpTimes", 0);
        }
        Data.savedata();
    }

    public static void setLeveluptimes(Player p, Integer number) {
        Data.getdatafile().set("players." + p.getName() + ".LevelUpTimes", number);
        Data.savedata();
    }

}
