package net.danh.Manager;

import net.danh.PlayerData.PData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import static net.danh.dcore.Utils.Player.sendPlayerMessage;

public class Credit {
    public static HashMap<Player, Double> credit = new HashMap<>();

    public static double getRealCredit(Player p) {
        return getCredit(p) - 100;
    }

    public static int getIntCredit(Player p) {
        return (int) getRealCredit(p);
    }

    public static double getOldCreditData(@NotNull String name) {
        return Data.getdatafile().getDouble("players." + name + ".Credit");
    }

    public static double getCreditData(@NotNull Player p) {
        return new PData(p.getName()).getConfig().getDouble("players." + p.getName() + ".Credit");
    }

    public static double getCredit(@NotNull Player p) {
        return credit.get(p);
    }

    public static void setCredit(@NotNull Player p, Double amount) {
        if (amount < 200D) {
            credit.put(p, Math.max(100D, amount));
        } else {
            credit.put(p, 200D);
        }
    }

    public static void addCredit(@NotNull Player p, Double amount) {
        if ((getCredit(p) + amount) <= 200D) {
            credit.replace(p, getCredit(p) + amount);
            sendPlayerMessage(p, "&a+ " + amount + " Uy tín");
        }
    }

    public static void removeCredit(@NotNull Player p, Double amount) {
        if ((getCredit(p) - amount) >= 0) {
            credit.replace(p, getCredit(p) - amount);
            sendPlayerMessage(p, "&c- " + amount + " Uy tín");
        } else {
            sendPlayerMessage(p, Data.getlanguagefile().getString("LOW_CREDIT"));
        }
    }
}
