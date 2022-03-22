package net.danh.Contest.Killing;

import net.danh.Contest.LeaderBoard;
import net.danh.Manager.Data;
import net.danh.Manager.Event;
import net.danh.Manager.PlayerData;
import net.danh.bCore;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TranslatableComponent;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;

public class Killing {

    static boolean Started = false;

    public static boolean getStart() {
        return Started;
    }

    public static void StartKilling() {
        Started = true;
        Bukkit.broadcastMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("CONTEST_START"))
                .replaceAll("%name%", "Tàn Sát")));
        for (Player p : bCore.getInstance().getServer().getOnlinePlayers()) {
            Event.setKillEvent(p, 0);
        }
        List<KillingManager> leaderboard = LeaderBoard.getLdKilling();
        (new BukkitRunnable() {
            int counter = Data.getconfigfile().getInt("EVENT_TIME");

            public void run() {
                counter--;
                for (Player p : bCore.getInstance().getServer().getOnlinePlayers()) {
                    p.spigot().sendMessage(ChatMessageType.valueOf(Data.getconfigfile().getString("Add_Type")),
                            new TranslatableComponent(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("COUNTDOWN"))
                                    .replaceAll("%time%", String.valueOf(counter)))));
                }
                if (counter <= 0) {
                    cancel();
                    Bukkit.broadcastMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("CONTEST_END"))
                            .replaceAll("%name%", "Tàn Sát")));
                    for (int i = 0; i < Math.min(leaderboard.size(), 5); i++) {
                        KillingManager top = leaderboard.get(i);
                        Bukkit.broadcastMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("TOP"))
                                .replaceAll("%top%", String.valueOf(i + 1))
                                .replaceAll("%player%", PlayerData.getPlayerName(top.getPlayer()))
                                .replaceAll("%amount%", Event.getKillEvent(top.getPlayer()) + " Kills")));
                        EconomyResponse r = bCore.economy.depositPlayer(top.getPlayer(), Event.getKillEvent(top.getPlayer()));
                        bCore.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), Objects.requireNonNull(Objects.requireNonNull(Data.getcmdfile().getString("Commands.Contest"))
                                .replaceAll("%player%", top.getPlayer().getName())
                                .replaceAll("%amount%", String.valueOf(Event.getKillEvent(top.getPlayer())))));
                        if (r.transactionSuccess()) {
                            top.getPlayer().sendMessage(Data.convert(Data.getlanguagefile().getString("REWARD")));
                        }
                    }
                    for (Player p : bCore.getInstance().getServer().getOnlinePlayers()) {
                        Event.setKillEvent(p, 0);
                    }
                    Started = false;
                }
            }
        }).runTaskTimer(bCore.getInstance(), 20L, 20L);
    }
}
