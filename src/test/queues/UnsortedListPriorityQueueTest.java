package test.queues;

import main.queues.Queue;
import main.queues.UnsortedListPriorityQueue;
import main.sort.NaturalComparator;

public class UnsortedListPriorityQueueTest extends AbstractPriorityQueueTest {
    @Override
    protected Queue createQueue(NaturalComparator instance) {
        return new UnsortedListPriorityQueue(instance);
    }
}
