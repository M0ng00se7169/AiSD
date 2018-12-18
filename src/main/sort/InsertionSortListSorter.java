package main.sort;

import main.iteration.Iterator;
import main.lists.LinkedList;
import main.lists.List;

public class InsertionSortListSorter implements ListSorter {
    private final Comparator comparator;

    /**
     * Konstruktor
     * @param comparator - Comparator
     */
    public InsertionSortListSorter(Comparator comparator) {
        assert comparator != null : "Nie określono komparatora";
        this.comparator = comparator;
    }

    @Override
    public List sort(List list) {
        assert list != null : "Nie określono listy wejściowej";

        final List result = new LinkedList();
        Iterator iterator = list.iterator();

        for (iterator.first(); !iterator.isDone(); iterator.next()) {
            int slot = result.size();
            while (slot > 0) {
                if (comparator.compare(iterator.current(), result.get(slot - 1)) >= 0) {
                    break;
                }
                --slot;
            }
            result.insert(slot, iterator.current());
        }

        return list;
    }
}
