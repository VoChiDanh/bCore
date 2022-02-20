package net.danh.bCore.Events;

import net.danh.bCore.CustomEnchantment.Money;
import net.danh.bCore.Gui.Enchant;
import net.danh.bCore.Gui.SellBlocks;
import net.danh.bCore.Manager.Data;
import net.danh.bCore.Manager.PlayerData;
import net.danh.bCore.Manager.Price;
import net.danh.bCore.Manager.Storage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

import java.util.Objects;

public class Inventory implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void OnInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals(Data.getguifile().getString(Data.convert("GUI.SellGui.Title")))) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null) return;
            if (e.getSlot() == Data.getguifile().getInt("GUI.SellGui.Stone.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.Sell(p, "STONE".toUpperCase(), 1D);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.Sell(p, "STONE".toUpperCase(), 5D);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.Sell(p, "STONE".toUpperCase(), Storage.getStorage(p, "STONE".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.Sell(p, "STONE".toUpperCase(), 10D);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.Sell(p, "STONE".toUpperCase(), 50D);
                }
            }
            if (e.getSlot() == Data.getguifile().getInt("GUI.SellGui.Coal.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.Sell(p, "COAL".toUpperCase(), 1D);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.Sell(p, "COAL".toUpperCase(), 5D);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.Sell(p, "COAL".toUpperCase(), Storage.getStorage(p, "COAL".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.Sell(p, "COAL".toUpperCase(), 10D);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.Sell(p, "COAL".toUpperCase(), 50D);
                }
            }
            if (e.getSlot() == Data.getguifile().getInt("GUI.SellGui.Iron.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.Sell(p, "IRON".toUpperCase(), 1D);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.Sell(p, "IRON".toUpperCase(), 5D);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.Sell(p, "IRON".toUpperCase(), Storage.getStorage(p, "IRON".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.Sell(p, "IRON".toUpperCase(), 10D);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.Sell(p, "IRON".toUpperCase(), 50D);
                }
            }
            if (e.getSlot() == Data.getguifile().getInt("GUI.SellGui.Gold.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.Sell(p, "GOLD".toUpperCase(), 1D);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.Sell(p, "GOLD".toUpperCase(), 5D);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.Sell(p, "GOLD".toUpperCase(), Storage.getStorage(p, "GOLD".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.Sell(p, "GOLD".toUpperCase(), 10D);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.Sell(p, "GOLD".toUpperCase(), 50D);
                }
            }
            if (e.getSlot() == Data.getguifile().getInt("GUI.SellGui.Diamond.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.Sell(p, "DIAMOND".toUpperCase(), 1D);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.Sell(p, "DIAMOND".toUpperCase(), 5D);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.Sell(p, "DIAMOND".toUpperCase(), Storage.getStorage(p, "DIAMOND".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.Sell(p, "DIAMOND".toUpperCase(), 10D);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.Sell(p, "DIAMOND".toUpperCase(), 50D);
                }
            }
            if (e.getSlot() == Data.getguifile().getInt("GUI.SellGui.Emerald.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.Sell(p, "EMERALD".toUpperCase(), 1D);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.Sell(p, "EMERALD".toUpperCase(), 5D);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.Sell(p, "EMERALD".toUpperCase(), Storage.getStorage(p, "EMERALD".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.Sell(p, "EMERALD".toUpperCase(), 10D);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.Sell(p, "EMERALD".toUpperCase(), 50D);
                }
            }
            if (e.getSlot() == Data.getguifile().getInt("GUI.SellGui.Log.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.Sell(p, "LOG".toUpperCase(), 1D);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.Sell(p, "LOG".toUpperCase(), 5D);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.Sell(p, "LOG".toUpperCase(), Storage.getStorage(p, "LOG".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.Sell(p, "LOG".toUpperCase(), 10D);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.Sell(p, "LOG".toUpperCase(), 50D);
                }
            }
            p.closeInventory();
            p.openInventory(SellBlocks.getSellGui(p));
        }
        if (e.getView().getTitle().equals(Data.getguifile().getString(Data.convert("GUI.EnchantGui.Title")))) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null) return;
            if (e.getSlot() == Data.getguifile().getInt("GUI.EnchantGui.Money.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    if (PlayerData.getXP(p) >= Data.getguifile().getInt("GUI.EnchantGui.Money.Exp")) {
                        p.getItemInHand().addUnsafeEnchantment(new Money(), p.getItemInHand().getEnchantmentLevel(new Money()) + 1);
                        PlayerData.removeXP(p, Data.getguifile().getInt("GUI.EnchantGui.Money.Exp"));
                    } else {
                        p.sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("NOT_ENOUGH_EXP"))));
                    }
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    if (p.getItemInHand().getItemMeta() != null && p.getItemInHand().containsEnchantment(new Money())) {
                        int level = p.getItemInHand().getEnchantmentLevel(new Money());
                        if (level > 1) {
                            p.getItemInHand().addUnsafeEnchantment(new Money(), p.getItemInHand().getEnchantmentLevel(new Money()) - 1);
                        } else {
                            p.getItemInHand().removeEnchantment(new Money());
                        }
                        PlayerData.addXP(p, 1000);
                    }
                }
            }
            p.closeInventory();
            p.openInventory(Enchant.getEnchantGui(p));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void OnDragInventory(InventoryDragEvent e) {
        if (e.getView().getTitle().equals(Data.getguifile().getString(Data.convert("GUI.SellGui.Title")))
                || e.getView().getTitle().equals(Data.getguifile().getString(Data.convert("GUI.EnchantGui.Title")))) {
            e.setCancelled(true);
        }
    }
}
