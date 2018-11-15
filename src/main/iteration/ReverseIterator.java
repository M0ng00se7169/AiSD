package main.iteration;

public class ReverseIterator implements Iterator {
    private final Iterator iterator;

    public ReverseIterator(Iterator iterator) {
        assert iterator != null : "nie okreslono iteratora";
        this.iterator = iterator;
    }

    @Override
    public void first() {
        iterator.last();
    }

    @Override
    public void last() {
        iterator.first();
    }

    @Override
    public boolean isDone() {
        return iterator.isDone();
    }

    @Override
    public void next() {
        iterator.previous();
    }

    @Override
    public void previous() {
        iterator.next();
    }

    @Override
    public Object current() throws IteratorOutOfBoundsException {
        return iterator.current();
    }
}
