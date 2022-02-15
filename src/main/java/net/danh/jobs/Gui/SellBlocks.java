package net.danh.jobs.Gui;

import net.danh.jobs.Jobs;
import net.danh.jobs.Manager.Data;
import net.danh.jobs.Manager.Price;
import net.danh.jobs.Manager.Storage;
import net.danh.jobs.Manager.Weight;
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


    private static SellBlocks instance;

    public static SellBlocks getInstance() {

        if (instance == null) {
            instance = new SellBlocks();
        }
        return instance;
    }

    public Inventory getSellGui(Player p) {
        Inventory sellgui = Bukkit.createInventory(p, Data.getInstance().getgui().getInt("GUI.SellGui.Size"), Data.getInstance().getgui().getString(Data.getInstance().convert("GUI.SellGui.Title")));
        ItemStack coal = coal(p);
        ItemStack iron = iron(p);
        ItemStack gold = gold(p);
        ItemStack diamond = diamond(p);
        ItemStack emerald = emerald(p);
        ItemStack log = log(p);
        ItemStack player = playerinfo(p);
        ItemStack stone = stone(p);
        sellgui.setItem(Data.getInstance().getgui().getInt("GUI.SellGui.Coal.Slot"), coal);
        sellgui.setItem(Data.getInstance().getgui().getInt("GUI.SellGui.Stone.Slot"), stone);
        sellgui.setItem(Data.getInstance().getgui().getInt("GUI.SellGui.Iron.Slot"), iron);
        sellgui.setItem(Data.getInstance().getgui().getInt("GUI.SellGui.Gold.Slot"), gold);
        sellgui.setItem(Data.getInstance().getgui().getInt("GUI.SellGui.PlayerInfo.Slot"), player);
        sellgui.setItem(Data.getInstance().getgui().getInt("GUI.SellGui.Diamond.Slot"), diamond);
        sellgui.setItem(Data.getInstance().getgui().getInt("GUI.SellGui.Emerald.Slot"), emerald);
        sellgui.setItem(Data.getInstance().getgui().getInt("GUI.SellGui.Log.Slot"), log);
        return sellgui;
    }

    public ItemStack stone(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Data.getInstance().getgui().getString("GUI.SellGui.Stone.Material"))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getInstance().getgui().getStringList("GUI.SellGui.Stone.Lore")) {
            lore.add(Data.getInstance().convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Price.getInstance().getPrice("STONE".toUpperCase())))
                    .replaceAll("%amount%", String.valueOf(Storage.getInstance().getStorage(p, "STONE".toUpperCase()))));
        }
        meta.setLore(lore);
        meta.setDisplayName(Data.getInstance().convert(Data.getInstance().getgui().getString("GUI.SellGui.Stone.Display")));
        item.setItemMeta(meta);
        return item;
    }


    public ItemStack playerinfo(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Data.getInstance().getgui().getString("GUI.SellGui.PlayerInfo.Material"))));
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(p.getName());
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getInstance().getgui().getStringList("GUI.SellGui.PlayerInfo.Lore")) {
            lore.add(Data.getInstance().convert(loreconfig)
                    .replaceAll("%weight%", String.valueOf(Weight.getInstance().getWeight(p)))
                    .replaceAll("%maxweight%", String.valueOf(Weight.getInstance().getMaxWeight(p)))
                    .replaceAll("%log%", String.valueOf(Storage.getInstance().getStorage(p, "LOG".toUpperCase())))
                    .replaceAll("%coal%", String.valueOf(Storage.getInstance().getStorage(p, "COAL".toUpperCase())))
                    .replaceAll("%iron%", String.valueOf(Storage.getInstance().getStorage(p, "IRON".toUpperCase())))
                    .replaceAll("%gold%", String.valueOf(Storage.getInstance().getStorage(p, "GOLD".toUpperCase())))
                    .replaceAll("%diamond%", String.valueOf(Storage.getInstance().getStorage(p, "DIAMOND".toUpperCase())))
                    .replaceAll("%emerald%", String.valueOf(Storage.getInstance().getStorage(p, "EMERALD".toUpperCase())))
                    .replaceAll("%money%", String.valueOf(Jobs.economy.getBalance(p.getPlayer()))));
        }
        meta.setLore(lore);
        meta.setDisplayName(Data.getInstance().convert(Data.getInstance().getgui().getString("GUI.SellGui.PlayerInfo.Display").replaceAll("%player%", p.getName())));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack log(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Data.getInstance().getgui().getString("GUI.SellGui.Log.Material"))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getInstance().getgui().getStringList("GUI.SellGui.Log.Lore")) {
            lore.add(Data.getInstance().convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Price.getInstance().getPrice("LOG".toUpperCase())))
                    .replaceAll("%amount%", String.valueOf(Storage.getInstance().getStorage(p, "LOG".toUpperCase()))));
        }
        meta.setLore(lore);
        meta.setDisplayName(Data.getInstance().convert(Data.getInstance().getgui().getString("GUI.SellGui.Log.Display")));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack coal(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Data.getInstance().getgui().getString("GUI.SellGui.Coal.Material"))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getInstance().getgui().getStringList("GUI.SellGui.Coal.Lore")) {
            lore.add(Data.getInstance().convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Price.getInstance().getPrice("COAL".toUpperCase())))
                    .replaceAll("%amount%", String.valueOf(Storage.getInstance().getStorage(p, "COAL".toUpperCase()))));
        }
        meta.setLore(lore);
        meta.setDisplayName(Data.getInstance().convert(Data.getInstance().getgui().getString("GUI.SellGui.Coal.Display")));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack iron(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Data.getInstance().getgui().getString("GUI.SellGui.Iron.Material"))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getInstance().getgui().getStringList("GUI.SellGui.Iron.Lore")) {
            lore.add(Data.getInstance().convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Price.getInstance().getPrice("IRON".toUpperCase())))
                    .replaceAll("%amount%", String.valueOf(Storage.getInstance().getStorage(p, "IRON".toUpperCase()))));
        }
        meta.setLore(lore);
        meta.setDisplayName(Data.getInstance().convert(Data.getInstance().getgui().getString("GUI.SellGui.Iron.Display")));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack gold(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Data.getInstance().getgui().getString("GUI.SellGui.Gold.Material"))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getInstance().getgui().getStringList("GUI.SellGui.Gold.Lore")) {
            lore.add(Data.getInstance().convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Price.getInstance().getPrice("GOLD".toUpperCase())))
                    .replaceAll("%amount%", String.valueOf(Storage.getInstance().getStorage(p, "GOLD".toUpperCase()))));
        }
        meta.setLore(lore);
        meta.setDisplayName(Data.getInstance().convert(Data.getInstance().getgui().getString("GUI.SellGui.Gold.Display")));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack diamond(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Data.getInstance().getgui().getString("GUI.SellGui.Diamond.Material"))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getInstance().getgui().getStringList("GUI.SellGui.Diamond.Lore")) {
            lore.add(Data.getInstance().convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Price.getInstance().getPrice("DIAMOND".toUpperCase())))
                    .replaceAll("%amount%", String.valueOf(Storage.getInstance().getStorage(p, "DIAMOND".toUpperCase()))));
        }
        meta.setLore(lore);
        meta.setDisplayName(Data.getInstance().convert(Data.getInstance().getgui().getString("GUI.SellGui.Diamond.Display")));
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack emerald(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Data.getInstance().getgui().getString("GUI.SellGui.Emerald.Material"))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getInstance().getgui().getStringList("GUI.SellGui.Emerald.Lore")) {
            lore.add(Data.getInstance().convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Price.getInstance().getPrice("EMERALD".toUpperCase())))
                    .replaceAll("%amount%", String.valueOf(Storage.getInstance().getStorage(p, "EMERALD".toUpperCase()))));
        }
        meta.setLore(lore);
        meta.setDisplayName(Data.getInstance().convert(Data.getInstance().getgui().getString("GUI.SellGui.Emerald.Display")));
        item.setItemMeta(meta);
        return item;
    }

    public void openSellGui(Player p) {
        p.openInventory(getSellGui(p));
    }
}
