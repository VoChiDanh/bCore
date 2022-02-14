package net.danh.jobs.Events;

import net.danh.jobs.Manager.Files;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class Eating implements Listener {

    @EventHandler
    public void onEating(PlayerItemConsumeEvent e) {
        Files.getInstance().addPower(e.getPlayer(), Files.getInstance().getRestorePower(e.getPlayer()));
    }
}
