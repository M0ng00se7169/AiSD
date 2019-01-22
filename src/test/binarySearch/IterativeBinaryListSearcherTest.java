package test.binarySearch;

import main.binarySearch.IterativeBinaryListSearcher;
import main.binarySearch.ListSearcher;
import main.sort.Comparator;

public class IterativeBinaryListSearcherTest extends AbstractListSearcherTest {

    @Override
    protected ListSearcher createSearcher(Comparator comparator) {
        return new IterativeBinaryListSearcher(comparator);
    }
}
