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
            return String.valueOf(Power.getInstance().getPower(p));
        }

        if (identifier.equalsIgnoreCase("level")) {
            return String.valueOf(PlayerData.getInstance().getLevel(p));
        }

        if (identifier.equalsIgnoreCase("xp")) {
            return String.valueOf(PlayerData.getInstance().getXP(p));
        }
        if (identifier.equalsIgnoreCase("ganglevel")) {
            if (Gang.getInstance().getGang(p) != null) {
                return String.valueOf(Gang.getInstance().getLevelGang(p));
            }
            return "";
        }
        if (identifier.equalsIgnoreCase("gangpoints")) {
            if (Gang.getInstance().getGang(p) != null) {
                return String.valueOf(Gang.getInstance().getleveluptimes(p));
            }
            return "";
        }
        if (identifier.equalsIgnoreCase("gang")) {
            if (Gang.getInstance().getGang(p) != null) {
                return Gang.getInstance().getGang(p);
            }
            return "";
        }
        if (identifier.equalsIgnoreCase("restorepower")) {
            return String.valueOf(Power.getInstance().getRestorePower(p));
        }
        if (identifier.equalsIgnoreCase("maxpower")) {
            return String.valueOf(Power.getInstance().getMaxPower(p));
        }
        if (identifier.equalsIgnoreCase("weight")) {
            return String.valueOf(Weight.getInstance().getWeight(p));
        }
        if (identifier.equalsIgnoreCase("maxweight")) {
            return String.valueOf(Weight.getInstance().getMaxWeight(p));
        }
        if (identifier.startsWith("storage_")) {
            String name = identifier.substring(8).toUpperCase();
            return String.valueOf(Storage.getInstance().getStorage(p, name));
        }
        if (identifier.startsWith("price_")) {
            String name = identifier.substring(6).toUpperCase();
            return String.valueOf(Price.getInstance().getPrice(name));
        }
        return null;
    }
}

