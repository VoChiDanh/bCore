package net.danh.Events;

import net.danh.Manager.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Enchant implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onEnchant(@NotNull EnchantItemEvent e) {
        Player p = e.getEnchanter();
        List<String> w = Data.getconfigfile().getStringList("disable-worlds");
        if (w.contains(p.getWorld().getName())) {
            e.setCancelled(true);
        }
    }
}
