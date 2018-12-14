package test.queues;

import junit.framework.TestCase;
import main.queues.Queue;

public abstract class AbstractFifoQueueTestCase extends TestCase {
    private static final String VALUE_A = "A";
    private static final String VALUE_B = "B";
    private static final String VALUE_C = "C";

    private Queue queue;

    protected void setUp() throws Exception {
        super.setUp();

        queue = createFifoQueue();
    }

    protected abstract Queue createFifoQueue();
}
