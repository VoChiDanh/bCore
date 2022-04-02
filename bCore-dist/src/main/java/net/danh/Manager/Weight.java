package net.danh.Manager;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Weight {
    public static HashMap<Player, Integer> weight = new HashMap<>();
    public static HashMap<Player, Integer> maxweight = new HashMap<>();

    public static @NotNull Integer getWeightData(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".Weight");
    }

    public static @NotNull Integer getWeight(@NotNull Player p) {
        return weight.get(p);
    }

    public static void setWeight(@NotNull Player p, Integer number) {
        weight.put(p, number);
    }

    public static void addWeight(@NotNull Player p, Integer number) {
        weight.replace(p, getWeight(p) + number);
    }

    public static void removeWeight(Player p, Integer number) {
        if (getWeight(p) > number) {
            weight.replace(p, getWeight(p) - number);
        } else {
            weight.replace(p, 0);
        }
    }

    public static int getMaxWeightData(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".MaxWeight");
    }


    public static int getMaxWeight(@NotNull Player p) {
        return maxweight.get(p);
    }


    public static void setMaxWeight(@NotNull Player p, Integer number) {
        maxweight.put(p, number);
    }

    public static void addMaxWeight(@NotNull Player p, Integer number) {
        maxweight.replace(p, getMaxWeight(p) + number);
    }

    public static void removeMaxWeight(Player p, Integer number) {
        if (getMaxWeight(p) > number && (getMaxWeight(p) - number) > getWeight(p)) {
            maxweight.replace(p, getMaxWeight(p) - number);
        }
    }
}
