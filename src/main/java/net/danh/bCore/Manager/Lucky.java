package net.danh.bCore.Manager;

import org.bukkit.entity.Player;

public class Lucky {

    public static int getLuckyChance(Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".Lucky");
    }

    public static void setLuckyChance(Player p, int number) {
        Data.getdatafile().set("players." + p.getName() + ".Lucky", number);
        Data.savedata();
    }

    public static void addLuckyChance(Player p, int number) {
        Data.getdatafile().set("players." + p.getName() + ".Lucky", getLuckyChance(p) + number);
        Data.savedata();
    }

    public static void removeLuckyChance(Player p, int number) {
        if (getLuckyChance(p) > Data.getconfigfile().getInt("Default_Lucky_Chance")) {
            Data.getdatafile().set("players." + p.getName() + ".Lucky", getLuckyChance(p) - number);
        } else {
            Data.getdatafile().set("players." + p.getName() + ".Lucky", Data.getconfigfile().getInt("Default_Lucky_Chance"));
        }
        Data.savedata();
    }

}
