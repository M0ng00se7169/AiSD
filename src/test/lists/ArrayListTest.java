package test.lists;

import main.lists.ArrayList;
import main.lists.List;

public class ArrayListTest extends AbstractListTestCase {
    @Override
    protected List createList() {
        return new ArrayList();
    }

    public void testInsertIntoEmptyList() {
        List list = createList();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        list.insert(0, VAULE_A);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertSame(VAULE_A, list.get(0));
    }

}
