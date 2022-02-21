package net.danh.bCore.Events;

import net.danh.bCore.Manager.PvPPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerFishEvent;

public class PvP implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamagedByEntity(EntityDamageByEntityEvent e) {
        //check if attack was a player
        if (e.getDamager() instanceof Player damager && e.getEntity() instanceof Player attacked) {
            //player who hit
            boolean damagerState = PvPPlayer.getPvPStatus(damager);
            //player who was hit
            boolean attackedState = PvPPlayer.getPvPStatus(attacked);
            if (damagerState || attackedState) {
                e.setCancelled(true);
            }
        } else if (e.getDamager() instanceof Projectile arrow) {
            if (arrow.getShooter() instanceof Player) {
                if (e.getEntity() instanceof Player attacked) {
                    Player damager = (Player) arrow.getShooter();
                    boolean damagerState = PvPPlayer.getPvPStatus(damager);
                    boolean attackedState = PvPPlayer.getPvPStatus(attacked);
                    if (damagerState || attackedState) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamagedByEntity(EntityCombustByEntityEvent e) {
        if (e.getCombuster() instanceof Arrow arrow) {
            if (arrow.getShooter() instanceof Player damager && e.getEntity() instanceof Player attacked) {
                boolean damagerState = PvPPlayer.getPvPStatus(damager);
                boolean attackedState = PvPPlayer.getPvPStatus(attacked);
                if (damagerState || attackedState) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFishingPlayer(PlayerFishEvent e) {
        if (e.getCaught() instanceof final Player attacked) {
            final Player damager = e.getPlayer();
            boolean damagerState = PvPPlayer.getPvPStatus(damager);
            boolean attackedState = PvPPlayer.getPvPStatus(attacked);
            if (damager.getInventory().getItemInMainHand().getType() == Material.FISHING_ROD || damager.getInventory().getItemInOffHand().getType() == Material.FISHING_ROD) {
                if (damagerState || attackedState) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
