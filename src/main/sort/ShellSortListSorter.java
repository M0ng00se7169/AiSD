package main.sort;

import main.lists.List;

public class ShellSortListSorter implements ListSorter {
    private final Comparator comparator;
    private final int[] increments = {121, 40, 13, 4, 1};

    public ShellSortListSorter(Comparator comparator) {
        assert comparator != null : "Nie określono komparatora";

        this.comparator = comparator;
    }

    @Override
    public List sort(List list) {
        assert list != null : "nie określono listy";

        for (int i = 0; i < increments.length; ++i) {
            int increment = increments[i];
            hSort(list, increment);
        }

        return list;
    }

    private void hSort(List list, int increment) {
        if (list.size() < (increment * 2)) {
            return;
        }

        for (int i = 0; i < increment; ++i) {
            sortSublist(list, i, increment);
        }
    }

    private void sortSublist(List list, int startIndex, int increment) {
        for (int i = startIndex + increment; i < list.size(); i += increment) {
            Object value = list.get(i);
            int j;
            for (j = i; j > startIndex; j -= increment) {
                Object previousValue = list.get(j - increment);
                if (comparator.compare(value, previousValue) >= 0) {
                    break;
                }
                list.set(j, previousValue);
            }
            list.set(j, value);
        }
    }
}
