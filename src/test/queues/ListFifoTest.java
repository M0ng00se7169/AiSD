package test.queues;

import main.queues.EmptyQueueException;
import main.queues.ListFifoQueue;
import main.queues.Queue;

public class ListFifoTest extends AbstractFifoQueueTestCase {
    private static final String VALUE_A = "A";
    private static final String VALUE_B = "B";
    private static final String VALUE_C = "C";

    public Queue queue = createFifoQueue();

    @Override
    protected Queue createFifoQueue() {
        return new ListFifoQueue();
    }

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
        queue.enqueue(VALUE_A);
        queue.enqueue(VALUE_C);

        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());

        assertSame(VALUE_B, queue.dequeue());
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());

        assertSame(VALUE_A, queue.dequeue());
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());

        assertSame(VALUE_C, queue.dequeue());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());

        try {
            queue.dequeue();
            fail(); // zachowanie nieoczekiwane
        } catch (EmptyQueueException e) {
            // zachowanie oczekiwane
        }
    }

    public void testClear() {
        queue.enqueue(VALUE_A);
        queue.enqueue(VALUE_B);
        queue.enqueue(VALUE_C);

        assertFalse(queue.isEmpty());

        queue.clear();

        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());

        try {
            queue.dequeue();
            fail(); // zachowanie nieoczekiwane
        } catch (EmptyQueueException e) {
            // zachowanie oczekiwane
        }
    }
}
