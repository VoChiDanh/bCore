package net.danh.Commands;

import net.danh.Manager.Data;
import net.danh.Manager.Points;
import net.danh.Manager.Status;
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

import static net.danh.dcore.Utils.Player.sendPlayerMessage;

public class PvP extends CMDBase {

    public PvP(JavaPlugin core) {
        super(core, "pvp");
    }

    @Override
    public void playerexecute(Player p, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("off")) {
                if (Points.getPvPPoints(p) >= Data.getconfigfile().getInt("Req_Log_To_PvP")) {
                    Points.removePvPPoints(p, Data.getconfigfile().getInt("Req_Log_To_PvP"));
                    Status.TogglePvP(p, false);
                    sendPlayerMessage(p, Objects.requireNonNull(Data.getlanguagefile().getString("PVP.TOGGLE_MESSAGE"))
                            .replaceAll("%status%", Objects.requireNonNull(Data.getconfigfile().getString("PVP.STATUS_OFF"))));
                } else {
                    sendPlayerMessage(p, Objects.requireNonNull(Data.getlanguagefile().getString("DO_NOT_ENOUGH_LOG"))
                            .replaceAll("%amount%", String.valueOf(Data.getconfigfile().getInt("Req_Log_To_PvP"))));
                }
            } else if (args[0].equalsIgnoreCase("on")) {
                Status.TogglePvP(p, true);
                sendPlayerMessage(p, Objects.requireNonNull(Data.getlanguagefile().getString("PVP.TOGGLE_MESSAGE"))
                        .replaceAll("%status%", Objects.requireNonNull(Data.getconfigfile().getString("PVP.STATUS_ON"))));
            }
        }
    }

    @Override
    public void consoleexecute(ConsoleCommandSender sender, String[] args) {
        if (args.length == 2) {
            Player p = Bukkit.getPlayer(args[1]);
            if (p == null) return;
            if (args[0].equalsIgnoreCase("off")) {
                if (Points.getPvPPoints(p) >= Data.getconfigfile().getInt("Req_Log_To_PvP")) {
                    Points.removePvPPoints(p, Data.getconfigfile().getInt("Req_Log_To_PvP"));
                    Status.TogglePvP(p, false);
                    sendPlayerMessage(p, Objects.requireNonNull(Data.getlanguagefile().getString("PVP.TOGGLE_MESSAGE"))
                            .replaceAll("%status%", Objects.requireNonNull(Data.getconfigfile().getString("PVP.STATUS_OFF"))));
                } else {
                    sendPlayerMessage(p, Objects.requireNonNull(Data.getlanguagefile().getString("DO_NOT_ENOUGH_LOG"))
                            .replaceAll("%amount%", String.valueOf(Data.getconfigfile().getInt("Req_Log_To_PvP"))));
                }
            } else if (args[0].equalsIgnoreCase("on")) {
                Status.TogglePvP(p, true);
                sendPlayerMessage(p, Objects.requireNonNull(Data.getlanguagefile().getString("PVP.TOGGLE_MESSAGE"))
                        .replaceAll("%status%", Objects.requireNonNull(Data.getconfigfile().getString("PVP.STATUS_ON"))));
            }
        }
    }

    @Override
    public List<String> TabComplete(CommandSender sender, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        if (args.length == 1) {
            commands.add("on");
            commands.add("off");
            StringUtil.copyPartialMatches(args[0], commands, completions);
        }
        Collections.sort(completions);
        return completions;
    }
}
