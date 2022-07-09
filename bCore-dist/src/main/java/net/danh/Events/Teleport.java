package net.danh.Events;

import net.danh.Manager.Status;
import net.danh.bCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import static net.danh.dcore.Utils.Player.sendPlayerMessageType;

public class Teleport implements Listener {

    @EventHandler
    public void onTeleport(@NotNull PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        Status.TogglePvP(p, false);
        (new BukkitRunnable() {
            int time = 10;

            public void run() {
                if (time <= 0) {
                    Status.TogglePvP(p, true);
                    cancel();
                } else {
                    sendPlayerMessageType(p, null, "&aBật PvP trong " + time-- + " giây");
                }
            }
        }).runTaskTimer(bCore.getInstance(), 20L, 20L);
    }
}
