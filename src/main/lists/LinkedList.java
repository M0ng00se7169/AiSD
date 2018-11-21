package main.lists;

import main.iteration.Iterator;
import main.iteration.IteratorOutOfBoundsException;

public class LinkedList implements List {
    /** element-wartownik posiadajacy wskazniki na pierwszy i ostatni element listy */
    private final Element headAndTale = new Element(null);

    private int size; // Rozmiar listy

    public LinkedList() {
        clear();
    }

    @Override
    public void insert(int index, Object value) throws IndexOutOfBoundsException {
        assert value != null : "nie można wstawić pustych wartości";

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Element element = new Element(value);
        element.attachBefore(getElement(index));
        ++size;
    }

    @Override
    public void add(Object value) {
        insert(size(), value);
    }

    @Override
    public Object delete(int index) throws IndexOutOfBoundsException {
        checkOutOfBounds(index);
        Element element = getElement(index);
        element.detach();
        --size;
        return element.getValue();
    }

    @Override
    public boolean delete(Object value) {
        assert value != null : "w liście nie ma wartości pustych";

        for (Element e = headAndTale.getNext(); e != headAndTale; e = e.getNext()) {
            if (value.equals(e.getValue())) {
                e.detach();
                --size;
                return true;
            }
        }

        return false;
    }

    @Override
    public void clear() {
        headAndTale.setPrevious(headAndTale);
        headAndTale.setNext(headAndTale);
        size = 0;
    }

    @Override
    public Object set(int index, Object value) throws IndexOutOfBoundsException {
        assert value != null : "Nie można umieszczać w liście wartości pustych";

        checkOutOfBounds(index);
        Element element = getElement(index);
        Object oldValue = element.getValue();
        element.setValue(value);
        return oldValue;
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        checkOutOfBounds(index);
        return getElement(index).getValue();
    }

    @Override
    public int indexOf(Object value) {
        assert value != null : "w nie ma pustych wartości";

        int index = 0;

         for(Element e = headAndTale.getNext(); e != headAndTale; e = e.getNext()){
            if (value.equals(e.getValue())) {
                return index;
            }
            ++index;
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

    @Override
    public Iterator iterator() {
        return new ValueIterator();
    }

    private Element getElement(int index) {
        Element element = headAndTale.getNext();

        for (int i = index; i > 0; i--) {
            element = element.getNext();
        }
        return element;
    }

    private void checkOutOfBounds(int index) {
        if (isOutOfBounds(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isOutOfBounds(int index) {
        return index < 0 || index >= size();
    }

    private static final class Element {

        private Object value;
        private Element previous;
        private Element next;

        public Element(Object value){
            this.value = value;
        }

        public void attachBefore(Element next) {
            assert next != null : "wskażnik na element następny nie może być pusty";

            Element previous = next.getPrevious();
            setNext(next);
            setPrevious(previous);

            next.setPrevious(this);
            previous.setNext(this);
        }

        public void detach() {
            previous.setNext(next);
            next.setPrevious(previous);
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Element getPrevious() {
            return previous;
        }

        public void setPrevious(Element previous) {
            assert previous != null : "Wskażnik na element poprzedni nie może być pusty";
            this.previous = previous;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            assert previous != null : "Wskażnik na element następny nie może być pusty";
            this.next = next;
        }
    }

    private final class ValueIterator implements Iterator {
        private Element current = headAndTale;

        @Override
        public void first() {
            current = headAndTale.getNext();
        }

        @Override
        public void last() {
            current = headAndTale.getPrevious();
        }

        @Override
        public boolean isDone() {
            return current == headAndTale;
        }

        @Override
        public void next() {
            current = current.getNext();
        }

        @Override
        public void previous() {
            current = current.getPrevious();
        }

        @Override
        public Object current() throws IteratorOutOfBoundsException {
            if (isDone()) {
                throw new IteratorOutOfBoundsException();
            }
            return current.getValue();
        }
    }
}
