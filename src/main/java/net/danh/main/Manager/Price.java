package net.danh.main.Manager;

import net.danh.main.bCore;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Price {

    public static Integer getPrice(String name) {
        return Data.getpricefile().getInt("Price." + name);
    }

    public static Integer getBasePrice(String name) {
        return Data.getpricefile().getInt("BasePrice." + name);
    }

    public static void setPrice(String name, Integer price) {
        Data.getpricefile().set("Price." + name, price);
        Data.saveprice();
    }

    public static void setBasePrice(String name, Integer price) {
        Data.getpricefile().set("BasePrice." + name, price);
        Data.saveprice();
    }

    public static void addPrice(String name, Integer price) {
        Data.getpricefile().set("Price." + name, getPrice(name) + price);
        Data.saveprice();
    }

    public static void addBasePrice(String name, Integer price) {
        Data.getpricefile().set("BasePrice." + name, getBasePrice(name) + price);
        Data.saveprice();
    }

    public static void removePrice(String name, Integer price) {
        Data.getpricefile().set("Price." + name, getPrice(name) - price);
        if (getPrice(name) <= 0) {
            addPrice(name, getBasePrice(name));
            Data.saveprice();
        } else {
            Data.saveprice();
        }

    }

    public static void removeBasePrice(String name, Integer price) {
        Data.getpricefile().set("BasePrice." + name, getBasePrice(name) - price);
        if (getBasePrice(name) == 0) {
            addBasePrice(name, 1);
            Data.saveprice();
        }
    }

    public static void Sell(Player p, String name, Integer number) {
        if (Storage.getStorage(p, name.toUpperCase()) >= number && Weight.getWeight(p) >= number) {
            Storage.removeStorage(p, name, number);
            Weight.removeWeight(p, number);
            Data.savestorage();
            Data.savedata();
            int money = getPrice(name) * number;
            EconomyResponse r = bCore.economy.depositPlayer(p.getPlayer(), money);
            if (r.transactionSuccess()) {
                p.sendMessage(Data.convert("&a+ " + money + "$"));
            }
        } else {
            p.sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("NOT_ENOUGH_MATERIAL"))));
        }
    }
}
