package net.danh.jobs.Events;

import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.Data;
import net.danh.jobs.Manager.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class Death implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        String DEAD = Data.getInstance().getlanguage().getString("DEAD");
        assert DEAD != null;
        e.getPlayer().sendMessage(Data.getInstance().convert(DEAD
                .replaceAll("%xp%", String.valueOf(PlayerData.getInstance().getXP(Objects.requireNonNull(e.getEntity().getPlayer())) / 2))
                .replaceAll("%money%", String.valueOf(Jobs.economy.getBalance(e.getPlayer()) / 4))));
        PlayerData.getInstance().removeXP(e.getEntity().getPlayer(), PlayerData.getInstance().getXP(Objects.requireNonNull(e.getEntity().getPlayer())) / 2);
        Jobs.economy.withdrawPlayer(e.getPlayer(), Jobs.economy.getBalance(e.getPlayer()) / 4);

    }
}
