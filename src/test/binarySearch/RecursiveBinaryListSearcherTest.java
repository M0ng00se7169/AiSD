package test.binarySearch;

import main.binarySearch.ListSearcher;
import main.binarySearch.RecursiveBinaryListSearcher;
import main.sort.Comparator;

public class RecursiveBinaryListSearcherTest extends AbstractListSearcherTest {
    @Override
    protected ListSearcher createSearcher(Comparator comparator) {
        return new RecursiveBinaryListSearcher(comparator);
    }
}
