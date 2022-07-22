package net.danh.Manager;

import net.danh.PlayerData.PData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class QPoints {
    private static final HashMap<Player, Integer> qp = new HashMap<>();

    public static int getOldQPData(@NotNull String name) {
        return Data.getdatafile().getInt("players" + name + ".QPoints");
    }

    public static int getQPData(@NotNull Player p) {
        return new PData(p.getName()).getConfig().getInt("qpoints");
    }

    public static int getQP(Player p) {
        return qp.get(p);
    }

    public static void setQP(Player p, Integer amount) {
        qp.put(p, Math.max(amount, 0));
    }

    public static void addQP(Player p, Integer amount) {
        qp.replace(p, getQP(p) + amount);
    }

    public static void removeQP(Player p, Integer amount) {
        if (getQP(p) - amount > 0) {
            qp.replace(p, getQP(p) - amount);
        } else {
            qp.put(p, 0);
        }
    }
}
