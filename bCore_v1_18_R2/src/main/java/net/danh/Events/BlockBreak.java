package net.danh.Events;

import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import net.danh.Contest.Mining.Mining;
import net.danh.Manager.Credit;
import net.danh.Manager.Data;
import net.danh.Manager.Event;
import net.danh.Manager.PlayerData;
import net.danh.bCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BlockBreak implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBreaking(@NotNull BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (bCore.getInstance().getServer().getPluginManager().getPlugin("WorldGuard") != null) {
            LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(p);
            Location loc = new com.sk89q.worldedit.util.Location(localPlayer.getWorld(), e.getBlock().getLocation().getBlockX(), e.getBlock().getLocation().getBlockY(), e.getBlock().getLocation().getBlockZ());
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionQuery query = container.createQuery();
            if (!query.testState(loc, localPlayer, Flags.BLOCK_BREAK) && !p.hasPermission("bCore.admin")) {
                e.setCancelled(true);
                return;
            }
        }
        if (Credit.getCredit(p) < 95) {
            p.sendMessage(Data.convert(Data.getlanguagefile().getString("LOW_CREDIT_WORKING")));
            e.setCancelled(true);
            return;
        }
        if (PlayerData.getBlocks(p) >= Data.getcmdfile().getInt("Commands.Mine.Amount")) {
            PlayerData.setBlocks(p, 0);
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), Objects.requireNonNull(Objects.requireNonNull(Data.getcmdfile().getString("Commands.Mine.Command")).replaceAll("%player%", p.getName())));
            p.sendMessage(Data.convert(Data.getlanguagefile().getString("MINER_REWARD")));
        }
        if (Mining.getStart()) {
            Event.addBlocksEvent(p, 1);
        }
        PlayerData.addBlocks(p, 1);
    }
}
