package net.danh.Events;

import net.danh.Manager.*;
import net.danh.bCore;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
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
        Power.setPower(p, Power.getPowerData(p));
        Power.setMaxPower(p, Power.getMaxPowerData(p));
        Power.setRestorePower(p, Power.getRestorePowerData(p));
        Points.setPvPPoints(p, Points.getPvPPointsData(p));
        Status.TogglePvP(p, Status.getPvPStatusData(p));
        QPoints.setQP(p, QPoints.getQPData(p));
        if (Power.getRestorePower(p) == 0) {
            Power.setRestorePower(p, Data.getconfigfile().getInt("Restore_Power"));
        }
        if (Power.getMaxPower(p) == 0) {
            Power.setMaxPower(p, Data.getconfigfile().getInt("Max_Power"));
        }
        if (Bank.getBankLevel(p) == 0) {
            Bank.setBankLevel(p, 1);
        }
        if (Credit.getCredit(p) == 0) {
            Credit.setCredit(p, 100D);
        }
        (new BukkitRunnable() {
            int time = 10;

            public void run() {
                if (time <= 0) {
                    Status.TogglePvP(p, true);
                    cancel();
                } else {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                            new TranslatableComponent(Data.convert("&aBật PvP trong " + time-- + " giây")));
                }
            }
        }).runTaskTimer(bCore.getInstance(), 20L, 20L);
    }
}
