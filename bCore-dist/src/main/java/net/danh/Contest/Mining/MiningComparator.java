package net.danh.Contest.Mining;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class MiningComparator implements Comparator<MiningManager> {

    @Override
    public int compare(@NotNull MiningManager o1, @NotNull MiningManager o2) {
        int winsComparator = Integer.compare(o1.mining, o2.mining);
        return winsComparator == 0 ? Integer.compare(o1.mining, o2.mining) : winsComparator;
    }
}