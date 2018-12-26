package test.queues;

import junit.framework.TestCase;
import main.queues.EmptyQueueException;
import main.queues.Queue;
import main.sort.NaturalComparator;

public abstract class AbstractPriorityQueueTest extends TestCase {
    private static final String VALUE_A = "A";
    private static final String VALUE_B = "B";
    private static final String VALUE_C = "C";
    private static final String VALUE_D = "D";
    private static final String VALUE_E = "E";

    private Queue queue;

    protected void setUp() throws Exception {
        super.setUp();

        this.queue = createQueue(NaturalComparator.INSTANCE);
    }

    protected void tearDown() throws Exception {
        this.queue = null;

        super.tearDown();
    }

    protected abstract Queue createQueue(NaturalComparator instance);

    public void testAccessAnEmptyQueue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());

        try {
            queue.dequeue();
            fail(); // zachowanie nieoczekiwane
        } catch (EmptyQueueException e) {
            // zachowanie oczekiwane
        }
    }

    public void testEnqueueDequeue() {
        queue.enqueue(VALUE_B);
        queue.enqueue(VALUE_D);
        queue.enqueue(VALUE_A);

        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());

        assertSame(VALUE_D, queue.dequeue());
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());

        assertSame(VALUE_B, queue.dequeue());
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());

        queue.enqueue(VALUE_E);
        queue.enqueue(VALUE_C);

        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());

        assertSame(VALUE_E, queue.dequeue());
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());

        assertSame(VALUE_C, queue.dequeue());
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());

        assertSame(VALUE_A, queue.dequeue());
        assertEquals(0, queue.size());
        assertFalse(queue.isEmpty());
    }

    public void testClear() {
        queue.enqueue(VALUE_A);
        queue.enqueue(VALUE_B);
        queue.enqueue(VALUE_C);

        assertFalse(queue.isEmpty());

        queue.clear();

        assertTrue(queue.isEmpty());
    }
}
