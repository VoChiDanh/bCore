package net.danh.jobs.Commands;

import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.Files;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class Commands implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, String label, String[] args) {

        String RELOADED = Files.getInstance().getlanguage().getString("RELOAD");
        String NOT_ENOUGH_PERMISSION = Files.getInstance().getlanguage().getString("NOT_ENOUGH_PERMISSION");
        String PREFIX = Files.getInstance().getlanguage().getString("PREFIX");
        String DONE = Files.getInstance().getlanguage().getString("DONE");
        String NOT_ENOUGH_MONEY = Files.getInstance().getlanguage().getString("NOT_ENOUGH_MONEY");
        String NOT_CORRECT_JOBS = Files.getInstance().getlanguage().getString("NOT_CORRECT_JOBS");

        if (label.equalsIgnoreCase("jobs")) {
            if (args.length == 0) {
                if (sender.hasPermission("jobs.user")) {
                    for (String user : Files.getInstance().getlanguage().getStringList("HELP_USER")) {
                        sender.sendMessage(Files.getInstance().convert(user));
                    }
                }
                if (sender.hasPermission("jobs.admin")) {
                    for (String admin : Files.getInstance().getlanguage().getStringList("HELP_ADMIN")) {
                        sender.sendMessage(Files.getInstance().convert(admin));
                    }
                }
            }
            if (args.length == 1) {
                if (sender.hasPermission("jobs.admin")) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        Files.getInstance().reloadconfig();
                        sender.sendMessage(Files.getInstance().convert(PREFIX + RELOADED));
                    }
                } else {
                    sender.sendMessage(Files.getInstance().convert(PREFIX + NOT_ENOUGH_PERMISSION));
                }
            }
            if (args.length == 2) {
                if (sender.hasPermission("jobs.user")) {
                    if (args[0].equalsIgnoreCase("info")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            for (String check_info : Files.getInstance().getlanguage().getStringList("INFO_CHECK")) {
                                sender.sendMessage(Files.getInstance().convert(check_info
                                        .replaceAll("%player%", Objects.requireNonNull(Bukkit.getPlayer(args[1])).getName())
                                        .replaceAll("%jobs%", Files.getInstance().getJobs(Objects.requireNonNull(Bukkit.getPlayer(args[1]))))
                                        .replaceAll("%plevel%", String.valueOf(Files.getInstance().getAge(Objects.requireNonNull(Bukkit.getPlayer(args[1])))))
                                        .replaceAll("%xp", String.valueOf(Files.getInstance().getXP(Objects.requireNonNull(Bukkit.getPlayer(args[1])))))
                                        .replaceAll("%glevel%", String.valueOf(Files.getInstance().getLevelGang(Objects.requireNonNull(Bukkit.getPlayer(args[1])))))
                                        .replaceAll("%power%", String.valueOf(Files.getInstance().getPower(Objects.requireNonNull(Bukkit.getPlayer(args[1])))))
                                        .replaceAll("%leveluptimes%", String.valueOf(Files.getInstance().getleveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[1])))))));
                            }
                        }
                    }
                }
            }
            if (args.length == 4) {
                if (sender.hasPermission("jobs.admin")) {
                    if (args[0].equalsIgnoreCase("plevel")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setAge(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addAge(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeAge(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("xp")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("power")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("glevel")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setLevelGang(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addLevelGang(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeLevelGang(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("levelup")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("maxpower")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("restorepower")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                    }
                }
            }
            if (args.length == 2) {
                if (sender instanceof Player) {
                    if (sender.hasPermission("jobs.user")) {
                        if (args[0].equalsIgnoreCase("select")) {
                            if (args[1].equalsIgnoreCase("Miner")) {
                                Files.getInstance().setJobs(Objects.requireNonNull(((Player) sender).getPlayer()), args[1].toUpperCase());
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[1].toUpperCase());
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                            if (args[1].equalsIgnoreCase("Farmer")) {
                                Files.getInstance().setJobs(Objects.requireNonNull(((Player) sender).getPlayer()), args[1].toUpperCase());
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[1].toUpperCase());
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                            if (args[1].equalsIgnoreCase("Woodcutter")) {
                                Files.getInstance().setJobs(Objects.requireNonNull(((Player) sender).getPlayer()), args[1].toUpperCase());
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[1].toUpperCase());
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                            if (args[1].equalsIgnoreCase("Fisher")) {
                                Files.getInstance().setJobs(Objects.requireNonNull(((Player) sender).getPlayer()), args[1].toUpperCase());
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[1].toUpperCase());
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                            if (args[1].equalsIgnoreCase("Doctor")) {
                                Files.getInstance().setJobs(Objects.requireNonNull(((Player) sender).getPlayer()), args[1].toUpperCase());
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[1].toUpperCase());
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                    }
                }
            }
            if (args.length == 2) {
                if (sender instanceof Player) {
                    if (args[0].equalsIgnoreCase("items")) {
                        if (args[1].equalsIgnoreCase("Doctor")) {
                            if (Files.getInstance().getJobs(Objects.requireNonNull(((Player) sender).getPlayer())).equals(args[1].toUpperCase())) {
                                if (Jobs.economy.getBalance(((Player) sender).getPlayer()) >= Files.getInstance().getconfig().getInt("doctor_price_medicine")) {
                                    ItemStack items = new ItemStack(Material.valueOf(Files.getInstance().getconfig().getString("doctor_items.MATERIAL")), Files.getInstance().getconfig().getInt("doctor_items.AMOUNT"));
                                    ItemMeta meta = items.getItemMeta();
                                    Objects.requireNonNull(meta).setDisplayName(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getconfig().getString("doctor_items.DISPLAY_NAME"))));
                                    ArrayList<String> lore = new ArrayList<>();
                                    lore.add(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getconfig().getString("doctor_items.LORE1"))));
                                    lore.add(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getconfig().getString("doctor_items.LORE2"))));
                                    meta.setLore(lore);
                                    items.setItemMeta(meta);
                                    ((Player) sender).getInventory().addItem(items);
                                    Jobs.economy.withdrawPlayer(((Player) sender).getPlayer(), Files.getInstance().getconfig().getInt("doctor_price_medicine"));
                                    sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                                } else {
                                    sender.sendMessage(Files.getInstance().convert(PREFIX + NOT_ENOUGH_MONEY));
                                }
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + NOT_CORRECT_JOBS));
                            }
                        }
                    }
                }
            }
            return true;
        }
        return true;
    }
}
