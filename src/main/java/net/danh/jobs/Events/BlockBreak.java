package net.danh.jobs.Events;

import com.cryptomorin.xseries.XMaterial;
import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.logging.Level;

public class BlockBreak implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();
        double systemchance = Math.random() * 100.0D;
        double playerchance = Lucky.getInstance().getLuckyChance(e.getPlayer());
        String NEED_MORE_POWER = Data.getInstance().getlanguage().getString("NEED_MORE_POWER");
        String PREFIX = Data.getInstance().getlanguage().getString("PREFIX");
        String LUCKY = Data.getInstance().getlanguage().getString("LUCKY");
        Integer POWER = Data.getInstance().getconfig().getInt("Lucky_Power");
        Integer XP = Data.getInstance().getconfig().getInt("Lucky_XP");
        Integer LEVELUP = Gang.getInstance().getleveluptimes(e.getPlayer());
        String NOT_CORRECT_ITEMS = Data.getInstance().getlanguage().getString("NOT_CORRECT_ITEMS");
        String FULL_STORAGE = Data.getInstance().getlanguage().getString("FULL_STORAGE");
        String blocks = e.getBlock().getType().toString();
        int max = 0;
        int min = 0;
        if (e.getPlayer().hasPermission("jobs.admin")) {
            e.setCancelled(false);
            return;
        }
        if (b.getType() == XMaterial.COAL_ORE.parseMaterial()
                || b.getType() == XMaterial.IRON_ORE.parseMaterial()
                || b.getType() == XMaterial.GOLD_ORE.parseMaterial()
                || b.getType() == XMaterial.DIAMOND_ORE.parseMaterial()
                || b.getType() == XMaterial.EMERALD_ORE.parseMaterial()
                || b.getType() == XMaterial.COAL_BLOCK.parseMaterial()
                || b.getType() == XMaterial.IRON_BLOCK.parseMaterial()
                || b.getType() == XMaterial.GOLD_BLOCK.parseMaterial()
                || b.getType() == XMaterial.DIAMOND_BLOCK.parseMaterial()
                || b.getType() == XMaterial.EMERALD_BLOCK.parseMaterial()) {

            if (p.getItemInHand().getType() == XMaterial.STONE_PICKAXE.parseMaterial()
                    || p.getItemInHand().getType() == XMaterial.IRON_PICKAXE.parseMaterial()
                    || p.getItemInHand().getType() == XMaterial.GOLDEN_PICKAXE.parseMaterial()
                    || p.getItemInHand().getType() == XMaterial.DIAMOND_PICKAXE.parseMaterial()
                    || p.getItemInHand().getType() == XMaterial.NETHERITE_PICKAXE.parseMaterial()) {

                if (Data.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Check pickaxe");
                }

                if (Power.getInstance().getPower(e.getPlayer()) <= Data.getInstance().getconfig().getInt("minimum_power")) {
                    e.getPlayer().sendMessage(Data.getInstance().convert(PREFIX + NEED_MORE_POWER));
                    e.setCancelled(true);
                } else {
                    if (Data.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Check power");
                    }
                    e.setDropItems(false);
                    e.getBlock().getDrops().clear();
                    if (!e.isDropItems()) {
                        if (Data.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "No drop");
                        }
                        for (String getBlockType : Data.getInstance().getconfig().getConfigurationSection("Blocks.").getKeys(false)) {
                            if (blocks.equalsIgnoreCase(getBlockType)) {
                                max = Data.getInstance().getconfig().getInt("Blocks." + blocks + ".max");
                                min = Data.getInstance().getconfig().getInt("Blocks." + blocks + ".min");
                                break;
                            }
                        }
                        if (Data.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Correct block");
                        }
                        int kg = Data.getInstance().getRandomNumber(min, max);
                        if (Weight.getInstance().getWeight(e.getPlayer()) + kg <= Weight.getInstance().getMaxWeight(e.getPlayer())) {

                            if (Data.getInstance().getconfig().getBoolean("debug")) {
                                Jobs.getInstance().getLogger().log(Level.INFO, "Enough space");
                            }
                            if (kg != 0) {
                                Weight.getInstance().addWeight(e.getPlayer(), kg);
                                if (e.getBlock().getType() == XMaterial.COAL_ORE.parseMaterial()
                                        || e.getBlock().getType() == XMaterial.COAL_BLOCK.parseMaterial()) {
                                    Storage.getInstance().addStorage(e.getPlayer(), "COAL".toUpperCase(), kg);
                                } else if (e.getBlock().getType() == XMaterial.IRON_ORE.parseMaterial()
                                        || e.getBlock().getType() == XMaterial.IRON_BLOCK.parseMaterial()) {
                                    Storage.getInstance().addStorage(e.getPlayer(), "IRON".toUpperCase(), kg);
                                } else if (e.getBlock().getType() == XMaterial.GOLD_ORE.parseMaterial()
                                        || e.getBlock().getType() == XMaterial.GOLD_BLOCK.parseMaterial()) {
                                    Storage.getInstance().addStorage(e.getPlayer(), "GOLD".toUpperCase(), kg);
                                } else if (e.getBlock().getType() == XMaterial.DIAMOND_ORE.parseMaterial()
                                        || e.getBlock().getType() == XMaterial.DIAMOND_BLOCK.parseMaterial()) {
                                    Storage.getInstance().addStorage(e.getPlayer(), "DIAMOND".toUpperCase(), kg);
                                } else if (e.getBlock().getType() == XMaterial.EMERALD_ORE.parseMaterial()
                                        || e.getBlock().getType() == XMaterial.EMERALD_BLOCK.parseMaterial()) {
                                    Storage.getInstance().addStorage(e.getPlayer(), "EMERALD".toUpperCase(), kg);
                                }
                            }
                        } else {
                            e.getPlayer().sendMessage(Data.getInstance().convert(PREFIX + FULL_STORAGE));
                            if (Data.getInstance().getconfig().getBoolean("debug")) {
                                Jobs.getInstance().getLogger().log(Level.INFO, "Not Enough space");
                            }
                            e.setCancelled(true);
                            return;
                        }
                    }
                    if (playerchance >= systemchance) {
                        if (Gang.getInstance().getGang(e.getPlayer()) != null) {
                            PlayerData.getInstance().addXP(e.getPlayer(), 2 + Gang.getInstance().getleveluptimes(e.getPlayer()));
                        }
                        Power.getInstance().addPower(e.getPlayer(), 5 + Gang.getInstance().getleveluptimes(e.getPlayer()));
                        e.getPlayer().sendMessage(Data.getInstance().convert(PREFIX + LUCKY
                                .replaceAll("%power%", String.valueOf(POWER + LEVELUP))
                                .replaceAll("%xp%", String.valueOf(XP + LEVELUP))));
                        return;
                    }
                    if (Gang.getInstance().getGang(e.getPlayer()) != null) {
                        PlayerData.getInstance().addXP(e.getPlayer(), Data.getInstance().getconfig().getInt("xp") + Gang.getInstance().getleveluptimes(e.getPlayer()));
                    }
                    Power.getInstance().removePower(p, 1);
                    if (Data.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
                    }
                    return;
                }

            } else {
                p.sendMessage(Data.getInstance().convert(PREFIX + NOT_CORRECT_ITEMS));
                e.setCancelled(true);
                return;
            }
        }
        if (b.getType() == XMaterial.ACACIA_LOG.parseMaterial()
                || b.getType() == XMaterial.BIRCH_LOG.parseMaterial()
                || b.getType() == XMaterial.DARK_OAK_LOG.parseMaterial()
                || b.getType() == XMaterial.JUNGLE_LOG.parseMaterial()
                || b.getType() == XMaterial.OAK_LOG.parseMaterial()
                || b.getType() == XMaterial.SPRUCE_LOG.parseMaterial()) {

            if (p.getItemInHand().getType() == XMaterial.STONE_AXE.parseMaterial()
                    || p.getItemInHand().getType() == XMaterial.IRON_AXE.parseMaterial()
                    || p.getItemInHand().getType() == XMaterial.GOLDEN_AXE.parseMaterial()
                    || p.getItemInHand().getType() == XMaterial.DIAMOND_AXE.parseMaterial()
                    || p.getItemInHand().getType() == XMaterial.NETHERITE_AXE.parseMaterial()) {
                if (Data.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Check block");
                }

                if (Power.getInstance().getPower(e.getPlayer()) <= Data.getInstance().getconfig().getInt("minimum_power")) {
                    e.getPlayer().sendMessage(Data.getInstance().convert(PREFIX + NEED_MORE_POWER));
                    e.setCancelled(true);
                } else {
                    if (Data.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Check power");
                    }
                    e.setDropItems(false);
                    e.getBlock().getDrops().clear();
                    if (!e.isDropItems()) {
                        if (Data.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "No drop");
                        }
                        for (String getBlockType : Data.getInstance().getconfig().getConfigurationSection("Blocks.").getKeys(false)) {
                            if (blocks.equalsIgnoreCase(getBlockType)) {
                                max = Data.getInstance().getconfig().getInt("Blocks." + blocks + ".max");
                                min = Data.getInstance().getconfig().getInt("Blocks." + blocks + ".min");
                                break;
                            }
                        }
                        if (Data.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Correct block");
                        }
                        int kg = Data.getInstance().getRandomNumber(min, max);
                        if (Weight.getInstance().getWeight(e.getPlayer()) + kg <= Weight.getInstance().getMaxWeight(e.getPlayer())) {

                            if (Data.getInstance().getconfig().getBoolean("debug")) {
                                Jobs.getInstance().getLogger().log(Level.INFO, "Enough space");
                            }
                            if (kg != 0) {
                                Weight.getInstance().addWeight(e.getPlayer(), kg);
                                if (b.getType() == XMaterial.ACACIA_LOG.parseMaterial()
                                        || b.getType() == XMaterial.BIRCH_LOG.parseMaterial()
                                        || b.getType() == XMaterial.DARK_OAK_LOG.parseMaterial()
                                        || b.getType() == XMaterial.JUNGLE_LOG.parseMaterial()
                                        || b.getType() == XMaterial.OAK_LOG.parseMaterial()
                                        || b.getType() == XMaterial.SPRUCE_LOG.parseMaterial()) {
                                    Storage.getInstance().addStorage(e.getPlayer(), "LOG".toUpperCase(), kg);
                                }
                            }
                        } else {
                            e.getPlayer().sendMessage(Data.getInstance().convert(PREFIX + FULL_STORAGE));
                            if (Data.getInstance().getconfig().getBoolean("debug")) {
                                Jobs.getInstance().getLogger().log(Level.INFO, "Not Enough space");
                            }
                            e.setCancelled(true);
                            return;
                        }
                    }
                    if (playerchance >= systemchance) {
                        if (Gang.getInstance().getGang(e.getPlayer()) != null) {
                            PlayerData.getInstance().addXP(e.getPlayer(), 2 + Gang.getInstance().getleveluptimes(e.getPlayer()));
                        }
                        Power.getInstance().addPower(e.getPlayer(), 5 + Gang.getInstance().getleveluptimes(e.getPlayer()));
                        e.getPlayer().sendMessage(Data.getInstance().convert(PREFIX + LUCKY
                                .replaceAll("%power%", String.valueOf(POWER + LEVELUP))
                                .replaceAll("%xp%", String.valueOf(XP + LEVELUP))));
                        return;
                    }
                    if (Gang.getInstance().getGang(e.getPlayer()) != null) {
                        PlayerData.getInstance().addXP(e.getPlayer(), Data.getInstance().getconfig().getInt("xp") + Gang.getInstance().getleveluptimes(e.getPlayer()));
                    }
                    Power.getInstance().removePower(p, 1);
                    if (Data.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
                    }
                    return;
                }

            } else {
                p.sendMessage(Data.getInstance().convert(PREFIX + NOT_CORRECT_ITEMS));
                e.setCancelled(true);
                return;
            }
        }
    }
}