package main.queues;

import main.lists.LinkedList;
import main.lists.List;
import main.sort.Comparator;

public class SortedListPriorityQueue implements Queue {

    /** Lista przechowująca elementy */
    private final List list;

    /** Komparator wyznaczający priorytet elementów */
    private final Comparator comparator;

    public SortedListPriorityQueue(Comparator comparator) {
        assert comparator != null : "nie określono komparatora";
        this.comparator = comparator;
        this.list = new LinkedList();
    }

    @Override
    public void enqueue(Object value) {
        int pos = list.size();
        while (pos > 0 && comparator.compare(list.get(pos - 1), value) > 0) {
            --pos;
        }
        list.insert(pos, value);
    }

    @Override
    public Object dequeue() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException();
        return list.delete(list.size() - 1);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
