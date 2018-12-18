package test.sort;

import main.sort.Comparator;
import main.sort.ListSorter;
import main.sort.SelectionSortListSorter;

public class SelectionSortListTester extends AbstractListSorterTest {
    @Override
    protected ListSorter createListSorter(Comparator comparator) {
        return new SelectionSortListSorter(comparator);
    }
}
