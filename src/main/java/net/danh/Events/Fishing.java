package net.danh.Events;

import net.danh.Manager.Data;
import net.danh.Manager.Lucky;
import net.danh.Manager.PlayerData;
import net.danh.Manager.Power;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Fishing implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onFishing(@NotNull PlayerFishEvent e) {
        String NEED_MORE_POWER = Data.getlanguagefile().getString("NEED_MORE_POWER");
        String PREFIX = Data.getlanguagefile().getString("PREFIX");
        String LUCKY = Data.getlanguagefile().getString("LUCKY");
        String NOT_FISH = Data.getlanguagefile().getString("NOT_FISH");
        Integer POWER = Data.getconfigfile().getInt("Lucky_Power");
        Integer XP = Data.getconfigfile().getInt("Lucky_XP");
        Integer LEVELUP = PlayerData.getleveluptimes(e.getPlayer());
        if (Power.getPower(e.getPlayer()) <= Data.getconfigfile().getInt("minimum_power")) {
            e.getPlayer().sendMessage(Data.convert(PREFIX + NEED_MORE_POWER));
            e.setCancelled(true);
        } else {
            if (e.getCaught() instanceof Item) {
                ((Item) e.getCaught()).getItemStack().getType();
                double systemchance = Math.random() * 100.0D;
                double playerchance = Lucky.getLuckyChance(e.getPlayer());
                if (playerchance >= systemchance) {
                    PlayerData.addXP(e.getPlayer(), 2 + PlayerData.getleveluptimes(e.getPlayer()));
                    Power.addPower(e.getPlayer(), 5 + PlayerData.getleveluptimes(e.getPlayer()));
                    e.getPlayer().sendMessage(Data.convert(PREFIX + Objects.requireNonNull(LUCKY)
                            .replaceAll("%power%", String.valueOf(POWER + LEVELUP))
                            .replaceAll("%xp%", String.valueOf(XP + LEVELUP))));
                    return;
                }

                PlayerData.addXP(e.getPlayer(), Data.getconfigfile().getInt("xp") + PlayerData.getleveluptimes(e.getPlayer()));
                Power.removePower(e.getPlayer(), 1);
                e.setCancelled(false);
                if (Objects.requireNonNull(((Item) e.getCaught()).getItemStack().getItemMeta()).hasEnchants()) {
                    e.getPlayer().sendMessage(Data.convert(PREFIX + NOT_FISH));
                    e.setCancelled(true);
                }
            }
        }

    }
}