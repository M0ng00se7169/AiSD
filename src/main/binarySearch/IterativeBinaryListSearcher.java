package main.binarySearch;

import main.lists.List;
import main.sort.Comparator;
import main.sort.NaturalComparator;

public class IterativeBinaryListSearcher implements ListSearcher {

    /** Komparator
     *  Wyznacza porządek elementów listy
     */
    private final Comparator comparator;

    /**
     * Konstruktor
     * @param comparator - Komparator
     */
    public IterativeBinaryListSearcher(Comparator comparator) {
        assert comparator != null : "Nie określono komparatora";
        this.comparator = comparator;
    }

    @Override
    public int search(List list, Object key) {
        assert list != null : "Nie określono listy";

        int lowerIndex = 0;
        int upperIndex = list.size() - 1;

        while (lowerIndex <= upperIndex) {
            int index = lowerIndex + (upperIndex - lowerIndex) / 2;

            int cmp = (Integer)(comparator.compare(key, list.get(index)));

            if (cmp == 0) {
                return index;
            } else if (cmp > 0) {
                upperIndex = index - 1;
            } else {
                lowerIndex = index + 1;
            }
        }

        return -(lowerIndex + 1);
    }
}
