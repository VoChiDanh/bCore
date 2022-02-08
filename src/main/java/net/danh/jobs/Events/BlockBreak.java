package net.danh.jobs.Events;

import com.cryptomorin.xseries.XMaterial;
import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.Files;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();
        String NEED_MORE_POWER = Files.getInstance().getlanguage().getString("NEED_MORE_POWER");
        String PREFIX = Files.getInstance().getlanguage().getString("PREFIX");
        String LUCKY = Files.getInstance().getlanguage().getString("LUCKY");
        Integer POWER = Files.getInstance().getconfig().getInt("Lucky_Power");
        Integer XP = Files.getInstance().getconfig().getInt("Lucky_XP");
        Integer LEVELUP = Files.getInstance().getleveluptimes(e.getPlayer());
        String NOT_CORRECT_JOBS = Files.getInstance().getlanguage().getString("NOT_CORRECT_JOBS");
        String NOT_CORRECT_ITEMS = Files.getInstance().getlanguage().getString("NOT_CORRECT_ITEMS");
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
                        double systemchance = Math.random() * 100.0D;
                        double playerchance = Files.getInstance().getAge(e.getPlayer()) / 10;
                        if (playerchance >= systemchance) {
                            Files.getInstance().addXP(e.getPlayer(), 2 + Files.getInstance().getleveluptimes(e.getPlayer()));
                            Files.getInstance().addPower(e.getPlayer(), 5 + Files.getInstance().getleveluptimes(e.getPlayer()));
                            e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + LUCKY
                                    .replaceAll("%power%", String.valueOf(POWER + LEVELUP))
                                    .replaceAll("%xp%", String.valueOf(XP + LEVELUP))));
                            return;
                        }
                        Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp") + Files.getInstance().getleveluptimes(e.getPlayer()));
                        Files.getInstance().removePower(p, 1);
                        if (b.getType() == XMaterial.COAL_ORE.parseMaterial()) {
                            b.getDrops().clear();
                            ItemStack coal = new ItemStack(Objects.requireNonNull(XMaterial.valueOf(Files.getInstance().getitems().getString("coal.MATERIAL")).parseMaterial()), Files.getInstance().getitems().getInt("coal.AMOUNT"));
                            ItemMeta meta = coal.getItemMeta();
                            Objects.requireNonNull(meta).setDisplayName(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("coal.DISPLAY_NAME"))));
                            ArrayList<String> lore = new ArrayList<>();
                            lore.add(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("coal.LORE1"))));
                            lore.add(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("coal.LORE2"))));
                            meta.setLore(lore);
                            coal.setItemMeta(meta);
                            e.getPlayer().getInventory().addItem(coal);
                        } else if (b.getType() == XMaterial.IRON_ORE.parseMaterial()) {
                            b.getDrops().clear();
                            ItemStack iron = new ItemStack(Objects.requireNonNull(XMaterial.valueOf(Files.getInstance().getitems().getString("iron.MATERIAL")).parseMaterial()), Files.getInstance().getitems().getInt("iron.AMOUNT"));
                            ItemMeta meta = iron.getItemMeta();
                            Objects.requireNonNull(meta).setDisplayName(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("iron.DISPLAY_NAME"))));
                            ArrayList<String> lore = new ArrayList<>();
                            lore.add(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("iron.LORE1"))));
                            lore.add(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("iron.LORE2"))));
                            meta.setLore(lore);
                            iron.setItemMeta(meta);
                            e.getPlayer().getInventory().addItem(iron);
                        } else if (b.getType() == XMaterial.GOLD_ORE.parseMaterial()) {
                            b.getDrops().clear();
                            ItemStack gold = new ItemStack(Objects.requireNonNull(XMaterial.valueOf(Files.getInstance().getitems().getString("gold.MATERIAL")).parseMaterial()), Files.getInstance().getitems().getInt("gold.AMOUNT"));
                            ItemMeta meta = gold.getItemMeta();
                            Objects.requireNonNull(meta).setDisplayName(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("gold.DISPLAY_NAME"))));
                            ArrayList<String> lore = new ArrayList<>();
                            lore.add(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("gold.LORE1"))));
                            lore.add(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("gold.LORE2"))));
                            meta.setLore(lore);
                            gold.setItemMeta(meta);
                            e.getPlayer().getInventory().addItem(gold);
                        } else if (b.getType() == XMaterial.DIAMOND_ORE.parseMaterial()) {
                            b.getDrops().clear();
                            ItemStack diamond = new ItemStack(Objects.requireNonNull(XMaterial.valueOf(Files.getInstance().getitems().getString("diamond.MATERIAL")).parseMaterial()), Files.getInstance().getitems().getInt("diamond.AMOUNT"));
                            ItemMeta meta = diamond.getItemMeta();
                            Objects.requireNonNull(meta).setDisplayName(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("diamond.DISPLAY_NAME"))));
                            ArrayList<String> lore = new ArrayList<>();
                            lore.add(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("diamond.LORE1"))));
                            lore.add(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("diamond.LORE2"))));
                            meta.setLore(lore);
                            diamond.setItemMeta(meta);
                            e.getPlayer().getInventory().addItem(diamond);
                        } else if (b.getType() == XMaterial.EMERALD_ORE.parseMaterial()) {
                            b.getDrops().clear();
                            ItemStack emerald = new ItemStack(Objects.requireNonNull(XMaterial.valueOf(Files.getInstance().getitems().getString("emerald.MATERIAL")).parseMaterial()), Files.getInstance().getitems().getInt("emerald.AMOUNT"));
                            ItemMeta meta = emerald.getItemMeta();
                            Objects.requireNonNull(meta).setDisplayName(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("emerald.DISPLAY_NAME"))));
                            ArrayList<String> lore = new ArrayList<>();
                            lore.add(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("emerald.LORE1"))));
                            lore.add(Files.getInstance().convert(Objects.requireNonNull(Files.getInstance().getitems().getString("emerald.LORE2"))));
                            meta.setLore(lore);
                            emerald.setItemMeta(meta);
                            e.getPlayer().getInventory().addItem(emerald);
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
                        double systemchance = Math.random() * 100.0D;
                        double playerchance = Files.getInstance().getAge(e.getPlayer()) / 10;
                        if (playerchance >= systemchance) {
                            Files.getInstance().addXP(e.getPlayer(), 2 + Files.getInstance().getleveluptimes(e.getPlayer()));
                            Files.getInstance().addPower(e.getPlayer(), 5 + Files.getInstance().getleveluptimes(e.getPlayer()));
                            e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + LUCKY
                                    .replaceAll("%power%", String.valueOf(POWER + LEVELUP))
                                    .replaceAll("%xp%", String.valueOf(XP + LEVELUP))));
                            return;
                        }
                        Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp") + Files.getInstance().getleveluptimes(e.getPlayer()));
                        Files.getInstance().removePower(p, 1);
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
                            double systemchance = Math.random() * 100.0D;
                            double playerchance = Files.getInstance().getAge(e.getPlayer()) / 10;
                            if (playerchance >= systemchance) {
                                Files.getInstance().addXP(e.getPlayer(), 2 + Files.getInstance().getleveluptimes(e.getPlayer()));
                                Files.getInstance().addPower(e.getPlayer(), 5 + Files.getInstance().getleveluptimes(e.getPlayer()));
                                e.getPlayer().sendMessage(Files.getInstance().convert(PREFIX + LUCKY
                                        .replaceAll("%power%", String.valueOf(POWER + LEVELUP))
                                        .replaceAll("%xp%", String.valueOf(XP + LEVELUP))));
                                return;
                            }
                            Files.getInstance().addXP(e.getPlayer(), Files.getInstance().getconfig().getInt("xp") + Files.getInstance().getleveluptimes(e.getPlayer()));
                            Files.getInstance().removePower(p, 1);
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
                    e.setCancelled(true);
                }
            } else {
                return;
            }
        } else {
            p.sendMessage(Files.getInstance().convert(PREFIX + NOT_CORRECT_ITEMS));
            e.setCancelled(true);
            return;
        }
    }
}