package net.danh;

import me.clip.placeholderapi.metrics.bukkit.Metrics;
import me.clip.placeholderapi.metrics.charts.SimplePie;
import net.Indyuce.mmoitems.MMOItems;
import net.danh.Commands.Bank;
import net.danh.Commands.PvP;
import net.danh.Commands.bCoreCMD;
import net.danh.Contest.Mining;
import net.danh.Events.*;
import net.danh.Hook.MythicMobs;
import net.danh.Hook.PlaceholderAPI;
import net.danh.Manager.Data;
import net.danh.Manager.PlayerData;
import net.danh.PlayerData.RPGPlayer;
import net.danh.dcore.DCore;
import net.danh.dcore.NMS.NMSAssistant;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.logging.Level;

import static net.danh.PlayerData.PlayerData.loadPlayerData;
import static net.danh.PlayerData.PlayerData.savePlayerData;

public class bCore extends JavaPlugin {


    public static Economy economy;
    private static bCore instance;


    public static bCore getInstance() {
        return instance;
    }


    @Override
    public void onLoad() {
        instance = this;
        performNMSChecks();
        MMOItems.plugin.setRPG(new RPGPlayer());
    }

    @Override
    public void onEnable() {
        Metrics metrics = new Metrics(this, 14684);
        metrics.addCustomChart(new SimplePie("plugin_name", () -> getDescription().getName()));
        HookWithOthersPlugins();
        RegisterEvent();
        RegisterCommands();
        Data.createconfig();
        DCore.RegisterDCore(bCore.getInstance());
        for (Player p : Bukkit.getOnlinePlayers()) {
            loadPlayerData(p);
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> Bukkit.getOnlinePlayers().forEach(this::syncXPBar), 1L, 1L);
        (new BukkitRunnable() {
            public void run() {
                if (getServer().getOnlinePlayers().size() > 0) {
                    Mining.StartMining();
                }
            }
        }).runTaskTimer(this, 1800 * 20L, 1800 * 20L);
    }

    @Override
    public void onDisable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            savePlayerData(p);
        }
        getLogger().log(Level.INFO, "Saved player data");
        Data.savedata();
        Data.saveconfig();
        Data.savelanguage();
        Data.savebank();
        Data.savecmd();
        getLogger().log(Level.INFO, "Saved data");
    }


    public void syncXPBar(Player p) {
        float xp = PlayerData.getXP(p);
        float level = PlayerData.getleveluptimes(p);
        float req = level * 10000;
        if (xp / req <= 1) {
            p.setExp(Float.parseFloat(String.valueOf(xp / req)));
            p.setLevel((int) level);
        }
    }

    private void performNMSChecks() {
        NMSAssistant nmsAssistant = new NMSAssistant();
        if (nmsAssistant.isVersionGreaterThan(8)) {
            getLogger().log(Level.INFO, "Loading data matching server version " + nmsAssistant.getNMSVersion().toString());
        } else {
            getLogger().warning("The server version is not suitable to load the plugin");
            getLogger().warning("Support version 1.9.x - 1.19.x");
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
        }
        if (getServer().getPluginManager().getPlugin("Vault") != null) {
            getLogger().log(Level.INFO, "Successfully hooked with Vault v" + Objects.requireNonNull(getServer().getPluginManager().getPlugin("Vault")).getDescription().getVersion());
        }
        if (getServer().getPluginManager().getPlugin("MythicMobs") != null) {
            getLogger().log(Level.INFO, "Successfully hooked with MythicMobs v" + Objects.requireNonNull(getServer().getPluginManager().getPlugin("MythicMobs")).getDescription().getVersion());
        }
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getLogger().log(Level.INFO, "Successfully hooked with PlaceholderAPI v" + Objects.requireNonNull(getServer().getPluginManager().getPlugin("PlaceholderAPI")).getDescription().getVersion());
            new PlaceholderAPI().register();
        }
    }


    private void RegisterEvent() {
        registerEventHandlers();
        if (getServer().getPluginManager().getPlugin("MythicMobs") != null) {
            registerMythic();
        }
    }

    private void registerEventHandlers() {
        getServer().getPluginManager().registerEvents(new Death(), this);
        getServer().getPluginManager().registerEvents(new Kill(), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Quit(), this);
        getServer().getPluginManager().registerEvents(new DamagedByEntity(), this);
        getServer().getPluginManager().registerEvents(new DamagedByFishing(), this);
        getServer().getPluginManager().registerEvents(new Teleport(), this);
        getServer().getPluginManager().registerEvents(new Enchant(), this);
        getServer().getPluginManager().registerEvents(new Bow(), this);
    }

    private void registerMythic() {
        getServer().getPluginManager().registerEvents(new MythicMobs(), this);
    }

    private void RegisterCommands() {
        new bCoreCMD(this);
        new PvP(this);
        new Bank(this);
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
