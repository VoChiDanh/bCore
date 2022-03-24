package net.danh.Contest;

import net.danh.Contest.Mining.MiningComparator;
import net.danh.Contest.Mining.MiningManager;
import net.danh.Manager.Data;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class LeaderBoard {

    public static @NotNull List<MiningManager> getLdMining() {
        List<MiningManager> tMinings = new ArrayList<>();
        Set<String> playername = Objects.requireNonNull(Data.getdatafile().getConfigurationSection("players.")).getKeys(false);
        for (String name : playername) {
            String pname = Data.getdatafile().getString("players." + name + ".name");
            int block = Data.getdatafile().getInt("players." + name + ".Event.Blocks");
            tMinings.add(new MiningManager(Bukkit.getPlayer(Objects.requireNonNull(pname)), block));
        }
        //Sort the list using a custom comparator
        tMinings.sort(new MiningComparator());
        Collections.reverse(tMinings);
        return tMinings;
    }

}
