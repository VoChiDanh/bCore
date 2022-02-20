package net.danh.bCore.Gui;

import net.danh.bCore.CustomEnchantment.Money;
import net.danh.bCore.Manager.Data;
import net.danh.bCore.Manager.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Enchant implements Listener {

    public static Inventory getEnchantGui(Player p) {
        Inventory EnchantGui = Bukkit.createInventory(p, Data.getguifile().getInt("GUI.EnchantGui.Size"), Objects.requireNonNull(Data.getguifile().getString(Data.convert("GUI.EnchantGui.Title"))));
        ItemStack MoneyEnchant = MoneyEnchant(p);
        EnchantGui.setItem(Data.getguifile().getInt("GUI.EnchantGui.Money.Slot"), MoneyEnchant);
        return EnchantGui;
    }

    public static ItemStack MoneyEnchant(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Data.getguifile().getString("GUI.EnchantGui.Money.Material")))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getguifile().getStringList("GUI.EnchantGui.Money.Lore")) {
            lore.add(Data.convert(loreconfig)
                    .replaceAll("%xp%", String.valueOf(PlayerData.getXP(p)))
                    .replaceAll("%exp%", String.valueOf(Data.getguifile().getInt("GUI.EnchantGui.Money.Exp"))));
        }
        Objects.requireNonNull(meta).setLore(lore);
        if (p.getItemInHand().getItemMeta() != null && p.getItemInHand().getItemMeta().hasEnchants() && p.getItemInHand().containsEnchantment(new Money())) {
            meta.setDisplayName(Data.convert(Objects.requireNonNull(Objects.requireNonNull(Data.getguifile().getString("GUI.EnchantGui.Money.Display"))
                    .replaceAll("%level%", String.valueOf(p.getItemInHand().getItemMeta().getEnchantLevel(new Money()))))));
        } else {
            meta.setDisplayName(Data.convert(Objects.requireNonNull(Objects.requireNonNull(Data.getguifile().getString("GUI.EnchantGui.Money.Display"))
                    .replaceAll("%level%", String.valueOf(0)))));
        }
        item.setItemMeta(meta);
        return item;
    }

}
