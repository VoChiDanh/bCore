package net.danh.main.CustomEnchantment;

import net.danh.main.Manager.Data;
import net.danh.main.Manager.Lucky;
import net.danh.main.bCore;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import preponderous.ponder.minecraft.bukkit.tools.MaterialChecker;

public class Money extends Enchantment implements Listener {

    public Money() {
        super(new NamespacedKey(bCore.getInstance(), "Money"));
    }

    @EventHandler
    public void onBreak(@NotNull BlockBreakEvent e) {
        Material b = e.getBlock().getType();
        Player p = e.getPlayer();
        double money = p.getItemInHand().getEnchantmentLevel(this) * Data.getRandomInt(1, 100);
        MaterialChecker CheckMaterial = new MaterialChecker();
        if (CheckMaterial.isCoal(b) || CheckMaterial.isIron(b) || CheckMaterial.isGold(b) || CheckMaterial.isDiamond(b) || CheckMaterial.isEmerald(b) || CheckMaterial.isLog(b)) {
            if (p.getItemInHand().getItemMeta() != null && p.getItemInHand().hasItemMeta()) {
                if (p.getItemInHand().containsEnchantment(this)) {
                    if (Data.getRandomInt(1, 100) <= Lucky.getLuckyChance(p)) {
                        EconomyResponse r = bCore.economy.depositPlayer(p, money);
                        if (r.transactionSuccess()) {
                            p.sendMessage(Data.convert("&a+ " + money + "$ &7| " + b.toString().replaceAll("_", " ").toLowerCase()));
                        }
                    }
                }
            }
        }
    }

    @Override
    public @NotNull String getName() {
        return "Money";
    }

    @Override
    public int getMaxLevel() {
        return 100;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public @NotNull EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.TOOL;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack itemStack) {
        return true;
    }

}

