package net.danh.jobs.Events;

import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.Files;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Eating implements Listener {

    @EventHandler
    public void onEating(PlayerItemConsumeEvent e) {
        if (e.getItem().getType() == Material.POTION) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
                }
            }.runTaskLaterAsynchronously(Jobs.getInstance(), 1L);
            Files.getInstance().addPower(e.getPlayer(), Files.getInstance().getRestorePower(e.getPlayer()));
        }
    }
}
