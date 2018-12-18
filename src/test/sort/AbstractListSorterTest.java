package test.sort;

import junit.framework.TestCase;
import main.iteration.Iterator;
import main.lists.LinkedList;
import main.lists.List;
import main.sort.Comparable;
import main.sort.Comparator;
import main.sort.ListSorter;
import main.sort.NaturalComparator;

public abstract class AbstractListSorterTest extends TestCase implements Comparable{
    private List unsortedList;
    private List sortedList;

    protected void setUp() throws Exception {
        unsortedList = new LinkedList();

        unsortedList.add("programowanie");
        unsortedList.add("sterowanie");
        unsortedList.add("testami");
        unsortedList.add("to");
        unsortedList.add("maly");
        unsortedList.add("krok");
        unsortedList.add("dla");
        unsortedList.add("programisty");
        unsortedList.add("lecz");
        unsortedList.add("olbrzymi");
        unsortedList.add("skok");
        unsortedList.add("w");
        unsortedList.add("dziejach");
        unsortedList.add("programowania");

        sortedList = new LinkedList();

        sortedList.add("dla");
        sortedList.add("dziejach");
        sortedList.add("krok");
        sortedList.add("lecz");
        sortedList.add("maly");
        sortedList.add("olbrzymi");
        sortedList.add("programisty");
        sortedList.add("programowania");
        sortedList.add("programowanie");
        sortedList.add("skok");
        sortedList.add("sterowanie");
        sortedList.add("testami");
        sortedList.add("to");
        sortedList.add("w");
    }

    protected void tearDown() throws Exception {
        sortedList = null;
        unsortedList = null;
    }

    protected abstract ListSorter createListSorter(Comparator comparator);

    public void testListSorterCanSortSampleList() throws ClassCastException{
        ListSorter sorter = createListSorter(NaturalComparator.INSTANCE);
        List result = sorter.sort(unsortedList);

        assertEquals(result.size(), sortedList.size());

        Iterator actual = result.iterator();
        actual.first();

        Iterator expected = sortedList.iterator();
        expected.first();

        while (!expected.isDone()) {
            assertEquals(expected.current(), actual.current());

            expected.next();
            actual.next();
        }
    }
}
