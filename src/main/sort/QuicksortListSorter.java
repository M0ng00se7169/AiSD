package main.sort;

import main.lists.List;

public class QuicksortListSorter implements ListSorter {
    private final Comparator comparator;

    /**
     * Konstruktor
     * @param comparator - Comparator
     */
    public QuicksortListSorter(Comparator comparator) {
        assert comparator != null : "nie określono komparatora";

        this.comparator = comparator;
    }

    @Override
    public List sort(List list) {
        assert list != null : "nie określono listy";

        quicksort(list, 0, list.size() - 1);

        return list;
    }

    private void quicksort(List list, int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex >= list.size()) {
            return;
        }
        if (endIndex <= startIndex) {
            return;
        }

        Object value = list.get(endIndex); // Ostatni element jest dzielącym

        int partition = partition(list, value, startIndex, endIndex - 1);
        if (comparator.compare(list.get(partition), value) > 0) {
            ++partition;
        }

        swap(list, partition, endIndex);

        quicksort(list, startIndex, partition-1);
        quicksort(list, partition + 1, endIndex);
    }

    private int partition(List list, Object value, int leftIndex, int rightIndex) {
        int left = leftIndex;
        int right = rightIndex;

        while (left < right) {
            if (comparator.compare(list.get(left), value) < 0) {
                ++left;
                continue;
            }
            if (comparator.compare(list.get(right), value) >= 0) {
                --right;
                continue;
            }
            if (left > right) {
                swap(list, left, right);
            }
            ++left;
        }

        return left;
    }

    private void swap(List list, int left, int right) {
        if (left == right) {
            return;
        }
        Object temp = list.get(left);
        list.set(left, list.get(right));
        list.set(right, temp);
    }
}
