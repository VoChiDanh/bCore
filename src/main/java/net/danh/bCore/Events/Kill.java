package net.danh.bCore.Events;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;
import net.danh.bCore.Manager.Data;
import net.danh.bCore.Manager.PlayerData;
import net.danh.bCore.bCore;
import org.bukkit.entity.Animals;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class Kill implements Listener {

    @EventHandler
    public void onNormalKill(EntityDeathEvent e) {
        Player p = e.getEntity().getKiller();
        LivingEntity mob = e.getEntity();
        String vanillamobs = e.getEntityType().toString();
        if (p == null) {
            return;
        }
        if (bCore.getInstance().getServer().getPluginManager().isPluginEnabled("MythicMobs")) {
            BukkitAPIHelper mythicMobsAPI = MythicMobs.inst().getAPIHelper();
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
            PlayerData.addKill(p, 1);
            int money = Data.getRandomInt(min, max);
            bCore.economy.depositPlayer(p, money);
            p.sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("KILL_MOBS"))
                    .replaceAll("%money%", String.valueOf(money))
                    .replaceAll("%mobs%", mob.getName())));
        }
    }

}
