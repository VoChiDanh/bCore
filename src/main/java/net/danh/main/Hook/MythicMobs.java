package net.danh.main.Hook;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import net.danh.main.Manager.Data;
import net.danh.main.Manager.PlayerData;
import net.danh.main.bCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

public class MythicMobs implements Listener {

    @EventHandler
    public void onMythicMobsKill(MythicMobDeathEvent mme) {
        Player p = (Player) mme.getKiller();
        String mobname = mme.getMobType().getInternalName();
        String mob = mme.getMob().getName();

        if (p == null) {
            return;
        }
        int max = Data.getconfigfile().getInt("MythicMobs.Default.max");
        int min = Data.getconfigfile().getInt("MythicMobs.Default.min");
        if (max == 0) {
            if (min == 0) {
                return;
            }
        }
        for (String getstring : Objects.requireNonNull(Data.getconfigfile().getConfigurationSection("MythicMobs.")).getKeys(false)) {
            if (mobname.equalsIgnoreCase(getstring)) {
                max = Data.getconfigfile().getInt("MythicMobs." + mobname + ".max");
                min = Data.getconfigfile().getInt("MythicMobs." + mobname + ".min");
                break;
            }
        }
        PlayerData.addKill(p, 1);
        int money = Data.getRandomInt(min, max);
        bCore.economy.depositPlayer(p, money);
        p.sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("KILL_MOBS"))
                .replaceAll("%money%", String.valueOf(money))
                .replaceAll("%mobs%", mob)));
    }
}
