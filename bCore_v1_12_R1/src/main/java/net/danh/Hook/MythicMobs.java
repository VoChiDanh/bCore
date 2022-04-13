package net.danh.Hook;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import net.danh.Manager.Data;
import net.danh.Manager.PlayerData;
import net.danh.Manager.Power;
import net.danh.bCore;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TranslatableComponent;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MythicMobs implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onMythicMobsKill(@NotNull MythicMobDeathEvent mme) {
        Player p = (Player) mme.getKiller();
        String mobname = mme.getMobType().getInternalName();
        String mob = mme.getMob().getType().getDisplayName();

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
        PlayerData.addXP(p, Data.getconfigfile().getInt("xp") + PlayerData.getleveluptimes(p));
        Power.addPower(p, Power.getRestorePower(p));
        if (r.transactionSuccess()) {
            p.spigot().sendMessage(ChatMessageType.valueOf(Data.getconfigfile().getString("Add_Type")),
                    new TranslatableComponent(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("KILL_MOBS"))
                            .replaceAll("%money%", String.valueOf(money))
                            .replaceAll("%mobs%", mob))));
        }
    }
}
