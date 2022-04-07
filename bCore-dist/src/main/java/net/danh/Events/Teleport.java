package net.danh.Events;

import net.danh.Manager.Data;
import net.danh.Manager.Status;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Teleport implements Listener {

    @EventHandler
    public void onTeleport(@NotNull PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        List<String> w = Data.getconfigfile().getStringList("disable-worlds");
        Status.TogglePvP(p, !w.contains(p.getWorld().getName()));
    }
}
