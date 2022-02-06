package net.danh.jobs.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class Regen implements Listener {


    @EventHandler(priority = EventPriority.LOWEST)
    public void onHealthRegen(EntityRegainHealthEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        EntityRegainHealthEvent.RegainReason r = e.getRegainReason();
        if (r == EntityRegainHealthEvent.RegainReason.EATING || r == EntityRegainHealthEvent.RegainReason.MAGIC || r == EntityRegainHealthEvent.RegainReason.MAGIC_REGEN || r == EntityRegainHealthEvent.RegainReason.REGEN || r == EntityRegainHealthEvent.RegainReason.SATIATED) {
            e.setCancelled(true);
            if (e.getEntity().hasPermission("heal.bypass"))
                e.setCancelled(false);
        }
    }
}

