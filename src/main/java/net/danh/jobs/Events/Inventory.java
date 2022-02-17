package net.danh.jobs.Events;

import net.danh.jobs.Gui.SellBlocks;
import net.danh.jobs.Manager.Data;
import net.danh.jobs.Manager.Price;
import net.danh.jobs.Manager.Storage;
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
        if (e.getView().getTitle().equals(Data.getInstance().getguifile().getString(Data.getInstance().convert("GUI.SellGui.Title")))) {
            e.setCancelled(true);
            Objects.requireNonNull(e.getCurrentItem()).getType();
            if (e.getSlot() == Data.getInstance().getguifile().getInt("GUI.SellGui.Stone.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.getInstance().Sell(p, "STONE".toUpperCase(), 1D);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.getInstance().Sell(p, "STONE".toUpperCase(), 5D);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.getInstance().Sell(p, "STONE".toUpperCase(), Storage.getInstance().getStorage(p, "STONE".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.getInstance().Sell(p, "STONE".toUpperCase(), 10D);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.getInstance().Sell(p, "STONE".toUpperCase(), 50D);
                }
            }
            if (e.getSlot() == Data.getInstance().getguifile().getInt("GUI.SellGui.Coal.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.getInstance().Sell(p, "COAL".toUpperCase(), 1D);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.getInstance().Sell(p, "COAL".toUpperCase(), 5D);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.getInstance().Sell(p, "COAL".toUpperCase(), Storage.getInstance().getStorage(p, "COAL".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.getInstance().Sell(p, "COAL".toUpperCase(), 10D);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.getInstance().Sell(p, "COAL".toUpperCase(), 50D);
                }
            }
            if (e.getSlot() == Data.getInstance().getguifile().getInt("GUI.SellGui.Iron.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.getInstance().Sell(p, "IRON".toUpperCase(), 1D);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.getInstance().Sell(p, "IRON".toUpperCase(), 5D);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.getInstance().Sell(p, "IRON".toUpperCase(), Storage.getInstance().getStorage(p, "IRON".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.getInstance().Sell(p, "IRON".toUpperCase(), 10D);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.getInstance().Sell(p, "IRON".toUpperCase(), 50D);
                }
            }
            if (e.getSlot() == Data.getInstance().getguifile().getInt("GUI.SellGui.Gold.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.getInstance().Sell(p, "GOLD".toUpperCase(), 1D);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.getInstance().Sell(p, "GOLD".toUpperCase(), 5D);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.getInstance().Sell(p, "GOLD".toUpperCase(), Storage.getInstance().getStorage(p, "GOLD".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.getInstance().Sell(p, "GOLD".toUpperCase(), 10D);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.getInstance().Sell(p, "GOLD".toUpperCase(), 50D);
                }
            }
            if (e.getSlot() == Data.getInstance().getguifile().getInt("GUI.SellGui.Diamond.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.getInstance().Sell(p, "DIAMOND".toUpperCase(), 1D);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.getInstance().Sell(p, "DIAMOND".toUpperCase(), 5D);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.getInstance().Sell(p, "DIAMOND".toUpperCase(), Storage.getInstance().getStorage(p, "DIAMOND".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.getInstance().Sell(p, "DIAMOND".toUpperCase(), 10D);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.getInstance().Sell(p, "DIAMOND".toUpperCase(), 50D);
                }
            }
            if (e.getSlot() == Data.getInstance().getguifile().getInt("GUI.SellGui.Emerald.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.getInstance().Sell(p, "EMERALD".toUpperCase(), 1D);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.getInstance().Sell(p, "EMERALD".toUpperCase(), 5D);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.getInstance().Sell(p, "EMERALD".toUpperCase(), Storage.getInstance().getStorage(p, "EMERALD".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.getInstance().Sell(p, "EMERALD".toUpperCase(), 10D);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.getInstance().Sell(p, "EMERALD".toUpperCase(), 50D);
                }
            }
            if (e.getSlot() == Data.getInstance().getguifile().getInt("GUI.SellGui.Log.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.getInstance().Sell(p, "LOG".toUpperCase(), 1D);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.getInstance().Sell(p, "LOG".toUpperCase(), 5D);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.getInstance().Sell(p, "LOG".toUpperCase(), Storage.getInstance().getStorage(p, "LOG".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.getInstance().Sell(p, "LOG".toUpperCase(), 10D);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.getInstance().Sell(p, "LOG".toUpperCase(), 50D);
                }
            }
            p.closeInventory();
            p.openInventory(SellBlocks.getInstance().getSellGui(p));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void OnDragInventory(InventoryDragEvent e) {
        if (e.getView().getTitle().equals(Data.getInstance().getguifile().getString(Data.getInstance().convert("GUI.SellGui.Title")))) {
            e.setCancelled(true);
        }
    }
}
