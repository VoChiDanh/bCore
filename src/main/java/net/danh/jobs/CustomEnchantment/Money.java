package net.danh.jobs.CustomEnchantment;

import io.papermc.paper.enchantments.EnchantmentRarity;
import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.Data;
import net.danh.jobs.Manager.Lucky;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class Money extends Enchantment implements Listener {

    public Money() {
        super(new NamespacedKey(Jobs.getInstance(), "Money"));
    }

    @EventHandler
    public void onBreak(@NotNull BlockBreakEvent e) {
        Material b = e.getBlock().getType();
        Player p = e.getPlayer();
        double money = p.getItemInHand().getEnchantmentLevel(this) * Data.getInstance().getRandomNumber(1, 100);
        if (isCoal(b) || isIron(b) || isGold(b) || isDiamond(b) || isEmerald(b) || isLog(b)) {
            if (p.getItemInHand().getItemMeta() != null && p.getItemInHand().hasItemMeta()) {
                if (p.getItemInHand().containsEnchantment(this)) {
                    if (Data.getInstance().getRandomNumber(1, 100) <= Lucky.getInstance().getLuckyChance(p)) {
                        EconomyResponse r = Jobs.economy.depositPlayer(p, money);
                        if (r.transactionSuccess()) {
                            p.sendMessage(Data.getInstance().convert("&a+ " + money + "$ &7| " + b.toString().replaceAll("_", " ").toLowerCase()));
                        }
                    }
                }
            }
        }
    }

    public boolean isCoal(@NotNull Material material) {
        return material.name().contains("COAL");
    }

    public boolean isIron(@NotNull Material material) {
        return material.name().contains("IRON");
    }

    public boolean isGold(@NotNull Material material) {
        return material.name().contains("GOLD");
    }

    public boolean isDiamond(@NotNull Material material) {
        return material.name().contains("DIAMOND");
    }

    public boolean isEmerald(@NotNull Material material) {
        return material.name().contains("EMERALD");
    }

    public boolean isLog(@NotNull Material material) {
        return material.name().contains("LOG");
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

    @Override
    public @NotNull Component displayName(int i) {
        return Component.translatable("Money");
    }

    @Override
    public boolean isTradeable() {
        return true;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public @NotNull EnchantmentRarity getRarity() {
        return EnchantmentRarity.COMMON;
    }

    @Override
    public float getDamageIncrease(int i, @NotNull EntityCategory entityCategory) {
        return 0;
    }

    @Override
    public @NotNull Set<EquipmentSlot> getActiveSlots() {
        return Set.of(EquipmentSlot.HAND);
    }

    @Override
    public @NotNull String translationKey() {
        return "Money";
    }

    @Override
    public @NotNull Key key(){ 
        return super.key();
    }
}

