package net.danh.Manager;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Event {

    public static HashMap<Player, Integer> blockevent = new HashMap<>();

    public static @NotNull Integer getBlockEvent(@NotNull Player p) {
        return blockevent.get(p);
    }

    /*
    Data.getdatafile().getInt("players." + p.getName() + ".Event.Blocks");
     */
    public static void setBlocksEvent(@NotNull Player p, Integer number) {
        blockevent.put(p, number);
    }

    public static void addBlocksEvent(@NotNull Player p, Integer number) {
        blockevent.replace(p, getBlockEvent(p) + number);
    }

    public static void removeBlocksEvent(@NotNull Player p, Integer number) {
        if ((getBlockEvent(p) - number) > 0) {
            blockevent.replace(p, getBlockEvent(p) - number);
        } else {
            blockevent.put(p, 0);
        }
    }

    public static void SaveEvent(@NotNull Player p) {
        Data.getdatafile().set("players." + p.getName() + ".Event.Blocks", getBlockEvent(p));
        Data.savedata();
    }

    public static int getBEData(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".Event.Blocks");
    }
}
