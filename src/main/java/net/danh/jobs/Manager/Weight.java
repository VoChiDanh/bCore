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

    public double getWeight(Player p) {
        return Data.getInstance().getdatafile().getInt("players." + p.getName() + ".Weight");
    }

    public void setWeight(Player p, Double number) {
        Data.getInstance().getdatafile().set("players." + p.getName() + ".Weight", number);
        Data.getInstance().savedata();
    }

    public void addWeight(Player p, Double number) {
        Data.getInstance().getdatafile().set("players." + p.getName() + ".Weight", getWeight(p) + number);
        Data.getInstance().savedata();
    }

    public void removeWeight(Player p, Double number) {
        if (getWeight(p) > 1) {
            Data.getInstance().getdatafile().set("players." + p.getName() + ".Weight", getWeight(p) - number);
        } else {
            Data.getInstance().getdatafile().set("players." + p.getName() + ".Weight", 0);
        }
        Data.getInstance().savedata();
    }

    public int getMaxWeight(Player p) {
        return Data.getInstance().getdatafile().getInt("players." + p.getName() + ".MaxWeight");
    }


    public void setMaxWeight(Player p, Double number) {
        Data.getInstance().getdatafile().set("players." + p.getName() + ".MaxWeight", number);
        Data.getInstance().savedata();
    }

    public void addMaxWeight(Player p, Double number) {
        Data.getInstance().getdatafile().set("players." + p.getName() + ".MaxWeight", getMaxWeight(p) + number);
        Data.getInstance().savedata();
    }

    public void removeMaxWeight(Player p, Double number) {
        if (getMaxWeight(p) > 10) {
            Data.getInstance().getdatafile().set("players." + p.getName() + ".MaxWeight", getMaxWeight(p) - number);
        } else {
            Data.getInstance().getdatafile().set("players." + p.getName() + ".MaxWeight", 10);
        }
        Data.getInstance().savedata();
    }
}
