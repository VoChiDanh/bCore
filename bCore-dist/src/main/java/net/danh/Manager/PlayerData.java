package net.danh.Manager;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerData {

    public static int getLevel(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".Level");
    }

    public static int getXP(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".XP");
    }

    public static void setLevel(@NotNull Player p, int number) {
        Data.getdatafile().set("players." + p.getName() + ".Level", number);
        Data.savedata();
    }

    public static void setXP(@NotNull Player p, int number) {
        Data.getdatafile().set("players." + p.getName() + ".XP", number);
        Data.savedata();
    }

    public static void addLevel(@NotNull Player p, int number) {
        Data.getdatafile().set("players." + p.getName() + ".Level", getLevel(p) + number);
        Power.addMaxPower(p, Data.getconfigfile().getInt("Per_Max_Power"));
        Power.addRestorePower(p, Data.getconfigfile().getInt("Per_Restore_Power"));
        Data.savedata();
        p.sendMessage(Data.convert("&aChúc mừng bạn đã lên cấp " + getLevel(p)));
    }

    public static void addXP(@NotNull Player p, int number) {
        Data.getdatafile().set("players." + p.getName() + ".XP", getXP(p) + number);
        checkLevelUp(p);
        Data.savedata();
        p.sendMessage(Data.convert("&a+ " + number + " XP"));
    }

    public static void removeLevel(Player p, int number) {
        if (getLevel(p) > number) {
            Data.getdatafile().set("players." + p.getName() + ".Level", getLevel(p) - number);
        } else {
            Data.getdatafile().set("players." + p.getName() + ".Level", 1);
        }
        Data.savedata();
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
            p.sendMessage(Data.convert("&aChúc mừng bạn đã lên cấp độ " + getLevel(p)));
        }
    }

    public static int getleveluptimes(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".LevelUpTimes");
    }

    public static void addLeveluptimes(@NotNull Player p, Integer number) {
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

    public static void setLeveluptimes(@NotNull Player p, Integer number) {
        Data.getdatafile().set("players." + p.getName() + ".LevelUpTimes", number);
        Data.savedata();
    }

    @Deprecated
    public static Integer getPlayerLimit(Player p, String perm) {
        int limit = 0;
        for (int i = 0; i <= 1000000000; i++) {
            if (p.hasPermission(perm + "." + i)) {
                if (limit < i) {
                    limit = i;
                }
            }
        }
        return limit;
    }

    public static @NotNull Integer getBlocks(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".Blocks");
    }

    public static void setBlocks(@NotNull Player p, Integer number) {
        Data.getdatafile().set("players." + p.getName() + ".Blocks", number);
        Data.savedata();
    }

    public static void addBlocks(@NotNull Player p, Integer number) {
        Data.getdatafile().set("players." + p.getName() + ".Blocks", getBlocks(p) + number);
        Data.savedata();
    }

    public static void removeBlocks(@NotNull Player p, Integer number) {
        Data.getdatafile().set("players." + p.getName() + ".Blocks", getBlocks(p) - number);
        Data.savedata();
    }

}
