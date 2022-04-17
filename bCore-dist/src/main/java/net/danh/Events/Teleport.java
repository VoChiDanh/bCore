package net.danh.Events;

import net.danh.Manager.Status;
import net.danh.bCore;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class Teleport implements Listener {

    @EventHandler
    public void onTeleport(@NotNull PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        Status.TogglePvP(p, false);
        (new BukkitRunnable() {
            int time = 10;
            public void run() {
                if (time == 0) {
                    Status.TogglePvP(p, true);
                    return;
                }
                for (final Player player : Bukkit.getOnlinePlayers()) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                            new TranslatableComponent("&aBật PvP trong " + time + " giây"));
                }

                time--;
            }
        }).runTaskTimer(bCore.getInstance(), 20L, 20L);
    }
}
