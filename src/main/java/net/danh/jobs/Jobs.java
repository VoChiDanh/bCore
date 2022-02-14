package net.danh.jobs;

import net.danh.jobs.Commands.Commands;
import net.danh.jobs.Commands.TabCommand;
import net.danh.jobs.Events.*;
import net.danh.jobs.Hook.PlaceholderAPI;
import net.danh.jobs.Manager.Files;
import net.danh.jobs.Manager.Price;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.logging.Level;

public final class Jobs extends JavaPlugin implements Listener {


    public static Economy economy;
    private static Jobs instance;

    public static Jobs getInstance() {
        return instance;
    }

    public static String getNMSVersion() {
        String v = Bukkit.getServer().getClass().getPackage().getName();
        return v.substring(v.lastIndexOf('.') + 1);
    }

    public static String formatNMSVersion(String nms) {
        switch (nms) {
            case "v1_8_R1":
                return "1.8.x";
            case "v1_8_R2":
                return "1.8.x";
            case "v1_8_R3":
                return "1.8.x";
            case "v1_9_R1":
                return "1.9.x";
            case "v1_9_R2":
                return "1.9.x";
            case "v1_10_R1":
                return "1.10.x";
            case "v1_11_R1":
                return "1.11.x";
            case "v1_12_R1":
                return "1.12.x";
            case "v1_13_R1":
                return "1.13.x";
            case "v1_13_R2":
                return "1.13.x";
            case "v1_14_R1":
                return "1.14.x";
            case "v1_15_R1":
                return "1.15.x";
            case "v1_16_R1":
                return "1.16.x";
            case "v1_16_R2":
                return "1.16.x";
            case "v1_16_R3":
                return "1.16.x";
            case "v1_17_R1":
                return "1.17.x";
            case "v1_18_R1":
                return "1.18.x";

        }
        throw new IllegalArgumentException("Your sever version is " + nms + ". Plugin doesnt support this version!");
    }

    @Override
    public void onEnable() {
        instance = this;
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            getLogger().log(Level.INFO, "Hooked with PlaceholderAPI");
            new PlaceholderAPI().register();
        }
        if (getServer().getPluginManager().isPluginEnabled("Vault")) {
            getLogger().log(Level.INFO, "Hooked with Vault");
        }
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Fishing(), this);
        getServer().getPluginManager().registerEvents(new Eating(), this);
        getServer().getPluginManager().registerEvents(new Death(), this);
        getServer().getPluginManager().registerEvents(new Inventory(), this);
        Objects.requireNonNull(getCommand("jobs")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("jobs")).setTabCompleter(new TabCommand());
        Objects.requireNonNull(getCommand("gang")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("gang")).setTabCompleter(new TabCommand());
        Files.getInstance().createconfig();
        getLogger().log(Level.INFO, "Loading data matching server version " + formatNMSVersion(getNMSVersion()));
        (new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (Files.getInstance().getLevel(p) == 1) {
                        if (Files.getInstance().getPower(p) > Files.getInstance().getMaxPower(p)) {
                            Files.getInstance().setPower(p, Files.getInstance().getMaxPower(p));
                        }
                        if (Files.getInstance().getPower(p) >= Files.getInstance().getMaxPower(p) * 0.9D) {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, Integer.MIN_VALUE));
                        } else {
                            p.removePotionEffect(PotionEffectType.CONFUSION);
                        }
                    }
                    if (Files.getInstance().getWeight(p) >= Files.getInstance().getMaxWeight(p) * 0.9D) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, Integer.MIN_VALUE));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, Integer.MIN_VALUE));
                    } else {
                        p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
                        p.removePotionEffect(PotionEffectType.SLOW);
                    }
                }
            }
        }).runTaskTimer(this, 20L, 20L);
        (new BukkitRunnable() {
            public void run() { //TODO Need do something with this
                double number = Files.getInstance().getRandomNumber(1, 100);

                if (number < 50) {
                    Price.getInstance().removePrice("COAL", number / 100);
                    Price.getInstance().removePrice("IRON", number / 100);
                    Price.getInstance().removePrice("GOLD", number / 100);
                    Price.getInstance().removePrice("DIAMOND", number / 100);
                    Price.getInstance().removePrice("EMERALD", number / 100);
                    Price.getInstance().removePrice("LOG", number / 100);
                } else {
                    Price.getInstance().addPrice("COAL", number / 100);
                    Price.getInstance().addPrice("IRON", number / 100);
                    Price.getInstance().addPrice("GOLD", number / 100);
                    Price.getInstance().addPrice("DIAMOND", number / 100);
                    Price.getInstance().addPrice("EMERALD", number / 100);
                    Price.getInstance().addPrice("LOG", number / 100);
                }
            }
        }).runTaskTimer(this, 300 * 20L, 300 * 20L);
    }

    @Override
    public void onDisable() {
        Files.getInstance().savedata();
        Files.getInstance().saveconfig();
        Files.getInstance().savelanguage();
        Files.getInstance().savegui();
        Files.getInstance().saveprice();
        getLogger().log(Level.INFO, "Saved data");
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null)
            economy = economyProvider.getProvider();
        return (economy != null);
    }
}
