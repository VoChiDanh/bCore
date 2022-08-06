package net.danh.Events;

import net.danh.PClass.CName;
import net.danh.PClass.PClass;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.jetbrains.annotations.NotNull;

public class Interact implements Listener {

    @EventHandler
    public void onShooted(@NotNull ProjectileHitEvent e) {
        if (e.getEntity().getShooter() instanceof Player p) {
            if (!PClass.getPClass(p).equals(CName.MAGE)) {
                if (e.getEntityType() == EntityType.ARROW) {
                    e.setCancelled(true);
                    e.getEntity().remove();
                }
            }
        }
    }

    @EventHandler
    public void onBow(@NotNull EntityShootBowEvent e) {
        Player p = e.getEntity().getKiller();
        if (p != null) {
            if (!PClass.getPClass(p).equals(CName.MAGE)) {
                e.setCancelled(true);
            }
        }
    }

}
