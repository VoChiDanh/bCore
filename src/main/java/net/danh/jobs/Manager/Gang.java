package net.danh.jobs.Manager;

import org.bukkit.entity.Player;

import java.util.Objects;

public class Gang {


    public static String getGang(Player p) {
        return Data.getdatafile().getString("players." + p.getName() + ".Gang");
    }

    public static void selectGang(Player p, String name) {
        if (getGang(p) != null) {
            removeMemberGang(p);
        }
        Data.getdatafile().set("players." + p.getName() + ".Gang", name.toUpperCase());
        if (getLevelGang(p) == 0) {
            setLevelGang(p);
        }
        addMemberGang(p);
        if (getleveluptimes(p) == 0) {
            setLeveluptimes(p, 0);
        }
        Data.savedata();
    }

    public static int getMemberGang(Player p) {
        return Data.getdatafile().getInt("gangs." + getGang(p) + ".Member");
    }

    public static void removeMemberGang(Player p) {
        Data.getdatafile().set("gangs." + getGang(p) + ".Member", getMemberGang(p) - 1);
        Data.savedata();
    }

    public static void addMemberGang(Player p) {
        Data.getdatafile().set("gangs." + getGang(p) + ".Member", getMemberGang(p) + 1);
        Data.savedata();
    }

    public static int getLevelGang(Player p) {
        return Data.getdatafile().getInt("gangs." + getGang(p) + ".Level");
    }

    public static void addLevelGang(Player p, Integer number) {
        Data.getdatafile().set("gangs." + getGang(p) + ".Level", getLevelGang(p) + number);
        Data.savedata();
    }

    public static void setLevelGang(Player p) {
        Data.getdatafile().set("gangs." + getGang(p) + ".Level", 1);
        Data.savedata();
    }


    public static void setLevelGang(Player p, Integer number) {
        Data.getdatafile().set("gangs." + getGang(p) + ".Level", number);
        Data.savedata();
    }

    public static void removeLevelGang(Player p, Integer number) {
        if (getLevelGang(p) > 1) {
            Data.getdatafile().set("gangs." + getGang(p) + ".Level", getLevelGang(p) - number);
        } else {
            Data.getdatafile().set("gangs." + getGang(p) + ".Level", 1);
        }
        Data.savedata();
    }

    public static void checkLevelUp(Player p) {
        if (PlayerData.getXP(p) >= (((PlayerData.getLevel(p) + getLevelGang(p)) / 2)) * 1000) {
            addLevelGang(p, 1);
            PlayerData.setXP(p, 0);
            addLeveluptimes(p, 1);
            p.sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("LEVEL_UP_GANG"))));
        }
    }

    public static int getleveluptimes(Player p) {
        return Data.getdatafile().getInt("gangs." + getGang(p) + ".LevelUpTimes." + p.getName());
    }

    public static void addLeveluptimes(Player p, Integer number) {
        Data.getdatafile().set("gangs." + getGang(p) + ".LevelUpTimes." + p.getName(), getleveluptimes(p) + number);
        Data.savedata();
    }

    public static void removeLeveluptimes(Player p, Integer number) {
        if (getleveluptimes(p) > 1) {
            Data.getdatafile().set("gangs." + getGang(p) + ".LevelUpTimes." + p.getName(), getleveluptimes(p) - number);
        } else {
            Data.getdatafile().set("gangs." + getGang(p) + ".LevelUpTimes." + p.getName(), 0);
        }
        Data.savedata();
    }

    public static void setLeveluptimes(Player p, Integer number) {
        Data.getdatafile().set("gangs." + getGang(p) + ".LevelUpTimes." + p.getName(), number);
        Data.savedata();
    }
}
