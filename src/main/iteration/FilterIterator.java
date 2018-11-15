package main.iteration;

public class FilterIterator implements Iterator {
    private final Iterator iterator;
    private final Predicate predicate;

    public FilterIterator(Iterator iterator, Predicate predicate) {
        assert iterator != null : "nie okreslono iteratora";
        assert predicate != null : "nie okreslono predykatora";
        this.iterator = iterator;
        this.predicate = predicate;
    }


    @Override
    public void first() {
        iterator.first();
        filterForwards();
    }

    @Override
    public void last() {
        iterator.last();
        filterBackwards();
    }

    @Override
    public boolean isDone() {
        return iterator.isDone();
    }

    @Override
    public void next() {
        iterator.next();
        filterForwards();
    }

    @Override
    public void previous() {
        iterator.previous();
        filterBackwards();
    }

    @Override
    public Object current() throws IteratorOutOfBoundsException {
        return iterator.current();
    }

    private void filterForwards() {
        while (!iterator.isDone() && !predicate.evaluate(iterator.current())) {
            iterator.next();
        }
    }

    private void filterBackwards() {
        while (!iterator.isDone() && !predicate.evaluate(iterator.current())) {
            iterator.previous();
        }
    }
}
