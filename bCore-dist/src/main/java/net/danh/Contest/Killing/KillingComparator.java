package net.danh.Contest.Killing;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class KillingComparator implements Comparator<KillingManager> {

    @Override
    public int compare(@NotNull KillingManager o1, @NotNull KillingManager o2) {
        int winsComparator = Integer.compare(o1.killing, o2.killing);
        return winsComparator == 0 ? Integer.compare(o1.killing, o2.killing) : winsComparator;
    }
}