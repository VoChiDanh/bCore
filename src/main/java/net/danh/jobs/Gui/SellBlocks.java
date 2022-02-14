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
        Inventory sellgui = Bukkit.createInventory(p, 9, "Sell Gui");
        ItemStack coal = coal(p);
        ItemStack iron = iron(p);
        ItemStack gold = gold(p);
        ItemStack diamond = diamond(p);
        ItemStack emerald = emerald(p);
        ItemStack log = log(p);
        sellgui.setItem(1, coal);
        sellgui.setItem(2, iron);
        sellgui.setItem(3, gold);
        sellgui.setItem(4, diamond);
        sellgui.setItem(5, emerald);
        sellgui.setItem(6, log);
        return sellgui;
    }

    public ItemStack log(Player p) {
        ItemStack log = new ItemStack(XMaterial.OAK_LOG.parseMaterial());
        ItemMeta logmeta = log.getItemMeta();
        ArrayList<String> lorelog = new ArrayList<String>();
        log.setItemMeta(logmeta);
        lorelog.add("");
        lorelog.add(Files.getInstance().convert("&aPrice: " + Price.getInstance().getPrice("LOG".toUpperCase()) + "$/kg"));
        lorelog.add("");
        lorelog.add(Files.getInstance().convert("&aYou have &6" + Files.getInstance().getStorage(p, "LOG".toUpperCase()) + "&a Log in Storage"));
        lorelog.add("");
        lorelog.add(Files.getInstance().convert("&aLeft click to sell 1kg"));
        lorelog.add(Files.getInstance().convert("&aRight click to sell 10kg"));
        logmeta.setLore(lorelog);
        logmeta.setDisplayName(Files.getInstance().convert("&7Log"));
        log.setItemMeta(logmeta);
        return log;
    }

    public ItemStack coal(Player p) {
        ItemStack coal = new ItemStack(XMaterial.COAL.parseMaterial());
        ItemMeta coalmeta = coal.getItemMeta();
        ArrayList<String> lorecoal = new ArrayList<String>();
        coal.setItemMeta(coalmeta);
        lorecoal.add("");
        lorecoal.add(Files.getInstance().convert("&aPrice: " + Price.getInstance().getPrice("COAL".toUpperCase()) + "$/kg"));
        lorecoal.add("");
        lorecoal.add(Files.getInstance().convert("&aYou have &6" + Files.getInstance().getStorage(p, "COAL".toUpperCase()) + "&a Coal in Storage"));
        lorecoal.add("");
        lorecoal.add(Files.getInstance().convert("&aLeft click to sell 1kg"));
        lorecoal.add(Files.getInstance().convert("&aRight click to sell 10kg"));
        coalmeta.setLore(lorecoal);
        coalmeta.setDisplayName(Files.getInstance().convert("&7Coal"));
        coal.setItemMeta(coalmeta);
        return coal;
    }

    public ItemStack iron(Player p) {
        ItemStack iron = new ItemStack(XMaterial.IRON_INGOT.parseMaterial());
        ItemMeta ironmeta = iron.getItemMeta();
        ArrayList<String> loreiron = new ArrayList<String>();
        iron.setItemMeta(ironmeta);
        loreiron.add("");
        loreiron.add(Files.getInstance().convert("&aPrice: " + Price.getInstance().getPrice("IRON_INGOT".toUpperCase()) + "$/kg"));
        loreiron.add("");
        loreiron.add(Files.getInstance().convert("&aYou have &6" + Files.getInstance().getStorage(p, "IRON_INGOT".toUpperCase()) + "&a Iron in Storage"));
        loreiron.add("");
        loreiron.add(Files.getInstance().convert("&aLeft click to sell 1kg"));
        loreiron.add(Files.getInstance().convert("&aRight click to sell 10kg"));
        ironmeta.setLore(loreiron);
        ironmeta.setDisplayName(Files.getInstance().convert("&7Iron"));
        iron.setItemMeta(ironmeta);
        return iron;
    }

    public ItemStack gold(Player p) {

        ItemStack gold = new ItemStack(XMaterial.GOLD_INGOT.parseMaterial());
        ItemMeta goldmeta = gold.getItemMeta();
        ArrayList<String> loregold = new ArrayList<String>();
        gold.setItemMeta(goldmeta);
        loregold.add("");
        loregold.add(Files.getInstance().convert("&aPrice: " + Price.getInstance().getPrice("GOLD_INGOT".toUpperCase()) + "$/kg"));
        loregold.add("");
        loregold.add(Files.getInstance().convert("&aYou have &6" + Files.getInstance().getStorage(p, "GOLD_INGOT".toUpperCase()) + "&a Gold in Storage"));
        loregold.add("");
        loregold.add(Files.getInstance().convert("&aLeft click to sell 1kg"));
        loregold.add(Files.getInstance().convert("&aRight click to sell 10kg"));
        goldmeta.setLore(loregold);
        goldmeta.setDisplayName(Files.getInstance().convert("&6Gold"));
        gold.setItemMeta(goldmeta);
        return gold;
    }

    public ItemStack diamond(Player p) {
        ItemStack diamond = new ItemStack(XMaterial.DIAMOND.parseMaterial());
        ItemMeta diamondmeta = diamond.getItemMeta();
        ArrayList<String> lorediamond = new ArrayList<String>();
        diamond.setItemMeta(diamondmeta);
        lorediamond.add("");
        lorediamond.add(Files.getInstance().convert("&aPrice: " + Price.getInstance().getPrice("DIAMOND".toUpperCase()) + "$/kg"));
        lorediamond.add("");
        lorediamond.add(Files.getInstance().convert("&aYou have &6" + Files.getInstance().getStorage(p, "DIAMOND".toUpperCase()) + "&a Diamond in Storage"));
        lorediamond.add("");
        lorediamond.add(Files.getInstance().convert("&aLeft click to sell 1kg"));
        lorediamond.add(Files.getInstance().convert("&aRight click to sell 10kg"));
        diamondmeta.setLore(lorediamond);
        diamondmeta.setDisplayName(Files.getInstance().convert("&bDiamond"));
        diamond.setItemMeta(diamondmeta);
        return diamond;
    }

    public ItemStack emerald(Player p) {
        ItemStack emerald = new ItemStack(XMaterial.EMERALD.parseMaterial());
        ItemMeta emeraldmeta = emerald.getItemMeta();
        ArrayList<String> loreemerald = new ArrayList<String>();
        emerald.setItemMeta(emeraldmeta);
        loreemerald.add("");
        loreemerald.add(Files.getInstance().convert("&aPrice: " + Price.getInstance().getPrice("EMERALD".toUpperCase()) + "$/kg"));
        loreemerald.add("");
        loreemerald.add(Files.getInstance().convert("&aYou have &6" + Files.getInstance().getStorage(p, "EMERALD".toUpperCase()) + "&a Emerald in Storage"));
        loreemerald.add("");
        loreemerald.add(Files.getInstance().convert("&aLeft click to sell 1kg"));
        loreemerald.add(Files.getInstance().convert("&aRight click to sell 10kg"));
        emeraldmeta.setLore(loreemerald);
        emeraldmeta.setDisplayName(Files.getInstance().convert("&aEmerald"));
        emerald.setItemMeta(emeraldmeta);
        return emerald;
    }

    public void openSellGui(Player p) {
        p.openInventory(getSellGui(p));
    }
}
