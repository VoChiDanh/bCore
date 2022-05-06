package net.danh.Hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.danh.Manager.*;
import net.danh.bCore;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPI extends PlaceholderExpansion {


    @Override
    public @NotNull String getAuthor() {
        return bCore.getInstance().getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getIdentifier() {
        return "bCore";
    }

    @Override
    public @NotNull String getVersion() {
        return bCore.getInstance().getDescription().getVersion();
    }


    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player p, @NotNull String identifier) {
        if (p == null) {
            return "Player not online";
        }
        if (identifier.equalsIgnoreCase("power")) {
            return String.valueOf(Power.getPower(p));
        }

        if (identifier.equalsIgnoreCase("level")) {
            return String.valueOf(PlayerData.getLevel(p));
        }

        if (identifier.equalsIgnoreCase("xp")) {
            return String.valueOf(PlayerData.getXP(p));
        }
        if (identifier.equalsIgnoreCase("qp")) {
            return String.valueOf(QPoints.getQP(p));
        }
        if (identifier.equalsIgnoreCase("leveluptimes")) {
            return String.valueOf(PlayerData.getleveluptimes(p));
        }
        if (identifier.equalsIgnoreCase("restorepower")) {
            return String.valueOf(Power.getRestorePower(p));
        }
        if (identifier.equalsIgnoreCase("maxpower")) {
            return String.valueOf(Power.getMaxPower(p));
        }
        if (identifier.equalsIgnoreCase("status")) {
            if (Status.getPvPStatus(p)) {
                return Data.getconfigfile().getString("Boolean.true");
            } else {
                return Data.getconfigfile().getString("Boolean.false");
            }
        }
        if (identifier.equalsIgnoreCase("pvp_points")) {
            return String.valueOf(Points.getPvPPoints(p));
        }
        if (identifier.equalsIgnoreCase("bitcoin")) {
            return String.valueOf(Bitcoin.getBitCoin(p));
        }
        if (identifier.equalsIgnoreCase("bitcoin_max")) {
            return String.valueOf(Bitcoin.getBitCoinMax());
        }
        if (identifier.equalsIgnoreCase("bitcoin_buy")) {
            return String.format("%,d", Bitcoin.getBitCoinBuyPrice()) + "$";
        }
        if (identifier.equalsIgnoreCase("bitcoin_sell")) {
            return String.format("%,d", Bitcoin.getBitCoinSellPrice()) + "$";
        }
        if (identifier.equalsIgnoreCase("bank_money")) {
            return String.valueOf(Bank.getBankMoney(p));
        }
        if (identifier.equalsIgnoreCase("bank_level")) {
            return String.valueOf(Bank.getBankLevel(p));
        }
        if (identifier.equalsIgnoreCase("bank_max_money")) {
            return String.valueOf(Bank.getBankLevel(p) * Data.getconfigfile().getInt("Level_Bank"));
        }
        if (identifier.equalsIgnoreCase("blocks_event")) {
            return String.valueOf(Event.getBlockEvent(p));
        }
        if (identifier.equalsIgnoreCase("blocks")) {
            return String.valueOf(PlayerData.getBlocks(p));
        }
        if (identifier.equalsIgnoreCase("icredit")) {
            return String.valueOf(Credit.getIntCredit(p));
        }
        if (identifier.equalsIgnoreCase("credit")) {
            if (Credit.getCredit(p) <= 200D && Credit.getCredit(p) >= 100D) {
                return "&a" + Credit.getRealCredit(p);
            }
            if (Credit.getCredit(p) < 100D && Credit.getCredit(p) >= 0D) {
                return "&c" + Credit.getRealCredit(p);
            }
            return "&e" + Credit.getRealCredit(p);
        }
        if (identifier.equalsIgnoreCase("version")) {
            return getVersion();
        }
        return null;
    }
}

