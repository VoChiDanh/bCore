package net.danh.jobs.Events;

import com.cryptomorin.xseries.XMaterial;
import net.danh.jobs.Manager.Files;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class Fishing implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onFishing(PlayerFishEvent e) {
        String NEED_MORE_POWER = Files.getInstance().getlanguage().getString("NEED_MORE_POWER");
        String PREFIX = Files.getInstance().getlanguage().getString("PREFIX");
        String LUCKY = Files.getInstance().getlanguage().getString("LUCKY");
        String NOT_FISH = Files.getInstance().getlanguage().getString("NOT_FISH");
        Integer POWER = Files.getInstance().getconfig().getInt("Lucky_Power");
        Integer XP = Files.getInstance().getconfig().getInt("Lucky_XP");
        Integer LEVELUP = Files.getInstance().getleveluptimes(e.getPlayer());
        if (Files.getInstance().getPower(e.getPlayer()) <= Files.getInstance().getconfig().getInt("minimum_power")) {
            e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + NEED_MORE_POWER));
            e.setCancelled(true);
            return;
        } else {
            if (e.getCaught() instanceof Item) {
                if (((Item) e.getCaught()).getItemStack().getType() == XMaterial.TROPICAL_FISH.parseMaterial()
                        || (((Item) e.getCaught()).getItemStack().getType()) == XMaterial.PUFFERFISH.parseMaterial()
                        || (((Item) e.getCaught()).getItemStack().getType()) == XMaterial.COD.parseMaterial()
                        || (((Item) e.getCaught()).getItemStack().getType()) == XMaterial.SALMON.parseMaterial()) {
                    double systemchance = Math.random() * 100.0D;
                    double playerchance = Files.getInstance().getLuckyChance(e.getPlayer());
                    if (playerchance >= systemchance) {
                        if (Files.getInstance().getGang(e.getPlayer()) != null) {
                            Files.getInstance().addXP(e.getPlayer(), 2 + Files.getInstance().getleveluptimes(e.getPlayer()));
                        }
                        Files.getInstance().addPower(e.getPlayer(), 5 + Files.getInstance().getleveluptimes(e.getPlayer()));
                        e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + LUCKY
                                .replaceAll("%power%", String.valueOf(POWER + LEVELUP))
                                .replaceAll("%xp%", String.valueOf(XP + LEVELUP))));
                        return;
                    }

                    if (Files.getInstance().getGang(e.getPlayer()) != null) {
                        Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp") + Files.getInstance().getleveluptimes(e.getPlayer()));
                    }
                    Files.getInstance().removePower(e.getPlayer(), 1);
                    e.setCancelled(false);
                } else {
                    e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + NOT_FISH));
                    e.setCancelled(true);
                    return;
                }
                if (((Item) e.getCaught()).getItemStack().getItemMeta().hasEnchants()) {
                    e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + NOT_FISH));
                    e.setCancelled(true);
                    return;
                }
            }
        }

    }
}