package net.danh.Manager;

import net.danh.PlayerData.PData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import static net.danh.dcore.Utils.Player.sendPlayerMessage;

public class Points {
    public static HashMap<Player, Integer> points = new HashMap<>();

    public static int getOldPvPPointsData(@NotNull String name) {
        return Data.getdatafile().getInt("players." + name + ".Points");
    }

    public static int getPvPPointsData(@NotNull Player p) {
        return new PData(p.getName()).getConfig().getInt("points");
    }

    public static int getPvPPoints(@NotNull Player p) {
        return points.get(p);
    }

    public static void setPvPPoints(@NotNull Player p, int amount) {
        points.put(p, Math.max(amount, 0));
    }

    public static void addPvPPoints(@NotNull Player p, int amount) {
        sendPlayerMessage(p, "&a+" + amount + " Điểm PvP");
        points.replace(p, getPvPPoints(p) + amount);
    }

    public static void removePvPPoints(@NotNull Player p, int amount) {
        sendPlayerMessage(p, "&c-" + amount + " Điểm PvP");
        points.replace(p, Math.max(getPvPPoints(p) - amount, 0));
    }

}
