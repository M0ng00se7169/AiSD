package test.sort;

import main.sort.BubbleSortListSorter;
import main.sort.Comparator;
import main.sort.ListSorter;

public class BubbleSortListSorterTest extends AbstractListSorterTest {
    @Override
    protected ListSorter createListSorter(Comparator comparator) {
        return new BubbleSortListSorter(comparator);
    }
}
