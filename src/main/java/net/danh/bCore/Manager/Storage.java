package net.danh.bCore.Manager;

import org.bukkit.entity.Player;

import java.util.Objects;

public class Storage {

    public static Integer getStorage(Player p, String name) {
        return Data.getstoragefile().getInt("players." + p.getName() + ".Blocks." + name);
    }

    public static void setStorage(Player p, String name, Integer number) {
        Data.getstoragefile().set("players." + p.getName() + ".Blocks." + name, number);
        Data.savestorage();
    }

    public static void addStorage(Player p, String name, Integer number) {
        Data.getstoragefile().set("players." + p.getName() + ".Blocks." + name, getStorage(p, name) + number);
        p.sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("ADD_STORAGE"))
                .replaceAll("%amount%", String.valueOf(number))
                .replaceAll("%block%", name.toLowerCase())
                .replaceAll("%weight%", String.valueOf(Weight.getWeight(p)))
                .replaceAll("%maxweight%", String.valueOf(Weight.getMaxWeight(p)))));
        Data.savestorage();
    }

    public static void removeStorage(Player p, String name, Integer number) {
        Data.getstoragefile().set("players." + p.getName() + ".Blocks." + name, getStorage(p, name) - number);
        p.sendMessage(Data.convert(Objects.requireNonNull(Data.getlanguagefile().getString("REMOVE_STORAGE"))
                .replaceAll("%amount%", String.valueOf(number))
                .replaceAll("%block%", name.toLowerCase())
                .replaceAll("%weight%", String.valueOf(Weight.getWeight(p) - number))
                .replaceAll("%maxweight%", String.valueOf(Weight.getMaxWeight(p)))));
        Data.savestorage();
    }
}
