package net.danh.bCore.Manager;

import org.bukkit.entity.Player;

public class Weight {

    public static double getWeight(Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".Weight");
    }

    public static void setWeight(Player p, Double number) {
        Data.getdatafile().set("players." + p.getName() + ".Weight", number);
        Data.savedata();
    }

    public static void addWeight(Player p, Double number) {
        Data.getdatafile().set("players." + p.getName() + ".Weight", getWeight(p) + number);
        Data.savedata();
    }

    public static void removeWeight(Player p, Double number) {
        if (getWeight(p) > 1) {
            Data.getdatafile().set("players." + p.getName() + ".Weight", getWeight(p) - number);
        } else {
            Data.getdatafile().set("players." + p.getName() + ".Weight", 0);
        }
        Data.savedata();
    }

    public static int getMaxWeight(Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".MaxWeight");
    }


    public static void setMaxWeight(Player p, Double number) {
        Data.getdatafile().set("players." + p.getName() + ".MaxWeight", number);
        Data.savedata();
    }

    public static void addMaxWeight(Player p, Double number) {
        Data.getdatafile().set("players." + p.getName() + ".MaxWeight", getMaxWeight(p) + number);
        Data.savedata();
    }

    public static void removeMaxWeight(Player p, Double number) {
        if (getMaxWeight(p) > 10) {
            Data.getdatafile().set("players." + p.getName() + ".MaxWeight", getMaxWeight(p) - number);
        } else {
            Data.getdatafile().set("players." + p.getName() + ".MaxWeight", 10);
        }
        Data.savedata();
    }
}
