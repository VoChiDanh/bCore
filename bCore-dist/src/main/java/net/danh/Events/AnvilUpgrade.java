package net.danh.Events;

import io.lumine.mythic.lib.api.item.NBTItem;
import net.danh.Manager.Data;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AnvilUpgrade implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInventoryClick(@NotNull InventoryClickEvent e) {
// check if the event has been cancelled by another plugin
        HumanEntity ent = e.getWhoClicked();

// not really necessary
        if (ent instanceof Player) {
            Player player = (Player) ent;
            Inventory inv = e.getInventory();

// see if the event is about an anvil
            if (inv instanceof AnvilInventory) {
                InventoryView view = e.getView();
                int rawSlot = e.getRawSlot();

// compare the raw slot with the inventory view to make sure we are talking about the upper inventory
                if (rawSlot == view.convertSlot(rawSlot)) {
/*
slot 0 = left item slot
slot 1 = right item slot
slot 2 = result item slot

see if the player clicked in the result item slot of the anvil inventory
*/
                    if (rawSlot == 2) {
/*
get the current item1 in the result slot
I think inv.getItem(rawSlot) would be possible too
*/
                        ItemStack item1 = inv.getItem(1);
                        ItemStack item2 = inv.getItem(0);
                        NBTItem nbtItem = NBTItem.get(item1);
                        NBTItem nbtItem1 = NBTItem.get(item2);
                        if (nbtItem.hasType() && nbtItem1.hasType()) {
                            if (nbtItem.getType().equals("MATERIAL")) {
                                if (nbtItem1.getType().equals("SWORD") || nbtItem1.getType().equals("TOOL") || nbtItem1.getType().equals("ARMOR")) {
                                    if (nbtItem.getString("MMOITEMS_ITEM_ID").equals("DANANGCAP")) {
                                        ItemStack itemStack = new ItemStack(Objects.requireNonNull(item2));
                                        itemStack.addUnsafeEnchantment((Enchantment) itemStack.getEnchantments(), itemStack.getEnchantmentLevel((Enchantment) itemStack.getEnchantments()) + 1);
                                        inv.setItem(2, itemStack);
                                        player.sendMessage(Data.convert("&aHoàn Tất"));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
