package net.danh.bCore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TabCommand implements TabCompleter {


    List<String> arg = new ArrayList<>();
    List<String> argg = new ArrayList<>();
    List<String> arggg = new ArrayList<>();
    List<String> argggg = new ArrayList<>();
    List<String> gang = new ArrayList<>();

    List<String> arg1 = new ArrayList<>();
    List<String> arg2 = new ArrayList<>();
    List<String> arg3 = new ArrayList<>();
    List<String> gang1 = new ArrayList<>();

    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("bank")) {
            arggg.add("deposit");
            arggg.add("withdraw");
        }
        if (label.equalsIgnoreCase("bCore")) {
            if (sender.hasPermission("bCore.admin")) {
                arg.add("info");
                arg.add("plevel");
                arg.add("xp");
                arg.add("power");
                arg.add("glevel");
                arg.add("levelup");
                arg.add("maxpower");
                arg.add("restorepower");
                arg.add("luckychance");
                arg.add("weight");
                arg.add("maxweight");
                arg.add("storage");
                arg.add("luckychance");
                arg.add("update");
                arg.add("price");
                arg.add("baseprice");
                arg.add("bankmoney");
                arg.add("bankmaxmoney");
                arg.add("blocks");
                arg.add("kills");
            }
            if (sender.hasPermission("bCore.admin")) {
                if (args[0].equalsIgnoreCase("plevel")
                        || args[0].equalsIgnoreCase("xp")
                        || args[0].equalsIgnoreCase("power")
                        || args[0].equalsIgnoreCase("glevel")
                        || args[0].equalsIgnoreCase("levelup")
                        || args[0].equalsIgnoreCase("maxpower")
                        || args[0].equalsIgnoreCase("restorepower")
                        || args[0].equalsIgnoreCase("luckychance")
                        || args[0].equalsIgnoreCase("weight")
                        || args[0].equalsIgnoreCase("maxweight")
                        || args[0].equalsIgnoreCase("price")
                        || args[0].equalsIgnoreCase("baseprice")
                        || args[0].equalsIgnoreCase("bankmoney")
                        || args[0].equalsIgnoreCase("bankmaxmoney")
                        || args[0].equalsIgnoreCase("blocks")
                        || args[0].equalsIgnoreCase("kills")) {
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
        if (args.length == 1) {
            for (String a3 : argggg) {
                if (a3.toLowerCase().startsWith(args[0].toLowerCase()))
                    arg3.add(a3);
            }
            return arg3;
        }
        if (args.length == 1) {
            for (String gang : gang) {
                if (gang.toLowerCase().startsWith(args[0].toLowerCase()))
                    gang1.add(gang);
            }
            return gang1;
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("plevel")
                    || args[0].equalsIgnoreCase("xp")
                    || args[0].equalsIgnoreCase("power")
                    || args[0].equalsIgnoreCase("glevel")
                    || args[0].equalsIgnoreCase("levelup")
                    || args[0].equalsIgnoreCase("maxpower")
                    || args[0].equalsIgnoreCase("restorepower")
                    || args[0].equalsIgnoreCase("luckychance")
                    || args[0].equalsIgnoreCase("weight")
                    || args[0].equalsIgnoreCase("maxweight")
                    || args[0].equalsIgnoreCase("price")
                    || args[0].equalsIgnoreCase("baseprice")
                    || args[0].equalsIgnoreCase("bankmoney")
                    || args[0].equalsIgnoreCase("bankmaxmoney")
                    || args[0].equalsIgnoreCase("blocks")
                    || args[0].equalsIgnoreCase("kills")) {
                for (String a2 : argg) {
                    if (a2.toLowerCase().startsWith(args[1].toLowerCase()))
                        arg2.add(a2);
                }
                return arg2;
            }
        }
        return null;
    }
}