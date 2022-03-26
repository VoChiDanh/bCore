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
        if (identifier.equalsIgnoreCase("leveluptimes")) {
            return String.valueOf(PlayerData.getleveluptimes(p));
        }
        if (identifier.equalsIgnoreCase("restorepower")) {
            return String.valueOf(Power.getRestorePower(p));
        }
        if (identifier.equalsIgnoreCase("maxpower")) {
            return String.valueOf(Power.getMaxPower(p));
        }
        if (identifier.equalsIgnoreCase("weight")) {
            return String.valueOf(Weight.getWeight(p));
        }
        if (identifier.equalsIgnoreCase("maxweight")) {
            return String.valueOf(Weight.getMaxWeight(p));
        }
        if (identifier.equalsIgnoreCase("luckychance")) {
            return String.valueOf(Lucky.getLuckyChance(p));
        }
        if (identifier.startsWith("storage_")) {
            String name = identifier.substring(8).toUpperCase();
            return String.valueOf(Storage.getStorage(p, name));
        }
        if (identifier.startsWith("price_")) {
            String name = identifier.substring(6).toUpperCase();
            return String.valueOf(Items.getPrice(name));
        }
        if (identifier.equalsIgnoreCase("bank_")) {
            String name = identifier.substring(5).toLowerCase();
            if (name.equalsIgnoreCase("money")) {
                return String.valueOf(Bank.getBankMoney(p));
            }
            if (name.equalsIgnoreCase("max_money")) {
                return String.valueOf(Bank.getBankLevel(p) * Data.getconfigfile().getInt("Level_Bank"));
            }
            return "";
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
        return null;
    }
}

