package net.danh.Manager;

import net.danh.PlayerData.PData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Status {
    public static HashMap<Player, Boolean> pvpstatus = new HashMap<>();

    public static boolean getOldPvPStatusData(@NotNull String name) {
        return Data.getdatafile().getBoolean("players." + name + ".Status");
    }

    public static boolean getPvPStatusData(@NotNull Player p) {
        return new PData(p.getName()).getConfig().getBoolean("status");
    }

    public static boolean getPvPStatus(@NotNull Player p) {
        return pvpstatus.get(p);
    }

    public static void TogglePvP(@NotNull Player p, boolean status) {
        pvpstatus.put(p, status);
    }
}
