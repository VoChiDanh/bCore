package net.danh;

import me.clip.placeholderapi.metrics.bukkit.Metrics;
import me.clip.placeholderapi.metrics.charts.SimplePie;
import net.danh.Commands.Commands;
import net.danh.Contest.Mining.Mining;
import net.danh.Events.*;
import net.danh.Hook.MythicMobs;
import net.danh.Hook.PlaceholderAPI;
import net.danh.Manager.Bitcoin;
import net.danh.Manager.Data;
import net.danh.Manager.DiscordWebhook;
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

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
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
        Metrics metrics = new Metrics(this, 14684);
        metrics.addCustomChart(new SimplePie("plugin_name", () -> getDescription().getName()));
        HookWithOthersPlugins();
        RegisterEvent();
        RegisterCommands();
        Data.createconfig();
        Bitcoin.setBitCoinBuyPrice(Bitcoin.getBitCoinBuyPriceData());
        Bitcoin.setBitCoinSellPrice(Bitcoin.getBitCoinBuyPrice() / 2);
        DiscordWebhook webhook = new DiscordWebhook(Data.getdiscordfile().getString("discord-bitcoin-webhook-url"));
        DiscordWebhook.EmbedObject eb = new DiscordWebhook.EmbedObject();
        for (int i = 1; i < 26; i++) {

            if (Data.getdiscordfile().getString("bitcoin-discord-fields." + i + ".title") == null) continue;

            eb.addField(Data.getdiscordfile().getString("bitcoin-discord-fields." + i + ".title"),
                    Objects.requireNonNull(Data.getdiscordfile().getString("bitcoin-discord-fields." + i + ".content"))
                            .replace("{giaban}", String.format("%,d", Bitcoin.getBitCoinBuyPrice()))
                            .replace("{giamua}", String.format("%,d", Bitcoin.getBitCoinSellPrice()))
                            .replace("{type}", "+")
                            .replace("{amount}", "0"),
                    Data.getdiscordfile().getBoolean("bitcoin-discord-fields." + i + ".inline"));
        }
        // Setting features of the embed
        eb.setColor(Color.decode(Objects.requireNonNull(Data.getdiscordfile().getString("discord-embed-bitcoin-discord-colour-increase"))));
        eb.setFooter(Data.getdiscordfile().getString("bitcoin-discord-embed-footer"), Data.getdiscordfile().getString("bitcoin-discord-embed-icon"));
        // Adding the embed to the webhook before sending off
        webhook.addEmbed(eb);

        AtomicBoolean error = new AtomicBoolean(false);

        Bukkit.getScheduler().runTaskAsynchronously(bCore.getInstance(), () -> {
            try {
                webhook.execute();
            } catch (Exception ex) {
                ex.printStackTrace();
                error.set(true);
            }
        });
        (new BukkitRunnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.setLevel(PlayerData.getLevel(p));
                }
            }
        }).runTaskTimer(this, 20L, 20L);
        (new BukkitRunnable() {
            public void run() {
                if (getServer().getOnlinePlayers().size() > 0) {
                    Mining.StartMining();
                }
            }
        }).runTaskTimer(this, 3600 * 20L, 3600 * 20L);
        (new BukkitRunnable() {
            public void run() {
                int a = Data.getRandomInt(1000, 100000);
                if (a >= 50000) {
                    Bitcoin.IncreasePrice(a);
                } else {
                    Bitcoin.DecreasePrice(a * 2);
                }
            }
        }).runTaskTimer(this, 1800 * 20L, 1800 * 20L);
    }

    @Override
    public void onDisable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            Data.saveall(p);
        }
        Data.getdatafile().set("BitCoinsBuyPrice.BC", Bitcoin.getBitCoinBuyPrice());
        getLogger().log(Level.INFO, "Saved player data");
        Data.savedata();
        Data.saveconfig();
        Data.savelanguage();
        Data.savebank();
        Data.savecmd();
        Data.savediscord();
        getLogger().log(Level.INFO, "Saved data");
    }


    private void performNMSChecks() {
        final NMSAssistant nmsAssistant = new NMSAssistant();
        if (nmsAssistant.isVersionGreaterThan(8)) {
            getLogger().log(Level.INFO, "Loading data matching server version " + nmsAssistant.getNMSVersion().toString());
        } else {
            getLogger().warning("The server version is not suitable to load the plugin");
            getLogger().warning("Support version 1.9.x - 1.18.x");
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

    @Contract(" -> new")
    private @NotNull ArrayList<Listener> initializeListeners() {
        return new ArrayList<Listener>(Arrays.asList(
                new Death(),
                new Kill(),
                new BlockBreak(),
                new Join(),
                new Quit(),
                new DamagedByEntity(),
                new DamagedByFishing(),
                new Teleport(),
                new Enchant(),
                new Interact()
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
        Objects.requireNonNull(getCommand("pvp")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("latxu")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("bitcoin")).setExecutor(new Commands());
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
