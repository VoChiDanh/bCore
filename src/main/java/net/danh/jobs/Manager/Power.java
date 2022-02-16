package net.danh.jobs.Manager;

import org.bukkit.entity.Player;

public class Power {

    private static Power instance;

    public static Power getInstance() {

        if (instance == null) {
            instance = new Power();
        }
        return instance;
    }

    public int getPower(Player p) {
        return Data.getInstance().getdatafile().getInt("players." + p.getName() + ".Power");
    }


    public void setPower(Player p, int number) {
        Data.getInstance().getdatafile().set("players." + p.getName() + ".Power", number);
        Data.getInstance().savedata();
    }

    public void addPower(Player p, int number) {
        Data.getInstance().getdatafile().set("players." + p.getName() + ".Power", getPower(p) + number);
        Data.getInstance().savedata();
    }


    public void removePower(Player p, int number) {
        if (getPower(p) > number) {
            Data.getInstance().getdatafile().set("players." + p.getName() + ".Power", getPower(p) - number);
            Data.getInstance().savedata();
        } else {
            Data.getInstance().getdatafile().set("players." + p.getName() + ".Power", 0);
            Data.getInstance().savedata();
        }
    }

    public int getMaxPower(Player p) {
        return Data.getInstance().getdatafile().getInt("players." + p.getName() + ".MaxPower");
    }

    public void setMaxPower(Player p, int number) {
        Data.getInstance().getdatafile().set("players." + p.getName() + ".MaxPower", number);
        Data.getInstance().savedata();
    }

    public void addMaxPower(Player p, int number) {
        Data.getInstance().getdatafile().set("players." + p.getName() + ".MaxPower", getMaxPower(p) + number);
        Data.getInstance().savedata();
    }

    public void removeMaxPower(Player p, int number) {
        if (getMaxPower(p) > Data.getInstance().getconfigfile().getInt("Max_Power")) {
            Data.getInstance().getdatafile().set("players." + p.getName() + ".MaxPower", getMaxPower(p) - number);
        } else {
            Data.getInstance().getdatafile().set("players." + p.getName() + ".MaxPower", Data.getInstance().getconfigfile().getInt("Max_Power"));
        }
        Data.getInstance().savedata();
    }

    public int getRestorePower(Player p) {
        return Data.getInstance().getdatafile().getInt("players." + p.getName() + ".RestorePower");
    }

    public void setRestorePower(Player p, int number) {
        Data.getInstance().getdatafile().set("players." + p.getName() + ".RestorePower", number);
        Data.getInstance().savedata();
    }

    public void addRestorePower(Player p, int number) {
        Data.getInstance().getdatafile().set("players." + p.getName() + ".RestorePower", getRestorePower(p) + number);
        Data.getInstance().savedata();
    }

    public void removeRestorePower(Player p, int number) {
        if (getMaxPower(p) > Data.getInstance().getconfigfile().getInt("Restore_Power")) {
            Data.getInstance().getdatafile().set("players." + p.getName() + ".RestorePower", getRestorePower(p) - number);
        } else {
            Data.getInstance().getdatafile().set("players." + p.getName() + ".RestorePower", Data.getInstance().getconfigfile().getInt("Restore_Power"));
        }
        Data.getInstance().savedata();
    }

}
