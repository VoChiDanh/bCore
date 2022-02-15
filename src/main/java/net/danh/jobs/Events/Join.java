package net.danh.jobs.Events;

import net.danh.jobs.Manager.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        PlayerData.getInstance().setXP(p, 0);
        Power.getInstance().setRestorePower(p, Data.getInstance().getconfig().getInt("Restore_Power"));
        Power.getInstance().setMaxPower(p, Data.getInstance().getconfig().getInt("Max_Power"));
        Lucky.getInstance().setLuckyChance(p, Data.getInstance().getconfig().getInt("Default_Lucky_Chance"));
        Power.getInstance().setPower(p, Power.getInstance().getMaxPower(p));
        Weight.getInstance().setWeight(p, 0);
        Weight.getInstance().setMaxWeight(p, 100);
        Storage.getInstance().setStorage(p, "BEDROCK", 0);
        Data.getInstance().savedata();
    }
}
