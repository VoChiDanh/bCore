package net.danh.Commands;

import net.danh.Contest.Mining.Mining;
import net.danh.Manager.*;
import net.danh.bCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Commands implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        String RELOADED = Data.getlanguagefile().getString("RELOAD");
        String PREFIX = Data.getlanguagefile().getString("PREFIX");
        String UNKNOWN_PLAYER = Data.getlanguagefile().getString("UNKNOWN_PLAYER");
        if (label.equalsIgnoreCase("pvp")) {
            if (sender instanceof Player) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("off")) {
                        if (Points.getPvPPoints(Objects.requireNonNull(((Player) sender).getPlayer())) >= Data.getconfigfile().getInt("Req_Log_To_PvP")) {
                            Points.removePvPPoints(((Player) sender).getPlayer(), Data.getconfigfile().getInt("Req_Log_To_PvP"));
                            Status.TogglePvP(Objects.requireNonNull(((Player) sender).getPlayer()), false);
                            sender.sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("PVP.TOGGLE_MESSAGE"))
                                    .replaceAll("%status%", Objects.requireNonNull(Data.getconfigfile().getString("PVP.STATUS_OFF")))));
                        } else {
                            sender.sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("DO_NOT_ENOUGH_LOG"))
                                    .replaceAll("%amount%", String.valueOf(Data.getconfigfile().getInt("Req_Log_To_PvP")))));
                        }
                    } else {
                        if (args[0].equalsIgnoreCase("on")) {
                            Status.TogglePvP(Objects.requireNonNull(((Player) sender).getPlayer()), true);
                            sender.sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("PVP.TOGGLE_MESSAGE"))
                                    .replaceAll("%status%", Objects.requireNonNull(Data.getconfigfile().getString("PVP.STATUS_ON")))));
                            return true;
                        }
                    }
                }
            }
            return true;
        }
        if (label.equalsIgnoreCase("bCore")) {
            if (args.length == 0) {
                if (sender.hasPermission("bCore.user")) {
                    for (String user : Data.getlanguagefile().getStringList("HELP_USER")) {
                        sender.sendMessage(Data.convert(user));
                    }
                }
                if (sender.hasPermission("bCore.admin")) {
                    for (String admin : Data.getlanguagefile().getStringList("HELP_ADMIN")) {
                        sender.sendMessage(Data.convert(admin));
                    }
                }
            }
            if (args.length == 1) {
                if (sender.hasPermission("bCore.admin")) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        Data.reloadfiles();
                        sender.sendMessage(Data.convert(PREFIX + RELOADED));
                    }
                    if (args[0].equalsIgnoreCase("start")) {
                        if (bCore.getInstance().getServer().getOnlinePlayers().size() > 0) {
                            Mining.StartMining();
                        }
                    }
                }
            }
            if (args.length == 4) {
                if (sender.hasPermission("bCore.admin")) {
                    if (args[0].equalsIgnoreCase("level")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.setLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.addLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.removeLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("xp")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.setXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.addXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.removeXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("credit")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Credit.setCredit(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Double.parseDouble(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Credit.addCredit(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Double.parseDouble(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Credit.removeCredit(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Double.parseDouble(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("power")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.setPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Integer.parseInt(args[3]) > 0) {
                                if (Bukkit.getPlayer(args[2]) != null) {
                                    Power.addPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                } else {
                                    sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                                }
                            } else {
                                if (Integer.parseInt(args[3]) == 0) {
                                    Power.setPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Power.getMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2]))));
                                }
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.removePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("levelup")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.setLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.addLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.removeLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("maxpower")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.setMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.addMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.removeMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("restorepower")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.setRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.addRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.removeRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("luckychance")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Lucky.setLuckyChance(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Lucky.addLuckyChance(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Lucky.removeLuckyChance(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("bankmoney")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.setBankMoney(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.addBankMoney(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.removeBankMoney(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("banklevel")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.setBankLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.addBankLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.removeBankLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("blocks-event")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Event.setBlocksEvent(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Event.addBlocksEvent(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Event.removeBlocksEvent(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("pvpp")) {
                        if (Bukkit.getPlayer(args[2]) != null) {
                            if (args[1].equalsIgnoreCase("set")) {
                                Points.setPvPPoints(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                Objects.requireNonNull(Bukkit.getPlayer(args[2])).sendMessage("&a+ " + args[3] + " Điểm PvP");

                            }
                            if (args[1].equalsIgnoreCase("add")) {
                                Points.addPvPPoints(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                Objects.requireNonNull(Bukkit.getPlayer(args[2])).sendMessage("&a+ " + args[3] + " Điểm PvP");

                            }
                            if (args[1].equalsIgnoreCase("remove")) {
                                Points.removePvPPoints(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                Objects.requireNonNull(Bukkit.getPlayer(args[2])).sendMessage("&c- " + args[3] + " Điểm PvP");

                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("bitcoin")) {
                        if (Bukkit.getPlayer(args[2]) != null) {
                            if (args[1].equalsIgnoreCase("set")) {
                                Bitcoin.setBitCoin(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            }
                            if (args[1].equalsIgnoreCase("add")) {
                                Bitcoin.addBitCoin(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            }
                            if (args[1].equalsIgnoreCase("remove")) {
                                Bitcoin.removeBitCoin(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("bitcoinmax")) {
                        if (Bukkit.getPlayer(args[2]) != null) {
                            if (args[1].equalsIgnoreCase("set")) {
                                Bitcoin.setBitCoinMax(Integer.parseInt(args[3]));
                            }
                            if (args[1].equalsIgnoreCase("add")) {
                                Bitcoin.addBitCoinMax(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            }
                            if (args[1].equalsIgnoreCase("remove")) {
                                Bitcoin.removeBitCoinMax(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("blocks")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.setBlocks(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.addBlocks(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.removeBlocks(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("qp")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                QPoints.setQP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                QPoints.addQP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                QPoints.removeQP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                }
            }
            if (sender.hasPermission("bCore.admin")) {
                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("status")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Status.TogglePvP(Objects.requireNonNull(Bukkit.getPlayer(args[1])), Boolean.getBoolean(args[2]));
                            if (Status.getPvPStatus(Objects.requireNonNull(Bukkit.getPlayer(args[1])))) {
                                Objects.requireNonNull(Bukkit.getPlayer(args[1])).sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("PVP.TOGGLE_MESSAGE" + " &7(" + Objects.requireNonNull(Bukkit.getPlayer(args[1])).getName() + ")"))
                                        .replaceAll("%status%", Objects.requireNonNull(Data.getconfigfile().getString("PVP.STATUS_ON")))));
                            } else {
                                Objects.requireNonNull(Bukkit.getPlayer(args[1])).sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("PVP.TOGGLE_MESSAGE" + " &7(" + Objects.requireNonNull(Bukkit.getPlayer(args[1])).getName() + ")"))
                                        .replaceAll("%status%", Objects.requireNonNull(Data.getconfigfile().getString("PVP.STATUS_OFF")))));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("bitcoinprice")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            Bitcoin.setBitCoinBuyPrice(Integer.parseInt(args[2]));
                            Bitcoin.setBitCoinSellPrice(Integer.parseInt(args[2]));
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            Bitcoin.IncreasePrice(Integer.parseInt(args[2]));
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            Bitcoin.DecreasePrice(Integer.parseInt(args[2]));
                        }
                    }
                }
                return true;
            }
            return true;
        }
        if (label.equalsIgnoreCase("bank")) {
            if (sender instanceof Player) {
                if (args.length == 0) {
                    sender.sendMessage(Data.convert(Objects.requireNonNull(Objects.requireNonNull(Data.getlanguagefile().getString("BANK"))
                            .replaceAll("%money%", String.valueOf(Bank.getBankMoney(Objects.requireNonNull(((Player) sender).getPlayer()))))
                            .replaceAll("%level%", String.valueOf(Bank.getBankLevel(((Player) sender).getPlayer()))))));
                }
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("deposit")) {
                        if (Integer.parseInt(args[1]) > 0) {
                            Bank.depositBank(((Player) sender).getPlayer(), Integer.parseInt(args[1]));
                        }
                    }
                    if (args[0].equalsIgnoreCase("withdraw")) {
                        if (Integer.parseInt(args[1]) > 0) {
                            Bank.withdrawBank(((Player) sender).getPlayer(), Integer.parseInt(args[1]));
                        }
                    }
                }
            }
            return true;
        }
        if (label.equalsIgnoreCase("bitcoin")) {
            if (sender instanceof ConsoleCommandSender) {
                if (args.length == 0) {
                    sender.sendMessage(Data.convert("&6Giá của mua 1 BitCoin hiện tại là " + String.format("%,d", Bitcoin.getBitCoinBuyPrice()) + "$"));
                    sender.sendMessage(Data.convert("&6Giá của bán 1 BitCoin hiện tại là " + String.format("%,d", Bitcoin.getBitCoinSellPrice()) + "$"));
                }
            }
            if (sender instanceof Player) {
                if (args.length == 0) {
                    sender.sendMessage(Data.convert("&aBạn có " + Bitcoin.getBitCoin(Objects.requireNonNull(((Player) sender).getPlayer())) + "/" + Bitcoin.getBitCoinMax() + " BitCoins"));
                    sender.sendMessage(Data.convert("&6Giá của mua 1 BitCoin hiện tại là " + String.format("%,d", Bitcoin.getBitCoinBuyPrice()) + "$"));
                    sender.sendMessage(Data.convert("&6Giá của bán 1 BitCoin hiện tại là " + String.format("%,d", Bitcoin.getBitCoinSellPrice()) + "$"));
                    sender.sendMessage(Data.convert("&3Cứ mỗi 1h giá BitCoin sẽ biến động"));
                    sender.sendMessage(Data.convert("&bDùng lệnh: /bitcoin mua <số bitcoin muốn mua> Để mua bitcoin"));
                    sender.sendMessage(Data.convert("&bDùng lệnh: /bitcoin ban <số bitcoin muốn bán> Để bán bitcoin"));
                }
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("mua")) {
                        if (Integer.parseInt(args[1]) > 0) {
                            if (Integer.parseInt(args[1]) + Bitcoin.getBitCoin(Objects.requireNonNull(((Player) sender).getPlayer())) <= Bitcoin.getBitCoinMax()) {
                                if (Bitcoin.getBitCoinSellPrice() > 250000) {
                                    if (bCore.economy.getBalance(((Player) sender).getPlayer()) >= Bitcoin.getBitCoinBuyPrice() * Integer.parseInt(args[1])) {
                                        if (Bitcoin.getBitCoin(Objects.requireNonNull(((Player) sender).getPlayer())) + Integer.parseInt(args[1]) <= Bitcoin.getBitCoinMax()) {
                                            Bitcoin.addBitCoin(Objects.requireNonNull(((Player) sender).getPlayer()), Integer.parseInt(args[1]));
                                            bCore.economy.withdrawPlayer(((Player) sender).getPlayer(), Bitcoin.getBitCoinBuyPrice() * Integer.parseInt(args[1]));
                                        } else {
                                            sender.sendMessage(Data.convert("&cBạn đã đạt đến giới hạn mua BitCoins"));
                                        }
                                    } else {
                                        sender.sendMessage(Data.convert("&cBạn không đủ tiền mua " + Integer.parseInt(args[1]) + " BitCoins"));
                                    }
                                } else {
                                    sender.sendMessage(Data.convert("&cBạn không thể mua bitcoin lúc này!"));
                                }
                            } else {
                                sender.sendMessage(Data.convert("&cBạn không thể mua thêm bitcoin"));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("ban")) {
                        if (Integer.parseInt(args[1]) > 0) {
                            if (Bitcoin.getBitCoin(Objects.requireNonNull(((Player) sender).getPlayer())) - Integer.parseInt(args[1]) >= 0) {
                                Bitcoin.removeBitCoin(((Player) sender).getPlayer(), Integer.parseInt(args[1]));
                                bCore.economy.depositPlayer(((Player) sender).getPlayer(), Bitcoin.getBitCoinSellPrice() * Integer.parseInt(args[1]));
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
}
