package test.lists;

import junit.framework.TestCase;
import main.lists.List;

public abstract class AbstractListTestCase extends TestCase {
    protected static final Object VAULE_A = 'A';
    protected static final Object VAULE_B = 'B';
    protected static final Object VAULE_C = 'C';

    protected abstract List createList();
}
