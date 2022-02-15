package net.danh.jobs.Events;

import net.danh.jobs.Manager.Power;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class Eating implements Listener {

    @EventHandler
    public void onEating(PlayerItemConsumeEvent e) {
        Power.getInstance().addPower(e.getPlayer(), Power.getInstance().getRestorePower(e.getPlayer()));
    }
}
