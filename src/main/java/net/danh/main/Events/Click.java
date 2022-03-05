package net.danh.main.Events;

import net.danh.main.Gui.SellBlocks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import preponderous.ponder.minecraft.bukkit.tools.MaterialChecker;

public class Click implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onClick(@NotNull PlayerInteractEvent e) {
        Player p = e.getPlayer();
        MaterialChecker CheckMaterial = new MaterialChecker();
        if (CheckMaterial.isAxe(p.getItemInHand().getType())
                || CheckMaterial.isPickaxe(p.getItemInHand().getType())
                || CheckMaterial.isShovel(p.getItemInHand().getType())) {
            if (!(e.getAction().equals(Action.LEFT_CLICK_BLOCK)
                    || e.getAction().equals(Action.LEFT_CLICK_AIR))) {
                p.openInventory(SellBlocks.getSellGui(p));
            }
        }
    }
}
