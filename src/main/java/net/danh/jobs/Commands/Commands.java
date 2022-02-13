package net.danh.jobs.Commands;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.Files;
import net.danh.jobs.Manager.Price;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
        String UNKNOW_PLAYER = Files.getInstance().getlanguage().getString("UNKNOW_PLAYER");
        String UNKNOW_GANG = Files.getInstance().getlanguage().getString("UNKNOW_GANG");

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
                                URL download = new URL("https://github.com/VoChiDanh/Jobs/releases/download/" + tagname + "/Jobs.jar");
                                sender.sendMessage(Files.getInstance().convert("&eDownloading " + name + "! &6Your version is &6v" + version));
                                sender.sendMessage(Files.getInstance().convert("&aUpdate log: " + parsebody));
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
                                            sender.sendMessage(Files.getInstance().convert("&6Download finished, stop the server and check new jar in Jobs folder to get the new update"));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.runTaskLaterAsynchronously(Jobs.getInstance(), 1);
                            } else {
                                sender.sendMessage(Files.getInstance().convert("&aYou are on latest build"));
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
                        } else {
                            sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
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
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addAge(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeAge(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("xp")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeXP(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("power")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("glevel")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setLevelGang(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addLevelGang(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeLevelGang(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("levelup")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeLeveluptimes(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("maxpower")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeMaxPower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        } else {
                            sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                        }
                    }
                    if (args[0].equalsIgnoreCase("restorepower")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeRestorePower(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("luckychance")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setLuckyChance(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addLuckyChance(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeLuckyChance(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("weight")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("maxweight")) {
                        if (args[1].equalsIgnoreCase("set")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().setMaxWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().addMaxWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                        if (args[1].equalsIgnoreCase("remove")) {
                            if (Bukkit.getPlayer(args[2]) != null) {
                                Files.getInstance().removeMaxWeight(Objects.requireNonNull(Bukkit.getPlayer(args[2])), Integer.parseInt(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("price")) {
                        if (Material.getMaterial(args[2]) != null) {
                            if (args[1].equalsIgnoreCase("set")) {
                                Price.getInstance().setPrice(args[2], Double.valueOf(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));

                            }
                            if (args[1].equalsIgnoreCase("add")) {
                                Price.getInstance().addPrice(args[2], Double.valueOf(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                            if (args[1].equalsIgnoreCase("remove")) {
                                Price.getInstance().removePrice(args[2], Double.valueOf(args[3]));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                    }
                }
            }
            if (args.length == 3) {
                if (sender.hasPermission("jobs.user")) {
                    if (args[0].equalsIgnoreCase("sell")) {
                        if (sender instanceof Player) {
                            Price.getInstance().Sell(((Player) sender).getPlayer(), args[1].toUpperCase(), Integer.parseInt(args[2]));
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
                                    Files.getInstance().setStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Integer.parseInt(args[4]));
                                    sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                                } else {
                                    sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                                }
                            }
                            if (args[1].equalsIgnoreCase("add")) {
                                if (Bukkit.getPlayer(args[2]) != null) {
                                    Files.getInstance().addStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Integer.parseInt(args[4]));
                                    sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                                } else {
                                    sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                                }
                            }
                            if (args[1].equalsIgnoreCase("remove")) {
                                if (Bukkit.getPlayer(args[2]) != null) {
                                    Files.getInstance().removeStorage(Objects.requireNonNull(Bukkit.getPlayer(args[2])), args[3], Integer.parseInt(args[4]));
                                    sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                                } else {
                                    sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                                }
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
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                            if (args[1].equalsIgnoreCase("Farmer")) {
                                Files.getInstance().setJobs(Objects.requireNonNull(((Player) sender).getPlayer()), args[1].toUpperCase());
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                            if (args[1].equalsIgnoreCase("Woodcutter")) {
                                Files.getInstance().setJobs(Objects.requireNonNull(((Player) sender).getPlayer()), args[1].toUpperCase());
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                            if (args[1].equalsIgnoreCase("Fisher")) {
                                Files.getInstance().setJobs(Objects.requireNonNull(((Player) sender).getPlayer()), args[1].toUpperCase());
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                            if (args[1].equalsIgnoreCase("Doctor")) {
                                Files.getInstance().setJobs(Objects.requireNonNull(((Player) sender).getPlayer()), args[1].toUpperCase());
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
                                    ItemStack items = new ItemStack(Material.valueOf(Files.getInstance().getitems().getString("doctor_items.MATERIAL")), Files.getInstance().getitems().getInt("doctor_items.AMOUNT"));
                                    ItemMeta meta = items.getItemMeta();
                                    Objects.requireNonNull(meta).setDisplayName(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("doctor_items.DISPLAY_NAME"))));
                                    ArrayList<String> lore = new ArrayList<>();
                                    lore.add(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("doctor_items.LORE1"))));
                                    lore.add(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("doctor_items.LORE2"))));
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
        if (label.equalsIgnoreCase("gang")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("jobs.user")) {
                    if (args.length == 2) {
                        if (args[0].equalsIgnoreCase("create")) {
                            if (args[1] != null) {
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), args[1].toUpperCase());
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            }
                        }
                        if (args[0].equalsIgnoreCase("join")) {
                            if (Bukkit.getPlayer(args[1]) != null) {
                                if (Files.getInstance().getGang(Objects.requireNonNull(Bukkit.getPlayer(args[1]))) != null) {
                                    Files.getInstance().selectGang(((Player) sender).getPlayer(), Files.getInstance().getGang(Objects.requireNonNull(Bukkit.getPlayer(args[1]))));
                                    sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                                } else {
                                    sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_GANG));
                                }
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_PLAYER));
                            }
                        }
                    }
                    if (args.length == 1) {
                        if (args[0].equalsIgnoreCase("leave")) {
                            if (Files.getInstance().getGang(Objects.requireNonNull(((Player) sender).getPlayer())) != null) {
                                Files.getInstance().selectGang(((Player) sender).getPlayer(), Files.getInstance().getJobs(((Player) sender).getPlayer()));
                                sender.sendMessage(Files.getInstance().convert(PREFIX + DONE));
                            } else {
                                sender.sendMessage(Files.getInstance().convert(PREFIX + UNKNOW_GANG));
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
