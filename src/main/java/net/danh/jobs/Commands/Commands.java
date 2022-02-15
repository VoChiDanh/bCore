package net.danh.jobs.Commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.danh.jobs.Gui.SellBlocks;
import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.*;
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

        String RELOADED = Data.getInstance().getlanguage().getString("RELOAD");
        String PREFIX = Data.getInstance().getlanguage().getString("PREFIX");
        String DONE = Data.getInstance().getlanguage().getString("DONE");
        String UNKNOW_PLAYER = Data.getInstance().getlanguage().getString("UNKNOW_PLAYER");
        String UNKNOW_GANG = Data.getInstance().getlanguage().getString("UNKNOW_GANG");

        if (label.equalsIgnoreCase("jobs")) {
            if (args.length == 0) {
                if (sender.hasPermission("jobs.user")) {
                    for (String user : Data.getInstance().getlanguage().getStringList("HELP_USER")) {
                        sender.sendMessage(Data.getInstance().convert(user));
                    }
                }
                if (sender.hasPermission("jobs.admin")) {
                    for (String admin : Data.getInstance().getlanguage().getStringList("HELP_ADMIN")) {
                        sender.sendMessage(Data.getInstance().convert(admin));
                    }
                }
            }
            if (args.length == 1) {
                if (sender.hasPermission("jobs.admin")) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        Data.getInstance().reloadconfig();
                        sender.sendMessage(Data.getInstance().convert(PREFIX + RELOADED));
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
                            version = Jobs.getInstance().getDescription().getVersion();
                            body = json.get("body").getAsString();
                            name = json.get("name").getAsString();

                            String parsename = name.replaceAll("v", "");
                            String parsebody = body.replaceAll("## Commits", "");
                            if (!(parsename.equalsIgnoreCase(version))) {
                                URL download = new URL("https://github.com/VoChiDanh/Jobs/releases/download/" + tagname + "/Jobs-0.2.2-SNAPSHOT.jar");
                                sender.sendMessage(Data.getInstance().convert("&eDownloading " + name + "! &6Your version is &6v" + version));
                                sender.sendMessage(Data.getInstance().convert("&aUpdate log: " + parsebody));
                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        try {
                                            InputStream in = download.openStream();
                                            File temp = new File("plugins/Jobs/update/");
                                            if (!temp.exists()) {
                                                temp.mkdir();
                                            }
                                            Path path = new File("plugins/Jobs/update/" + File.separator + "Jobs.jar").toPath();
                                            java.nio.file.Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
                                            sender.sendMessage(Data.getInstance().convert("&6Download finished, stop the server and check new jar in Jobs folder to get the new update"));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.runTaskLaterAsynchronously(Jobs.getInstance(), 1);
                            } else {
                                sender.sendMessage(Data.getInstance().convert("&aYou are on latest build"));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (args.length == 2) {
                if (sender.hasPermission("jobs.user")) {
                    if (args[0].equalsIgnoreCase("info")) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            for (String check_info : Data.getInstance().getlanguage().getStringList("INFO_CHECK")) {
                                sender.sendMessage(Data.getInstance().convert(check_info
                                        .replaceAll("%player%", Objects.requireNonNull(Bukkit.getPlayer(args[1])).getName())
                                        .replaceAll("%plevel%", String.valueOf(PlayerData.getInstance().getLevel(Objects.requireNonNull(Bukkit.getPlayer(args[1])))))
                                        .replaceAll("%xp", String.valueOf(PlayerData.getInstance().getXP(Objects.requireNonNull(Bukkit.getPlayer(args[1])))))
                                        .replaceAll("%glevel%", String.valueOf(Gang.getInstance().getLevelGang(Objects.requireNonNull(Bukkit.getPlayer(args[1])))))
                                        .replaceAll("%power%", String.valueOf(Power.getInstance().getPower(Objects.requireNonNull(Bukkit.getPlayer(args[1])))))
                                        .replaceAll("%leveluptimes%", String.valueOf(Gang.getInstance().getleveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[1])))))));
                            }
                        } else {
                            sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                        }
                    }
                }
            }
            if (args.length == 4) {
                if (sender.hasPermission("jobs.admin")) {
                    if (args[0].equalsIgnoreCase("plevel")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.getInstance().setLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.getInstance().addLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.getInstance().removeLevel(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("xp")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.getInstance().setXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.getInstance().addXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                PlayerData.getInstance().removeXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("power")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.getInstance().setPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.getInstance().addPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.getInstance().removePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("glevel")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Gang.getInstance().setLevelGang(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Gang.getInstance().addLevelGang(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Gang.getInstance().removeLevelGang(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("levelup")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Gang.getInstance().setLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Gang.getInstance().addLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Gang.getInstance().removeLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("maxpower")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.getInstance().setMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.getInstance().addMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.getInstance().removeMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            }
                        } else {
                            sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                        }
                    }
                    if (args[0].equalsIgnoreCase("restorepower")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.getInstance().setRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.getInstance().addRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Power.getInstance().removeRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("luckychance")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Lucky.getInstance().setLuckyChance(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Lucky.getInstance().addLuckyChance(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Lucky.getInstance().removeLuckyChance(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("weight")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.getInstance().setWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.getInstance().addWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.getInstance().removeWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("maxweight")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.getInstance().setMaxWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.getInstance().addMaxWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Weight.getInstance().removeMaxWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("price")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            Price.getInstance().setPrice(args[2], Double.valueOf(args[3]));
                            sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            Price.getInstance().addPrice(args[2], Double.valueOf(args[3]));
                            sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            Price.getInstance().removePrice(args[2], Double.valueOf(args[3]));
                            sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                        }
                    }
                    if (args[0].equalsIgnoreCase("baseprice")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            Price.getInstance().setBasePrice(args[2], Double.valueOf(args[3]));
                            sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            Price.getInstance().addBasePrice(args[2], Double.valueOf(args[3]));
                            sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            Price.getInstance().removeBasePrice(args[2], Double.valueOf(args[3]));
                            sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                        }
                    }
                }
            }
            if (args.length == 5) {
                if (sender.hasPermission("jobs.admin")) {
                    if (args[0].equalsIgnoreCase("storage")) {
                        if (Material.getMaterial(args[3]) != null) {
                            if (args[1].equalsIgnoreCase("set")) {
                                if (Bukkit.getPlayer(args[2]) != null) {
                                    Storage.getInstance().setStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Integer.parseInt(args[4]));
                                    sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                                } else {
                                    sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                                }
                            }
                            if (args[1].equalsIgnoreCase("add")) {
                                if (Bukkit.getPlayer(args[2]) != null) {
                                    Storage.getInstance().addStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Integer.parseInt(args[4]));
                                    sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                                } else {
                                    sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                                }
                            }
                            if (args[1].equalsIgnoreCase("remove")) {
                                if (Bukkit.getPlayer(args[2]) != null) {
                                    Storage.getInstance().removeStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Integer.parseInt(args[4]));
                                    sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                                } else {
                                    sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
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
                    if (sender.hasPermission("jobs.user")) {
                        SellBlocks.getInstance().openSellGui(Objects.requireNonNull(((Player) sender).getPlayer()));
                    }
                }
            }
            return true;
        }
        if (label.equalsIgnoreCase("gang")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("jobs.user")) {
                    if (args.length == 2) {
                        if (args[0].equalsIgnoreCase("create")) {
                            if (args[1] != null) {
                                Gang.getInstance().selectGang(((Player) sender).getPlayer(), args[1].toUpperCase());
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            }
                        }
                        if (args[0].equalsIgnoreCase("join")) {
                            if (Bukkit.getPlayer(args[1]) != null) {
                                if (Gang.getInstance().getGang(Objects.requireNonNull(Bukkit.getPlayer(args[1]))) != null) {
                                    Gang.getInstance().selectGang(((Player) sender).getPlayer(), Gang.getInstance().getGang(Objects.requireNonNull(Bukkit.getPlayer(args[1]))));
                                    sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                                } else {
                                    sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_GANG));
                                }
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("leave")) {
                            if (Gang.getInstance().getGang(Objects.requireNonNull(((Player) sender).getPlayer())) != null) {
                                Gang.getInstance().selectGang(((Player) sender).getPlayer(), "Server".toUpperCase());
                                sender.sendMessage(Data.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Data.getInstance().convert(PREFIX + UNKNOW_GANG));
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
