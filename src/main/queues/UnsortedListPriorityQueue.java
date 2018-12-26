package main.queues;

import main.lists.LinkedList;
import main.lists.List;
import main.sort.Comparator;

public class UnsortedListPriorityQueue implements Queue {
    /** Lista przechowująca elementy */
    private final List list;

    /** Komparator wyznaczający priorytet elementów */
    private final Comparator comparator;

    public UnsortedListPriorityQueue(Comparator comparator) {
        assert comparator != null : "Nie określono komparatora";
        this.comparator = comparator;
        this.list = new LinkedList();
    }

    @Override
    public void enqueue(Object value) {
        list.add(value);
    }

    @Override
    public Object dequeue() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException();
        return list.delete(getIndexOfLargestElement());
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

    private int getIndexOfLargestElement() {
        int result = 0;

        for (int i = 1; i < list.size(); i++) {
            if (comparator.compare(list.get(i), list.get(result)) > 0) {
                result = i;
            }
        }

        return result;
    }
}
