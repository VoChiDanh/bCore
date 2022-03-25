package net.danh.Commands;

import net.danh.Contest.Mining.Mining;
import net.danh.Manager.*;
import net.danh.bCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Commands implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        String RELOADED = Data.getlanguagefile().getString("RELOAD");
        String PREFIX = Data.getlanguagefile().getString("PREFIX");
        String DONE = Data.getlanguagefile().getString("DONE");
        String UNKNOWN_PLAYER = Data.getlanguagefile().getString("UNKNOWN_PLAYER");
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
            if (args.length == 3) {
                if (sender instanceof Player) {
                    if (args[0].equalsIgnoreCase("sell")) {
                        Items.Sell(((Player) sender).getPlayer(), Items.getName(args[1]), Integer.parseInt(args[2]));
                    }
                    if (args[0].equalsIgnoreCase("take")) {
                        Items.Take(((Player) sender).getPlayer(), args[1], Integer.parseInt(args[2]));
                    }
                }
            }
            if (args.length == 4) {
                if (sender.hasPermission("bCore.admin")) {
                    if (args[0].equalsIgnoreCase("plevel")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.setLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.addLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.removeLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("xp")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.setXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.addXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.removeXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("credit")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Credit.setCredit(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Double.parseDouble(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Credit.addCredit(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Double.parseDouble(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Credit.removeCredit(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Double.parseDouble(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("power")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.setPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Integer.parseInt(args[3]) > 0) {
                                if (Bukkit.getPlayer(args[2]) != null) {
                                    Power.addPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                    sender.sendMessage(Data.convert(PREFIX + DONE));
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
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("levelup")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.setLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.addLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.removeLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("maxpower")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.setMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.addMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.removeMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            }
                        } else {
                            sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                        }
                    }
                    if (args[0].equalsIgnoreCase("restorepower")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.setRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.addRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.removeRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("luckychance")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Lucky.setLuckyChance(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Lucky.addLuckyChance(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Lucky.removeLuckyChance(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("weight")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.setWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.addWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.removeWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("maxweight")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.setMaxWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.addMaxWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.removeMaxWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("price")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            Items.setPrice(args[2], Integer.parseInt(args[3]));
                            sender.sendMessage(Data.convert(PREFIX + DONE));
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            Items.addPrice(args[2], Integer.parseInt(args[3]));
                            sender.sendMessage(Data.convert(PREFIX + DONE));
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            Items.removePrice(args[2], Integer.parseInt(args[3]));
                            sender.sendMessage(Data.convert(PREFIX + DONE));
                        }
                    }
                    if (args[0].equalsIgnoreCase("bankmoney")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.setBankMoney(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.addBankMoney(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.removeBankMoney(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("banklevel")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.setBankLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.addBankLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.removeBankLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("blocks-event")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Event.setBlocksEvent(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Event.addBlocksEvent(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Event.removeBlocksEvent(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("blocks")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.setBlocks(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.addBlocks(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.removeBlocks(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                }
            }
            if (args.length == 5) {
                if (sender.hasPermission("bCore.admin")) {
                    if (args[0].equalsIgnoreCase("storage")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Storage.setStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Integer.parseInt(args[4]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Storage.addStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Integer.parseInt(args[4]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Storage.removeStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Integer.parseInt(args[4]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOWN_PLAYER));
                            }
                        }
                    }
                }
            }
            return true;
        }
        if (label.equalsIgnoreCase("bank")) {
            if (sender instanceof Player) {
                if (args.length == 0) {
                    sender.sendMessage(Data.convert(Objects.requireNonNull(Objects.requireNonNull(Data.getlanguagefile().getString("BANK"))
                            .replaceAll("%money%", String.valueOf(Bank.getBankMoney(Objects.requireNonNull(((Player) sender).getPlayer())))))));
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
        return true;
    }
}
