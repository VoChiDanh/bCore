package net.danh.Manager;

import net.danh.PlayerData.PData;
import net.danh.dcore.Indicator.Hologram;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Power {

    public static HashMap<Player, Integer> power = new HashMap<>();
    public static HashMap<Player, Integer> restorepower = new HashMap<>();
    public static HashMap<Player, Integer> maxpower = new HashMap<>();

    public static int getOldPowerData(@NotNull String name) {
        return Data.getdatafile().getInt("players." + name + ".Power");
    }

    public static int getPowerData(@NotNull Player p) {
        return new PData(p.getName()).getConfig().getInt("power.default");
    }

    public static int getPower(@NotNull Player p) {
        return power.get(p);
    }

    public static void setPower(@NotNull Player p, int number) {
        power.put(p, number);
    }

    public static void addPower(@NotNull Player p, int number) {
        if (getPower(p) + number <= getMaxPower(p)) {
            power.replace(p, getPower(p) + number);
            Hologram.create(p, "&a+" + number + " Power");
        } else {
            power.put(p, getMaxPower(p));
            Hologram.create(p, "&a+" + getMaxPower(p) + " Power");
        }
    }


    public static void removePower(Player p, int number) {
        if (getPower(p) > number) {
            power.replace(p, getPower(p) - number);
            Hologram.create(p, "&c-" + number + " Power");
        } else {
            power.put(p, 0);
            Hologram.create(p, "&c-" + getPower(p) + " Power");
        }
    }

    public static int getOldMaxPowerData(@NotNull String name) {
        return Data.getdatafile().getInt("players." + name + ".MaxPower");
    }

    public static int getMaxPowerData(@NotNull Player p) {
        return new PData(p.getName()).getConfig().getInt("power.max");
    }

    public static int getMaxPower(@NotNull Player p) {
        return maxpower.get(p);
    }

    public static void setMaxPower(@NotNull Player p, int number) {
        maxpower.put(p, Math.max(Data.getconfigfile().getInt("Max_Power"), number));
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

    public static int getOldRestorePowerData(@NotNull String name) {
        return Data.getdatafile().getInt("players." + name + ".RestorePower");
    }

    public static int getRestorePowerData(@NotNull Player p) {
        return new PData(p.getName()).getConfig().getInt("power.restore");
    }

    public static int getRestorePower(@NotNull Player p) {
        return restorepower.get(p);
    }

    public static void setRestorePower(@NotNull Player p, int number) {
        restorepower.put(p, Math.max(Data.getconfigfile().getInt("Restore_Power"), number));
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
