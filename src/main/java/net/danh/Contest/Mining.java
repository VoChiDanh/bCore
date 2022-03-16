package net.danh.Contest;

import net.danh.Manager.Data;
import net.danh.Manager.Event;
import net.danh.bCore;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TranslatableComponent;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;

public class Mining {

    static boolean Started = false;

    public static boolean getStart() {
        return Started;
    }

    public static void StartMining() {
        Started = true;
        Bukkit.broadcastMessage(Data.convert(Data.getlanguagefile().getString("MINING_START")));
        for (Player p : bCore.getInstance().getServer().getOnlinePlayers()) {
            Event.setBlocksEvent(p, 0);
        }
        List<MiningManager> leaderboard = LeaderBoard.getLdMining();
        (new BukkitRunnable() {
            int counter = Data.getconfigfile().getInt("EVENT_TIME");

            public void run() {
                counter--;
                if (counter == 0) {
                    Bukkit.broadcastMessage(Data.convert(Data.getlanguagefile().getString("MINING_END")));
                    for (int i = 0; i < Math.min(leaderboard.size(), 10); i++) {
                        MiningManager top = leaderboard.get(i);
                        Bukkit.broadcastMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("TOP"))
                                .replaceAll("%top%", String.valueOf(i + 1))
                                .replaceAll("%player%", top.getPlayer().getName())
                                .replaceAll("%block%", String.valueOf(Event.getBlockEvent(top.getPlayer())))));
                        EconomyResponse r = bCore.economy.depositPlayer(top.getPlayer(), Event.getBlockEvent(top.getPlayer()));
                        bCore.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), Objects.requireNonNull(Objects.requireNonNull(Data.getcmdfile().getString("Commands.Contest"))
                                .replaceAll("%player%", top.getPlayer().getName())
                                .replaceAll("%amount%", String.valueOf(Event.getBlockEvent(top.getPlayer())))));
                        if (r.transactionSuccess()) {
                            top.getPlayer().sendMessage(Data.convert(Data.getlanguagefile().getString("REWARD")));
                        }
                    }
                    for (Player p : bCore.getInstance().getServer().getOnlinePlayers()) {
                        Event.setBlocksEvent(p, 0);
                    }
                    cancel();
                    Started = false;
                } else {
                    for (Player p : bCore.getInstance().getServer().getOnlinePlayers()) {
                        p.spigot().sendMessage(ChatMessageType.valueOf(Data.getconfigfile().getString("Add_Type")),
                                new TranslatableComponent(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("COUNTDOWN"))
                                        .replaceAll("%time%", String.valueOf(counter)))));
                    }
                }
            }
        }).runTaskTimer(bCore.getInstance(), 20L, 20L);
    }
}
