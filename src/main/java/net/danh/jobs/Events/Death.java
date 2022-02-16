package net.danh.jobs.Events;

import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.Data;
import net.danh.jobs.Manager.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class Death implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        String DEAD = Data.getInstance().getlanguagefile().getString("DEAD");
        Player p = e.getEntity().getPlayer();
        Objects.requireNonNull(p).sendMessage(Data.getInstance().convert(Objects.requireNonNull(DEAD)
                .replaceAll("%xp%", String.valueOf(PlayerData.getInstance().getXP(Objects.requireNonNull(p)) / 2))
                .replaceAll("%money%", String.valueOf(Jobs.economy.getBalance(p) / 4))));
        PlayerData.getInstance().removeXP(p, PlayerData.getInstance().getXP(Objects.requireNonNull(p)) / 2);
        Jobs.economy.withdrawPlayer(p, Jobs.economy.getBalance(p) / 4);

    }
}
