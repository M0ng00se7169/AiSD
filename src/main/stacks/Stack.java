package main.stacks;

import main.queues.Queue;

public interface Stack extends Queue {
    public void push(Object value);
    public Object pop() throws EmptyStackException;
    public Object peek();
    public void clear();
    public int size();
    public boolean isEmpty();
}
