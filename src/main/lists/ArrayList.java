package main.lists;

import main.iteration.ArrayIterator;
import main.iteration.Iterator;

public class ArrayList implements List {

    // Domyslny rozmiar początkowy tablicy
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private final int initialCapacity;
    private Object[] array;
    private int size;

    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        assert initialCapacity > 0 : "Początkowy rozmiar tablicy musi być dodatni";

        this.initialCapacity = initialCapacity;
        clear();
    }

    @Override
    public Iterator iterator() {
        return new ArrayIterator(array, 0, size);
    }

    @Override
    public void insert(int index, Object value) throws IndexOutOfBoundsException {
        assert value != null : "Nie można wstawiać wartości pustych";

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        ++size;
    }

    @Override
    public void add(Object value) {
        insert(size, value);
    }

    @Override
    public Object delete(int index) throws IndexOutOfBoundsException {
        checkOutOfBounds(index);
        Object value = array[index];
        int copyFromIndex = index + 1;
        if (copyFromIndex < size) {
            System.arraycopy(array, copyFromIndex, array, index, size - copyFromIndex);
        }
        array[--size] = null;
        return value;
    }

    @Override
    public boolean delete(Object value) {
        int index = indexOf(value);
        if (index != -1) {
            delete(index);
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        this.array = new Object[initialCapacity];
        this.size = 0;
    }

    @Override
    public Object set(int index, Object value) throws IndexOutOfBoundsException {
        assert value != null : "wartość nie może być pusta";
        checkOutOfBounds(index);
        Object oldValue = array[index];
        array[index] = value;
        return oldValue;
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        checkOutOfBounds(index);
        return array[index];
    }

    @Override
    public int indexOf(Object value) {
        assert value != null : "Wartość nie może być pusta";

        for (int i = 0; i < size; i++) {
            if (value.equals(array[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void ensureCapacity(int capacity) {
        assert capacity > 0 : "rozmiar musi być dodatni";

        if (array.length < capacity) {
            Object[] copy = new Object[capacity + capacity / 2];
            System.arraycopy(array, 0, copy, 0, size);
            array = copy;
        }
    }

    private void checkOutOfBounds(int index) {
        if (isOutOfBounds(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isOutOfBounds(int index) {
        return index < 0 || index >= size;
    }
}
