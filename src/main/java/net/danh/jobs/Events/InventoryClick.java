package net.danh.jobs.Events;

import com.cryptomorin.xseries.XMaterial;
import net.danh.jobs.Gui.SellBlocks;
import net.danh.jobs.Manager.Price;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryClick implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void OnInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("Sell Gui")) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() != null) {
                if (e.getCurrentItem().getType() == XMaterial.COAL.parseMaterial()) {
                    if (e.isLeftClick()) {
                        Price.getInstance().Sell(p, "COAL".toUpperCase(), 1);
                    }
                    if (e.isRightClick()) {
                        Price.getInstance().Sell(p, "COAL".toUpperCase(), 10);
                    }
                }
                if (e.getCurrentItem().getType() == XMaterial.IRON_INGOT.parseMaterial()) {
                    if (e.isLeftClick()) {
                        Price.getInstance().Sell(p, "IRON_INGOT".toUpperCase(), 1);
                    }
                    if (e.isRightClick()) {
                        Price.getInstance().Sell(p, "IRON_INGOT".toUpperCase(), 10);
                    }
                }
                if (e.getCurrentItem().getType() == XMaterial.GOLD_INGOT.parseMaterial()) {
                    if (e.isLeftClick()) {
                        Price.getInstance().Sell(p, "GOLD_INGOT".toUpperCase(), 1);
                    }
                    if (e.isRightClick()) {
                        Price.getInstance().Sell(p, "GOLD_INGOT".toUpperCase(), 10);
                    }
                }
                if (e.getCurrentItem().getType() == XMaterial.DIAMOND.parseMaterial()) {
                    if (e.isLeftClick()) {
                        Price.getInstance().Sell(p, "DIAMOND".toUpperCase(), 1);
                    }
                    if (e.isRightClick()) {
                        Price.getInstance().Sell(p, "DIAMOND".toUpperCase(), 10);
                    }
                }
                if (e.getCurrentItem().getType() == XMaterial.EMERALD.parseMaterial()) {
                    if (e.isLeftClick()) {
                        Price.getInstance().Sell(p, "EMERALD".toUpperCase(), 1);
                    }
                    if (e.isRightClick()) {
                        Price.getInstance().Sell(p, "EMERALD".toUpperCase(), 10);
                    }
                }
                if (e.getCurrentItem().getType() == XMaterial.OAK_LOG.parseMaterial()) {
                    if (e.isLeftClick()) {
                        Price.getInstance().Sell(p, "LOG".toUpperCase(), 1);
                    }
                    if (e.isRightClick()) {
                        Price.getInstance().Sell(p, "LOG".toUpperCase(), 10);
                    }
                }
            }
            p.closeInventory();
            p.openInventory(SellBlocks.getInstance().getSellGui(p));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void OnDragInventory(InventoryDragEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("Sell Gui")) {
            e.setCancelled(true);
        }
    }
}
