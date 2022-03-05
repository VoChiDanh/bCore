package net.danh.main.Manager;

import net.danh.main.bCore;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Items {

    public static @NotNull Integer getPrice(String name) {
        return Data.getitemfile().getInt("Price." + name);
    }

    @Deprecated(since = "4/3/2022")
    public static @NotNull Integer getBasePrice(String name) {
        return Data.getitemfile().getInt("BasePrice." + name);
    }

    public static void setPrice(String name, Integer price) {
        Data.getitemfile().set("Price." + name, price);
        Data.saveitems();
    }

    @Deprecated(since = "4/3/2022")
    public static void setBasePrice(String name, Integer price) {
        Data.getitemfile().set("BasePrice." + name, price);
        Data.saveitems();
    }

    public static void addPrice(String name, Integer price) {
        Data.getitemfile().set("Price." + name, getPrice(name) + price);
        Data.saveitems();
    }


    @Deprecated(since = "4/3/2022")
    public static void addBasePrice(String name, Integer price) {
        Data.getitemfile().set("BasePrice." + name, getBasePrice(name) + price);
        Data.saveitems();
    }

    public static void removePrice(String name, Integer price) {
        Data.getitemfile().set("Price." + name, getPrice(name) - price);
        if (getPrice(name) <= 0) {
            addPrice(name, 1);
            Data.saveitems();
        } else {
            Data.saveitems();
        }

    }

    @Deprecated(since = "4/3/2022")
    public static void removeBasePrice(String name, Integer price) {
        Data.getitemfile().set("BasePrice." + name, getBasePrice(name) - price);
        if (getBasePrice(name) == 0) {
            addBasePrice(name, 1);
            Data.saveitems();
        }
    }

    public static String getName(String material) {
        return Data.getitemfile().getString("Name." + material);
    }

    public static void Sell(Player p, @NotNull String name, Integer number) {
        if (Storage.getStorage(p, name.toUpperCase()) >= number && Weight.getWeight(p) >= number) {
            Storage.removeStorage(p, name, number);
            Weight.removeWeight(p, number);
            Data.savestorage();
            Data.savedata();
            int money = getPrice(name) * number;
            EconomyResponse r = bCore.economy.depositPlayer(p.getPlayer(), money);
            if (r.transactionSuccess()) {
                p.sendMessage(Data.convert("&a+ " + money + "$"));
            }
        } else {
            p.sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("NOT_ENOUGH_MATERIAL"))));
        }
    }

    public static void Take(Player p, @NotNull String name, Integer number) {
        if (getName(name) != null) {
            if (Storage.getStorage(p, getName(name)) >= number * 10 && Weight.getWeight(p) >= number * 10) {
                Storage.removeStorage(p, getName(name), number * 10);
                Weight.removeWeight(p, number * 10);
                ItemStack items = new ItemStack(Objects.requireNonNull(Material.getMaterial(name)), number);
                p.getInventory().addItem(items);
                Data.savestorage();
                Data.savedata();
            } else {
                p.sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("NOT_ENOUGH_MATERIAL"))));
            }
        }
    }
}
