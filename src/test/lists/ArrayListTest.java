package test.lists;

import main.iteration.Iterator;
import main.iteration.IteratorOutOfBoundsException;
import main.lists.ArrayList;
import main.lists.List;


public class ArrayListTest extends AbstractListTestCase {
    @Override
    protected List createList() {
        return new ArrayList();
    }


    // Wstawianie elementu do pustej tablicy
    public void testInsertIntoEmptyList() {
        List list = createList();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        list.insert(0, VAULE_A);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertSame(VAULE_A, list.get(0));
    }

    // Wstawianie elementu pomiedza dwoma istniejacymi elementami
    public void testInsertBetweenElements() {
        List list = createList();

        list.insert(0, VAULE_A);
        list.insert(1, VAULE_B);
        list.insert(2, VAULE_C);
        assertEquals(3, list.size());

        assertSame(VAULE_A, list.get(0));
        assertSame(VAULE_B, list.get(1));
        assertSame(VAULE_C, list.get(2));
    }

    // Wstawianie elementu na poczatek listy
    public void testInsertBeforeFirstElement() {
        List list = createList();

        list.insert(0, VAULE_A); // Wstawianie na poczatek
        list.insert(0, VAULE_B); // Wstawianie na poczatek

        assertEquals(2, list.size());
        assertSame(VAULE_B, list.get(0));
        assertSame(VAULE_A, list.get(1));
    }

    // Wstawianie elementu na koniec listy
    public void testInsertAfterLastElement() {
        List list = createList();

        list.insert(0, VAULE_A); // Wstawianie na poczatek
        list.insert(1, VAULE_B); // Wstawianie na poczatek

        assertEquals(2, list.size());
        assertSame(VAULE_A, list.get(0));
        assertSame(VAULE_B, list.get(1));
    }

    public void testIsertOutOfBounds() {
        List list = createList();

        try {
            list.insert(-1, VAULE_A);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // zachowanie oczekiwane
        }

        try {
            list.insert(1, VAULE_B);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // zachowanie oczekiwane
        }
    }

    // Dolaczenie elementu do listy
    public void testAdd() {
        List list = createList();

        list.add(VAULE_A);
        list.add(VAULE_B);
        list.add(VAULE_C);

        assertSame(3, list.size());
        assertSame(VAULE_A, list.get(0));
        assertSame(VAULE_B, list.get(1));
        assertSame(VAULE_C, list.get(2));
    }

    public void testSet() {
        List list = createList();
        list.insert(0, VAULE_A);
        assertSame(VAULE_A, list.get(0));

        assertSame(VAULE_A, list.set(0, VAULE_B));
        assertSame(VAULE_B, list.get(0));
    }

