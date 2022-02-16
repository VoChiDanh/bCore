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
        if (PlayerData.getInstance().getXP(p) == 0) {
            PlayerData.getInstance().setXP(p, 0);
        }
        if (Power.getInstance().getRestorePower(p) == 0) {
            Power.getInstance().setRestorePower(p, Data.getInstance().getconfigfile().getInt("Restore_Power"));
        }
        if (Power.getInstance().getMaxPower(p) == 0) {
            Power.getInstance().setMaxPower(p, Data.getInstance().getconfigfile().getInt("Max_Power"));
        }
        if (Lucky.getInstance().getLuckyChance(p) == 0) {
            Lucky.getInstance().setLuckyChance(p, Data.getInstance().getconfigfile().getInt("Default_Lucky_Chance"));
        }

        if (Power.getInstance().getPower(p) == 0) {
            Power.getInstance().setPower(p, Power.getInstance().getMaxPower(p));
        }
        if (Weight.getInstance().getWeight(p) == 0) {
            Weight.getInstance().setWeight(p, 0);
        }
        if (Weight.getInstance().getMaxWeight(p) == 0) {
            Weight.getInstance().setMaxWeight(p, 100);
        }
        if (Storage.getInstance().getStorage(p, "LOG".toUpperCase()) == 0) {
            Storage.getInstance().setStorage(p, "LOG".toUpperCase(), 0);
        }
        Data.getInstance().savedata();
    }
}
