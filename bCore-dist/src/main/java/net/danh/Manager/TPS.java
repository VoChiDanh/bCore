package net.danh.Manager;

import net.danh.bCore;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class TPS {
    private static final String name = Bukkit.getServer().getClass().getPackage().getName();
    private static final String version = name.substring(name.lastIndexOf('.') + 1);

    public static @NotNull Class<?> getNMSClass(String className) {
        try {
            return Class.forName("net.minecraft.server." + version + "." + className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getTPS(int time) {
        try {
            double[] tps = ((double[]) bCore.tpsField.get(bCore.serverInstance));
            return bCore.format.format(tps[time]);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
