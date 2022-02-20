package net.danh.bCore.Manager;

import net.danh.bCore.bCore;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Price {

    public static double getPrice(String name) {
        return Data.getpricefile().getInt("Price." + name);
    }

    public static double getBasePrice(String name) {
        return Data.getpricefile().getInt("BasePrice." + name);
    }

    public static void setPrice(String name, Double price) {
        Data.getpricefile().set("Price." + name, price);
        Data.saveprice();
    }

    public static void setBasePrice(String name, Double price) {
        Data.getpricefile().set("BasePrice." + name, price);
        Data.saveprice();
    }

    public static void addPrice(String name, Double price) {
        Data.getpricefile().set("Price." + name, getPrice(name) + price);
        Data.saveprice();
    }

    public static void addBasePrice(String name, Double price) {
        Data.getpricefile().set("BasePrice." + name, getBasePrice(name) + price);
        Data.saveprice();
    }

    public static void removePrice(String name, Double price) {
        Data.getpricefile().set("Price." + name, getPrice(name) - price);
        if (getPrice(name) <= 0) {
            addPrice(name, getBasePrice(name));
            Data.saveprice();
        } else {
            Data.saveprice();
        }

    }

    public static void removeBasePrice(String name, Double price) {
        Data.getpricefile().set("BasePrice." + name, getBasePrice(name) - price);
        if (getBasePrice(name) == 0) {
            addBasePrice(name, 1.0);
            Data.saveprice();
        }
    }

    public static void Sell(Player p, String name, Double number) {
        if (Storage.getStorage(p, name.toUpperCase()) >= number && Weight.getWeight(p) >= number) {
            Storage.removeStorage(p, name, number);
            Weight.removeWeight(p, number);
            Data.savestorage();
            Data.savedata();
            double money = getPrice(name) * number;
            EconomyResponse r = bCore.economy.depositPlayer(p.getPlayer(), money);
            if (r.transactionSuccess()) {
                p.sendMessage(Data.convert("&a+ " + money + "$"));
            }
        } else {
            p.sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("NOT_ENOUGH_MATERIAL"))));
        }
    }
}
