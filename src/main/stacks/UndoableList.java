package main.stacks;

import main.iteration.Iterator;
import main.lists.List;

public class UndoableList implements List {
    /** Odnośna lista */
    private final List list;

    /** Stos do operacji wycofywania */
    private final Stack undoStack = new ListStack();

    public UndoableList(List list) {
        assert list != null : "nie określono listy";
        this.list = list;
    }

    @Override
    public void insert(int index, Object value) throws IndexOutOfBoundsException {
        list.insert(index, value);
        undoStack.push(new UndoInsertAction(index));
    }

    @Override
    public void add(Object value) {
        insert(size(), value);
    }

    @Override
    public Object delete(int index) throws IndexOutOfBoundsException {
        Object value = list.delete(index);
        undoStack.push(new UndoDeleteAction(index, value));
        return value;
    }

    @Override
    public boolean delete(Object value) {
        int index = indexOf(value);
        if (index == 1) {
            return false;
        }

        delete(index);
        return true;
    }

    @Override
    public void clear() {
        list.clear();
        undoStack.clear();
    }

    @Override
    public Object set(int index, Object value) throws IndexOutOfBoundsException {
        Object originalValue = list.set(index, value);
        undoStack.push(new UndoSetAction(index, originalValue));
        return originalValue;
    }

    public void undo() throws EmptyStackException {
        ((UndoAction) undoStack.pop()).execute();
    }

    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
        return list.get(index);
    }

    @Override
    public int indexOf(Object value) {
        return list.indexOf(value);
    }

    @Override
    public boolean contains(Object value) {
        return list.contains(value);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator iterator() {
        return list.iterator();
    }

    public String toString() {
        return list.toString();
    }

    public boolean equals(Object object) {
        return list.equals(object);
    }

    private static interface UndoAction {
        public void execute();
    }

    private final class UndoInsertAction implements UndoAction {

        private final int index;

        public UndoInsertAction(int index) {
            this.index = index;
        }

        public void execute() {
            list.delete(index);
        }
    }

    private final class UndoDeleteAction implements UndoAction {
        private final int index;
        private final Object value;

        public UndoDeleteAction(int index, Object value) {
            this.index = index;
            this.value = value;
        }

        public void execute() {
            list.insert(index, value);
        }
    }

    private final class UndoSetAction implements UndoAction {
        private final int index;
        private final Object value;

        public UndoSetAction(int index, Object value) {
            this.index = index;
            this.value = value;
        }

        public void execute() {
            list.set(index, value);
        }
    }
}
