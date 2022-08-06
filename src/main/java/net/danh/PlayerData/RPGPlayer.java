package net.danh.PlayerData;

import net.Indyuce.mmoitems.comp.rpg.RPGHandler;
import net.danh.Manager.Power;
import org.jetbrains.annotations.NotNull;

public class RPGPlayer implements RPGHandler {
    @Override
    public net.Indyuce.mmoitems.api.player.RPGPlayer getInfo(net.Indyuce.mmoitems.api.player.PlayerData playerData) {
        return new PlayerData(playerData);
    }

    @Override
    public void refreshStats(net.Indyuce.mmoitems.api.player.PlayerData playerData) {

    }

    public static class PlayerData extends net.Indyuce.mmoitems.api.player.RPGPlayer {

        public PlayerData(@NotNull net.Indyuce.mmoitems.api.player.PlayerData playerData) {
            super(playerData);
        }

        @Override
        public int getLevel() {
            return net.danh.Manager.PlayerData.getLevel(getPlayer());
        }

        @Override
        public String getClassName() {
            return "bClass";
        }

        @Override
        public double getMana() {
            return Power.getPower(getPlayer());
        }

        @Override
        public double getStamina() {
            return getPlayer().getFoodLevel();
        }

        @Override
        public void setMana(double v) {
            Power.setPower(getPlayer(), (int) v);
        }

        @Override
        public void setStamina(double v) {
            getPlayer().setFoodLevel((int) v);
        }
    }
}
