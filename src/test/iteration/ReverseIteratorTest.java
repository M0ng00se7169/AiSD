package test.iteration;

import junit.framework.TestCase;
import main.iteration.ArrayIterator;
import main.iteration.IteratorOutOfBoundsException;
import main.iteration.ReverseIterator;

public class ReverseIteratorTest extends TestCase {

    private static final Object[] ARRAY = new Object[] {"A", "B", "C"};

    public void testForwardsIterationToBackwards() {
        ReverseIterator iterator = new ReverseIterator(new ArrayIterator(ARRAY));

        iterator.first();
        assertFalse(iterator.isDone());
        assertSame(ARRAY[2], iterator.current());

        iterator.next();
        assertFalse(iterator.isDone());
        assertSame(ARRAY[1], iterator.current());

        iterator.next();
        assertFalse(iterator.isDone());
        assertSame(ARRAY[0], iterator.current());

        iterator.next();
        assertTrue(iterator.isDone());
        try {
            iterator.current();
            fail();
        } catch (IteratorOutOfBoundsException e) {
            // zachowanie oczekiwane
        }
    }

    public void testBackwardsIterationToForwards() {
        ReverseIterator iterator = new ReverseIterator(new ArrayIterator(ARRAY));

        iterator.last();
        assertFalse(iterator.isDone());
        assertSame(ARRAY[0], iterator.current());

        iterator.previous();
        assertFalse(iterator.isDone());
        assertSame(ARRAY[1], iterator.current());

        iterator.previous();
        assertFalse(iterator.isDone());
        assertSame(ARRAY[2], iterator.current());

        iterator.previous();
        assertTrue(iterator.isDone());
        try {
            iterator.current();
            fail();
        } catch (IteratorOutOfBoundsException e) {
            // zachowanie oczekiwane
        }
    }
}
