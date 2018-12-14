package main.queues;

public class BlockingQueue implements Queue {
    /** Obiekt synchronizujący */
    private final Object mutex = new Object();

    /** Odnośna kolejka */
    private final Queue queue;

    /** Maksymalny dopuszczalny rozmiar kolejki */
    private final int maxSize;

    /**
     * Konstruktor
     * @param queue = Odnośna kolejka,
     * @param maxSize = Maksymalny rozmiar kolejki
     */

    public BlockingQueue(Queue queue, int maxSize) {
        assert queue != null : "nie określono kolejki";
        assert maxSize > 0 : "maksymalny rozmiar musi być dodatni";

        this.queue = queue;
        this.maxSize = maxSize;
    }

    /**
     * Konstruktor
     * @param queue = Odnośna kolejka
     */
    public BlockingQueue(Queue queue) {
        this(queue, Integer.MAX_VALUE);
    }

    @Override
    public void enqueue(Object value) {
        synchronized (mutex) {
            while (size() == maxSize) {
                waitForNotification();
            }
            queue.enqueue(value);
            mutex.notifyAll();
        }
    }

    private void waitForNotification() {
        try {
            mutex.wait();
        } catch (InterruptedException e) {
            // ignoruj wyjatek
        }
    }

    @Override
    public Object dequeue() throws EmptyQueueException {
        synchronized (mutex) {
            while (isEmpty()) {
                waitForNotification();
            }
            Object value = queue.dequeue();
            mutex.notifyAll();
            return value;
        }
    }

    @Override
    public void clear() {
        synchronized (mutex) {
            queue.clear();
            mutex.notifyAll();
        }
    }

    @Override
    public int size() {
        synchronized (mutex) {
            return queue.size();
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (mutex) {
            return queue.isEmpty();
        }
    }
}
