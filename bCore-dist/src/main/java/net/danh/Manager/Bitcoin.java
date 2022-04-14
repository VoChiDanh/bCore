package net.danh.Manager;

import net.danh.bCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Bitcoin {

    public static HashMap<Player, Integer> bitcoin = new HashMap<>();
    public static HashMap<String, Integer> bitcoin_max = new HashMap<>();
    public static HashMap<String, Integer> bitcoin_buyprice = new HashMap<>();
    public static HashMap<String, Integer> bitcoin_sellprice = new HashMap<>();

    public static int getBitCoinData(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".BitCoins.BC");
    }

    public static int getBitCoin(@NotNull Player p) {
        return bitcoin.get(p);
    }

    public static void setBitCoin(@NotNull Player p, int number) {
        bitcoin.put(p, Math.max(number, 0));
        setBitCoinMax(number);
    }

    public static void addBitCoin(@NotNull Player p, int number) {
        if (getBitCoin(p) + number <= getBitCoinMax()) {
            bitcoin.replace(p, getBitCoin(p) + number);
            p.sendMessage(Data.convert("&a+ " + number + " BitCoins"));
        }
    }

    public static void removeBitCoin(Player p, int number) {
        if (getBitCoin(p) - number >= 0) {
            bitcoin.replace(p, getBitCoin(p) - number);
            p.sendMessage(Data.convert("&c- " + number + " BitCoins"));
        }
    }

    public static int getBitCoinMaxData() {
        return Data.getdatafile().getInt("BitCoinsMax.BC");
    }

    public static int getBitCoinMax() {
        return bitcoin_max.get("BitCoin_BC");
    }

    public static void setBitCoinMax(int number) {
        bitcoin_max.put("BitCoin_BC", Math.max(number, Data.getconfigfile().getInt("bitcoin_max")));
    }

    public static void addBitCoinMax(@NotNull Player p, int number) {
        bitcoin_max.replace("BitCoin_BC", getBitCoinMax() + number);
        p.sendMessage(Data.convert("&a+ " + number + " Giới Hạn BitCoins"));
    }

    public static void removeBitCoinMax(Player p, int number) {
        if (getBitCoinMax() - number >= Data.getconfigfile().getInt("bitcoin_max")) {
            bitcoin_max.replace("BitCoin_BC", getBitCoin(p) - number);
        } else {
            bitcoin_max.put("BitCoin_BC", Data.getconfigfile().getInt("bitcoin_max"));
        }
        p.sendMessage(Data.convert("&c- " + number + " Giới Hạn BitCoins"));
    }

    public static int getBitCoinBuyPriceData() {
        return Data.getdatafile().getInt("BitCoinsBuyPrice.BC");
    }

    public static int getBitCoinBuyPrice() {
        return bitcoin_buyprice.get("BitCoin_BC");
    }

    public static void setBitCoinBuyPrice(int number) {
        bitcoin_buyprice.put("BitCoin_BC", Math.max(number, Data.getconfigfile().getInt("bitcoin")));
    }

    private static void addBitCoinBuyPrice(int number) {
        bitcoin_buyprice.replace("BitCoin_BC", getBitCoinBuyPrice() + number);
        Bukkit.getServer().broadcastMessage(Data.convert("&6Giá Mua của BitCoin đã tăng lên&a " + String.format("%,d", getBitCoinBuyPrice()) + "$/1 BitCoin"));
    }

    private static void removeBitCoinBuyPrice(int number) {
        if (getBitCoinBuyPrice() - number > Data.getconfigfile().getInt("bitcoin")) {
            bitcoin_buyprice.replace("BitCoin_BC", getBitCoinBuyPrice() - number);
        } else {
            bitcoin_buyprice.put("BitCoin_BC", Data.getconfigfile().getInt("bitcoin"));
        }
        Bukkit.getServer().broadcastMessage(Data.convert("&6Giá Mua của BitCoin đã giảm xuống&c " + String.format("%,d", getBitCoinBuyPrice()) + "$/1 BitCoin"));
    }

    public static int getBitCoinSellPrice() {
        return bitcoin_sellprice.get("BitCoin_BC");
    }

    public static void setBitCoinSellPrice(int number) {
        bitcoin_sellprice.put("BitCoin_BC", Math.min(number, getBitCoinBuyPrice() / 2));
    }

    private static void addBitCoinSellPrice(int number) {
        if (getBitCoinSellPrice() + number <= getBitCoinBuyPrice() / 2) {
            bitcoin_sellprice.replace("BitCoin_BC", getBitCoinSellPrice() + number);
        } else {
            bitcoin_sellprice.put("BitCoin_BC", getBitCoinBuyPrice() / 2);
        }
        Bukkit.getServer().broadcastMessage(Data.convert("&6Giá Bán của BitCoin đã tăng lên&a " + String.format("%,d", getBitCoinSellPrice()) + "$/1 BitCoin"));
    }

    private static void removeBitCoinSellPrice(int number) {
        if (getBitCoinSellPrice() - number > 0) {
            bitcoin_sellprice.replace("BitCoin_BC", getBitCoinSellPrice() - number);
        } else {
            bitcoin_sellprice.put("BitCoin_BC", 0);
        }
        Bukkit.getServer().broadcastMessage(Data.convert("&6Giá Bán của BitCoin đã giảm xuống&c " + String.format("%,d", getBitCoinSellPrice()) + "$/1 BitCoin"));
    }

    public static void IncreasePrice(int number) {
        addBitCoinBuyPrice(number);
        addBitCoinSellPrice(number);
        DiscordWebhook webhook = new DiscordWebhook(Data.getdiscordfile().getString("discord-bitcoin-webhook-url"));
        DiscordWebhook.EmbedObject eb = new DiscordWebhook.EmbedObject();
        for (int i = 1; i < 26; i++) {

            if (Data.getdiscordfile().getString("bitcoin-discord-fields." + i + ".title") == null) continue;

            eb.addField(Data.getdiscordfile().getString("bitcoin-discord-fields." + i + ".title"),
                    Objects.requireNonNull(Data.getdiscordfile().getString("bitcoin-discord-fields." + i + ".content"))
                            .replace("{giaban}", String.format("%,d", getBitCoinBuyPrice()))
                            .replace("{giamua}", String.format("%,d", getBitCoinSellPrice()))
                            .replace("{type}", "+")
                            .replace("{amount}", String.format("%,d", number)),
                    Data.getdiscordfile().getBoolean("bitcoin-discord-fields." + i + ".inline"));
        }
        // Setting features of the embed
        eb.setColor(Color.decode(Objects.requireNonNull(Data.getdiscordfile().getString("discord-embed-bitcoin-discord-colour-increase"))));
        eb.setFooter(Data.getdiscordfile().getString("bitcoin-discord-embed-footer"), Data.getdiscordfile().getString("bitcoin-discord-embed-icon"));
        // Adding the embed to the webhook before sending off
        webhook.addEmbed(eb);

        AtomicBoolean error = new AtomicBoolean(false);

        Bukkit.getScheduler().runTaskAsynchronously(bCore.getInstance(), () -> {
            try {
                webhook.execute();
            } catch (Exception ex) {
                ex.printStackTrace();
                error.set(true);
            }
        });
    }

    public static void DecreasePrice(int number) {
        removeBitCoinBuyPrice(number);
        removeBitCoinSellPrice(number);
        DiscordWebhook webhook = new DiscordWebhook(Data.getdiscordfile().getString("discord-bitcoin-webhook-url"));
        DiscordWebhook.EmbedObject eb = new DiscordWebhook.EmbedObject();
        for (int i = 1; i < 26; i++) {

            if (Data.getdiscordfile().getString("bitcoin-discord-fields." + i + ".title") == null) continue;

            eb.addField(Data.getdiscordfile().getString("bitcoin-discord-fields." + i + ".title"),
                    Objects.requireNonNull(Data.getdiscordfile().getString("bitcoin-discord-fields." + i + ".content"))
                            .replace("{giaban}", String.format("%,d", getBitCoinBuyPrice()))
                            .replace("{giamua}", String.format("%,d", getBitCoinSellPrice()))
                            .replace("{type}", "-")
                            .replace("{amount}", String.format("%,d", number)),
                    Data.getdiscordfile().getBoolean("bitcoin-discord-fields." + i + ".inline"));
        }
        // Setting features of the embed
        eb.setColor(Color.decode(Objects.requireNonNull(Data.getdiscordfile().getString("discord-embed-bitcoin-discord-colour-decrease"))));
        eb.setFooter(Data.getdiscordfile().getString("bitcoin-discord-embed-footer"), Data.getdiscordfile().getString("bitcoin-discord-embed-icon"));
        // Adding the embed to the webhook before sending off
        webhook.addEmbed(eb);

        AtomicBoolean error = new AtomicBoolean(false);

        Bukkit.getScheduler().runTaskAsynchronously(bCore.getInstance(), () -> {
            try {
                webhook.execute();
            } catch (Exception ex) {
                ex.printStackTrace();
                error.set(true);
            }
        });
    }
}
