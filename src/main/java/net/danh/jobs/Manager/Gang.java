package net.danh.jobs.Manager;

import org.bukkit.entity.Player;

import java.util.Objects;

public class Gang {

    private static Gang instance;

    public static Gang getInstance() {

        if (instance == null) {
            instance = new Gang();
        }
        return instance;
    }

    public String getGang(Player p) {
        return Data.getInstance().getdata().getString("players." + p.getName() + ".Gang");
    }

    public void selectGang(Player p, String name) {
        if (getGang(p) != null) {
            removeMemberGang(p);
        }
        Data.getInstance().getdata().set("players." + p.getName() + ".Gang", name.toUpperCase());
        if (getLevelGang(p) == 0) {
            setLevelGang(p);
        }
        addMemberGang(p);
        if (getleveluptimes(p) == 0) {
            setLeveluptimes(p, 0);
        }
        Data.getInstance().savedata();
    }

    public int getMemberGang(Player p) {
        return Data.getInstance().getdata().getInt("gangs." + getGang(p) + ".Member");
    }

    public void removeMemberGang(Player p) {
        Data.getInstance().getdata().set("gangs." + getGang(p) + ".Member", getMemberGang(p) - 1);
        Data.getInstance().savedata();
    }

    public void addMemberGang(Player p) {
        Data.getInstance().getdata().set("gangs." + getGang(p) + ".Member", getMemberGang(p) + 1);
        Data.getInstance().savedata();
    }

    public int getLevelGang(Player p) {
        return Data.getInstance().getdata().getInt("gangs." + getGang(p) + ".Level");
    }

    public void addLevelGang(Player p, Integer number) {
        Data.getInstance().getdata().set("gangs." + getGang(p) + ".Level", getLevelGang(p) + number);
        Data.getInstance().savedata();
    }

    public void setLevelGang(Player p) {
        Data.getInstance().getdata().set("gangs." + getGang(p) + ".Level", 1);
        Data.getInstance().savedata();
    }


    public void setLevelGang(Player p, Integer number) {
        Data.getInstance().getdata().set("gangs." + getGang(p) + ".Level", number);
        Data.getInstance().savedata();
    }

    public void removeLevelGang(Player p, Integer number) {
        if (getLevelGang(p) > 1) {
            Data.getInstance().getdata().set("gangs." + getGang(p) + ".Level", getLevelGang(p) - number);
        } else {
            Data.getInstance().getdata().set("gangs." + getGang(p) + ".Level", 1);
        }
        Data.getInstance().savedata();
    }

    public void checkLevelUp(Player p) {
        if (PlayerData.getInstance().getXP(p) >= (((PlayerData.getInstance().getLevel(p) + getLevelGang(p)) / 2)) * 1000) {
            addLevelGang(p, 1);
            PlayerData.getInstance().setXP(p, 0);
            addLeveluptimes(p, 1);
            p.sendMessage(Data.getInstance().convert(Objects.requireNonNull(Data.getInstance().getlanguage().getString("LEVEL_UP_GANG"))));
        }
    }

    public int getleveluptimes(Player p) {
        return Data.getInstance().getdata().getInt("gangs." + getGang(p) + ".LevelUpTimes." + p.getName());
    }

    public void addLeveluptimes(Player p, Integer number) {
        Data.getInstance().getdata().set("gangs." + getGang(p) + ".LevelUpTimes." + p.getName(), getleveluptimes(p) + number);
        Data.getInstance().savedata();
    }

    public void removeLeveluptimes(Player p, Integer number) {
        if (getleveluptimes(p) > 1) {
            Data.getInstance().getdata().set("gangs." + getGang(p) + ".LevelUpTimes." + p.getName(), getleveluptimes(p) - number);
        } else {
            Data.getInstance().getdata().set("gangs." + getGang(p) + ".LevelUpTimes." + p.getName(), 0);
        }
        Data.getInstance().savedata();
    }

    public void setLeveluptimes(Player p, Integer number) {
        Data.getInstance().getdata().set("gangs." + getGang(p) + ".LevelUpTimes." + p.getName(), number);
        Data.getInstance().savedata();
    }
}
