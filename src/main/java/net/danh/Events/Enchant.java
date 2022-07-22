package net.danh.Events;

import net.danh.Manager.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.jetbrains.annotations.NotNull;

import static net.danh.dcore.Utils.Player.sendPlayerMessage;

public class Enchant implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onEnchant(@NotNull EnchantItemEvent e) {
        Player p = e.getEnchanter();
        if (e.getExpLevelCost() * 1000 < PlayerData.getXP(p)) {
            PlayerData.removeXP(p, e.getExpLevelCost() * 1000);
        } else {
            sendPlayerMessage(p, "&cBạn không đủ " + e.getExpLevelCost() * 1000 + " kinh nghiệm để phù phép");
            e.setCancelled(true);
        }
    }
}
