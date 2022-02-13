package net.danh.jobs.Gui;

import com.cryptomorin.xseries.XMaterial;
import net.danh.jobs.Manager.Files;
import net.danh.jobs.Manager.Price;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SellBlocks implements Listener {


    private static SellBlocks instance;

    public static SellBlocks getInstance() {

        if (instance == null) {
            instance = new SellBlocks();
        }
        return instance;
    }

    public Inventory getSellGui(Player p) {
        Inventory sellgui = Bukkit.createInventory(p, 18, "Sell Gui");
        ItemStack coal = new ItemStack(XMaterial.COAL_ORE.parseMaterial());
        ItemMeta coalmeta = coal.getItemMeta();
        ArrayList<String> lorecoal = new ArrayList<String>();
        coal.setItemMeta(coalmeta);
        lorecoal.add("");
        lorecoal.add(Files.getInstance().convert("&aPrice: " + Price.getInstance().getPrice("COAL_ORE".toUpperCase()) + "$/kg"));
        lorecoal.add("");
        lorecoal.add(Files.getInstance().convert("&aYou have &6" + Files.getInstance().getStorage(p, "COAL_ORE".toUpperCase()) + "&a Coal Ore in Storage"));
        lorecoal.add("");
        lorecoal.add(Files.getInstance().convert("&aLeft click to sell 1kg"));
        lorecoal.add(Files.getInstance().convert("&aRight click to sell 10kg"));
        lorecoal.add("");
        lorecoal.add(Files.getInstance().convert("&aShift + Left click to sell 64kg"));
        lorecoal.add(Files.getInstance().convert("&aShift + Right click to sell 640kg"));
        coalmeta.setLore(lorecoal);
        coalmeta.setDisplayName(Files.getInstance().convert("&7Coal Ore"));
        coal.setItemMeta(coalmeta);

        ItemStack iron = new ItemStack(XMaterial.IRON_ORE.parseMaterial());
        ItemMeta ironmeta = iron.getItemMeta();
        ArrayList<String> loreiron = new ArrayList<String>();
        iron.setItemMeta(ironmeta);
        loreiron.add("");
        loreiron.add(Files.getInstance().convert("&aPrice: " + Price.getInstance().getPrice("IRON_ORE".toUpperCase()) + "$/kg"));
        loreiron.add("");
        loreiron.add(Files.getInstance().convert("&aYou have &6" + Files.getInstance().getStorage(p, "IRON_ORE".toUpperCase()) + "&a Iron Ore in Storage"));
        loreiron.add("");
        loreiron.add(Files.getInstance().convert("&aLeft click to sell 1kg"));
        loreiron.add(Files.getInstance().convert("&aRight click to sell 10kg"));
        loreiron.add("");
        loreiron.add(Files.getInstance().convert("&aShift + Left click to sell 64kg"));
        loreiron.add(Files.getInstance().convert("&aShift + Right click to sell 640kg"));
        ironmeta.setLore(loreiron);
        ironmeta.setDisplayName(Files.getInstance().convert("&7Iron Ore"));
        iron.setItemMeta(ironmeta);

        ItemStack gold = new ItemStack(XMaterial.GOLD_ORE.parseMaterial());
        ItemMeta goldmeta = gold.getItemMeta();
        ArrayList<String> loregold = new ArrayList<String>();
        gold.setItemMeta(goldmeta);
        loregold.add("");
        loregold.add(Files.getInstance().convert("&aPrice: " + Price.getInstance().getPrice("GOLD_ORE".toUpperCase()) + "$/kg"));
        loregold.add("");
        loregold.add(Files.getInstance().convert("&aYou have &6" + Files.getInstance().getStorage(p, "GOLD_ORE".toUpperCase()) + "&a Gold Ore in Storage"));
        loregold.add("");
        loregold.add(Files.getInstance().convert("&aLeft click to sell 1kg"));
        loregold.add(Files.getInstance().convert("&aRight click to sell 10kg"));
        loregold.add("");
        loregold.add(Files.getInstance().convert("&aShift + Left click to sell 64kg"));
        loregold.add(Files.getInstance().convert("&aShift + Right click to sell 640kg"));
        goldmeta.setLore(loregold);
        goldmeta.setDisplayName(Files.getInstance().convert("&6Gold Ore"));
        gold.setItemMeta(goldmeta);

        ItemStack diamond = new ItemStack(XMaterial.DIAMOND_ORE.parseMaterial());
        ItemMeta diamondmeta = diamond.getItemMeta();
        ArrayList<String> lorediamond = new ArrayList<String>();
        diamond.setItemMeta(diamondmeta);
        lorediamond.add("");
        lorediamond.add(Files.getInstance().convert("&aPrice: " + Price.getInstance().getPrice("DIAMOND_ORE".toUpperCase()) + "$/kg"));
        lorediamond.add("");
        lorediamond.add(Files.getInstance().convert("&aYou have &6" + Files.getInstance().getStorage(p, "DIAMOND_ORE".toUpperCase()) + "&a Diamond Ore in Storage"));
        lorediamond.add("");
        lorediamond.add(Files.getInstance().convert("&aLeft click to sell 1kg"));
        lorediamond.add(Files.getInstance().convert("&aRight click to sell 10kg"));
        lorediamond.add("");
        lorediamond.add(Files.getInstance().convert("&aShift + Left click to sell 64kg"));
        lorediamond.add(Files.getInstance().convert("&aShift + Right click to sell 640kg"));
        diamondmeta.setLore(lorediamond);
        diamondmeta.setDisplayName(Files.getInstance().convert("&bDiamond Ore"));
        diamond.setItemMeta(diamondmeta);


        ItemStack emerald = new ItemStack(XMaterial.EMERALD_ORE.parseMaterial());
        ItemMeta emeraldmeta = emerald.getItemMeta();
        ArrayList<String> loreemerald = new ArrayList<String>();
        emerald.setItemMeta(emeraldmeta);
        loreemerald.add("");
        loreemerald.add(Files.getInstance().convert("&aPrice: " + Price.getInstance().getPrice("EMERALD_ORE".toUpperCase()) + "$/kg"));
        loreemerald.add("");
        loreemerald.add(Files.getInstance().convert("&aYou have &6" + Files.getInstance().getStorage(p, "EMERALD_ORE".toUpperCase()) + "&a Emerald Ore in Storage"));
        loreemerald.add("");
        loreemerald.add(Files.getInstance().convert("&aLeft click to sell 1kg"));
        loreemerald.add(Files.getInstance().convert("&aRight click to sell 10kg"));
        loreemerald.add("");
        loreemerald.add(Files.getInstance().convert("&aShift + Left click to sell 64kg"));
        loreemerald.add(Files.getInstance().convert("&aShift + Right click to sell 640kg"));
        emeraldmeta.setLore(loreemerald);
        emeraldmeta.setDisplayName(Files.getInstance().convert("&aEmerald Ore"));
        emerald.setItemMeta(emeraldmeta);

        sellgui.setItem(0, coal);
        sellgui.setItem(2, iron);
        sellgui.setItem(4, gold);
        sellgui.setItem(6, diamond);
        sellgui.setItem(8, emerald);
        return sellgui;
    }

    public void openSellGui(Player p) {
        p.openInventory(getSellGui(p));
    }
}
