package net.danh.Manager;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Power {

    public static HashMap<Player, Integer> power = new HashMap<>();
    public static HashMap<Player, Integer> restorepower = new HashMap<>();
    public static HashMap<Player, Integer> maxpower = new HashMap<>();

    public static int getPowerData(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".Power");
    }

    public static int getPower(@NotNull Player p) {
        return power.get(p);
    }


    public static void SavePower(@NotNull Player p) {
        Data.getdatafile().set("players." + p.getName() + ".Power", getPower(p));
        Data.savedata();
    }

    public static void setPower(@NotNull Player p, int number) {
        power.put(p, number);
    }

    public static void addPower(@NotNull Player p, int number) {
        if (getPower(p) + number <= getMaxPower(p)) {
            power.replace(p, getPower(p) + number);
        } else {
            power.put(p, getMaxPower(p));
        }
        p.sendMessage(Data.convert("&a+ " + number + " Power"));
    }


    public static void removePower(Player p, int number) {
        if (getPower(p) > number) {
            power.replace(p, getPower(p) - number);
        } else {
            power.put(p, 0);
        }
        p.sendMessage(Data.convert("&c- " + number + " Power"));
    }

    public static int getMaxPowerData(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".MaxPower");
    }

    public static void SaveMaxPower(@NotNull Player p) {
        Data.getdatafile().set("players." + p.getName() + ".MaxPower", getMaxPower(p));
        Data.savedata();
    }

    public static int getMaxPower(@NotNull Player p) {
        return maxpower.get(p);
    }

    public static void setMaxPower(@NotNull Player p, int number) {
        maxpower.put(p, number);
    }

    public static void addMaxPower(@NotNull Player p, int number) {
        maxpower.replace(p, getMaxPower(p) + number);
    }

    public static void removeMaxPower(Player p, int number) {
        if (getMaxPower(p) > Data.getconfigfile().getInt("Max_Power")) {
            maxpower.replace(p, getMaxPower(p) - number);
        } else {
            maxpower.replace(p, Data.getconfigfile().getInt("Max_Power"));
        }
    }

    public static int getRestorePowerData(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".RestorePower");
    }

    public static void SaveRestorePower(@NotNull Player p) {
        Data.getdatafile().set("players." + p.getName() + ".RestorePower", getRestorePower(p));
        Data.savedata();
    }

    public static int getRestorePower(@NotNull Player p) {
        return restorepower.get(p);
    }

    public static void setRestorePower(@NotNull Player p, int number) {
        restorepower.put(p, number);
    }

    public static void addRestorePower(@NotNull Player p, int number) {
        restorepower.replace(p, getRestorePower(p) + number);
    }

    public static void removeRestorePower(Player p, int number) {
        if (getMaxPower(p) > Data.getconfigfile().getInt("Restore_Power")) {
            restorepower.replace(p, getRestorePower(p) - number);
        } else {
            restorepower.put(p, Data.getconfigfile().getInt("Restore_Power"));
        }
    }

}
