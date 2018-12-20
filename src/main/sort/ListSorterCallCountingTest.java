package main.sort;

import junit.framework.TestCase;
import main.lists.ArrayList;
import main.lists.List;

public class ListSorterCallCountingTest extends TestCase {
    private final static int TEST_SIZE = 1000;

    private final List sortedArrayList = new ArrayList(TEST_SIZE);
    private final List reverseArrayList = new ArrayList(TEST_SIZE);
    private final List randomArrayList = new ArrayList(TEST_SIZE);

    private CallCountingComparator comparator;

    protected void setUp() throws Exception {
        super.setUp();
        comparator = new CallCountingComparator(NaturalComparator.INSTANCE);

        for (int i = 1; i < TEST_SIZE; ++i) {
            sortedArrayList.add(i);
        }

        for (int i = TEST_SIZE; i > 0; --i) {
            reverseArrayList.add(i);
        }

        for (int i = 1; i < TEST_SIZE; ++i) {
            randomArrayList.add(TEST_SIZE * Math.random());
        }
    }

    public void testReverseCaseBubblesort() {
        new BubbleSortListSorter(comparator).sort(reverseArrayList);
        reportCalls();
    }

    public void testReverseCaseSelectionSort() {
        new SelectionSortListSorter(comparator).sort(reverseArrayList);
        reportCalls();
    }

    public void testReverseCaseInsertSort() {
        new InsertionSortListSorter(comparator).sort(reverseArrayList);
        reportCalls();
    }

    public void testDirectCaseBubblesort() {
        new BubbleSortListSorter(comparator).sort(sortedArrayList);
        reportCalls();
    }

    public void testDirectCaseSelectionSort() {
        new SelectionSortListSorter(comparator).sort(sortedArrayList);
        reportCalls();
    }

    public void testDirectCaseInsertSort() {
        new InsertionSortListSorter(comparator).sort(sortedArrayList);
        reportCalls();
    }

    public void testRandomCaseBubblesort() {
        new BubbleSortListSorter(comparator).sort(randomArrayList);
        reportCalls();
    }

    public void testRandomCaseSelectionSort() {
        new SelectionSortListSorter(comparator).sort(randomArrayList);
        reportCalls();
    }

    public void testRandomCaseInsertSort() {
        new InsertionSortListSorter(comparator).sort(randomArrayList);
        reportCalls();
    }

    private void reportCalls() {
        System.out.println(getName() + ": " + comparator.getCallCount() + " wywolan");
    }
}
