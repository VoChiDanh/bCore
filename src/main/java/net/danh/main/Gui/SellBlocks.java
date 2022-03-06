package net.danh.main.Gui;

import net.danh.main.Manager.Data;
import net.danh.main.Manager.Items;
import net.danh.main.Manager.Storage;
import net.danh.main.Manager.Weight;
import net.danh.main.bCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SellBlocks implements Listener {

    public static @NotNull Inventory getSellGui(Player p) {
        Inventory sellgui = Bukkit.createInventory(p, Data.getguifile().getInt("GUI.SellGui.Size"), Objects.requireNonNull(Data.getguifile().getString(Data.convert("GUI.SellGui.Title"))));
        updateSellGui(p, sellgui);
        return sellgui;
    }

    private static void updateSellGui(Player p, Inventory inv) {
        new BukkitRunnable() {
            @Override
            public void run() {
                String inventory = p.getOpenInventory().getTitle();
                if (inventory.equals(Objects.requireNonNull(Data.getguifile().getString(Data.convert("GUI.SellGui.Title"))))) {
                    ItemStack coal = coal(p);
                    ItemStack iron = iron(p);
                    ItemStack gold = gold(p);
                    ItemStack diamond = diamond(p);
                    ItemStack emerald = emerald(p);
                    ItemStack log = log(p);
                    ItemStack player = playerinfo(p);
                    ItemStack stone = stone(p);
                    inv.setItem(Data.getguifile().getInt("GUI.SellGui.Coal.Slot"), coal);
                    inv.setItem(Data.getguifile().getInt("GUI.SellGui.Stone.Slot"), stone);
                    inv.setItem(Data.getguifile().getInt("GUI.SellGui.Iron.Slot"), iron);
                    inv.setItem(Data.getguifile().getInt("GUI.SellGui.Gold.Slot"), gold);
                    inv.setItem(Data.getguifile().getInt("GUI.SellGui.PlayerInfo.Slot"), player);
                    inv.setItem(Data.getguifile().getInt("GUI.SellGui.Diamond.Slot"), diamond);
                    inv.setItem(Data.getguifile().getInt("GUI.SellGui.Emerald.Slot"), emerald);
                    inv.setItem(Data.getguifile().getInt("GUI.SellGui.Log.Slot"), log);
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(bCore.getInstance(), 20L, 20L);
    }

    public static @NotNull ItemStack stone(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Stone.Material")))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getguifile().getStringList("GUI.SellGui.Stone.Lore")) {
            lore.add(Data.convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Items.getPrice(Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Stone.Material"))))))
                    .replaceAll("%amount%", String.valueOf(Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Stone.Material"))))))
                    .replaceAll("%left%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Left")))
                    .replaceAll("%right%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Right")))
                    .replaceAll("%shift_left%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Shift_Left")))
                    .replaceAll("%shift_right%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Shift_Right"))));
        }
        Objects.requireNonNull(meta).setLore(lore);
        meta.setDisplayName(Data.convert(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Stone.Display"))));
        item.setItemMeta(meta);
        return item;
    }


    public static @NotNull ItemStack playerinfo(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.PlayerInfo.Material")))));
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        Objects.requireNonNull(meta).setOwningPlayer(p);
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getguifile().getStringList("GUI.SellGui.PlayerInfo.Lore")) {
            lore.add(Data.convert(loreconfig)
                    .replaceAll("%weight%", String.valueOf(Weight.getWeight(p)))
                    .replaceAll("%maxweight%", String.valueOf(Weight.getMaxWeight(p)))
                    .replaceAll("%log%", String.valueOf(Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Log.Material"))))))
                    .replaceAll("%stone%", String.valueOf(Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Stone.Material"))))))
                    .replaceAll("%coal%", String.valueOf(Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Coal.Material"))))))
                    .replaceAll("%iron%", String.valueOf(Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Iron.Material"))))))
                    .replaceAll("%gold%", String.valueOf(Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Gold.Material"))))))
                    .replaceAll("%diamond%", String.valueOf(Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Diamond.Material"))))))
                    .replaceAll("%emerald%", String.valueOf(Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Emerald.Material"))))))
                    .replaceAll("%money%", String.valueOf(bCore.economy.getBalance(p.getPlayer()))));
        }
        meta.setLore(lore);
        meta.setDisplayName(Data.convert(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.PlayerInfo.Display")).replaceAll("%player%", p.getName())));
        item.setItemMeta(meta);
        return item;
    }

    public static @NotNull ItemStack log(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Log.Material")))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getguifile().getStringList("GUI.SellGui.Log.Lore")) {
            lore.add(Data.convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Items.getPrice(Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Log.Material"))))))
                    .replaceAll("%amount%", String.valueOf(Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Log.Material"))))))
                    .replaceAll("%left%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Left")))
                    .replaceAll("%right%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Right")))
                    .replaceAll("%shift_left%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Shift_Left")))
                    .replaceAll("%shift_right%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Shift_Right"))));
        }
        Objects.requireNonNull(meta).setLore(lore);
        meta.setDisplayName(Data.convert(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Log.Display"))));
        item.setItemMeta(meta);
        return item;
    }

    public static @NotNull ItemStack coal(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Coal.Material")))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getguifile().getStringList("GUI.SellGui.Coal.Lore")) {
            lore.add(Data.convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Items.getPrice(Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Coal.Material"))))))
                    .replaceAll("%amount%", String.valueOf(Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Coal.Material"))))))
                    .replaceAll("%left%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Left")))
                    .replaceAll("%right%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Right")))
                    .replaceAll("%shift_left%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Shift_Left")))
                    .replaceAll("%shift_right%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Shift_Right"))));
        }
        Objects.requireNonNull(meta).setLore(lore);
        meta.setDisplayName(Data.convert(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Coal.Display"))));
        item.setItemMeta(meta);
        return item;
    }

    public static @NotNull ItemStack iron(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Iron.Material")))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getguifile().getStringList("GUI.SellGui.Iron.Lore")) {
            lore.add(Data.convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Items.getPrice(Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Iron.Material"))))))
                    .replaceAll("%amount%", String.valueOf(Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Iron.Material"))))))
                    .replaceAll("%left%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Left")))
                    .replaceAll("%right%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Right")))
                    .replaceAll("%shift_left%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Shift_Left")))
                    .replaceAll("%shift_right%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Shift_Right"))));
        }
        Objects.requireNonNull(meta).setLore(lore);
        meta.setDisplayName(Data.convert(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Iron.Display"))));
        item.setItemMeta(meta);
        return item;
    }

    public static @NotNull ItemStack gold(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Gold.Material")))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getguifile().getStringList("GUI.SellGui.Gold.Lore")) {
            lore.add(Data.convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Items.getPrice(Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Gold.Material"))))))
                    .replaceAll("%amount%", String.valueOf(Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Gold.Material"))))))
                    .replaceAll("%left%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Left")))
                    .replaceAll("%right%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Right")))
                    .replaceAll("%shift_left%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Shift_Left")))
                    .replaceAll("%shift_right%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Shift_Right"))));
        }
        Objects.requireNonNull(meta).setLore(lore);
        meta.setDisplayName(Data.convert(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Gold.Display"))));
        item.setItemMeta(meta);
        return item;
    }

    public static @NotNull ItemStack diamond(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Diamond.Material")))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getguifile().getStringList("GUI.SellGui.Diamond.Lore")) {
            lore.add(Data.convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Items.getPrice(Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Diamond.Material"))))))
                    .replaceAll("%amount%", String.valueOf(Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Diamond.Material"))))))
                    .replaceAll("%left%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Left")))
                    .replaceAll("%right%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Right")))
                    .replaceAll("%shift_left%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Shift_Left")))
                    .replaceAll("%shift_right%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Shift_Right"))));
        }
        Objects.requireNonNull(meta).setLore(lore);
        meta.setDisplayName(Data.convert(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Diamond.Display"))));
        item.setItemMeta(meta);
        return item;
    }

    public static @NotNull ItemStack emerald(Player p) {
        ItemStack item = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Emerald.Material")))));
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setItemMeta(meta);
        for (String loreconfig : Data.getguifile().getStringList("GUI.SellGui.Emerald.Lore")) {
            lore.add(Data.convert(loreconfig)
                    .replaceAll("%price%", String.valueOf(Items.getPrice(Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Emerald.Material"))))))
                    .replaceAll("%amount%", String.valueOf(Storage.getStorage(p, Items.getName(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Emerald.Material"))))))
                    .replaceAll("%left%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Left")))
                    .replaceAll("%right%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Right")))
                    .replaceAll("%shift_left%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Shift_Left")))
                    .replaceAll("%shift_right%", String.valueOf(Data.getguifile().getInt("GUI.SellGui.Click.Shift_Right"))));
        }
        Objects.requireNonNull(meta).setLore(lore);
        meta.setDisplayName(Data.convert(Objects.requireNonNull(Data.getguifile().getString("GUI.SellGui.Emerald.Display"))));
        item.setItemMeta(meta);
        return item;
    }

}
