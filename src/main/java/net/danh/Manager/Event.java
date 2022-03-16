package net.danh.Manager;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Event {

    public static @NotNull Integer getBlockEvent(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".Event.Blocks");
    }

    public static void setBlocksEvent(@NotNull Player p, Integer number) {
        Data.getdatafile().set("players." + p.getName() + ".Event.Blocks", number);
        Data.savedata();
    }

    public static void addBlocksEvent(@NotNull Player p, Integer number) {
        Data.getdatafile().set("players." + p.getName() + ".Event.Blocks", getBlockEvent(p) + number);
        Data.savedata();
    }

    public static void removeBlocksEvent(@NotNull Player p, Integer number) {
        Data.getdatafile().set("players." + p.getName() + ".Event.Blocks", getBlockEvent(p) - number);
        Data.savedata();
    }

    public static @NotNull Integer getKillEvent(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".Event.Kills");
    }

    public static void setKillEvent(@NotNull Player p, Integer number) {
        Data.getdatafile().set("players." + p.getName() + ".Event.Kills", number);
        Data.savedata();
    }

    public static void addKillEvent(@NotNull Player p, Integer number) {
        Data.getdatafile().set("players." + p.getName() + ".Event.Kills", getKillEvent(p) + number);
        Data.savedata();
    }

    public static void removeKillEvent(@NotNull Player p, Integer number) {
        Data.getdatafile().set("players." + p.getName() + ".Event.Kills", getKillEvent(p) - number);
        Data.savedata();
    }

}
