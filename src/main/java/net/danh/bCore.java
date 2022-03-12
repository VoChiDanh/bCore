package net.danh;

import net.danh.Commands.Commands;
import net.danh.Commands.TabCommand;
import net.danh.Events.*;
import net.danh.Hook.MythicMobs;
import net.danh.Hook.PlaceholderAPI;
import net.danh.Manager.Data;
import net.danh.Manager.PlayerData;
import net.danh.Manager.Power;
import net.danh.Manager.Weight;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import preponderous.ponder.minecraft.bukkit.abs.PonderBukkitPlugin;
import preponderous.ponder.minecraft.bukkit.nms.NMSAssistant;
import preponderous.ponder.minecraft.bukkit.tools.EventHandlerRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public final class bCore extends PonderBukkitPlugin implements Listener {


    public static Economy economy;
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
        RegisterEvent();
        RegisterCommands();
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
            return;
        }
    }

    private void HookWithOthersPlugins() {
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            getLogger().log(Level.INFO, "Successfully hooked with PlaceholderAPI");
            new PlaceholderAPI().register();
        } else {
            getLogger().log(Level.INFO, "Unsuccessfully hooked with PlaceholderAPI");
        }
        if (getServer().getPluginManager().isPluginEnabled("Vault")) {
            getLogger().log(Level.INFO, "Successfully hooked with Vault");
        } else {
            getLogger().log(Level.INFO, "Unsuccessfully hooked with Vault");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (getServer().getPluginManager().isPluginEnabled("MythicMobs")) {
            getLogger().log(Level.INFO, "Successfully hooked with MythicMobs");
        } else {
            getLogger().log(Level.INFO, "Unsuccessfully hooked with MythicMobs");
        }
    }

    private void RegisterEvent() {
        registerEventHandlers();
        if (getServer().getPluginManager().isPluginEnabled("MythicMobs")) {
            registerMythic();
        }

    }

    @Contract(" -> new")
    private @NotNull ArrayList<Listener> initializeListeners() {
        return new ArrayList<>(Arrays.asList(
                new BlockBreak(),
                new Join(),
                new Fishing(),
                new Moving(),
                new Death(),
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

    @Contract(" -> new")
    private @NotNull ArrayList<Listener> MythicListeners() {
        return new ArrayList<>(List.of(
                new MythicMobs()
        ));
    }

    /**
     * Registers the event handlers of the plugin using Ponder.
     */
    private void registerMythic() {
        ArrayList<Listener> mythic = MythicListeners();
        EventHandlerRegistry eventHandlerRegistry = new EventHandlerRegistry();
        eventHandlerRegistry.registerEventHandlers(mythic, this);
    }

    private void RegisterCommands() {
        Objects.requireNonNull(getCommand("bCore")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("bCore")).setTabCompleter(new TabCommand());
        Objects.requireNonNull(getCommand("bank")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("bank")).setTabCompleter(new TabCommand());
        Objects.requireNonNull(getCommand("pvp")).setExecutor(new Commands());
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null)
            economy = economyProvider.getProvider();
        return (economy != null);
    }

}
