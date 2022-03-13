package net.danh.Events;

import net.danh.Manager.Power;
import net.danh.bCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Moving implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onMove(@NotNull PlayerMoveEvent e) {
        Player p = e.getPlayer();
        int X = e.getFrom().getBlockX();
        int X1 = Objects.requireNonNull(e.getTo()).getBlockX();
        int Z = e.getFrom().getBlockZ();
        int Z1 = e.getTo().getBlockZ();
        if (X != X1 && Z != Z1) {
            if (Power.getPower(p) < Power.getMaxPower(p)) {
                (new BukkitRunnable() {
                    @Override
                    public void run() {
                        Power.addPower(p, Power.getRestorePower(p) / 5);
                    }
                }).runTaskTimer(bCore.getInstance(), 60 * 20L, 60 * 20L);
            }
        }
    }
}
