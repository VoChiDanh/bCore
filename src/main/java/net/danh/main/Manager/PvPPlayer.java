package net.danh.main.Manager;

import org.bukkit.entity.Player;

public class PvPPlayer {

    public static boolean getPvPStatus(Player p) {
        return Data.getdatafile().getBoolean("players." + p.getName() + ".PvP");
    }

    public static void PvPToggle(Player p) {
        Data.getdatafile().set("players." + p.getName() + ".PvP", !getPvPStatus(p));
        Data.savedata();
    }

    public static void setPvPStatus(Player p, Boolean Boolean) {
        Data.getdatafile().set("players." + p.getName() + ".PvP", Boolean);
        Data.savedata();
    }
}
