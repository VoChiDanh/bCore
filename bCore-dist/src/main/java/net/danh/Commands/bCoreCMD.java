package net.danh.Commands;

import net.danh.Contest.Mining;
import net.danh.Manager.Data;
import net.danh.Manager.Status;
import net.danh.bCore;
import net.danh.dcore.Commands.CMDBase;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static net.danh.Manager.Bank.*;
import static net.danh.Manager.Credit.*;
import static net.danh.Manager.Data.getInt;
import static net.danh.Manager.Data.getlanguagefile;
import static net.danh.Manager.Event.*;
import static net.danh.Manager.PlayerData.*;
import static net.danh.Manager.Points.*;
import static net.danh.Manager.Power.*;
import static net.danh.Manager.QPoints.*;
import static net.danh.dcore.Utils.Player.sendConsoleMessage;
import static net.danh.dcore.Utils.Player.sendPlayerMessage;

public class bCoreCMD extends CMDBase {

    public bCoreCMD(JavaPlugin core) {
        super(core, "bCore");
    }

    @Override
    public void playerexecute(Player p, String[] args) {
        String RELOADED = Data.getlanguagefile().getString("RELOAD");
        String PREFIX = Data.getlanguagefile().getString("PREFIX");
        String UNKNOWN_PLAYER = Data.getlanguagefile().getString("UNKNOWN_PLAYER");
        if (args.length == 0) {
            sendPlayerMessage(p, getlanguagefile().getStringList("HELP_USER"));
            if (p.hasPermission("bCore.admin")) {
                sendPlayerMessage(p, getlanguagefile().getStringList("HELP_ADMIN"));
            }
        }
        if (args.length == 1) {
            if (p.hasPermission("bCore.admin")) {
                if (args[0].equalsIgnoreCase("reload")) {
                    Data.reloadfiles();
                    sendPlayerMessage(p, PREFIX + RELOADED);
                }
                if (args[0].equalsIgnoreCase("start")) {
                    if (bCore.getInstance().getServer().getOnlinePlayers().size() > 0) {
                        Mining.StartMining();
                    }
                }
            }
        }
        if (args.length == 4) {
            Player t = Bukkit.getPlayer(args[2]);
            String msg = ChatColor.stripColor(args[3]);
            int amount = getInt(msg);
            if (t == null) {
                sendPlayerMessage(p, PREFIX + UNKNOWN_PLAYER);
                return;
            }
            if (p.hasPermission("bCore.admin")) {
                if (args[0].equalsIgnoreCase("level")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setLevel(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addLevel(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeLevel(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("xp")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setXP(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addXP(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeXP(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("credit")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setCredit(t, (double) amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addCredit(t, (double) amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeCredit(t, (double) amount);
                    }
                }
                if (args[0].equalsIgnoreCase("power")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setPower(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addPower(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removePower(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("levelup")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setLeveluptimes(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addLeveluptimes(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeLeveluptimes(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("maxpower")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setMaxPower(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addMaxPower(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeMaxPower(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("restorepower")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setRestorePower(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addRestorePower(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeRestorePower(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("bankmoney")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setBankMoney(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addBankMoney(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeBankMoney(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("banklevel")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setBankLevel(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addBankLevel(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeBankLevel(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("blocksevent")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setBlocksEvent(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addBlocksEvent(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeBlocksEvent(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("pvpp")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setPvPPoints(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addPvPPoints(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removePvPPoints(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("blocks")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setBlocks(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addBlocks(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeBlocks(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("qp")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setQP(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addQP(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeQP(t, amount);
                    }
                }
            }
        }
        if (args.length == 3) {
            if (p.hasPermission("bCore.admin")) {
                if (args[0].equalsIgnoreCase("status")) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        Status.TogglePvP(Objects.requireNonNull(Bukkit.getPlayer(args[1])), Boolean.getBoolean(args[2]));
                        if (Status.getPvPStatus(Objects.requireNonNull(Bukkit.getPlayer(args[1])))) {
                            sendPlayerMessage(Bukkit.getPlayer(args[1]), Objects.requireNonNull(Data.getlanguagefile().getString("PVP.TOGGLE_MESSAGE" + " &7(" + Objects.requireNonNull(Bukkit.getPlayer(args[1])).getName() + ")"))
                                    .replaceAll("%status%", Objects.requireNonNull(Data.getconfigfile().getString("PVP.STATUS_ON"))));
                        } else {
                            sendPlayerMessage(Bukkit.getPlayer(args[1]), Objects.requireNonNull(Data.getlanguagefile().getString("PVP.TOGGLE_MESSAGE" + " &7(" + Objects.requireNonNull(Bukkit.getPlayer(args[1])).getName() + ")"))
                                    .replaceAll("%status%", Objects.requireNonNull(Data.getconfigfile().getString("PVP.STATUS_OFF"))));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void consoleexecute(ConsoleCommandSender p, String[] args) {
        String RELOADED = Data.getlanguagefile().getString("RELOAD");
        String PREFIX = Data.getlanguagefile().getString("PREFIX");
        String UNKNOWN_PLAYER = Data.getlanguagefile().getString("UNKNOWN_PLAYER");
        if (args.length == 0) {
            sendConsoleMessage(p, getlanguagefile().getStringList("HELP_USER"));
            if (p.hasPermission("bCore.admin")) {
                sendConsoleMessage(p, getlanguagefile().getStringList("HELP_ADMIN"));
            }
        }
        if (args.length == 1) {
            if (p.hasPermission("bCore.admin")) {
                if (args[0].equalsIgnoreCase("reload")) {
                    Data.reloadfiles();
                    sendConsoleMessage(p, PREFIX + RELOADED);
                }
                if (args[0].equalsIgnoreCase("start")) {
                    if (bCore.getInstance().getServer().getOnlinePlayers().size() > 0) {
                        Mining.StartMining();
                    }
                }
            }
        }
        if (args.length == 4) {
            Player t = Bukkit.getPlayer(args[2]);
            String msg = ChatColor.stripColor(args[3]);
            int amount = getInt(msg);
            if (t == null) {
                sendConsoleMessage(p, PREFIX + UNKNOWN_PLAYER);
                return;
            }
            if (p.hasPermission("bCore.admin")) {
                if (args[0].equalsIgnoreCase("level")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setLevel(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addLevel(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeLevel(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("xp")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setXP(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addXP(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeXP(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("credit")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setCredit(t, (double) amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addCredit(t, (double) amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeCredit(t, (double) amount);
                    }
                }
                if (args[0].equalsIgnoreCase("power")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setPower(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addPower(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removePower(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("levelup")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setLeveluptimes(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addLeveluptimes(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeLeveluptimes(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("maxpower")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setMaxPower(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addMaxPower(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeMaxPower(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("restorepower")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setRestorePower(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addRestorePower(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeRestorePower(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("bankmoney")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setBankMoney(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addBankMoney(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeBankMoney(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("banklevel")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setBankLevel(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addBankLevel(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeBankLevel(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("blocksevent")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setBlocksEvent(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addBlocksEvent(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeBlocksEvent(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("pvpp")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setPvPPoints(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addPvPPoints(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removePvPPoints(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("blocks")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setBlocks(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addBlocks(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeBlocks(t, amount);
                    }
                }
                if (args[0].equalsIgnoreCase("qp")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        setQP(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        addQP(t, amount);
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        removeQP(t, amount);
                    }
                }
            }
        }
        if (args.length == 3) {
            if (p.hasPermission("bCore.admin")) {
                if (args[0].equalsIgnoreCase("status")) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        Status.TogglePvP(Objects.requireNonNull(Bukkit.getPlayer(args[1])), Boolean.getBoolean(args[2]));
                        if (Status.getPvPStatus(Objects.requireNonNull(Bukkit.getPlayer(args[1])))) {
                            sendPlayerMessage(Bukkit.getPlayer(args[1]), Objects.requireNonNull(Data.getlanguagefile().getString("PVP.TOGGLE_MESSAGE" + " &7(" + Objects.requireNonNull(Bukkit.getPlayer(args[1])).getName() + ")"))
                                    .replaceAll("%status%", Objects.requireNonNull(Data.getconfigfile().getString("PVP.STATUS_ON"))));
                        } else {
                            sendPlayerMessage(Bukkit.getPlayer(args[1]), Objects.requireNonNull(Data.getlanguagefile().getString("PVP.TOGGLE_MESSAGE" + " &7(" + Objects.requireNonNull(Bukkit.getPlayer(args[1])).getName() + ")"))
                                    .replaceAll("%status%", Objects.requireNonNull(Data.getconfigfile().getString("PVP.STATUS_OFF"))));
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        if (sender.hasPermission("bCore.admin")) {
            if (args.length == 1) {
                commands.add("reload");
                commands.add("start");
                commands.add("level");
                commands.add("xp");
                commands.add("credit");
                commands.add("power");
                commands.add("levelup");
                commands.add("maxpower");
                commands.add("restorepower");
                commands.add("bankmoney");
                commands.add("banklevel");
                commands.add("blocksevent");
                commands.add("pvpp");
                commands.add("blocks");
                commands.add("qp");
                commands.add("status");
                StringUtil.copyPartialMatches(args[0], commands, completions);
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("level")
                        || args[0].equalsIgnoreCase("xp")
                        || args[0].equalsIgnoreCase("credit")
                        || args[0].equalsIgnoreCase("power")
                        || args[0].equalsIgnoreCase("levelup")
                        || args[0].equalsIgnoreCase("maxpower")
                        || args[0].equalsIgnoreCase("restorepower")
                        || args[0].equalsIgnoreCase("bankmoney")
                        || args[0].equalsIgnoreCase("banklevel")
                        || args[0].equalsIgnoreCase("pvpp")
                        || args[0].equalsIgnoreCase("blocks")
                        || args[0].equalsIgnoreCase("qp")) {
                    commands.add("add");
                    commands.add("set");
                    commands.add("remove");
                    StringUtil.copyPartialMatches(args[1], commands, completions);
                }
            }
        }
        Collections.sort(completions);
        return completions;
    }
}
