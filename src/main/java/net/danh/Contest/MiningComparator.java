package net.danh.Contest;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class MiningComparator implements Comparator<TMining> {
    @Override
    public int compare(@NotNull TMining o1, @NotNull TMining o2) {
        int winsComparator = Integer.compare(o1.mining, o2.mining);
        return winsComparator == 0 ? Integer.compare(o1.mining, o2.mining) : winsComparator;
    }
}