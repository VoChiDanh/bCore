package net.danh.Manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;

public class Credit {
    public static HashMap<Player, Double> credit = new HashMap<>();

    public static double getRealCredit(Player p) {
        return getCredit(p) - 100;
    }

    public static int getIntCredit(Player p) {
        return (int) getRealCredit(p);
    }

    public static double getCreditData(@NotNull Player p) {
        return Data.getdatafile().getDouble("players." + p.getName() + ".Credit");
    }
    public static void SaveCredit(@NotNull Player p) {
        Data.getdatafile().set("players." + p.getName() + ".Credit", getCredit(p));
        Data.savedata();
    }

    public static double getCredit(@NotNull Player p) {
        return credit.get(p);
    }

    public static void setCredit(@NotNull Player p, Double amount) {
        credit.put(p, amount);
    }

    public static void addCredit(@NotNull Player p, Double amount) {
        if ((getCredit(p) + amount) <= 200D) {
            credit.replace(p, getCredit(p) + amount);
            p.sendMessage(Data.convert("&a+ " + amount + " Uy tín"));
        }
    }

    public static void removeCredit(@NotNull Player p, Double amount) {
        if ((getCredit(p) - amount) >= 0) {
            credit.replace(p, getCredit(p) - amount);
            p.sendMessage(Data.convert("&c- " + amount + " Uy tín"));
        } else {
            Objects.requireNonNull(Bukkit.getPlayer(p.getName())).sendMessage(Data.convert(Data.getlanguagefile().getString("LOW_CREDIT")));
        }
    }
}
