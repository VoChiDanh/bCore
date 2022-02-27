package net.danh.bCore;

import net.danh.bCore.Commands.Commands;
import net.danh.bCore.Commands.TabCommand;
import net.danh.bCore.CustomEnchantment.Money;
import net.danh.bCore.Events.*;
import net.danh.bCore.Hook.MythicMobs;
import net.danh.bCore.Hook.PlaceholderAPI;
import net.danh.bCore.Manager.*;
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

public final class bCore extends JavaPlugin implements Listener {


    public static Economy economy;
    static Enchantment MONEY;
    private static bCore instance;

    public static bCore getInstance() {
        return instance;
    }


    @Override
    public void onEnable() {
        instance = this;
        performNMSChecks();
        HookWithOthersPlugins();
        RegisterEvents();
        RegisterCommands();
        RegisterEnchantments();
        Data.createconfig();
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
                    }
                }
            }
        }).runTaskTimer(this, 20L, 20L);
        (new BukkitRunnable() {
            public void run() { //TODO Need do something with this
                if (getServer().getOnlinePlayers().size() > 0) {
                    int stone = Data.getRandomInt(1, 1000);
                    int coal = Data.getRandomInt(1, 1000);
                    int iron = Data.getRandomInt(1, 1000);
                    int gold = Data.getRandomInt(1, 1000);
                    int diamond = Data.getRandomInt(1, 1000);
                    int emerald = Data.getRandomInt(1, 1000);
                    int log = Data.getRandomInt(1, 1000);
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


    private void performNMSChecks() {
        final NMSAssistant nmsAssistant = new NMSAssistant();
        if (nmsAssistant.isVersionGreaterThan(12)) {
            getLogger().log(Level.INFO, "Loading data matching server version " + nmsAssistant.getNMSVersion().toString());
        } else {
            getLogger().warning("The server version is not suitable to load the plugin");
            getLogger().warning("Support version 1.13.x - 1.18.x");
            Bukkit.getServer().getPluginManager().disablePlugin(this);
        }
    }

    private void HookWithOthersPlugins() {
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            getLogger().log(Level.INFO, "Hooked with PlaceholderAPI v" + me.clip.placeholderapi.PlaceholderAPIPlugin.getInstance().getDescription().getVersion());
            new PlaceholderAPI().register();
        }
        if (getServer().getPluginManager().isPluginEnabled("Vault")) {
            getLogger().log(Level.INFO, "Hooked with Vault");
        }
        if (getServer().getPluginManager().isPluginEnabled("MythicMobs")) {
            getServer().getPluginManager().registerEvents(new MythicMobs(), this);
            getLogger().log(Level.INFO, "Hooked with MythicMobs v" + io.lumine.xikage.mythicmobs.MythicMobs.inst().getDescription().getVersion());
        }
    }

    private void RegisterEvents() {
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Fishing(), this);
        getServer().getPluginManager().registerEvents(new Eating(), this);
        getServer().getPluginManager().registerEvents(new Death(), this);
        getServer().getPluginManager().registerEvents(new Inventory(), this);
        getServer().getPluginManager().registerEvents(new Money(), this);
        getServer().getPluginManager().registerEvents(new Click(), this);
        getServer().getPluginManager().registerEvents(new Kill(), this);
        getServer().getPluginManager().registerEvents(new PvP(), this);
        getLogger().log(Level.INFO, "Registed all Events");
    }

    private void RegisterCommands() {
        Objects.requireNonNull(getCommand("bCore")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("storage")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("bCore")).setTabCompleter(new TabCommand());
        Objects.requireNonNull(getCommand("bank")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("bank")).setTabCompleter(new TabCommand());
        Objects.requireNonNull(getCommand("pvp")).setExecutor(new Commands());
        getLogger().log(Level.INFO, "Registed all Commands");
    }

    private void RegisterEnchantments() {
        /*
          Money Enchant
         */
        MONEY = new Money();
        registerEnchants(MONEY);
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
                getLogger().log(Level.INFO, "Registered Enchantments " + ench.getName());
            } catch (IllegalArgumentException e) {
                //if this is thrown it means the id is already taken.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
