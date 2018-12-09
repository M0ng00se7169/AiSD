package main.queues;

import main.lists.LinkedList;
import main.lists.List;

public class ListFifoQueue implements Queue {
    /** Lista na bazie której implementowana jest kolejka */
    private final List list;

    /**
     * Konstruktor tworzący kolejkę na bazie wskazanej listy
     *
     */
    public ListFifoQueue(List list) {
        assert list != null : "Nie określono listy";
        this.list = list;
    }

    /**
     * Domyślny konstruktor twarzący kolejkę na bazie
     * utworzonej ad hoc listy wiązanej
     */
    public ListFifoQueue() {
        this(new LinkedList());
    }

    @Override
    public void enqueue(Object value) {
        list.add(value);
    }

    @Override
    public Object dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return list.delete(0);
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
