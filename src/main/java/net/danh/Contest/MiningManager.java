package net.danh.Contest;

import org.bukkit.entity.Player;

public class MiningManager {

    protected int mining;
    protected Player player;

    public MiningManager(Player player, int mining) {
        this.player = player;
        this.mining = mining;
    }

    public Player getPlayer() {
        return player;
    }

    public int getMining() {
        return mining;
    }
}
