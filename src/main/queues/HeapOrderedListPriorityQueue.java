package main.queues;

import main.lists.ArrayList;
import main.lists.List;
import main.sort.Comparator;

public class HeapOrderedListPriorityQueue implements Queue {
    /** lista przechowująca elementy stogu */
    private final List list;

    /** Komparator wyznaczający priorytet elementów */
    private final Comparator comparator;

    /**
     * Konstruktor
     * @param comparator - Komparator Naturalny
     */
    public HeapOrderedListPriorityQueue(Comparator comparator) {
        assert comparator != null : "Nie określono komparatora";
        this.comparator = comparator;
        this.list = new ArrayList();
    }

    @Override
    public void enqueue(Object value) {
        list.add(value);
        swim(list.size() - 1);
    }

    @Override
    public Object dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        Object result = list.get(0);
        if (list.size() > 1) {
            list.set(0, list.get(list.size() - 1));
            sink(0);
        }

        list.delete(list.size() - 1);
        return result;
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

    private void swim(int index) {
        if (index == 0)
            return;
        int parent = (index - 1) / 2;
        if (comparator.compare(list.get(index), list.get(parent)) > 0) {
            swap(index, parent);
            swim(parent);
        }
    }

    private void swap(int index, int parent) {
        Object temp = list.get(index);
        list.set(index, list.get(parent));
        list.set(parent, temp);
    }

    private void sink(int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        if (left >= list.size())
            return;

        int largestChild = left;
        if (right < list.size()) {
            if (comparator.compare(list.get(left), list.get(right)) < 0) {
                largestChild = right;
            }
        }

        if (comparator.compare(list.get(index), list.get(largestChild)) < 0) {
            swap(index, largestChild);
            sink(largestChild);
        }
    }
}
