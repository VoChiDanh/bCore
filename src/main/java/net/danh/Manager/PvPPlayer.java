package net.danh.Manager;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PvPPlayer {

    public static boolean getPvPStatus(@NotNull Player p) {
        return Data.getdatafile().getBoolean("players." + p.getName() + ".PvP");
    }

    public static void PvPToggle(@NotNull Player p) {
        Data.getdatafile().set("players." + p.getName() + ".PvP", !getPvPStatus(p));
        Data.savedata();
    }

    public static void setPvPStatus(@NotNull Player p, Boolean Boolean) {
        Data.getdatafile().set("players." + p.getName() + ".PvP", Boolean);
        Data.savedata();
    }

    public static boolean getFirstJoin(@NotNull Player p) {
        return !Data.getdatafile().getBoolean("players." + p.getName() + ".First-Join.PvP.Status");
    }

    public static void setFirstJoinStatus(@NotNull Player p, Boolean status) {
        Data.getdatafile().set("players." + p.getName() + ".First-Join.PvP.Status", status);
        Data.savedata();
    }

    public static int getProtectTime(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".First-Join.PvP.Protect-Time");
    }

    public static void setProtectTime(@NotNull Player p, Integer times) {
        Data.getdatafile().set("players." + p.getName() + ".First-Join.PvP.Protect-Time", times);
        Data.savedata();
    }
}
