package net.danh.Manager;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Status {
    public static HashMap<Player, Boolean> pvpstatus = new HashMap<>();

    public static boolean getPvPStatusData(@NotNull Player p) {
        return Data.getdatafile().getBoolean("players." + p.getName() + ".Status");
    }

    public static boolean getPvPStatus(@NotNull Player p) {
        return pvpstatus.get(p);
    }

    public static void TogglePvP(@NotNull Player p, boolean status) {
        pvpstatus.put(p, status);
        Points.removePvPPoints(p, Data.getconfigfile().getInt("PVP.POINTS_TOGGLE"));
    }
}
