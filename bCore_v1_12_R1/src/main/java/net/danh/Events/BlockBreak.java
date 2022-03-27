package net.danh.Events;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.RegionQuery;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import net.danh.Contest.Mining.Mining;
import net.danh.Manager.*;
import net.danh.bCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class BlockBreak implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onBlockBreak(@NotNull BlockBreakEvent e) {
        Material b = e.getBlock().getType();
        Player p = e.getPlayer();
        String NEED_MORE_POWER = Data.getlanguagefile().getString("NEED_MORE_POWER");
        String PREFIX = Data.getlanguagefile().getString("PREFIX");
        String FULL_STORAGE = Data.getlanguagefile().getString("FULL_STORAGE");
        String blocks = e.getBlock().getType().toString();
        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(p);
        Location loc = new Location(p.getWorld(), e.getBlock().getLocation().getBlockX(), e.getBlock().getLocation().getBlockY(), e.getBlock().getLocation().getBlockZ());
        RegionContainer container = WorldGuardPlugin.inst().getRegionContainer();
        RegionQuery query = container.createQuery();
        if (!query.testState(loc, localPlayer, DefaultFlag.BLOCK_BREAK) && !p.hasPermission("bCore.admin")) {
            e.setCancelled(true);
            return;
        }
        List<String> w = Data.getconfigfile().getStringList("disable-worlds");
        if (!w.contains(p.getWorld().getName())) {
            int max = 0;
            int min = 0;
            if (Credit.getCredit(p) < 100) {
                p.sendMessage(Data.convert(Data.getlanguagefile().getString("LOW_CREDIT_WORKING")));
                e.setCancelled(true);
                return;
            }
            if (Power.getPower(e.getPlayer()) <= Data.getconfigfile().getInt("minimum_power")) {
                e.getPlayer().sendMessage(Data.convert(PREFIX + NEED_MORE_POWER));
                e.setCancelled(true);
                return;
            }
            for (String getBlockType : Objects.requireNonNull(Data.getconfigfile().getConfigurationSection("Blocks.")).getKeys(false)) {
                if (blocks.equalsIgnoreCase(getBlockType)) {
                    max = Data.getconfigfile().getInt("Blocks." + blocks + ".max");
                    min = Data.getconfigfile().getInt("Blocks." + blocks + ".min");
                    break;
                }
            }
            if (min == 0 || max == 0) {
                return;
            }
            int kg = Data.getRandomInt(min, max);
            Bukkit.getScheduler().runTaskAsynchronously(bCore.getInstance(), () -> {
                if (Weight.getWeight(e.getPlayer()) + kg <= Weight.getMaxWeight(e.getPlayer())) {
                    if (e.getPlayer().getItemInHand().containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
                        int fortunelevel = e.getPlayer().getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                        int fortuneblocks = (fortunelevel * Data.getRandomInt(1, 10)) + kg;
                        Weight.addWeight(e.getPlayer(), fortuneblocks);
                        Storage.addStorage(e.getPlayer(), Items.getName(b.toString()), fortuneblocks);
                    } else {
                        Weight.addWeight(e.getPlayer(), kg);
                        Storage.addStorage(e.getPlayer(), Items.getName(b.toString()), kg);
                    }
                } else {
                    e.getPlayer().sendMessage(Data.convert(PREFIX + FULL_STORAGE));
                    e.setCancelled(true);
                    return;
                }
                PlayerData.addXP(e.getPlayer(), Data.getconfigfile().getInt("xp") + PlayerData.getleveluptimes(e.getPlayer()));
                Power.removePower(p, 1);
                if (Mining.getStart()) {
                    Event.addBlocksEvent(p, 1);
                }
                PlayerData.addBlocks(p, 1);
            });
        }
    }
}