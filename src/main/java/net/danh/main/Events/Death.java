package net.danh.main.Events;

import net.danh.main.Manager.Data;
import net.danh.main.Manager.PlayerData;
import net.danh.main.bCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class Death implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        String DEAD = Data.getlanguagefile().getString("DEAD");
        Player p = e.getEntity().getPlayer();
        Objects.requireNonNull(p).sendMessage(Data.convert(Objects.requireNonNull(DEAD)
                .replaceAll("%xp%", String.valueOf(PlayerData.getXP(Objects.requireNonNull(p)) / 2))
                .replaceAll("%money%", String.valueOf(bCore.economy.getBalance(p) / 4))));
        PlayerData.removeXP(p, PlayerData.getXP(Objects.requireNonNull(p)) / 2);
        bCore.economy.withdrawPlayer(p, bCore.economy.getBalance(p) / 4);

    }
}
