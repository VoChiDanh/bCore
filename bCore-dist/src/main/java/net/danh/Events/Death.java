package net.danh.Events;

import net.danh.Manager.Credit;
import net.danh.Manager.Data;
import net.danh.Manager.PlayerData;
import net.danh.Manager.Points;
import net.danh.bCore;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Death implements Listener {

    @EventHandler
    public void onDeath(@NotNull PlayerDeathEvent e) {
        String DEAD = Data.getlanguagefile().getString("DEAD");
        Player p = e.getEntity();
        Player k = p.getKiller();
        PlayerData.removeXP(p, PlayerData.getXP(Objects.requireNonNull(p)) / 2);
        Credit.removeCredit(p, 0.5D);
        if (k != null) {
            Credit.removeCredit(k, 1D);
            Points.addPvPPoints(p, 1);
        }
        EconomyResponse r = bCore.economy.withdrawPlayer(p, bCore.economy.getBalance(p) / 4);
        if (r.transactionSuccess()) {
            Objects.requireNonNull(p).sendMessage(Data.convert(Objects.requireNonNull(DEAD)
                    .replaceAll("%xp%", String.valueOf(PlayerData.getXP(Objects.requireNonNull(p)) / 2))
                    .replaceAll("%money%", String.valueOf(bCore.economy.getBalance(p) / 4))));
        }
    }
}
