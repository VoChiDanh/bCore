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
        if (e.getView().getTitle().equals(Data.getInstance().getgui().getString(Data.getInstance().convert("GUI.SellGui.Title")))) {
            e.setCancelled(true);
            Objects.requireNonNull(e.getCurrentItem()).getType();
            if (e.getSlot() == Data.getInstance().getgui().getInt("GUI.SellGui.Coal.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.getInstance().Sell(p, "COAL".toUpperCase(), 1);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.getInstance().Sell(p, "COAL".toUpperCase(), 5);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.getInstance().Sell(p, "COAL".toUpperCase(), Storage.getInstance().getStorage(p, "COAL".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.getInstance().Sell(p, "COAL".toUpperCase(), 10);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.getInstance().Sell(p, "COAL".toUpperCase(), 50);
                }
            }
            if (e.getSlot() == Data.getInstance().getgui().getInt("GUI.SellGui.Iron.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.getInstance().Sell(p, "IRON".toUpperCase(), 1);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.getInstance().Sell(p, "IRON".toUpperCase(), 5);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.getInstance().Sell(p, "IRON".toUpperCase(), Storage.getInstance().getStorage(p, "IRON".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.getInstance().Sell(p, "IRON".toUpperCase(), 10);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.getInstance().Sell(p, "IRON".toUpperCase(), 50);
                }
            }
            if (e.getSlot() == Data.getInstance().getgui().getInt("GUI.SellGui.Gold.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.getInstance().Sell(p, "GOLD".toUpperCase(), 1);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.getInstance().Sell(p, "GOLD".toUpperCase(), 5);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.getInstance().Sell(p, "GOLD".toUpperCase(), Storage.getInstance().getStorage(p, "GOLD".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.getInstance().Sell(p, "GOLD".toUpperCase(), 10);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.getInstance().Sell(p, "GOLD".toUpperCase(), 50);
                }
            }
            if (e.getSlot() == Data.getInstance().getgui().getInt("GUI.SellGui.Diamond.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.getInstance().Sell(p, "DIAMOND".toUpperCase(), 1);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.getInstance().Sell(p, "DIAMOND".toUpperCase(), 5);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.getInstance().Sell(p, "DIAMOND".toUpperCase(), Storage.getInstance().getStorage(p, "DIAMOND".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.getInstance().Sell(p, "DIAMOND".toUpperCase(), 10);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.getInstance().Sell(p, "DIAMOND".toUpperCase(), 50);
                }
            }
            if (e.getSlot() == Data.getInstance().getgui().getInt("GUI.SellGui.Emerald.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.getInstance().Sell(p, "EMERALD".toUpperCase(), 1);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.getInstance().Sell(p, "EMERALD".toUpperCase(), 5);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.getInstance().Sell(p, "EMERALD".toUpperCase(), Storage.getInstance().getStorage(p, "EMERALD".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.getInstance().Sell(p, "EMERALD".toUpperCase(), 10);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.getInstance().Sell(p, "EMERALD".toUpperCase(), 50);
                }
            }
            if (e.getSlot() == Data.getInstance().getgui().getInt("GUI.SellGui.Log.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Price.getInstance().Sell(p, "LOG".toUpperCase(), 1);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Price.getInstance().Sell(p, "LOG".toUpperCase(), 5);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Price.getInstance().Sell(p, "LOG".toUpperCase(), Storage.getInstance().getStorage(p, "LOG".toUpperCase()));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Price.getInstance().Sell(p, "LOG".toUpperCase(), 10);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Price.getInstance().Sell(p, "LOG".toUpperCase(), 50);
                }
            }
            p.closeInventory();
            p.openInventory(SellBlocks.getInstance().getSellGui(p));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void OnDragInventory(InventoryDragEvent e) {
        if (e.getView().getTitle().equals(Data.getInstance().getgui().getString(Data.getInstance().convert("GUI.SellGui.Title")))) {
            e.setCancelled(true);
        }
    }
}
