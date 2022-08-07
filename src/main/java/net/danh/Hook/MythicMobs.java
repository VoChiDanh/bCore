package net.danh.Hook;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import net.danh.Manager.Data;
import net.danh.Manager.PlayerData;
import net.danh.Manager.Power;
import net.danh.bCore;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class MythicMobs implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onMythicMobsKill(@NotNull MythicMobDeathEvent mme) {
        Player p = (Player) mme.getKiller();
        String mobname = mme.getMobType().getInternalName();
        String mob = mme.getMob().getDisplayName();

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
        int money = Data.getRandomInt(min, max);
        EconomyResponse r = bCore.economy.depositPlayer(p, money);
        int xp = Data.getconfigfile().getInt("xp") + PlayerData.getleveluptimes(p);
        int power = new Random().nextInt(Power.getRestorePower(p) * 2);
        PlayerData.addXP(p, xp);
        Power.addPower(p, power);
    }
}
