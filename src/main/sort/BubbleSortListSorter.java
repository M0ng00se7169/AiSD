package main.sort;

import main.lists.List;

public class BubbleSortListSorter implements ListSorter {
    private final Comparator comparator;

    /**
     * Konstruktor
     * @param comparator = Comparator
     */
    public BubbleSortListSorter(Comparator comparator) {
        assert comparator != null : "Nie określono komparatora";
        this.comparator = comparator;
    }

    @Override
    public List sort(List list) {
        assert list != null : "Nie określono listy wejściowej";

        int size = list.size();

        for (int pass = 1; pass < size; ++pass) {
            for (int left = 0; left < (size - pass); ++left) {
                int right = left + 1;
                if (comparator.compare(list.get(left), list.get(right)) > 0) {
                    swap(list, left, right);
                }
            }
        }

        return list;
    }

    private void swap(List list, int left, int right) {
        Object temp = list.get(left);
        list.set(left, list.get(right));
        list.set(right, temp);
    }
}
