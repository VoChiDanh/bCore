package net.danh.Events;

import io.lumine.mythic.bukkit.BukkitAPIHelper;
import net.danh.Manager.Data;
import net.danh.Manager.PlayerData;
import net.danh.Manager.Power;
import net.danh.bCore;
import net.danh.dcore.Indicator.Hologram;
import net.danh.dcore.Utils.Chat;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TranslatableComponent;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Animals;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Kill implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onNormalKill(@NotNull EntityDeathEvent e) {
        Player p = e.getEntity().getKiller();
        LivingEntity mob = e.getEntity();
        String vanillamobs = e.getEntityType().toString();
        if (p == null) {
            return;
        }
        if (bCore.getInstance().getServer().getPluginManager().isPluginEnabled("MythicMobs")) {
            BukkitAPIHelper mythicMobsAPI = new BukkitAPIHelper();
            if (mythicMobsAPI.isMythicMob(mob)) {
                return;
            }
        }

        int max = Data.getconfigfile().getInt("Vanilla.Default.max");
        int min = Data.getconfigfile().getInt("Vanilla.Default.min");
        if (max == 0) {
            if (min == 0) {
                return;
            }
        }
        for (String getEntityType : Objects.requireNonNull(Data.getconfigfile().getConfigurationSection("Vanilla.")).getKeys(false)) {
            if (vanillamobs.equalsIgnoreCase(getEntityType)) {
                max = Data.getconfigfile().getInt("Vanilla." + vanillamobs + ".max");
                min = Data.getconfigfile().getInt("Vanilla." + vanillamobs + ".min");
                break;
            }
        }
        if (mob instanceof Monster || mob instanceof Animals) {
            int money = Data.getRandomInt(min, max);
            EconomyResponse r = bCore.economy.depositPlayer(p, money);
            PlayerData.addXP(p, Data.getconfigfile().getInt("xp") + PlayerData.getleveluptimes(p));
            Power.addPower(p, Power.getRestorePower(p));
            if (r.transactionSuccess()) {
                Hologram.create(e.getEntity(), Objects.requireNonNull(Data.getlanguagefile().getString("KILL_MOBS"))
                        .replaceAll("%money%", String.valueOf(money))
                        .replaceAll("%mobs%", mob.getName()));
            }
        }
    }

}
