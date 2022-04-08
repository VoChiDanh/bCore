package net.danh.Events;

import net.danh.Manager.Data;
import net.danh.Manager.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.jetbrains.annotations.NotNull;

public class Enchant implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onEnchant(@NotNull EnchantItemEvent e) {
        Player p = e.getEnchanter();
        if (e.getExpLevelCost() <= PlayerData.getXP(p)) {
            PlayerData.removeXP(p, e.getExpLevelCost());
        } else {
            p.sendMessage(Data.convert("&cBạn không đủ kinh nghiệm để phù phép"));
            e.setCancelled(true);
        }
    }
}
