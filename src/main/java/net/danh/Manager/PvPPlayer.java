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
}
