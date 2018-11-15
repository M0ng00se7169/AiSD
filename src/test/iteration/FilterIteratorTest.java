package test.iteration;

import junit.framework.TestCase;
import main.iteration.*;

public class FilterIteratorTest extends TestCase {
    private static final Object[] ARRAY = new Object[] {"A", "B", "C", "D", "E", "F"};

    public void testForwardsIterationIncludesItemsPredicateReturnsTrue() {
        Iterator expectedIterator = new ArrayIterator(ARRAY);
        Iterator underlyingIterator = new ArrayIterator(ARRAY);

        Iterator iterator = new FilterIterator(underlyingIterator, new DummyPredicate(expectedIterator, true));

        iterator.first();
        assertFalse(iterator.isDone());
        assertSame(ARRAY[0], iterator.current());

        for (int i = 1; i < ARRAY.length; i++) {
            iterator.next();
            assertFalse(iterator.isDone());
            assertSame(ARRAY[i], iterator.current());
        }

        iterator.next();
        assertTrue(iterator.isDone());
        try {
            iterator.current();
            fail();
        } catch (IteratorOutOfBoundsException e) {
            // Zachowanie oczekiwane
        }

        assertTrue(expectedIterator.isDone());
        assertTrue(underlyingIterator.isDone());
    }

    public void testForwardsIterationExcludesItemsPredicateReturnsFalse() {
        Iterator expectedIterator = new ArrayIterator(ARRAY);
        Iterator underlyingIterator = new ArrayIterator(ARRAY);

        Iterator iterator = new FilterIterator(underlyingIterator, new DummyPredicate(expectedIterator, false));

        iterator.first();
        assertTrue(iterator.isDone());
        try {
            iterator.current();
            fail();
        } catch (IteratorOutOfBoundsException e) {
            // Zachowanie oczekiwane
        }

        assertTrue(expectedIterator.isDone());
        assertTrue(underlyingIterator.isDone());
    }

    public void testBackwardsIterationIncludesItemsPredicateReturnsTrue() {
        Iterator expectedIterator = new ReverseIterator(new ArrayIterator(ARRAY));
        Iterator underlyingIterator = new ArrayIterator(ARRAY);

        Iterator iterator = new FilterIterator(underlyingIterator, new DummyPredicate(expectedIterator, true));

        iterator.last();
        assertFalse(iterator.isDone());
        assertSame(ARRAY[5], iterator.current());

        for (int i = 4; i > -1; i--) {
            iterator.previous();
            assertFalse(iterator.isDone());
            assertSame(ARRAY[i], iterator.current());
        }

        iterator.previous();
        assertTrue(iterator.isDone());
        try {
            iterator.current();
            fail();
        } catch (IteratorOutOfBoundsException e) {
            // Zachowanie oczekiwane
        }

        assertTrue(expectedIterator.isDone());
        assertTrue(underlyingIterator.isDone());
    }

    public void testBackwardsIterationExcludesItemsPredicateReturnsFalse() {
        Iterator expectedIterator = new ReverseIterator(new ArrayIterator(ARRAY));
        Iterator underlyingIterator = new ArrayIterator(ARRAY);

        Iterator iterator = new FilterIterator(underlyingIterator, new DummyPredicate(expectedIterator, false));

        iterator.last();
        assertTrue(iterator.isDone());
        try {
            iterator.current();
            fail();
        } catch (IteratorOutOfBoundsException e) {
            // Zachowanie oczekiwane
        }

        assertTrue(expectedIterator.isDone());
        assertTrue(underlyingIterator.isDone());
    }

    private static final class DummyPredicate implements Predicate {
        private final Iterator iterator;
        private final boolean result;

        public DummyPredicate(Iterator iterator, boolean result) {
            this.iterator = iterator;
            this.result = result;
            iterator.first();
        }

        @Override
        public boolean evaluate(Object object) {
            assertSame(iterator.current(), object);
            iterator.next();
            return result;
        }
    }
}
