package net.danh.Commands;

import net.danh.Manager.Data;
import net.danh.dcore.Commands.CMDBase;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static net.danh.dcore.Utils.Player.sendConsoleMessage;
import static net.danh.dcore.Utils.Player.sendPlayerMessage;

public class Bank extends CMDBase {
    public Bank(JavaPlugin core) {
        super(core, "Bank");
    }

    @Override
    public void playerexecute(Player p, String[] args) {
        if (args.length == 0) {
            sendPlayerMessage(p, Objects.requireNonNull(Objects.requireNonNull(Data.getlanguagefile().getString("BANK"))
                    .replaceAll("%money%", String.valueOf(net.danh.Manager.Bank.getBankMoney(Objects.requireNonNull(p))))
                    .replaceAll("%level%", String.valueOf(net.danh.Manager.Bank.getBankLevel(p)))));
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("deposit")) {
                if (Integer.parseInt(args[1]) > 0) {
                    net.danh.Manager.Bank.depositBank(p, Integer.parseInt(args[1]));
                }
            }
            if (args[0].equalsIgnoreCase("withdraw")) {
                if (Integer.parseInt(args[1]) > 0) {
                    net.danh.Manager.Bank.withdrawBank(p, Integer.parseInt(args[1]));
                }
            }
        }
    }

    @Override
    public void consoleexecute(ConsoleCommandSender c, String[] args) {
        if (args.length == 1) {
            Player p = Bukkit.getPlayer(args[0]);
            if (p == null) {
                sendConsoleMessage(c, Data.getlanguagefile().getString("UNKNOWN_PLAYER"));
                return;
            }
            sendConsoleMessage(c, Objects.requireNonNull(Objects.requireNonNull(Data.getlanguagefile().getString("BANK"))
                    .replaceAll("%money%", String.valueOf(net.danh.Manager.Bank.getBankMoney(Objects.requireNonNull(p))))
                    .replaceAll("%level%", String.valueOf(net.danh.Manager.Bank.getBankLevel(p)))));
        }
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        if (args.length == 1) {
            commands.add("deposit");
            commands.add("withdraw");
            StringUtil.copyPartialMatches(args[0], commands, completions);
        }
        if (sender instanceof ConsoleCommandSender) {
            if (args.length == 1) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    commands.add(p.getName());
                    StringUtil.copyPartialMatches(args[0], commands, completions);
                }
            }
        }
        Collections.sort(completions);
        return completions;
    }
}
