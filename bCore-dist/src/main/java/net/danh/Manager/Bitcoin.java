package net.danh.Manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Bitcoin {

    public static HashMap<Player, Integer> bitcoin = new HashMap<>();
    public static HashMap<Player, Integer> bitcoin_max = new HashMap<>();
    public static HashMap<String, Integer> bitcoin_buyprice = new HashMap<>();
    public static HashMap<String, Integer> bitcoin_sellprice = new HashMap<>();

    public static int getBitCoinData(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".BitCoins");
    }

    public static int getBitCoin(@NotNull Player p) {
        return bitcoin.get(p);
    }

    public static void setBitCoin(@NotNull Player p, int number) {
        bitcoin.put(p, Math.max(number, 0));
    }

    public static void addBitCoin(@NotNull Player p, int number) {
        if (getBitCoin(p) + number <= getBitCoinMax(p)) {
            bitcoin.replace(p, getBitCoin(p) + number);
        } else {
            bitcoin.put(p, getBitCoinMax(p));
        }
        p.sendMessage(Data.convert("&a+ " + number + " BitCoins"));
    }

    public static void removeBitCoin(Player p, int number) {
        if (getBitCoin(p) - number >= 0) {
            bitcoin.replace(p, getBitCoin(p) - number);
        } else {
            bitcoin.put(p, 0);
        }
        p.sendMessage(Data.convert("&c- " + number + " BitCoins"));
    }

    public static int getBitCoinMaxData(@NotNull Player p) {
        return Data.getdatafile().getInt("players." + p.getName() + ".BitCoinsMax");
    }

    public static int getBitCoinMax(@NotNull Player p) {
        return bitcoin_max.get(p);
    }

    public static void setBitCoinMax(@NotNull Player p, int number) {
        bitcoin_max.put(p, Math.max(number, Data.getconfigfile().getInt("bitcoin_max")));
    }

    public static void addBitCoinMax(@NotNull Player p, int number) {
        bitcoin_max.replace(p, getBitCoinMax(p) + number);
        p.sendMessage(Data.convert("&a+ " + number + " Giới Hạn BitCoins"));
    }

    public static void removeBitCoinMax(Player p, int number) {
        if (getBitCoinMax(p) - number >= Data.getconfigfile().getInt("bitcoin_max")) {
            bitcoin_max.replace(p, getBitCoin(p) - number);
        } else {
            bitcoin_max.put(p, Data.getconfigfile().getInt("bitcoin_max"));
        }
        p.sendMessage(Data.convert("&c- " + number + " Giới Hạn BitCoins"));
    }

    public static int getBitCoinBuyPriceData() {
        return Data.getdatafile().getInt("BitCoinsBuyPrice");
    }

    public static int getBitCoinBuyPrice() {
        return bitcoin_buyprice.get("BC");
    }

    public static void setBitCoinBuyPrice(int number) {
        bitcoin_buyprice.put("BC", Math.max(number, Data.getconfigfile().getInt("bitcoin")));
    }

    public static void addBitCoinBuyPrice(int number) {
        bitcoin_buyprice.replace("BC", getBitCoinBuyPrice() + number);
        Bukkit.getServer().broadcastMessage(Data.convert("&6Giá Mua của BitCoin đã tăng lên&a " + String.format("%,d", getBitCoinBuyPrice()) + "$/1 BitCoin"));
    }

    public static void removeBitCoinBuyPrice(int number) {
        if (getBitCoinBuyPrice() - number > Data.getconfigfile().getInt("bitcoin")) {
            bitcoin_buyprice.replace("BC", getBitCoinBuyPrice() - number);
        } else {
            bitcoin_buyprice.put("BC", Data.getconfigfile().getInt("bitcoin"));
        }
        Bukkit.getServer().broadcastMessage(Data.convert("&6Giá Mua của BitCoin đã giảm xuống&c " + String.format("%,d", getBitCoinBuyPrice()) + "$/1 BitCoin"));
    }

    public static int getBitCoinSellPrice() {
        return bitcoin_sellprice.get("BC");
    }

    public static void setBitCoinSellPrice(int number) {
        bitcoin_sellprice.put("BC", Math.min(number, getBitCoinBuyPrice() / 2));
    }

    public static void addBitCoinSellPrice(int number) {
        if (getBitCoinSellPrice() + number <= getBitCoinBuyPrice() / 2) {
            bitcoin_sellprice.replace("BC", getBitCoinSellPrice() + number);
        } else {
            bitcoin_sellprice.put("BC", getBitCoinBuyPrice() / 2);
        }
        Bukkit.getServer().broadcastMessage(Data.convert("&6Giá Bán của BitCoin đã tăng lên&a " + String.format("%,d", getBitCoinSellPrice()) + "$/1 BitCoin"));
    }

    public static void removeBitCoinSellPrice(int number) {
        if (getBitCoinSellPrice() - number > 0) {
            bitcoin_sellprice.replace("BC", getBitCoinSellPrice() - number);
        } else {
            bitcoin_sellprice.put("BC", 0);
        }
        Bukkit.getServer().broadcastMessage(Data.convert("&6Giá Bán của BitCoin đã giảm xuống&c " + String.format("%,d", getBitCoinSellPrice()) + "$/1 BitCoin"));
    }
}
