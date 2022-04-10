package net.danh.Events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.jetbrains.annotations.NotNull;

public class Interact implements Listener {

    @EventHandler
    public void onShooted(@NotNull ProjectileHitEvent e) {
        if (e.getEntityType() == EntityType.ARROW) {
            e.setCancelled(true);
            e.getEntity().remove();
        }
    }

    @EventHandler
    public void onBow(@NotNull EntityShootBowEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void StoneForm(@NotNull BlockFormEvent e) {
        e.setCancelled(true);
    }
}
