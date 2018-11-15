package main.iteration;

public class ArrayIterator implements Iterator {
    private final Object[] array;
    private final int first, last;
    private int current = -1;

    public ArrayIterator(Object[] array, int start, int length) {
        assert array != null : "Nie Określono tablicy";
        assert start >= 0 : "Ujemny indeks elementu poczatkowego";
        assert start < array.length : "index elementu poczatkowego poza tablica";
        assert length >= 0 : "ujemna dlugosc tablicy";

        this.array = array;
        this.first = start;
        this.last = start + length - 1;

        assert last < array.length : "wycinek wykracza poza tablice";
    }

    public ArrayIterator(Object[] array) {
        this.array = array;
        assert array != null : "nie okreslono tablicy";
        array = this.array;
        first = 0;
        last = array.length - 1;
    }

    @Override
    public void first() {
        current = this.first;
    }

    @Override
    public void last() {
        current = this.last;
    }

    @Override
    public boolean isDone() {
        return current < first || current > last;
    }

    @Override
    public void next() {
        ++current;
    }

    @Override
    public void previous() {
        --current;
    }

    @Override
    public Object current() throws IteratorOutOfBoundsException {
        if (isDone()) {
            throw new IteratorOutOfBoundsException();
        }
        return array[current];
    }
}
