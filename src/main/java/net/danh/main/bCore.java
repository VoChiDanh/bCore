package net.danh.main;

import net.danh.main.Commands.Commands;
import net.danh.main.Commands.TabCommand;
import net.danh.main.CustomEnchantment.Money;
import net.danh.main.Events.*;
import net.danh.main.Hook.MythicMobs;
import net.danh.main.Hook.PlaceholderAPI;
import net.danh.main.Manager.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scheduler.BukkitRunnable;
import preponderous.ponder.minecraft.bukkit.abs.PonderBukkitPlugin;
import preponderous.ponder.minecraft.bukkit.nms.NMSAssistant;
import preponderous.ponder.minecraft.bukkit.tools.EventHandlerRegistry;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;

public final class bCore extends PonderBukkitPlugin implements Listener {


    public static Economy economy;
    static Enchantment MONEY;
    private static bCore instance;

    public static bCore getInstance() {
        return instance;
    }


    @Override
    public void onLoad() {
        performNMSChecks();
    }

    @Override
    public void onEnable() {
        instance = this;
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
                        if (Weight.getWeight(p) == Weight.getMaxWeight(p)) {
                            p.setWalkSpeed(0F);
                            p.setFlySpeed(0F);
                        } else {
                            p.setWalkSpeed(0.2F);
                            p.setFlySpeed(0.2F);
                        }
                    }
                }
            }
        }).runTaskTimer(this, 20L, 20L);
    }

    @Override
    public void onDisable() {
        Data.savedata();
        Data.saveconfig();
        Data.savelanguage();
        Data.savegui();
        Data.saveitems();
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
            new PlaceholderAPI().register();
        }
        getLogger().info(Data.convert("&a--------- &ebCore v" + getDescription().getVersion() + "&a ---------"));
        getLogger().info("");
        getLogger().info(Data.convert("&3Hook With Other Plugins"));
        getLogger().info("");
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            getLogger().info(Data.convert("&a✔ PlaceholderAPI"));
        } else {
            getLogger().info(Data.convert("&7✘ PlaceholderAPI"));
        }
        if (getServer().getPluginManager().isPluginEnabled("Vault")) {
            getLogger().info(Data.convert("&a✔ Vault"));
        } else {
            getLogger().info(Data.convert("&7✘ Vault"));
        }
        if (getServer().getPluginManager().isPluginEnabled("MythicMobs")) {
            getLogger().info(Data.convert("&a✔ MythicMobs"));
        } else {
            getLogger().info(Data.convert("&7✘ MythicMobs"));
        }
        getLogger().info("");
    }

    private void RegisterEvents() {
        getLogger().info("");
        getLogger().info(Data.convert("&3Register Event"));
        getLogger().info("");
        registerEventHandlers();
        getLogger().info(Data.convert("&a✔ BlockBreak Event"));
        getLogger().info(Data.convert("&a✔ Join Event"));
        getLogger().info(Data.convert("&a✔ Fishing Event"));
        getLogger().info(Data.convert("&a✔ Eating Event"));
        getLogger().info(Data.convert("&a✔ Death Event"));
        getLogger().info(Data.convert("&a✔ Inventory Event"));
        getLogger().info(Data.convert("&a✔ Money Event"));
        getLogger().info(Data.convert("&a✔ Click Event"));
        getLogger().info(Data.convert("&a✔ Kill Event"));
        getLogger().info(Data.convert("&a✔ PvP Event"));
        if (getServer().getPluginManager().isPluginEnabled("MythicMobs")) {
            getServer().getPluginManager().registerEvents(new MythicMobs(), this);
            getLogger().info(Data.convert("&a✔ MythicMobs Event"));
        } else {
            getLogger().info(Data.convert("&7✘ MythicMobs Event"));
        }
        getLogger().info("");

    }

    private ArrayList<Listener> initializeListeners() {
        return new ArrayList<>(Arrays.asList(
                new BlockBreak(),
                new Join(),
                new Fishing(),
                new Eating(),
                new Death(),
                new Inventory(),
                new Money(),
                new Click(),
                new Kill(),
                new PvP()
        ));
    }

    /**
     * Registers the event handlers of the plugin using Ponder.
     */
    private void registerEventHandlers() {
        ArrayList<Listener> listeners = initializeListeners();
        EventHandlerRegistry eventHandlerRegistry = new EventHandlerRegistry();
        eventHandlerRegistry.registerEventHandlers(listeners, this);
    }

    private void RegisterCommands() {
        getLogger().info("");
        getLogger().info(Data.convert("&3Register Commands"));
        getLogger().info("");
        Objects.requireNonNull(getCommand("bCore")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("bCore")).setTabCompleter(new TabCommand());
        getLogger().info(Data.convert("&a✔ /bCore"));
        Objects.requireNonNull(getCommand("bank")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("bank")).setTabCompleter(new TabCommand());
        getLogger().info(Data.convert("&a✔ /Bank"));
        Objects.requireNonNull(getCommand("storage")).setExecutor(new Commands());
        getLogger().info(Data.convert("&a✔ /Storage"));
        Objects.requireNonNull(getCommand("pvp")).setExecutor(new Commands());
        getLogger().info(Data.convert("&a✔ /PvP"));
        getLogger().info("");
    }

    private void RegisterEnchantments() {
        getLogger().info("");
        getLogger().info(Data.convert("&3Register Enchantments"));
        getLogger().info("");
        MONEY = new Money();
        registerEnchants(MONEY);
        getLogger().info(Data.convert("&a✔ Enchantment Money"));
        getLogger().info("");
        getLogger().info(Data.convert("&a--------- &ebCore v" + getDescription().getVersion() + "&a ---------"));
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
            } catch (IllegalArgumentException e) {
                //if this is thrown it means the id is already taken.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
