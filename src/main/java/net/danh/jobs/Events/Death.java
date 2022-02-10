package net.danh.jobs.Events;

import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.Files;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class Death implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        String DEAD = Files.getInstance().getlanguage().getString("DEAD");
        String PREFIX = Files.getInstance().getlanguage().getString("PREFIX");
        e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + Objects.requireNonNull(DEAD)
                .replaceAll("%xp%", String.valueOf(Files.getInstance().getXP(Objects.requireNonNull(e.getEntity().getPlayer())) / 2))
                .replaceAll("%money%", String.valueOf(Jobs.economy.getBalance(e.getPlayer()) / 4))));
        Files.getInstance().removeXP(e.getEntity().getPlayer(), Files.getInstance().getXP(Objects.requireNonNull(e.getEntity().getPlayer())) / 2);
        Jobs.economy.withdrawPlayer(e.getPlayer(), Jobs.economy.getBalance(e.getPlayer()) / 4);

    }
}
