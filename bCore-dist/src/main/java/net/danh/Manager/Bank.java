package net.danh.Manager;

import net.danh.bCore;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;

import static net.danh.dcore.Utils.Player.sendPlayerMessage;

public class Bank {

    public static HashMap<Player, Integer> bank = new HashMap<>();
    public static HashMap<Player, Integer> banklevel = new HashMap<>();

    /*
     Data.getbankfile().getInt("banks." + p.getName() + ".money");
     */
    public static int getBankMoney(@NotNull Player p) {
        return bank.get(p);
    }

    public static int getBMData(@NotNull Player p) {
        return Data.getbankfile().getInt("banks." + p.getName() + ".money");
    }


    public static void setBankMoney(@NotNull Player p, Integer number) {
        bank.put(p, number);
    }

    public static void addBankMoney(Player p, Integer number) {
        bank.replace(p, getBankMoney(p) + number);
    }

    public static void removeBankMoney(Player p, Integer number) {
        bank.replace(p, getBankMoney(p) - number);
    }

    public static void depositBank(Player p, Integer number) {
        if (getBankLevel(p) * Data.getconfigfile().getInt("Level_Bank") < (getBankMoney(p) + number)) {
            sendPlayerMessage(p, Data.getlanguagefile().getString("FULL_BANK"));
        } else {
            if (bCore.economy.getBalance(p) >= number) {
                addBankMoney(p, number);
                bCore.economy.withdrawPlayer(p, number);
                sendPlayerMessage(p, Objects.requireNonNull(Data.getlanguagefile().getString("DEPOSIT_BANK")).replaceAll("%money%", String.valueOf(number)));
            } else {
                sendPlayerMessage(p, Data.getlanguagefile().getString("NOT_ENOUGH_MONEY"));
            }
        }
    }

    public static void withdrawBank(Player p, Integer number) {
        if (getBankMoney(p) >= number) {
            removeBankMoney(p, number);
            bCore.economy.depositPlayer(p, number);
            sendPlayerMessage(p, Objects.requireNonNull(Data.getlanguagefile().getString("WITHDRAW_BANK"))
                    .replaceAll("%money%", String.valueOf(number)));
        } else {
            sendPlayerMessage(p, Data.getlanguagefile().getString("NOT_ENOUGH_MONEY"));
        }
    }

    public static int getBLData(@NotNull Player p) {
        return Data.getbankfile().getInt("banks." + p.getName() + ".level");
    }

    public static int getBankLevel(@NotNull Player p) {
        return banklevel.get(p);
    }


    public static void setBankLevel(@NotNull Player p, Integer level) {
        banklevel.put(p, level);
    }

    public static void addBankLevel(@NotNull Player p, Integer level) {
        banklevel.replace(p, getBankLevel(p) + level);
    }

    public static void removeBankLevel(Player p, Integer level) {
        if (getBankLevel(p) > level) {
            banklevel.replace(p, getBankLevel(p) - level);
        } else {
            banklevel.replace(p, 1);
        }
    }
}
