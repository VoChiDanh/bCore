package net.danh.jobs.Hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPI extends PlaceholderExpansion {


    @Override
    public @NotNull String getAuthor() {
        return Jobs.getInstance().getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getIdentifier() {
        return "jobs";
    }

    @Override
    public @NotNull String getVersion() {
        return Jobs.getInstance().getDescription().getVersion();
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
            return String.valueOf(Price.getPrice(name));
        }
        if (identifier.equalsIgnoreCase("bank_")) {
            String name = identifier.substring(5).toLowerCase();
            if (name.equalsIgnoreCase("money")) {
                return String.valueOf(Bank.getBankMoney(p));
            }
            if (name.equalsIgnoreCase("max_money")) {
                return String.valueOf(Bank.getBankMaxMoney(p));
            }
            return "";
        }
        return null;
    }
}

