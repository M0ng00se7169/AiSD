package test.sort;

import main.sort.Comparator;
import main.sort.ListSorter;
import main.sort.MergeSortListSorter;

public class MergeSortListSorterTest extends AbstractListSorterTest {
    @Override
    protected ListSorter createListSorter(Comparator comparator) {
        return new MergeSortListSorter(comparator);
    }
}
