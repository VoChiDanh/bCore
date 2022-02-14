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
        return Files.getInstance().getprice().getInt("Price." + name);
    }

    public void setPrice(String name, Double price) {
        Files.getInstance().getprice().set("Price." + name, price);
        Files.getInstance().saveprice();
    }

    public void addPrice(String name, Double price) {
        Files.getInstance().getprice().set("Price." + name, getPrice(name) + price);
        Jobs.getInstance().getServer().broadcastMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("INCREASE_PRICE")
                .replaceAll("%block%", name.toLowerCase())
                .replaceAll("%price%", String.valueOf(getPrice(name)))));
        Files.getInstance().saveprice();
    }

    public void removePrice(String name, Double price) {
        Files.getInstance().getprice().set("Price." + name, getPrice(name) - price);
        Jobs.getInstance().getServer().broadcastMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("DECREASE_PRICE")
                .replaceAll("%block%", name.toLowerCase())
                .replaceAll("%price%", String.valueOf(getPrice(name)))));
        Files.getInstance().saveprice();
    }

    public void Sell(Player p, String name, Integer number) {
        if (Files.getInstance().getStorage(p, name.toUpperCase()) >= number && Files.getInstance().getWeight(p) >= number) {
            Files.getInstance().removeStorage(p, name, number);
            Files.getInstance().removeWeight(p, number);
            Files.getInstance().savestorage();
            Files.getInstance().savedata();
            double money = getPrice(name) * number;
            EconomyResponse r = Jobs.economy.depositPlayer(p.getPlayer(), money);
            if (r.transactionSuccess()) {
                p.sendMessage(Files.getInstance().convert("&a+ " + money + "$"));
            }
        } else {
            p.sendMessage(Files.getInstance().convert(Files.getInstance().getlanguage().getString("NOT_ENOUGH_MATERIAL")));
        }
    }
}
