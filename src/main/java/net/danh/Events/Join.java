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
        if (PlayerData.getXP(p) == 0) {
            PlayerData.setXP(p, 0);
        }
        if (Power.getRestorePower(p) == 0) {
            Power.setRestorePower(p, Data.getconfigfile().getInt("Restore_Power"));
        }
        if (Power.getMaxPower(p) == 0) {
            Power.setMaxPower(p, Data.getconfigfile().getInt("Max_Power"));
        }
        if (Lucky.getLuckyChance(p) == 0) {
            Lucky.setLuckyChance(p, Data.getconfigfile().getInt("Default_Lucky_Chance"));
        }

        if (Power.getPower(p) == 0) {
            Power.setPower(p, Power.getMaxPower(p));
        }
        if (Weight.getWeight(p) == 0) {
            Weight.setWeight(p, 0);
        }
        if (Weight.getMaxWeight(p) == 0) {
            Weight.setMaxWeight(p, 5000);
        }
        if (Storage.getStorage(p, "LOG".toUpperCase()) == 0) {
            Storage.setStorage(p, "LOG".toUpperCase(), 0);
        }
        if (Bank.getBankLevel(p) == 0) {
            Bank.setBankLevel(p, 1);
        }
        if (PlayerData.getBlocks(p) == 0) {
            PlayerData.setBlocks(p, 0);
        }
        if (PlayerData.getKill(p) == 0) {
            PlayerData.setKill(p, 0);
        }
        if (!PvPPlayer.getPvPStatus(p)) {
            PvPPlayer.setPvPStatus(p, true);
        }
    }
}
