package net.danh.bCore.Events;

import net.danh.bCore.Manager.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
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
            Weight.setWeight(p, 0D);
        }
        if (Weight.getMaxWeight(p) == 0) {
            Weight.setMaxWeight(p, 5000D);
        }
        if (Storage.getStorage(p, "LOG".toUpperCase()) == 0) {
            Storage.setStorage(p, "LOG".toUpperCase(), 0D);
        }
        if (Bank.getBankMaxMoney(p) == 0) {
            Bank.setBankMaxMoney(p, 10000);
        }
    }
}
