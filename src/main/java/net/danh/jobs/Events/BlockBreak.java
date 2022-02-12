package net.danh.jobs.Events;

import com.cryptomorin.xseries.XMaterial;
import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.Files;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
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
        double playerchance = Files.getInstance().getLuckyChance(e.getPlayer());
        String NEED_MORE_POWER = Files.getInstance().getlanguage().getString("NEED_MORE_POWER");
        String PREFIX = Files.getInstance().getlanguage().getString("PREFIX");
        String LUCKY = Files.getInstance().getlanguage().getString("LUCKY");
        Integer POWER = Files.getInstance().getconfig().getInt("Lucky_Power");
        Integer XP = Files.getInstance().getconfig().getInt("Lucky_XP");
        Integer LEVELUP = Files.getInstance().getleveluptimes(e.getPlayer());
        String NOT_CORRECT_JOBS = Files.getInstance().getlanguage().getString("NOT_CORRECT_JOBS");
        String NOT_CORRECT_ITEMS = Files.getInstance().getlanguage().getString("NOT_CORRECT_ITEMS");
        String FULL_STORAGE = Files.getInstance().getlanguage().getString("FULL_STORAGE");
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
                || b.getType() == XMaterial.EMERALD_ORE.parseMaterial()) {

            if (p.getItemInHand().getType() == XMaterial.STONE_PICKAXE.parseMaterial()
                    || p.getItemInHand().getType() == XMaterial.IRON_PICKAXE.parseMaterial()
                    || p.getItemInHand().getType() == XMaterial.GOLDEN_PICKAXE.parseMaterial()
                    || p.getItemInHand().getType() == XMaterial.DIAMOND_PICKAXE.parseMaterial()
                    || p.getItemInHand().getType() == XMaterial.NETHERITE_PICKAXE.parseMaterial()) {

                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Check pickaxe");
                }

                if (Files.getInstance().getJobs(p).equals("MINER")) {

                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Check Jobs");
                    }

                    if (Files.getInstance().getPower(e.getPlayer()) <= Files.getInstance().getconfig().getInt("minimum_power")) {
                        e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + NEED_MORE_POWER));
                        e.setCancelled(true);
                    } else {
                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Check power");
                        }
                        if (playerchance >= systemchance) {
                            if (Files.getInstance().getGang(e.getPlayer()) != null) {
                                Files.getInstance().addXP(e.getPlayer(), 2 + Files.getInstance().getleveluptimes(e.getPlayer()));
                            }
                            Files.getInstance().addPower(e.getPlayer(), 5 + Files.getInstance().getleveluptimes(e.getPlayer()));
                            e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + LUCKY
                                    .replaceAll("%power%", String.valueOf(POWER + LEVELUP))
                                    .replaceAll("%xp%", String.valueOf(XP + LEVELUP))));
                            return;
                        }
                        if (Files.getInstance().getGang(e.getPlayer()) != null) {
                            Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp") + Files.getInstance().getleveluptimes(e.getPlayer()));
                        }
                        Files.getInstance().removePower(p, 1);
                        e.setDropItems(false);
                        e.getBlock().getDrops().clear();
                        if (!e.isDropItems()) {
                            if (Files.getInstance().getconfig().getBoolean("debug")) {
                                Jobs.getInstance().getLogger().log(Level.INFO, "No drop");
                            }
                            for (String getBlockType : Files.getInstance().getconfig().getConfigurationSection("Blocks.").getKeys(false)) {
                                if (blocks.equalsIgnoreCase(getBlockType)) {
                                    max = Files.getInstance().getconfig().getInt("Blocks." + blocks + ".max");
                                    min = Files.getInstance().getconfig().getInt("Blocks." + blocks + ".min");
                                    break;
                                }
                            }
                            if (Files.getInstance().getconfig().getBoolean("debug")) {
                                Jobs.getInstance().getLogger().log(Level.INFO, "Correct block");
                            }
                            Integer kg = Files.getInstance().getRandomNumber(min, max);
                            if (Files.getInstance().getWeight(e.getPlayer()) + kg <= Files.getInstance().getMaxWeight(e.getPlayer())) {

                                if (Files.getInstance().getconfig().getBoolean("debug")) {
                                    Jobs.getInstance().getLogger().log(Level.INFO, "Enough space");
                                }
                                if (kg != 0) {
                                    Files.getInstance().addWeight(e.getPlayer(), kg);
                                    Files.getInstance().addStorage(e.getPlayer(), blocks, kg);
                                }
                            } else {
                                e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + FULL_STORAGE));
                                if (Files.getInstance().getconfig().getBoolean("debug")) {
                                    Jobs.getInstance().getLogger().log(Level.INFO, "Not Enough space");
                                }
                                e.setCancelled(true);
                            }
                        }
                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
                        }
                        return;
                    }
                } else {
                    p.sendMessage(Files.getInstance().convert(PREFIX + NOT_CORRECT_JOBS));
                    e.setCancelled(true);
                    return;
                }
            } else {
                p.sendMessage(Files.getInstance().convert(PREFIX + NOT_CORRECT_ITEMS));
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
                if (Files.getInstance().getconfig().getBoolean("debug")) {
                    Jobs.getInstance().getLogger().log(Level.INFO, "Check block");
                }

                if (Files.getInstance().getJobs(p).equals("WOODCUTTER")) {
                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Check Jobs");
                    }

                    if (Files.getInstance().getPower(e.getPlayer()) <= Files.getInstance().getconfig().getInt("minimum_power")) {
                        e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + NEED_MORE_POWER));
                        e.setCancelled(true);
                    } else {
                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Check power");
                        }
                        if (playerchance >= systemchance) {
                            if (Files.getInstance().getGang(e.getPlayer()) != null) {
                                Files.getInstance().addXP(e.getPlayer(), 2 + Files.getInstance().getleveluptimes(e.getPlayer()));
                            }
                            Files.getInstance().addPower(e.getPlayer(), 5 + Files.getInstance().getleveluptimes(e.getPlayer()));
                            e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + LUCKY
                                    .replaceAll("%power%", String.valueOf(POWER + LEVELUP))
                                    .replaceAll("%xp%", String.valueOf(XP + LEVELUP))));
                            return;
                        }
                        if (Files.getInstance().getGang(e.getPlayer()) != null) {
                            Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp") + Files.getInstance().getleveluptimes(e.getPlayer()));
                        }
                        Files.getInstance().removePower(p, 1);
                        e.setDropItems(false);
                        e.getBlock().getDrops().clear();
                        if (!e.isDropItems()) {
                            if (Files.getInstance().getconfig().getBoolean("debug")) {
                                Jobs.getInstance().getLogger().log(Level.INFO, "No drop");
                            }
                            for (String getBlockType : Files.getInstance().getconfig().getConfigurationSection("Blocks.").getKeys(false)) {
                                if (blocks.equalsIgnoreCase(getBlockType)) {
                                    max = Files.getInstance().getconfig().getInt("Blocks." + blocks + ".max");
                                    min = Files.getInstance().getconfig().getInt("Blocks." + blocks + ".min");
                                    break;
                                }
                            }
                            if (Files.getInstance().getconfig().getBoolean("debug")) {
                                Jobs.getInstance().getLogger().log(Level.INFO, "Correct block");
                            }
                            Integer kg = Files.getInstance().getRandomNumber(min, max);
                            if (Files.getInstance().getWeight(e.getPlayer()) + kg <= Files.getInstance().getMaxWeight(e.getPlayer())) {

                                if (Files.getInstance().getconfig().getBoolean("debug")) {
                                    Jobs.getInstance().getLogger().log(Level.INFO, "Enough space");
                                }
                                if (kg != 0) {
                                    Files.getInstance().addWeight(e.getPlayer(), kg);
                                    Files.getInstance().addStorage(e.getPlayer(), blocks, kg);
                                }
                            } else {
                                e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + FULL_STORAGE));
                                if (Files.getInstance().getconfig().getBoolean("debug")) {
                                    Jobs.getInstance().getLogger().log(Level.INFO, "Not Enough space");
                                }
                                e.setCancelled(true);
                            }
                        }
                        if (Files.getInstance().getconfig().getBoolean("debug")) {
                            Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
                        }
                        return;
                    }
                } else {
                    p.sendMessage(Files.getInstance().convert(PREFIX + NOT_CORRECT_JOBS));
                    e.setCancelled(true);
                    return;
                }
            } else {
                p.sendMessage(Files.getInstance().convert(PREFIX + NOT_CORRECT_ITEMS));
                e.setCancelled(true);
                return;
            }
        }
        if (p.getItemInHand().getType() == XMaterial.WOODEN_HOE.parseMaterial()
                || p.getItemInHand().getType() == XMaterial.IRON_HOE.parseMaterial()
                || p.getItemInHand().getType() == XMaterial.STONE_HOE.parseMaterial()
                || p.getItemInHand().getType() == XMaterial.GOLDEN_HOE.parseMaterial()
                || p.getItemInHand().getType() == XMaterial.DIAMOND_HOE.parseMaterial()
                || p.getItemInHand().getType() == XMaterial.NETHERITE_HOE.parseMaterial()) {
            if (Files.getInstance().getconfig().getBoolean("debug")) {
                Jobs.getInstance().getLogger().log(Level.INFO, "Check item");
            }
            BlockData bdata = b.getBlockData();
            if (bdata instanceof Ageable) {
                if (((Ageable) bdata).getAge() == ((Ageable) bdata).getMaximumAge()) {
                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                        Jobs.getInstance().getLogger().log(Level.INFO, "Check age block");
                    }
                    if (Files.getInstance().getJobs(p).equals("FARMER")) {

                        if (Files.getInstance().getPower(e.getPlayer()) <= Files.getInstance().getconfig().getInt("minimum_power")) {
                            e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + NEED_MORE_POWER));
                            e.setCancelled(true);
                        } else {
                            if (Files.getInstance().getconfig().getBoolean("debug")) {
                                Jobs.getInstance().getLogger().log(Level.INFO, "Check power");
                            }
                            if (playerchance >= systemchance) {
                                if (Files.getInstance().getGang(e.getPlayer()) != null) {
                                    Files.getInstance().addXP(e.getPlayer(), 2 + Files.getInstance().getleveluptimes(e.getPlayer()));
                                }
                                Files.getInstance().addPower(e.getPlayer(), 5 + Files.getInstance().getleveluptimes(e.getPlayer()));
                                e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + LUCKY
                                        .replaceAll("%power%", String.valueOf(POWER + LEVELUP))
                                        .replaceAll("%xp%", String.valueOf(XP + LEVELUP))));

                            }
                            if (Files.getInstance().getGang(e.getPlayer()) != null) {
                                Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp") + Files.getInstance().getleveluptimes(e.getPlayer()));
                            }
                            Files.getInstance().removePower(p, 1);
                            e.setDropItems(false);
                            e.getBlock().getDrops().clear();
                            if (!e.isDropItems()) {
                                if (Files.getInstance().getconfig().getBoolean("debug")) {
                                    Jobs.getInstance().getLogger().log(Level.INFO, "No drop");
                                }
                                for (String getBlockType : Files.getInstance().getconfig().getConfigurationSection("Blocks.").getKeys(false)) {
                                    if (blocks.equalsIgnoreCase(getBlockType)) {
                                        max = Files.getInstance().getconfig().getInt("Blocks." + blocks + ".max");
                                        min = Files.getInstance().getconfig().getInt("Blocks." + blocks + ".min");
                                        break;
                                    }
                                }
                                if (Files.getInstance().getconfig().getBoolean("debug")) {
                                    Jobs.getInstance().getLogger().log(Level.INFO, "Correct block");
                                }
                                Integer kg = Files.getInstance().getRandomNumber(min, max);
                                if (Files.getInstance().getWeight(e.getPlayer()) + kg <= Files.getInstance().getMaxWeight(e.getPlayer())) {

                                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                                        Jobs.getInstance().getLogger().log(Level.INFO, "Enough space");
                                    }
                                    if (kg != 0) {
                                        Files.getInstance().addWeight(e.getPlayer(), kg);
                                        Files.getInstance().addStorage(e.getPlayer(), blocks, kg);
                                    }
                                } else {
                                    e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + FULL_STORAGE));
                                    if (Files.getInstance().getconfig().getBoolean("debug")) {
                                        Jobs.getInstance().getLogger().log(Level.INFO, "Not Enough space");
                                    }
                                    e.setCancelled(true);
                                }
                            }
                            if (Files.getInstance().getconfig().getBoolean("debug")) {
                                Jobs.getInstance().getLogger().log(Level.INFO, "Add xp");
                            }
                        }
                    } else {
                        p.sendMessage(Files.getInstance().convert(PREFIX + NOT_CORRECT_JOBS));
                        e.setCancelled(true);
                    }
                } else {
                    e.setCancelled(true);
                }
            }
        } else {
            p.sendMessage(Files.getInstance().convert(PREFIX + NOT_CORRECT_ITEMS));
            e.setCancelled(true);
        }
    }
}