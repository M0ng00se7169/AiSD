package main.binarySearch;

import main.sort.Comparator;

public class BinarySearchTree {

    /** Komparator wyznaczający porządek wartości reprezentowanych przez węzły */
    private final Comparator comparator;

    /** Wskazanie na korzeń lub wartość pusta dla pustego drzewa */
    private Node root;

    /**
     * Konstruktor
     * @param comparator: Komparator wyznaczający porządek wartości
     */
    public BinarySearchTree(Comparator comparator) {
        assert comparator != null : "Nie określono komparatora";
        this.comparator = comparator;
    }

    /**
     * Poszukiwanie wartości w drzewie
     *
     * @param value: Szukana wartość
     * @return: Węzeł reprezentujący wartość lub wartość pusta
     */
    public Node search(Object value) {
        assert value != null : "Nie podano wartości";

        Node node = root;

        while (node != null) {
            int cmp = comparator.compare(value, node.getValue());
            if (cmp == 0) {
                break;
            }

            node = cmp < 0 ? node.getSmaller() : node.getLarger();
        }

        return node;
    }

    /**
     * Wstawianie węzła do drzewa
     *
     * @param value: wartość do wstawienia
     * @return: węzeł reprezentujący wartość
     */
    public Node insert(Object value) {
        Node parent = null;
        Node node = root;
        int cmp = 0;

        while (node != null) {
            parent = node;
            cmp = comparator.compare(value, node.getValue());
            node = cmp <= 0 ? node.getSmaller() : node.getLarger();
        }

        Node inserted = new Node(value);
        inserted.setParent(parent);

        if (parent == null) {
            root = inserted;
        } else if (cmp < 0) {
            parent.setSmaller(inserted);
        } else {
            parent.setLarger(inserted);
        }

        return inserted;
    }

    /**
     * Usuwanie wartości z drzewa
     *
     * @param value: Wartość do usunięcia
     * @return: usunięty wynik lub wartość pusta
     */
    public Node delete(Object value) {
        Node node = search(value);
        if (node == null) {
            return null;
        }

        Node deleted = node.getSmaller() != null && node.getLarger() != null
                ? node.successor() : node;

        assert deleted  != null : "Podano pustą wartość";

        Node replacement = deleted.getSmaller() != null ? deleted.getSmaller() : deleted.getLarger();
        if (replacement != null) {
            replacement.setParent(deleted.getParent());
        }

        if (deleted == root) {
            root = replacement;
        } else if (deleted.isSmaller()) {
            deleted.getParent().setSmaller(replacement);
        } else {
            deleted.getParent().setLarger(replacement);
        }

        if (deleted != node) {
            Object deletedValue = node.getValue();
            node.setValue(deleted.getValue());
            deleted.setValue(deletedValue);
        }

        return deleted;
    }

    /**
     * Zwraca korzeń drzewa
     *
     * @return: Korzeń drzewa lub wartość pusta
     */
    public Node getRoot() {
        return root;
    }
}
