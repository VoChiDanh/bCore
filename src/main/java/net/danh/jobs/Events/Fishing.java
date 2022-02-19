package net.danh.jobs.Events;

import com.cryptomorin.xseries.XMaterial;
import net.danh.jobs.Manager.*;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.Objects;

public class Fishing implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFishing(PlayerFishEvent e) {
        String NEED_MORE_POWER = Data.getlanguagefile().getString("NEED_MORE_POWER");
        String PREFIX = Data.getlanguagefile().getString("PREFIX");
        String LUCKY = Data.getlanguagefile().getString("LUCKY");
        String NOT_FISH = Data.getlanguagefile().getString("NOT_FISH");
        Integer POWER = Data.getconfigfile().getInt("Lucky_Power");
        Integer XP = Data.getconfigfile().getInt("Lucky_XP");
        Integer LEVELUP = Gang.getleveluptimes(e.getPlayer());
        if (Power.getPower(e.getPlayer()) <= Data.getconfigfile().getInt("minimum_power")) {
            e.getPlayer().sendMessage(Data.convert(PREFIX + NEED_MORE_POWER));
            e.setCancelled(true);
        } else {
            if (e.getCaught() instanceof Item) {
                if (((Item) e.getCaught()).getItemStack().getType() == XMaterial.TROPICAL_FISH.parseMaterial()
                        || (((Item) e.getCaught()).getItemStack().getType()) == XMaterial.PUFFERFISH.parseMaterial()
                        || (((Item) e.getCaught()).getItemStack().getType()) == XMaterial.COD.parseMaterial()
                        || (((Item) e.getCaught()).getItemStack().getType()) == XMaterial.SALMON.parseMaterial()) {
                    double systemchance = Math.random() * 100.0D;
                    double playerchance = Lucky.getLuckyChance(e.getPlayer());
                    if (playerchance >= systemchance) {
                        if (Gang.getGang(e.getPlayer()) != null) {
                            PlayerData.addXP(e.getPlayer(), 2 + Gang.getleveluptimes(e.getPlayer()));
                        }
                        Power.addPower(e.getPlayer(), 5 + Gang.getleveluptimes(e.getPlayer()));
                        e.getPlayer().sendMessage(Data.convert(PREFIX + Objects.requireNonNull(LUCKY)
                                .replaceAll("%power%", String.valueOf(POWER + LEVELUP))
                                .replaceAll("%xp%", String.valueOf(XP + LEVELUP))));
                        return;
                    }

                    if (Gang.getGang(e.getPlayer()) != null) {
                        PlayerData.addXP(e.getPlayer(), Data.getconfigfile().getInt("xp") + Gang.getleveluptimes(e.getPlayer()));
                    }
                    Power.removePower(e.getPlayer(), 1);
                    e.setCancelled(false);
                } else {
                    e.getPlayer().sendMessage(Data.convert(PREFIX + NOT_FISH));
                    e.setCancelled(true);
                    return;
                }
                if (((Item) e.getCaught()).getItemStack().getItemMeta().hasEnchants()) {
                    e.getPlayer().sendMessage(Data.convert(PREFIX + NOT_FISH));
                    e.setCancelled(true);
                }
            }
        }

    }
}