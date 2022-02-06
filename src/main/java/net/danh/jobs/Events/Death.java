package net.danh.jobs.Events;

import net.danh.jobs.Manager.Files;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class Death implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Files.getInstance().removeXP(e.getEntity().getPlayer(), Files.getInstance().getXP(Objects.requireNonNull(e.getEntity().getPlayer())) / 2);
    }
}
