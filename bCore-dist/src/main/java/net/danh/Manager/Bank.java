package net.danh.Manager;

import net.danh.bCore;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Deprecated
public class Bank {


    public static int getBankMoney(@NotNull Player p) {
        return Data.getbankfile().getInt("banks." + p.getName() + ".money");
    }

    public static void setBankMoney(@NotNull Player p, Integer number) {
        Data.getbankfile().set("banks." + p.getName() + ".money", number);
        Data.savebank();
    }

    public static void addBankMoney(Player p, Integer number) {
        if (getBankLevel(p) * Data.getconfigfile().getInt("Level_Bank") < (getBankMoney(p) + number) || getBankLevel(p) * Data.getconfigfile().getInt("Level_Bank") < getBankMoney(p)) {
            p.sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("FULL_BANK"))));
        } else {
            Data.getbankfile().set("banks." + p.getName() + ".money", getBankMoney(p) + number);
            Data.savebank();
        }
    }

    public static void removeBankMoney(Player p, Integer number) {
        if (getBankLevel(p) * Data.getconfigfile().getInt("Level_Bank") >= number) {
            Data.getbankfile().set("banks." + p.getName() + ".money", getBankMoney(p) - number);
        } else {
            Data.getbankfile().set("banks." + p.getName() + ".money", 0);
        }
        Data.savebank();
    }

    @Deprecated
    public static int getBankMaxMoney(@NotNull Player p) {
        return Data.getbankfile().getInt("banks." + p.getName() + ".maxmoney");
    }

    @Deprecated
    public static void setBankMaxMoney(@NotNull Player p, Integer number) {
        Data.getbankfile().set("banks." + p.getName() + ".maxmoney", number);
        Data.savebank();
    }

    @Deprecated
    public static void addBankMaxMoney(@NotNull Player p, Integer number) {
        Data.getbankfile().set("banks." + p.getName() + ".maxmoney", getBankMaxMoney(p) + number);
        Data.savebank();
    }

    @Deprecated
    public static void removeBankMaxMoney(Player p, Integer number) {
        if (getBankMaxMoney(p) >= number) {
            Data.getbankfile().set("banks." + p.getName() + ".maxmoney", getBankMaxMoney(p) - number);
        } else {
            Data.getbankfile().set("banks." + p.getName() + ".maxmoney", 10000);
        }
        Data.savebank();
    }

    public static void depositBank(Player p, Integer number) {
        if (getBankLevel(p) * Data.getconfigfile().getInt("Level_Bank") < (getBankMoney(p) + number) || getBankLevel(p) * Data.getconfigfile().getInt("Level_Bank") < getBankMoney(p)) {
            p.sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("FULL_BANK"))));
        } else {
            if (bCore.economy.getBalance(p) >= number) {
                addBankMoney(p, number);
                bCore.economy.withdrawPlayer(p, number);
                p.sendMessage(Data.convert(Objects.requireNonNull(Objects.requireNonNull(Data.getlanguagefile().getString("DEPOSIT_BANK"))
                        .replaceAll("%money%", String.valueOf(number)))));
            } else {
                p.sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("NOT_ENOUGH_MONEY"))));
            }
            Data.savebank();
        }
    }

    public static void withdrawBank(Player p, Integer number) {
        if (getBankMoney(p) >= number) {
            removeBankMoney(p, number);
            bCore.economy.depositPlayer(p, number);
            p.sendMessage(Data.convert(Objects.requireNonNull(Objects.requireNonNull(Data.getlanguagefile().getString("WITHDRAW_BANK"))
                    .replaceAll("%money%", String.valueOf(number)))));
        } else {
            p.sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("NOT_ENOUGH_MONEY"))));
        }
        Data.savebank();
    }

    public static int getBankLevel(Player p) {
        return Data.getbankfile().getInt("banks." + p.getName() + ".level");
    }

    public static void setBankLevel(Player p, Integer level) {
        Data.getbankfile().set("banks." + p.getName() + ".level", level);
        Data.savebank();
    }

    public static void addBankLevel(Player p, Integer level) {
        Data.getbankfile().set("banks." + p.getName() + ".level", getBankLevel(p) + level);
        Data.savebank();
    }

    public static void removeBankLevel(Player p, Integer level) {
        if (getBankLevel(p) > level) {
            Data.getbankfile().set("banks." + p.getName() + ".level", getBankLevel(p) - level);
        } else {
            Data.getbankfile().set("banks." + p.getName() + ".level", 1);
        }
        Data.savebank();
    }
}
