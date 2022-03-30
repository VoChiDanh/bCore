package net.danh.Events;

import net.danh.Manager.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class Quit implements Listener {

    @EventHandler
    public void onQuit(@NotNull PlayerQuitEvent e) {
        Player p = e.getPlayer();
        Data.saveall(p);
    }
}
