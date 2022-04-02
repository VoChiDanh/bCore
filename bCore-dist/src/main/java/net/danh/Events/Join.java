package net.danh.Events;

import net.danh.Manager.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class Join implements Listener {

    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Event.setBlocksEvent(p, Event.getBEData(p));
        PlayerData.setLeveluptimes(p, PlayerData.getLUData(p));
        PlayerData.setLevel(p, PlayerData.getLevelData(p));
        PlayerData.setXP(p, PlayerData.getXPData(p));
        PlayerData.setBlocks(p, PlayerData.getBlockData(p));
        Bank.setBankMoney(p, Bank.getBMData(p));
        Bank.setBankLevel(p, Bank.getBLData(p));
        Credit.setCredit(p, Credit.getCreditData(p));
        Lucky.setLuckyChance(p, Lucky.getLuckyChanceData(p));
        Power.setPower(p, Power.getPowerData(p));
        Power.setMaxPower(p, Power.getMaxPowerData(p));
        Power.setRestorePower(p, Power.getRestorePowerData(p));
        Weight.setWeight(p, Weight.getWeightData(p));
        Weight.setMaxWeight(p, Weight.getMaxWeightData(p));
    }
}
