package net.danh.main.Events;

import net.danh.main.Manager.*;
import net.danh.main.bCore;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import preponderous.ponder.minecraft.bukkit.tools.MaterialChecker;

import java.util.Objects;
import java.util.logging.Level;

public class BlockBreak implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();
        double systemchance = Math.random() * 100.0D;
        double playerchance = Lucky.getLuckyChance(e.getPlayer());
        String NEED_MORE_POWER = Data.getlanguagefile().getString("NEED_MORE_POWER");
        String PREFIX = Data.getlanguagefile().getString("PREFIX");
        String LUCKY = Data.getlanguagefile().getString("LUCKY");
        Integer POWER = Data.getconfigfile().getInt("Lucky_Power");
        Integer XP = Data.getconfigfile().getInt("Lucky_XP");
        Integer LEVELUP = PlayerData.getleveluptimes(e.getPlayer());
        String FULL_STORAGE = Data.getlanguagefile().getString("FULL_STORAGE");
        String blocks = e.getBlock().getType().toString();
        int max = 0;
        int min = 0;
        MaterialChecker CheckMaterial = new MaterialChecker();
        PlayerData.addBlocks(p, 1);
        if (e.getPlayer().hasPermission("bCore.admin")) {
            e.setCancelled(false);
            return;
        }
        if (CheckMaterial.isCoal(b.getType())
                || CheckMaterial.isIron(b.getType())
                || CheckMaterial.isGold(b.getType())
                || CheckMaterial.isDiamond(b.getType())
                || CheckMaterial.isEmerald(b.getType())
                || CheckMaterial.isLog(b.getType())) {

            if (Data.getconfigfile().getBoolean("debug")) {
                bCore.getInstance().getLogger().log(Level.INFO, "Check pickaxe");
            }
            if (Power.getPower(e.getPlayer()) <= Data.getconfigfile().getInt("minimum_power")) {
                e.getPlayer().sendMessage(Data.convert(PREFIX + NEED_MORE_POWER));
                e.setCancelled(true);
            } else {
                if (Data.getconfigfile().getBoolean("debug")) {
                    bCore.getInstance().getLogger().log(Level.INFO, "Check power");
                }
                e.setDropItems(false);
                e.getBlock().getDrops().clear();
                e.setExpToDrop(0);
                if (!e.isDropItems()) {
                    if (Data.getconfigfile().getBoolean("debug")) {
                        bCore.getInstance().getLogger().log(Level.INFO, "No drop");
                    }
                    for (String getBlockType : Objects.requireNonNull(Data.getconfigfile().getConfigurationSection("Blocks.")).getKeys(false)) {
                        if (blocks.equalsIgnoreCase(getBlockType)) {
                            max = Data.getconfigfile().getInt("Blocks." + blocks + ".max");
                            min = Data.getconfigfile().getInt("Blocks." + blocks + ".min");
                            break;
                        }
                    }
                    if (Data.getconfigfile().getBoolean("debug")) {
                        bCore.getInstance().getLogger().log(Level.INFO, "Correct block");
                    }
                    int kg = Data.getRandomInt(min, max);
                    if (Weight.getWeight(e.getPlayer()) + kg <= Weight.getMaxWeight(e.getPlayer())) {

                        if (Data.getconfigfile().getBoolean("debug")) {
                            bCore.getInstance().getLogger().log(Level.INFO, "Enough space");
                        }
                        if (kg != 0) {
                            if (Objects.requireNonNull(e.getPlayer().getItemInHand().getItemMeta()).hasEnchant(Enchantment.LOOT_BONUS_BLOCKS) &&
                                    e.getPlayer().getItemInHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS) >= 1) {
                                int fortunelevel = Objects.requireNonNull(e.getPlayer().getItemInHand().getItemMeta()).getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS);
                                int fortunechance = (e.getPlayer().getItemInHand().getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS) * Lucky.getLuckyChance(e.getPlayer()));
                                int fortuneblocks = ((fortunechance + kg) * fortunelevel);
                                Weight.addWeight(e.getPlayer(), fortuneblocks);
                                if (CheckMaterial.isCoal(b.getType())) {
                                    Storage.addStorage(e.getPlayer(), "COAL".toUpperCase(), fortuneblocks);
                                } else if (CheckMaterial.isIron(b.getType())) {
                                    Storage.addStorage(e.getPlayer(), "IRON".toUpperCase(), fortuneblocks);
                                } else if (CheckMaterial.isGold(b.getType())) {
                                    Storage.addStorage(e.getPlayer(), "GOLD".toUpperCase(), fortuneblocks);
                                } else if (CheckMaterial.isDiamond(b.getType())) {
                                    Storage.addStorage(e.getPlayer(), "DIAMOND".toUpperCase(), fortuneblocks);
                                } else if (CheckMaterial.isEmerald(b.getType())) {
                                    Storage.addStorage(e.getPlayer(), "EMERALD".toUpperCase(), fortuneblocks);
                                } else if (CheckMaterial.isStone(b.getType())) {
                                    Storage.addStorage(e.getPlayer(), "STONE".toUpperCase(), fortuneblocks);
                                } else if (CheckMaterial.isLog(b.getType())) {
                                    Storage.addStorage(e.getPlayer(), "LOG".toUpperCase(), fortuneblocks);
                                }
                            } else {
                                Weight.addWeight(e.getPlayer(), kg);
                                if (CheckMaterial.isCoal(b.getType())) {
                                    Storage.addStorage(e.getPlayer(), "COAL".toUpperCase(), kg);
                                } else if (CheckMaterial.isIron(b.getType())) {
                                    Storage.addStorage(e.getPlayer(), "IRON".toUpperCase(), kg);
                                } else if (CheckMaterial.isGold(b.getType())) {
                                    Storage.addStorage(e.getPlayer(), "GOLD".toUpperCase(), kg);
                                } else if (CheckMaterial.isDiamond(b.getType())) {
                                    Storage.addStorage(e.getPlayer(), "DIAMOND".toUpperCase(), kg);
                                } else if (CheckMaterial.isEmerald(b.getType())) {
                                    Storage.addStorage(e.getPlayer(), "EMERALD".toUpperCase(), kg);
                                } else if (CheckMaterial.isStone(b.getType())) {
                                    Storage.addStorage(e.getPlayer(), "STONE".toUpperCase(), kg);
                                } else if (CheckMaterial.isLog(b.getType())) {
                                    Storage.addStorage(e.getPlayer(), "LOG".toUpperCase(), kg);
                                }
                            }
                        }
                    } else {
                        e.getPlayer().sendMessage(Data.convert(PREFIX + FULL_STORAGE));
                        if (Data.getconfigfile().getBoolean("debug")) {
                            bCore.getInstance().getLogger().log(Level.INFO, "Not Enough space");
                        }
                        e.setCancelled(true);
                        return;
                    }
                }
                if (playerchance >= systemchance) {
                    PlayerData.addXP(e.getPlayer(), 2 + PlayerData.getleveluptimes(e.getPlayer()));
                    Power.addPower(e.getPlayer(), 5 + PlayerData.getleveluptimes(e.getPlayer()));
                    e.getPlayer().sendMessage(Data.convert(PREFIX + Objects.requireNonNull(LUCKY)
                            .replaceAll("%power%", String.valueOf(POWER + LEVELUP))
                            .replaceAll("%xp%", String.valueOf(XP + LEVELUP))));
                    return;
                }
                PlayerData.addXP(e.getPlayer(), Data.getconfigfile().getInt("xp") + PlayerData.getleveluptimes(e.getPlayer()));
                Power.removePower(p, 1);
                if (Data.getconfigfile().getBoolean("debug")) {
                    bCore.getInstance().getLogger().log(Level.INFO, "Add xp");
                }
            }

        }
    }
}