package net.danh.Events;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.RegionQuery;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import net.danh.Contest.Mining;
import net.danh.Manager.Credit;
import net.danh.Manager.Data;
import net.danh.Manager.Event;
import net.danh.Manager.PlayerData;
import net.danh.bCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static net.danh.dcore.Utils.Player.sendPlayerMessage;

public class BlockBreak implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBreaking(@NotNull BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (bCore.getInstance().getServer().getPluginManager().getPlugin("WorldGuard") != null) {
            LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(p);
            Location loc = new Location(p.getWorld(), e.getBlock().getLocation().getBlockX(), e.getBlock().getLocation().getBlockY(), e.getBlock().getLocation().getBlockZ());
            RegionContainer container = WorldGuardPlugin.inst().getRegionContainer();
            RegionQuery query = container.createQuery();
            if (!query.testState(loc, localPlayer, DefaultFlag.BLOCK_BREAK) && !p.hasPermission("bCore.admin")) {
                e.setCancelled(true);
                return;
            }
        }
        if (Credit.getCredit(p) < 95) {
            sendPlayerMessage(p, Data.getlanguagefile().getString("LOW_CREDIT_WORKING"));
            e.setCancelled(true);
            return;
        }
        if (PlayerData.getBlocks(p) >= Data.getcmdfile().getInt("Commands.Mine.Amount")) {
            PlayerData.setBlocks(p, 0);
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), Objects.requireNonNull(Objects.requireNonNull(Data.getcmdfile().getString("Commands.Mine.Command"))
                    .replaceAll("%player%", p.getName())));
            sendPlayerMessage(p, Data.getlanguagefile().getString("MINER_REWARD"));
        }
        if (Mining.getStart()) {
            Event.addBlocksEvent(p, 1);
        }
        PlayerData.addBlocks(p, 1);
    }
}
