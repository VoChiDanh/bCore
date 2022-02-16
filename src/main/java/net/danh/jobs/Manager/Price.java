package net.danh.jobs.Manager;

import net.danh.jobs.Jobs;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;

public class Price {

    private static Price instance;

    public static Price getInstance() {

        if (instance == null) {
            instance = new Price();
        }
        return instance;
    }

    public double getPrice(String name) {
        return Data.getInstance().getprice().getInt("Price." + name);
    }

    public double getBasePrice(String name) {
        return Data.getInstance().getprice().getInt("BasePrice." + name);
    }

    public void setPrice(String name, Double price) {
        Data.getInstance().getprice().set("Price." + name, price);
        Data.getInstance().saveprice();
    }

    public void setBasePrice(String name, Double price) {
        Data.getInstance().getprice().set("BasePrice." + name, price);
        Data.getInstance().saveprice();
    }

    public void addPrice(String name, Double price) {
        Data.getInstance().getprice().set("Price." + name, getPrice(name) + price);
        Data.getInstance().saveprice();
    }

    public void addBasePrice(String name, Double price) {
        Data.getInstance().getprice().set("BasePrice." + name, getBasePrice(name) + price);
        Data.getInstance().saveprice();
    }

    public void removePrice(String name, Double price) {
        Data.getInstance().getprice().set("Price." + name, getPrice(name) - price);
        if (getPrice(name) <= 0) {
            addPrice(name, getBasePrice(name));
            Data.getInstance().saveprice();
        } else {
            Data.getInstance().saveprice();
        }

    }

    public void removeBasePrice(String name, Double price) {
        Data.getInstance().getprice().set("BasePrice." + name, getBasePrice(name) - price);
        if (getBasePrice(name) == 0) {
            addBasePrice(name, 1.0);
            Data.getInstance().saveprice();
            return;
        }
    }

    public void Sell(Player p, String name, Integer number) {
        if (Storage.getInstance().getStorage(p, name.toUpperCase()) >= number && Weight.getInstance().getWeight(p) >= number) {
            Storage.getInstance().removeStorage(p, name, number);
            Weight.getInstance().removeWeight(p, number);
            Data.getInstance().savestorage();
            Data.getInstance().savedata();
            double money = getPrice(name) * number;
            EconomyResponse r = Jobs.economy.depositPlayer(p.getPlayer(), money);
            if (r.transactionSuccess()) {
                p.sendMessage(Data.getInstance().convert("&a+ " + money + "$"));
            }
        } else {
            p.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguage().getString("NOT_ENOUGH_MATERIAL")));
        }
    }
}
