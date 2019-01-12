package main.binarySearch;

import main.lists.List;
import main.sort.Comparator;

public class RecursiveBinaryListSearcher implements ListSearcher {

    /** Komparator wyznaczający porządek elementów listy */
    private final Comparator comparator;

    /**
     * Konstruktor
     * @param comparator - Komparator
     */
    public RecursiveBinaryListSearcher(Comparator comparator) {
        assert comparator != null : "Nie określono komparatora";
        this.comparator = comparator;
    }

    @Override
    public int search(List list, Object key) {
        assert list != null : "nie określono listy";

        return searchRecursively(list, key, 0, list.size() - 1);
    }

    private int searchRecursively(List list, Object key, int lowerIndex, int upperIndex) {
        assert list != null : "Nie określono listy";

        if (lowerIndex > upperIndex) {
            return -(lowerIndex + 1);
        }

        int index = lowerIndex + (upperIndex - lowerIndex) / 2;
        int cmp = comparator.compare(key, list.get(index));

        if (cmp < 0) {
            index = searchRecursively(list, key, lowerIndex, index - 1);
        } else if (cmp > 0) {
            index = searchRecursively(list, key, index + 1, upperIndex);
        }

        return index;
    }
}
