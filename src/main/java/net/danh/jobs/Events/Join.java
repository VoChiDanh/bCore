package net.danh.jobs.Events;

import net.danh.jobs.Manager.Files;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (Files.getInstance().getJobs(p) == null) {
            Files.getInstance().setJobs(p, "MINER".toUpperCase());
            Files.getInstance().setAge(p, 1);
            Files.getInstance().setXP(p, 0);
            Files.getInstance().setRestorePower(p, Files.getInstance().getconfig().getInt("Restore_Power"));
            Files.getInstance().setMaxPower(p, Files.getInstance().getconfig().getInt("Max_Power"));
            Files.getInstance().setLuckyChance(p, Files.getInstance().getconfig().getInt("Default_Lucky_Chance"));
            Files.getInstance().setPower(p, Files.getInstance().getMaxPower(p));
            Files.getInstance().setWeight(p, 0);
            Files.getInstance().setMaxWeight(p, 10);
            Files.getInstance().setStorage(p, "BEDROCK", 0);
            Files.getInstance().savedata();
        }
    }
}
