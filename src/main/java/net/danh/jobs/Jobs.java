package net.danh.jobs;

import net.danh.jobs.Commands.Commands;
import net.danh.jobs.Commands.TabCommand;
import net.danh.jobs.CustomEnchantment.Money;
import net.danh.jobs.Events.*;
import net.danh.jobs.Hook.PlaceholderAPI;
import net.danh.jobs.Manager.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.logging.Level;

public final class Jobs extends JavaPlugin implements Listener {


    public static Economy economy;
    static Enchantment MONEY;
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
                return "1.8";
            case "v1_8_R2":
                return "1.8.3";
            case "v1_8_R3":
                return "1.8.x";
            case "v1_9_R1":
                return "1.9.x";
            case "v1_9_R2":
                return "1.9.4";
            case "v1_10_R1":
                return "1.10.x";
            case "v1_11_R1":
                return "1.11.x";
            case "v1_12_R1":
                return "1.12.x";
            case "v1_13_R1":
                return "1.13";
            case "v1_13_R2":
                return "1.13.x";
            case "v1_14_R1":
                return "1.14.x";
            case "v1_15_R1":
                return "1.15.x";
            case "v1_16_R1":
                return "1.16.1";
            case "v1_16_R2":
                return "1.16.2 / 1.16.3";
            case "v1_16_R3":
                return "1.16.4 / 1.16.5";
            case "v1_17_R1":
                return "1.17.x";
            case "v1_18_R1":
                return "1.18.x";

        }
        throw new IllegalArgumentException("Your sever version is " + nms + ". Plugin does not support this version!");
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
        getServer().getPluginManager().registerEvents(new Money(), this);
        getServer().getPluginManager().registerEvents(new Click(), this);
        Objects.requireNonNull(getCommand("jobs")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("storage")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("jobs")).setTabCompleter(new TabCommand());
        Objects.requireNonNull(getCommand("bank")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("bank")).setTabCompleter(new TabCommand());
        MONEY = new Money();
        registerEnchants(MONEY);
        Data.createconfig();
        getLogger().log(Level.INFO, "Loading data matching server version " + formatNMSVersion(getNMSVersion()));
        (new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (PlayerData.getLevel(p) == 1) {
                        if (Power.getPower(p) > Power.getMaxPower(p)) {
                            Power.setPower(p, Power.getMaxPower(p));
                        }
                        if (Power.getPower(p) >= Power.getMaxPower(p) * 0.9D) {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, Integer.MIN_VALUE));
                        } else {
                            p.removePotionEffect(PotionEffectType.CONFUSION);
                        }
                    }
                    if (Weight.getWeight(p) >= Weight.getMaxWeight(p) * 0.9D) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, Integer.MIN_VALUE));
                    } else {
                        p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
                        p.removePotionEffect(PotionEffectType.SLOW);
                    }
                }
            }
        }).runTaskTimer(this, 20L, 20L);
        (new BukkitRunnable() {
            public void run() { //TODO Need do something with this
                if (getServer().getOnlinePlayers().size() > 0) {
                    double stone = Data.getRandomNumber(1, 1000);
                    double coal = Data.getRandomNumber(1, 1000);
                    double iron = Data.getRandomNumber(1, 1000);
                    double gold = Data.getRandomNumber(1, 1000);
                    double diamond = Data.getRandomNumber(1, 1000);
                    double emerald = Data.getRandomNumber(1, 1000);
                    double log = Data.getRandomNumber(1, 1000);
                    if (stone < 500) {
                        Price.removePrice("STONE".toUpperCase(), stone / 100);
                    } else {
                        Price.addPrice("STONE".toUpperCase(), stone / 100);
                    }
                    if (coal < 500) {
                        Price.removePrice("COAL".toUpperCase(), coal / 100);
                    } else {
                        Price.addPrice("COAL".toUpperCase(), coal / 100);
                    }
                    if (iron < 500) {
                        Price.removePrice("IRON".toUpperCase(), iron / 100);
                    } else {
                        Price.addPrice("IRON".toUpperCase(), iron / 100);
                    }
                    if (gold < 500) {
                        Price.removePrice("GOLD".toUpperCase(), gold / 100);
                    } else {
                        Price.addPrice("GOLD".toUpperCase(), gold / 100);
                    }
                    if (diamond < 500) {
                        Price.removePrice("DIAMOND".toUpperCase(), diamond / 100);
                    } else {
                        Price.addPrice("DIAMOND".toUpperCase(), diamond / 100);
                    }
                    if (emerald < 500) {
                        Price.removePrice("EMERALD".toUpperCase(), emerald / 100);
                    } else {
                        Price.addPrice("EMERALD".toUpperCase(), emerald / 100);
                    }
                    if (log < 500) {
                        Price.removePrice("LOG".toUpperCase(), log / 100);
                    } else {
                        Price.addPrice("LOG".toUpperCase(), log / 100);
                    }
                }
            }
        }).runTaskTimer(this, 300 * 20L, 300 * 20L);
    }

    @Override
    public void onDisable() {
        Data.savedata();
        Data.saveconfig();
        Data.savelanguage();
        Data.savegui();
        Data.saveprice();
        Data.savebank();
        getLogger().log(Level.INFO, "Saved data");
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null)
            economy = economyProvider.getProvider();
        return (economy != null);
    }

    private void registerEnchants(Enchantment ench) {
        try {
            try {
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Enchantment.registerEnchantment(ench);
                getLogger().log(Level.INFO, "Registered " + ench.getName());
            } catch (IllegalArgumentException e) {
                //if this is thrown it means the id is already taken.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
