package net.danh.Contest.Killing;

import org.bukkit.entity.Player;

public class KillingManager {

    protected int killing;
    protected Player player;

    public KillingManager(Player player, int killing) {
        this.player = player;
        this.killing = killing;
    }

    public Player getPlayer() {
        return player;
    }

    public int getKilling() {
        return killing;
    }
}
