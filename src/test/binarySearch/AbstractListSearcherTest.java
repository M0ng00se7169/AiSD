package test.binarySearch;

import junit.framework.TestCase;
import main.binarySearch.ListSearcher;
import main.lists.ArrayList;
import main.lists.List;
import main.sort.Comparator;
import main.sort.NaturalComparator;

import java.util.Collections;

public abstract class AbstractListSearcherTest extends TestCase {

    private static final Object[] VALIUES =
            { "B", "C", "D", "F", "H", "I", "J", "K", "L", "M", "P", "Q" };

    private ListSearcher searcher;
    private List list;

    protected abstract ListSearcher createSearcher(Comparator comparator);

    protected void setUp() throws Exception {
        super.setUp();

        this.searcher = createSearcher(NaturalComparator.INSTANCE);
        this.list = new ArrayList();
        list.add(VALIUES);
    }
}