    public void testGetOutOfBounds() {
        List list = createList();
        try {
            list.get(-1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // zachowanie oczekiwane
        }

        try {
            list.get(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // zachowanie oczekiwane
        }

        list.add(VAULE_A);

        try {
            list.get(1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // zachowanie oczekiwane
        }
    }

    public void testSetOutOfBounds() {
        List list = createList();

        try {
            list.set(-1, VAULE_A);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // zachowanie oczekiwane
        }

        try {
            list.set(0, VAULE_B);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // zachowanie oczekiwane
        }

        list.insert(0, VAULE_C);

        try {
            list.set(1, VAULE_C);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // zachowanie oczekiwane
        }
    }

    // Usuwanie jedynego elementu
    public void testDeleteOnlyElement() {
        List list = createList();

        list.add(VAULE_A);

        assertEquals(1, list.size());
        assertSame(VAULE_A, list.get(0));

        assertSame(VAULE_A, list.delete(0));

        assertEquals(0, list.size());
    }

    // Usuniecie pierwszego elementu
    public void testDeleteFirstElement() {
        List list = createList();

        list.add(VAULE_A);
        list.add(VAULE_B);
        list.add(VAULE_C);

        assertEquals(3, list.size());
        assertSame(VAULE_A, list.get(0));
        assertSame(VAULE_B, list.get(1));
        assertSame(VAULE_C, list.get(2));

        assertSame(VAULE_A, list.delete(0));

        assertSame(2, list.size());
        assertSame(VAULE_B, list.get(0));
        assertSame(VAULE_C, list.get(1));
    }

    public void testDeleteLastElement() {
        List list = createList();

        list.add(VAULE_A);
        list.add(VAULE_B);
        list.add(VAULE_C);

        assertEquals(3, list.size());
        assertSame(VAULE_A, list.get(0));
        assertSame(VAULE_B, list.get(1));
        assertSame(VAULE_C, list.get(2));

        assertSame(VAULE_C, list.delete(2));

        assertEquals(2, list.size());
        assertSame(VAULE_A, list.get(0));
        assertSame(VAULE_B, list.get(1));
    }

    // Usuniecie posredniego elementu
    public void testDeleteMiddleElement() {
        List list = createList();

        list.add(VAULE_A);
        list.add(VAULE_B);
        list.add(VAULE_C);

        assertEquals(3, list.size());
        assertSame(VAULE_A, list.get(0));
        assertSame(VAULE_B, list.get(1));
        assertSame(VAULE_C, list.get(2));

        assertSame(VAULE_B, list.delete(1));

        assertEquals(2, list.size());
        assertSame(VAULE_A, list.get(0));
        assertSame(VAULE_C, list.get(1));
    }

    public void testDeleteOutOfBounds() {
        List list = createList();

        try {
            list.delete(-1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // zachowanie oczekiwane
        }

        try {
            list.delete(0);
            fail();
        } catch (IndexOutOfBoundsException e) {
            // zachowanie oczekiwane
        }
    }

    // Usuwanie po wartosci
    public void testDeleteByVaLue() {
        List list = createList();

        list.add(VAULE_A);
        list.add(VAULE_B);
        list.add(VAULE_A);

        assertEquals(3, list.size());
        assertSame(VAULE_A, list.get(0));
        assertSame(VAULE_B, list.get(1));
        assertSame(VAULE_A, list.get(2));

        assertTrue(list.delete(VAULE_A));

        assertEquals(2, list.size());
        assertSame(VAULE_B, list.get(0));
        assertSame(VAULE_A, list.get(1));

        assertTrue(list.delete(VAULE_A));

        assertEquals(1, list.size());
        assertSame(VAULE_B, list.get(0));

        assertFalse(list.delete(VAULE_C));

        assertEquals(1, list.size());
        assertSame(VAULE_B, list.get(0));

        assertTrue(list.delete(VAULE_B));

        assertEquals(0, list.size());
    }

    // Iterowanie pustej listy
    public void testEmptyIteration() {
        List list = createList();

        Iterator iterator = list.iterator();
        assertTrue(iterator.isDone());

        try {
            iterator.current();
            fail();
        } catch (IteratorOutOfBoundsException e) {
            // zachowanie oczekiwane
        }
    }

    public void testForwardIteration() {
        List list = createList();

        list.add(VAULE_A);
        list.add(VAULE_B);
        list.add(VAULE_C);

        Iterator iterator = list.iterator();

        iterator.first();
        assertFalse(iterator.isDone());
        assertSame(VAULE_A, iterator.current());

        iterator.next();
        assertFalse(iterator.isDone());
        assertSame(VAULE_B, iterator.current());

        iterator.next();
        assertFalse(iterator.isDone());
        assertSame(VAULE_C, iterator.current());

        iterator.next();
        assertTrue(iterator.isDone());

        try {
            iterator.current();
            fail();
        } catch (IteratorOutOfBoundsException e) {
            // zachowanie oczekiwane
        }
    }

    public void testReverseIteration() {
        List list = createList();

        list.add(VAULE_A);
        list.add(VAULE_B);
        list.add(VAULE_C);

        Iterator iterator = list.iterator();

        iterator.last();
        assertFalse(iterator.isDone());
        assertSame(VAULE_C, iterator.current());

        iterator.previous();
        assertFalse(iterator.isDone());
        assertSame(VAULE_B, iterator.current());

        iterator.previous();
        assertFalse(iterator.isDone());
        assertSame(VAULE_A, iterator.current());

        iterator.previous();
        assertTrue(iterator.isDone());

        try {
            iterator.current();
            fail();
        } catch (IteratorOutOfBoundsException e) {
            // zachowanie oczekiwane
        }
    }

    public void testIndexOf() {
        List list = createList();

        list.add(VAULE_A);
        list.add(VAULE_B);
        list.add(VAULE_A);

        assertEquals(0, list.indexOf(VAULE_A));
        assertEquals(1, list.indexOf(VAULE_B));
        assertEquals(-1, list.indexOf(VAULE_C));
    }

    public void testContains() {
        List list = createList();

        list.add(VAULE_A);
        list.add(VAULE_B);
        list.add(VAULE_A);

        assertTrue(list.contains(VAULE_A));
        assertTrue(list.contains(VAULE_B));
        assertFalse(list.contains(VAULE_C));
    }

    public void testClear() {
        List list = createList();

        list.add(VAULE_A);
        list.add(VAULE_B);
        list.add(VAULE_C);

        assertFalse(list.isEmpty());
        assertEquals(3, list.size());

        list.clear();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }
}
