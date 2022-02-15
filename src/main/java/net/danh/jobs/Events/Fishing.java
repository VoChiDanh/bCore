package net.danh.jobs.Events;

import com.cryptomorin.xseries.XMaterial;
import net.danh.jobs.Manager.*;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class Fishing implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFishing(PlayerFishEvent e) {
        String NEED_MORE_POWER = Data.getInstance().getlanguage().getString("NEED_MORE_POWER");
        String PREFIX = Data.getInstance().getlanguage().getString("PREFIX");
        String LUCKY = Data.getInstance().getlanguage().getString("LUCKY");
        String NOT_FISH = Data.getInstance().getlanguage().getString("NOT_FISH");
        Integer POWER = Data.getInstance().getconfig().getInt("Lucky_Power");
        Integer XP = Data.getInstance().getconfig().getInt("Lucky_XP");
        Integer LEVELUP = Gang.getInstance().getleveluptimes(e.getPlayer());
        if (Power.getInstance().getPower(e.getPlayer()) <= Data.getInstance().getconfig().getInt("minimum_power")) {
            e.getPlayer().sendMessage(Data.getInstance().convert(PREFIX + NEED_MORE_POWER));
            e.setCancelled(true);
            return;
        } else {
            if (e.getCaught() instanceof Item) {
                if (((Item) e.getCaught()).getItemStack().getType() == XMaterial.TROPICAL_FISH.parseMaterial()
                        || (((Item) e.getCaught()).getItemStack().getType()) == XMaterial.PUFFERFISH.parseMaterial()
                        || (((Item) e.getCaught()).getItemStack().getType()) == XMaterial.COD.parseMaterial()
                        || (((Item) e.getCaught()).getItemStack().getType()) == XMaterial.SALMON.parseMaterial()) {
                    double systemchance = Math.random() * 100.0D;
                    double playerchance = Lucky.getInstance().getLuckyChance(e.getPlayer());
                    if (playerchance >= systemchance) {
                        if (Gang.getInstance().getGang(e.getPlayer()) != null) {
                            PlayerData.getInstance().addXP(e.getPlayer(), 2 + Gang.getInstance().getleveluptimes(e.getPlayer()));
                        }
                        Power.getInstance().addPower(e.getPlayer(), 5 + Gang.getInstance().getleveluptimes(e.getPlayer()));
                        e.getPlayer().sendMessage(Data.getInstance().convert(PREFIX + LUCKY
                                .replaceAll("%power%", String.valueOf(POWER + LEVELUP))
                                .replaceAll("%xp%", String.valueOf(XP + LEVELUP))));
                        return;
                    }

                    if (Gang.getInstance().getGang(e.getPlayer()) != null) {
                        PlayerData.getInstance().addXP(e.getPlayer(), Data.getInstance().getconfig().getInt("xp") + Gang.getInstance().getleveluptimes(e.getPlayer()));
                    }
                    Power.getInstance().removePower(e.getPlayer(), 1);
                    e.setCancelled(false);
                } else {
                    e.getPlayer().sendMessage(Data.getInstance().convert(PREFIX + NOT_FISH));
                    e.setCancelled(true);
                    return;
                }
                if (((Item) e.getCaught()).getItemStack().getItemMeta().hasEnchants()) {
                    e.getPlayer().sendMessage(Data.getInstance().convert(PREFIX + NOT_FISH));
                    e.setCancelled(true);
                    return;
                }
            }
        }

    }
}