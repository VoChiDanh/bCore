package net.danh.main.Events;

import net.danh.main.Manager.Power;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Moving implements Listener {

    @EventHandler
    public void onMove(@NotNull PlayerMoveEvent e) {
        Player p = e.getPlayer();
        int X = e.getFrom().getBlockX();
        int X1 = Objects.requireNonNull(e.getTo()).getBlockX();
        int Y = e.getFrom().getBlockY();
        int Y1 = e.getTo().getBlockY();
        int Z = e.getFrom().getBlockZ();
        int Z1 = e.getTo().getBlockZ();
        if (X == X1 && Y == Y1 && Z == Z1) {
            if (Power.getPower(p) < Power.getMaxPower(p)) {
                Power.addPower(p, Power.getRestorePower(p));
            }
        }
    }
}
