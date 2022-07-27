package net.danh.Manager;

import net.danh.PlayerData.PData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import static net.danh.dcore.Utils.Player.sendPlayerMessage;

public class PlayerData {

    public static HashMap<Player, Integer> block = new HashMap<>();
    public static HashMap<Player, Integer> xp = new HashMap<>();
    public static HashMap<Player, Integer> level = new HashMap<>();
    public static HashMap<Player, Integer> levelup = new HashMap<>();

    public static int getOldXPData(@NotNull String name) {
        return Data.getdatafile().getInt("players." + name + ".XP");
    }

    public static int getXPData(@NotNull Player p) {
        return new PData(p.getName()).getConfig().getInt("xp");
    }

    public static int getOldLevelData(@NotNull String name) {
        return Data.getdatafile().getInt("players." + name + ".Level");
    }

    public static int getLevelData(@NotNull Player p) {
        return new PData(p.getName()).getConfig().getInt("level");
    }

    public static int getLevel(@NotNull Player p) {
        return level.get(p);
    }

    public static int getXP(@NotNull Player p) {
        return xp.get(p);
    }

    public static void setLevel(@NotNull Player p, int number) {
        level.put(p, Math.max(number, 1));
    }

    public static void setXP(@NotNull Player p, int number) {
        xp.put(p, Math.max(number, 0));
    }

    public static void addLevel(@NotNull Player p, int number) {
        level.replace(p, getLevel(p) + number);
        Power.addMaxPower(p, Data.getconfigfile().getInt("Per_Max_Power"));
        Power.addRestorePower(p, Data.getconfigfile().getInt("Per_Restore_Power"));
        sendPlayerMessage(p, "&aChúc mừng bạn đã lên cấp " + getLevel(p));
    }

    public static void addXP(@NotNull Player p, int number) {
        xp.replace(p, getXP(p) + number);
        checkLevelUp(p);
        sendPlayerMessage(p, "&a+ " + number + " XP");
    }

    public static void removeLevel(Player p, int number) {
        if (getLevel(p) > number) {
            level.replace(p, getLevel(p) - number);
        } else {
            level.put(p, 1);
        }
    }

    public static void removeXP(Player p, int number) {
        if (getXP(p) > number) {
            xp.replace(p, getXP(p) - number);
        } else {
            xp.put(p, 0);
        }
    }


    public static void checkLevelUp(Player p) {
        if (getXP(p) >= getLevel(p) * 10000) {
            removeXP(p, getLevel(p) * 10000);
            addLevel(p, 1);
            addLeveluptimes(p, 1);
            sendPlayerMessage(p, "&aChúc mừng bạn đã lên cấp độ " + getLevel(p));
        }
    }

    public static int getleveluptimes(@NotNull Player p) {
        return levelup.get(p);
    }

    public static int getOldLUData(@NotNull String name) {
        return Data.getdatafile().getInt("players." + name + ".LevelUpTimes");
    }

    public static int getLUData(@NotNull Player p) {
        return new PData(p.getName()).getConfig().getInt("level_up_times");
    }

    public static void addLeveluptimes(@NotNull Player p, Integer number) {
        levelup.replace(p, getleveluptimes(p) + number);
    }

    public static void removeLeveluptimes(Player p, Integer number) {
        if (getleveluptimes(p) > 1) {
            levelup.replace(p, getleveluptimes(p) - number);
        } else {
            levelup.put(p, 0);
        }
    }

    public static void setLeveluptimes(@NotNull Player p, Integer number) {
        levelup.put(p, number);
    }

    public static @NotNull Integer getBlocks(@NotNull Player p) {
        return block.get(p);
    }

    public static int getOldBlockData(@NotNull String name) {
        return Data.getdatafile().getInt("players." + name + ".Blocks");
    }

    public static int getBlockData(@NotNull Player p) {
        return new PData(p.getName()).getConfig().getInt("blocks");
    }

    public static void setBlocks(@NotNull Player p, Integer number) {
        block.put(p, number);
    }

    public static void addBlocks(@NotNull Player p, Integer number) {
        block.replace(p, getBlocks(p) + number);
    }

    public static void removeBlocks(@NotNull Player p, Integer number) {
        block.replace(p, getBlocks(p) - number);
    }

}
