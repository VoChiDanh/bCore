package net.danh.Manager;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Points {
    public static HashMap<Player, Integer> points = new HashMap<>();

    public static int getPvPPointsData(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".Points");
    }

    public static int getPvPPoints(@NotNull Player p) {
        return points.get(p);
    }

    public static void setPvPPoints(@NotNull Player p, int amount) {
        points.put(p, amount);
    }

    public static void addPvPPoints(@NotNull Player p, int amount) {
        p.sendMessage(Data.convert("&a+" + amount + " Điểm PvP"));
        points.replace(p, getPvPPoints(p) + amount);
    }

    public static void removePvPPoints(@NotNull Player p, int amount) {
        p.sendMessage(Data.convert("&c-" + amount + " Điểm PvP"));
        points.replace(p, Math.max(getPvPPoints(p) - amount, 0));
    }

}
