package main.sort;

import main.iteration.Iterator;
import main.lists.ArrayList;
import main.lists.List;

public class MergeSortListSorter implements ListSorter {
    /** Komparator wyznaczający porządek sortowanych wartości */
    private final Comparator comparator;

    public MergeSortListSorter(Comparator comparator) {
        assert comparator != null : "nie określono komparatora";
        this.comparator = comparator;
    }

    @Override
    public List sort(List list) {
        assert list != null : "nie określono listy";

        return mergeSort(list, 0, list.size() - 1);
    }

    private List mergeSort(List list, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            List result = new ArrayList();
            result.add(list.get(startIndex));
            return result;
        }

        // Indeks Graniczny
        int splitIndex = startIndex + (endIndex - startIndex) / 2;

        // Sortowani Lewej połówki
        List left = mergeSort(list, startIndex, splitIndex);

        // Sortowanie prawej połówki
        List right = mergeSort(list, splitIndex + 1, endIndex);

        // łączenie posortowanych połówek
        return merge(left, right);
    }

    private List merge(List left, List right) {
        List result = new ArrayList();

        Iterator l = left.iterator();
        Iterator r = right.iterator();

        l.first();
        r.first();

        while (! (l.isDone() && r.isDone())) {
            if (l.isDone()) {
                result.add(r.current());
                r.next();
            } else if (r.isDone()) {
                result.add(l.current());
                l.next();
            } else if (comparator.compare(l.current(), r.current()) <= 0) {
                result.add(l.current());
                l.next();
            } else {
                result.add(r.current());
                r.next();
            }
        }

        return result;
    }
}
