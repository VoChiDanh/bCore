package net.danh.main.Manager;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class CheckMaterial {


    public static boolean isStone(@NotNull Material material) {
        return material.name().contains("STONE");
    }

    public static boolean isCoal(@NotNull Material material) {
        return material.name().contains("COAL");
    }

    public static boolean isIron(@NotNull Material material) {
        return material.name().contains("IRON");
    }

    public static boolean isGold(@NotNull Material material) {
        return material.name().contains("GOLD");
    }

    public static boolean isDiamond(@NotNull Material material) {
        return material.name().contains("DIAMOND");
    }

    public static boolean isEmerald(@NotNull Material material) {
        return material.name().contains("EMERALD");
    }

    public static boolean isLog(@NotNull Material material) {
        return material.name().contains("LOG");
    }

    public static boolean isPickaxe(@NotNull Material material) {
        return material.name().contains("PICKAXE");
    }

    public static boolean isAxe(@NotNull Material material) {
        return material.name().contains("AXE");
    }
}
