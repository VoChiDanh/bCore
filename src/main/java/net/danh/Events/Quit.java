package net.danh.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import static net.danh.PlayerData.PlayerData.savePlayerData;

public class Quit implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(@NotNull PlayerQuitEvent e) {
        Player p = e.getPlayer();
        savePlayerData(p);
    }
}
