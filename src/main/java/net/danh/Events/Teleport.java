package net.danh.Events;

import net.danh.Manager.Status;
import net.danh.PlayerData.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.jetbrains.annotations.NotNull;

public class Teleport implements Listener {

    @EventHandler
    public void onTeleport(@NotNull PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        Status.TogglePvP(p, false);
        PlayerData.togglePvP(p);
    }
}
