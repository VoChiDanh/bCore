package net.danh.jobs.Manager;

import org.bukkit.entity.Player;

public class Weight {


    private static Weight instance;

    public static Weight getInstance() {

        if (instance == null) {
            instance = new Weight();
        }
        return instance;
    }

    public int getWeight(Player p) {
        return Data.getInstance().getdata().getInt("players." + p.getName() + ".Weight");
    }

    public void setWeight(Player p, int number) {
        Data.getInstance().getdata().set("players." + p.getName() + ".Weight", number);
        Data.getInstance().savedata();
    }

    public void addWeight(Player p, int number) {
        Data.getInstance().getdata().set("players." + p.getName() + ".Weight", getWeight(p) + number);
        Data.getInstance().savedata();
    }

    public void removeWeight(Player p, int number) {
        if (getWeight(p) > 1) {
            Data.getInstance().getdata().set("players." + p.getName() + ".Weight", getWeight(p) - number);
        } else {
            Data.getInstance().getdata().set("players." + p.getName() + ".Weight", 0);
        }
        Data.getInstance().savedata();
    }

    public int getMaxWeight(Player p) {
        return Data.getInstance().getdata().getInt("players." + p.getName() + ".MaxWeight");
    }


    public void setMaxWeight(Player p, int number) {
        Data.getInstance().getdata().set("players." + p.getName() + ".MaxWeight", number);
        Data.getInstance().savedata();
    }

    public void addMaxWeight(Player p, int number) {
        Data.getInstance().getdata().set("players." + p.getName() + ".MaxWeight", getMaxWeight(p) + number);
        Data.getInstance().savedata();
    }

    public void removeMaxWeight(Player p, int number) {
        if (getMaxWeight(p) > 10) {
            Data.getInstance().getdata().set("players." + p.getName() + ".MaxWeight", getMaxWeight(p) - number);
        } else {
            Data.getInstance().getdata().set("players." + p.getName() + ".MaxWeight", 10);
        }
        Data.getInstance().savedata();
    }
}
