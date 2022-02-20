package net.danh.bCore.Commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.danh.bCore.CustomEnchantment.Money;
import net.danh.bCore.Gui.SellBlocks;
import net.danh.bCore.bCore;
import net.danh.bCore.Manager.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class Commands implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, String label, String[] args) {

        String RELOADED = Data.getlanguagefile().getString("RELOAD");
        String PREFIX = Data.getlanguagefile().getString("PREFIX");
        String DONE = Data.getlanguagefile().getString("DONE");
        String UNKNOW_PLAYER = Data.getlanguagefile().getString("UNKNOW_PLAYER");
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
                        Data.reloadconfig();
                        sender.sendMessage(Data.convert(PREFIX + RELOADED));
                    }
                    if (args[0].equalsIgnoreCase("update")) {
                        try {
                            String tagname = null;
                            String version = null;
                            String body = null;
                            String name = null;
                            URL api = new URL("https://api.github.com/repos/VoChiDanh/Jobs/releases/latest");
                            URLConnection con = api.openConnection();
                            con.setConnectTimeout(15000);
                            con.setReadTimeout(15000);

                            JsonObject json = new JsonParser().parse(new InputStreamReader(con.getInputStream())).getAsJsonObject();
                            tagname = json.get("tag_name").getAsString();
                            version = bCore.getInstance().getDescription().getVersion();
                            body = json.get("body").getAsString();
                            name = json.get("name").getAsString();

                            String parsename = name.replaceAll("v", "");
                            String parsebody = body.replaceAll("## Commits", "");
                            if (!(parsename.equalsIgnoreCase(version))) {
                                URL download = new URL("https://github.com/VoChiDanh/Jobs/releases/download/" + tagname + "/bCore.jar");
                                sender.sendMessage(Data.convert("&eDownloading " + name + "! &6Your version is &6v" + version));
                                sender.sendMessage(Data.convert("&aUpdate log: " + parsebody));
                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        try {
                                            InputStream in = download.openStream();
                                            File temp = new File("plugins/bCore/update/");
                                            if (!temp.exists()) {
                                                temp.mkdir();
                                            }
                                            Path path = new File("plugins/bCore/update/" + File.separator + "bCore.jar").toPath();
                                            java.nio.file.Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
                                            sender.sendMessage(Data.convert("&6Download finished, stop the server and check new jar in bCore folder to get the new update"));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.runTaskLaterAsynchronously(bCore.getInstance(), 1);
                            } else {
                                sender.sendMessage(Data.convert("&aYou are on latest build"));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (args.length == 2) {
                if (sender.hasPermission("bCore.user")) {
                    if (args[0].equalsIgnoreCase("info")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            for (String check_info : Data.getlanguagefile().getStringList("INFO_CHECK")) {
                                sender.sendMessage(Data.convert(check_info
                                        .replaceAll("%player%", Objects.requireNonNull(Bukkit.getPlayer(args[1])).getName())
                                        .replaceAll("%plevel%", String.valueOf(PlayerData.getLevel(Objects.requireNonNull(Bukkit.getPlayer(args[1])))))
                                        .replaceAll("%xp", String.valueOf(PlayerData.getXP(Objects.requireNonNull(Bukkit.getPlayer(args[1])))))
                                        .replaceAll("%power%", String.valueOf(Power.getPower(Objects.requireNonNull(Bukkit.getPlayer(args[1])))))));
                            }
                        } else {
                            sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                        }
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
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.addLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.removeLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("xp")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.setXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.addXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.removeXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("power")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.setPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.addPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.removePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("levelup")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.setLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.addLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.removeLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("maxpower")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.setMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.addMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.removeMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            }
                        } else {
                            sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                        }
                    }
                    if (args[0].equalsIgnoreCase("restorepower")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.setRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.addRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.removeRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("luckychance")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Lucky.setLuckyChance(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Lucky.addLuckyChance(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Lucky.removeLuckyChance(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("weight")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.setWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Double.valueOf(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.addWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Double.valueOf(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.removeWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Double.valueOf(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("maxweight")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.setMaxWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Double.valueOf(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.addMaxWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Double.valueOf(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.removeMaxWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Double.valueOf(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("price")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            Price.setPrice(args[2], Double.valueOf(args[3]));
                            sender.sendMessage(Data.convert(PREFIX + DONE));
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            Price.addPrice(args[2], Double.valueOf(args[3]));
                            sender.sendMessage(Data.convert(PREFIX + DONE));
                            sender.sendMessage(Data.convert(PREFIX + DONE));
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            Price.removePrice(args[2], Double.valueOf(args[3]));
                            sender.sendMessage(Data.convert(PREFIX + DONE));
                            sender.sendMessage(Data.convert(PREFIX + DONE));
                        }
                    }
                    if (args[0].equalsIgnoreCase("baseprice")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            Price.setBasePrice(args[2], Double.valueOf(args[3]));
                            sender.sendMessage(Data.convert(PREFIX + DONE));
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            Price.addBasePrice(args[2], Double.valueOf(args[3]));
                            sender.sendMessage(Data.convert(PREFIX + DONE));
                            sender.sendMessage(Data.convert(PREFIX + DONE));
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            Price.removeBasePrice(args[2], Double.valueOf(args[3]));
                            sender.sendMessage(Data.convert(PREFIX + DONE));
                            sender.sendMessage(Data.convert(PREFIX + DONE));
                        }
                    }
                    if (args[0].equalsIgnoreCase("bankmoney")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.setBankMoney(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.addBankMoney(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.removeBankMoney(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("bankmaxmoney")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.setBankMaxMoney(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.addBankMaxMoney(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Bank.removeBankMaxMoney(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("enchant")) {
                        if (sender instanceof Player) {
                            if (args[1].equalsIgnoreCase("set")) {
                                if (args[2].equals("Money")) {
                                    Objects.requireNonNull(((Player) sender).getPlayer()).getItemInHand().addUnsafeEnchantment(new Money(), Integer.parseInt(args[3]));
                                    sender.sendMessage(Data.convert(PREFIX + DONE));
                                }
                            }
                            if (args[1].equalsIgnoreCase("add")) {
                                if (args[2].equals("Money")) {
                                    if (Objects.requireNonNull(((Player) sender).getPlayer()).getItemInHand().getEnchantmentLevel(new Money()) == 0) {
                                        Objects.requireNonNull(((Player) sender).getPlayer()).getItemInHand().addUnsafeEnchantment(new Money(), 1);
                                    } else {
                                        Objects.requireNonNull(((Player) sender).getPlayer()).getItemInHand().addUnsafeEnchantment(new Money(), ((Player) sender).getPlayer().getItemInHand().getEnchantmentLevel(new Money()) + Integer.parseInt(args[3]));
                                        sender.sendMessage(Data.convert(PREFIX + DONE));
                                    }
                                }
                            }
                            if (args[1].equalsIgnoreCase("remove")) {
                                if (args[2].equals("Money")) {
                                    if (Objects.requireNonNull(((Player) sender).getPlayer()).getItemInHand().getEnchantmentLevel(new Money()) > 1) {
                                        if (((Player) sender).getPlayer().getItemInHand().containsEnchantment(new Money())) {
                                            Objects.requireNonNull(((Player) sender).getPlayer()).getItemInHand().addUnsafeEnchantment(new Money(), ((Player) sender).getPlayer().getItemInHand().getEnchantmentLevel(new Money()) - Integer.parseInt(args[3]));
                                            sender.sendMessage(Data.convert(PREFIX + DONE));
                                        }
                                    } else {
                                        ((Player) sender).getPlayer().getItemInHand().removeEnchantment(new Money());
                                        sender.sendMessage(Data.convert(PREFIX + DONE));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (args.length == 5) {
                if (sender.hasPermission("bCore.admin")) {
                    if (args[0].equalsIgnoreCase("storage")) {
                        if (Material.getMaterial(args[3]) != null) {
                            if (args[1].equalsIgnoreCase("set")) {
                                if (Bukkit.getPlayer(args[2]) != null) {
                                    Storage.setStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Double.valueOf(args[4]));
                                    sender.sendMessage(Data.convert(PREFIX + DONE));
                                } else {
                                    sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                                }
                            }
                            if (args[1].equalsIgnoreCase("add")) {
                                if (Bukkit.getPlayer(args[2]) != null) {
                                    Storage.addStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Double.valueOf(args[4]));
                                    sender.sendMessage(Data.convert(PREFIX + DONE));
                                } else {
                                    sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                                }
                            }
                            if (args[1].equalsIgnoreCase("remove")) {
                                if (Bukkit.getPlayer(args[2]) != null) {
                                    Storage.removeStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Double.valueOf(args[4]));
                                    sender.sendMessage(Data.convert(PREFIX + DONE));
                                } else {
                                    sender.sendMessage(Data.convert(PREFIX + UNKNOW_PLAYER));
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }
        if (label.equalsIgnoreCase("storage")) {
            if (sender instanceof Player) {
                if (args.length == 0) {
                    if (sender.hasPermission("bCore.user")) {
                        Objects.requireNonNull(((Player) sender).getPlayer()).openInventory(SellBlocks.getSellGui(((Player) sender).getPlayer()));
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
                        if (Integer.parseInt(args[1]) != 0) {
                            Bank.depositBank(((Player) sender).getPlayer(), Integer.parseInt(args[1]));
                        }
                    }
                    if (args[0].equalsIgnoreCase("withdraw")) {
                        if (Integer.parseInt(args[1]) != 0) {
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
