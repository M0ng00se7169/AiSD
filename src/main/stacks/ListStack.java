package main.stacks;

import main.lists.LinkedList;
import main.lists.List;
import main.queues.EmptyQueueException;

public class ListStack implements Stack {
    /** Lista, na bazie której implementowany jest stos */
    private final List list = new LinkedList();

    @Override
    public void push(Object value) {
        list.add(value);
    }

    @Override
    public Object pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.delete(list.size() - 1);
    }

    @Override
    public Object peek() {
        Object result = pop();
        push(result);
        return result;
    }

    @Override
    public void enqueue(Object value) {
        push(value);
    }

    @Override
    public Object dequeue() throws EmptyQueueException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return pop();
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
