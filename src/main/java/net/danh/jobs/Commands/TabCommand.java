package net.danh.jobs.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCommand implements TabCompleter {


    List<String> arg = new ArrayList<>();
    List<String> argg = new ArrayList<>();
    List<String> arggg = new ArrayList<>();

    List<String> arg1 = new ArrayList<String>();
    List<String> arg2 = new ArrayList<String>();
    List<String> arg3 = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("jobs")) {
            if (sender.hasPermission("jobs.user")) {
                arg.add("select");
                arg.add("items");
            }
            if (sender.hasPermission("jobs.admin")) {
                arg.add("info");
                arg.add("plevel");
                arg.add("xp");
                arg.add("power");
                arg.add("glevel");
                arg.add("levelup");
                arg.add("maxpower");
                arg.add("restorepower");
                arg.add("update");
            }
            if (args[0].equalsIgnoreCase("select")) {
                arggg.add("Miner");
                arggg.add("WoodCutter");
                arggg.add("Farmer");
                arggg.add("Fisher");
                arggg.add("Doctor");
            }
            if (sender.hasPermission("jobs.admin")) {
                if (args[0].equalsIgnoreCase("plevel")
                        || args[0].equalsIgnoreCase("xp")
                        || args[0].equalsIgnoreCase("power")
                        || args[0].equalsIgnoreCase("glevel")
                        || args[0].equalsIgnoreCase("levelup")
                        || args[0].equalsIgnoreCase("maxpower")
                        || args[0].equalsIgnoreCase("restorepower")) {
                    argg.add("set");
                    argg.add("add");
                    argg.add("remove");
                }
            }
        }
        if (args.length == 1) {
            for (String a1 : arg) {
                if (a1.toLowerCase().startsWith(args[0].toLowerCase()))
                    arg1.add(a1);
            }
            return arg1;
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("plevel")
                    || args[0].equalsIgnoreCase("xp")
                    || args[0].equalsIgnoreCase("power")
                    || args[0].equalsIgnoreCase("glevel")
                    || args[0].equalsIgnoreCase("levelup")
                    || args[0].equalsIgnoreCase("maxpower")
                    || args[0].equalsIgnoreCase("restorepower")) {
                for (String a2 : argg) {
                    if (a2.toLowerCase().startsWith(args[1].toLowerCase()))
                        arg2.add(a2);
                }
                return arg2;
            }
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("select")) {
                for (String a3 : arggg) {
                    if (a3.toLowerCase().startsWith(args[1].toLowerCase()))
                        arg3.add(a3);
                }
                return arg3;
            }
        }
        return null;
    }
}