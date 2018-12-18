package test.sort;

import main.sort.Comparator;
import main.sort.ListSorter;
import main.sort.QuicksortListSorter;

public class QuickSortTest extends AbstractListSorterTest {
    @Override
    protected ListSorter createListSorter(Comparator comparator) {
        return new QuicksortListSorter(comparator);
    }
}
