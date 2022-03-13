package net.danh.Contest;

import net.danh.Manager.Data;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class LDMining {

    public static @NotNull List<TMining> getLeaderboard() {

        //Create an empty list of your new object
        List<TMining> tMinings = new ArrayList<>();

        //Get every (PlayerUUID) from your configuration file
        Set<String> playername = Objects.requireNonNull(Data.getdatafile().getConfigurationSection("players.")).getKeys(false);

        //Generate fill list of TopKiller using the configuration file
        for (String name : playername) {
            String pname = Data.getdatafile().getString("players." + name + ".name");
            int kill = Data.getdatafile().getInt("players." + name + ".Blocks");
            tMinings.add(new TMining(Bukkit.getPlayer(Objects.requireNonNull(pname)), kill));
        }
        //Sort the list using a custom comparator
        tMinings.sort(new MiningComparator());
        Collections.reverse(tMinings);
        return tMinings;
    }
}
