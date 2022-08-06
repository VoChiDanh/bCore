package net.danh.PClass;

import net.danh.Manager.Data;
import net.danh.PlayerData.PData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class PCPoints {
    private static final HashMap<Player, Integer> PCP = new HashMap<>();

    public static int getOldPCPData(@NotNull String name) {
        return Data.getdatafile().getInt("players" + name + ".PCPoints");
    }

    public static int getPCPData(@NotNull Player p) {
        return new PData(p.getName()).getConfig().getInt("PCPoints");
    }

    public static int getPCP(Player p) {
        return PCP.get(p);
    }

    public static void setPCP(Player p, Integer amount) {
        PCP.put(p, Math.max(amount, 0));
    }

    public static void addPCP(Player p, Integer amount) {
        PCP.replace(p, getPCP(p) + amount);
    }

    public static void removePCP(Player p, Integer amount) {
        if (getPCP(p) - amount > 0) {
            PCP.replace(p, getPCP(p) - amount);
        } else {
            PCP.put(p, 0);
        }
    }
}
