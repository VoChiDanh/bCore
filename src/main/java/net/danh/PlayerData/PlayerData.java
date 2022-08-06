package net.danh.PlayerData;

import net.danh.Manager.*;
import net.danh.PClass.PCPoints;
import net.danh.bCore;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

import static net.danh.dcore.Utils.Player.sendPlayerMessageType;

public class PlayerData {

    public static void convertData() {
        for (String name : Objects.requireNonNull(Data.getdatafile().getConfigurationSection("players")).getKeys(false)) {
            PData new_data = new PData(name);
            new_data.load();
            new_data.getConfig().set("banks.money", Bank.getOldBMData(name));
            new_data.getConfig().set("banks.level", Bank.getOldBLData(name));
            new_data.getConfig().set("credit", Credit.getOldCreditData(name));
            new_data.getConfig().set("event.blocks", Event.getOldBEData(name));
            new_data.getConfig().set("xp", net.danh.Manager.PlayerData.getOldXPData(name));
            new_data.getConfig().set("level", net.danh.Manager.PlayerData.getOldLevelData(name));
            new_data.getConfig().set("level_up_times", net.danh.Manager.PlayerData.getOldLUData(name));
            new_data.getConfig().set("blocks", net.danh.Manager.PlayerData.getOldBlockData(name));
            new_data.getConfig().set("power.default", Power.getOldPowerData(name));
            new_data.getConfig().set("power.max", Power.getOldMaxPowerData(name));
            new_data.getConfig().set("power.restore", Power.getOldRestorePowerData(name));
            new_data.getConfig().set("points", Points.getOldPvPPointsData(name));
            new_data.getConfig().set("status", Status.getOldPvPStatusData(name));
            new_data.getConfig().set("qpoints", QPoints.getOldQPData(name));
            new_data.save();
        }
    }

    public static void togglePvP(Player p) {
        (new BukkitRunnable() {
            int time = 10;

            public void run() {
                if (time <= 0) {
                    Status.TogglePvP(p, true);
                    cancel();
                } else {
                    sendPlayerMessageType(p, null, "&aBật PvP trong " + time-- + " giây");
                }
            }
        }).runTaskTimer(bCore.getInstance(), 20L, 20L);
    }

    public static void loadPlayerData(Player p) {
        PData data = new PData(p.getName());
        data.load();
        Power.setPower(p, Power.getPowerData(p));
        net.danh.Manager.PlayerData.setLevel(p, net.danh.Manager.PlayerData.getLevelData(p));
        Event.setBlocksEvent(p, Event.getBEData(p));
        net.danh.Manager.PlayerData.setLeveluptimes(p, net.danh.Manager.PlayerData.getLUData(p));
        net.danh.Manager.PlayerData.setXP(p, net.danh.Manager.PlayerData.getXPData(p));
        net.danh.Manager.PlayerData.setBlocks(p, net.danh.Manager.PlayerData.getBlockData(p));
        Bank.setBankMoney(p, Bank.getBMData(p));
        Bank.setBankLevel(p, Bank.getBLData(p));
        Credit.setCredit(p, Credit.getCreditData(p));
        Power.setMaxPower(p, Power.getMaxPowerData(p));
        Power.setRestorePower(p, Power.getRestorePowerData(p));
        Points.setPvPPoints(p, Points.getPvPPointsData(p));
        Status.TogglePvP(p, Status.getPvPStatusData(p));
        QPoints.setQP(p, QPoints.getQPData(p));
        PCPoints.setPCP(p, PCPoints.getPCPData(p));
    }

    public static void savePlayerData(Player p) {
        PData data = new PData(p.getName());
        data.load();
        data.getConfig().set("banks.money", Bank.getBankMoney(p));
        data.getConfig().set("banks.level", Bank.getBankLevel(p));
        data.getConfig().set("credit", Credit.getCredit(p));
        data.getConfig().set("event.blocks", Event.getBlockEvent(p));
        data.getConfig().set("xp", net.danh.Manager.PlayerData.getXP(p));
        data.getConfig().set("level", net.danh.Manager.PlayerData.getLevel(p));
        data.getConfig().set("level_up_times", net.danh.Manager.PlayerData.getleveluptimes(p));
        data.getConfig().set("blocks", net.danh.Manager.PlayerData.getBlocks(p));
        data.getConfig().set("power.default", Power.getPower(p));
        data.getConfig().set("power.max", Power.getMaxPower(p));
        data.getConfig().set("power.restore", Power.getRestorePower(p));
        data.getConfig().set("points", Points.getPvPPoints(p));
        data.getConfig().set("status", Status.getPvPStatus(p));
        data.getConfig().set("qpoints", QPoints.getQP(p));
        data.getConfig().set("PCPoints", PCPoints.getPCP(p));
        data.save();
    }

}
