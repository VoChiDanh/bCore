package net.danh.Events;

import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import net.danh.Manager.Credit;
import net.danh.Manager.Data;
import net.danh.Manager.Status;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.jetbrains.annotations.NotNull;

public class DamagedByArrow implements Listener {

    @EventHandler
    public void onDamagedByArrow(@NotNull EntityCombustByEntityEvent e) {
        if (e.getCombuster() instanceof Arrow arrow) {
            if (arrow.getShooter() instanceof Player damager && e.getEntity() instanceof Player attacked) {
                boolean damagerState = Status.getPvPStatus(damager);
                boolean attackedState = Status.getPvPStatus(attacked);
                LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(damager);
                LocalPlayer localPlayer1 = WorldGuardPlugin.inst().wrapPlayer(attacked);
                Location loc = new Location(localPlayer.getWorld(), localPlayer.getLocation().getBlockX(), localPlayer.getLocation().getBlockY(), localPlayer.getLocation().getBlockZ());
                Location loc1 = new Location(localPlayer1.getWorld(), localPlayer1.getLocation().getBlockX(), localPlayer1.getLocation().getBlockY(), localPlayer1.getLocation().getBlockZ());
                RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                RegionQuery query = container.createQuery();
                if (Credit.getCredit(damager) <= 50) {
                    damager.sendMessage(Data.convert(Data.getlanguagefile().getString("LOW_CREDIT_WORKING")));
                    e.setCancelled(true);
                    return;
                }
                if (!damagerState || !attackedState || !query.testState(loc, localPlayer, Flags.PVP) || !query.testState(loc1, localPlayer1, Flags.PVP)) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
