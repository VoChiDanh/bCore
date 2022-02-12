package net.danh.jobs.Hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.Files;
import org.bukkit.Material;
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

        if (identifier.equalsIgnoreCase("name")) {
            return Files.getInstance().getJobs(p);
        }

        if (identifier.equalsIgnoreCase("power")) {
            return String.valueOf(Files.getInstance().getPower(p));
        }

        if (identifier.equalsIgnoreCase("age")) {
            return String.valueOf(Files.getInstance().getAge(p));
        }

        if (identifier.equalsIgnoreCase("xp")) {
            return String.valueOf(Files.getInstance().getXP(p));
        }
        if (identifier.equalsIgnoreCase("ganglevel")) {
            return String.valueOf(Files.getInstance().getLevelGang(p));
        }
        if (identifier.equalsIgnoreCase("gangpoints")) {
            return String.valueOf(Files.getInstance().getleveluptimes(p));
        }
        if (identifier.equalsIgnoreCase("gang")) {
            return Files.getInstance().getGang(p);
        }
        if (identifier.equalsIgnoreCase("restorepower")) {
            return String.valueOf(Files.getInstance().getRestorePower(p));
        }
        if (identifier.equalsIgnoreCase("maxpower")) {
            return String.valueOf(Files.getInstance().getMaxPower(p));
        }
        if (identifier.equalsIgnoreCase("weight")) {
            return String.valueOf(Files.getInstance().getWeight(p));
        }
        if (identifier.equalsIgnoreCase("maxweight")){
            return String.valueOf(Files.getInstance().getMaxWeight(p));
        }
        if (identifier.startsWith("storage_")){
            String name = identifier.substring(7).toUpperCase();
            if (Material.getMaterial(name) != null) {
                return String.valueOf(Files.getInstance().getStorage(p, name));
            }
            return "Unknow Material";
        }
        return null;
    }
}

