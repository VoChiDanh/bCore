package net.danh.Events;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.RegionQuery;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import net.danh.Manager.PvPPlayer;
import org.bukkit.Location;
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
import org.jetbrains.annotations.NotNull;

public class PvP implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamagedByEntity(@NotNull EntityDamageByEntityEvent e) {
        //check if attack was a player
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            //player who hit
            Player damager = (Player) e.getDamager();
            Player attacked = (Player) e.getEntity();
            boolean damagerState = PvPPlayer.getPvPStatus(damager);
            //player who was hit
            boolean attackedState = PvPPlayer.getPvPStatus(attacked);
            if (damagerState || attackedState) {
                e.setCancelled(true);
            }
        } else if (e.getDamager() instanceof Projectile) {
            Projectile arrow = (Projectile) e.getDamager();
            if (arrow.getShooter() instanceof Player) {
                if (e.getEntity() instanceof Player) {
                    Player attacked = (Player) e.getEntity();
                    Player damager = (Player) arrow.getShooter();
                    boolean damagerState = PvPPlayer.getPvPStatus(damager);
                    boolean attackedState = PvPPlayer.getPvPStatus(attacked);
                    LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(damager);
                    LocalPlayer localPlayer1 = WorldGuardPlugin.inst().wrapPlayer(attacked);
                    Location loc = new Location(damager.getWorld(), damager.getLocation().getBlockX(), damager.getLocation().getBlockY(), damager.getLocation().getBlockZ());
                    Location loc1 = new Location(attacked.getWorld(), attacked.getLocation().getBlockX(), attacked.getLocation().getBlockY(), attacked.getLocation().getBlockZ());
                    RegionContainer container = WorldGuardPlugin.inst().getRegionContainer();
                    RegionQuery query = container.createQuery();
                    if (damagerState || attackedState
                            || PvPPlayer.getProtectTime(damager) > 0 || PvPPlayer.getProtectTime(attacked) > 0
                            || !query.testState(loc, localPlayer, DefaultFlag.PVP) || !query.testState(loc1, localPlayer1, DefaultFlag.PVP)) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDamagedByEntity(@NotNull EntityCombustByEntityEvent e) {
        if (e.getCombuster() instanceof Arrow) {
            Arrow arrow = (Arrow) e.getCombuster();
            if (arrow.getShooter() instanceof Player && e.getEntity() instanceof Player) {
                Player damager = (Player) arrow.getShooter();
                Player attacked = (Player) e.getEntity();
                boolean damagerState = PvPPlayer.getPvPStatus(damager);
                boolean attackedState = PvPPlayer.getPvPStatus(attacked);
                LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(damager);
                LocalPlayer localPlayer1 = WorldGuardPlugin.inst().wrapPlayer(attacked);
                Location loc = new Location(damager.getWorld(), damager.getLocation().getBlockX(), damager.getLocation().getBlockY(), damager.getLocation().getBlockZ());
                Location loc1 = new Location(attacked.getWorld(), attacked.getLocation().getBlockX(), attacked.getLocation().getBlockY(), attacked.getLocation().getBlockZ());
                RegionContainer container = WorldGuardPlugin.inst().getRegionContainer();
                RegionQuery query = container.createQuery();
                if (damagerState || attackedState
                        || PvPPlayer.getProtectTime(damager) > 0 || PvPPlayer.getProtectTime(attacked) > 0
                        || !query.testState(loc, localPlayer, DefaultFlag.PVP) || !query.testState(loc1, localPlayer1, DefaultFlag.PVP)) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFishingPlayer(@NotNull PlayerFishEvent e) {
        if (e.getCaught() instanceof Player) {
            Player attacked = (Player) e.getCaught();
            Player damager = e.getPlayer();
            boolean damagerState = PvPPlayer.getPvPStatus(damager);
            boolean attackedState = PvPPlayer.getPvPStatus(attacked);
            LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(damager);
            LocalPlayer localPlayer1 = WorldGuardPlugin.inst().wrapPlayer(attacked);
            Location loc = new Location(damager.getWorld(), damager.getLocation().getBlockX(), damager.getLocation().getBlockY(), damager.getLocation().getBlockZ());
            Location loc1 = new Location(attacked.getWorld(), attacked.getLocation().getBlockX(), attacked.getLocation().getBlockY(), attacked.getLocation().getBlockZ());
            RegionContainer container = WorldGuardPlugin.inst().getRegionContainer();
            RegionQuery query = container.createQuery();
            if (damager.getInventory().getItemInMainHand().getType() == Material.FISHING_ROD || damager.getInventory().getItemInOffHand().getType() == Material.FISHING_ROD) {
                if (damagerState || attackedState
                        || PvPPlayer.getProtectTime(damager) > 0 || PvPPlayer.getProtectTime(attacked) > 0
                        || !query.testState(loc, localPlayer, DefaultFlag.PVP) || !query.testState(loc1, localPlayer1, DefaultFlag.PVP)) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
