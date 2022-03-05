package net.danh.main.Events;

import net.danh.main.Manager.Data;
import net.danh.main.Manager.Items;
import net.danh.main.Manager.Storage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Inventory implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void OnInventoryClick(@NotNull InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals(Data.getguifile().getString(Data.convert("GUI.SellGui.Title")))) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) return;
            if (e.getSlot() == Data.getguifile().getInt("GUI.SellGui.Stone.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Stone.Material"))), 10);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Stone.Material"))), 100);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Stone.Material"))), Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Stone.Material")))));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Items.Take(p, Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Stone.Material")), 1);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Items.Take(p, Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Stone.Material")), 10);
                }
            }
            if (e.getSlot() == Data.getguifile().getInt("GUI.SellGui.Coal.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Coal.Material"))), 10);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Coal.Material"))), 100);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Coal.Material"))), Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Coal.Material")))));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Items.Take(p, Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Coal.Material")), 1);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Items.Take(p, Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Coal.Material")), 10);
                }
            }
            if (e.getSlot() == Data.getguifile().getInt("GUI.SellGui.Iron.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Iron.Material"))), 10);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Iron.Material"))), 100);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Iron.Material"))), Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Iron.Material")))));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Items.Take(p, Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Iron.Material")), 1);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Items.Take(p, Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Iron.Material")), 10);
                }
            }
            if (e.getSlot() == Data.getguifile().getInt("GUI.SellGui.Gold.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Gold.Material"))), 10);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Gold.Material"))), 50);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Gold.Material"))), Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Gold.Material")))));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Gold.Material"))), 1);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Gold.Material"))), 10);
                }
            }
            if (e.getSlot() == Data.getguifile().getInt("GUI.SellGui.Diamond.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Diamond.Material"))), 1);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Diamond.Material"))), 5);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Diamond.Material"))), Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Diamond.Material")))));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Items.Sell(p, Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Diamond.Material")), 10);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Items.Sell(p, Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Diamond.Material")), 50);
                }
            }
            if (e.getSlot() == Data.getguifile().getInt("GUI.SellGui.Emerald.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Emerald.Material"))), 10);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Emerald.Material"))), 100);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Emerald.Material"))), Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Emerald.Material")))));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Items.Sell(p, Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Emerald.Material")), 1);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Items.Sell(p, Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Emerald.Material")), 10);
                }
            }
            if (e.getSlot() == Data.getguifile().getInt("GUI.SellGui.Log.Slot")) {
                if (e.getClick().equals(ClickType.LEFT)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Log.Material"))), 10);
                }
                if (e.getClick().equals(ClickType.RIGHT)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Log.Material"))), 100);
                }
                if (e.getClick().equals(ClickType.MIDDLE)) {
                    Items.Sell(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Log.Material"))), Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Log.Material")))));
                }
                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
                    Items.Sell(p, Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Log.Material")), 1);
                }
                if (e.getClick().equals(ClickType.SHIFT_RIGHT)) {
                    Items.Sell(p, Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Log.Material")), 10);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void OnDragInventory(@NotNull InventoryDragEvent e) {
        if (e.getView().getTitle().equals(Data.getguifile().getString(Data.convert("GUI.SellGui.Title")))
                || e.getView().getTitle().equals(Data.getguifile().getString(Data.convert("GUI.EnchantGui.Title")))) {
            e.setCancelled(true);
        }
    }
}
