package net.danh.jobs.Manager;

import org.bukkit.entity.Player;

public class Storage {

    private static Storage instance;

    public static Storage getInstance() {

        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }


    public double getStorage(Player p, String name) {
        return Data.getInstance().getstoragefile().getInt("players." + p.getName() + ".Blocks." + name);
    }

    public void setStorage(Player p, String name, Double number) {
        Data.getInstance().getstoragefile().set("players." + p.getName() + ".Blocks." + name, number);
        Data.getInstance().savestorage();
    }

    public void addStorage(Player p, String name, Double number) {
        Data.getInstance().getstoragefile().set("players." + p.getName() + ".Blocks." + name, getStorage(p, name) + number);
        p.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguagefile().getString("ADD_STORAGE")
                .replaceAll("%amount%", String.valueOf(number))
                .replaceAll("%block%", name.toLowerCase())
                .replaceAll("%weight%", String.valueOf(Weight.getInstance().getWeight(p)))
                .replaceAll("%maxweight%", String.valueOf(Weight.getInstance().getMaxWeight(p)))));
        Data.getInstance().savestorage();
    }

    public void removeStorage(Player p, String name, Double number) {
        Data.getInstance().getstoragefile().set("players." + p.getName() + ".Blocks." + name, getStorage(p, name) - number);
        p.sendMessage(Data.getInstance().convert(Data.getInstance().getlanguagefile().getString("REMOVE_STORAGE")
                .replaceAll("%amount%", String.valueOf(number))
                .replaceAll("%block%", name.toLowerCase())
                .replaceAll("%weight%", String.valueOf(Weight.getInstance().getWeight(p) - number))
                .replaceAll("%maxweight%", String.valueOf(Weight.getInstance().getMaxWeight(p)))));
        Data.getInstance().savestorage();
    }
}
