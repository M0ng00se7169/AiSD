package test.queues;

import main.queues.Queue;
import main.queues.SortedListPriorityQueue;
import main.sort.NaturalComparator;

public class SortedListPriorityQueueTest extends AbstractPriorityQueueTest {
    @Override
    protected Queue createQueue(NaturalComparator instance) {
        return new SortedListPriorityQueue(instance);
    }
}
