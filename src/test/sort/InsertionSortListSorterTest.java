package test.sort;

import main.sort.Comparator;
import main.sort.InsertionSortListSorter;
import main.sort.ListSorter;

public class InsertionSortListSorterTest extends AbstractListSorterTest {
    @Override
    protected ListSorter createListSorter(Comparator comparator) {
        return new InsertionSortListSorter(comparator);
    }
}
