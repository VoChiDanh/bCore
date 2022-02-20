package net.danh.bCore.Events;

import net.danh.bCore.Gui.Enchant;
import net.danh.bCore.Manager.CheckMaterial;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Click implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (CheckMaterial.isAxe(p.getItemInHand().getType()) || CheckMaterial.isPickaxe(p.getItemInHand().getType())) {
            p.openInventory(Enchant.getEnchantGui(p));
        }
    }
}
