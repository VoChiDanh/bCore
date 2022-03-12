package net.danh.Hook;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import net.danh.bCore;
import net.danh.Manager.Data;
import net.danh.Manager.PlayerData;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TranslatableComponent;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MythicMobs implements Listener {

    @EventHandler
    public void onMythicMobsKill(@NotNull MythicMobDeathEvent mme) {
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
        EconomyResponse r = bCore.economy.depositPlayer(p, money);
        if (r.transactionSuccess()) {
            p.spigot().sendMessage(ChatMessageType.valueOf(Data.getconfigfile().getString("Add_Type")),
                    new TranslatableComponent(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("KILL_MOBS"))
                            .replaceAll("%money%", String.valueOf(money))
                            .replaceAll("%mobs%", mob))));
        }
    }
}
