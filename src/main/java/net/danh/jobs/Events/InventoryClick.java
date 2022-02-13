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
            if (e.getCurrentItem().getType() != null &&
                    e.getCurrentItem().getType() == XMaterial.COAL_ORE.parseMaterial()
                    || e.getCurrentItem().getType() == XMaterial.IRON_ORE.parseMaterial()
                    || e.getCurrentItem().getType() == XMaterial.GOLD_ORE.parseMaterial()
                    || e.getCurrentItem().getType() == XMaterial.DIAMOND_ORE.parseMaterial()
                    || e.getCurrentItem().getType() == XMaterial.EMERALD_ORE.parseMaterial()) {
                if (e.isLeftClick()) {
                    Price.getInstance().Sell(p, e.getCurrentItem().getType().toString().toUpperCase(), 1);
                } else if (e.isRightClick()) {
                    Price.getInstance().Sell(p, e.getCurrentItem().getType().toString().toUpperCase(), 10);
                } else if (e.isShiftClick() && e.isLeftClick()) {
                    Price.getInstance().Sell(p, e.getCurrentItem().getType().toString().toUpperCase(), 64);
                } else if (e.isShiftClick() && e.isRightClick()) {
                    Price.getInstance().Sell(p, e.getCurrentItem().getType().toString().toUpperCase(), 640);
                }
                p.closeInventory();
                p.openInventory(SellBlocks.getInstance().getSellGui(p));
            }
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
