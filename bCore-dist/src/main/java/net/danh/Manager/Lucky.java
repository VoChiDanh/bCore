package net.danh.Manager;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Lucky {

    public static HashMap<Player, Integer> lucky = new HashMap<>();

    public static int getLuckyChanceData(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".Lucky");
    }

    public static int getLuckyChance(@NotNull Player p) {
        return lucky.get(p);
    }

    public static void SaveLuckyChance(@NotNull Player p) {
        Data.getdatafile().set("players." + p.getName() + ".Lucky", getLuckyChance(p));
        Data.savedata();
    }

    public static void setLuckyChance(@NotNull Player p, int number) {
        lucky.put(p, number);
    }

    public static void addLuckyChance(@NotNull Player p, int number) {
        lucky.replace(p, getLuckyChance(p) + number);
    }

    public static void removeLuckyChance(Player p, int number) {
        if (getLuckyChance(p) > Data.getconfigfile().getInt("Default_Lucky_Chance")) {
            lucky.replace(p, getLuckyChance(p) - number);
        } else {
            lucky.put(p, Data.getconfigfile().getInt("Default_Lucky_Chance"));
        }
    }

}
