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
        Event.SaveEvent(p);
        PlayerData.SaveBlock(p);
        PlayerData.SaveLevel(p);
        PlayerData.SaveXP(p);
        PlayerData.SaveLevelup(p);
        Bank.SaveBankLevel(p);
        Bank.SaveBankMoney(p);
        Credit.SaveCredit(p);
        Lucky.SaveLuckyChance(p);
        Power.SavePower(p);
        Power.SaveMaxPower(p);
        Power.SaveRestorePower(p);
    }
}
