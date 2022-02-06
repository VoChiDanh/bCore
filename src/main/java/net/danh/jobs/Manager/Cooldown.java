package net.danh.jobs.Manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cooldown {
    public static final int DEFAULT_COOLDOWN = 120;
    private static Cooldown instance;
    private final Map<UUID, Integer> cooldowns = new HashMap<>();

    public static Cooldown getInstance() {

        if (instance == null) {
            instance = new Cooldown();
        }
        return instance;
    }

    public void setCooldown(UUID player, int time) {
        if (time < 1) {
            cooldowns.remove(player);
        } else {
            cooldowns.put(player, time);
        }
    }

    public int getCooldown(UUID player) {
        return cooldowns.getOrDefault(player, 0);
    }
}
