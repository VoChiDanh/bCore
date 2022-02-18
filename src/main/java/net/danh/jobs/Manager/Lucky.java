package net.danh.jobs.Manager;

import org.bukkit.entity.Player;

public class Lucky {

    private static Lucky instance;

    public static Lucky getInstance() {

        if (instance == null) {
            instance = new Lucky();
        }
        return instance;
    }

    public int getLuckyChance(Player p) {
        return Data.getInstance().getdatafile().getInt("players." + p.getName() + ".Lucky");
    }

    public void setLuckyChance(Player p, int number) {
        Data.getInstance().getdatafile().set("players." + p.getName() + ".Lucky", number);
        Data.getInstance().savedata();
    }

    public void addLuckyChance(Player p, int number) {
        Data.getInstance().getdatafile().set("players." + p.getName() + ".Lucky", getLuckyChance(p) + number);
        Data.getInstance().savedata();
    }

    public void removeLuckyChance(Player p, int number) {
        if (getLuckyChance(p) > Data.getInstance().getconfigfile().getInt("Default_Lucky_Chance")) {
            Data.getInstance().getdatafile().set("players." + p.getName() + ".Lucky", getLuckyChance(p) - number);
        } else {
            Data.getInstance().getdatafile().set("players." + p.getName() + ".Lucky", Data.getInstance().getconfigfile().getInt("Default_Lucky_Chance"));
        }
        Data.getInstance().savedata();
    }

}
