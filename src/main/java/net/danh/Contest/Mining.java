package net.danh.Contest;

import net.danh.Manager.Data;
import net.danh.Manager.Event;
import net.danh.bCore;
import net.danh.dcore.Utils.Chat;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

import static net.danh.dcore.Utils.Player.sendPlayerMessage;
import static net.danh.dcore.Utils.Player.sendPlayerMessageType;

public class Mining {

    static boolean Started = false;

    public static boolean getStart() {
        return Started;
    }

    public static void StartMining() {
        Started = true;
        Bukkit.broadcastMessage(Chat.colorize(Objects.requireNonNull(Data.getlanguagefile().getString("CONTEST_START"))
                .replaceAll("%name%", "Đào Khối")));
        for (Player p : bCore.getInstance().getServer().getOnlinePlayers()) {
            Event.setBlocksEvent(p, 0);
        }
        (new BukkitRunnable() {
            int counter = Data.getconfigfile().getInt("EVENT_TIME");

            public void run() {
                counter--;
                for (Player p : bCore.getInstance().getServer().getOnlinePlayers()) {
                    sendPlayerMessageType(p, Data.getconfigfile().getString("Add_Type"), Objects.requireNonNull(Data.getlanguagefile().getString("COUNTDOWN"))
                            .replaceAll("%time%", String.valueOf(counter)));
                }
                if (counter <= 0) {
                    cancel();
                    Bukkit.broadcastMessage(Chat.colorize(Objects.requireNonNull(Data.getlanguagefile().getString("CONTEST_END"))
                            .replaceAll("%name%", "Đào Khối")));
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        EconomyResponse r = bCore.economy.depositPlayer(p, Event.getBlockEvent(p));
                        bCore.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), Objects.requireNonNull(Objects.requireNonNull(Data.getcmdfile().getString("Commands.Contest"))
                                .replaceAll("%player%", p.getName())
                                .replaceAll("%amount%", String.valueOf(Event.getBlockEvent(p) / 100))));
                        if (r.transactionSuccess()) {
                            sendPlayerMessage(p, Data.getlanguagefile().getString("REWARD"));
                        }
                    }
                    for (Player p : bCore.getInstance().getServer().getOnlinePlayers()) {
                        Event.setBlocksEvent(p, 0);
                    }
                    Started = false;
                }
            }
        }).runTaskTimer(bCore.getInstance(), 20L, 20L);
    }
}
