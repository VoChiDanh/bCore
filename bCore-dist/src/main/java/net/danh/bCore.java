package net.danh;

import me.clip.placeholderapi.metrics.bukkit.Metrics;
import me.clip.placeholderapi.metrics.charts.SimplePie;
import net.danh.Commands.Commands;
import net.danh.Contest.Mining.Mining;
import net.danh.Events.Death;
import net.danh.Events.Fishing;
import net.danh.Events.Kill;
import net.danh.Hook.MythicMobs;
import net.danh.Hook.PlaceholderAPI;
import net.danh.Manager.Data;
import net.danh.Manager.PlayerData;
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

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.logging.Level;

public final class bCore extends PonderBukkitPlugin implements Listener {


    public static Economy economy;
    private static bCore instance;

    public static DecimalFormat format = new DecimalFormat("##.##");
    public static Object serverInstance;
    public static Field tpsField;

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
        Metrics metrics = new Metrics(this, 14684);
        metrics.addCustomChart(new SimplePie("plugin_name", () -> getDescription().getName()));
        HookWithOthersPlugins();
        RegisterEvent();
        RegisterCommands();
        Data.createconfig();
        (new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (PlayerData.getBlocks(p) >= Data.getcmdfile().getInt("Commands.Mine.Amount")) {
                        PlayerData.setBlocks(p, 0);
                        getServer().dispatchCommand(Bukkit.getConsoleSender(), Objects.requireNonNull(Objects.requireNonNull(Data.getcmdfile().getString("Commands.Mine.Command"))
                                .replaceAll("%player%", p.getName())));
                        p.sendMessage(Data.convert(Data.getlanguagefile().getString("MINER_REWARD")));
                    }
                    p.setLevel(PlayerData.getLevel(p));
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
        Data.savecmd();
        Data.savestorage();
        getLogger().log(Level.INFO, "Saved data");
    }


    private void performNMSChecks() {
        final NMSAssistant nmsAssistant = new NMSAssistant();
        if (nmsAssistant.isVersionGreaterThan(7)) {
            getLogger().log(Level.INFO, "Loading data matching server version " + nmsAssistant.getNMSVersion().toString());
        } else {
            getLogger().warning("The server version is not suitable to load the plugin");
            getLogger().warning("Support version 1.8.x - 1.18.x");
            Bukkit.getServer().getPluginManager().disablePlugin(this);
        }
    }

    private void HookWithOthersPlugins() {
        if (!setupEconomy()) {
            getLogger().log(Level.SEVERE, "Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (getServer().getPluginManager().getPlugin("WorldGuard") != null) {
            getLogger().log(Level.INFO, "Successfully hooked with WorldGuard v" + Objects.requireNonNull(getServer().getPluginManager().getPlugin("WorldGuard")).getDescription().getVersion());
        } else {
            getLogger().log(Level.INFO, "Unsuccessfully hooked with WorldGuard");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if (getServer().getPluginManager().isPluginEnabled("Vault")) {
            getLogger().log(Level.INFO, "Successfully hooked with Vault v" + Objects.requireNonNull(getServer().getPluginManager().getPlugin("Vault")).getDescription().getVersion());
        }
        if (getServer().getPluginManager().getPlugin("PvP") != null && Objects.requireNonNull(getServer().getPluginManager().getPlugin("PvP")).getDescription().getAuthors().contains("VoChiDanh")) {
            getLogger().log(Level.INFO, "Successfully hooked with PvP v" + Objects.requireNonNull(getServer().getPluginManager().getPlugin("PvP")).getDescription().getVersion());
        }
        if (getServer().getPluginManager().getPlugin("MythicMobs") != null) {
            getLogger().log(Level.INFO, "Successfully hooked with MythicMobs v" + Objects.requireNonNull(getServer().getPluginManager().getPlugin("MythicMobs")).getDescription().getVersion());
        } else {
            getLogger().log(Level.INFO, "Unsuccessfully hooked with MythicMobs");
        }
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getLogger().log(Level.INFO, "Successfully hooked with PlaceholderAPI v" + Objects.requireNonNull(getServer().getPluginManager().getPlugin("PlaceholderAPI")).getDescription().getVersion());
            new PlaceholderAPI().register();
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
                new Fishing(),
                new Death(),
                new Kill()
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
        return new ArrayList<>(Collections.singletonList(
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
        Objects.requireNonNull(getCommand("bank")).setExecutor(new Commands());
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return true;
    }

}
