package net.danh.main.Manager;

import org.bukkit.entity.Player;

public class Power {


    public static int getPower(Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".Power");
    }


    public static void setPower(Player p, int number) {
        Data.getdatafile().set("players." + p.getName() + ".Power", number);
        Data.savedata();
    }

    public static void addPower(Player p, int number) {
        Data.getdatafile().set("players." + p.getName() + ".Power", getPower(p) + number);
        Data.savedata();
    }


    public static void removePower(Player p, int number) {
        if (getPower(p) > number) {
            Data.getdatafile().set("players." + p.getName() + ".Power", getPower(p) - number);
            Data.savedata();
        } else {
            Data.getdatafile().set("players." + p.getName() + ".Power", 0);
            Data.savedata();
        }
    }

    public static int getMaxPower(Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".MaxPower");
    }

    public static void setMaxPower(Player p, int number) {
        Data.getdatafile().set("players." + p.getName() + ".MaxPower", number);
        Data.savedata();
    }

    public static void addMaxPower(Player p, int number) {
        Data.getdatafile().set("players." + p.getName() + ".MaxPower", getMaxPower(p) + number);
        Data.savedata();
    }

    public static void removeMaxPower(Player p, int number) {
        if (getMaxPower(p) > Data.getconfigfile().getInt("Max_Power")) {
            Data.getdatafile().set("players." + p.getName() + ".MaxPower", getMaxPower(p) - number);
        } else {
            Data.getdatafile().set("players." + p.getName() + ".MaxPower", Data.getconfigfile().getInt("Max_Power"));
        }
        Data.savedata();
    }

    public static int getRestorePower(Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".RestorePower");
    }

    public static void setRestorePower(Player p, int number) {
        Data.getdatafile().set("players." + p.getName() + ".RestorePower", number);
        Data.savedata();
    }

    public static void addRestorePower(Player p, int number) {
        Data.getdatafile().set("players." + p.getName() + ".RestorePower", getRestorePower(p) + number);
        Data.savedata();
    }

    public static void removeRestorePower(Player p, int number) {
        if (getMaxPower(p) > Data.getconfigfile().getInt("Restore_Power")) {
            Data.getdatafile().set("players." + p.getName() + ".RestorePower", getRestorePower(p) - number);
        } else {
            Data.getdatafile().set("players." + p.getName() + ".RestorePower", Data.getconfigfile().getInt("Restore_Power"));
        }
        Data.savedata();
    }

}
