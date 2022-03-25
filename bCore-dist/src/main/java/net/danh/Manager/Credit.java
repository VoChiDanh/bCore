package net.danh.Manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Credit {

    public static double getRealCredit(Player p) {
        return getCredit(p) - 100;
    }

    public static int getIntCredit(Player p) {
        return (int) getRealCredit(p);
    }

    public static double getCredit(@NotNull Player p) {
        return Data.getdatafile().getDouble("players." + p.getName() + ".Credit");
    }

    public static boolean getType(@NotNull Player p) {
        return Data.getdatafile().getBoolean("players." + p.getName() + ".CType");
    }

    public static void setType(@NotNull Player p, Boolean type) {
        Data.getdatafile().set("players." + p.getName() + ".CType", type);
        Data.savedata();
    }

    public static void setCredit(@NotNull Player p, Double amount) {
        Data.getdatafile().set("players." + p.getName() + ".Credit", amount);
        Data.savedata();
    }

    public static void addCredit(@NotNull Player p, Double amount) {
        if ((getCredit(p) + amount) <= 200D) {
            if ((getCredit(p) + amount) <= 200D && (getCredit(p) + amount) >= 100D) {
                setType(p, true);
            } else {
                if ((getCredit(p) + amount) < 100D && (getCredit(p) + amount) > 0D) {
                    setType(p, false);
                }
            }
            Data.getdatafile().set("players." + p.getName() + ".Credit", getCredit(p) + amount);
        }
        Data.savedata();
    }

    public static void removeCredit(@NotNull Player p, Double amount) {
        if ((getCredit(p) - amount) >= 0) {
            if ((getCredit(p) - amount) <= 200D && (getCredit(p) - amount) >= 100D) {
                setType(p, true);
            } else {
                if ((getCredit(p) - amount) < 100D && (getCredit(p) - amount) > 0D) {
                    setType(p, false);
                }
            }
            Data.getdatafile().set("players." + p.getName() + ".Credit", getCredit(p) - amount);
        } else {
            Objects.requireNonNull(Bukkit.getPlayer(p.getName())).sendMessage(Data.convert(Data.getlanguagefile().getString("LOW_CREDIT")));
        }
        Data.savedata();
    }
}
