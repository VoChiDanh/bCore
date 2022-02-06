package net.danh.jobs.Hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.Files;
import org.bukkit.entity.Player;

public class PlaceholderAPI extends PlaceholderExpansion {


    @Override
    public String getAuthor() {
        return Jobs.getInstance().getDescription().getAuthors().toString();
    }

    @Override
    public String getIdentifier() {
        return "jobs";
    }

    @Override
    public String getVersion() {
        return Jobs.getInstance().getDescription().getVersion();
    }


    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
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
            if (Files.getInstance().getJobs(p).equals("NOTHING")) {
                return "";
            } else {
                return String.valueOf(Files.getInstance().getLevelGang(p));
            }
        }
        if (identifier.equalsIgnoreCase("gangpoints")) {
            if (Files.getInstance().getJobs(p).equals("NOTHING")) {
                return "";
            } else {
                return String.valueOf(Files.getInstance().getleveluptimes(p));
            }
        }
        if (identifier.equalsIgnoreCase("restorepower")) {
            return String.valueOf(Files.getInstance().getRestorePower(p));
        }
        if (identifier.equalsIgnoreCase("maxpower")) {
            return String.valueOf(Files.getInstance().getMaxPower(p));
        }
        return null;
    }
}

