package test.sort;

import main.sort.BubblesortListSorter;
import main.sort.Comparator;
import main.sort.ListSorter;

public class BubblesortListSorterTest extends AbstractListSorterTest {
    @Override
    protected ListSorter createListSorter(Comparator comparator) {
        return new BubblesortListSorter(comparator);
    }
}
