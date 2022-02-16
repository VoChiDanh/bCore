package net.danh.jobs.Manager;

import net.danh.jobs.Jobs;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Bank {

    private static Bank instance;

    public static Bank getInstance() {

        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

    public int getBankMoney(@NotNull Player p) {
        return Data.getInstance().getbankfile().getInt("banks." + p.getName() + ".money");
    }

    public void setBankMoney(@NotNull Player p, Integer number) {
        Data.getInstance().getbankfile().set("banks." + p.getName() + ".money", number);
        Data.getInstance().savebank();
    }

    public void addBankMoney(Player p, Integer number) {
        if (getBankMaxMoney(p) <= (getBankMoney(p) + number) || getBankMaxMoney(p) <= getBankMoney(p)) {
            p.sendMessage(Data.getInstance().convert(Objects.requireNonNull(Data.getInstance().getlanguagefile().getString("FULL_BANK"))));
        } else {
            Data.getInstance().getbankfile().set("banks." + p.getName() + ".money", getBankMoney(p) + number);
            Data.getInstance().savebank();
        }
    }

    public void removeBankMoney(Player p, Integer number) {
        if (getBankMoney(p) >= number) {
            Data.getInstance().getbankfile().set("banks." + p.getName() + ".money", getBankMoney(p) - number);
        } else {
            Data.getInstance().getbankfile().set("banks." + p.getName() + ".money", 0);
        }
        Data.getInstance().savebank();
    }

    public int getBankMaxMoney(@NotNull Player p) {
        return Data.getInstance().getbankfile().getInt("banks." + p.getName() + ".maxmoney");
    }

    public void setBankMaxMoney(@NotNull Player p, Integer number) {
        Data.getInstance().getbankfile().set("banks." + p.getName() + ".maxmoney", number);
        Data.getInstance().savebank();
    }

    public void addBankMaxMoney(@NotNull Player p, Integer number) {
        Data.getInstance().getbankfile().set("banks." + p.getName() + ".maxmoney", getBankMaxMoney(p) + number);
        Data.getInstance().savebank();
    }

    public void removeBankMaxMoney(Player p, Integer number) {
        if (getBankMaxMoney(p) >= number) {
            Data.getInstance().getbankfile().set("banks." + p.getName() + ".maxmoney", getBankMaxMoney(p) - number);
        } else {
            Data.getInstance().getbankfile().set("banks." + p.getName() + ".maxmoney", 10000);
        }
        Data.getInstance().savebank();
    }

    public void depositBank(Player p, Integer number) {
        if (Jobs.economy.getBalance(p) >= number) {
            addBankMoney(p, number);
            Jobs.economy.withdrawPlayer(p, number);
            p.sendMessage(Data.getInstance().convert(Objects.requireNonNull(Objects.requireNonNull(Data.getInstance().getlanguagefile().getString("DEPOSIT_BANK"))
                    .replaceAll("%money%", String.valueOf(number)))));
        } else {
            p.sendMessage(Data.getInstance().convert(Objects.requireNonNull(Data.getInstance().getlanguagefile().getString("NOT_ENOUGH_MONEY"))));
        }
        Data.getInstance().savebank();
    }

    public void withdrawBank(Player p, Integer number) {
        if (getBankMoney(p) >= number) {
            removeBankMoney(p, number);
            Jobs.economy.depositPlayer(p, number);
            p.sendMessage(Data.getInstance().convert(Objects.requireNonNull(Objects.requireNonNull(Data.getInstance().getlanguagefile().getString("WITHDRAW_BANK"))
                    .replaceAll("%money%", String.valueOf(number)))));
        } else {
            p.sendMessage(Data.getInstance().convert(Objects.requireNonNull(Data.getInstance().getlanguagefile().getString("NOT_ENOUGH_MONEY"))));
        }
        Data.getInstance().savebank();
    }
}
