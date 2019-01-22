package test.binarySearch;

import junit.framework.TestCase;
import main.binarySearch.IterativeBinaryListSearcher;
import main.binarySearch.ListInserter;
import main.iteration.Iterator;
import main.lists.ArrayList;
import main.lists.List;
import main.sort.NaturalComparator;

public class ListInserterTest extends TestCase {

    private static final int TEST_SIZE = 1023;

    private ListInserter inserter;
    private List list;

    protected void setUp() throws Exception {
        super.setUp();

        this.inserter = new ListInserter(
                new IterativeBinaryListSearcher(NaturalComparator.INSTANCE));

        this.list = new ArrayList(TEST_SIZE);
    }

    private void verify() {
        int previousValue = Integer.MIN_VALUE;
        Iterator iterator = list.iterator();

        for (iterator.first(); !iterator.isDone(); iterator.next()) {
            int currentValue = ((Integer) iterator.current()).intValue();
            assertTrue(currentValue >= previousValue);
            previousValue = currentValue;
        }
    }

    public void testAscendingInOrderInsertion() {
        for (int i = 0; i < TEST_SIZE; ++i) {
            assertEquals(i, inserter.insert(list, new Integer(i)));
        }

        verify();
    }

    public void testDescendingInOrderInsertion() {
        for (int i = TEST_SIZE - 1; i >= 0; --i) {
            assertEquals(0, inserter.insert(list, new Integer(i)));
        }

        verify();
    }

    public void testRandomInsertion() {
        for (int i = 0; i < TEST_SIZE; ++i) {
            inserter.insert(list, new Integer((int)(TEST_SIZE * Math.random())));
        }

        verify();
    }
}
