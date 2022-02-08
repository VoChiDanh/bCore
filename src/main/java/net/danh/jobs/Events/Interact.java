package net.danh.jobs.Events;

import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.Cooldown;
import net.danh.jobs.Manager.Files;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;

public class Interact implements Listener {

    @EventHandler
    public void onRev(PlayerInteractAtEntityEvent e) {
        Player bs = e.getPlayer();
        String NEED_MORE_POWER = Files.getInstance().getlanguage().getString("NEED_MORE_POWER");
        String PREFIX = Files.getInstance().getlanguage().getString("PREFIX");
        String LUCKY = Files.getInstance().getlanguage().getString("LUCKY");
        Integer POWER = Files.getInstance().getconfig().getInt("Lucky_Power");
        Integer XP = Files.getInstance().getconfig().getInt("Lucky_XP");
        Integer LEVELUP = Files.getInstance().getleveluptimes(e.getPlayer());
        double systemchance = Math.random() * 100.0D;
        double playerchance = Files.getInstance().getLuckyChance(e.getPlayer());
        if (Files.getInstance().getJobs(bs).equals("DOCTOR")) {
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Check Jobs");
            }
            if (e.getRightClicked().getType().equals(EntityType.PLAYER)) {
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "He/She is player");
                }
                if (Files.getInstance().getPower(e.getPlayer()) <= 0) {
                    e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + NEED_MORE_POWER));
                    e.setCancelled(true);
                    return;
                } else {
                    String name = e.getRightClicked().getName();
                    if (name == null) {
                        return;
                    }
                    if (Bukkit.getPlayerExact(name) == null) {
                        return;
                    }
                    Player bn = Objects.requireNonNull(Bukkit.getPlayerExact(name)).getPlayer();
                    int timeLeft = Cooldown.getInstance().getCooldown(Objects.requireNonNull(bn).getUniqueId());
                    if (timeLeft == 0) {
                        Cooldown.getInstance().setCooldown(bn.getUniqueId(), Cooldown.DEFAULT_COOLDOWN);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                int timeLeft = Cooldown.getInstance().getCooldown(bn.getUniqueId());
                                Cooldown.getInstance().setCooldown(bn.getUniqueId(), --timeLeft);
                                if (timeLeft == 0) {
                                    this.cancel();
                                }
                            }
                        }.runTaskTimer(Jobs.getInstance(), 20, 20);

                    } else {
                        //Hasn't expired yet, shows how many seconds left until it does
                        bn.sendMessage(Files.getInstance().convert("&cBạn cần chờ &6" + timeLeft + "&6s &cđể được cứu chữa!"));
                        bs.sendMessage(Files.getInstance().convert("&cBệnh nhân sẽ được chữa trị sau &6" + timeLeft + "&6s"));
                        return;
                    }
                    if (bn instanceof Player) {
                        if (Objects.requireNonNull(bn.getPlayer()).getHealth() <= (bn.getPlayer().getMaxHealth() / 2)) {
                            if (Files.getInstance().getconfig().getBoolean("debug")) {
                                Jobs.getInstance().getLogger().log(Level.INFO, "Less more than 50%");
                            }
                            ItemStack items = new ItemStack(Material.valueOf(Files.getInstance().getitems().getString("doctor_items.MATERIAL")), Files.getInstance().getitems().getInt("doctor_items.AMOUNT"));
                            ItemMeta meta = items.getItemMeta();

                            Objects.requireNonNull(meta).setDisplayName(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("doctor_items.DISPLAY_NAME"))));
                            ArrayList<String> lore = new ArrayList<String>();
                            lore.add(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("doctor_items.LORE1"))));
                            lore.add(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("doctor_items.LORE2"))));
                            meta.setLore(lore);
                            items.setItemMeta(meta);
                            Material item = items.getType();

                            if (bs.getItemInHand().getType() == null) {
                                return;
                            }
                            if (bs.getItemInHand().getType() != null && bs.getItemInHand().hasItemMeta() && bs.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Files.getInstance().convert("doctor_items.DISPLAY_NAME"))) {

                                if (Files.getInstance().getconfig().getBoolean("debug")) {
                                    Jobs.getInstance().getLogger().log(Level.INFO, "True Medicine");
                                }
                                bs.getItemInHand().setAmount(bs.getItemInHand().getAmount() - 1);
                                bs.updateInventory();
                                bn.setHealth(bn.getMaxHealth());
                                if (Files.getInstance().getconfig().getBoolean("debug")) {
                                    Jobs.getInstance().getLogger().log(Level.INFO, "Remove Medicine");
                                }
                                if (Files.getInstance().getconfig().getBoolean("debug")) {
                                    Jobs.getInstance().getLogger().log(Level.INFO, "Heal");

                                }
                                if (playerchance >= systemchance) {
                                    Files.getInstance().addXP(e.getPlayer(), 2 + Files.getInstance().getleveluptimes(e.getPlayer()));
                                    Files.getInstance().addPower(e.getPlayer(), 5 + Files.getInstance().getleveluptimes(e.getPlayer()));
                                    e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + LUCKY
                                            .replaceAll("%power%", String.valueOf(POWER + LEVELUP))
                                            .replaceAll("%xp%", String.valueOf(XP + LEVELUP))));
                                    return;
                                }
                                Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp") + Files.getInstance().getleveluptimes(e.getPlayer()));
                                EconomyResponse err = Jobs.economy.depositPlayer(bs.getName(), 600);
                                Files.getInstance().removePower(e.getPlayer(), 1);
                            }
                        }
                    }
                }
            }
        }
    }
}

