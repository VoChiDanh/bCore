package net.danh.bCore.Gui;

import net.danh.bCore.bCore;
import net.danh.bCore.Manager.Data;
import net.danh.bCore.Manager.Price;
import net.danh.bCore.Manager.Storage;
import net.danh.bCore.Manager.Weight;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SellBlocks implements Listener {

    public static Inventory getSellGui(Player p) {
        Inventory sellgui = Bukkit.createInventory(p, Data.getguifile().getInt("GUI.SellGui.Size"), Objects.requireNonNull(Data.getguifile().getString(Data.convert("GUI.SellGui.Title"))));
        ItemStack coal = coal(p);
        ItemStack iron = iron(p);
        ItemStack gold = gold(p);
        ItemStack diamond = diamond(p);
        ItemStack emerald = emerald(p);
        ItemStack log = log(p);
        ItemStack player = playerinfo(p);
        ItemStack stone = stone(p);
        sellgui.setItem(Data.getguifile().getInt("GUI.SellGui.Coal.Slot"), coal);
        sellgui.setItem(Data.getguifile().getInt("GUI.SellGui.Stone.Slot"), stone);
        sellgui.setItem(Data.getguifile().getInt("GUI.SellGui.Iron.Slot"), iron);
        sellgui.setItem(Data.getguifile().getInt("GUI.SellGui.Gold.Slot"), gold);
        sellgui.setItem(Data.getguifile().getInt("GUI.SellGui.PlayerInfo.Slot"), player);
        sellgui.setItem(Data.getguifile().getInt("GUI.SellGui.Diamond.Slot"), diamond);
        sellgui.setItem(Data.getguifile().getInt("GUI.SellGui.Emerald.Slot"), emerald);
        sellgui.setItem(Data.getguifile().getInt("GUI.SellGui.Log.Slot"), log);
        return sellgui;
    }

    public static ItemStack stone(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Stone.Material")))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getguifile().getStringList("GUI.SellGui.Stone.Lore")) {
            lore.add(Data.convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Price.getPrice("STONE".toUpperCase())))
                    .replaceAll("%amount%", String.valueOf(Storage.getStorage(p, "STONE".toUpperCase()))));
        }
        meta.setLore(lore);
        meta.setDisplayName(Data.convert(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Stone.Display"))));
        item.setItemMeta(meta);
        return item;
    }


    public static ItemStack playerinfo(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.PlayerInfo.Material")))));
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(p.getName());
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getguifile().getStringList("GUI.SellGui.PlayerInfo.Lore")) {
            lore.add(Data.convert(loreconfig)
                    .replaceAll("%weight%", String.valueOf(Weight.getWeight(p)))
                    .replaceAll("%maxweight%", String.valueOf(Weight.getMaxWeight(p)))
                    .replaceAll("%log%", String.valueOf(Storage.getStorage(p, "LOG".toUpperCase())))
                    .replaceAll("%coal%", String.valueOf(Storage.getStorage(p, "COAL".toUpperCase())))
                    .replaceAll("%iron%", String.valueOf(Storage.getStorage(p, "IRON".toUpperCase())))
                    .replaceAll("%gold%", String.valueOf(Storage.getStorage(p, "GOLD".toUpperCase())))
                    .replaceAll("%diamond%", String.valueOf(Storage.getStorage(p, "DIAMOND".toUpperCase())))
                    .replaceAll("%emerald%", String.valueOf(Storage.getStorage(p, "EMERALD".toUpperCase())))
                    .replaceAll("%money%", String.valueOf(bCore.economy.getBalance(p.getPlayer()))));
        }
        meta.setLore(lore);
        meta.setDisplayName(Data.convert(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.PlayerInfo.Display")).replaceAll("%player%", p.getName())));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack log(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Log.Material")))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getguifile().getStringList("GUI.SellGui.Log.Lore")) {
            lore.add(Data.convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Price.getPrice("LOG".toUpperCase())))
                    .replaceAll("%amount%", String.valueOf(Storage.getStorage(p, "LOG".toUpperCase()))));
        }
        meta.setLore(lore);
        meta.setDisplayName(Data.convert(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Log.Display"))));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack coal(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Coal.Material")))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getguifile().getStringList("GUI.SellGui.Coal.Lore")) {
            lore.add(Data.convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Price.getPrice("COAL".toUpperCase())))
                    .replaceAll("%amount%", String.valueOf(Storage.getStorage(p, "COAL".toUpperCase()))));
        }
        meta.setLore(lore);
        meta.setDisplayName(Data.convert(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Coal.Display"))));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack iron(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Iron.Material")))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getguifile().getStringList("GUI.SellGui.Iron.Lore")) {
            lore.add(Data.convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Price.getPrice("IRON".toUpperCase())))
                    .replaceAll("%amount%", String.valueOf(Storage.getStorage(p, "IRON".toUpperCase()))));
        }
        meta.setLore(lore);
        meta.setDisplayName(Data.convert(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Iron.Display"))));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack gold(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Gold.Material")))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getguifile().getStringList("GUI.SellGui.Gold.Lore")) {
            lore.add(Data.convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Price.getPrice("GOLD".toUpperCase())))
                    .replaceAll("%amount%", String.valueOf(Storage.getStorage(p, "GOLD".toUpperCase()))));
        }
        meta.setLore(lore);
        meta.setDisplayName(Data.convert(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Gold.Display"))));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack diamond(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Diamond.Material")))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getguifile().getStringList("GUI.SellGui.Diamond.Lore")) {
            lore.add(Data.convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Price.getPrice("DIAMOND".toUpperCase())))
                    .replaceAll("%amount%", String.valueOf(Storage.getStorage(p, "DIAMOND".toUpperCase()))));
        }
        meta.setLore(lore);
        meta.setDisplayName(Data.convert(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Diamond.Display"))));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack emerald(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Emerald.Material")))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getguifile().getStringList("GUI.SellGui.Emerald.Lore")) {
            lore.add(Data.convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Price.getPrice("EMERALD".toUpperCase())))
                    .replaceAll("%amount%", String.valueOf(Storage.getStorage(p, "EMERALD".toUpperCase()))));
        }
        meta.setLore(lore);
        meta.setDisplayName(Data.convert(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Emerald.Display"))));
        item.setItemMeta(meta);
        return item;
    }

}
