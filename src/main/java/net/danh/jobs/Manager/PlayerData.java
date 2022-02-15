package net.danh.jobs.Manager;

import org.bukkit.entity.Player;

public class PlayerData {

    private static PlayerData instance;

    public static PlayerData getInstance() {

        if (instance == null) {
            instance = new PlayerData();
        }
        return instance;
    }

    public int getLevel(Player p) {
        return Data.getInstance().getdata().getInt("players." + p.getName() + ".Level");
    }

    public int getXP(Player p) {
        return Data.getInstance().getdata().getInt("players." + p.getName() + ".XP");
    }

    public void setLevel(Player p, int number) {
        Data.getInstance().getdata().set("players." + p.getName() + ".Level", number);
        Data.getInstance().savedata();
    }

    public void setXP(Player p, int number) {
        Data.getInstance().getdata().set("players." + p.getName() + ".XP", number);
        Data.getInstance().savedata();
    }

    public void addLevel(Player p, int number) {
        Data.getInstance().getdata().set("players." + p.getName() + ".Level", getLevel(p) + number);
        Data.getInstance().savedata();
        Power.getInstance().addMaxPower(p, Data.getInstance().getconfig().getInt("Per_Max_Power"));
        Power.getInstance().addRestorePower(p, Data.getInstance().getconfig().getInt("Per_Restore_Power"));
        for (String age_up : Data.getInstance().getlanguage().getStringList("LEVEL_UP")) {
            p.sendMessage(Data.getInstance().convert(age_up
                    .replaceAll("%mpower%", String.valueOf(Power.getInstance().getMaxPower(p)))
                    .replaceAll("%level%", String.valueOf(getLevel(p)))
                    .replaceAll("%rpower%", String.valueOf(Power.getInstance().getRestorePower(p)))));
        }
    }

    public void addXP(Player p, int number) {
        Data.getInstance().getdata().set("players." + p.getName() + ".XP", getXP(p) + number);
        Gang.getInstance().checkLevelUp(p);
        Data.getInstance().savedata();
    }

    public void removeLevel(Player p, int number) {
        if (getLevel(p) > number) {
            Data.getInstance().getdata().set("players." + p.getName() + ".Level", getLevel(p) - number);
            Data.getInstance().savedata();
        } else {
            Data.getInstance().getdata().set("players." + p.getName() + ".Level", 1);
            Data.getInstance().savedata();
        }
    }

    public void removeXP(Player p, int number) {
        if (getXP(p) > number) {
            Data.getInstance().getdata().set("players." + p.getName() + ".XP", getXP(p) - number);
            Data.getInstance().savedata();
        } else {
            Data.getInstance().getdata().set("players." + p.getName() + ".XP", 0);
            Data.getInstance().savedata();
        }
    }


}
