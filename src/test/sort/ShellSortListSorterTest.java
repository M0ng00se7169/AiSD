package test.sort;

import main.sort.Comparator;
import main.sort.ListSorter;
import main.sort.ShellSortListSorter;

public class ShellSortListSorterTest extends AbstractListSorterTest {
    @Override
    protected ListSorter createListSorter(Comparator comparator) {
        return new ShellSortListSorter(comparator);
    }
}
